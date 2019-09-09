package com.escst.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.escst.commons.exception.EscstException;
import com.escst.task.entity.ScheduledPlanEntity;
import com.escst.task.mapper.TaskDistributionMapper;

/**
 * @author dwj
 * @desc
 * @date 11:26 2017/3/27
 */
@Service
@Transactional
public class TaskDistributionService {

	@Autowired
	private TaskDistributionMapper taskDistributionMapper;

	/**
	 * 查询工程结构
	 **/
	public List<ScheduledPlanEntity> queryScheduledPlanList(ScheduledPlanEntity scheduledPlanEntity) {
		List<ScheduledPlanEntity> list = new ArrayList<ScheduledPlanEntity>();
		try {
			// 工地Id
			String constructionId = scheduledPlanEntity.getConstructionId();
			// 查询该工地下的所有的工程结构
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("constructionId", constructionId);
			List<ScheduledPlanEntity> scheduledPlanEntityList = taskDistributionMapper.queryScheduledPlanList(paramMap);
			if (!CollectionUtils.isEmpty(scheduledPlanEntityList)) {
				for (ScheduledPlanEntity entity : scheduledPlanEntityList) {
					String id = entity.getId();
					String parentId = entity.getParentId();
					if ("0".equals(parentId) || StringUtils.isEmpty(parentId)) {
						List<ScheduledPlanEntity> childrenList = buildData(scheduledPlanEntityList, entity);
						entity.setData(childrenList);
						list.add(entity);
					}
				}
			}

		} catch (Exception e) {
			throw new EscstException("查询工程进度失败" + e.getMessage());
		}
		return list;
	}

	private List<ScheduledPlanEntity> buildData(List<ScheduledPlanEntity> list, ScheduledPlanEntity entity) {
		// 获取该父节点下的子节点数据
		List<ScheduledPlanEntity> childrenList = getChildren(list, entity);
		if (!CollectionUtils.isEmpty(childrenList)) {
			for (ScheduledPlanEntity child : childrenList) {
				String id = child.getId();
				List<ScheduledPlanEntity> childList = buildData(list, child);
				child.setData(childList);
			}
		}
		return childrenList;
	}

	private List<ScheduledPlanEntity> getChildren(List<ScheduledPlanEntity> list, ScheduledPlanEntity entity) {
		List<ScheduledPlanEntity> childrenList = new ArrayList<ScheduledPlanEntity>();
		String id = entity.getId();
		for (ScheduledPlanEntity child : list) {
			String parentId = child.getParentId();
			if (id.equals(parentId)) {
				childrenList.add(child);
			}
		}
		return childrenList;
	}

}