package com.escst.material.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialBudgetEntity;
import com.escst.material.service.MaterialBudgetService;

/**
 * @desc 材料预算
 * @author niejing
 * @date 2017年8月26日 下午4:34:35
 */
@Controller
@RequestMapping("materialBudget")
public class MaterialBudgetController {

	private static Logger logger = LoggerFactory.getLogger(MaterialBudgetController.class);
	
	@Autowired
	private MaterialBudgetService service;
	
	@RequestMapping("list")
	public ModelAndView list(Model model) {
		return new ModelAndView("material/budget");
	}
	
	/**
	 * 
	 * @desc 
	 * @param entity 材料预算列表
	 * @return 
	 * @author niejing
	 * @date 2017年8月26日 下午4:40:54
	 */
	@RequestMapping("materialBudgetList")
	@ResponseBody
	public ReturnJson materialBudgetList(MaterialBean entity) {
		ReturnJson returnJson;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			PageVo pageVo = service.queryList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询材料进场列表出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("查询材料进场列表出现异常");
		}
		return returnJson;
	}
	

	/**
	 * 
	 * @desc 跳转到新增材料预算页面
	 * @param model
	 * @return
	 * @author niejing
	 * @date 2017年12月6日 下午2:39:48
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		return new ModelAndView("material/addBudget");
	}
	
	/**
	 * 
	 * @desc 新增材料预算
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2017年12月7日 下午1:33:17
	 */
	@RequestMapping("add")
	@ResponseBody
	public ReturnJson add(MaterialBudgetEntity entity) {
		ReturnJson returnJson = null;
		try{
			service.save(entity);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("新增材料预算异常",e.getMessage());
			returnJson = ReturnJson.fail("新增材料预算异常");
		}
		return returnJson;
	}

	
	/**
	 * 
	 * @desc 跳转到修改材料预算页面
	 * @param model
	 * @return
	 * @author niejing
	 * @date 2017年12月6日 下午2:39:48
	 */
	@RequestMapping("toEdit")
	public ModelAndView toEdit(Model model, String id) {
		Map<String,Object> map = service.queryBudgetById(id);
		model.addAttribute("map", map);
		return new ModelAndView("material/editBudget");
	}
	
	/**
	 * 
	 * @desc 修改材料预算
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2017年12月7日 下午1:33:17
	 */
	@RequestMapping("edit")
	@ResponseBody
	public ReturnJson edit(MaterialBudgetEntity entity) {
		ReturnJson returnJson = null;
		try{
			service.update(entity);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("修改材料预算异常",e.getMessage());
			returnJson = ReturnJson.fail("修改材料预算异常");
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 跳转到新增材料预算页面
	 * @param model
	 * @return
	 * @author niejing
	 * @date 2017年12月6日 下午2:39:48
	 */
	@RequestMapping("chooseMaterial")
	public ModelAndView chooseMaterial(Model model) {
		return new ModelAndView("material/chooseMaterial");
	}
	
	@RequestMapping("toImport")
	public ModelAndView toImport(Model model, String constructionId) {
		return new ModelAndView("material/toImportBudget");
	}

	/**
	 * 导入
	 *
	 * @param request
	 * @param response
	 * @param file
	 *            文件
	 * @param constructionId
	 *            工地ID
	 * @return
	 */
	@RequestMapping("saveFile")
	@ResponseBody
	public void saveFile(MultipartFile file,HttpServletResponse response,String constructionId) throws IOException {
		ReturnJson returnJson = null;
		String result = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			result = service.importDataFromExcel(file, constructionId);
			logger.info(result);
			if (!StringUtils.isEmpty(result)) {
				returnJson = ReturnJson.fail(result);
			} else {
				returnJson = ReturnJson.success();
			}
		} catch (EscstException e) {
			logger.error("导入总体进度计划数据出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("导入数据出现异常");
		}
		String data = JSONObject.toJSONString(returnJson);
		response.getWriter().write(data);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
