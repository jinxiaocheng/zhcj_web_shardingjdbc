package com.escst.thirdPart.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.CodeContent;
import org.tempuri.Depart;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.thirdPart.entity.CodeContentEntity;
import com.escst.thirdPart.entity.DepartEntity;
import com.escst.thirdPart.manage.ToolboxManage;
import com.escst.thirdPart.mapper.ToolboxMapper;

/**
 * @desc
 * @author niejing
 * @date 2018年7月24日 上午10:03:44
 */
@Service
public class ToolboxService {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("properties/safeTraining");

	private static Map<String, Object> map = new HashMap<String, Object>();

	@Autowired
	private ToolboxManage manage;

	@Autowired
	ToolboxMapper mapper;

	static {
		map.put("民族", 1);
		map.put("岗位", 2);
		map.put("工种", 3);
		map.put("婚姻状况", 4);
		map.put("籍贯", 5);
		map.put("文化水平", 6);
		map.put("户口性质", 7);
	}

	/**
	 * 
	 * @desc 保存所有的单位信息
	 * @author niejing
	 * @date 2018年7月30日 下午1:31:49
	 */
	public void batchInsert() {
		try {
			List<Depart> list = manage.getDeparts();
			if (!CollectionUtils.isEmpty(list)) {
				List<DepartEntity> dhList = transformDepary(list);
				mapper.batchInsert(dhList);
			}
		} catch (Exception e) {
			throw new EscstException("批量插入单位数据异常", e);
		}
	}

	/**
	 * 
	 * @desc 获取工具箱树
	 * @return
	 * @author niejing
	 * @date 2018年7月31日 下午2:37:30
	 */
	public List<TreeEntity> queryDepartTree() {
		List<DepartEntity> departList = mapper.queryList();
		
		TreeEntity topTree = new TreeEntity();
		String topName="演示箱单位1";
		try {
			topName = new String(bundle.getString("topName").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("获取顶级数名称异常"+e);
		}
		topTree.setId("593529145");
		topTree.setName(topName);
		topTree.setpId("0");
		topTree.setOpen(true);
		
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		for (DepartEntity dh : departList) {
			TreeEntity entity = new TreeEntity();
			entity.setId(dh.getId());
			entity.setName(dh.getDepartName());
			entity.setpId(dh.getParentID());
			entity.setOpen(false);

			list.add(entity);
		}
		list.add(topTree);
		Collections.sort(list);
		return list;
	}

	/**
	 * 
	 * @desc 批量插入编码数据
	 * @return
	 * @author niejing
	 * @date 2018年8月1日 上午10:14:49
	 */
	public List<CodeContentEntity> batchInsertCode() {
		List<CodeContentEntity> rst = new ArrayList<CodeContentEntity>();
		try {
			String kindName = new String(bundle.getString("kindName").getBytes("ISO-8859-1"), "UTF-8");
			String[] tmpStr = kindName.split(",");
			for (int i = 0; i < tmpStr.length; i++) {
				String name = tmpStr[i];
				List<CodeContent> list = manage.getCodeContentByKindName(name);
				List<CodeContentEntity> tmplist = transformCode(list, Integer.parseInt(map.get(name).toString()));

				rst.addAll(tmplist);
			}
			mapper.batchInsertCode(rst);
		} catch (Exception e) {
			throw new EscstException("批量插入编码数据", e);
		}
		return rst;
	}

	private List<CodeContentEntity> transformCode(List<CodeContent> list, int name) {
		List<CodeContentEntity> rst = new ArrayList<CodeContentEntity>();
		for (CodeContent code : list) {
			CodeContentEntity entity = new CodeContentEntity();
			entity.setId(code.getID());
			entity.setOperDate(code.getOperDate().toString());
			entity.setCreateDate(code.getCreateDate().toString());
			entity.setAutoID(code.getAutoID());
			entity.setOperUser(code.getOperUser());
			entity.setCreateUser(code.getCreateUser());
			entity.setCodeName(code.getCodeName());
			entity.setCodeOrder(code.getCodeOrder().toString());
			entity.setRemark(code.getRemark());
			entity.setKindID(code.getKindID());
			entity.setCodeContent(code.getContent());
			entity.setType(name);

			rst.add(entity);
		}
		return rst;
	}

	private List<DepartEntity> transformDepary(List<Depart> list) {
		List<DepartEntity> dhList = new ArrayList<DepartEntity>();

		for (Depart d : list) {
			DepartEntity dh = new DepartEntity();
			dh.setId(d.getID());
			dh.setOperDate(d.getOperDate().toString());
			dh.setCreateDate(d.getCreateDate().toString());
			dh.setAutoID(d.getAutoID());
			dh.setOperUser(d.getOperUser());
			dh.setCreateUser(d.getCreateUser());
			dh.setParentID(d.getParentID());
			dh.setDepartCode(d.getDepartCode());
			dh.setParentCode(d.getParentCode());
			dh.setDepartName(d.getDepartName());
			dh.setDepartType(d.getDepartType());
			dh.setDepartShortName(d.getDepartShortName());
			dh.setDepartOrder(d.getDepartOrder().toString());
			dh.setState(d.getState());
			dh.setDepartSir(d.getDepartSir());
			dh.setDepartTel(d.getDepartTel());
			dh.setIsEpiboly(d.getIsEpiboly());
			dh.setRemark(d.getRemark());
			dh.setPhone(d.getPhone());
			dh.setCharge(d.getCharge());
			dh.setAreaCode(d.getAreaCode());
			dh.setAreaID(d.getAreaID());
			dh.setFinished(d.getFinished());
			dh.setItemType(d.getItemType());

			dhList.add(dh);
		}
		return dhList;
	}

}
