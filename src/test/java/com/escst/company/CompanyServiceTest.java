package com.escst.company;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.entity.BaseEntity;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.BaseVO;
import com.escst.projectCompany.service.ProjectCompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 10:04 2018/5/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class CompanyServiceTest {

    @Autowired
    private ProjectCompanyService projectCompanyService;


    @Test
    public void test01_selectCompany(){
        BaseAuthVO vo = new BaseAuthVO();
//        vo.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        vo.setUserId("dc4b533c3c6b43e6a548b27ea9843f9e");
        List<BaseVO> list =  projectCompanyService.selectCompanyList(vo);
        System.out.println(JSONObject.toJSON(list).toString());

    }
}
