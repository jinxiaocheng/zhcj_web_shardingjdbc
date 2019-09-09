package com.escst.territory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.ReturnJson;
import com.escst.territory.service.TerritoryService;
import com.escst.territory.vo.AreaConstructionVO;

/**
 * @author caozx
 * @desc 区域相关接口
 * @date 2017/8/18 15:27
 */
@Controller
@RequestMapping("territory")
public class TerritoryController {

	private static final Logger logger = LoggerFactory.getLogger(TerritoryController.class);

	@Autowired
	private TerritoryService territoryService;

	@RequestMapping("queryAuthAreaByUserId")
	@ResponseBody
	public ReturnJson queryAuthAreaByUserId(@RequestParam(value = "userId") String userId) {
		ReturnJson returnJson = null;
		try {
			// add by zhouwei 2017-10-23 不用传用户ID,使用当前用户的ID
			userId = ContextUtils.getCurrentUserId();
			List<AreaConstructionVO> list = territoryService.queryAuthAreaByUserId(userId);
			returnJson = ReturnJson.success(list);
		} catch (EscstException e) {
			returnJson = ReturnJson.fail("系统异常");
			logger.error("查询有权限的区域和工地信息异常：" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 根据用户ID查询权限树
	 * @param userId
	 * @return
	 * @author niejing
	 * @date 2017年8月28日 下午4:29:08
	 */
	@RequestMapping("queryAreaTreeByUserId")
	@ResponseBody
	public ReturnJson queryAreaTreeByUserId() {
		ReturnJson returnJson = null;
		try {
			String userId = ContextUtils.getCurrentUserId();
			List<TreeEntity> treeList = territoryService.queryAreaTreeByUserId(userId);
			returnJson = ReturnJson.success(treeList);
		} catch (EscstException e) {
			returnJson = ReturnJson.fail("系统异常");
			logger.error("查询有权限的区域和工地信息异常：" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 获取市区树 
	 * @return 
	 * @author niejing
	 * @date 2017年9月21日 上午9:44:35
	 */
	@RequestMapping("queryCityTree")
	@ResponseBody
	public ReturnJson queryCityTree() {
		ReturnJson returnJson = null;
		try {
			List<TreeEntity> list = territoryService.queryByParentId(null, "1");
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("获取城市区域树异常：", e);
			returnJson = ReturnJson.fail("获取城市区域树异常");
		}
		return returnJson;
	}
}
