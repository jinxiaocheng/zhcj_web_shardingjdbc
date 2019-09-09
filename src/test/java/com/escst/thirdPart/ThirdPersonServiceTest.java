package com.escst.thirdPart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.BaseVO;
import com.escst.thirdPart.bean.CountBean;
import com.escst.thirdPart.service.ThirdPersonService;
import com.escst.thirdPart.vo.ChartResultVo;
import com.escst.thirdPart.vo.CountVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 9:33 31/7/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml" })
public class ThirdPersonServiceTest {

    @Autowired
    private ThirdPersonService thirdPersonService;

    @Test
    public void trst01_batchInsert(){
        thirdPersonService.getPersons();

    }

    @Test
    public void test02_selectCount(){
        CountBean bean = new CountBean();
        bean.setStartTime("2018-01-01");
        bean.setEndTime("2018-06-30");
        bean.setType(1);
        List<CountVo> list = thirdPersonService.getCountData(bean);
        System.out.println(JSONObject.toJSON(list).toString());
    }

    @Test
    public void test03_selectDepart(){
        List<BaseVO> vos = thirdPersonService.getDepart();
        System.out.println(JSONObject.toJSON(vos).toString());
    }

    @Test
    public void test04_selectChartDate(){
        CountBean bean = new CountBean();
        bean.setStartTime("2018-01-01");
        bean.setEndTime("2018-06-30");
        bean.setDepartId("-1248306914");
        bean.setValue(0);
        bean.setType(2);
        List<ChartResultVo> list = thirdPersonService.getChartData(bean);
        System.out.println(JSONObject.toJSON(list).toString());
    }
}
