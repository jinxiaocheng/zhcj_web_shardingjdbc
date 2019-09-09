package com.escst.thread;

import com.escst.person.entity.PersonEntity;
import com.escst.person.service.PersonSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dwj
 * @desc 同步人员信息线程服务
 * @date 15:36 2018/5/24
 */
public class PersonSyncThread extends  Thread{

    private static final Logger logger = LoggerFactory.getLogger(PersonSyncThread.class);

    private PersonEntity personEntity;
    private PersonSyncService personSyncService;

    public PersonSyncThread(){}

    public PersonSyncThread(PersonEntity personEntity,PersonSyncService personSyncService){
        this.personEntity = personEntity;
        this.personSyncService = personSyncService;
    }

    public  void run(){
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[start]-----" );
        if(personEntity != null){
            personSyncService.syncPersonToAccess(personEntity);
        }
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[end]-----" );
    }

}
