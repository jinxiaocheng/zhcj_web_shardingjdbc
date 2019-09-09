package com.escst.highformwork.mapper;

import com.escst.highformwork.vo.HighformworkFlowVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 高支模预警，历史数据相关
 * @date 2018-7-18 16:53
 */
@Repository
public interface HighformworkWarningMapper {


    /**
     * 根据流水段，获取其中的测点
     */
    List<HighformworkRealTimeVo> listMeasurePoint(HighformworkRealTimeVo highformworkRealTimeVo);


    /**
     * 查询流水段，测点数据条数
     */
    List<HighformworkFlowVo> getCount(HighformworkRealTimeVo vo);

    int getDataCount(HighformworkRealTimeVo vo);

    /**
     * 查询流水段，测点数据
     */
    List<HighformworkRealTimeVo> listData(HighformworkRealTimeVo vo);

    List<HighformworkRealTimeVo> selectAlarmDetail(Map<String, Object> map);

    List<HighformworkFlowVo> listFlow(HighformworkRealTimeVo vo);

    List<HighformworkFlowVo> listFlowTree(String constructionId);

    HighformworkFlowVo getTime(String flowId);


}
