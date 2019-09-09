package com.escst.towerCrane.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.threadpool.SimpleThreadPool;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.mapper.EquipmentManagerMapper;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.equipment.vo.QueryVO;
import com.escst.equipment.vo.SimpleEquipmentVO;
import com.escst.lifter.vo.LifterRealtimeVO;
import com.escst.menu.mapper.MenuMapper;
import com.escst.towerCrane.entity.TowerCraneEntity;
import com.escst.towerCrane.mapper.TowerCraneMapper;
import com.escst.towerCrane.thread.ShardingThread;
import com.escst.towerCrane.vo.QueryConditionVo;
import com.escst.towerCrane.vo.TowercraneMonitorVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author niejing
 * @desc 塔吊服务类
 * @date 2017年4月19日 下午3:50:15
 */
@Service
@Transactional
public class TowerCraneService {

    @Autowired
    private TowerCraneMapper towerCraneMapper;

    @Autowired
    private EquipmentManagerMapper equipmentManagerMapper;

    @Autowired
    private MenuMapper menuMapper;


    /**
     * @param entity
     * @desc 新增塔吊
     * @author niejing
     * @date 2017年5月9日 上午9:42:57
     */
    public void addTowerCrane(TowerCraneEntity entity) {
        try {
            String id = UuidUtils.getUuid();
            entity.setId(id);
            towerCraneMapper.insert(entity);
        } catch (Exception e) {
            throw new EscstException("插入塔吊数据异常" + e.getMessage(), e);
        }
    }

