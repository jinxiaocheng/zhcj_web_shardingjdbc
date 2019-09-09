package com.escst.menu.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.menu.entity.MenuEntity;
import com.escst.menu.mapper.MenuMapper;
import com.escst.menu.vo.MenuVO;
import com.escst.orgMenu.entity.OrgMenuEntity;
import com.escst.orgMenu.mapper.OrgMenuMapper;
import com.escst.organization.mapper.OrgMapper;
import com.escst.redis.annotation.RedisCache;
import com.escst.role.entity.RoleEntity;
import com.escst.roleMenu.entity.RoleMenuEntity;
import com.escst.roleMenu.mapper.RoleMenuMapper;
import com.escst.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author caozx
 * @desc 菜单service相关
 * @date 2017/3/6 10:11
 */
@Service
@Transactional
public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    // 平台标识
    private static final String PLAT = "1";
    // app标识
    private static final String APP = "2";

    //平台一级菜单
    private static final String PLAT_ONE_LEVEL_MENU = "1";

    private static final String APP_ONE_LEVEL_MENU = "2";

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrgMenuMapper orgMenuMapper;


    /**
     * 查询主菜单
     *
     * @return
     */
    public PageVo queryByNodeId(MenuEntity entity) {

        PageVo pageVo = new PageVo();
        try {
//            entity.setOrgId(userMapper.getOrgId(ContextUtils.getCurrentUserId()));
            int count = menuMapper.selectCount(entity);
            // 当前页
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<MenuEntity> menulist = menuMapper.selectByPId(entity);
            pageVo.setRows(menulist);
        } catch (Exception e) {
            throw new EscstException("查询主菜单出现异常:" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * @return
     * @desc 得到系统所有的菜单 (修改为得到本机构所拥有的菜单)
     * @author zhouwei
     * @date 2017年9月14日 下午6:04:02
     */
    public List<TreeEntity> queryMenuTree(MenuEntity entity) {
        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
        menuList = menuMapper.queryMenuByOrgId(entity.getOrgId());
        List<TreeEntity> rst = new ArrayList<TreeEntity>();
        for (MenuEntity menuEntity : menuList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(menuEntity.getParentId());
            treeEntity.setId(menuEntity.getId());
            treeEntity.setName(menuEntity.getName());
            treeEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
            treeEntity.setOperationAuthority(menuEntity.getOperationAuthority());
            // 只显示二级菜单树
            if ("0".equals(menuEntity.getParentId())) {
                // 树节点的父节点id为0，就展开
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }

            rst.add(treeEntity);
        }
        Collections.sort(rst);
        return rst;
    }

    /**
     * 查询菜单（录入机构时调用）
     *
     * @param
     * @return
     */
    public List<TreeEntity> queryAllMenuList(MenuEntity entity) {
        List<String> checkedList = new ArrayList<String>();
        if (StringUtils.isNotBlank(entity.getRoleId())) {
            checkedList = this.queryMenuIdByRoleId(entity.getRoleId());
        }

        List<TreeEntity> list = new ArrayList<TreeEntity>();

        List<MenuEntity> menuList = this.queryMenuByNoFlag();

        for (MenuEntity menuEntity : menuList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(menuEntity.getParentId());
            treeEntity.setId(menuEntity.getId());
            treeEntity.setName(menuEntity.getName());
            treeEntity.setOperationAuthority(menuEntity.getOperationAuthority());
            treeEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
            if (StringUtils.equals(menuEntity.getParentId(), PLAT_ONE_LEVEL_MENU)
                    || StringUtils.equals(menuEntity.getParentId(), APP_ONE_LEVEL_MENU)) {
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }
            if (!CollectionUtils.isEmpty(checkedList)) {
                if (checkedList.contains(menuEntity.getId())) {
                    treeEntity.setChecked(true);
                }
            }
            list.add(treeEntity);
        }
        return list;
    }


    /**
     * @param orgId 机构Id
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询机构对应的菜单树集合(自动选中拥有的菜单)
     * @author jincheng
     * @date 2018/1/17 14:29
     */
    public List<TreeEntity> queryMenuList(String orgId) {

        // 获得机构的父ID
        String parentOrgId = orgMapper.getParentOrgId(orgId);

        List<String> checkedList = new ArrayList<String>();
        List<TreeEntity> treeList = new ArrayList<TreeEntity>();
        if (StringUtils.isNotBlank(orgId)) {
            // 根据机构id，查询对应的菜单id集合
            checkedList = this.queryMenuIdByorgId(orgId);
            // 查询机构的菜单
            treeList = this.listMenuByOrgId(orgId);
        }

        List<TreeEntity> list = new ArrayList<TreeEntity>();
        List<MenuEntity> menuList = this.queryMenuByNoFlag();

        for (MenuEntity menuEntity : menuList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(menuEntity.getParentId());
            treeEntity.setId(menuEntity.getId());
            treeEntity.setName(menuEntity.getName());
            treeEntity.setOperationAuthority(menuEntity.getOperationAuthority());
            treeEntity.setFlag(menuEntity.getFlag());
            treeEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
            if (StringUtils.equals(menuEntity.getParentId(), PLAT_ONE_LEVEL_MENU)
                    || StringUtils.equals(menuEntity.getParentId(), APP_ONE_LEVEL_MENU)) {
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }
            if (!CollectionUtils.isEmpty(checkedList)) {
                // 前台自动选中机构对应的菜单
                if (checkedList.contains(menuEntity.getId())) {
                    treeEntity.setChecked(true);
                }
            }
            list.add(treeEntity);
        }
        // 编辑机构时，处理机构个性化名称，排序
        for (TreeEntity treeEntity : treeList) {
            for (TreeEntity entity : list) {
                if (treeEntity.getId().equals(entity.getId())) {
                    entity.setSortNum(treeEntity.getSortNum());
                    entity.setName(treeEntity.getName());
                }
            }
        }
        Collections.sort(list);
        return list;
    }


    /**
     * @param orgId 机构Id
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询机构被选中的菜单树集合(机构录入角色时调用)
     * @author jincheng
     * @date 2018/1/17 14:29
     */
    public List<TreeEntity> queryOrgMenuList(String orgId) {
        List<String> checkedList = new ArrayList<String>();
        List<MenuEntity> operationList = new ArrayList<MenuEntity>();
        if (StringUtils.isNotBlank(orgId)) {
            // 查询机构对应的菜单id集合
            checkedList = this.queryMenuIdByorgId(orgId);
            // 查询机构对应的权限
            operationList = this.selectOperationByOrgId(orgId);
        }

        List<TreeEntity> list = new ArrayList<TreeEntity>();
        List<MenuEntity> menuList = this.queryMenuByOrgId(orgId);

        for (MenuEntity menuEntity : menuList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(menuEntity.getParentId());
            treeEntity.setId(menuEntity.getId());
            treeEntity.setName(menuEntity.getName());
            treeEntity.setOperationAuthority(menuEntity.getOperationAuthority());
            treeEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
            if (StringUtils.equals(menuEntity.getParentId(), PLAT_ONE_LEVEL_MENU)
                    || StringUtils.equals(menuEntity.getParentId(), APP_ONE_LEVEL_MENU)) {
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }
            if (!CollectionUtils.isEmpty(checkedList)) {
                if (checkedList.contains(menuEntity.getId())) {
                    treeEntity.setChecked(false);
                }
            }
            // 设置机构菜单相应的操作权限
            if (!CollectionUtils.isEmpty(operationList)) {
                for (MenuEntity entity : operationList) {
                    if (menuEntity.getId().equals(entity.getId())) {
                        if (!entity.getOperationAuthority().equals(menuEntity.getOperationAuthority())) {
                            treeEntity.setOperationAuthority(entity.getOperationAuthority());
                        }
                    }
                }
            }
            list.add(treeEntity);
        }
        return list;
    }

    /**
     * @param orgId 机构Id
     * @return java.util.List<com.escst.menu.entity.MenuEntity>
     * @desc 根据机构id, 查询机构对应的菜单id，操作权限
     * @author jincheng
     * @date 2018/1/22 17:07
     */
    private List<MenuEntity> selectOperationByOrgId(String orgId) {
        List<MenuEntity> list = new ArrayList<MenuEntity>();
        if (StringUtils.isNotBlank(orgId)) {
            list = menuMapper.selectOperationByOrgId(orgId);
        }
        return list;
    }

    /**
     * @return
     * @desc 查询所有的菜单
     * @author niejing
     * @date 2017年11月9日 下午6:35:05
     */
    @RedisCache
    public List<MenuEntity> queryMenuByNoFlag() {
        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
        try {
            menuList = menuMapper.selectByFlag(null);
        } catch (Exception e) {
            logger.error("查询所有菜单异常", e);
        }
        return menuList;
    }

    /**
     * @param orgId 机构Id
     * @return java.util.List<com.escst.menu.entity.MenuEntity>
     * @desc 根据机构id, 查询机构菜单
     * @author jincheng
     * @date 2018/1/18 9:39
     */
    public List<MenuEntity> queryMenuByOrgId(String orgId) {
        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
        try {
            menuList = menuMapper.queryMenuByOrgId(orgId);
        } catch (Exception e) {
            logger.error("查询机构菜单异常", e);
        }
        return menuList;
    }

    /**
     * @return
     * @desc 查询一级菜单
     * @author zhouwei
     * @date 2017年4月22日 下午8:16:14
     */
    public List<MenuEntity> queryLevelOne() {
        try {
            List<MenuEntity> list = menuMapper.selectByParentId(PLAT_ONE_LEVEL_MENU, PLAT);
            return list;
        } catch (Exception e) {
            throw new EscstException("查询一级菜单出现异常:" + e.getMessage(), e);
        }
    }

    /**
     * 查询二级菜单
     *
     * @return
     */
    public List<MenuEntity> queryLevelTwo() {
        List<MenuEntity> levelTwolist = new ArrayList<MenuEntity>();
        try {
            List<MenuEntity> list = menuMapper.selectByParentId(PLAT_ONE_LEVEL_MENU, PLAT);
            for (MenuEntity levelOne : list) {
                List<MenuEntity> menuList = menuMapper.selectByParentId(levelOne.getId(), PLAT);
                for (MenuEntity levelTwo : menuList) {
                    levelTwolist.add(levelTwo);
                }
            }
        } catch (Exception e) {
            throw new EscstException("查询二级菜单出现异常:" + e.getMessage(), e);
        }
        return levelTwolist;
    }

    /**
     * 根据父id查询子菜单
     *
     * @param parentId
     * @return
     */
    public List<MenuEntity> queryByParentId(String parentId) {
        List<MenuEntity> list = new ArrayList<MenuEntity>();
        try {
            List<MenuEntity> menuList = menuMapper.selectByParentId(parentId, PLAT);
            if (!CollectionUtils.isEmpty(menuList)) {
                for (MenuEntity menuEntity : menuList) {
                    list.add(menuEntity);
                    String id = menuEntity.getId();
                    // 通过id查询下级菜单数据
                    List<MenuEntity> threeMenuList = menuMapper.selectByParentId(id, PLAT);
                    if (!CollectionUtils.isEmpty(threeMenuList)) {
                        for (MenuEntity threeEntity : threeMenuList) {
                            list.add(threeEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("查询子菜单出现异常:" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 根据父id查询子菜单（APP）
     *
     * @param parentId
     * @return
     */
    public List<MenuEntity> queryAppByParentId(String parentId) {
        List<MenuEntity> list = new ArrayList<MenuEntity>();
        try {
            List<MenuEntity> menuList = menuMapper.selectByParentId(parentId, APP);
            if (!CollectionUtils.isEmpty(menuList)) {
                for (MenuEntity menuEntity : menuList) {
                    list.add(menuEntity);
                    String id = menuEntity.getId();
                    // 通过id查询下级菜单数据
                    List<MenuEntity> threeMenuList = menuMapper.selectByParentId(id, APP);
                    if (!CollectionUtils.isEmpty(threeMenuList)) {
                        for (MenuEntity threeEntity : threeMenuList) {
                            list.add(threeEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("查询子菜单出现异常:" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 根据Id查询菜单信息
     *
     * @param menuId
     * @return
     */
    public MenuEntity queryMenuById(OrgMenuEntity orgMenuEntity) {
        MenuEntity menuEntity = new MenuEntity();
        try {
            menuEntity.setOrgId(orgMenuEntity.getOrgId());
            menuEntity.setId(orgMenuEntity.getId());
            menuEntity = menuMapper.selectById(menuEntity);
        } catch (Exception e) {
            throw new EscstException("根据ID查询菜单异常", e);
        }
        return menuEntity;
    }

    /**
     * 新增菜单
     *
     * @param menuEntity
     */
    public void addMenu(MenuEntity menuEntity) {
        try {
//            int sortNum = getLatestSortNum(menuEntity.getParentId());
            String id = UuidUtils.getUuid();
            menuEntity.setId(id);
            // 获取父菜单的flag
            int flag = menuMapper.getFlag(menuEntity.getParentId());
            menuEntity.setFlag(flag);
            menuEntity.setSortNum(menuEntity.getSortNum());
            menuEntity.setCreateTime(new Date());
            menuEntity.setCreateBy(ContextUtils.getCurrentUserId());
            menuMapper.insertMenu(menuEntity);

            OrgMenuEntity orgMenuEntity = new OrgMenuEntity();
            orgMenuEntity.setId(UuidUtils.getUuid());
            orgMenuEntity.setOrgId(menuEntity.getOrgId());
            orgMenuEntity.setMenuId(id);
            orgMenuEntity.setName(menuEntity.getName());
            orgMenuEntity.setSortNum(menuEntity.getSortNum());
            orgMenuMapper.insert(orgMenuEntity);
        } catch (Exception e) {
            throw new EscstException("新增菜单异常" + e.getMessage(), e);
        }
    }

    /**
     * 修改菜单
     *
     * @param menuEntity
     */
    public void updateMenu(MenuEntity menuEntity) {
        try {

            // 恒信国通机构修改菜单名称时，修改t_sys_menu表
            if (menuEntity.getOrgId().equals(ResourceUtil.gethxgtOrgId())) {
                menuMapper.update(menuEntity);
            }

            // 修改机构对应的菜单
            menuMapper.updateOrgMenu(menuEntity);

            // 获得机构下面的角色id集合
            List<RoleEntity> roleIdList = orgMapper.queryRoleByOrgId(menuEntity.getOrgId());
            for (RoleEntity roleEntity : roleIdList) {
                // 获得每个角色对应的权限菜单
                List<RoleMenuEntity> roleMenuList = roleMenuMapper.selectByRoleId(roleEntity.getId());
                for (RoleMenuEntity roleMenuEntity : roleMenuList) {
                    if (roleMenuEntity.getMenuId().equals(menuEntity.getId())) {
                        // 修改角色对应的菜单
                        menuEntity.setRoleId(roleMenuEntity.getRoleId());
                        menuMapper.updateRoleMenu(menuEntity);
                    }
                }
            }


        } catch (Exception e) {
            throw new EscstException("更新菜单异常" + e.getMessage(), e);
        }
    }

    /**
     * 删除菜单
     *
     * @param entity
     */
    public void delMenu(MenuEntity entity) {
        List<String> ids = new ArrayList<String>();
        try {
            ids.add(entity.getId());
            List<MenuEntity> list = menuMapper.selectByParentId(entity.getId(), PLAT);
            if (!CollectionUtils.isEmpty(list)) {
                for (MenuEntity menuEntity : list) {
                    ids.add(menuEntity.getId());
                }
            }
            logger.info("menuIds size is {} ,content is {}", ids.size(), ids);
            menuMapper.batchRemove(ids);
            orgMenuMapper.removeByIdAndOrgId(entity);
        } catch (Exception e) {
            throw new EscstException("删除菜单异常" + e.getMessage(), e);
        }
    }

    /**
     * 查询出角色菜单关系
     *
     * @param roleId
     * @return
     */
    public List<TreeEntity> listMenuByRoleId(String roleId) {
        List<TreeEntity> list = null;
        try {
            list = menuMapper.listMenuByRoleId(roleId);
        } catch (Exception e) {
            throw new EscstException("根据角色ID查询菜单信息异常", e);
        }
        return list;
    }

    /**
     * @param orgId 机构id
     * @return java.util.List<com.escst.orgMenu.entity.OrgMenuEntity>
     * @desc 根据机构id 查询机构菜单关系集合
     * @author jincheng
     * @date 2018/1/23 9:42
     */
    public List<TreeEntity> listMenuByOrgId(String orgId) {
        List<TreeEntity> list = null;
        try {
            list = menuMapper.listMenuByOrgId(orgId);
        } catch (Exception e) {
            throw new EscstException("根据机构ID查询菜单信息异常", e);
        }
        return list;
    }

    /**
     * @param roleId
     * @return
     * @desc 查询角色所有的已选中的菜单id集合
     * @author niejing
     * @date 2017年11月9日 下午6:35:32
     */
    public List<String> queryMenuIdByRoleId(String roleId) {
        List<String> list = null;
        try {
            list = menuMapper.selectMenuIdByRoleId(roleId);
        } catch (Exception e) {
            throw new EscstException("根据角色ID查询菜单信息异常", e);
        }
        return list;
    }

    /**
     * @param orgId 机构id
     * @return java.util.List<java.lang.String>
     * @desc 查询机构已选中的菜单id集合
     * @author jincheng
     * @date 2018/1/17 14:31
     */
    private List<String> queryMenuIdByorgId(String orgId) {
        List<String> list = null;
        try {
            list = menuMapper.selectMenuIdByOrgId(orgId);
        } catch (Exception e) {
            throw new EscstException("根据机构查询菜单信息异常", e);
        }
        return list;
    }

    /**
     * @param menuVO
     * @return
     * @desc 查询菜单
     * @author zhouwei
     * @date 2017年7月27日 下午3:03:48
     */
    public List<MenuEntity> listMenu(MenuVO menuVO) {
        return menuMapper.listMenu(menuVO);
    }

    /**
     * @param menuVO
     * @return
     * @desc 查询有权限访问的菜单
     * @author caox
     * @date 2017年9月06日 下午2:03:48
     */
    public List<MenuEntity> listAuthAllMenu(MenuVO menuVO) {
        List<MenuEntity> list = new ArrayList<MenuEntity>();
        try {
            List<MenuEntity> menuList = menuMapper.selectAuthAllMenu(menuVO);
            // 找到所有一级菜单
            for (MenuEntity menuEntity : menuList) {
                // 一级菜单parent_id为1
                if (MenuEntity.PLATFORM_MENU_PARENT_ID.equals(menuEntity.getParentId())) {
                    list.add(menuEntity);
                }
            }
            // 为一级菜单设置子菜单
            if (!CollectionUtils.isEmpty(list)) {
                for (MenuEntity entity : list) {
                    entity.setChildMenus(getChild(entity.getId(), menuList));
                }
            }
        } catch (EscstException e) {
            throw new EscstException("查询有权限的菜单数据出现异常：" + e.getMessage());
        }
        return list;
    }

    /**
     * 递归查询子菜单数据
     *
     * @param id
     * @param rootMenuList
     * @return
     */
    private List<MenuEntity> getChild(String id, List<MenuEntity> rootMenuList) {
        List<MenuEntity> childList = new ArrayList<MenuEntity>();
        for (MenuEntity menuEntity : rootMenuList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menuEntity.getParentId())) {
                if (menuEntity.getParentId().equals(id)) {
                    childList.add(menuEntity);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuEntity menu : childList) {
            // 没有url子菜单还有子菜单
            if (StringUtils.isBlank(menu.getUrl())) {
                // 递归
                menu.setChildMenus(getChild(menu.getId(), rootMenuList));
            }
        }

        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }

        return childList;
    }

    /**
     * @return
     * @desc 获取最新的排序号
     * @author niejing
     * @date 2017年6月28日 上午10:38:48
     */
    private Integer getLatestSortNum(String parentId) {
        Integer sortNum = 0;
        try {
            String maxNum = menuMapper.selectMaxSortNum(parentId);
            if (StringUtils.isBlank(maxNum)) {
                sortNum = 1;
            } else {
                int tmpnum = Integer.parseInt(maxNum);
                sortNum = tmpnum + 1;
            }
        } catch (Exception e) {
            logger.error("获取最新的排序号失败", e);
        }
        return sortNum;
    }


    /**
     * @param orgId 机构Id
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询机构被选中的菜单树集合(机构编辑角色时调用)
     * @author jincheng
     * @date 2018/1/17 14:29
     */
    public List<TreeEntity> findOrgMenuList(String orgId, String roleId) {
        List<String> checkedList = new ArrayList<String>();
        List<MenuEntity> operationList = new ArrayList<MenuEntity>();
        if (StringUtils.isNotBlank(orgId)) {
            // 查询角色对应的菜单id集合
            checkedList = this.queryMenuIdByRoleId(roleId);
            // 查询机构对应的权限
            operationList = this.selectOperationByOrgId(orgId);
        }

        List<TreeEntity> list = new ArrayList<TreeEntity>();
        List<MenuEntity> menuList = this.queryMenuByOrgId(orgId);

        for (MenuEntity menuEntity : menuList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(menuEntity.getParentId());
            treeEntity.setId(menuEntity.getId());
            treeEntity.setName(menuEntity.getName());
            treeEntity.setOperationAuthority(menuEntity.getOperationAuthority());
            treeEntity.setSortNum(menuEntity.getSortNum() == null ? 0 : menuEntity.getSortNum());
            if (StringUtils.equals(menuEntity.getParentId(), PLAT_ONE_LEVEL_MENU)
                    || StringUtils.equals(menuEntity.getParentId(), APP_ONE_LEVEL_MENU)) {
                treeEntity.setOpen(true);
            } else {
                treeEntity.setOpen(false);
            }
            if (!CollectionUtils.isEmpty(checkedList)) {
                if (checkedList.contains(menuEntity.getId())) {
                    treeEntity.setChecked(true);
                }
            }
            // 设置机构菜单相应的操作权限
            if (!CollectionUtils.isEmpty(operationList)) {
                for (MenuEntity entity : operationList) {
                    if (menuEntity.getId().equals(entity.getId())) {
                        if (!entity.getOperationAuthority().equals(menuEntity.getOperationAuthority())) {
                            treeEntity.setOperationAuthority(entity.getOperationAuthority());
                        }
                    }
                }
            }
            list.add(treeEntity);
        }
        return list;
    }

}
