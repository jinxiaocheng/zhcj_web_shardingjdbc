package com.escst.construction.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.construction.entity.ConstructionLicenseEntity;
import org.springframework.stereotype.Repository;

/**
 * @desc 
 * @author niejing
 * @date 2017年8月10日 下午2:39:21
 */
@Repository
public interface ConstructionLicenseMapper extends BaseMapper<ConstructionLicenseEntity> {
	
	public ConstructionLicenseEntity selectConstructionLicenseById(String id);

}
