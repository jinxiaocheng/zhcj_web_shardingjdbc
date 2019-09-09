package com.escst.lifter.mapper;

import java.util.List;
import java.util.Map;

import com.escst.towerCrane.vo.QueryConditionVo;
import org.springframework.stereotype.Repository;

import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.vo.LifterRealtimeVO;

@Repository
public interface LifterMapper {
	
	/**
	 * @desc 得到升降机的实时数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月21日 下午4:22:12
	 */
	List<LifterRealtimeVO> queryRealtimeList(AcquisitionDataQueryVO queryVO);
	
	int getRealtimeCount(AcquisitionDataQueryVO queryVO);
	
	int selectTimeDiffByIds(Map<String,Object> map);

	LifterRealtimeVO getLifterRealTimeData(String equipmentId);

	LifterRealtimeVO getLifterWarningData(String id);

	int countLifterHistoryData(QueryConditionVo queryConditionVo);

	List<LifterRealtimeVO> listLifterHistoryData(QueryConditionVo queryConditionVo);
}
