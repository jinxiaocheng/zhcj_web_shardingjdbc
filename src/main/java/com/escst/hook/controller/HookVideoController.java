package com.escst.hook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.ReturnJson;
import com.escst.hook.service.HookVideoService;

/**
 * @desc
 * @author niejing
 * @date 2018年10月22日 下午2:32:18
 */
@Controller
@RequestMapping("/hookVideo")
public class HookVideoController {

	private static Logger logger = LoggerFactory.getLogger(HookVideoController.class);

	@Autowired
	private HookVideoService hookVideoService;

	/**
	 * 
	 * @desc 获取摄像头
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年10月22日 下午2:47:29
	 */
	@RequestMapping("queryProjectVideo")
	@ResponseBody
	public ReturnJson queryProjectVideo() {
		ReturnJson returnJson = null;
		try {
			List<TreeEntity> list = hookVideoService.queryVideoConstructionTree(ContextUtils.getCurrentUserId());
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("获取摄像头异常", e);
			returnJson = ReturnJson.fail("获取摄像头异常");
		}

		return returnJson;
	}

}
