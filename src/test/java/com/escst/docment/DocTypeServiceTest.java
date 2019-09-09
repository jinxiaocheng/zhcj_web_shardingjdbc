package com.escst.docment;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.document.entity.DocTypeEntity;
import com.escst.document.service.DocTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 10:13 2018/5/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class DocTypeServiceTest {

    @Autowired
    private DocTypeService docTypeService;


    @Test
    public void test01_save(){
        DocTypeEntity entity = new DocTypeEntity();
        entity.setName("测试文档1");
//        entity.setId("069bb8dd2dc9462da84d494f8b659fb3");
        entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        entity.setParentId("ed8c0c018cf340a48a117d08af412b05");
        docTypeService.save(entity);
    }

    @Test
    public void test02_select(){
        TreeEntity entity = new TreeEntity();
        entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
//        entity.setId("ed8c0c018cf340a48a117d08af412b05");
        entity.setPage(10);
        entity.setRowNum(10);
        PageVo vo = docTypeService.selectByConstructionId(entity);
        System.out.println(JSONObject.toJSON(vo ).toString());
    }

    @Test
    public void test03_delete(){
        TreeEntity entity = new TreeEntity();
        entity.setId("43cb5b9321834571b0a7047f99932ab1");
        entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        docTypeService.deleteDocType(entity);
     }
}
