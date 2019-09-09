package com.escst.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.thirdPart.service.TrainService;

/**
 * @desc
 * @author niejing
 * @date 2018年8月1日 下午4:34:46
 */
public class TrainThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(TrainThread.class);

	TrainService service;

	public TrainThread() {

	}

	public TrainThread(TrainService service) {
		this.service = service;
	}

	public void run() {
		logger.info("TrainThread thread name{}",Thread.currentThread().getName());
		try {
			service.saveTrainRecord();
			service.saveTrainRecordPerson();
		} catch (Exception e) {
			logger.error("更新安全培训数据异常", e);
		}
	}
}
