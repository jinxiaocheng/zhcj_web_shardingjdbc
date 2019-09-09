package com.escst.towerCrane.mapper;

import com.escst.towerCrane.vo.TowercraneEntityDemo;

public interface TowercraneEntityDemoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TowercraneEntityDemo record);

    int insertSelective(TowercraneEntityDemo record);

    TowercraneEntityDemo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TowercraneEntityDemo record);

    int updateByPrimaryKey(TowercraneEntityDemo record);
}