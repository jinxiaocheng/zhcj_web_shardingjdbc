package com.escst.importdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author caozx
 * @desc
 * @date 2018/4/10 18:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class ImportDataServiceTest {

    @Autowired
    private ImportDataService importDataService;

    @Autowired
    private InspectionDataService inspectionDataService;

    @Test
    public void test() {
        String constructionId = "17472c0a727e4abe8f5a1a2e43b45c80";
        importDataService.importPlan(constructionId);

    }

    @Test
    public void data() throws InterruptedException {
        inspectionDataService.data();
    }
}
