package com.escst.projectStructure.mapper;

import com.escst.commons.tree.TreeEntity;
import com.escst.projectStructure.entity.ProjectStructureEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 11:34 2017/4/24
 */
@Repository
public interface ProjectStructureMapper {

    List<TreeEntity> queryProjectStructrue(Map<String, Object> map);

    List<TreeEntity> queryParentProjectStructrue(Map<String, Object> map);

    List<ProjectStructureEntity> queryScheduledPlanList(Map<String, Object> map);

    List<Map<String, Object>> selectProjectStructureById(Map<String, Object> map);

    List<Map<String, Object>> selectProjectById(Map<String, Object> map);

    Map<String, Object> selectProjectStructureByParentId(Map<String, Object> map);

    int selectByParentIdCount(String parentId);

    void insertProjectStructure(ProjectStructureEntity projectStructureEntity);

    void updateBatch(List<ProjectStructureEntity> list);

    void batchInsert(List<ProjectStructureEntity> list);

    List<ProjectStructureEntity> selectByParentId(@Param("parentId") String parentId, @Param("constructionId") String constructionId);

    List<String> selectConstructionIdList();


    List<String> queryByName(Map<String, Object> map);

    List<ProjectStructureEntity> queryBySortNum(Map<String, Object> map);

    int selectCount(ProjectStructureEntity projectStructureEntity);

    /**
     * @param projectStructureEntity
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询检查部位树
     * @author jincheng
     * @date 2018-4-10 15:01
     */
    List<TreeEntity> listProjectStructureTree(ProjectStructureEntity projectStructureEntity);


    /**
     * @param list
     * @return com.escst.construction.vo.ScheduledPlanTree
     * @desc 判断是否是子节点
     * @author jincheng
     * @date 2018-4-8 17:25
     */
    List<TreeEntity> isParent(List<TreeEntity> list);

    /**
     * @param treeEntity
     * @return com.escst.construction.vo.ScheduledPlanTree
     * @desc 添加工程结构
     * @author jincheng
     * @date 2018-4-8 17:25
     */
    void insertStructure(TreeEntity treeEntity);

    /**
     * @param treeEntity
     * @return void
     * @desc 修改检查部位
     * @author jincheng
     * @date 2018-4-8 14:57
     */
    void updateStructure(TreeEntity treeEntity);


    /**
     * @param constructionId 工地Id
     * @return java.util.List<java.lang.String>
     * @desc 查询所有工程结构的ID, 父ID
     * @author jincheng
     * @date 2018-4-9 11:23
     */
    List<TreeEntity> listAll(String constructionId);

    /**
     * @param list
     * @return void
     * @desc 删除工程结构
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    void batchDelete(List<TreeEntity> list);

    List<ProjectStructureEntity> selectProjectStructrue(String constructionId);


}
