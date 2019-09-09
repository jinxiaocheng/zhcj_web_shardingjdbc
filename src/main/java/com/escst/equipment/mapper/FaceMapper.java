package com.escst.equipment.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.equipment.entity.FaceEntity;

/**
 * @desc
 * @author niejing
 * @date 2018年4月10日 下午1:28:30
 */
@Repository
public interface FaceMapper extends BaseMapper<FaceEntity> {
	
	
	List<Map<String, Object>> selectByEquipmentId(String equipmentId);
	
	int selectCount(String equipmentId);
}
