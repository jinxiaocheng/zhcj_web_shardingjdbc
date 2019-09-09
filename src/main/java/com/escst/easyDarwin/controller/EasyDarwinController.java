package com.escst.easyDarwin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.escst.commons.vo.ReturnJson;
import com.escst.easyDarwin.entity.ChannelEntity;
import com.escst.easyDarwin.service.ChannelService;

/**
 * 
 * @desc 
 * @author niejing
 * @date 2018年3月7日 下午2:28:27
 */
@RestController
@RequestMapping("easyDarwin")
public class EasyDarwinController {

	private static final Logger logger = LoggerFactory.getLogger(EasyDarwinController.class);

	@Autowired
	public ChannelService channelService;
	
	/**
	 * 
	 * @desc 通道列表
	 * @return 
	 * @author niejing
	 * @date 2018年3月7日 上午9:51:38
	 */
	@RequestMapping("channelList")
	@ResponseBody
	public ReturnJson channelList(String constructionId) {
		ReturnJson returnJson = null;
		try {
			List<ChannelEntity> list = channelService.queryChannelList(constructionId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("获取通道信息异常",e);
			returnJson = ReturnJson.fail("获取通道信息异常");
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 视频预览
	 * @return 
	 * @author niejing
	 * @date 2018年3月7日 上午9:59:06
	 */
	@RequestMapping("getRtmpUrl")
	@ResponseBody
	public ReturnJson getRtmpUrl(String constructionId) {
		ReturnJson returnJson = null;
		try {
			List<String> list = channelService.queryChannelStream(constructionId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("获取视频预览异常");
			returnJson = ReturnJson.fail("获取视频预览异常");
		}
		return returnJson;
	}
}
