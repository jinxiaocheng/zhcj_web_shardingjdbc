package com.escst.roleMenu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.utils.UuidUtils;
import com.escst.menu.entity.MenuEntity;
import com.escst.roleMenu.entity.RoleMenuEntity;
import com.escst.roleMenu.mapper.RoleMenuMapper;

/**
 * @desc 
 * @author niejing
 * @date 2017年7月28日 下午3:37:34
 */
@Service
@Transactional
public class RoleMenuService {

	@Autowired
	public RoleMenuMapper mapper;
	
	/**
	 * 
	 * @desc 生成超级管理员权限 
	 * @param list 
	 * @author niejing
	 * @date 2017年7月28日 下午4:34:26
	 */
	public void batchInsert(List<MenuEntity> list){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		List<RoleMenuEntity> roleList = new ArrayList<RoleMenuEntity>();
		for(MenuEntity menuEntity:list){
			RoleMenuEntity entity = new RoleMenuEntity();
			entity.setId(UuidUtils.getUuid());
			entity.setMenuId(menuEntity.getId());
			entity.setRoleId("21c1c04c36a642c1999c8a2e4ce82d95");//超级管理员
			entity.setOperationAuthority(menuEntity.getOperationAuthority());
			roleList.add(entity);
		}
		mapper.batchInsert(roleList);
	}
}
