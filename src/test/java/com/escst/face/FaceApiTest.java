package com.escst.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.HttpClientUtils;
import com.escst.faceRecognition.vo.FaceRecognitionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jincheng
 * @desc 调用人像轨迹监控系统接口测试
 * @date 2018/3/13 10:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class FaceApiTest {


}