    /**
     * @param queryVO
     * @return
     * @desc 查询塔吊的实时数据
     * @author zhouwei
     * @date 2017年8月21日 下午4:06:52
     */
    public PageVo queryRealtimeList(AcquisitionDataQueryVO queryVO) {
        int totalQty = towerCraneMapper.getRealtimeCount(queryVO);
        List<TowercraneRealtimeVO> list = null;
        if (totalQty > 0) {
            list = towerCraneMapper.queryRealtimeList(queryVO);
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
        int count = towerCraneMapper.selectTimeDiffByIds(map);
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

    public List<TowercraneMonitorVo> selectTowerCraneMonitor(QueryVO queryVO) {
        List<TowercraneMonitorVo> rst = new ArrayList<TowercraneMonitorVo>();
        Map<String, List<TowercraneRealtimeVO>> map = new HashMap<String, List<TowercraneRealtimeVO>>();
        try {
            queryVO.setType(EquipmentTypeEnum.TOWERCRANE.getValue());
            queryVO.setUserId(queryVO.getUserId());
            queryVO.setStatus(1);
            //有权限查看的工地设备
            List<SimpleEquipmentVO> list = equipmentManagerMapper.queryAuthEquipmentList(queryVO);
            for (SimpleEquipmentVO equipmentVO : list) {
                String equipmentId = equipmentVO.getId();
                String equipmentName = equipmentVO.getName();
                String constructionId = equipmentVO.getConstructionId();
                String constructionName = equipmentVO.getConstructionName();
                AcquisitionDataQueryVO vo = new AcquisitionDataQueryVO();
                vo.setConstructionId(constructionId);
                //获取设备实时数据
                TowercraneRealtimeVO realtimeVo = towerCraneMapper.queryRealtimeData(equipmentId);
                if (realtimeVo != null) {
                    // 处理实时数据是否预警
                    TowercraneRealtimeVO towerCraneWarningData = towerCraneMapper.getTowerCraneWarningData(realtimeVo.getId());
                    if (towerCraneWarningData != null) {
                        realtimeVo.setWarning(1);
                        Integer type = towerCraneWarningData.getType();
                        if (type == null) {
                            realtimeVo.setAngleWarning(1);
                            realtimeVo.setExtentWarning(1);
                            realtimeVo.setHeightWarning(1);
                            realtimeVo.setWindSpeedWarning(1);
                            realtimeVo.setWeightWarning(1);
                            realtimeVo.setObliquityWarning(1);
                        } else {
                            if (type == 1) {
                                realtimeVo.setAngleWarning(1);
                            }
                            if (type == 2) {
                                realtimeVo.setExtentWarning(1);
                            }
                            if (type == 3) {
                                realtimeVo.setHeightWarning(1);
                            }
                            if (type == 4) {
                                realtimeVo.setWindSpeedWarning(1);
                            }
                            if (type == 5) {
                                realtimeVo.setWeightWarning(1);
                            }
                            if (type == 6) {
                                realtimeVo.setObliquityWarning(1);
                            }
                        }

                    }
                    realtimeVo.setName(equipmentName);
                    if (map.containsKey(constructionId)) {
                        List<TowercraneRealtimeVO> realtimeVoList = map.get(constructionId);
                        realtimeVoList.add(realtimeVo);
                    } else {
                        List<TowercraneRealtimeVO> realtimeVoList = new ArrayList<TowercraneRealtimeVO>();
                        realtimeVoList.add(realtimeVo);
                        map.put(constructionId, realtimeVoList);
                        TowercraneMonitorVo monitorVo = new TowercraneMonitorVo();
                        monitorVo.setData(realtimeVoList);
                        monitorVo.setConstructionId(constructionId);
                        monitorVo.setConstructionName(constructionName);
                        rst.add(monitorVo);
                    }
                }

            }

        } catch (Exception e) {
            throw new EscstException("查询工地下塔吊实时数据异常" + e.getMessage(), e);
        }
        return rst;
    }

    /**
     * @desc 查询塔吊历史数据
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    public PageVo listHistoryData(TowercraneRealtimeVO towercraneRealtimeVO) {
        PageVo pageVo = new PageVo();
        try {
            int count = towerCraneMapper.getCount(towercraneRealtimeVO);
            if (count == 0) {
                return pageVo;
            }
            towercraneRealtimeVO.setStartIndex((towercraneRealtimeVO.getPage() - 1) * towercraneRealtimeVO.getRowNum());
            List<TowercraneRealtimeVO> list = towerCraneMapper.listHistoryData(towercraneRealtimeVO);
            pageVo.setTotalRecord(count);
            pageVo.setCurrentPage(towercraneRealtimeVO.getPage());
            pageVo.setPageSize(towercraneRealtimeVO.getRowNum());
//            pageVo.setTotalPage();
            pageVo.setRows(list);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询塔吊历史数据异常", e);
        }
    }

    /**
     * @desc 查询塔吊预警数据
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    public PageVo listTowerCraneWarningData(QueryConditionVo queryConditionVo) {
        PageVo pageVo = new PageVo();
        try {
            int count = towerCraneMapper.countTowerCraneWarningData(queryConditionVo);
            if (count == 0) {
                return pageVo;
            }
            queryConditionVo.setStartIndex((queryConditionVo.getPage() - 1) * queryConditionVo.getRowNum());
            List<TowercraneRealtimeVO> list = towerCraneMapper.listTowerCraneWarningData(queryConditionVo);
            pageVo.setTotalRecord(count);
            pageVo.setCurrentPage(queryConditionVo.getPage());
            pageVo.setPageSize(queryConditionVo.getRowNum());
//            pageVo.setTotalPage();
            pageVo.setRows(list);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询塔吊历史数据异常", e);
        }
    }

    /**
     * @desc 查询升降机预警数据
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    public PageVo listLifterWarningData(QueryConditionVo queryConditionVo) {
        PageVo pageVo = new PageVo();
        try {
            int count = towerCraneMapper.countLifterWarningData(queryConditionVo);
            if (count == 0) {
                return pageVo;
            }
            queryConditionVo.setStartIndex((queryConditionVo.getPage() - 1) * queryConditionVo.getRowNum());
            List<LifterRealtimeVO> list = towerCraneMapper.listLifterWarningData(queryConditionVo);
            pageVo.setTotalRecord(count);
            pageVo.setCurrentPage(queryConditionVo.getPage());
            pageVo.setPageSize(queryConditionVo.getRowNum());
//            pageVo.setTotalPage();
            pageVo.setRows(list);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询升降机预警数据异常", e);
        }
    }


    public void data() {
        String start = "2018-07";
        int i;
        int end = 201809;
        for (i = 0; i <= end; i++) {
            // 获得当月的第一天
            String startTime = DateUtils.getFirstDayOfMonth(start);
            // 获得当月的最后一天
            String endTime = DateUtils.getLastDayOfMonth(start);
            TowercraneRealtimeVO vo = new TowercraneRealtimeVO();
            vo.setStartTime(startTime);
            vo.setEndTime(endTime);

            // 获取当月的数据
            List<TowerCraneEntity> list = towerCraneMapper.data(vo);


            start = startTime.split("-")[0] + startTime.split("-")[1];
            String tableName = "t_basic_towercrane_realtime_" + start;
            i = Integer.valueOf(start);
            // 获得下一个月
            start = DateUtils.getPreMonth(start);

            if (CollectionUtils.isEmpty(list)) {
                continue;
            }

            for (TowerCraneEntity towerCraneEntity : list) {
                if (StringUtils.isBlank(towerCraneEntity.getCreateBy())) {
                    towerCraneEntity.setCreateBy("1");
                }
                if (StringUtils.isBlank(towerCraneEntity.getUpdateBy())) {
                    towerCraneEntity.setUpdateBy("1");
                }
                towerCraneEntity.setUpdateTime(new Date());
                if (towerCraneEntity.getCreateTime() == null) {
                    towerCraneEntity.setCreateTime(new Date());
                }
            }


            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tableName", tableName);
            map.put("list", list);

            towerCraneMapper.insertData(map);

        }

    }


    public void test() {
        String date = "2017-10";
//        // 获得当月的第一天
        String startTime = DateUtils.getFirstDayOfMonth(date);
//        // 获得当月的最后一天
        String endTime = DateUtils.getLastDayOfMonth(date);
//        String startTime = "2018-05-25 18:31:00";
//        String endTime = "2018-05-25 18:31:04";

        TowercraneRealtimeVO vo = new TowercraneRealtimeVO();
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);

        // 获取当月的数据
        List<TowerCraneEntity> list = towerCraneMapper.data(vo);

//        String tableName = "t_basic_towercrane_realtime_history_" +;

        for (TowerCraneEntity towerCraneEntity : list) {
            towerCraneEntity.setCreateBy("1");
            towerCraneEntity.setUpdateBy("1");
            towerCraneEntity.setUpdateTime(new Date());
            if (towerCraneEntity.getCreateTime() == null) {
                towerCraneEntity.setCreateTime(new Date());
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("tableName", tableName);
        map.put("list", list);

        towerCraneMapper.insertData(map);
    }

    public void shardingInsert() {
        Date start = new Date();
        int num = 10;
        List<String> groupByEquipmentIdList = towerCraneMapper.groupByEquipmentId();
        for (String equipmentId : groupByEquipmentIdList) {
            String tableName = "t_towercrane_data_";
            if (StringUtils.isNotBlank(equipmentId)) {
                int i = equipmentId.hashCode() % num;
                i = Math.abs(i);
                tableName = tableName + i;

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("tableName", tableName);
                map.put("equipmentId", equipmentId);

//                SimpleThreadPool.getInstance().executorService.execute(new ShardingThread(map));

                towerCraneMapper.insertShardingData(map);
            }
        }
        Date end = new Date();
        System.out.println("================================================塔吊分表总计耗时" + DateUtils.getDatePoor(end, start));
    }

    public void insertShardingData(Map<String, Object> map) {
        towerCraneMapper.insertShardingData(map);
    }


    public List<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("08b8fa74a218400f9e666d59f5dbe9e7");
        list.add("18401c410c53423bbb71208e9a1ca611");
        list.add("18401c410c53423bbb71208e9a1ca613");
        list.add("23ba9f8217ce4ba0b027b7713ae917f1");
        list.add("25c664de9c424ba3b9bd06defab43aa3");
        list.add("2adefad214214be3b41acb861ac55326");
        list.add("3245dbc2b9ce4fb88dc29bf21f8b9b52");
        list.add("3777a58d1da747768964d031f8173460");
        list.add("3ba322dbb1c842e990445c5b33ab026f");
        list.add("5df48878ac9611e78e1bc81f66fba166");
        list.add("695873baec914ebea81da19011af31d5");
        list.add("6d299bf1085d4f0a8cf53656d265d7ae");
        list.add("72fd61198ea948c9af1c5864f9e6a8f9");
        list.add("76bfaf9665344995a03746638c2ac054");
        list.add("78fdf737d49f42a1b9752f840fd4d6c6");
        list.add("8407025a15b74426906d0d849cec2ce9");
        list.add("85ac915a29e24daba815df427e0538c4");
        list.add("8a7214f518344d42a572c7ed24049b88");
        list.add("8a7cfd391c8f4be3bdbd368a8752724e");
        list.add("8fe127962c2342d1b984fcdb55bf9205");
        list.add("8fe7096b502e4008a241c2575b6fb61e");
        list.add("95a0c0a9818048058946e27b87511d10");
        list.add("bbadeb590e314bf5a67251d7b25709bb");
        list.add("bbfd08c8b2e847939ded8e6f13117697");
        list.add("bd79eec50e794e46ba99af0e4e5d2953");
        list.add("c8c61425eae011e7819bc81f66fba166");
        list.add("db0a9dffe913438b898282bd6aad46ab");
        list.add("e1212cc97b334b18bcc8508bc83a76fa");
        list.add("e2442519bfa011e7819bc81f66fba166");
        list.add("e33cc361fca04f23a811ba4bfdeda901");
        list.add("edb5e8d52f9f435fade0af28f7cf201f");
        list.add("fe78e865f528493fbc457099c0cd5402");
        list.add("fe78e865f528493fbc457099c0cd540a");
        return list;
    }

}
