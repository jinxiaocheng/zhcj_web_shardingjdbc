package com.escst.material.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialAcquisitionEntity;
import com.escst.material.service.MaterialAcquisitionService;
import com.escst.material.service.MaterialApproachService;
import com.escst.person.entity.PersonEntity;
import com.escst.person.service.PersonService;

/**
 * @desc 材料领用控制器
 * @author niejing
 * @date 2017年8月21日 下午5:10:27
 */
@Controller
@RequestMapping("materialAcquisition")
public class MaterialAcquisitionController {
	private static Logger logger = LoggerFactory.getLogger(MaterialAcquisitionController.class);
	@Autowired
	private MaterialAcquisitionService service;

	@Autowired
	private MaterialApproachService approachService;
	
	@Autowired
	private PersonService personService;

	/**
	 * 进入材料领用列表页面
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public ModelAndView list(Model model) {
		model.addAttribute("userId", ContextUtils.getCurrentUserId());
		return new ModelAndView("material/useMaterialList");
	}

	/**
	 * 材料领用列表
	 *
	 * @param entity
	 *            参数
	 * @return 返回值
	 */
	@RequestMapping("materialAcquisitionList")
	@ResponseBody
	public ReturnJson materialAcquisitionList(MaterialBean entity) {
		ReturnJson returnJson;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			PageVo pageVo = service.queryUseMaterialList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("查询材料领用列表失败:" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * 跳转到新增材料领用页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		// 获取可用材料列表
		List<Map<String,Object>> materialList = approachService.queryAvailableMApproachList(ContextUtils.getCurrentUserId());
		List<PersonEntity> personList = personService.queryAuthPerson(ContextUtils.getCurrentUserId());
		model.addAttribute("materialList", materialList);
		model.addAttribute("personList", personList);
		return new ModelAndView("material/addMaterialAcquisition");
	}

	/**
	 * 跳转到获取材料信息页面
	 *
	 * @param model
	 * @author kongzheng
	 * @date 2017年12月27日 上午9:58
	 * @return
	 */
	@RequestMapping("toGetMaterialData")
	public ModelAndView toGetMaterialData() {
		return new ModelAndView("material/getMaterialData");
	}
	
	/**
	 * 
	 * @desc 新增材料领用
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2017年8月31日 上午9:07:41
	 */
	@RequestMapping("add")
	@ResponseBody
	public ReturnJson add(MaterialAcquisitionEntity entity){
		ReturnJson returnJson = new ReturnJson();
		try {
			service.add(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("修改材料领用状态失败:" + e.getMessage(), e);
		}
		return returnJson;
	}
	
	/**
	 * 修改材料领用状态
	 * 
	 * @param materialAcquisitionEntity
	 *            参数
	 * @return 返回值
	 */
	@RequestMapping("updateUseMaterialStatus")
	@ResponseBody
	public ReturnJson updateUseMaterialStatus(MaterialAcquisitionEntity materialAcquisitionEntity) {
		ReturnJson returnJson;
		try {
			returnJson = service.updateUseMaterialStatus(materialAcquisitionEntity);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("修改材料领用状态失败:" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * 材料领用详情
	 * 
	 * @param entity
	 *            详情
	 * @return 返回值
	 */
	@RequestMapping("acquisitionDetailInfo")
	@ResponseBody
	public ReturnJson acquisitionMaterialDetailInfo(MaterialAcquisitionEntity entity) {
		ReturnJson returnJson;
		try {
			Map<String, Object> map = service.queryAcquisitionDetailInfo(entity);
			returnJson = ReturnJson.success(map);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("查询材料领用详情失败:" + e.getMessage(), e);
		}
		return returnJson;
	}

	/**
	 * @desc 查询可用量大于0的进场材料
	 * @param entity
	 * @return 
	 * @author zhouwei
	 * @date 2017年12月29日 下午1:47:12
	 */
	@RequestMapping("queryMaterialAvailableList")
	@ResponseBody
	public ReturnJson queryMaterialAvailableList(MaterialBean entity) {
		ReturnJson returnJson;
		try {
			PageVo pageVo = approachService.queryMaterialAvailableList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("查询可用量大于0的进场材料失败:", e);
		}
		return returnJson;
	}
}
