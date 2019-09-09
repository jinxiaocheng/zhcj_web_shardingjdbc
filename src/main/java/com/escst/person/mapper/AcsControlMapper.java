package com.escst.person.mapper;

import com.escst.person.entity.AcsControlEntity;
import com.escst.person.entity.AcsControlEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 13:46 2017/12/18
 */
@Repository
public interface AcsControlMapper {

    void save(AcsControlEntity entity);

    void update(AcsControlEntity entity);

    List<AcsControlEntity> queryList(AcsControlEntity entity);

    AcsControlEntity queryById(String id);

    int queryCount(String constructionId);

    /**
     * @param
     * @return
     * @desc 查询没有分配门的控制器
     * @author jincheng
     * @date 2018-5-20 14:44
     */
    List<AcsControlEntity> listControllerNoDoor(String constructionId);


}
