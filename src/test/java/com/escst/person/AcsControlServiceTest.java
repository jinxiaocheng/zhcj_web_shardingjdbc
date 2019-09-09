package com.escst.person;

import com.escst.commons.vo.PageVo;
import com.escst.person.entity.AcsControlEntity;
import com.escst.person.service.AcsControlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jincheng
 * @desc
 * @date 2018-5-30 17:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml"})
public class AcsControlServiceTest {


    @Autowired
    private AcsControlService acsControlService;

    @Test
    public void listControllerNoDoor() {
        List<AcsControlEntity> list = acsControlService.listControllerNoDoor("ed37af25f84211e7ad0dc81f66fba165");
    }


    @Test
    public void queryList() {
        AcsControlEntity entity = new AcsControlEntity();
        entity.setPage(1);
        entity.setRowNum(5);
        entity.setConstructionId("ed37af25f84211e7ad0dc81f66fba165");
        PageVo pageVo = acsControlService.queryList(entity);
    }

    @Test
    public void saveOrUpdate() {
        AcsControlEntity entity = new AcsControlEntity();
        entity.setId("d376796e923945eaae96b9ca3a45259f");
        entity.setName("云+控制器");
        entity.setNumber("100");
        acsControlService.saveOrUpdate(entity);
    }


    @Test
    public void queryById() {
        AcsControlEntity entity = new AcsControlEntity();
        entity.setId("f1225673b3194084b728b8bfea9b81d0");
        acsControlService.queryById(entity.getId());
    }

}
