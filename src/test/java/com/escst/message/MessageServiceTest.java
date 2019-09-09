package com.escst.message;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.ContextUtils;
import com.escst.message.entity.MessageEntity;
import com.escst.message.service.MessageService;
import com.escst.message.vo.NoticeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 15:39 7/8/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class MessageServiceTest {

    @Autowired
    private MessageService service;

    @Test
    public void test01_selectNotice(){
        MessageEntity entity = new MessageEntity();
        entity.setCreateBy("c18c1656727011e79386b82a72dd25a2");
        NoticeVo vo = service.selectNotice(entity);
        System.out.println(JSONObject.toJSON(vo).toString());
    }

    @Test
    public void test02_update(){
        List<String> ids = new ArrayList<String>();
        ids.add("14f46b0c46734e778d3c4a57b37efcad");
        ids.add("adab1270dc73447899a18aab21750522");
        //service.batchUpdate(ids);
    }
}
