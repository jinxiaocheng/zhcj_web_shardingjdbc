package com.escst.material.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.escst.commons.mapper.BaseMapper;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.entity.MaterialModelEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月22日 上午9:35:57
 */
@Component
public interface MaterialMapper extends BaseMapper<MaterialEntity> {

	void batchInsertModel(List<MaterialModelEntity> entity);

	List<Map<String, Object>> selectMaterial(MaterialEntity entity);

	int selectMaterialCount(MaterialEntity entity);

	String selectByMap(Map<String, Object> map);
	
	List<MaterialEntity> selectByCondition(Map<String,Object> map);
	
	void insertMaterialModel(MaterialModelEntity entity);
	
	List<Map<String, Object>> queryBaseMaterialList(MaterialEntity entity);

	int queryBaseMaterialCount(MaterialEntity entity);
	
	MaterialModelEntity selectModeById(String id);
}
