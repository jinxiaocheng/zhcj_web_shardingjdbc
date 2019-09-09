package com.escst.construction.service;

import java.util.*;

import com.escst.commons.tree.TreeEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.vo.ScheduledPlanTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.vo.PageVo;
import com.escst.construction.bean.ProjectScheduleQueryBean;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.mapper.ProjectScheduleMapper;
import com.escst.construction.mapper.ScheduledPlanMapper;

/**
 * @author niejing
 * @desc 工程进度控制器
 * @date 2017年8月18日 下午1:23:04
 */
@Service
public class ProjectScheduleService {

    /**
     * 表示为子节点
     */
    private static final String OPEN = "open";

    @Autowired
    public ProjectScheduleMapper mapper;
    @Autowired
    public ScheduledPlanMapper spMapper;

    public PageVo queryProjectSchedule(ProjectScheduleQueryBean entity) {
        PageVo pageVo = new PageVo();
        try {
            // 根据工地id查询人员总记录数
            int count = mapper.selectMapCount(entity);
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);

            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<Map<String, Object>> list = mapper.selectMapList(entity);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("constructionId", entity.getConstructionId());
            paramMap.put("parentId", entity.getParentId());
            Map<String, Object> map = spMapper.selectDiffDaysByMap(paramMap);

            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> tmpMap : list) {
                    tmpMap.put("initTime", map.get("initTime"));
                    tmpMap.put("diffDays", map.get("diffDays"));
                }
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("查询工程进度失败！", e);
        }
        return pageVo;
    }

    public List<ProjectScheduleEntity> queryByParentId(String parentId) {
        return mapper.selectByParentId(parentId);
    }

    /**
     * @param entity
     * @return void
     * @desc 更新工程进度为已完成
     * @author jincheng
     * @date 2018-4-14 11:38
     */
    @Transactional
    public void updateStatus(ProjectScheduleEntity entity) {

        if (OPEN.equals(entity.getState())) {
            // 是子节点，只更新自己
            mapper.updateStatus(entity.getId());
        } else {
            // 为父节点
            entity.setStatus("2");
            this.update(entity);
        }
    }

    /**
     * @param entity
     * @return void
     * @desc 修改工程进度
     * @author jincheng
     * @date 2018-4-14 11:34
     */
    @Transactional
    public void update(ProjectScheduleEntity entity) {

        if (OPEN.equals(entity.getState())) {
            // 只修改自己
            mapper.update(entity);
        }
        if (!OPEN.equals(entity.getState()) && entity.getStatus().equals("2")) {
            // 如果父工程进度已完成，下面所有的子工程进度也需要改成已完成
            // 获得工程进度计划的ID，父ID集合
            List<ProjectScheduleEntity> allList = mapper.listAll(entity.getConstructionId());
            // 获得Id下的所有子节点ID
            List<ProjectScheduleEntity> idList = this.listAllNode(allList, entity.getId());
            // 添加父节点ID
            idList.add(entity);
            // 批量修改
            mapper.batchUpdate(idList);
        } else {
            mapper.update(entity);
        }

    }


    // 子节点
    private List<ProjectScheduleEntity> childList = new ArrayList<ProjectScheduleEntity>();

    /**
     * 递归，通过父节点ID 获得所有的子节点ID集合
     *
     * @param allList
     * @return
     */

    private List<ProjectScheduleEntity> listAllNode(List<ProjectScheduleEntity> allList, String id) {

        for (ProjectScheduleEntity entity : allList) {
            if (entity.getpId().equals(id)) {
                // 递归
                listAllNode(allList, entity.getId());
                childList.add(entity);
            }
        }

        return childList;

    }


    /**
     * @param entity
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询工程进度树
     * @author jincheng
     * @date 2018-4-8 11:48
     */
    public PageVo listProjectScheduleTree(ProjectScheduleEntity entity) {
        try {
            PageVo pageVo = new PageVo();
            if (!com.alibaba.druid.util.StringUtils.isEmpty(entity.getId())) {
                // 查子节点，不分页
                entity.setStartIndex(0);
                entity.setRowNum(Integer.MAX_VALUE);
            }
            // 根据工地id查询总记录数
            int count = mapper.selectCount(entity);
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
            if (!com.alibaba.druid.util.StringUtils.isEmpty(entity.getId())) {
                entity.setName("");
            }

            // 原始数据
            List<ProjectScheduleEntity> projectScheduleEntityList = mapper.listProjectScheduleTree(entity);
            if (CollectionUtils.isEmpty(projectScheduleEntityList)) {
                return pageVo;
            }

            // 获得节点下的叶子节点数量
            List<ProjectScheduleEntity> parentList = mapper.isParent(projectScheduleEntityList);

            // 组装树
            for (ProjectScheduleEntity projectScheduleEntity : projectScheduleEntityList) {
                for (ProjectScheduleEntity scheduleEntity : parentList) {
                    if (projectScheduleEntity.getId().equals(scheduleEntity.getpId()) && !scheduleEntity.getCount().equals("0")) {
                        projectScheduleEntity.setState("closed");
                    }
                }
            }

            pageVo.setRows(projectScheduleEntityList);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("查询工地下的进度计划树异常！", e);
        }
    }
}
