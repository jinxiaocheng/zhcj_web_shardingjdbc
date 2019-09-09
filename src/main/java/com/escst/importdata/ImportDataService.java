package com.escst.importdata;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.mapper.ProjectScheduleMapper;
import com.escst.construction.mapper.ScheduledPlanMapper;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.projectStructure.mapper.ProjectStructureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author caozx
 * @desc 查询工程结构数据导入工程进度计划和工程进度
 * @date 2018/4/10 17:51
 */
@Service
public class ImportDataService {

    @Autowired
    private ProjectStructureMapper projectStructureMapper;

    @Autowired
    private ScheduledPlanMapper scheduledPlanMapper;

    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Transactional
    public void importPlan(String constructionId) {

        List<ScheduledPlanEntity> scheduledPlanEntities = new ArrayList<ScheduledPlanEntity>();
        List<ProjectScheduleEntity> projectScheduleEntities = new ArrayList<ProjectScheduleEntity>();

        List<ProjectStructureEntity> list = projectStructureMapper.selectProjectStructrue(constructionId);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (ProjectStructureEntity entity : list) {
            ScheduledPlanEntity scheduledPlanEntity = new ScheduledPlanEntity();
            scheduledPlanEntity.setId(entity.getId());
            scheduledPlanEntity.setName(entity.getName());
            scheduledPlanEntity.setParentId(entity.getParentId());
            scheduledPlanEntity.setConstructionId(constructionId);
            scheduledPlanEntity.setStartTime(DateUtils.parse("2017-04-10","yyyy-MM-dd"));
            scheduledPlanEntity.setEndTime(DateUtils.parse("2018-04-10","yyyy-MM-dd"));
            scheduledPlanEntity.setSortNum(entity.getSortNum());
            scheduledPlanEntities.add(scheduledPlanEntity);

            ProjectScheduleEntity projectScheduleEntity = new ProjectScheduleEntity();
            projectScheduleEntity.setId(UuidUtils.getUuid());
            projectScheduleEntity.setScheduledPlanId(entity.getId());
            projectScheduleEntity.setName(entity.getName());
            projectScheduleEntity.setConstructionId(constructionId);
            projectScheduleEntity.setStatus("1");
            projectScheduleEntity.setPlanDays(365);
            projectScheduleEntity.setCreateTime(new Date());
            projectScheduleEntities.add(projectScheduleEntity);
        }

        scheduledPlanMapper.batchInsert(scheduledPlanEntities);
        projectScheduleMapper.batchInsert(projectScheduleEntities);


    }

}
