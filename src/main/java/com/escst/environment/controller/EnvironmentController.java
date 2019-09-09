package com.escst.environment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.environment.service.EnvironmentService;
import com.escst.environment.vo.EnvironmentRealtimeVO;
import com.escst.equipment.vo.AcquisitionDataQueryVO;

/**
 * @desc 环境监测
 * @author zhouwei
 * @date 2017年4月27日 下午12:01:31
 */
@Controller
@RequestMapping("environment")
public class EnvironmentController {

	private static Logger logger = LoggerFactory.getLogger(EnvironmentController.class);
	
	@Autowired
	private EnvironmentService environmentService;
	
	/**
	 * @desc 环境监测
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 下午12:02:19
	 */
	@RequestMapping("index")
	public ModelAndView index() {
		return new ModelAndView("environment/index");
	}
	
	/**
     * @desc 进入环境实时数据界面
     * @return 
     * @author zhouwei
     * @date 2017年8月17日 下午1:28:56
     */
    @RequestMapping("/toRealtime")
    public ModelAndView toRealtime(HttpServletRequest request){
		ModelAndView view = new ModelAndView("environment/realtime");
		String currentDate = DateUtils.format(new Date(), DateUtils.TO_DATE);
		view.addObject("equipmentId", request.getParameter("id"));
		view.addObject("startDate", currentDate);
		view.addObject("endDate", currentDate);
        return view;
    }
    
    /**
     * @desc 查询实时监测数据
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年8月17日 下午2:29:29
     */
	@RequestMapping("/queryRealtimeList")
	@ResponseBody
	public ReturnJson queryRealtimeList(AcquisitionDataQueryVO queryVO) {
		ReturnJson returnJson;
		try {
			PageVo pageVo = environmentService.queryRealtimeListPage(queryVO);
			returnJson = ReturnJson.success(pageVo);
		}
		catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询环境实时数据出现异常", e);
		}
		return returnJson;
	}
	
	/**
	 * @desc 导出实时监测数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月27日 上午11:21:16
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public ReturnJson exportExcel(AcquisitionDataQueryVO queryVO) {
    	ReturnJson returnJson = null;
		try {
			queryVO.setRowNum(Integer.MAX_VALUE);//导出就查询所有符合条件的数据,不用做分页
			List<EnvironmentRealtimeVO> list = environmentService.queryRealtimeList(queryVO);
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("list", list);
			String filePath = ExcelUtils.createExcel("environment_realtime.xls", beanParams);
			returnJson = ReturnJson.success(filePath);
		}
		catch (Exception e) {
			logger.error("导出excel异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
    }
}
