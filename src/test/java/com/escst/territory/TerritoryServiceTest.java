package com.escst.territory;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.territory.service.TerritoryService;
import com.escst.territory.vo.AreaConstructionVO;

/**
 * @author caozx
 * @desc
 * @date 2017/8/19 16:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml" })
public class TerritoryServiceTest {

    @Autowired
    private TerritoryService territoryService;

    @Test
    public void test() {

        //admin用户ID
        List<String> constructionIds = territoryService.queryAuthConstructionIds("b0dff884d0a84e4e8896cbc91c5e60f9");

        //rsj用户ID
        List<String> constructionIds1 = territoryService.queryAuthConstructionIds("70c4fe29f7ac483cb02820abb239dc4a");

        //hlp用户ID
        List<String> constructionIds2 = territoryService.queryAuthConstructionIds("3eebd85351ac478fa8e113ce1174267d");

        //qu用户ID
        List<String> constructionIds3 = territoryService.queryAuthConstructionIds("a2491f68bad24225a00b9c1ed10856a9");


        System.out.println(JSONObject.toJSONString(constructionIds));

        System.out.println(JSONObject.toJSONString(constructionIds1));

        System.out.println(JSONObject.toJSONString(constructionIds2));

        System.out.println(JSONObject.toJSONString(constructionIds3));
    }

    @Test
    public void test1() {
        List<AreaConstructionVO> vos = territoryService.queryAuthAreaByUserId("b0dff884d0a84e4e8896cbc91c5e60f9");

        List<AreaConstructionVO> vos1 = territoryService.queryAuthAreaByUserId("70c4fe29f7ac483cb02820abb239dc4a");

        List<AreaConstructionVO> vos2 = territoryService.queryAuthAreaByUserId("a2491f68bad24225a00b9c1ed10856a9");

        System.out.println(JSONObject.toJSONString(vos));

        System.out.println(JSONObject.toJSONString(vos1));

        System.out.println(JSONObject.toJSONString(vos2));
    }
    
    @Test
    public void test2(){
    	List<TreeEntity> list = territoryService.queryAreaTreeByUserId("c18c1656727011e79386b82a72dd25a2");
        System.out.println(JSONObject.toJSONString(list));
    }
}
