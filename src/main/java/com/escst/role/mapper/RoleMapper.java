package com.escst.role.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.role.bean.RoleQueryBean;
import com.escst.role.entity.RoleEntity;

/**
 * 
 * @desc
 * @author niejing
 * @date 2017年4月15日 下午3:04:04
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {

	int selectCount(RoleQueryBean bean);

	List<Map<String, Object>> selectByCondition(RoleQueryBean bean);

	Map<String,Object> selectMapById(String id);

	int getCount(RoleQueryBean bean);

	List<Map<String, Object>> queryByCondition(RoleQueryBean bean);

	List<String> queryByUserId(String userId);

	void deleteByOrgId(String orgId);

}
