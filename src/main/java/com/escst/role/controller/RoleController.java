package com.escst.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escst.commons.tree.TreeEntity;
import com.escst.orgMenu.entity.OrgMenuEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.menu.service.MenuService;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.role.bean.RoleQueryBean;
import com.escst.role.entity.RoleAndMenuBean;
import com.escst.role.service.RoleService;
import com.escst.roleMenu.entity.RoleMenuEntity;
import com.escst.user.entity.UserEntity;
import com.escst.user.service.UserService;

/**
 * 角色控制器
 *
 * @author niejing
 * @desc
 * @date 2017年4月15日 下午3:03:43
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    public RoleService roleService;

    @Autowired
    public OrgService orgService;

    @Autowired
    public UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * @param model
     * @return
     * @desc 跳转到角色列表页面
     * @author niejing
     * @date 2017年4月25日 下午8:16:56
     */
    @RequestMapping("list")
    public ModelAndView list(Model model) {
        UserEntity user = ContextUtils.getCurrentUser();
        model.addAttribute("orgId", user.getOrgId());
        model.addAttribute("userId", user.getId());

        return new ModelAndView("role/roleList");
    }

    /**
     * @param bean
     * @return
     * @desc 角色列表 根据机构id，查询角色
     * @author niejing
     * @date 2017年4月25日 下午8:16:40
     */
    @RequestMapping("roleList")
    @ResponseBody
    public ReturnJson roleList(RoleQueryBean bean) {

        ReturnJson returnJson = null;
        PageVo pageVo = null;
        OrgEntity org = new OrgEntity();
        try {
            if (StringUtils.isNotBlank(bean.getOrgId())) {
                // 查询机构实体
                org = orgService.queryById(bean.getOrgId());
            }
            if (StringUtils.isNotBlank(org.getConstructionId())) {
                // 设置工地Id
                bean.setConstructionId(org.getConstructionId());
            }

            pageVo = roleService.queryRoleByCondition(bean);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询角色列表异常", e);
            returnJson = ReturnJson.fail("查询角色列表异常");
        }
        return returnJson;


    }

    /**
     * @param model
     * @param nodeId
     * @return
     * @desc 跳转到角色列表页面
     * @author niejing
     * @date 2017年4月25日 下午8:17:32
     */
    @RequestMapping("fetchStartPreviewParam")
    public ModelAndView fetchStartPreviewParam(Model model, String nodeId) {
        ModelAndView mav = new ModelAndView("role/roleTreeList");

        OrgEntity org = orgService.queryById(nodeId);
        if (StringUtils.isNotBlank(org.getConstructionId())) {
            model.addAttribute("constructionId", org.getConstructionId());
        }
        model.addAttribute("nodeId", nodeId);
        return mav;
    }

    /**
     * @param orgEntity
     * @return
     * @desc 根据结点ID查询角色列表
     * @author niejing
     * @date 2017年4月25日 下午8:19:38
     */
    @RequestMapping("queryByNodeId")
    @ResponseBody
    public ReturnJson queryByNodeId(OrgEntity orgEntity) {
        logger.info("queryByNodeId start id parentId is{}" + orgEntity.getParentId());
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = orgService.queryByNodeId(orgEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询机构子菜单出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询机构子菜单出现异常：" + e.getMessage());
        }
        logger.info("queryByNodeId end");
        return returnJson;
    }

    /**
     * @param model
     * @param orgId
     * @return
     * @desc 跳转到添加角色页面
     * @author niejing
     * @date 2017年4月25日 下午8:20:50
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(Model model, String orgId) {
        OrgEntity org = this.orgService.queryById(orgId);
        model.addAttribute("org", org);
        return new ModelAndView("role/add");
    }

    /**
     * @param entity
     * @return
     * @desc 添加角色
     * @author niejing
     * @date 2017年4月25日 下午8:20:41
     */
    @RequestMapping("add")
    @ResponseBody
    public ReturnJson addRole(RoleAndMenuBean bean) {
        ReturnJson returnJson = null;
        try {
            roleService.addRole(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增角色信息异常", e);
            returnJson = ReturnJson.fail("新增角色信息异常：" + e);
        }
        return returnJson;
    }

    /**
     * @param model
     * @param roleId
     * @param
     * @return
     * @desc 查看权限详情
     * @author niejing
     * @date 2017年9月7日 下午4:41:27
     */
    @RequestMapping("toLookPage")
    public ModelAndView toLookPage(Model model, String roleId) {
        List<TreeEntity> menuList = menuService.listMenuByRoleId(roleId);
        model.addAttribute("menuList", menuList);
        model.addAttribute("roleId", roleId);

        Map<String, Object> map = roleService.queryById(roleId);
        model.addAttribute("name", map.get("name"));
        model.addAttribute("orgId", map.get("orgId"));
        model.addAttribute("orgName", map.get("orgName"));
        model.addAttribute("type", "view");
        return new ModelAndView("/role/look");
    }

    /**
     * @param model
     * @param roleId
     * @return
     * @desc 跳转到角色编辑页面
     * @author niejing
     * @date 2017年9月7日 下午4:41:27
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Model model, String roleId) {
        model.addAttribute("roleId", roleId);
        Map<String, Object> map = roleService.queryById(roleId);
        model.addAttribute("name", map.get("name"));
        model.addAttribute("orgId", map.get("orgId"));
        model.addAttribute("orgName", map.get("orgName"));
        model.addAttribute("type", "edit");
        return new ModelAndView("/role/look");
    }

    /**
     * @param orgId 机构id
     * @return com.escst.commons.vo.ReturnJson
     * @desc 根据机构id, 查询被选中的菜单集合(机构编辑角色时调用)
     * @author jincheng
     * @date 2018/1/17 14:24
     */
    @RequestMapping("findMenuByOrgId")
    @ResponseBody
    public ReturnJson fingMenuByOrgId(String orgId, String roleId) {
        ReturnJson returnJson = new ReturnJson();
        try {
            // 获得所有菜单
            List<TreeEntity> allList = menuService.findOrgMenuList(orgId, roleId);
            // 获得拥有的菜单
            List<TreeEntity> ownList = menuService.listMenuByRoleId(roleId);
            Map<String, List<TreeEntity>> map = new HashMap<String, List<TreeEntity>>();
            map.put("allList", allList);
            map.put("ownList", ownList);
            returnJson = ReturnJson.success(map);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取树节点信息异常：");
            logger.error("获取树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;

    }


    /**
     * @param bean
     * @return
     * @desc 修改权限
     * @author niejing
     * @date 2017年9月11日 上午8:45:59
     */
    @RequestMapping("edit")
    @ResponseBody
    public ReturnJson edit(RoleAndMenuBean bean) {
        ReturnJson returnJson = null;
        try {
            roleService.editRole(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("修改角色信息异常", e);
            returnJson = ReturnJson.fail("修改角色信息异常：" + e);
        }
        return returnJson;
    }
}
