package com.escst.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.thirdPart.service.ThirdPersonService;

/**
 * @desc 
 * @author niejing
 * @date 2018年8月1日 下午4:36:20
 */
public class ThirdPersonThread extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(ThirdPersonThread.class);

	ThirdPersonService service;

	public ThirdPersonThread() {

	}

	public ThirdPersonThread(ThirdPersonService service) {
		this.service = service;
	}

	public void run() {
		logger.info("ThirdPersonThread thread name{}",Thread.currentThread().getName());
		try {
			service.getPersons();
		} catch (Exception e) {
			logger.error("更新人员信息数据异常", e);
		}
	}
}
