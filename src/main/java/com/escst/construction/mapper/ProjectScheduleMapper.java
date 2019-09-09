package com.escst.construction.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.mapper.BaseMapper;
import com.escst.construction.bean.ProjectScheduleQueryBean;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.vo.ScheduledPlanTree;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

/**
 * @author niejing
 * @desc 工程进度相关
 * @date 2017年8月18日 下午3:45:52
 */
@Repository
public interface ProjectScheduleMapper extends BaseMapper<ProjectScheduleEntity> {

    int selectMapCount(ProjectScheduleQueryBean entity);

    List<Map<String, Object>> selectMapList(ProjectScheduleQueryBean entity);

    /**
     * @param id
     * @desc 更新工程进度状态为已完成
     * @author niejing
     * @date 2017年8月18日 下午5:19:48
     */
    void updateStatus(String id);

    List<ProjectScheduleEntity> selectByParentId(String parentId);

    /**
     * @param scheduleId
     * @desc 根据进度计划id更新子进度
     * @author niejing
     * @date 2018年1月8日 下午4:46:33
     */
    void updateSubTreeStatus(String scheduleId);

    /**
     * @param projectScheduleEntity
     * @return void
     * @desc 修改工程进度名称，计划工期,实际工期
     * @author jincheng
     * @date 2018-4-10 9:14
     */
    void updateNameAndDays(ProjectScheduleEntity projectScheduleEntity);


    /**
     * @param
     * @return
     * @desc 删除工程进度
     * @author jincheng
     * @date 2018-4-10 9:14
     */
    void batchDelete(List<ScheduledPlanTree> planTreeList);

    /**
     * @param entity
     * @return
     * @desc 查询工程进度树
     * @author jincheng
     * @date 2017年8月18日 下午1:49:16
     */
    List<ProjectScheduleEntity> listProjectScheduleTree(ProjectScheduleEntity entity);

    /**
     * @param list
     * @return com.escst.construction.vo.ScheduledPlanTree
     * @desc 判断是否是子节点
     * @author jincheng
     * @date 2018-4-8 17:25
     */
    List<ProjectScheduleEntity> isParent(List<ProjectScheduleEntity> list);


    /**
     * @param constructionId 工地Id
     * @return java.util.List<java.lang.String>
     * @desc 查询工地下所有工程进度的ID，父ID
     * @author jincheng
     * @date 2018-4-9 11:23
     */
    List<ProjectScheduleEntity> listAll(String constructionId);


}
