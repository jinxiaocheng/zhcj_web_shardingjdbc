package com.escst.positionWork.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.positionWork.entity.PositionWorkEntity;
import com.escst.positionWork.vo.PositionWorkVo;

/**
 * @desc
 * @author niejing
 * @date 2018年4月25日 下午3:25:12
 */
@Repository
public interface PositionWorkMapper extends BaseMapper<PositionWorkEntity> {
	List<Map<String, Object>> selectMapList(PositionWorkEntity entity);

	List<PositionWorkEntity> selectByConstructionId(PositionWorkVo vo);
	
	int selectByName(Map<String,Object> map);
	
}
