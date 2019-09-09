package com.escst.inspection;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.vo.InspectionVO;
import com.escst.inspection.bean.InspectionRequestBean;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.entity.NotifyEntity;
import com.escst.inspection.service.InspectionService;
import com.escst.inspection.vo.ItemsCountVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/** 
* @author hukang
* @version 创建时间：2017年11月21日 下午6:41:19 
* 类说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class InspectionServiceTest {

	@Autowired
	private InspectionService service;
	
//	@Test
//	public void test01_queryCheckResultsTree() {
//		List<TreeEntity> list = service.querySafetyItemsResultsTree(2);
//		System.out.println(JSONObject.toJSONString(list));
//	}
	@Test
	public void test02_save(){
		InspectionEntity inspectionEntity=new InspectionEntity();
		NotifyEntity notifyEntity=new NotifyEntity();
		notifyEntity.setId(UuidUtils.getUuid());
		notifyEntity.setInspectionId(UuidUtils.getUuid());
		notifyEntity.setMobile("123456789");
		notifyEntity.setProjectCompanyId("123");
		notifyEntity.setTeamId("456");
		notifyEntity.setUserId("WTFGSDFGJH");
		
		NotifyEntity notifyEntity02=new NotifyEntity();
		notifyEntity02.setId(UuidUtils.getUuid());
		notifyEntity02.setInspectionId(UuidUtils.getUuid());
		notifyEntity02.setMobile("123456789");
		notifyEntity02.setProjectCompanyId("123");
		notifyEntity02.setTeamId("456");
		notifyEntity02.setUserId("WTFGSDFGJH");
		List<NotifyEntity> NotifyEntity=new ArrayList<NotifyEntity>();
		NotifyEntity.add(notifyEntity);
		NotifyEntity.add(notifyEntity02);
		service.save(inspectionEntity);
	}
	
	@Test
	public void test_queryResultsByInspectionId(){
		String result = service.queryResultsByInspectionId("03de4045761348e786582ef7a37f532d");
		System.out.println("result>>>>"+result);
	}
	
	@Test
	public void test_listCheckItemTree() {
		TreeEntity entity = new TreeEntity();
		entity.setType(2);
		entity.setConstructionId("");
		entity.setPage(1);
		entity.setRowNum(22);
		PageVo pageVo = service.listCheckItemTree(entity);
	}

	@Test
	public void test_addCheckItem() {
		TreeEntity entity = new TreeEntity();
		entity.setId(UuidUtils.getUuid());
		entity.setType(2);
		entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
		entity.setName("测试");
		entity.setpId("0");
		service.addCheckItem(entity);
	}

	@Test
	public void test_updateCheckItem() {
		TreeEntity entity = new TreeEntity();
		entity.setId("51244a18a64740589c2a55da42ef536f");
		entity.setName("测试修改");
		String s = service.updateCheckItem(entity);
		System.out.println(s);
	}

	@Test
	public void test_deleteCheckItem() {
		TreeEntity entity = new TreeEntity();
		entity.setId("51244a18a64740589c2a55da42ef536f");
		entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
		String s = service.deleteCheckItem(entity);
		System.out.println(s);
	}


	@Test
	public void test03_selectItems(){
		InspectionRequestBean bean = new InspectionRequestBean();
		bean.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
		bean.setType(2);
		List<ItemsCountVo> list = service.selectItemsByConstructionId(bean);
		System.out.println(JSONObject.toJSON(list).toString());
	}

	@Test
	public void listInspection() {
		InspectionEntity entity = new InspectionEntity();
		entity.setConstructionId("bbdf3bee43aa4daeb8de784cd9b9a9ec");
		entity.setDate("2018-08-21");
		List<InspectionVO> list = service.listInspection(entity);
	}
}
