package com.escst.material.controller;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialApproachEntity;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.service.MaterialApproachService;
import com.escst.material.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author niejing
 * @desc 材料进场
 * @date 2017年8月21日 下午5:10:08
 */
@Controller
@RequestMapping("materialApproach")
public class MaterialApproachController {
    private static Logger logger = LoggerFactory.getLogger(MaterialApproachController.class);

    @Autowired
    private MaterialApproachService service;

    @Autowired
    private MaterialService materialService;

    @RequestMapping("list")
    public ModelAndView doMaterialApproachList(Model model) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        return new ModelAndView("material/materialList");
    }

    /**
     * 查询材料进场列表
     *
     * @param entity 参数
     * @return 返回值
     */
    @RequestMapping("materialApproachList")
    @ResponseBody
    public ReturnJson queryMaterialApproachList(MaterialBean entity) {
        ReturnJson returnJson;
        try {
            entity.setUserId(ContextUtils.getCurrentUserId());
            PageVo pageVo = service.queryMaterialApproachList(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询材料进场列表出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询材料进场列表出现异常");
        }
        return returnJson;
    }

    /**
     * 进入新增材料进场页面
     *
     * @param model
     * @return
     */
    @RequestMapping("addMaterialApproach")
    public ModelAndView toMaterialApproach(Model model) {
        List<MaterialEntity> list = materialService.list();
        model.addAttribute("materialList", list);
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        return new ModelAndView("material/addMaterialApproach");
    }

    /**
     * 跳转到获取材料名称页面
     *
     * @param model
     * @return
     * @author kongzheng
     * @date 2017年12月27日 上午9:58
     */
    @RequestMapping("toGetMaterialName")
    public ModelAndView toGetMaterialName() {
        return new ModelAndView("material/getMaterialName");
    }

    /**
     * 新增材料
     *
     * @param materialEntity 参数
     * @return 返回值
     */
    @RequestMapping("addMaterialApproachInfo")
    @ResponseBody
    public ReturnJson addMaterialApproachInfo(MaterialApproachEntity entity) {
        ReturnJson returnJson;
        try {
            service.addMaterialApproachInfo(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("更新材料管理信息失败:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * 跳转到材料进场详情页面
     */
    @RequestMapping("toApproachDetailInfo")
    public ModelAndView toApproachDetailInfo() {
        return new ModelAndView("material/approachDetailInfo");
    }


    /**
     * 材料进场详情
     *
     * @param entity 参数
     * @return 返回值
     */
    @RequestMapping("approachDetailInfo")
    @ResponseBody
    public ReturnJson approachMaterialDetailInfo(MaterialApproachEntity entity) {
        ReturnJson returnJson;
        try {
            Map<String, Object> map = service.queryApproachDetailInfo(entity);
            returnJson = ReturnJson.success(map);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("查询材料进场详情失败:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @param
     * @return
     * @desc 新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片
     * @author jincheng
     * @date 2018-6-13 10:39
     */
    @RequestMapping("getWeighbridge")
    @ResponseBody
    public ReturnJson getWeighbridge(MaterialApproachEntity entity) {
        ReturnJson returnJson = null;
        try {
            MaterialApproachEntity weighbridge = service.getWeighbridge(entity);
            returnJson = ReturnJson.success(weighbridge);
        } catch (Exception e) {
            logger.error("新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片异常", e);
            returnJson = ReturnJson.fail("新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片异常");
        }
        return returnJson;
    }


}
