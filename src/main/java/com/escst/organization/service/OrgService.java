package com.escst.organization.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escst.menu.entity.MenuEntity;
import com.escst.role.service.RoleService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.orgMenu.entity.OrgMenuEntity;
import com.escst.orgMenu.mapper.OrgMenuMapper;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.mapper.OrgMapper;
import com.escst.redis.annotation.RedisCache;
import com.escst.role.entity.RoleAndMenuBean;
import com.escst.role.entity.RoleEntity;
import com.escst.role.mapper.RoleMapper;
import com.escst.roleMenu.entity.RoleMenuEntity;
import com.escst.roleMenu.mapper.RoleMenuMapper;

/**
 * 机构业务相关类
 *
 * @author Administrator
 */
@Service
public class OrgService {

    @Autowired
    public OrgMapper orgMapper;

    @Autowired
    private OrgMenuMapper orgMenuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据父id查询下一级子机构
     *
     * @param parentId
     * @return
     */
    public List<OrgEntity> queryByParentId(String parentId) {
        List<OrgEntity> orgList = new ArrayList<OrgEntity>();
        try {
            List<OrgEntity> list = orgMapper.selectByParentId(parentId);
            if (!CollectionUtils.isEmpty(list)) {
                for (OrgEntity orgEntity : list) {
                    orgList.add(orgEntity);
                    List<OrgEntity> thridList = orgMapper.selectByParentId(orgEntity.getId());
                    if (!CollectionUtils.isEmpty(thridList)) {
                        for (OrgEntity thridEntity : thridList) {
                            orgList.add(thridEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("查询子机构异常", e);
        }

        return orgList;
    }

    /**
     * @param userId
     * @return
     * @desc 机构数-区分用户权限
     * @author niejing
     * @date 2017年10月27日 上午10:08:41
     */
    public List<TreeEntity> queryTreeListByUserId(String userId) {
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        List<OrgEntity> orgList = orgMapper.selectOrgByUserId(userId);
        for (OrgEntity entity : orgList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(entity.getParentId());
            treeEntity.setId(entity.getId());
            treeEntity.setName(entity.getName());
            // 只显示二级树慈菜单
            if (entity.getParentId().equals("1")) {
                // 树节点的父id为1，则展开
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }
            list.add(treeEntity);
        }
        return list;
    }

    public PageVo queryByNodeId(OrgEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            // 因为查出自己，所以加1
            int count = this.orgMapper.selectByParentIdCount(entity.getParentId()) + 1;
            // 当前页
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<OrgEntity> list = orgMapper.selectBypId(entity);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("根据id查询机构信息异常:" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 根据机构id查询结构信息
     *
     * @param id
     * @return
     */
    public OrgEntity queryById(String id) {
        OrgEntity orgEntity = null;
        try {
            orgEntity = orgMapper.selectById(id);
        } catch (Exception e) {
            throw new EscstException("根据id查询机构信息异常", e);
        }
        return orgEntity;
    }

    public Map<String, Object> queryMapById(String id) {
        Map<String, Object> map = null;
        try {
            map = orgMapper.selectMapById(id);
        } catch (Exception e) {
            throw new EscstException("根据id查询机构信息异常", e);
        }
        return map;
    }

    /**
     * 新增机构信息，返回机构ID
     */
    public String addOrg(RoleAndMenuBean bean) {
        try {
            OrgEntity orgEntity = new OrgEntity();
            String orgId = bean.getOrgId();
            if (StringUtils.isBlank(orgId)) {
                orgEntity.setParentId("1");
            }
            String sysCode = getNextSysCode(orgId);
            String id = UuidUtils.getUuid();
            orgEntity.setId(id);
            orgEntity.setParentId(orgId);
            orgEntity.setName(bean.getName());
            orgEntity.setSysCode(sysCode);
            orgEntity.setStatus(1);
            orgEntity.setCreateBy(ContextUtils.getCurrentUserId());
            orgEntity.setCreateTime(new Date());
            orgMapper.insert(orgEntity);
            return id;
        } catch (Exception e) {
            throw new EscstException("新增机构信息异常", e);
        }
    }

    /**
     * @param bean 机构菜单
     * @return void
     * @desc 添加机构，机构对应的菜单
     * @author jincheng
     * @date 2018/1/17 13:54
     */
    public void addOrgMenu(RoleAndMenuBean bean) {
        try {
            // 保存机构信息
            String orgId = this.addOrg(bean);
            List<MenuEntity> menuList = JSON.parseArray(bean.getMenuAuthMap(), MenuEntity.class);
            // 保存机构菜单表
            if (!CollectionUtils.isEmpty(menuList)) {
                List<OrgMenuEntity> orgMenuEntityList = new ArrayList<OrgMenuEntity>();
                for (MenuEntity entity : menuList) {
                    String menuId = entity.getId();
                    OrgMenuEntity t = new OrgMenuEntity();
                    t.setId(UuidUtils.getUuid());
                    t.setOrgId(orgId);
                    t.setMenuId(menuId);
                    t.setName(entity.getName());
                    t.setSortNum(entity.getSortNum() == null ? 0 : entity.getSortNum());
                    t.setOperationAuthority(entity.getOperationAuthority());
                    orgMenuEntityList.add(t);
                }
                // 批量添加
                orgMenuMapper.batchInsert(orgMenuEntityList);
            }

            /**
             * 按照机构权限，在机构下面新增一个系统管理员的角色
             */
            bean.setOrgId(orgId);
            bean.setName("系统管理员");
            roleService.addRole(bean);

        } catch (Exception e) {
            throw new EscstException("新增机构信息异常", e);
        }
    }


    /**
     * 根据id删除机构信息（逻辑删除，修改状态为0）
     *
     * @param id
     */
    public void delOrg(String id) {
        try {
            OrgEntity orgEntity = new OrgEntity();
            orgEntity.setId(id);
            orgEntity.setStatus(0);
            orgMapper.update(orgEntity);
            // 删除机构下面的所有角色
            roleMapper.deleteByOrgId(id);
        } catch (Exception e) {
            throw new EscstException("删除机构信息异常", e);
        }
    }

    /**
     * 更新机构信息
     *
     * @param bean
     */
    public void udpateOrg(RoleAndMenuBean bean) {
        try {
            OrgEntity entity = new OrgEntity();
            entity.setId(bean.getOrgId());
            entity.setName(bean.getName());
            entity.setUpdateBy(ContextUtils.getCurrentUserId());
            entity.setUpdateTime(new Date());
            orgMapper.update(entity);
        } catch (Exception e) {
            throw new EscstException("更新机构信息异常" + e.getMessage(), e);
        }
    }

    /**
     * @param bean 机构信息，权限菜单信息
     * @return void
     * @desc 更新机构，机构菜单关系
     * @author jincheng
     * @date 2018/1/17 15:37
     */
    public void udpateOrgMenu(RoleAndMenuBean bean) {
        // 修改机构名称等信息
        this.udpateOrg(bean);
        // 查询出机构的所有权限
        List<OrgMenuEntity> list = orgMenuMapper.selectByOrgId(bean.getOrgId());
        // 修改机构下，角色的按钮
        this.updateOperationAuthority(list, bean);
        // 删除机构原本的权限
        orgMenuMapper.removeById(bean.getOrgId());

        try {
            List<OrgMenuEntity> addList = new ArrayList<OrgMenuEntity>();
            // 处理前台传过来的权限
            List<MenuEntity> menuEntityList = JSON.parseArray(bean.getMenuAuthMap(), MenuEntity.class);
            if (!CollectionUtils.isEmpty(menuEntityList)) {
                for (MenuEntity menuEntity : menuEntityList) {
                    OrgMenuEntity orgMenuEntity = new OrgMenuEntity();
                    orgMenuEntity.setId(UuidUtils.getUuid());
                    orgMenuEntity.setMenuId(menuEntity.getId());
                    orgMenuEntity.setName(menuEntity.getName());
                    orgMenuEntity.setOrgId(bean.getOrgId());
                    orgMenuEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
                    orgMenuEntity.setOperationAuthority(menuEntity.getOperationAuthority());
                    addList.add(orgMenuEntity);
                }
            }
            // 添加权限到数据库中
            orgMenuMapper.batchInsert(addList);
            // 删除机构下的角色权限
            this.deleteRoleMenu(list, addList, bean);
        } catch (Exception e) {
            throw new EscstException("更新机构菜单表异常！");
        }


    }

    /**
     * @param list    机构原来的权限
     * @param addlist 修改机构原来的权限
     * @desc 根据机构被删除的权限，删除机构下的角色权限
     */
    private void deleteRoleMenu(List<OrgMenuEntity> list, List<OrgMenuEntity> addlist, RoleAndMenuBean bean) {


        // 获取机构被删除的菜单权限
        List<OrgMenuEntity> differenceList = new ArrayList<OrgMenuEntity>();
        for (OrgMenuEntity orgMenuEntity : list) {
            boolean flag = true;
            for (OrgMenuEntity entity : addlist) {
                if (orgMenuEntity.getMenuId().equals(entity.getMenuId())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                differenceList.add(orgMenuEntity);
            }
        }

        /**
         * 根据机构被删除的权限，删除角色的权限
         */
        List<RoleMenuEntity> roleMenuList = new ArrayList<RoleMenuEntity>();
        List<RoleEntity> roleIdList = new ArrayList<RoleEntity>();
        if (!CollectionUtils.isEmpty(differenceList)) {
            // 获得机构下面的角色id集合
            roleIdList = orgMapper.queryRoleByOrgId(bean.getOrgId());
            for (RoleEntity roleEntity : roleIdList) {
                // 获得每个角色对应的权限菜单
                roleMenuList = roleMenuMapper.selectByRoleId(roleEntity.getId());
                // 如果角色菜单拥有机构被删除的菜单，则删除角色的菜单
                for (OrgMenuEntity orgMenuEntity : differenceList) {
                    String menuId = orgMenuEntity.getMenuId();
                    for (RoleMenuEntity roleMenuEntity : roleMenuList) {
                        if (roleMenuEntity.getMenuId().equals(menuId)) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("menuId", roleMenuEntity.getMenuId());
                            map.put("roleId", roleMenuEntity.getRoleId());
                            // 删除
                            roleMenuMapper.deleteMenuIdByRoleId(map);

                        }
                    }
                }
            }
        }
    }

    /**
     * @param list 机构原来的权限
     * @param bean 前台传来修改之后的权限字符串
     * @return void
     * @desc 机构菜单按钮减少时，机构下面的角色菜单按钮也减少
     * @author jincheng
     * @date 2018/2/22 13:08
     */
    private void updateOperationAuthority(List<OrgMenuEntity> list, RoleAndMenuBean bean) {
        List<OrgMenuEntity> operationAuthorityList = new ArrayList<OrgMenuEntity>();
        // 处理前台传过来的json权限串
        List<MenuEntity> menuEntityList = JSON.parseArray(bean.getMenuAuthMap(), MenuEntity.class);
        if (!CollectionUtils.isEmpty(menuEntityList)) {
            for (MenuEntity menuEntity : menuEntityList) {
                String menuId = menuEntity.getId();
                String operationAuthority = menuEntity.getOperationAuthority();
                OrgMenuEntity orgMenuEntity = new OrgMenuEntity();
                orgMenuEntity.setMenuId(menuId);
                orgMenuEntity.setOperationAuthority(operationAuthority == null ? "" : operationAuthority);
                // 前台修改之后的机构权限集合
                operationAuthorityList.add(orgMenuEntity);
            }
        }

        // 获得被减少的按钮菜单集合
        List<OrgMenuEntity> resultList = new ArrayList<OrgMenuEntity>();
        for (OrgMenuEntity entity : operationAuthorityList) {
            for (OrgMenuEntity orgMenuEntity : list) {
                if (orgMenuEntity.getMenuId().equals(entity.getMenuId())) {
                    // 菜单相同
                    if (entity.getOperationAuthority().equals(orgMenuEntity.getOperationAuthority())) {
                        // 按钮没有修改，跳过
                        continue;
                    } else {
                        if (StringUtils.isBlank(entity.getOperationAuthority()) && StringUtils.isBlank(orgMenuEntity.getOperationAuthority())) {
                            continue;
                        }
                        // 获得该菜单下，被减少的按钮权限
                        String oldOperationAuthority = orgMenuEntity.getOperationAuthority();
                        String newOperationAuthority = entity.getOperationAuthority();
                        List<String> beDeleteButtonList = this.listBeDeleteButton(oldOperationAuthority, newOperationAuthority);
                        entity.setBeDeleteButton(beDeleteButtonList);
                        resultList.add(entity);
                    }
                }
            }

        }

        if (CollectionUtils.isEmpty(resultList)) {
            return;
        }
        List<RoleMenuEntity> roleMenuList = new ArrayList<RoleMenuEntity>();
        List<RoleEntity> roleIdList = new ArrayList<RoleEntity>();
        // 获得机构下面的角色id集合
        roleIdList = orgMapper.queryRoleByOrgId(bean.getOrgId());
        for (RoleEntity roleEntity : roleIdList) {
            // 获得每个角色对应的权限菜单
            roleMenuList = roleMenuMapper.selectByRoleId(roleEntity.getId());
            for (OrgMenuEntity orgMenuEntity : resultList) {
                for (RoleMenuEntity roleMenuEntity : roleMenuList) {
                    if (orgMenuEntity.getMenuId().equals(roleMenuEntity.getMenuId())) {
                        // 获得角色原本的权限
                        String roleOperationAuthority = roleMenuEntity.getOperationAuthority();
                        List<String> roleButtonList = Arrays.asList(roleOperationAuthority.split(","));
                        // 机构里，该菜单被删除的权限
                        List<String> beDeleteButtonList = orgMenuEntity.getBeDeleteButton();
                        // 获得角色剩下的权限
                        String operationAuthority = this.getRoleButton(roleButtonList, beDeleteButtonList);
                        // 修改角色的按钮
                        if (!operationAuthority.equals(roleMenuEntity.getOperationAuthority())) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("menuId", roleMenuEntity.getMenuId());
                            map.put("roleId", roleMenuEntity.getRoleId());
                            map.put("operationAuthority", operationAuthority);
                            roleMenuMapper.updateOperationAuthority(map);
                        }
                    }
                }
            }
        }

    }


    /**
     * 获得机构被删除的按钮权限
     */
    private List<String> listBeDeleteButton(String oldButtonString, String newButtonString) {
        List<String> oldButtonList = Arrays.asList(oldButtonString.split(","));
        List<String> newButtonList = Arrays.asList(newButtonString.split(","));
        // 获取机构被删除的按钮权限
        List<String> beDeleteButtonList = new ArrayList<String>();
        for (String oldButton : oldButtonList) {
            boolean flag = true;
            for (String newButton : newButtonList) {
                if (oldButton.equals(newButton)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                beDeleteButtonList.add(oldButton);
            }
        }
        return beDeleteButtonList;
    }


    /**
     * 获得角色剩下的按钮权限
     */
    private String getRoleButton(List<String> roleButtonList, List<String> beDeleteButtonList) {
        List<String> resultList = new ArrayList<String>();
        String resultRoleButton = "";
        for (String button : roleButtonList) {
            boolean flag = true;
            for (String deleteButton : beDeleteButtonList) {
                if (button.equals(deleteButton)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                resultList.add(button);
            }
        }
        String[] array = new String[resultList.size()];
        String[] buttonArray = resultList.toArray(array);
        resultRoleButton = StringUtils.join(buttonArray, ",");
        return resultRoleButton;
    }


    /**
     * 根据工地ID查询机构信息
     *
     * @param constructionId
     * @return
     */
    public OrgEntity queryByconstructionId(String constructionId) {
        OrgEntity entity = new OrgEntity();
        try {
            entity = orgMapper.selectByconstructionId(constructionId);
        } catch (Exception e) {
            throw new EscstException("根据工地ID查询机构信息异常！", e);
        }
        return entity;
    }

    /**
     * 批量插入机构数据
     *
     * @param
     * @desc
     * @author niejing
     * @date 2017年4月27日 下午3:09:06
     */
    public void addOrgBatch(String constructionSel, String constructNames, String parentId) {
        try {
            String[] arrayIds = constructionSel.split(",");
            String[] names = constructNames.split(",");
            for (int i = 0; i < arrayIds.length; i++) {
                String constructionId = arrayIds[i];
                int count = orgMapper.checkIsExsit(parentId, constructionId);
                if (count == 1) {
                    continue;
                }
                OrgEntity orgEntity = new OrgEntity();
                String sysCode = getNextSysCode(parentId);
                String name = names[i];
                orgEntity.setId(UuidUtils.getUuid());
                orgEntity.setParentId(parentId);
                orgEntity.setConstructionId(constructionId);
                orgEntity.setName(name);
                orgEntity.setStatus(1);
                orgEntity.setLevel(3);
                orgEntity.setSysCode(sysCode);
                orgMapper.insert(orgEntity);
            }
        } catch (Exception e) {
            throw new EscstException("批量插入机构数据异常" + e.getMessage(), e);
        }
    }

    /**
     * @param parentId
     * @return
     * @desc 得到下一个子节点的编码
     * @author zhouwei
     * @date 2017年8月1日 下午2:47:13
     */
    public String getNextSysCode(String parentId) {
        Map<String, Object> map = getSysCodeAndChildrenNum(parentId);
        if (MapUtils.isEmpty(map)) {
            throw new EscstException("父节点[" + parentId + "]不存在");
        }
        String sysCode = (String) map.get("sys_code");
        Long num = (Long) map.get("num");
        String[] strs = sysCode.split("_");
        int strLen = strs.length > 2 ? 3 : strs.length + 1;// 树形结构的第一级用1个字符,第二级用2个字符,三级或以上的用3个字符
        String index = convertString(num.intValue() + 1, strLen);
        return sysCode + "_" + index;
    }

    public void initSysCode() {
        String rootId = "c18c1656727011e79386b82a72dd25a2";
        String parentSysCode = "1";
        OrgEntity rootOrg = orgMapper.selectById(rootId);
        rootOrg.setSysCode(parentSysCode);
        orgMapper.update(rootOrg);

        initSysCode(rootId, parentSysCode);
    }

    /**
     * @param parentId
     * @param parentSysCode
     * @desc 为机构重新设置长编码
     * @author zhouwei
     * @date 2017年8月2日 上午8:58:05
     */
    private void initSysCode(String parentId, String parentSysCode) {
        List<OrgEntity> list = orgMapper.selectByParentId(parentId);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String[] strs = parentSysCode.split("_");
        int strLen = strs.length + 1;
        if (strLen > 3) {
            strLen = 3;
        }
        for (int i = 0; i < list.size(); i++) {
            String index = convertString(i + 1, strLen);
            String subSysCode = parentSysCode + "_" + index;
            OrgEntity subOrg = list.get(i);
            subOrg.setSysCode(subSysCode);
            orgMapper.update(subOrg);

            initSysCode(subOrg.getId(), subSysCode);
        }
    }

    /**
     * @param val
     * @param stringLength
     * @return
     * @desc 将数字转换为指定长度的字符串, 如果数字长度不够, 则在前面用0补齐
     * @author zhouwei
     * @date 2017年8月1日 下午2:41:28
     */
    private String convertString(int val, int stringLength) {
        String str = String.valueOf(val);
        if (str.length() >= stringLength) {
            return str;
        }
        int len = stringLength - str.length();
        for (int i = 0; i < len; i++) {
            str = "0" + str;
        }
        return str;
    }


    /**
     * @param parentId
     * @return
     * @desc 根据父节点ID得到父节点编码以及子节点数量
     * @author zhouwei
     * @date 2017年8月1日 下午3:11:05
     */
    public Map<String, Object> getSysCodeAndChildrenNum(String parentId) {
        return orgMapper.getSysCodeAndChildrenNum(parentId);
    }

    /**
     * @param areaId
     * @return
     * @desc 查询该区域下所有的工地对应的组织机构
     * @author niejing
     * @date 2017年8月26日 下午4:18:41
     */
    public List<String> queryParentIdByArea(String areaId) {
        return orgMapper.selectParentIdByArea(areaId);
    }

    /**
     * @param constructionId
     * @return
     * @desc 根据工地ID得到机构集合
     * @author zhouwei
     * @date 2017年10月27日 下午12:49:21
     */
    @RedisCache
    public List<OrgEntity> queryByConstructionId(String constructionId) {
        return orgMapper.selectByConstructionId(constructionId);
    }

    /**
     * @param sysCode
     * @return
     * @desc 根据sysCode获取父节点, 父节点按照关系由近到远排列
     * @author zhouwei
     * @date 2017年12月13日 下午6:28:59
     */
    public List<OrgEntity> queryParentBySysCode(String sysCode) {
        return orgMapper.selectParentBySysCode(sysCode);
    }


}
