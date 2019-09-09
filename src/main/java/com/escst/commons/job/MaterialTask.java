package com.escst.commons.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.escst.commons.utils.UuidUtils;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.entity.MaterialModelEntity;
import com.escst.material.mapper.MaterialAcquisitionMapper;
import com.escst.material.mapper.MaterialApproachMapper;
import com.escst.material.mapper.MaterialMapper;

/**
 * @desc
 * @author niejing
 * @date 2017年8月21日 下午4:41:36
 */
@Component
public class MaterialTask {

	@Autowired
	public MaterialMapper mapper;
	@Autowired
	public MaterialApproachMapper approachMapper;
	@Autowired
	public MaterialAcquisitionMapper acquisitionMapper;

	public void copyData() {
		// step1 查询所有材料进场数据，并保存到材料表
		List<Map<String, Object>> approachlist = approachMapper.selectApproach();
		List<MaterialEntity> list = new ArrayList<MaterialEntity>();
		List<MaterialModelEntity> list2 = new ArrayList<MaterialModelEntity>();
		for (Map<String, Object> map : approachlist) {
			MaterialEntity entity = new MaterialEntity();
			String materialId  = UuidUtils.getUuid();
			entity.setId(materialId);

			String name = (String) map.get("name");
			entity.setName(name);
			entity.setCode((String) map.get("code"));
//			entity.setModel((String) map.get("model"));
			entity.setUnit((String) map.get("unit"));
			entity.setCreateBy((String) map.get("createBy"));
			entity.setCreateTime(new Date());
			entity.setUpdateBy((String) map.get("id"));// 暂时保存材料进场ID，后面会更新为null

			list.add(entity);
			
			MaterialModelEntity e2 =  new MaterialModelEntity();
			e2.setId(UuidUtils.getUuid());
			e2.setMaterialId(materialId);
			e2.setName((String) map.get("model"));
			e2.setCreateBy((String) map.get("createBy"));
			e2.setCreateTime(new Date());
			e2.setUpdateBy((String) map.get("id"));
			list2.add(e2);
		}
		mapper.batchInsert(list);
		mapper.batchInsertModel(list2);
		// step2 将材料id更新到材料进场和材料领用表里
		approachMapper.batchUpdateId();
		acquisitionMapper.batchUpdateId();
		
		//step3 清空材料表的updateBy字段
//		mapper.updateId(t);
	}
}
