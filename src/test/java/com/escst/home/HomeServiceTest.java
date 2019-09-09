package com.escst.home;

import com.escst.home.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jincheng
 * @desc
 * @date 2018-8-21 10:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class HomeServiceTest {

    @Autowired
    private HomeService homeService;



    @Test
    public void queryBasicInfo() {
        homeService.queryBasicInfo("bbdf3bee43aa4daeb8de784cd9b9a9ec");
    }


}
