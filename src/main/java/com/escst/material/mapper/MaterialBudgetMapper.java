package com.escst.material.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.mapper.BaseMapper;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialBudgetEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月26日 下午4:34:58
 */
public interface MaterialBudgetMapper extends BaseMapper<MaterialBudgetEntity> {

	List<Map<String, Object>> selectMapList(MaterialBean entity);
	
	int selectMapCount(MaterialBean entity);
	
	public Map<String,Object> selectBudgetById(String id);
	
	int updateByIds(MaterialBudgetEntity entity);
}
