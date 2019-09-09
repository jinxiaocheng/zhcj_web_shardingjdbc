package com.escst.territory.mapper;

import java.util.List;
import java.util.Map;

import com.escst.territory.vo.ConstructionVO;
import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.territory.entity.TerritoryEntity;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 17:49
 */
@Repository
public interface TerritoryMapper extends BaseMapper<TerritoryEntity> {

	/**
	 * 根据父id找到对应的子id集合
	 * @param id
	 * @return
	 */
	List<TerritoryEntity> selectByParentId(String id);

	List<Map<String,Object>> selectAuthAreaByUserId(Map<String,Object> paramMap);

	List<ConstructionVO> selectAuthConstructionByUserId(Map<String,Object> map);

	List<String> selectAuthConstructionIds(Map<String,Object> map);
	
	List<TerritoryEntity> queryAllList();
}
