package com.escst.positionWork.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.positionWork.entity.PositionWorkEntity;
import com.escst.positionWork.service.PositionWorkService;
import com.escst.positionWork.vo.PositionWorkVo;

/**
 * @desc 岗位工种控制器
 * @author niejing
 * @date 2018年4月26日 下午1:23:05
 */
@Controller
@RequestMapping("positionWork")
public class PositionWorkController {

	private static Logger logger = LoggerFactory.getLogger(PositionWorkController.class);

	@Autowired
	private PositionWorkService positionWorkService;

	/**
	 * 
	 * @desc 跳转到岗位工种页面
	 * @return
	 * @author niejing
	 * @date 2018年4月26日 下午4:03:59
	 */
	@RequestMapping("toList")
	public ModelAndView toList() {
		ModelAndView mav = new ModelAndView("positionWork/list");
		return mav;
	}

	/**
	 * 
	 * @desc 岗位工种列表
	 * @param entity
	 * @return
	 * @author niejing
	 * @date 2018年4月26日 下午4:03:41
	 */
	@RequestMapping("list")
	@ResponseBody
	public ReturnJson list(PositionWorkEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			PageVo pageVo = positionWorkService.list(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("岗位工种列表异常", e);
			returnJson = ReturnJson.fail("岗位工种列表异常");
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 跳转到岗位工种新增页面
	 * @return
	 * @author niejing
	 * @date 2018年4月26日 下午4:04:12
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd() {
		ModelAndView mav = new ModelAndView("positionWork/add");
		return mav;
	}

	@RequestMapping("add")
	@ResponseBody
	public ReturnJson add(PositionWorkEntity entity) {
		ReturnJson returnJson = null;
		try {
			positionWorkService.add(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("新增岗位工种异常", e);
			if (e instanceof EscstException) {
				returnJson = ReturnJson.fail(e.getMessage());
			} else {
				returnJson = ReturnJson.fail("系统异常");
			}
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 跳转到岗位工种编辑页面
	 * @return
	 * @author niejing
	 * @date 2018年4月26日 下午4:04:26
	 */
	@RequestMapping("toEdit")
	public ModelAndView toEdit() {
		ModelAndView mav = new ModelAndView("positionWork/edit");
		return mav;
	}

	@RequestMapping("edit")
	@ResponseBody
	public ReturnJson edit(PositionWorkEntity entity) {
		ReturnJson returnJson = null;
		try {
			positionWorkService.edit(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("修改岗位工种异常", e);
			if (e instanceof EscstException) {
				returnJson = ReturnJson.fail(e.getMessage());
			} else {
				returnJson = ReturnJson.fail("系统异常");
			}
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 根据工地id查询岗位工种
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年4月28日 下午2:56:05
	 */
	@RequestMapping("queryByConstructionId")
	@ResponseBody
	public ReturnJson queryByConstructionId(PositionWorkVo vo) {
		ReturnJson returnJson = null;
		try {
			List<PositionWorkEntity> list = positionWorkService.queryByConstructionId(vo);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("根据工地id查询岗位工种异常", e);
			returnJson = ReturnJson.fail("根据工地id查询岗位工种异常");
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 初始化岗位工种数据
	 * @return
	 * @author niejing
	 * @date 2018年9月10日 上午10:55:44
	 */
	@RequestMapping("initData")
	@ResponseBody
	public ReturnJson initData(String constructionId) {
		ReturnJson returnJson = null;
		try{
			positionWorkService.initData(constructionId);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("初始化岗位工种数据异常：",e);
			returnJson = ReturnJson.fail("初始化数据异常");
		}
		return returnJson;
	}
}
