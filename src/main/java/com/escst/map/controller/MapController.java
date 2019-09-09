package com.escst.map.controller;

import javax.servlet.http.HttpServletRequest;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.user.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年10月20日 上午9:14:40
 */
@Controller
@RequestMapping("/map")
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);

    @Autowired
    private ConstructionService constructionService;

	@RequestMapping("/index")
    public ModelAndView toIndex(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = null;
	    String level = request.getParameter("level");
	    if ("3".equals(level)) {
            modelAndView = new ModelAndView("map/index");
        } else {
            modelAndView = new ModelAndView("map/indexAll");
        }
        return modelAndView;
    }


    @RequestMapping("/bim")
    public ModelAndView toBim(HttpServletRequest request,Model model) {
        return new ModelAndView("map/bim");
    }

    /**
     * @desc  点击地图上工地图标跳转到工地首页
     * @param constructionId
     * @return
     * @author caozx
     * @date 2018/2/24 16:27
     */
    @RequestMapping("/toIndexMin")
    public ModelAndView toConstruction(String constructionId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("constructionId",constructionId);
        modelAndView.setViewName("map/indexMin");
        return modelAndView;
    }

    /**
     * @return
     * @desc   地图上查询有权限的工地信息
     * @author caozx
     * @date 2017/10/20 14:57
     */
    @RequestMapping("/query")
    @ResponseBody
    public ReturnJson query() {
        ReturnJson returnJson = null;
        try {
            UserEntity userEntity = ContextUtils.getCurrentUser();
            List<SimpleConstructionVO> list = userEntity.getConstructionList();
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }


    /**
     * @desc
     * @param constructionId
     * @return
     * @author caozx
     * @date 2018/3/8 10:05
     */
    @RequestMapping("/foorplan")
    @ResponseBody
    public ReturnJson foorplan(String constructionId) {
        ReturnJson returnJson = null;
        try {
            String path = constructionService.queryFloorPlanById(constructionId);
            returnJson = ReturnJson.success(path);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

}
