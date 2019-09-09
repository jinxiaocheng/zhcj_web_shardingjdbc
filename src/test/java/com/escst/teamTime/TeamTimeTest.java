package com.escst.teamTime;

import com.escst.commons.vo.PageVo;
import com.escst.teamTime.VO.TeamTimeVO;
import com.escst.teamTime.service.TeamTimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jincheng
 * @desc 班组工时
 * @date 2018/2/6 16:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class TeamTimeTest {

    @Autowired
    private TeamTimeService teamTimeService;

    @Test
    public void testListData() {
        TeamTimeVO teamTimeVO = new TeamTimeVO();
        teamTimeVO.setUserId("c18c1656727011e79386b82a72dd25a2");
        PageVo pageVo = teamTimeService.listData(teamTimeVO);
        pageVo.getRows();
    }

    @Test
    public void testPersonTimeData() {
        TeamTimeVO teamTimeVO = new TeamTimeVO();
        teamTimeVO.setConstructionId("790de2cfac9111e78e1bc81f66fba166");
        teamTimeVO.setTime("2017-11-15");
        teamTimeVO.setTeamId("6a5140bebdfd11e7819bc81f66fba166");
//        teamTimeVO.setPersonName("叶泽求");
        PageVo pageVo = teamTimeService.personTimeData(teamTimeVO);
    }

    @Test
    public void testSelect() {
        String id = "320b96880ff2437a9d9370591d95af13";
        TeamTimeVO teamTimeVO = teamTimeService.select(id);
        System.out.println(teamTimeVO);
    }



}
