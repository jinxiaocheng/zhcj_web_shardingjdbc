package com.escst.person;

import com.escst.commons.vo.PageVo;
import com.escst.person.entity.DoorEntity;
import com.escst.person.service.DoorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc
 * @date 2018-5-30 15:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class DoorServiceTest {

    @Autowired
    private DoorService doorService;


    @Test
    public void queryList() {
        DoorEntity entity = new DoorEntity();
        entity.setPage(1);
        entity.setRowNum(5);
        entity.setConstructionId("ed37af25f84211e7ad0dc81f66fba165");
        PageVo pageVo = doorService.queryList(entity);
    }

    @Test
    public void add() {
        DoorEntity entity = new DoorEntity();
        entity.setName("芝麻开门");
        List<String> list = new ArrayList<String>();
        list.add("f1225673b3194084b728b8bfea9b81d1");
        entity.setControllerId(list);
        doorService.add(entity);
    }

    @Test
    public void listController() {
        String doorId = "4669f19eced8490e923e646ab7c399af";
        DoorEntity doorEntities = doorService.listController(doorId);
    }

    @Test
    public void updateDoor() {
        DoorEntity entity = new DoorEntity();
        entity.setId("22af51be4286414ab3bc5265e744ff98");
        entity.setName("m0004");
        List<String> list = new ArrayList<String>();
//        list.add("f1225673b3194084b728b8bfea9b81d1");
        entity.setControllerId(list);
        doorService.updateDoor(entity);
    }
    
    @Test
    public void listDoor() {
        String personId = "5ef363622d134d2583603c5c0d1176c9";
        Map<Object, Collection> map = doorService.listDoor(personId);
    }

    @Test
    public void allotDoor() {
        DoorEntity entity = new DoorEntity();
        entity.setPersonId("5ef363622d134d2583603c5c0d1176c9");
        List<String> list = new ArrayList<String>();
        list.add("22af51be4286414ab3bc5265e744ff98");
        entity.setDoorIds(list);
        doorService.allotDoor(entity);
    }



}
