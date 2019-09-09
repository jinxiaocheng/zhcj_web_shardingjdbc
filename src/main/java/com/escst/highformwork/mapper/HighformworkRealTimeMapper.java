package com.escst.highformwork.mapper;

import java.util.List;
import java.util.Map;

import com.escst.highformwork.bean.RealTimeRequestBean;
import com.escst.highformwork.entity.HighformworkThresholdValueEntity;
import com.escst.highformwork.vo.ConstructionFlowVo;
import com.escst.highformwork.vo.EquipmentVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import com.escst.highformwork.vo.TrendRequestVo;
import com.escst.highformwork.vo.TrendResultVo;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月17日 下午4:24:30
 */
public interface HighformworkRealTimeMapper {

	public List<HighformworkRealTimeVo> getRealTimeDataByConstructionId(Map<String, Object> paramMap);
	
	public List<HighformworkThresholdValueEntity> getAlarmInfo(String equipmentId);
	
	public List<HighformworkRealTimeVo> selectAlarmDetail(Map<String, Object> map);
	
	public List<TrendResultVo> selectTrend(TrendRequestVo vo);
	
	public List<ConstructionFlowVo> selectConstructionFlowList(String constructionId);
	
	public List<EquipmentVo> selectEquipment(TrendRequestVo requestVo);
	
	public List<HighformworkRealTimeVo> selectByFlowId(RealTimeRequestBean bean);

	public int selectCount(RealTimeRequestBean bean);
}
