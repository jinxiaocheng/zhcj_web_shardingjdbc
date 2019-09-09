package com.escst.commons.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.escst.hook.service.HookService;

/**
 * @desc 吊钩监测定时任务
 * @author zhouwei
 * @date 2017年9月20日 下午4:23:15
 */
@Component
public class HookTask {

	@Autowired
	private HookService hookService;
	
	/**
	 * @desc 每5分钟执行一次
	 * @author zhouwei
	 * @date 2017年9月20日 下午4:59:03
	 */
//	@Scheduled(cron = "0 0/5 * * * ?")
	public void copyRealtime() {
		//TODO zhouwei 贵阳工地的1号塔吊的数据要插入到1号吊钩的实时监测中
		String hookEquipmentId = "c2fc1df4987711e7b6b7002590f074f8";
		String towercraneEquipmentId = "c2fc1df4987711e7b6b7002590f07a4";
		hookService.copyRealtime(hookEquipmentId, towercraneEquipmentId);
	}
}
