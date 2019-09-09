package com.escst.thread;

import com.escst.excelimport.service.PersonExcelImportSyncService;
import com.escst.excelimport.vo.PersonImportVO;
import com.escst.person.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author dwj
 * @desc 同步excel导入人员信息线程服务
 * @date 13:55 2018/6/4
 */
public class ExcelPersonSyncThread extends  Thread {

    private static final Logger logger = LoggerFactory.getLogger(PersonSyncThread.class);

    private List<PersonEntity> list;

    private String constructionId;

    private PersonExcelImportSyncService personExcelImportSyncService;

    public ExcelPersonSyncThread(){}

    public ExcelPersonSyncThread(List<PersonEntity> list,String constructionId,PersonExcelImportSyncService personExcelImportSyncService){
        this.list = list;
        this.personExcelImportSyncService = personExcelImportSyncService;
        this.constructionId = constructionId;
    }


    public  void run(){
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[start]-----" );
        if(list != null){
            personExcelImportSyncService.syncPersonExcelImportToAccess(list,constructionId);
        }
        logger.info("-------threadName:" + Thread.currentThread().getName() + "------[end]-----" );
    }
}
