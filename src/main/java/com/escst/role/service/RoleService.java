package com.escst.role.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escst.menu.entity.MenuEntity;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.role.bean.RoleQueryBean;
import com.escst.role.entity.RoleAndMenuBean;
import com.escst.role.entity.RoleEntity;
import com.escst.role.mapper.RoleMapper;
import com.escst.roleMenu.entity.RoleMenuEntity;
import com.escst.roleMenu.mapper.RoleMenuMapper;
import org.springframework.util.CollectionUtils;

/**
 * @author niejing
 * @desc 机构服务类
 * @date 2017年4月15日 下午3:03:24
 */
@Service
public class RoleService {

    @Autowired
    public RoleMapper roleMapper;
    @Autowired
    public RoleMenuMapper roleMenuMapper;


    public PageVo queryRoleByCondition(RoleQueryBean bean) {
        PageVo pageVo = new PageVo();

//		int count = roleMapper.selectCount(bean);
        int count = roleMapper.getCount(bean);
        // 当前页
        pageVo.setCurrentPage(bean.getPage());
        // 总记录数
        pageVo.setTotalRecord(count);
        pageVo.setPageSize(bean.getRowNum());
        // 每页第一条记录的索引
        int startIndex = (bean.getPage() - 1) * (bean.getRowNum());
        bean.setStartIndex(startIndex);
//		List<Map<String, Object>> list = roleMapper.selectByCondition(bean);
        List<Map<String, Object>> list = roleMapper.queryByCondition(bean);
        if (!CollectionUtils.isEmpty(list)) {
            pageVo.setRows(list);
        }
        return pageVo;
    }

    public Map<String, Object> queryById(String id) {
        return roleMapper.selectMapById(id);
    }

    /**
     * 增加角色
     *
     * @param bean
     */
    @Transactional
    public void addRole(RoleAndMenuBean bean) {
        try {
            // 增加角色基本信息
            RoleEntity roleEntity = new RoleEntity();
            String id = UuidUtils.getUuid();
            roleEntity.setId(id);
            roleEntity.setName(bean.getName());
            roleEntity.setOrgId(bean.getOrgId());
            roleEntity.setCreateBy(ContextUtils.getCurrentUserId());
            roleEntity.setCreateTime(new Date());
            roleMapper.insert(roleEntity);

            // 添加角色权限
            List<MenuEntity> menuEntityList = JSON.parseArray(bean.getMenuAuthMap(), MenuEntity.class);
            if (!CollectionUtils.isEmpty(menuEntityList)) {
                List<RoleMenuEntity> roleMenuEntityList = new ArrayList<RoleMenuEntity>();
                for (MenuEntity menuEntity : menuEntityList) {
                    String menuId = menuEntity.getId();
                    RoleMenuEntity t = new RoleMenuEntity();
                    t.setId(UuidUtils.getUuid());
                    t.setRoleId(id);
                    t.setMenuId(menuId);
                    t.setOperationAuthority(menuEntity.getOperationAuthority());
                    t.setName(menuEntity.getName());
                    t.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
                    roleMenuEntityList.add(t);
                }
                roleMenuMapper.batchInsert(roleMenuEntityList);
            }
        } catch (Exception e) {
            throw new EscstException("新增角色信息异常", e);
        }
    }

    /**
     * 更新机构菜单关系
     *
     * @param bean
     */
    @Transactional
    public void editRole(RoleAndMenuBean bean) {
        /**
         * 修改角色基本信息
         */
        try {
            RoleEntity roleEntity = roleMapper.selectById(bean.getRoleId());
            roleEntity.setName(bean.getName());
            roleEntity.setUpdateBy(ContextUtils.getCurrentUserId());
            roleEntity.setUpdateTime(new Date());
            roleMapper.update(roleEntity);
        } catch (Exception e) {
            throw new EscstException("更新角色表异常！");
        }

        /**
         * 修改角色权限
         */
        List<RoleMenuEntity> addList = new ArrayList<RoleMenuEntity>();
        try {
            // 删除角色原本的权限
            roleMenuMapper.removeById(bean.getRoleId());
            // 新增角色权限
            List<MenuEntity> menuEntityList = JSON.parseArray(bean.getMenuAuthMap(), MenuEntity.class);
            if (!CollectionUtils.isEmpty(menuEntityList)) {
                for (MenuEntity menuEntity : menuEntityList) {
                    RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                    roleMenuEntity.setId(UuidUtils.getUuid());
                    roleMenuEntity.setMenuId(menuEntity.getId());
                    roleMenuEntity.setName(menuEntity.getName());
                    roleMenuEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
                    roleMenuEntity.setRoleId(bean.getRoleId());
                    roleMenuEntity.setOperationAuthority(menuEntity.getOperationAuthority());
                    addList.add(roleMenuEntity);
                }
                roleMenuMapper.batchInsert(addList);
            }
        } catch (Exception e) {
            throw new EscstException("修改角色菜单信息异常！");
        }
    }
}
