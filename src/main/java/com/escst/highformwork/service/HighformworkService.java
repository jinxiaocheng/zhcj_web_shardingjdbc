package com.escst.highformwork.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.highformwork.bean.CollectorBean;
import com.escst.highformwork.bean.CollectorTimeBean;
import com.escst.highformwork.bean.FlowBean;
import com.escst.highformwork.bean.FlowCollectorBean;
import com.escst.highformwork.entity.CollectorEntity;
import com.escst.highformwork.entity.FlowEntity;
import com.escst.highformwork.mapper.HighformworkMapper;
import com.escst.highformwork.vo.CollectorVo;
import com.escst.highformwork.vo.FlowVo;
import com.escst.highformwork.vo.UpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author dwj
 * @desc
 * @date 17:13 16/7/2018
 */
@Service
public class HighformworkService {

    @Autowired
    private HighformworkMapper highformworkMapper;

    @Autowired
    private FileService fileService;

    /**
     * @param entity
     * @return
     * @desc 获取采集器列表
     * @author dwj
     * @date 16/7/2018 17:17
     */
    public PageVo queryList(CollectorEntity entity) {
        PageVo vo = new PageVo();
        List<CollectorVo> list = new ArrayList<CollectorVo>();
        try {
            int pageNum = entity.getPage();
            int pageSize = entity.getRowNum();
            int offset = (pageNum - 1) * pageSize;
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("constructionId", entity.getConstructionId());
            paraMap.put("name", entity.getName());
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            int count = highformworkMapper.collectorCount(paraMap);
            if (count > 0) {
                list = highformworkMapper.selectCollectorAll(paraMap);

            }
            if (!CollectionUtils.isEmpty(list)) {
                //相关数据封装到pageVo对象
                vo.setTotalRecord(count);
                vo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("获取采集器列表异常" + e.getMessage(), e);
        }
        return vo;
    }

    /**
     * @param beanList
     * @return
     * @desc 批量添加采集器
     * @author dwj
     * @date 16/7/2018 17:39
     */
    @Transactional
    public void save(CollectorBean beanList) {
        List<CollectorEntity> saveList = new ArrayList<CollectorEntity>();
        String constructionId = beanList.getConstructionId();
        String constructionName = beanList.getConstructionName();
        String createBy = beanList.getCreateBy();
        List<CollectorVo> vos = beanList.getVos();
        if (!CollectionUtils.isEmpty(vos)) {
            for (CollectorVo vo : vos) {
                CollectorEntity entity = new CollectorEntity();
                entity.setId(UuidUtils.getUuid());
                entity.setNumber(vo.getNumber());
                entity.setCreateBy(createBy);
                entity.setName(vo.getName());
                entity.setStatus(1);
                entity.setConstructionId(constructionId);
                entity.setConstructionName(constructionName);
                entity.setCreateTime(new Date());
                saveList.add(entity);
            }

        }
        //批量添加采集器
        if (!CollectionUtils.isEmpty(saveList)) {
            highformworkMapper.batchInsert(saveList);
        }

    }

    /**
     * @param bean
     * @return
     * @desc 新增流水段
     * @author dwj
     * @date 17/7/2018 16:12
     */
    @Transactional
    public void saveFlow(FlowBean bean) {
        String id = bean.getId();
        String createBy = bean.getCreateBy();
        if (StringUtils.isEmpty(id)) {
            FlowEntity entity = new FlowEntity();
            entity.setId(UuidUtils.getUuid());
            entity.setName(bean.getName());
            entity.setConstructionId(bean.getConstructionId());
            entity.setConstructionName(bean.getConstructionName());
            MultipartFile file = bean.getFile();
            //上传测点部署图
            if (file != null) {
                String fileId = fileService.uploadFile("highformwork", file);
                entity.setFileId(fileId);
            }
            entity.setCreateTime(new Date());
            highformworkMapper.insertFlow(entity);
            String collectorBeanList = bean.getIds();
            String[] strs = collectorBeanList.split(",");
            List<String> ids = Arrays.asList(strs);
            //添加到流水段采集器中间表
            List<FlowCollectorBean> list = new ArrayList<FlowCollectorBean>();
            for (String s : ids) {
                FlowCollectorBean flowCollectorBean = new FlowCollectorBean();
                flowCollectorBean.setId(UuidUtils.getUuid());
                flowCollectorBean.setFlowId(entity.getId());
                flowCollectorBean.setFlowStatus(1);
                flowCollectorBean.setCollectorId(s);
                flowCollectorBean.setCreateTime(new Date());
                flowCollectorBean.setCreateBy(createBy);
                list.add(flowCollectorBean);
            }
            //批量流水段采集器中间表
            if (!CollectionUtils.isEmpty(list)) {
                highformworkMapper.batchFlowCollector(list);
            }
            //修改采集器状态
            UpdateVo vos = new UpdateVo();
            vos.setStatus(0);
            vos.setUpdateTime(new Date());
            vos.setUpdateBy(createBy);
            vos.setIds(ids);
            highformworkMapper.batchUpdate(vos);

        } else {
            FlowEntity entity = new FlowEntity();
            entity.setId(bean.getId());
            entity.setStatus(2);
            entity.setStartTime(DateUtils.parse(bean.getStartTime(),DateUtils.TO_MINUTE));
            entity.setEndTime(DateUtils.parse(bean.getEndTime(), DateUtils.TO_MINUTE));
            entity.setUpdateTime(new Date());
            highformworkMapper.updateFlow(entity);
            Map<Object,Object> map = new HashMap<Object,Object>();
            map.put("folwId",entity.getId());
            List<String> ids = highformworkMapper.selectCollectorIdsByFlowId(map);
            //修改流水段采集器中间表
            FlowCollectorBean flowCollectorBean = new FlowCollectorBean();
            flowCollectorBean.setCreateBy(createBy);
            flowCollectorBean.setUpdateTime(new Date());
            flowCollectorBean.setFlowStatus(2);
            flowCollectorBean.setFlowId(id);
            highformworkMapper.updateFlowCollector(flowCollectorBean);

            //修改采集器状态
            UpdateVo updateVo = new UpdateVo();
            updateVo.setStatus(1);
            updateVo.setUpdateTime(new Date());
            updateVo.setUpdateBy(createBy);
            updateVo.setIds(ids);
            highformworkMapper.batchUpdate(updateVo);
        }
    }


    /**
     * @param entity
     * @return
     * @desc 获取当前工地下的流水段
     * @author dwj
     * @date 17/7/2018 16:13
     */
    public PageVo selectAllByConstructionId(FlowEntity entity) {
        PageVo vo = new PageVo();
        List<FlowVo> list = new ArrayList<FlowVo>();
        try {
            int pageNum = entity.getPage();
            int pageSize = entity.getRowNum();
            int offset = (pageNum - 1) * pageSize;
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("name", entity.getName());
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            paraMap.put("status", entity.getStatus());
            paraMap.put("constructionId", entity.getConstructionId());
            int count = highformworkMapper.flowCount(paraMap);
            if (count > 0) {
                list = highformworkMapper.selectAllByConstructionId(paraMap);
                for(FlowVo flowVo : list){
                    String startTime = flowVo.getStartTime();
                    String flowVoId = flowVo.getId();
                    if(StringUtils.isEmpty(startTime)){
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("folwId",flowVoId);
                        map.put("flowStatus",1);
                        List<String> ids = highformworkMapper.selectCollectorIdsByFlowId(map);
                        if(!CollectionUtils.isEmpty(ids)){
                            CollectorTimeBean bean = new CollectorTimeBean();
                            bean.setFlowId(flowVoId);
                            bean.setIds(ids);
                            startTime = highformworkMapper.selectMinTimeByCollectorId(bean);
                            flowVo.setStartTime(startTime);
                        }

                    }
                    List<CollectorVo> finishVos  = new ArrayList<CollectorVo>();
                    List<CollectorVo> UseVos  = new ArrayList<CollectorVo>();
                    List<CollectorVo> vos = highformworkMapper.selectCollectorByFlowId(flowVoId);
                    for(CollectorVo collectorVo : vos){
                        if(collectorVo.getStatus() ==2 ){
                            finishVos.add(collectorVo);
                        }else {
                            UseVos.add(collectorVo);
                        }
                    }
                    if(CollectionUtils.isEmpty(UseVos)){
                        flowVo.setQty(finishVos.size());
                        flowVo.setStatus(2);
                    }else {
                        flowVo.setQty(UseVos.size());
                        flowVo.setStatus(1);
                    }

                }
            }
            if (!CollectionUtils.isEmpty(list)) {
                vo.setTotalRecord(count);
                vo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("获取流水段异常" + e.getMessage(), e);
        }

        return vo;
    }


    /**
     * @param vo
     * @return
     * @desc 查看流水段详情信息
     * @author dwj
     * @date 18/7/2018 13:47
     */
    public FlowVo selectFlowDetail(FlowVo vo) {
        FlowVo flowVo = highformworkMapper.selectFlowDetail(vo.getId());
        String path ="";
        //获取测点图信息信息
        if(!StringUtils.isEmpty(flowVo.getFileId())){
            path = fileService.queryFilePathById(flowVo.getFileId());
            flowVo.setFilePath(path);
        }
        List<CollectorVo> finishVos  = new ArrayList<CollectorVo>();
        List<CollectorVo> UseVos  = new ArrayList<CollectorVo>();
        List<CollectorVo> vos = highformworkMapper.selectCollectorByFlowId(vo.getId());
        for(CollectorVo collectorVo : vos){
            if(collectorVo.getStatus() ==2 ){
                finishVos.add(collectorVo);
            }else {
                UseVos.add(collectorVo);
            }
        }
        if(CollectionUtils.isEmpty(UseVos)){
            flowVo.setCollectorVos(finishVos);
        }else {
            flowVo.setCollectorVos(UseVos);
        }

        return flowVo;
    }


    /**
     * @desc 获取流水段图片
     * @author jincheng
     * @date 16/10/2018 13:52
     */
    public String selectFilePath(String flowId) {
        String filePath = "";
        try {
            filePath = highformworkMapper.selectFilePath(flowId);
            filePath = ResourceUtil.getFileDownloadPathNew() + filePath;
        } catch (Exception e) {
            throw new EscstException("获取流水段图片异常" + e.getMessage(), e);
        }

        return filePath;
    }

    /**
     * @param constructionId
     * @return
     * @desc 获取当前工地下的有效采集器
     * @author dwj
     * @date 11/10/2018 14:22
     */
    public List<CollectorVo> getVaildCollector(String constructionId) {
        List<CollectorVo> list = new ArrayList<CollectorVo>();
        try {
            list = highformworkMapper.selectVaildCollectorByConstructionId(constructionId);
            return list;
        } catch (Exception e) {
            throw new EscstException("获取有效的采集器异常" + e.getMessage(), e);
        }
    }


    /**
     * @param entity
     * @return
     * @desc 停用或启用采集器
     * @author dwj
     * @date 11/10/2018 14:52
     */
    public void disableOrEnable(CollectorEntity entity) {
        String equipemntId = highformworkMapper.isUse(entity.getId());
        if (StringUtils.isEmpty(equipemntId)) {
            highformworkMapper.disableOrEnable(entity);
        } else {
            throw new EscstException("此采集器正在流水段中使用，无法启用/停用");
        }
    }


    /**
    * @desc 获取采集器采集时间
    * @param flowId
    * @return
    * @author dwj
    * @date 17/10/2018 14:01
    */
    public FlowVo getTime(String  flowId){
        FlowVo flowVo = new FlowVo();
        CollectorTimeBean bean = new CollectorTimeBean();
        try{
            Map<Object,Object> map = new HashMap<Object,Object>();
            map.put("folwId",flowId);
            List<String> ids = highformworkMapper.selectCollectorIdsByFlowId(map);
            bean.setFlowId(flowId);
            bean.setIds(ids);
            String startTime = highformworkMapper.selectMinTimeByCollectorId(bean);
            String endTime = highformworkMapper.selectMaxTimeByCollectorId(bean);
            flowVo.setStartTime(startTime);
            flowVo.setEndTime(endTime);
        }catch (Exception e){
            throw new EscstException("获取采集时间异常"+e.getMessage(),e);
        }
        return flowVo;
    }


    /**
    * @desc 编辑流水段
    * @param bean
    * @return
    * @author dwj
    * @date 18/10/2018 10:05
    */
    @Transactional
    public void updateFlow(FlowBean bean){
        try{
            if(!StringUtils.isEmpty(bean.getName())){
                FlowEntity entity = new FlowEntity();
                entity.setId(bean.getId());
                entity.setUpdateTime(new Date());
                entity.setName(bean.getName());
                if(bean.getFile() != null){
                    String fileId = fileService.uploadFile("highformwork", bean.getFile());
                    entity.setFileId(fileId);
                }
                highformworkMapper.updateFlow(entity);
            }

            String collectorBeanList = bean.getIds();
            String[] strs = collectorBeanList.split(",");
            //需要修改的采集器
            List<String> ids = Arrays.asList(strs);
            //新增的采集器
            List<String> newIds = new ArrayList<String>();
            Map<Object,Object> map = new HashMap<Object,Object>();
            map.put("folwId",bean.getId());
            map.put("flowStatus",1);
            List<String> list = highformworkMapper.selectCollectorIdsByFlowId(map);
            for(String id : ids){
                    if(list.contains(id)){
                        list.remove(id);
                    }else {
                        newIds.add(id);
                    }
                }


            if(!CollectionUtils.isEmpty(list)){
                //批量修改采集器状态
                UpdateVo updateVo = new UpdateVo();
                updateVo.setUpdateTime(new Date());
                updateVo.setStatus(1);
                updateVo.setIds(list);
                highformworkMapper.batchUpdate(updateVo);
                //批量修改流水段采集器中间表
               Map<Object,Object> parmMap = new HashMap<Object,Object>();
                parmMap.put("flowId",bean.getId());
                parmMap.put("equipmentIds",list);
               List<String> paramIds = highformworkMapper.selectCollectorFlowId(parmMap);
                UpdateVo vo = new UpdateVo();
                vo.setStatus(2);
                vo.setUpdateTime(new Date());
                vo.setIds(paramIds);
                highformworkMapper.batchUpdateFlow(vo);
            }
            if(!CollectionUtils.isEmpty(newIds)){
                List<FlowCollectorBean> beans = new ArrayList<FlowCollectorBean>();
                for (String s : newIds) {
                    FlowCollectorBean flowCollectorBean = new FlowCollectorBean();
                    flowCollectorBean.setId(UuidUtils.getUuid());
                    flowCollectorBean.setFlowId(bean.getId());
                    flowCollectorBean.setFlowStatus(1);
                    flowCollectorBean.setCollectorId(s);
                    flowCollectorBean.setCreateTime(new Date());
                    beans.add(flowCollectorBean);
                }
                //批量流水段采集器中间表
                if (!CollectionUtils.isEmpty(beans)) {
                    highformworkMapper.batchFlowCollector(beans);
                }
                //批量修改采集器状态
                UpdateVo updateVo = new UpdateVo();
                updateVo.setUpdateTime(new Date());
                updateVo.setStatus(0);
                updateVo.setIds(newIds);
                highformworkMapper.batchUpdate(updateVo);
            }

        }catch (Exception e){
        throw new EscstException("修改流水段异常"+e.getMessage(),e);
        }
    }

}
