package com.escst.roleMenu.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.roleMenu.entity.RoleMenuEntity;

/**
 * 
 * @desc 
 * @author niejing
 * @date 2017年4月15日 下午3:03:05
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity>{
	List<RoleMenuEntity> selectByRoleId(String roleId);

	void deleteMenuIdByRoleId(Map<String,String> map);

	void updateOperationAuthority(Map<String,String> map);
}
