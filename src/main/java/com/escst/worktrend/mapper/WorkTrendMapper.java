package com.escst.worktrend.mapper;

import com.escst.worktrend.entity.WorkTrendEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 16:47 2018/2/22
 */
@Repository
public interface WorkTrendMapper {

    void save(WorkTrendEntity entity);

    List<WorkTrendEntity> queryList(Map<String,Object> map);

    int count(Map<String,Object> map);
}
