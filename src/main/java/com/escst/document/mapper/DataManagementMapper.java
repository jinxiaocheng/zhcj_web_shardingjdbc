package com.escst.document.mapper;

import com.escst.commons.tree.TreeEntity;
import com.escst.document.bean.DocumentBean;
import com.escst.document.entity.DataManagementEntity;
import com.escst.document.entity.DocTypeEntity;
import com.escst.document.vo.DataManagementVO;
import com.escst.document.vo.DocumentTypeVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/9 11:21
 */
@Repository
public interface DataManagementMapper {

    public List<Map<String,Object>> selectList(Map<String, Object> map);

    public Integer selectCount(Map<String, Object> map);

    public DataManagementVO selectById(String id);

    public int insert(DataManagementEntity dataManagementEntity);

    public int update(DataManagementEntity dataManagementEntity);

    List<DocumentTypeVo> queryList(Map<String,Object> map);

    public void batchSave(List<DocumentBean> list);

    void saveDocType(DocTypeEntity entity);

    List<TreeEntity> selectDocTypeByConstructionId(TreeEntity entity);

    List<TreeEntity> selectDocTypeParentByConstructionId(List<TreeEntity> entityList);


    public Integer seletDocTypeCount(TreeEntity entity);

    void updateDocType(DocTypeEntity entity);

    void deleteDocType(List<String> ids);

    List<String>  selectByTypeId(TreeEntity entity);
}
