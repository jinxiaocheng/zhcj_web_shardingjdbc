package com.escst.safety.mapper;

import com.escst.safety.entity.RiskOperationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 危险作业相关
 * @date 2018-8-14 15:59
 */
@Repository
public interface RiskOperationMapper {

    void add(RiskOperationEntity entity);

    void delete(RiskOperationEntity entity);

    int getCount(RiskOperationEntity entity);

    List<RiskOperationEntity> listData(RiskOperationEntity entity);

    RiskOperationEntity view(RiskOperationEntity entity);

    List<RiskOperationEntity> data(RiskOperationEntity entity);

    List<RiskOperationEntity> dataIsPush(RiskOperationEntity entity);

    void batchUpdate(List<RiskOperationEntity> list);

    void update(RiskOperationEntity entity);


}
