package com.escst.highformwork;

import com.alibaba.fastjson.JSONArray;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.vo.ReturnJson;
import com.escst.highformwork.controller.HighformworkWarningController;
import com.escst.highformwork.service.HighformworkWarningService;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author jincheng
 * @desc
 * @date 2018-8-2 9:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml"})
public class HighformworkWaringTest {

    @Autowired
    private HighformworkWarningService highformworkWarningService;
    @Autowired
    private HighformworkWarningController highformworkWarningController;

    @Test
    public void listFlow() {
        HighformworkRealTimeVo timeVo = new HighformworkRealTimeVo();
        timeVo.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        highformworkWarningService.listFlow(timeVo);
    }


    @Test
    public void listFlowTree() {
        String userId  = "cd292687127f4c52b71ba4b44b2e5a96";
        List<TreeEntity> list = highformworkWarningService.listFlowTree(userId);
        String s = JSONArray.toJSONString(list);
        System.out.println(s);

    }

    @Test
    public void listData() {
        HighformworkRealTimeVo timeVo = new HighformworkRealTimeVo();
        timeVo.setFlowId("2a6ad1d5d35f4508a8c5e7e7fad60657");
        timeVo.setStartTime("2018-06-29 01");
        timeVo.setEndTime("2018-07-02 20");
        timeVo.setPage(1);
        timeVo.setRowNum(10);
        timeVo.setWarning(1);
        ReturnJson returnJson = highformworkWarningController.listData(timeVo);

    }


}
