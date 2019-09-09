package com.escst.menu.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.commons.tree.TreeEntity;
import com.escst.menu.entity.MenuEntity;
import com.escst.menu.vo.MenuVO;
import com.escst.orgMenu.entity.OrgMenuEntity;
import com.escst.roleMenu.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 10:06
 */
@Repository
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 根据父id查询下面的子菜单
     *
     * @param parentId
     * @return
     */
    List<MenuEntity> selectByParentId(@Param("parentId") String parentId, @Param("flag") String flag);


    /**
     * @return java.util.List<com.escst.menu.entity.MenuEntity>
     * @desc 根据父id查询下面的子菜单
     * @author jincheng
     * @date 2018/5/8 15:09
     */
    List<MenuEntity> selectByPId(MenuEntity entity);

    /**
     * 插入菜单数据
     *
     * @param menuEntity
     */
    void insertMenu(MenuEntity menuEntity);

    String selectMaxSortNum(String parentId);

    List<TreeEntity> listMenuByRoleId(String roleId);

    /**
     * @param menuVO
     * @return
     * @desc 查询菜单
     * @author zhouwei
     * @date 2017年7月27日 下午3:03:48
     */
    List<MenuEntity> listMenu(MenuVO menuVO);

    /**
     * @param menuVO
     * @return
     * @desc 查询所有有权限的菜单
     * @author caozx
     * @date 2017年9月06日 下午2:03:48
     */
    List<MenuEntity> selectAuthAllMenu(MenuVO menuVO);

    /**
     * @return
     * @desc 查询所有的菜单
     * @author zhouwei
     * @date 2017年9月14日 下午6:08:55
     */
    List<MenuEntity> queryAllMenu();

    /**
     * @param id
     * @param flag
     * @return
     * @desc 根据id和平台标识查询菜单列表
     * @author niejing
     * @date 2017年11月6日 上午10:54:55
     */
    List<MenuEntity> selectByFlag(String flag);

    List<String> selectMenuIdByRoleId(String roleId);

    /**
     * @param orgId 机构id
     * @return java.util.List<java.lang.String>
     * @desc 查询机构已选中的菜单id
     * @author jincheng
     * @date 2018/1/17 14:33
     */
    List<String> selectMenuIdByOrgId(String orgId);

    /**
     * @param orgId 机构id
     * @return java.util.List<com.escst.menu.entity.MenuEntity>
     * @desc 查询机构菜单
     * @author jincheng
     * @date 2018/1/18 9:41
     */
    List<MenuEntity> queryMenuByOrgId(String orgId);


    /**
     * @param orgId 机构id
     * @return java.util.List<com.escst.menu.entity.MenuEntity>
     * @desc 根据机构id, 查询机构对应的菜单id，操作权限
     * @author jincheng
     * @date 2018/1/22 17:01
     */
    List<MenuEntity> selectOperationByOrgId(String orgId);

    /**
     * @param orgId 机构id
     * @return java.util.List<com.escst.orgMenu.entity.OrgMenuEntity>
     * @desc 根据机构id 查询机构菜单关系集合
     * @author jincheng
     * @date 2018/1/23 9:42
     */
    List<TreeEntity> listMenuByOrgId(String orgId);

    /**
     * @param entity
     * @return void
     * @desc 修改机构的个性化菜单
     * @author jincheng
     * @date 2018/5/8 16:01
     */
    void updateOrgMenu(MenuEntity entity);


    /**
     * @param entity
     * @return void
     * @desc 修改角色的个性化菜单
     * @author jincheng
     * @date 2018/5/8 16:01
     */
    void updateRoleMenu(MenuEntity entity);

    MenuEntity selectById(MenuEntity entity);

    /**
    * @desc 获取人员菜单
    * @param map
    * @return 
    * @author dwj
    * @date 7/8/2018 15:05
    */
    String selectMenuByUserId(Map<String,Object> map);

    int getFlag(String id);

    List<String> groupByEquipmentId();
}
