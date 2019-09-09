package com.escst.thread;

import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.service.InspectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dwj
 * @desc
 * @date 14:41 2018/2/23
 */
public class WorkTrendThread extends  Thread {


    private static final Logger logger = LoggerFactory.getLogger(WorkTrendThread.class);

    private InspectionEntity inspectionEntity;

    private InspectionService inspectionService;

    public WorkTrendThread() {}

    public WorkTrendThread(InspectionService inspectionService,InspectionEntity inspectionEntity) {
        this.inspectionEntity = inspectionEntity;
        this.inspectionService = inspectionService;
    }

    public void run() {
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[start]-----" );
        if (inspectionEntity != null) {
            inspectionService.saveWorkState(inspectionEntity);
        }
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[end]-----" );
    }
}
