package com.escst.organization.controller;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.menu.entity.MenuEntity;
import com.escst.menu.service.MenuService;
import com.escst.orgMenu.entity.OrgMenuEntity;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.role.entity.RoleAndMenuBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("org")
public class OrgController {
    private static Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    public OrgService orgService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    public ModelAndView orgListView(Model model) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        return new ModelAndView("org/orgList");
    }

    /**
     * @return
     * @desc 获取机构数
     * @author niejing
     * @date 2017年4月27日 下午3:36:33
     */
    @RequestMapping("fetchOrgNodeList")
    @ResponseBody
    public ReturnJson fetchOrgNodeList(String userId) {
        ReturnJson returnJson = new ReturnJson();
        try {
            List<TreeEntity> list = orgService.queryTreeListByUserId(userId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取树节点信息异常：");
            logger.error("获取树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    @RequestMapping("fetchStartPreviewParam")
    public ModelAndView fetchStartPreviewParam(Model model, String nodeId) {
        OrgEntity org = orgService.queryById(nodeId);
        if (StringUtils.isNotBlank(org.getConstructionId())) {
            model.addAttribute("constructionId", org.getConstructionId());
        }
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("org/orgTreeList");
    }

    /**
     * @return
     * @desc 通过结点查询对应的下属机构
     * @author niejing
     * @date 2017年4月27日 下午3:36:55
     */
    @RequestMapping("queryByNodeId")
    @ResponseBody
    public ReturnJson queryByNodeId(OrgEntity orgEntity) {
        logger.info("queryByNodeId start id is{}" + orgEntity.getParentId());
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

    @RequestMapping("queryById")
    @ResponseBody
    public ReturnJson queryById(String id) {
        logger.info("queryById start id is{}" + id);
        ReturnJson returnJson = null;
        try {
            OrgEntity orgEntity = orgService.queryById(id);
            returnJson = ReturnJson.success(orgEntity);
        } catch (Exception e) {
            logger.error("查询机构子菜单出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询机构子菜单出现异常：" + e.getMessage());
        }
        logger.info("queryById end");
        return returnJson;
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(Model model, String nodeId) {
        OrgEntity org = orgService.queryById(nodeId);
        model.addAttribute("org", org);
        return new ModelAndView("org/addOrg");
    }

    /**
     * @return
     * @desc 新增机构, 机构对应的菜单
     * @author niejing
     * @date 2017年4月27日 下午3:38:12
     */
    @RequestMapping("addOrg")
    @ResponseBody
    public ReturnJson addOrg(RoleAndMenuBean bean) {
        ReturnJson returnJson = null;
        try {
            orgService.addOrgMenu(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增组织机构信息异常", e);
            returnJson = ReturnJson.fail("新增组织机构信息异常：" + e.getMessage());
        }
        return returnJson;
    }

    @RequestMapping("toAssign")
    public ModelAndView toAssign(Model model, String nodeId) {
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("org/assignConst");
    }

    /**
     * @return
     * @desc 分配
     * @author niejing
     * @date 2017年4月27日 下午2:12:57
     */
    @RequestMapping("assign")
    @ResponseBody
    public ReturnJson assign(String constructionSel, String constructNames, String parentId) {
        logger.info("constructionSel is{}", constructionSel);
        ReturnJson returnJson = null;
        try {
            orgService.addOrgBatch(constructionSel, constructNames, parentId);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增组织机构信息异常", e);
            returnJson = ReturnJson.fail("新增组织机构信息异常：" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param model
     * @param id
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到机构编辑页面
     * @author jincheng
     * @date 2018-4-24 16:13
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Model model, String id) {
        Map<String, Object> map = orgService.queryMapById(id);
        model.addAttribute("map", map);
        return new ModelAndView("org/editOrg");
    }

    /**
     * @param orgId 机构id
     * @return com.escst.commons.vo.ReturnJson
     * @desc 处理机构编辑时的菜单数据
     * @author jincheng
     * @date 2018/1/17 14:24
     */
    @RequestMapping("fetchMenuNodeList")
    @ResponseBody
    public ReturnJson fetchMenuNodeList(String orgId) {
        ReturnJson returnJson = new ReturnJson();
        try {
            // 查询全部菜单
            List<TreeEntity> allList = menuService.queryMenuList(orgId);
            // 查询拥有菜单
            List<TreeEntity> ownList = menuService.listMenuByOrgId(orgId);
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
     * 编辑机构
     *
     * @param bean
     * @return
     */
    @RequestMapping("editOrg")
    @ResponseBody
    public ReturnJson editOrg(RoleAndMenuBean bean) {
        ReturnJson returnJson = null;
        try {
            orgService.udpateOrgMenu(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("根据id查询机构信息异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * 逻辑删除机构
     *
     * @param id 机构ID
     * @return
     */
    @RequestMapping("delOrg")
    @ResponseBody
    public ReturnJson delOrg(String id) {
        ReturnJson returnJson = null;
        try {
            orgService.delOrg(id);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("删除机构异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


}
