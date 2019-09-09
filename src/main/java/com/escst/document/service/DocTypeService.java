package com.escst.document.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.document.entity.DocTypeEntity;
import com.escst.document.mapper.DataManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 9:00 2018/5/16
 */
@Service
public class DocTypeService {

    @Autowired
    private DataManagementMapper dataManagementMapper;



    /**
    * @desc 新增或者修改文档类型
    * @param entity
    * @return
    * @author dwj
    * @date 2018/5/16 10:12
    */
    @Transactional
    public void save(DocTypeEntity entity){
        String id = entity.getId();
        try{
            if(StringUtils.isBlank(id)){
                id = UuidUtils.getUuid();
                if(StringUtils.isNotBlank(entity.getParentId())){
                    entity.setIsLeaf(1);
                }else {
                    entity.setParentId("0");
                }
                entity.setId(id);
                entity.setCreateTime(new Date());
                dataManagementMapper.saveDocType(entity);
            }else {
                dataManagementMapper.updateDocType(entity);
            }
        }catch (Exception e){
            throw new EscstException("新增或修改文档类型失败");
        }
    }



    /**
    * @desc 获取当前工地下的文档类型
    * @param entity
    * @return
    * @author dwj
    * @date 2018/5/16 10:12
    */
    public PageVo selectByConstructionId(TreeEntity entity){
        PageVo pageVo = new PageVo();
        try{
            if(StringUtils.isNotBlank(entity.getId())){
                // 查子节点，不分页
                entity.setStartIndex(0);
                entity.setRowNum(Integer.MAX_VALUE);
            }
            // 根据工地id查询总记录数
                Integer count = dataManagementMapper.seletDocTypeCount(entity);
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 设置总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引

            // 处理easyUI树翻页的问题
            int startIndex = ((entity.getPage() - 1) * (entity.getRowNum()) == Integer.MAX_VALUE) ? 0 : (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            // 处理按名称搜索
            if (StringUtils.isNotBlank(entity.getId())) {
                entity.setName("");
            }
            List<TreeEntity> list = dataManagementMapper.selectDocTypeByConstructionId(entity);

            if (CollectionUtils.isEmpty(list)) {
                return pageVo;
            }
            // 获得节点下的叶子节点数量
            List<TreeEntity> parentList = dataManagementMapper.selectDocTypeParentByConstructionId(list);
            for(TreeEntity treeEntity : list){
                if (treeEntity.getId().equals(treeEntity.getpId())) {
                    treeEntity.setState("closed");
                }
            }

            for (TreeEntity treeEntity : parentList) {
                for (TreeEntity tree : list) {
                    if (tree.getId().equals(treeEntity.getpId()) && !treeEntity.getCount().equals("0")) {
                        tree.setState("closed");
                    }
                }
            }

            pageVo.setRows(list);
        }catch (Exception e){
            throw new EscstException("查询文档类型失败"+e.getMessage());
        }
     return pageVo;
    }

    /**
    * @desc 删除文档类型
    * @param entity
    * @return
    * @author dwj
    * @date 2018/5/16 10:12
    */
    @Transactional
    public void deleteDocType(TreeEntity entity){
             List<String> ids = new ArrayList<String>();
            List<TreeEntity> childList = new ArrayList<TreeEntity>();
            //获取当前工地下的所有文档类型
            entity.setRowNum(Integer.MAX_VALUE);
            List<TreeEntity> list = dataManagementMapper.selectDocTypeByConstructionId(entity);
            // 获得Id下的所有子节点ID
            List<TreeEntity> treeEntities = this.getChild(list, entity.getId(),childList);
            treeEntities.add(entity);
            if(treeEntities.size()>1){
                for(TreeEntity treeEntity : treeEntities){
                    String id = treeEntity.getId();
                    List<String> names = dataManagementMapper.selectByTypeId(treeEntity);
                    if(CollectionUtils.isEmpty(names)){
                        ids.add(id);
                    }else {
                        throw new EscstException("选中的文档类型有正在使用,不能删除");
                    }
                }
            }else {
                TreeEntity treeEntity = treeEntities.get(0);
                String id = treeEntity.getId();
                List<String> names = dataManagementMapper.selectByTypeId(treeEntity);
                if(CollectionUtils.isEmpty(names)){
                    ids.add(id);
                }else {
                    throw new EscstException("此文档类型正在使用,不能删除");
                }

            }
            //批量删除
            if(!CollectionUtils.isEmpty(ids)){
                dataManagementMapper.deleteDocType(ids);
            }
    }


    /**
    * @desc 递归得到所有的子节点
    * @param list id
    * @return
    * @author dwj
    * @date 2018/5/16 10:11
    */
    private List<TreeEntity> getChild(List<TreeEntity> list,String id,  List<TreeEntity> childList){
        for(TreeEntity entity : list){
            if(entity.getpId().equals(id)){
                getChild(list,entity.getId(),childList);
                childList.add(entity);
            }
        }
        return childList;
    }


}
