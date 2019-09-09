package com.escst.highformwork;

import com.alibaba.fastjson.JSONObject;
import com.escst.highformwork.bean.FlowBean;
import com.escst.highformwork.service.HighformworkService;
import com.escst.highformwork.vo.FlowVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author jincheng
 * @desc
 * @date 2018-10-15 16:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class HighformworkTest {

    @Autowired
    private HighformworkService highformworkService;

    @Test
    public void selectFilePathTest() {
        String flowId = "23199ff0db914f029c9fa064615acd02";
        String filePath = highformworkService.selectFilePath(flowId);
    }


    @Test
    public void getTime(){
        String flowId = "c044a7a092d849f2b15b4ab8100cea54";
        FlowVo vo = highformworkService.getTime(flowId);
        System.out.println(JSONObject.toJSONString(vo));
    }


    @Test
    public void update(){
        FlowBean bean = new FlowBean();
        bean.setId("86332c9a51ff40498de56d2e597cefac");
        bean.setIds("9ac22d57414644ecaedef35ea5794c14,269b48e6d9c2414caa32d6980f30464d");
        highformworkService.updateFlow(bean);
    }


}
