package com.escst.construction.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.QtyVO;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.entity.ConstructionEntity;
import com.escst.construction.entity.ConstructionLicenseEntity;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.ConstructionScheduleVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.territory.service.TerritoryService;
import com.escst.territory.vo.AreaConstructionVO;

/**
 * @author zhouwei
 * @desc 工地
 * @date 2017年4月27日 下午12:04:25
 */
@Controller
@RequestMapping("construction")
public class ConstructionController {
    private static final Logger logger = LoggerFactory.getLogger(ConstructionController.class);

    @Autowired
    private ConstructionService service;

    @Autowired
    private TerritoryService territoryService;

    /**
     * @return
     * @desc 工程信息
     * @author zhouwei
     * @date 2017年4月27日 下午12:06:18
     */
    @RequestMapping("detail")
    public ModelAndView detail(Model model) {
        model.addAttribute("constructionId", ContextUtils.getCurrentUser().getConstructionId());
        return new ModelAndView("construction/constructionList");
    }

    /**
     * @param entity
     * @return
     * @desc 工程列表
     * @author niejing
     * @date 2017年8月10日 下午1:39:52
     */
    @RequestMapping("constructionList")
    @ResponseBody
    public ReturnJson constructionList(ConstructionEntity entity) {
        ReturnJson returnJson = null;
        try {
            entity.setUserId(ContextUtils.getCurrentUserId());
            PageVo pageVo = service.queryConstructionInfo(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询工程信息异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    @RequestMapping("toConstructionAdd")
    public ModelAndView toInfoAdd(Model model, int type) {
        //查询所有区
        List<AreaConstructionVO> terrList = territoryService.queryAuthAreaByUserId(ContextUtils.getCurrentUserId());
        model.addAttribute("terrList", terrList);
        model.addAttribute("type", type);
        return new ModelAndView("construction/constructionAdd");
    }

    /**
     * @param licenseEntity
     * @return
     * @desc 新增工程
     * @author niejing
     * @date 2017年4月27日 下午3:38:12
     */
    @RequestMapping("constructionAdd")
    @ResponseBody
    public ReturnJson add(ConstructionLicenseEntity licenseEntity) {
        ReturnJson returnJson = null;
        try {
            this.service.addConstruction(licenseEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增工程信息异常", e);
            returnJson = ReturnJson.fail("新增工程信息异常：" + e.getMessage());
        }
        return returnJson;
    }

    @RequestMapping("toConstructionDetail")
    public ModelAndView toInfoDetail(Model model, String constructionId) {
        ConstructionLicenseEntity entity = service.queryConstructionLicenseById(constructionId);
        model.addAttribute("entity", entity);
        return new ModelAndView("construction/constructionDetail");
    }

    @RequestMapping("toConstructionById")
    @ResponseBody
    public ReturnJson toConstructionById(String constructionId) {
        ReturnJson returnJson = null;
        try {
            ConstructionLicenseEntity entity = service.queryConstructionLicenseById(constructionId);
            returnJson = ReturnJson.success(entity);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取工地信息异常：");
            logger.error("获取工地信息异常：" + e.getMessage(), e);
        }
        return returnJson;

    }


    /**
     * @return
     * @desc 检查部位
     * @author zhouwei
     * @date 2017年4月27日 下午12:06:18
     */
    @RequestMapping("projectStructure")
    public ModelAndView projectStructure() {
        return new ModelAndView("construction/projectStructure");
    }

    /**
     * @return
     * @desc 查询工地树
     * @author niejing
     * @date 2017年4月26日 上午11:32:53
     */
    @RequestMapping("fetchConstructionNodeList")
    @ResponseBody
    public ReturnJson fetchConstructionNodeList() {
        ReturnJson returnJson = null;
        try {
            List<TreeEntity> list = service.queryConstructionTree();
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取树节点信息异常：");
            logger.error("获取树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 获取用户有权限看到的工地树形结构
     * @author zhouwei
     * @date 2017年8月15日 下午2:24:32
     */
    @RequestMapping("/queryAuthConstructionTree")
    @ResponseBody
    public ReturnJson queryAuthConstructionTree() {
        ReturnJson returnJson = null;
        try {
            String userId = ContextUtils.getCurrentUserId();
            List<TreeEntity> list = service.queryAuthConstructionTree(userId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取市区树节点信息异常：");
            logger.error("获取市区树节点信息异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 查询有权限查看的工地在各区的数量
     * @author zhouwei
     * @date 2017年8月25日 下午2:16:45
     */
    @RequestMapping("/queryAuthAreaConstructionQty")
    @ResponseBody
    public ReturnJson queryAuthAreaConstructionQty() {
        ReturnJson returnJson = null;
        try {
            String userId = ContextUtils.getCurrentUserId();
            List<QtyVO> list = service.queryAuthAreaConstructionQty(userId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询有权限查看的工地在各区的数量异常：");
            logger.error("查询有权限查看的工地在各区的数量异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    @RequestMapping("getConstructionSchedule")
    @ResponseBody
    public ReturnJson getConstructionSchedule() {
        ReturnJson returnJson = null;
        try {
            String userId = ContextUtils.getCurrentUserId();
            List<ConstructionScheduleVo> list = service.getConstructionSchedule(userId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取用户下的项目进度异常:");
            logger.error("获取用户下的项目进度异常：" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @param constructionId
     * @return
     * @desc 通过工地ID查询工程信息和工程进度信息
     * @author caozx
     * @date 2018/2/24 17:01
     */
    @RequestMapping("queryScheduleByConstructionId")
    @ResponseBody
    public ReturnJson queryScheduleByConstructionId(String constructionId) {
        ReturnJson returnJson = null;
        try {
            SimpleConstructionVO vo = service.queryScheduleByConstructionId(constructionId);
            returnJson = ReturnJson.success(vo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取项目进度异常:");
            logger.error("获取项目进度异常：" + e.getMessage(), e);
        }
        return returnJson;
    }
    
    /**
     * 
     * @desc 获取当前用户所在工地信息 
     * @return 
     * @author niejing
     * @date 2018年2月26日 下午1:20:12
     */
    @RequestMapping("queryConstructionInfoByUserId")
    @ResponseBody
    public ReturnJson queryConstructionInfoByUserId(){
    	ReturnJson returnJson = null;
        try {
        	String userId = ContextUtils.getCurrentUserId();
        	Map<String,Object> map = service.queryConstructionInfoByUserId(userId);
        	returnJson = ReturnJson.success(map);
        }catch(Exception e){
            returnJson = ReturnJson.fail("获取项目进度异常");
        	logger.error("获取当前用户所在工地信息 ",e);
        }
        return returnJson;
    }
    
}
