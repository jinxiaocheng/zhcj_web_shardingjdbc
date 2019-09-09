package com.escst.thirdPart.mapper;

import com.escst.thirdPart.entity.RecordEntity;
import com.escst.thirdPart.entity.RecordPersonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 人员实名制相关
 * @date 2018-7-30 10:55
 */
@Repository
public interface TrainMapper {



    void saveTrainRecord(List<RecordEntity> list);

    int getCount(RecordEntity entity);

    List<RecordEntity> listTrainRecord(RecordEntity entity);

    List<String> listTrainType(String departCode);

    void saveTrainRecordPerson(List<RecordPersonEntity> list);

    int getPersonCount(RecordPersonEntity entity);

    List<RecordPersonEntity> listTrainRecordPerson(RecordPersonEntity entity);


}
