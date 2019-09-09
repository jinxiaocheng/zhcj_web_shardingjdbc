package com.escst.thirdPart;

import com.escst.commons.vo.PageVo;
import com.escst.thirdPart.entity.RecordEntity;
import com.escst.thirdPart.service.TrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jincheng
 * @desc
 * @date 2018-7-31 17:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class TrainTest {

    @Autowired
    private TrainService trainService;

    @Test
    public void saveTrainRecord() {
        trainService.saveTrainRecord();
    }


    @Test
    public void listTrainType() {
        List<String> list = trainService.listTrainType("-1248306914");
    }


    @Test
    public void listTrainRecord() {
        RecordEntity recordEntity = new RecordEntity();
//        recordEntity.setDepartCode("-1248306914");
        recordEntity.setPage(1);
        recordEntity.setRowNum(10);
//        recordEntity.setTrainType("安全技术交底");
        PageVo pageVo = trainService.listTrainRecord(recordEntity);
    }

    @Test
    public void saveTrainRecordPerson() {
        trainService.saveTrainRecordPerson();
    }


}
