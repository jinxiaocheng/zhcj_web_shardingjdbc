package com.escst.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.thirdPart.service.ToolboxService;

/**
 * @desc 
 * @author niejing
 * @date 2018年8月1日 下午2:59:17
 */
public class ToolboxThread extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(ToolboxThread.class);

	ToolboxService service;

	public ToolboxThread() {
		
	}
	
	public ToolboxThread(ToolboxService service) {
		this.service = service;
	}


	public void run() {
		try{
			logger.info("ToolboxThread thread name{}",Thread.currentThread().getName());
			service.batchInsert();
			service.batchInsertCode();
		}catch(Exception e){
			logger.error("更新单位和编码数据异常",e);
		}
	}
	
}
