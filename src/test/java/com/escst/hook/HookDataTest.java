package com.escst.hook;

import com.alibaba.fastjson.JSON;
import com.escst.commons.vo.PageVo;
import com.escst.hook.service.HookDataService;
import com.escst.hook.vo.HookDataVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc
 * @date 2018-10-22 14:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class HookDataTest {

    @Autowired
    private HookDataService hookDataService;

    @Test
    public void listHookTest() {
        String constructionId = "70b65b25f84b11e7ad0dc81f66fba165";
        List<HookDataVo> list = hookDataService.listHook(constructionId);
    }

    @Test
    public void getRealTimeDataTest() {
        String hookId = "027258a136fe4033aa33e153df6090fe";
        HookDataVo realTimeData = hookDataService.getRealTimeData(hookId);
    }

    @Test
    public void listHistoryData() {
        HookDataVo hookDataVo = new HookDataVo();
        hookDataVo.setHookId("027258a136fe4033aa33e153df6090fe");
        hookDataVo.setStartTime("2017-01-01");
        hookDataVo.setEndTime("2018-07-30");
        hookDataVo.setPage(1);
        hookDataVo.setRowNum(8);
        PageVo pageVo = hookDataService.listHistoryData(hookDataVo);
    }

    @Test
    public void getChartData() {
        HookDataVo hookDataVo = new HookDataVo();
        hookDataVo.setHookId("027258a136fe4033aa33e153df6090fe");
        Map<Object, List<String>> chartData = hookDataService.getChartData(hookDataVo);
        String s = JSON.toJSONString(chartData);
        System.out.println(s);
    }

}
