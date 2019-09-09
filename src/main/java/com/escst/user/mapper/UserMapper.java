package com.escst.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.role.entity.RoleEntity;
import com.escst.user.entity.UserEntity;
import com.escst.user.entity.UserRoleEntity;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

	UserEntity getUserByUserNameAndPasswordExits(Map<String,String> map);

	UserEntity selectUserById(String id);

	Map<String,Object> selectOrgInfoById(String userId);

	Map<String,Object> selectUserMap(String userName);
	
	/**
	 * 根据条件查询用户列表
	 * @param entity
	 * @return
	 */
	List<Map<String,Object>> selectByCondition(UserEntity entity);
	
	List<Map<String,Object>> selectConstructByUserId(String id);

	UserEntity selectUserByUserName(String userName);
	
	List<RoleEntity> selectRoleById(String id);

	List<String> selectMenuById(String id);
	
	void insertUserRole(UserRoleEntity entity);
	
	String getUuid(String userId);
	
	void updateStatus(String id);
	
	/**
	 * @desc 根据用户ID删除用户的角色
	 * @param userId 
	 * @author zhouwei
	 * @date 2017年9月14日 下午4:54:49
	 */
	void deleteByUserId(String userId);
	
	/**
	 * @desc 重置密码
	 * @param params 
	 * @author zhouwei
	 * @date 2017年10月31日 下午1:56:07
	 */
	void resetPassword(Map<String, Object> params);
	
	/**
	 * @desc 根据ID更新用户的姓名、性别、手机号码
	 * @param params 
	 * @author zhouwei
	 * @date 2017年11月6日 下午4:10:36
	 */
	void updateBaseInfo(Map<String, Object> params);

	/**
	* @desc 修改密码
	* @param params
	* @return
	* @author dwj
	* @date 2018/1/12 9:12
	*/
	void updatePassword(Map<String, Object> params);

	String getOrgId(String userId);


	List<UserEntity> selectByUser(Map<String,Object> map);
	
}
