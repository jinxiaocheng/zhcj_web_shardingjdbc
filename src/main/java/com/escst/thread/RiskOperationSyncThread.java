package com.escst.thread;

import com.escst.safety.entity.RiskOperationEntity;
import com.escst.safety.service.RiskOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jincheng
 * @desc 同步危险作业记录线程
 * @date 2018-8-16 10:13
 */
public class RiskOperationSyncThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RiskOperationSyncThread.class);

    private RiskOperationEntity riskOperationEntity;
    private RiskOperationService riskOperationService;

    public RiskOperationSyncThread(RiskOperationEntity riskOperationEntity, RiskOperationService riskOperationService) {
        this.riskOperationEntity = riskOperationEntity;
        this.riskOperationService = riskOperationService;
    }

    @Override
    public void run() {
        logger.info("TrainThread thread name{}", Thread.currentThread().getName());
        try {
            riskOperationService.syncRiskOperation(riskOperationEntity);
        } catch (Exception e) {
            logger.error("同步危险作业记录异常", e);
        }
    }
}
