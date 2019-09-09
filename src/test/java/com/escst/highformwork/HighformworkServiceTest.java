package com.escst.highformwork;

import com.escst.highformwork.bean.CollectorBean;
import com.escst.highformwork.service.HighformworkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:30 17/7/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml"})
public class HighformworkServiceTest {

    @Autowired
    private HighformworkService highformworkService;



    @Test
    public void batchSave(){
        List<CollectorBean> list = new ArrayList<CollectorBean>();
//        CollectorBean bean01 = new CollectorBean();
//        bean01.setName("采集器1");
//        bean01.setNumber("101");
//        list.add(bean01);
//        CollectorBean bean02 = new CollectorBean();
//        bean02.setName("采集器2");
//        bean02.setNumber("102");
//        list.add(bean02);
//        highformworkService.save(list,null,0);
    }

    @Test
    public void selectAll(){
//        CollectorEntity entity = new CollectorEntity();
//        entity.setStatus(1);
//        entity.setPageSize(1);
//        entity.setPageNum(1);
//        List<CollectorVo> entityList = highformworkService.queryList(entity);
//        System.out.println(JSONObject.toJSON(entityList).toString());
    }


    @Test
    public void saveFlow(){
//        FlowBean bean = new FlowBean();
//        bean.setName("流水段A");
//        bean.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
//        bean.setConstructionName("演示工地");
//        List<CollectorBean> list = new ArrayList<CollectorBean>();
//        CollectorBean bean1 = new CollectorBean();
//        bean1.setId("8723534d27fc445ca175aef4cce2249b");
//        bean1.setName("采集器1");
//        list.add(bean1);
//        CollectorBean bean2 = new CollectorBean();
//        bean2.setId("d8ac6b6d6b9d43a1bf0fbb5f99d2d426");
//        bean2.setName("采集器2");
//        list.add(bean2);
//        bean.setCollectorBeanList(list);
//        highformworkService.saveFlow(bean);
    }


    @Test
    public void  selectAllByConstructionId(){
//        FlowEntity entity = new FlowEntity();
//        entity.setPageSize(10);
//        entity.setPageNum(1);
//        entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
//        List<FlowVo> vos = highformworkService.selectAllByConstructionId(entity);
//        System.out.println(JSONObject.toJSON(vos).toString());
    }


//    @Test
//    public void  selectFilePath(){
//        String constructionId= "rt2bfa3a5186f0d30151d3008cb40d99";
//        List<String> filePath =   highformworkService.selectFilePath(constructionId);
//        System.out.println(JSONObject.toJSON(filePath).toString());
//    }
}
