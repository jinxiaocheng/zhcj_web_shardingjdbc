package com.escst.towerCrane.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.vo.LifterRealtimeVO;
import com.escst.towerCrane.entity.TowerCraneEntity;
import com.escst.towerCrane.vo.QueryConditionVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author niejing
 * @desc
 * @date 2017年4月19日 下午3:51:04
 */
@Repository
public interface TowerCraneMapper extends BaseMapper<TowerCraneEntity> {

    /**
     * @param queryVO
     * @return
     * @desc 得到塔吊的实时数据
     * @author zhouwei
     * @date 2017年8月21日 下午4:21:59
     */
    List<TowercraneRealtimeVO> queryRealtimeList(AcquisitionDataQueryVO queryVO);

    int getRealtimeCount(AcquisitionDataQueryVO queryVO);

    int selectTimeDiffByIds(Map<String, Object> map);

    TowercraneRealtimeVO queryRealtimeData(String equipmentId);

    List<TowercraneRealtimeVO> listHistoryData(TowercraneRealtimeVO vo);

    int getCount(TowercraneRealtimeVO vo);

    int countTowerCraneWarningData(QueryConditionVo queryConditionVo);

    List<TowercraneRealtimeVO> listTowerCraneWarningData(QueryConditionVo queryConditionVo);

    int countLifterWarningData(QueryConditionVo queryConditionVo);

    List<LifterRealtimeVO> listLifterWarningData(QueryConditionVo queryConditionVo);

    TowercraneRealtimeVO getTowerCraneWarningData(String id);

    List<TowerCraneEntity> data(TowercraneRealtimeVO vo);

    void insertData(Map<String, Object> map);

    List<TowercraneRealtimeVO> countGroupByEquipmentId();

    void insertShardingData(Map<String, Object> map);

    List<String> groupByEquipmentId();


}
