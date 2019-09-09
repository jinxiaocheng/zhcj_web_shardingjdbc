package com.escst.construction.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.tree.TreeEntity;
import com.escst.construction.vo.ScheduledPlanTree;
import org.springframework.stereotype.Component;

import com.escst.commons.mapper.BaseMapper;
import com.escst.construction.entity.ScheduledPlanEntity;

/**
 * @author niejing
 * @desc
 * @date 2017年8月18日 下午1:30:37
 */
@Component
public interface ScheduledPlanMapper extends BaseMapper<ScheduledPlanEntity> {

    /**
     * @param entity
     * @return
     * @desc 根据条件查询进度计划树
     * @author niejing
     * @date 2017年8月18日 下午1:49:16
     */
    List<ScheduledPlanTree> listScheduledPlanTree(ScheduledPlanEntity entity);

    /**
     * @param constructionId
     * @return
     * @desc 根据工地ID查找总体进度计划
     * @author niejing
     * @date 2017年8月19日 上午11:18:13
     */
    List<ScheduledPlanEntity> selectByConstructionId(String constructionId);


    /**
     * @param list
     * @return com.escst.construction.vo.ScheduledPlanTree
     * @desc 判断是否是子节点
     * @author jincheng
     * @date 2018-4-8 17:25
     */
    List<ScheduledPlanTree> isParent(List<ScheduledPlanTree> list);


    /**
     * @param scheduledPlanTree
     * @return void
     * @desc 修改工地下的进度计划树节点名称
     * @author jincheng
     * @date 2018-4-8 14:57
     */
    void updateScheduledPlanTreeName(ScheduledPlanTree scheduledPlanTree);

    /**
     * @param scheduledPlanTree
     * @return void
     * @desc 添加工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    void addScheduledPlanTree(ScheduledPlanTree scheduledPlanTree);

    /**
     * @param constructionId 工地Id
     * @return java.util.List<java.lang.String>
     * @desc 查询工地下所有进度计划的ID
     * @author jincheng
     * @date 2018-4-9 11:23
     */
    List<ScheduledPlanTree> listAll(String constructionId);

    /**
     * @param list
     * @return void
     * @desc 删除工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    void batchDelete(List<ScheduledPlanTree> list);

    List<Map<String, Object>> selectByParentId(String parentId);

    /**
     * @param map
     * @return
     * @desc 根据工地ID和parentId查询工程进度的初始时间，工期天数
     * @author niejing
     * @date 2017年11月1日 下午1:48:01
     */
    Map<String, Object> selectDiffDaysByMap(Map<String, Object> map);


}
