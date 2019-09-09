package com.escst.thirdPart.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.tempuri.ArrayOfCodeContent;
import org.tempuri.ArrayOfDepart;
import org.tempuri.ArrayOfProjectDepart;
import org.tempuri.ArrayOfUser;
import org.tempuri.CodeContent;
import org.tempuri.Depart;
import org.tempuri.ItemInfoService;
import org.tempuri.ItemInfoServiceSoap;
import org.tempuri.ProjectDepart;
import org.tempuri.User;

import com.escst.commons.exception.EscstException;
import com.escst.thirdPart.service.ThirdPartService;

/**
 * @desc
 * @author niejing
 * @date 2018年7月30日 下午2:37:32
 */
@Component
public class ToolboxManage {
	/**
	 * 
	 * @desc 获取工具箱下级列表
	 * @author niejing
	 * @date 2018年7月24日 上午10:05:21
	 */
	public List<ProjectDepart> getChildByParentID(String parentId) {
		List<ProjectDepart> list = new ArrayList<ProjectDepart>();
		try {
			ItemInfoService it = ThirdPartService.getItemInfoService();

			ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
			ArrayOfProjectDepart arr = soap.getChildByParentID(parentId);
			list = arr.getProjectDepart();
		} catch (Exception e) {
			throw new EscstException("调用第三方接口，获取工具箱下级列表异常", e);
		}
		return list;
	}

	/**
	 * 
	 * @desc 获取编码信息列表
	 * @author niejing
	 * @date 2018年7月24日 下午1:22:42
	 */
	public List<CodeContent> getCodeContentByKindName(String kindName) {
		List<CodeContent> list = new ArrayList<CodeContent>();
		try {
			ItemInfoService it = ThirdPartService.getItemInfoService();

			ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
			ArrayOfCodeContent arr = soap.getCodeContentByKindName(kindName);
			list = arr.getCodeContent();
		} catch (Exception e) {
			throw new EscstException("调用第三方接口，获取编码信息列表异常", e);
		}
		return list;
	}

	/**
	 * 
	 * @desc 获取项目部用户列表
	 * @author niejing
	 * @date 2018年7月24日 下午1:24:00
	 */
	public void getRootProjectUsers() {
		ItemInfoService it = ThirdPartService.getItemInfoService();

		ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
		ArrayOfUser arr = soap.getRootProjectUsers("111111");
		List<User> list = arr.getUser();
		System.out.println(">>>>>>>>>>>>>>>>>>" + list);
	}

	public List<Depart> getRootProject() {
		ItemInfoService it = ThirdPartService.getItemInfoService();

		ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
		ArrayOfDepart arr = soap.getRootProject();
		List<Depart> list = arr.getDepart();
		return list;
	}

	/**
	 * 
	 * @desc 获取所有的单位信息
	 * @author niejing
	 * @date 2018年7月30日 下午1:31:49
	 */
	public List<Depart> getDeparts() {
		List<Depart> list = new ArrayList<Depart>();
		try {
			ItemInfoService it = ThirdPartService.getItemInfoService();
			ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
			ArrayOfDepart arr = soap.getDeparts();
			list = arr.getDepart();
		} catch (Exception e) {
			throw new EscstException("调用第三方接口， 获取所有的单位信息 异常", e);
		}
		return list;
	}

	public static void main(String[] args) {
		ToolboxManage service = new ToolboxManage();
		 List<CodeContent> list = service.getCodeContentByKindName("户口性质");
		
		 for (CodeContent cc : list) {
			 System.out.println(cc.getContent());
		 }

//		List<Depart> list = service.getDeparts();
//		for (Depart d : list) {
//			System.out.println(d.getID() + "---" + d.getDepartName());
//		}
//
//		 List<Depart> list = service.getRootProject();
//		 for(Depart d : list){
//		 System.out.println("id>>>>>>>> "+d.getID()+"--"+d.getDepartName());
//		 }
//		
//		 List<ProjectDepart> dList =
//		 service.getChildByParentID("-1248306914");
//		 for(ProjectDepart p :dList){
//		 System.out.println(p.getDepartName());
//		 }
	}
}
