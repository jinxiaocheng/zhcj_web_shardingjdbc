package com.escst.document.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.FileUploadUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.document.bean.DocumentBean;
import com.escst.document.entity.DataManagementEntity;
import com.escst.document.mapper.DataManagementMapper;
import com.escst.document.vo.DataManagementVO;
import com.escst.document.vo.DocumentTypeVo;
import com.escst.file.service.FileService;
import com.escst.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author caozx
 * @desc 资料管理服务层
 * @date 2017/3/9 11:44
 */
@Service
@Transactional
public class DataManagementService {

    private static final String SEPARATOR_CHAR = "/";
    private static final String DOCUMENT = "document";

    @Autowired
    private DataManagementMapper dataManagementMapper;

    @Autowired
    private FileService fileService;

    /**
     * 查询资料列表信息
     *
     * @param dataManagementEntity
     * @return
     */
    public PageVo queryByConstructionId(DataManagementEntity dataManagementEntity) {
        PageVo pageVo = new PageVo();
        try {
            int pageNum = dataManagementEntity.getPage();
            int pageSize = dataManagementEntity.getRowNum();
            int offset = (pageNum - 1) * pageSize;
            Map<String, Object> paraMap = new HashMap<String, Object>();
            String constructionId = dataManagementEntity.getConstructionId();
            if(StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList",constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            paraMap.put("name", dataManagementEntity.getName());
            paraMap.put("areaId", dataManagementEntity.getBelongArea());
            paraMap.put("typeId",dataManagementEntity.getTypeId());
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            List<Map<String, Object>> list = dataManagementMapper.selectList(paraMap);
            Integer count = dataManagementMapper.selectCount(paraMap);
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询资料信息出现异常：" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 查询资料详细列表信息
     *
     * @param id
     * @return
     */
    public DataManagementVO queryById(String id) {
        DataManagementVO vo = null;
        try {
            vo = dataManagementMapper.selectById(id);
            //通过id获取上传的附件图片的路径集合
            String  filePathVO = fileService.queryFilePathById(id);
            String filePreviewPathVo = fileService.queryFilePathById(id);
            vo.setPicList(filePathVO);
            vo.setPreviewPic(filePreviewPathVo);
        } catch (EscstException e) {
            throw new EscstException("查询资料详细信息异常：" + e.getMessage(), e);
        }
        return vo;
    }

    /**
     * 新增文档
     */
    public void save(DataManagementEntity dataManagementEntity) {
        try {
            String id = dataManagementEntity.getId();
            //id为空执行新增操作，不为空执行修改操作
            if (StringUtils.isEmpty(id)) {
                dataManagementEntity.setId(UuidUtils.getUuid());
                dataManagementEntity.setCreateTime(new Date());
                dataManagementEntity.setStatus(1);
                MultipartFile[] files = dataManagementEntity.getFiles();
                if (files != null && files.length > 0) {
                    //业务id
                    id = dataManagementEntity.getId();
                    //文件存储的存到数据库的路径
                    String savePath = "upload" + SEPARATOR_CHAR + "dataManagement" + SEPARATOR_CHAR + DateUtils.format(new Date(), "yyyyMM");

                    //上传文件
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("businessId", id);
                    map.put("filePath", savePath);
                    String result = FileUploadUtils.uploadFiles(files, map);

                    JSONObject jsonObject = JSONObject.parseObject(result);
                    Integer status = jsonObject.getInteger("status");
                    if (status == 0) {
                        throw new EscstException("文件上传失败！");
                    }
                }
                dataManagementMapper.insert(dataManagementEntity);
            } else {
                dataManagementEntity.setUpdateTime(new Date());
                dataManagementMapper.update(dataManagementEntity);
            }
        } catch (Exception e) {
            throw new EscstException("保存资料信息出现异常：" + e.getMessage(), e);
        }
    }

    /**
    * @desc 获取文档类型
    * @param
    * @return
    * @author dwj
    * @date 2017/12/4 16:59
    */
    public  List<DocumentTypeVo> DocTypeTree(BaseAuthVO vo){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(vo.getConstructionId())){
            map.put("constructionId",vo.getConstructionId());
        }else {
            UserEntity userEntity = ContextUtils.getCurrentUser();
            List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
            map.put("constructionList",constructionList);
        }
        return dataManagementMapper.queryList(map);
    }

    public List<TreeEntity> queryDocTypeTree(BaseAuthVO baseAuthVO){
        List<DocumentTypeVo> list = DocTypeTree(baseAuthVO);
        List<TreeEntity> treeEntityList = new ArrayList<TreeEntity>();
        if(!CollectionUtils.isEmpty(list)){
            for(DocumentTypeVo vo : list){
                TreeEntity treeEntity = new TreeEntity();
                if("0".equals(vo.getParentId())){
                    treeEntity.setId(vo.getId());
                    treeEntity.setName(vo.getName());
                    treeEntity.setpId(vo.getParentId());
                    treeEntity.setLevel(vo.getIsLeaf());
                    treeEntityList.add(treeEntity);
                }else {
                    treeEntity.setId(vo.getId());
                    treeEntity.setName(vo.getName());
                    treeEntity.setpId(vo.getParentId());
                    treeEntity.setLevel(vo.getIsLeaf());
                    treeEntityList.add(treeEntity);
                }
            }
        }
        return treeEntityList;
    }

    public void bathSave(DataManagementEntity entity){
        try{
            List<DocumentBean> list = new ArrayList<DocumentBean>();
            String id = entity.getId();
            if(StringUtils.isEmpty(id)){
                MultipartFile[] files = entity.getFiles();
                if(files != null && files.length > 0) {
                 for(int i =0; i<files.length ; i++){
                     DocumentBean bean = new DocumentBean();
                     MultipartFile file = files[i];
                     bean.setCreateTime(new Date());
                     String documentId = fileService.uploadFile(DOCUMENT, file);
                     bean.setId(documentId);
                     bean.setStatus(1);
                     bean.setCreateBy(entity.getCreateBy());
                     bean.setConstructionId(entity.getConstructionId());
                     bean.setTypeId(entity.getTypeId());
                     bean.setRemark(entity.getRemark());
                     list.add(bean);
                 }
                }
            }
            if(!CollectionUtils.isEmpty(list)){
                dataManagementMapper.batchSave(list);
            }
        }catch (Exception e){
            throw new EscstException("新增文档信息失败"+e.getMessage(),e);
        }
    }


    /**
    * @desc 删除用户相关内容
    * @param entity
    * @return
    * @author dwj
    * @date 2018/4/13 16:51
    */
    public void deleteByUserId(DataManagementEntity entity){
            DataManagementVO vo = dataManagementMapper.selectById(entity.getId());
            String userId = vo.getCreateBy();
            if(userId.equals(entity.getCreateBy())){
                entity.setUpdateTime(new Date());
                dataManagementMapper.update(entity);
            }else {
                throw new EscstException("该文件不是当前用户上传无法删除!");
            }
    }


}
