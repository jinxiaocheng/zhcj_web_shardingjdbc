package com.escst.thirdPart.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.thread.ThirdPersonThread;
import com.escst.thread.ToolboxThread;
import com.escst.thread.TrainThread;

/**
 * @desc 
 * @author niejing
 * @date 2018年8月1日 下午4:44:08
 */
@Service
public class SafeTrainingService {

	private static final Logger logger = LoggerFactory.getLogger(SafeTrainingService.class);
	@Autowired
	ToolboxService toolboxService;
	
	@Autowired
	TrainService  trainService;
	
	@Autowired
	ThirdPersonService thirdPersonService;
	
	ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
	
	/**
	 * 
	 * @desc 批量更新数据
	 * @author niejing
	 * @date 2018年8月1日 下午2:11:59
	 */
	public void batchUpdate() {
		logger.info("==============批量更新数据开始==========");
		try {
			ToolboxThread thread = new ToolboxThread(toolboxService);
			fixedThreadPool.execute(thread);
			
			TrainThread trainThread = new TrainThread(trainService);
			fixedThreadPool.execute(trainThread);
			
			ThirdPersonThread thirdPersonThread = new ThirdPersonThread(thirdPersonService);
			fixedThreadPool.execute(thirdPersonThread);
		} catch (Exception e) {
			logger.error("批量更新数据异常",e);
		}
		logger.info("==============批量更新数据结束==========");
	}
}
