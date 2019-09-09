package com.escst.task.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.tree.TreeEntity;
import com.escst.task.bean.PersonProjectCompanyBean;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.task.entity.ProjectTaskProcessEntity;

/**
 * @author dwj
 * @desc
 * @date 19:29 2017/4/10
 */
@Repository
public interface ProjectTaskMapper {

    List<Map<String,Object>> queryList(Map<String, Object> map);

    List<ProjectTaskProcessEntity> selectList(Map<String, Object> map);

    int  selectCount(Map<String,Object> map);


    List<PersonProjectCompanyBean> queryProjectCompany(Map<String, Object> map);

    List<TreeEntity> queryProjectStructrue(Map<String,Object> map);

    List<TreeEntity> queryParentProjectStructrue(Map<String,Object> map);

    Map<String,Object> selectProjectStructureById(String projectStrutureId);

    void insertProjectTask(ProjectTaskEntity projectTaskEntity);
    void insertProjectTaskProcess(ProjectTaskProcessEntity projectTaskProcessEntity);
    void updateTaskStatus(ProjectTaskEntity projectTaskEntity);

    String selectLatestOrderNo(String constructionId);
    
    void updateOrderNo(ProjectTaskEntity entity);
    
    List<ProjectTaskEntity> selectByConstructionId(String constructionId);
}
