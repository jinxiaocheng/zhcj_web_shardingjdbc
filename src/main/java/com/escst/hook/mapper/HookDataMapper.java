package com.escst.hook.mapper;

import com.escst.hook.vo.HookDataVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 吊钩数据监测相关
 * @date 2018-10-22 13:43
 */
@Repository
public interface HookDataMapper {

    /**
     * @author jincheng
     * @desc 查询吊钩列表
     * @date 2018-10-22 13:43
     */
    List<HookDataVo> listHook(String constructionId);

    /**
     * @author jincheng
     * @desc 查询吊钩实时数据
     * @date 2018-10-22 13:43
     */
    HookDataVo getRealTimeData(String hookId);


    int countHistoryData(HookDataVo hookDataVo);

    /**
     * @author jincheng
     * @desc 查询吊钩历史数据
     * @date 2018-10-22 13:43
     */
    List<HookDataVo> listHistoryData(HookDataVo hookDataVo);

    /**
     * @author jincheng
     * @desc 查询吊钩监测当日小时趋势图数据
     * @date 2018-10-22 13:43
     */
    List<HookDataVo> getChartData(HookDataVo hookDataVo);
}
