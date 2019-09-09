package com.escst.safety;

import com.escst.commons.utils.UuidUtils;
import com.escst.safety.entity.RiskOperationEntity;
import com.escst.safety.service.RiskOperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author jincheng
 * @desc
 * @date 2018-8-14 17:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class RiskOperationServiceTest {

    @Autowired
    private RiskOperationService riskOperationService;


    @Test
    public void add() {
        RiskOperationEntity entity = new RiskOperationEntity();
        entity.setId(UuidUtils.getUuid());
        entity.setIsAttach(0);
        entity.setCategory("火灾");
        entity.setTitle("放火烧山,牢底坐穿");
        entity.setContent("孔完美,放火烧山");
        entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        entity.setCreateTime(new Date());
        riskOperationService.add(entity);
    }

    @Test
    public void delete() {
        RiskOperationEntity entity = new RiskOperationEntity();
        entity.setId("de406a849185423795973cb9b6f92e74");
        riskOperationService.delete(entity);
    }


    @Test
    public void listData() {
        RiskOperationEntity entity = new RiskOperationEntity();
        entity.setUserId("c18c1656727011e79386b82a72dd25a2");
        entity.setPage(1);
        entity.setRowNum(10);
        riskOperationService.listData(entity);
    }

}
