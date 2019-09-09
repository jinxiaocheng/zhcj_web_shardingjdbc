package com.escst.lifter.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.escst.commons.vo.QtyVO;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.mapper.EquipmentManagerMapper;
import com.escst.equipment.vo.QueryVO;
import com.escst.equipment.vo.SimpleEquipmentVO;
import com.escst.lifter.vo.LifterMonitorVo;
import com.escst.towerCrane.vo.QueryConditionVo;
import com.escst.towerCrane.vo.TowercraneMonitorVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.mapper.LifterMapper;
import com.escst.lifter.vo.LifterRealtimeVO;

/**
 * @author zhouwei
 * @desc 升降机的业务类
 * @date 2017年8月21日 下午4:50:20
 */
@Service
public class LifterService {

    @Autowired
    private LifterMapper lifterMapper;

    @Autowired
    private EquipmentManagerMapper equipmentManagerMapper;

    /**
     * @param queryVO
     * @return
     * @desc 查询升降机的实时数据
     * @author zhouwei
     * @date 2017年8月21日 下午4:06:52
     */
    public PageVo queryRealtimeList(AcquisitionDataQueryVO queryVO) {
        int totalQty = lifterMapper.getRealtimeCount(queryVO);
        List<LifterRealtimeVO> list = null;
        if (totalQty > 0) {
            list = lifterMapper.queryRealtimeList(queryVO);
        }
        PageVo rst = new PageVo();
        rst.setCurrentPage(queryVO.getPage());
        rst.setPageSize(queryVO.getRowNum());
        rst.setTotalRecord(totalQty);
        rst.setRows(list);
        return rst;
    }

    /**
     * @param constructionId
     * @param equipmentId
     * @return
     * @desc 根据工地id和设备id查询该工地是否在线
     * @author niejing
     * @date 2018年2月27日 上午10:10:29
     */
    public boolean isOline(String constructionId, String equipmentId) {
        boolean boo = false;
        // 获取最新一条数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionId", constructionId);
        map.put("equipmentId", equipmentId);
        map.put("createTime", DateUtils.getXminutesAgoDate(5));
        int count = lifterMapper.selectTimeDiffByIds(map);
        if (count == 0) {
            return boo;
        } else {
            boo = true;
        }
        return boo;
    }

    /**
     * @param createTime
     * @return
     * @desc
     * @author niejing
     * @date 2018年2月27日 上午10:27:28
     */
    public long diffMinute(String createTime) {
        long minute = 0L;
        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(new Date());
            long nowTime = ca.getTimeInMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long entTime = sdf.parse(createTime).getTime();
            minute = (nowTime - entTime) / (1000 * 60);
        } catch (Exception e) {
            throw new EscstException("获取间隔时间异常");
        }
        return minute;
    }


    /**
     * @desc 升降机监测
     * @author jincheng
     * @date 2018-11-5 10:05
     */
    public List<LifterMonitorVo> getLifterMonitor(QueryVO queryVO) {
        List<LifterMonitorVo> voList = new ArrayList<LifterMonitorVo>();
        Map<String, List<LifterRealtimeVO>> map = new HashMap<String, List<LifterRealtimeVO>>();
        try {
            queryVO.setType(EquipmentTypeEnum.LIFTER.getValue());
            queryVO.setStatus(1);
            //有权限查看的工地设备
            List<SimpleEquipmentVO> list = equipmentManagerMapper.queryAuthEquipmentList(queryVO);
            for (SimpleEquipmentVO equipmentVO : list) {
                String equipmentId = equipmentVO.getId();
                String equipmentName = equipmentVO.getName();
                String constructionId = equipmentVO.getConstructionId();
                String constructionName = equipmentVO.getConstructionName();

                //获取设备实时数据
                LifterRealtimeVO realtimeVo = lifterMapper.getLifterRealTimeData(equipmentId);
                if (realtimeVo != null) {
                    // 判断该条实时数据是否报警
                    LifterRealtimeVO lifterWarningData = lifterMapper.getLifterWarningData(realtimeVo.getId());
                    if (lifterWarningData != null) {
                        realtimeVo.setWarning(1);
                        Integer type = lifterWarningData.getType();
                        if (type == null) {
                            realtimeVo.setHeightWarning(1);
                            realtimeVo.setWeightWarning(1);
                            realtimeVo.setObliquityWarning(1);
                            realtimeVo.setPeopleNumWarning(1);
                        } else {
                            if (type == 1) {
                                realtimeVo.setHeightWarning(1);
                            }
                            if (type == 2) {
                                realtimeVo.setWeightWarning(1);
                            }
                            if (type == 3) {
                                realtimeVo.setObliquityWarning(1);
                            }
                            if (type == 4) {
                                realtimeVo.setPeopleNumWarning(1);
                            }
                        }
                    }
                    realtimeVo.setName(equipmentName);
                    if (map.containsKey(constructionId)) {
                        List<LifterRealtimeVO> realtimeVoList = map.get(constructionId);
                        realtimeVoList.add(realtimeVo);
                    } else {
                        List<LifterRealtimeVO> realtimeVoList = new ArrayList<LifterRealtimeVO>();
                        realtimeVoList.add(realtimeVo);
                        map.put(constructionId, realtimeVoList);
                        LifterMonitorVo lifterMonitorVo = new LifterMonitorVo();
                        lifterMonitorVo.setData(realtimeVoList);
                        lifterMonitorVo.setConstructionId(constructionId);
                        lifterMonitorVo.setConstructionName(constructionName);
                        voList.add(lifterMonitorVo);
                    }
                }
            }
            return voList;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "升降机监测异常", e);
        }
    }


    /**
     * @desc 查询升降机监测历史数据
     * @author jincheng
     * @date 2018-11-5 10:05
     */
    public PageVo listLifterHistoryData(QueryConditionVo queryConditionVo) {
        PageVo pageVo = new PageVo();
        try {
            int count = lifterMapper.countLifterHistoryData(queryConditionVo);
            if (count == 0) {
                return pageVo;
            }
            queryConditionVo.setPage((queryConditionVo.getPage() - 1) * queryConditionVo.getRowNum());
            List<LifterRealtimeVO> voList = lifterMapper.listLifterHistoryData(queryConditionVo);
            pageVo.setRows(voList);
            pageVo.setTotalRecord(count);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询升降机监测历史数据异常", e);
        }
    }
}
