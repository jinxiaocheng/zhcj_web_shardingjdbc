package com.escst.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.constant.Globals;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.MD5Util;
import com.escst.commons.utils.MyCrypto;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.role.entity.RoleEntity;
import com.escst.user.entity.UserEntity;
import com.escst.user.entity.UserRoleEntity;
import com.escst.user.mapper.UserMapper;
import com.escst.user.vo.UserVo;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// 有效
	private static final int VALID = 1;

	/**
	 * 检查用户名是否存在
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity checkUserExits(UserEntity user) {
		String password = MyCrypto.getInstance().md5Crypto(user.getUserName() + user.getUserPassword());
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", user.getUserName());
		map.put("password", password);
		UserEntity userEntity = userMapper.getUserByUserNameAndPasswordExits(map);
		return userEntity;
	}

	public UserEntity queryUserById(String id) {
		UserEntity entity = new UserEntity();
		try {
			entity = userMapper.selectUserById(id);
		} catch (Exception e) {
			throw new EscstException("通过用户id查询用户异常", e);
		}
		return entity;
	}

	/**
	 * 根据条件查询用户列表
	 * 
	 * @return
	 */
	public PageVo queryUser(UserEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			int count = userMapper.selectCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = userMapper.selectByCondition(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询用户列表异常" + e.getMessage(), e);
		}
		return pageVo;
	}

	/**
	 * 添加用户
	 * 
	 * @param bean
	 */
	public void addUser(UserEntity userEntity) {
		// 检查用户是否存在
		UserEntity checkUser = userMapper.selectUserByUserName(userEntity.getUserName());
		if (checkUser != null) {
			throw new EscstException("账号已存在");
		}

		try {
			// 保存用户信息
			userEntity.setId(UuidUtils.getUuid());
			userEntity.setOrgId(userEntity.getOrgId());
			userEntity.setCreateBy(ContextUtils.getCurrentUserId());
			userEntity.setCreateTime(new Date());
			// userEntity.setUserName(bean.getUserName());
			// userEntity.setName(bean.getName());
			// userEntity.setMobile(bean.getMobile());
			// userEntity.setEmail(bean.getEmail());
			// userEntity.setSex(bean.getSex());
			// userEntity.setIdCard(bean.getIdCard());
			userEntity.setStatus(VALID);
			userEntity.setUserPassword(MD5Util.md5Encode(userEntity.getUserName() + userEntity.getUserPassword()));
			userMapper.insert(userEntity);

		} catch (Exception e) {
			throw new EscstException("添加用户异常" + e.getMessage(), e);
		}
	}

	/**
	 * 根据用户Id查询组织信息
	 */
	public List<Map<String, Object>> queryConstructByUserId(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = this.userMapper.selectConstructByUserId(id);
		} catch (Exception e) {
			throw new EscstException("通过用户Id查询组织机构信息异常" + e.getMessage(), e);
		}
		return list;
	}

	/**
	 * 根据用户Id查询组织信息
	 */
	public List<RoleEntity> queryRoleById(String id) {
		List<RoleEntity> list = new ArrayList<RoleEntity>();
		try {
			list = this.userMapper.selectRoleById(id);
		} catch (Exception e) {
			throw new EscstException("通过用户Id查询组角色息异常" + e.getMessage(), e);
		}
		return list;
	}

	public List<String> queryRoleIdById(String id) {
		List<String> strList = new ArrayList<String>();
		List<RoleEntity> list = this.queryRoleById(id);
		for (RoleEntity entity : list) {
			strList.add(entity.getId());
		}
		return strList;
	}

	public void saveUserRole(String userId, String[] idList) {
		if (idList == null || idList.length == 0) {
			userMapper.deleteByUserId(userId);
			return;
		}
		// modify by zhouwei 2017-09-14 先删除该用户的角色,再给用户添加角色
		userMapper.deleteByUserId(userId);
		for (int i = 0; i < idList.length; i++) {
			UserRoleEntity entity = new UserRoleEntity();
			entity.setId(UuidUtils.getUuid());
			entity.setUserId(userId);
			entity.setRoleId(idList[i]);
			userMapper.insertUserRole(entity);
		}
	}

	public UserEntity checkUserName(String userName) {
		return userMapper.selectUserByUserName(userName);
	}

	public void delete(String id) {
		try {
			// 更新用户状态为无效
			userMapper.updateStatus(id);
			// 删除用户角色中间表
			userMapper.deleteByUserId(id);
		} catch (Exception e) {
			throw new EscstException("删除用户异常", e);
		}
	}

	/**
	 * @desc 重置密码
	 * @param userId
	 *            用户ID
	 * @author zhouwei
	 * @date 2017年10月31日 下午1:56:07
	 */
	public void resetPassword(String userId) {
		try {
			UserEntity entity = queryUserById(userId);
			if (entity == null) {
				return;
			}
			String userName = entity.getUserName();
			String password = userName + Globals.USER_DEFAULT_PASSWORD;
			String passwordEncode = MD5Util.md5Encode(password);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", userId);
			params.put("userPassword", passwordEncode);
			userMapper.resetPassword(params);
		} catch (Exception e) {
			throw new EscstException("重置用户密码出现异常");
		}
	}

	public void updateBaseInfo(Map<String, Object> params) {
		userMapper.updateBaseInfo(params);
	}

	public Map<String, Object> selectUserMap(String userName) {
		return userMapper.selectUserMap(userName);
	}

	public void insert(UserEntity entity) {
		userMapper.insert(entity);
	}

	public void batchInsert(List<UserEntity> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		userMapper.batchInsert(list);
	}

	public void updatePassWord(UserVo vo) {
		try {
			UserEntity entity = queryUserById(vo.getId());
			if (entity == null) {
				return;
			}
			String userName = entity.getUserName();
			String password = userName + vo.getUserPassword();
			String passwordEncode = MD5Util.md5Encode(password);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", vo.getId());
			params.put("userPassword", passwordEncode);
			userMapper.updatePassword(params);
		} catch (Exception e) {
			throw new EscstException("修改密码出现异常");
		}
	}

	public void edit(UserEntity bean) {
		try {
			// if (StringUtils.isNotBlank(bean.getCompany()) ||
			// bean.getPositionWorkType()!=null) {
			// PersonEntity personEntity = new PersonEntity();
			// personEntity.setProjectCompanyId(bean.getCompany());
			// personEntity.setPositionWorkType(bean.getPositionWorkType());
			// personEntity.setId(bean.getPersonId());
			// personMapper.update(personEntity);
			// }
			if (StringUtils.isNotBlank(bean.getUserPassword())) {
				bean.setUserPassword(MD5Util.md5Encode(bean.getUserName() + bean.getUserPassword()));
			}
			bean.setUpdateBy(ContextUtils.getCurrentUserId());
			bean.setUpdateTime(new Date());
			userMapper.update(bean);
		} catch (Exception e) {
			throw new EscstException("更新用户信息异常", e);
		}
	}

	public Map<String, Object> selectOrgInfoById(String id) {
		return userMapper.selectOrgInfoById(id);
	}
}
