package com.escst.menu.controller;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.menu.entity.MenuEntity;
import com.escst.menu.service.MenuService;
import com.escst.menu.vo.MenuVO;
import com.escst.orgMenu.entity.OrgMenuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 10:14
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    public ModelAndView menuListView(Model model) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        return new ModelAndView("menu/menuList");
    }

    /**
     * @return
     * @desc 获取机构拥有的菜单树
     * @author niejing
     * @date 2017年4月25日 下午8:21:28
     */
    @RequestMapping("fetchMenuNodeList")
    @ResponseBody
    public ReturnJson fetchMenuNodeList(MenuEntity entity) {
        ReturnJson returnJson = new ReturnJson();
        try {
            List<TreeEntity> list = menuService.queryMenuTree(entity);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取树节点信息异常：");
            logger.error("获取树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 获取所有菜单树（包括平台菜单和APP菜单）
     * @author niejing
     * @date 2017年4月25日 下午8:22:03
     */
    @RequestMapping("fetchAllMenuNodeList")
    @ResponseBody
    public ReturnJson fetchAllMenuNodeList(MenuEntity entity) {
        ReturnJson returnJson = new ReturnJson();
        try {
            List<TreeEntity> list = menuService.queryAllMenuList(entity);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取树节点信息异常：");
            logger.error("获取树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;
    }


    @RequestMapping("fetchStartPreviewParam")
    public ModelAndView fetchStartPreviewParam(Model model, String nodeId) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("menu/menuTreeList");
    }

    /**
     * @param menuEntity
     * @return
     * @desc 根据结点ID查询菜单列表
     * @author niejing
     * @date 2017年4月25日 下午8:22:33
     */
    @RequestMapping("queryByNodeId")
    @ResponseBody
    public ReturnJson queryByNodeId(MenuEntity menuEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = menuService.queryByNodeId(menuEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询子菜单出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询子菜单出现异常：" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * 根据父ID查询子菜单
     *
     * @param parentId
     * @return
     * @desc
     * @author niejing
     * @date 2017年4月25日 下午8:23:12
     */
    @RequestMapping("queryByParentId")
    @ResponseBody
    public ReturnJson queryByParentId(String parentId) {
        ReturnJson returnJson = null;
        try {
            List<MenuEntity> list = menuService.queryByParentId(parentId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询子菜单出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询子菜单出现异常：" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param menuId
     * @return
     * @desc 根据菜单ID查询菜单信息
     * @author niejing
     * @date 2017年4月25日 下午8:23:32
     */
    @RequestMapping("queryById")
    @ResponseBody
    public ReturnJson queryById(OrgMenuEntity orgMenuEntity) {
        ReturnJson returnJson = null;
        try {
            MenuEntity menuEntity = menuService.queryMenuById(orgMenuEntity);
            returnJson = ReturnJson.success(menuEntity);
        } catch (Exception e) {
            logger.error("根据id查询菜单异常", e);
            returnJson = ReturnJson.fail("根据id查询菜单异常");
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 查询当前用户的一级菜单
     * @author zhouwei
     * @date 2017年4月22日 下午8:11:35
     */
    @RequestMapping("queryMenu")
    @ResponseBody
    public ReturnJson queryMenu() {
        ReturnJson returnJson = null;
        try {
            MenuVO menuVO = new MenuVO();
            menuVO.setUserId(ContextUtils.getCurrentUserId());
            menuVO.setFlag(1);//只获取平台的菜单
            menuVO.setParentId("1");
            List<MenuEntity> list = menuService.listMenu(menuVO);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询当前用户的一级菜单异常", e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 查询当前用户的所有有权限的的菜单
     * @author caozx
     * @date 2017年9月06日 下午2:11:35
     */
    @RequestMapping("queryAuthAllMenu")
    @ResponseBody
    public ReturnJson queryAuthAllMenu() {
        ReturnJson returnJson = null;
        try {
            MenuVO menuVO = new MenuVO();
            menuVO.setUserId(ContextUtils.getCurrentUserId());
            menuVO.setFlag(1);
            List<MenuEntity> list = menuService.listAuthAllMenu(menuVO);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询当前用户的所有有权限的的菜单异常", e);
            returnJson = ReturnJson.fail("查询菜单异常");
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 查询二级菜单
     * @author niejing
     * @date 2017年4月25日 下午8:23:51
     */
    @RequestMapping("queryLevelTwo")
    @ResponseBody
    public ReturnJson queryLevelTwo() {
        logger.info("queryLevelTwo start menuId is {}");
        ReturnJson returnJson = null;
        try {
            List<MenuEntity> list = menuService.queryLevelTwo();
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("根据id查询菜单异常", e);
        }
        logger.info("queryLevelTwo end");
        return returnJson;
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(Model model, String nodeId) {
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("menu/addMenu");
    }

    /**
     * @param menuEntity
     * @return
     * @desc 添加菜单
     * @author niejing
     * @date 2017年4月25日 下午8:24:09
     */
    @RequestMapping("addMenu")
    @ResponseBody
    public ReturnJson addMenu(MenuEntity menuEntity) {
        ReturnJson returnJson = null;
        try {
            menuService.addMenu(menuEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增菜单异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    @RequestMapping("toEdit")
    public ModelAndView toEdit(Model model, OrgMenuEntity orgMenuEntity) {
        MenuEntity menuEntity = menuService.queryMenuById(orgMenuEntity);
        model.addAttribute("menuEntity", menuEntity);
        return new ModelAndView("menu/editMenu");
    }

    /**
     * @param menuEntity
     * @return
     * @desc 编辑菜单
     * @author niejing
     * @date 2017年4月25日 下午8:24:24
     */
    @RequestMapping("editMenu")
    @ResponseBody
    public ReturnJson editMenu(MenuEntity menuEntity) {
        ReturnJson returnJson = null;
        try {
            menuService.updateMenu(menuEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("更新菜单异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param id
     * @return
     * @desc 删除菜单
     * @author niejing
     * @date 2017年4月25日 下午8:24:38
     */
    @RequestMapping("delMenu")
    @ResponseBody
    public ReturnJson delMenu(MenuEntity entity) {
        ReturnJson returnJson = null;
        try {
            menuService.delMenu(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("根据id查询菜单异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


}
