package com.escst.task.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.task.entity.ScheduledPlanEntity;

/**
 * @author dwj
 * @desc
 * @date 10:58 2017/3/27
 */
@Repository
public interface TaskDistributionMapper {

    List<ScheduledPlanEntity> queryScheduledPlanList(Map<String, Object> map);
}
