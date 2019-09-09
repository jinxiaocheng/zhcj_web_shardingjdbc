package com.escst.excelimport;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escst.excelimport.service.PersonExcelImportService;
import com.escst.excelimport.vo.PersonImportVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月6日 下午3:21:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class PersonExcelImportServiceTest {

	@Autowired
	private PersonExcelImportService service;
	
	@Test
	public void test01() {
		String constructionId = "123464e35841e6f601584cd30ac10189";
		List<PersonImportVO> list = new ArrayList<PersonImportVO>();
		PersonImportVO vo = new PersonImportVO();
		vo.setConstructionId(constructionId);
		vo.setName("精准测试01");
		vo.setMobile("20171206001");
		vo.setCompanyName("分包公司");
		vo.setPositionWorkTypeName("施工员");
		vo.setPositionWorkTypeId("7");
		list.add(vo);
		
		vo = new PersonImportVO();
		vo.setConstructionId(constructionId);
		vo.setName("精准测试02");
		vo.setMobile("20171206002");
		vo.setCompanyName("劳务公司ffff");
		vo.setPositionWorkTypeName("瓦工");
		vo.setPositionWorkTypeId("105");
		list.add(vo);
		
		service.execute(list);
		System.out.println("执行完毕");
	}
	
	@Test
	public void test02() {
		String constructionId = "25993d79960b11e78e1bc81f66fba166";
		List<PersonImportVO> list = new ArrayList<PersonImportVO>();
		PersonImportVO vo = new PersonImportVO();
		vo.setConstructionId(constructionId);
		vo.setName("艺术馆员工1");
		vo.setMobile("20171206001");
		vo.setCompanyName("百度劳务分包公司");
		vo.setPositionWorkTypeName("项目经理");
		vo.setPositionWorkTypeId("1");
		list.add(vo);
		
		vo = new PersonImportVO();
		vo.setConstructionId(constructionId);
		vo.setName("艺术馆员工2");
		vo.setMobile("20171206003");
		vo.setCompanyName("阿里人才服务中心");
		vo.setPositionWorkTypeName("管工");
		vo.setPositionWorkTypeId("108");
		list.add(vo);
		
		service.execute(list);
		System.out.println("执行完毕");
	}
}
