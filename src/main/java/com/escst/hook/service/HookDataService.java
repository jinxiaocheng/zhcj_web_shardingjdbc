package com.escst.hook.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.hook.mapper.HookDataMapper;
import com.escst.hook.vo.HookDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author jincheng
 * @desc 吊钩数据监测相关
 * @date 2018-10-22 13:42
 */
@Service
public class HookDataService {

    @Autowired
    private HookDataMapper hookDataMapper;


    /**
     * @author jincheng
     * @desc 查询吊钩列表
     * @date 2018-10-22 13:43
     */
    public List<HookDataVo> listHook(String constructionId) {
        List<HookDataVo> list = new ArrayList<HookDataVo>();
        try {
            list = hookDataMapper.listHook(constructionId);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询吊钩列表异常", e);
        }
    }


    /**
     * @author jincheng
     * @desc 查询吊钩实时数据
     * @date 2018-10-22 13:43
     */
    public HookDataVo getRealTimeData(String hookId) {
        HookDataVo hookDataVo = new HookDataVo();
        try {
            hookDataVo = hookDataMapper.getRealTimeData(hookId);
            return hookDataVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询吊钩实时数据异常", e);
        }
    }


    /**
     * @author jincheng
     * @desc 查询吊钩历史数据
     * @date 2018-10-22 13:43
     */
    public PageVo listHistoryData(HookDataVo hookDataVo) {
        PageVo pageVo = new PageVo();
        try {
            hookDataVo.setPage((hookDataVo.getPage() - 1) * hookDataVo.getRowNum());
            int count = hookDataMapper.countHistoryData(hookDataVo);
            if (count == 0) {
                return pageVo;
            }
            List<HookDataVo> list = hookDataMapper.listHistoryData(hookDataVo);
            pageVo.setRows(list);
            pageVo.setTotalRecord(count);
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询吊钩历史数据异常", e);
        }
        return pageVo;
    }


    /**
     * @author jincheng
     * @desc 查询吊钩监测当日小时趋势图数据
     * @date 2018-10-22 13:43
     */
    public Map<Object, List<String>> getChartData(HookDataVo hookDataVo) {
        Map<Object, List<String>> map = new LinkedHashMap<Object, List<String>>();
        try {
            // 设置小时X轴的默认值
            int hour = DateUtils.getHour(new Date());
            List<String> xData = new ArrayList<String>();
            for (Integer i = 0; i <= hour; i++) {
                xData.add(String.valueOf(i));
            }

            // 设置幅度y轴的默认值
            List<String> extentData = new ArrayList<String>();
            for (Integer i = 0; i <= hour; i++) {
                extentData.add(String.valueOf(0));
            }

            // 设置高度y轴的默认值
            List<String> heightData = new ArrayList<String>();
            for (Integer i = 0; i <= hour; i++) {
                heightData.add(String.valueOf(0));
            }

            // 当天开始时间
            hookDataVo.setStartTime(DateUtils.format(DateUtils.getTodayStart(), DateUtils.TO_SECOND));
            // 当天结束时间
            hookDataVo.setEndTime(DateUtils.format(DateUtils.getTodayEnd(), DateUtils.TO_SECOND));
            // 查询真实数据
            List<HookDataVo> chartDataList = hookDataMapper.getChartData(hookDataVo);
            if (!CollectionUtils.isEmpty(chartDataList)) {
                for (HookDataVo dataVo : chartDataList) {
                    for (String xDatum : xData) {
                        if (dataVo.getAcquisitionTime().equals(xDatum)) {
                            // 替换掉默认数据
                            extentData.set(Integer.parseInt(xDatum), String.valueOf(dataVo.getExtent()));
                            heightData.set(Integer.parseInt(xDatum), String.valueOf(dataVo.getHeight()));
                        }
                    }
                }
            }

            // 处理前台需要时间X轴的特殊格式
            int size = xData.size();
            for (int i = 0; i < size; i++) {
                String x = xData.get(i);
                if (x.length() == 1) {
                    x = "0" + x + ":00";
                } else {
                    x = x + ":00";
                }
                xData.set(i,x);
            }

            map.put("xData", xData);
            map.put("extentData", extentData);
            map.put("heightData", heightData);
            return map;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询吊钩监测当日小时趋势图数据异常", e);
        }
    }


}
