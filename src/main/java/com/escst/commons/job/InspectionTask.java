package com.escst.commons.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.escst.construction.entity.ConstructionEntity;
import com.escst.construction.mapper.ConstructionMapper;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.mapper.InspectionMapper;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.task.entity.ProjectTaskProcessEntity;
import com.escst.task.mapper.ProjectTaskMapper;

/**
 * @desc 处理存量数据，将质量管理和日常安全管理中的数据按时间顺序自动生产orderNo
 * @author niejing
 * @date 2018年5月17日 上午11:18:30
 */
@Component
public class InspectionTask {

	@Autowired
	InspectionMapper inspectionMapper;

	@Autowired
	ConstructionMapper constrcutionMapper;

	@Autowired
	ProjectTaskMapper projectTaskMapper;
	
	
	// 质量检查初始单号
	public static final String ZL_ORDER_NO = "ZL-0001";
	// 日常安全检查初始单号
	public static final String AQ_ORDER_NO = "AQ-0001";
	public static final String RW_ORDER_NO = "RW-0001";

	public void execute() {

		List<ConstructionEntity> contructionList = constrcutionMapper.selectAll();
		for (ConstructionEntity entity : contructionList) {
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("type", 3);
//			map.put("constructionId", entity.getId());

//			List<InspectionEntity> list = inspectionMapper.selectByType(map);

			List<ProjectTaskEntity> list = projectTaskMapper.selectByConstructionId(entity.getId());
			
			if (CollectionUtils.isEmpty(list)) {
				continue;
			}
			String tmpNo = RW_ORDER_NO;
			for (ProjectTaskEntity inspectionEntity : list) {
				String orderNo = tmpNo;
				// 更新检查记录
				inspectionEntity.setOrderNo(orderNo);
//				inspectionMapper.updateOrderNo(inspectionEntity);
				projectTaskMapper.updateOrderNo(inspectionEntity);
				tmpNo = getOrderNo(orderNo);
			}
		}

	}

	private String getOrderNo(String orderNo) {
		String[] tmpStr = orderNo.split("-");
		orderNo = tmpStr[0] + "-" + increase(tmpStr[1]);

		return orderNo;
	}

	private String increase(String value) {
		int index = 1;
		int n = Integer.parseInt(value.substring(index)) + 1;
		String newValue = String.valueOf(n);
		int len = value.length() - newValue.length() - index;

		for (int i = 0; i < len; i++) {
			newValue = "0" + newValue;
		}
		return value.substring(0, index) + newValue;
	}
}
