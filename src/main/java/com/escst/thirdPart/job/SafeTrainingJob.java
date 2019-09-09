package com.escst.thirdPart.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.escst.thirdPart.service.SafeTrainingService;

/**
 * @desc 定时任务
 * @author niejing
 * @date 2018年7月30日 下午3:29:32
 */
@Component
public class SafeTrainingJob {

	private static final Logger logger = LoggerFactory.getLogger(SafeTrainingJob.class);
	
	@Autowired
	SafeTrainingService service;
	
	/**
	 * 
	 * @desc 批量更新数据
	 * @author niejing
	 * @date 2018年8月1日 下午2:11:59
	 */
//	@Scheduled(cron = "0 0/1 * * * ?")
//	 @Scheduled(cron = "0 0 */3 * * ?")
	public void batchUpdate() {
		long startTime = System.currentTimeMillis();
		logger.info("==============定时任务start==========");
		try {
			service.batchUpdate();
		} catch (Exception e) {
			logger.error("定时任务（批量更新数据）执行异常",e);
		}
		long diff = System.currentTimeMillis()-startTime;
		logger.info("==============定时任务end==========耗时：{}",diff);
	}
}
