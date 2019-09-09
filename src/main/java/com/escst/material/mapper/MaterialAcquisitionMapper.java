package com.escst.material.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialAcquisitionEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月21日 下午5:02:42
 */
@Repository
public interface MaterialAcquisitionMapper extends BaseMapper<MaterialAcquisitionEntity> {

	Map<String, Object> queryAcquisitionDetailInfo(MaterialAcquisitionEntity entity);

	void insertUseMatInfo(MaterialAcquisitionEntity materialAcquisitionEntity);

	List<Map<String, Object>> selectUseMaterialList(MaterialBean entity);

	void updateStatus(MaterialAcquisitionEntity materialAcquisitionEntity);

	String selectApproachId(String id);

	int selectAcquisitionCount(MaterialBean entity);
	
	/**
	 * 
	 * @desc 将材料ID更新到材料领用表  
	 * @author niejing
	 * @date 2017年8月22日 上午10:14:07
	 */
	void batchUpdateId();
}
