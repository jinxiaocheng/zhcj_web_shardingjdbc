package com.escst.construction;

import com.escst.commons.vo.PageVo;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.service.ScheduledPlanService;
import com.escst.construction.vo.ScheduledPlanTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jincheng
 * @desc 进度计划测试
 * @date 2018-4-8 13:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml"})
public class ScheduledPlanServiceTest {

    @Autowired
    private ScheduledPlanService scheduledPlanService;

    @Test
    public void listScheduledPlanTreeTest() {
        ScheduledPlanEntity scheduledPlanEntity = new ScheduledPlanEntity();
        scheduledPlanEntity.setConstructionId("b38462ebb4a411e7b955c81f66fba166");
//        scheduledPlanEntity.setName("土方");
//        scheduledPlanEntity.setParentId("d418966267c74cc7928eec4b5c2d0180");
        PageVo pageVo = scheduledPlanService.listScheduledPlanTree(scheduledPlanEntity);
    }

    @Test
    public void deleteScheduledPlanTreeTest() {
       ScheduledPlanTree scheduledPlanTree = new ScheduledPlanTree();
        scheduledPlanTree.setConstructionId("b38462ebb4a411e7b955c81f66fba166");
//
        scheduledPlanTree.setId("fa49c85be94940878ac54b1da6cd9d43");
//
        scheduledPlanService.deleteScheduledPlanTree(scheduledPlanTree);
    }


}
