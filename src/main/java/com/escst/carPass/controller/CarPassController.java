package com.escst.carPass.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.carPass.entity.CarPassEntity;
import com.escst.carPass.service.CarPassService;
import com.escst.carPass.vo.CarPassVo;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;

/**
 * @desc 车辆进出管理服务控制器
 * @author niejing
 * @date 2018年8月16日 上午10:03:34
 */
@Controller
@RequestMapping("carPass")
public class CarPassController {

	private static final Logger logger = LoggerFactory.getLogger(CarPassController.class);

	@Autowired
	private CarPassService carPassService;

	
    @RequestMapping("list")
    public ModelAndView menuListView(Model model) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        return new ModelAndView("carPass/list");
    }

	@RequestMapping("view")
	public ModelAndView menuView(Model model) {
		model.addAttribute("userId", ContextUtils.getCurrentUserId());
		return new ModelAndView("carPass/view");
	}
    
	/**
	 * 
	 * @desc 车辆进出列表
	 * @param entity
	 * @return
	 * @author niejing
	 * @date 2018年8月16日 上午10:24:23
	 */
	@RequestMapping("carList")
	@ResponseBody
	public ReturnJson carList(CarPassEntity entity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = carPassService.list(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询车辆进出列表异常", e);
			returnJson = ReturnJson.fail("查询车辆进出列表异常");
		}
		return returnJson;
	}
	
	@RequestMapping("queryDetail")
	@ResponseBody
	public ReturnJson queryDetail(String id) {
		ReturnJson returnJson = null;
		try{
			CarPassVo vo = carPassService.queryDetail(id);
			returnJson = ReturnJson.success(vo);
		} catch (Exception e) {
			logger.error("查询车辆详情异常",e);
		}
		return returnJson;
				
	}
}
