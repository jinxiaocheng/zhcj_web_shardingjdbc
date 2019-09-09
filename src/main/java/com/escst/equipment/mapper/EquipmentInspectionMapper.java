package com.escst.equipment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.equipment.vo.InspectionQueryVO;
import com.escst.equipment.vo.InspectionVO;

/**
 * @desc 设备巡查
 * @author zhouwei
 * @date 2017年8月23日 上午10:43:28
 */
@Repository
public interface EquipmentInspectionMapper {

	/**
	 * @desc 查询设备巡检记录.
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月23日 上午11:27:59
	 */
	List<InspectionVO> queryEquipmentInspectionList(InspectionQueryVO queryVO);
	
	int getEquipmentInspectionCount(InspectionQueryVO queryVO);
	
	/**
	 * @desc 得到设备巡检详情
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月24日 下午3:38:19
	 */
	InspectionVO getEquipmentInspection(String id);


}
