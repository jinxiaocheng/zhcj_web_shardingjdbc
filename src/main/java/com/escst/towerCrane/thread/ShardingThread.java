package com.escst.towerCrane.thread;

import com.escst.commons.utils.DateUtils;
import com.escst.towerCrane.service.TowerCraneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author jincheng
 * @desc 分表线程
 * @date 2018-12-29 9:24
 */
@Component
public class ShardingThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ShardingThread.class);

    @Autowired
    private TowerCraneService towerCraneService;

    private Map<String, Object> map;

    public ShardingThread() {
    }

    public ShardingThread( Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public void run() {
        String tableName = (String) map.get("tableName");
        logger.info("表{}添加数据 -------threadName:{},------[start]-----", tableName, Thread.currentThread().getName());
        Date start = new Date();
        if (!CollectionUtils.isEmpty(map)) {
            towerCraneService.insertShardingData(map);
        }
        logger.info("表{}添加数据 -------threadName:{}, ------[end]-----", tableName, Thread.currentThread().getName());

        Date end = new Date();
        String datePoor = DateUtils.getDatePoor(end, start);
        logger.info("表{}添加数据 -------threadName:{} ------耗时{}", Thread.currentThread().getName(), datePoor);
    }
}
