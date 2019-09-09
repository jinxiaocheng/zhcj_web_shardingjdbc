package com.escst.towerCrane;

import com.escst.commons.utils.UuidUtils;
import com.escst.equipment.vo.QueryVO;
import com.escst.towerCrane.mapper.TowercraneEntityDemoMapper;
import com.escst.towerCrane.vo.TowercraneEntityDemo;
import com.escst.towerCrane.vo.TowercraneMonitorVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.towerCrane.service.TowerCraneService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhouwei
 * @desc
 * @date 2017年8月21日 下午6:10:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class TowerCraneServiceTest {

    @Autowired
    public TowerCraneService service;
    @Autowired
    public TowercraneEntityDemoMapper towercraneEntityDemoMapper;

    @Test
    public void test01_queryRealtimeList() {
        AcquisitionDataQueryVO queryVO = new AcquisitionDataQueryVO();
        queryVO.setEquipmentId("7e162f4ed0f844be99cca61a258ec533");
        queryVO.setStartDate("2017-04-24");
        queryVO.setEndDate("2017-04-24");
        PageVo rst = service.queryRealtimeList(queryVO);
        System.out.println(JSONObject.toJSONString(rst));
    }

    @Test
    public void test02_queryRealtimeDate() {
        String userId = "c18c1656727011e79386b82a72dd25a2";
        QueryVO vo = new QueryVO();
        vo.setUserId(userId);
        List<TowercraneMonitorVo> monitorVo = service.selectTowerCraneMonitor(vo);
        System.out.println(JSONObject.toJSONString(monitorVo));
    }

    @Test
    public void test() {
        String equipmentId = "3ba322dbb1c842e990445c5b33ab026f";
        TowercraneRealtimeVO towercraneRealtimeVO = new TowercraneRealtimeVO();
        towercraneRealtimeVO.setEquipmentId(equipmentId);
        towercraneRealtimeVO.setPage(1);
        towercraneRealtimeVO.setRowNum(10000);
        towercraneRealtimeVO.setStartTime("2017-10-10");
        towercraneRealtimeVO.setEndTime("2019-10-10");
        PageVo pageVo = service.listHistoryData(towercraneRealtimeVO);
    }


    @Test
    public void test_insert() {
//        TowercraneEntityDemo towercraneEntityDemo = new TowercraneEntityDemo();
//        towercraneEntityDemo.setId(UuidUtils.getUuid());
//        towercraneEntityDemo.setEquipmentId("3ba322dbb1c842e990445c5b33ab026f");
//        towercraneEntityDemo.setCreateTime(new Date());
//        towercraneEntityDemoMapper.insert(towercraneEntityDemo);

          service.shardingInsert();
    }




}
