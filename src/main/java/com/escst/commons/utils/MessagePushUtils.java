package com.escst.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.commons.vo.MessageVo;

/**
 * @desc 发送消息工具类
 * @author niejing
 * @date 2017年11月28日 下午4:34:38
 */
public class MessagePushUtils {
	private static final Logger logger = LoggerFactory.getLogger(MessagePushUtils.class);

	/**
	 * 
	 * @desc 调用发送消息微服务，保存消息并发送 
	 * @param vo
	 * @return 
	 * @author niejing
	 * @date 2017年11月28日 下午5:35:35
	 */
	public static String sendMessage(MessageVo vo) {
		logger.info("发送消息start userList is {}", vo.getUsers());
		String response = "";
		try {
			String url = ResourceUtil.getPushSaveUrl();
			response = HttpClientUtils.httpPostWithJSON(url, vo);
			logger.info("sendMessage response is {}", response);
		} catch (Exception e) {
			logger.error("发送消息异常：", e);
		}
		logger.info("发送消息end");
		return response;
	}
}
