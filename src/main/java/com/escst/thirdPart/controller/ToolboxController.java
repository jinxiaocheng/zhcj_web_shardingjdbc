package com.escst.thirdPart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.vo.ReturnJson;
import com.escst.thirdPart.service.SafeTrainingService;
import com.escst.thirdPart.service.ToolboxService;

/**
 * @desc 工具箱控制器
 * @author niejing
 * @date 2018年7月25日 上午11:10:37
 */
@Controller
@RequestMapping("toolboxController")
public class ToolboxController {

	private static final Logger logger = LoggerFactory.getLogger(ToolboxController.class);

	@Autowired
	ToolboxService toolboxService;

	@Autowired
	SafeTrainingService safeTrainingService;
	
	/**
	 * 
	 * @desc 获取工具箱树
	 * @param parentId
	 * @return
	 * @author niejing
	 * @date 2018年7月25日 下午2:36:24
	 */
	@RequestMapping("queryDepartTree")
	@ResponseBody
	public ReturnJson queryDepartTree() {
		ReturnJson returnJson = null;
		try {
			List<TreeEntity> list = toolboxService.queryDepartTree();
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("获取工具箱树异常", e);
			returnJson = ReturnJson.fail("获取工具箱树异常");
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 批量更新安全培训的所有数据
	 * @return 
	 * @author niejing
	 * @date 2018年8月1日 下午4:49:18
	 */
	@RequestMapping("batchUpdate")
	@ResponseBody
	public ReturnJson batchUpdate(){
		ReturnJson returnJson = null;
		try {
			long startTime = System.currentTimeMillis();
			logger.info("==========手动更新start==========");
			
			safeTrainingService.batchUpdate();
			returnJson = ReturnJson.success();
			long diff = System.currentTimeMillis()-startTime;
			logger.info("==========手动更新end==========耗时{}",diff);
		}catch(Exception e){
			logger.error("手动批量更新数据异常", e);
			returnJson = ReturnJson.fail("手动批量更新数据异常");
		}
		return returnJson;
	}
	
}
