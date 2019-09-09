package com.escst.safety.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.threadpool.SimpleThreadPool;
import com.escst.commons.utils.*;
import com.escst.commons.vo.PageVo;
import com.escst.construction.mapper.ConstructionMapper;
import com.escst.file.mapper.FileMapper;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.safety.entity.RiskOperationEntity;
import com.escst.safety.mapper.RiskOperationMapper;
import com.escst.thread.RiskOperationSyncThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author jincheng
 * @desc 危险作业相关
 * @date 2018-8-14 16:37
 */
@Service
public class RiskOperationService {

    @Autowired
    private RiskOperationMapper riskOperationMapper;
    @Autowired
    private ConstructionMapper constructionMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;

    /**
     * 照片文件夹名称
     */
    private static final String MODELNAME = "riskOperation";


    /**
     * @param
     * @desc 新增危险作业记录
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @Transactional
    public void add(RiskOperationEntity riskOperationEntity) {
        try {
            riskOperationEntity.setId(UuidUtils.getUuid());
            riskOperationEntity.setCreateTime(new Date());
            if (riskOperationEntity.getFiles() != null) {
                // 照片上传
                riskOperationEntity.setIsAttach(1);
                fileService.uploadFile(MODELNAME, riskOperationEntity.getId(), riskOperationEntity.getFiles());
            }
            riskOperationEntity.setIsPush(0);
            riskOperationEntity.setOperation(0);
            riskOperationMapper.add(riskOperationEntity);
            // 同步到工地对应的门禁系统
            riskOperationEntity.setStatus(0);
            SimpleThreadPool.getInstance().executorService.execute(new RiskOperationSyncThread(riskOperationEntity, this));
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "新增危险作业记录异常");
        }
    }

    /**
     * @param riskOperationEntity
     * @return void
     * @desc 同步危险作业记录到门禁系统
     * @author jincheng
     * @date 2018-8-17 9:04
     */
    public void syncRiskOperation(RiskOperationEntity riskOperationEntity) {
        riskOperationEntity.setFiles(null);
        // 查询工地对应的同步IP地址
        String syncUrl = constructionMapper.getSyncUrl(riskOperationEntity.getConstructionId());
        syncUrl = syncUrl + ResourceUtil.getRiskOperationSyncUrl();
        // 同步数据到对应工地的门禁系统
        String s = HttpClientUtils.httpPostWithJSON(syncUrl, riskOperationEntity);

    }


    /**
     * @param
     * @desc 删除危险作业记录
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @Transactional
    public void delete(RiskOperationEntity riskOperationEntity) {
        try {
            riskOperationMapper.delete(riskOperationEntity);
            // 同步
            riskOperationEntity.setStatus(2);
            SimpleThreadPool.getInstance().executorService.execute(new RiskOperationSyncThread(riskOperationEntity, this));
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "删除危险作业记录异常");
        }
    }


    /**
     * @param
     * @desc 查询危险作业记录列表
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    public PageVo listData(RiskOperationEntity riskOperationEntity) {
        PageVo pageVo = new PageVo();
        try {
            riskOperationEntity.setUserId(ContextUtils.getCurrentUserId());
            // 符合查询条件的数据总条数
            int count = riskOperationMapper.getCount(riskOperationEntity);
            // 当前页号
            pageVo.setCurrentPage(riskOperationEntity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(riskOperationEntity.getRowNum());
            if (count == 0) {
                return pageVo;
            }
            // 每页的第一条记录的索引
            int startIndex = (riskOperationEntity.getPage() - 1) * riskOperationEntity.getRowNum();
            riskOperationEntity.setStartIndex(startIndex);
            // 分页之后的数据集合
            List<RiskOperationEntity> list = riskOperationMapper.listData(riskOperationEntity);
            pageVo.setRows(list);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询危险作业记录列表异常");
        }
    }


    /**
     * @param
     * @desc 查看危险作业记录详情
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    public RiskOperationEntity view(String id) {
        RiskOperationEntity entity = new RiskOperationEntity();
        entity.setId(id);
        RiskOperationEntity operationEntity = new RiskOperationEntity();
        List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
        try {
            operationEntity = riskOperationMapper.view(entity);
            if (operationEntity.getIsAttach() == 1) {
                filePathVOs = fileService.queryFilePath(entity.getId());
            }
            operationEntity.setPicList(filePathVOs);
            return operationEntity;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查看危险作业记录详情异常");
        }

    }


    /**
     * @param riskOperationEntity
     * @return java.util.List<com.escst.safety.entity.RiskOperationEntity>
     * @desc 查询危险作业记录（查询当天）
     * @author jincheng
     * @date 2018-8-17 14:03
     */
    @Transactional
    public List<RiskOperationEntity> data(RiskOperationEntity riskOperationEntity) {
        List<RiskOperationEntity> list = new ArrayList<RiskOperationEntity>();
        try {
            riskOperationEntity.setStartTime(DateUtils.getCurrentDate().substring(0, 10));
            if (riskOperationEntity.getType() != null) {
                list = riskOperationMapper.dataIsPush(riskOperationEntity);
            } else {
                list = riskOperationMapper.data(riskOperationEntity);
            }
            if (CollectionUtils.isEmpty(list)) {
                return list;
            }
            // 修改推送状态
            riskOperationMapper.batchUpdate(list);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询危险作业记录异常", e);
        }
    }


    /**
     * @param riskOperationEntity
     * @return java.util.List<com.escst.safety.entity.RiskOperationEntity>
     * @desc 修改危险作业记录
     * @author jincheng
     * @date 2018-8-17 14:03
     */
    @Transactional
    public void update(RiskOperationEntity riskOperationEntity) {
        try {
            // 修改
            if (riskOperationEntity.getFiles() != null && riskOperationEntity.getFiles().length > 0) {
                riskOperationEntity.setIsAttach(1);
                String busId = riskOperationEntity.getId();
                fileService.uploadFile(MODELNAME, busId, riskOperationEntity.getFiles());
            }

            if (StringUtils.isNotBlank(riskOperationEntity.getFilePathList())) {
                // 删除图片
                List<String> list = Arrays.asList(riskOperationEntity.getFilePathList().split(","));
                fileMapper.batchDelete(list);
            }
            riskOperationEntity.setIsPush(0);
            riskOperationEntity.setOperation(1);
            riskOperationEntity.setUpdateBy(ContextUtils.getCurrentUserId());
            riskOperationEntity.setUpdateTime(new Date());
            riskOperationMapper.update(riskOperationEntity);
            // 同步
            riskOperationEntity.setStatus(1);
            SimpleThreadPool.getInstance().executorService.execute(new RiskOperationSyncThread(riskOperationEntity, this));
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "修改危险作业记录异常", e);
        }
    }
}
