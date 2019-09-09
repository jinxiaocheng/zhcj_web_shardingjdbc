package com.escst.lifter.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.service.UnloadService;

/**
 * @desc 卸料平台控制器
 * @author niejing
 * @date 2018年4月23日 下午2:22:42
 */
@Controller
@RequestMapping("/unload")
public class UnloadController {
	
	private static Logger logger = LoggerFactory.getLogger(UnloadController.class);
	
	@Autowired
	private UnloadService unloadService;
	
	/**
	 * @desc 进入升降机实时数据列表界面
	 * @param request
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月22日 上午8:54:58
	 */
	@RequestMapping("/toRealtime")
    public ModelAndView toRealtime(HttpServletRequest request){
		ModelAndView view = new ModelAndView("unload/realtime");
		String currentDate = DateUtils.format(new Date(), DateUtils.TO_DATE);
		view.addObject("equipmentId", request.getParameter("id"));
		view.addObject("startDate", currentDate);
		view.addObject("endDate", currentDate);
        return view;
    }
	
	/**
	 * @desc 得到升降机的实时数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月21日 下午6:33:25
	 */
	@RequestMapping("/queryRealtimeList")
	@ResponseBody
	public ReturnJson queryRealtimeList(AcquisitionDataQueryVO queryVO) {
		ReturnJson returnJson;
		try {
			PageVo pageVo = unloadService.queryRealtimeList(queryVO);
			returnJson = ReturnJson.success(pageVo);
		}
		catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询升降机实时数据出现异常", e);
		}
		return returnJson;
	}
}
