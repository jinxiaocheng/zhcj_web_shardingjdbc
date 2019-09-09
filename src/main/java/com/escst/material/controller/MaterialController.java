package com.escst.material.controller;

import java.io.IOException;
import java.util.List;
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
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.service.MaterialService;
import com.escst.material.vo.MaterialVo;

/**
 * @desc 材料维护控制器
 * @author niejing
 * @date 2017年12月5日 下午3:33:57
 */
@Controller
@RequestMapping("material")
public class MaterialController {

	private static Logger logger = LoggerFactory.getLogger(MaterialController.class);

	@Autowired
	private MaterialService materialService;

	@RequestMapping("list")
	public ModelAndView list(Model model) {
		return new ModelAndView("material/list");
	}

	/**
	 * 
	 * @desc 材料列表
	 * @param entity
	 * @return
	 * @author niejing
	 * @date 2017年12月6日 下午2:40:02
	 */
	@RequestMapping("listData")
	@ResponseBody
	public ReturnJson listData(MaterialEntity entity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = materialService.queryMaterial(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("材料列表系统异常：{}", e.getMessage());
			returnJson = ReturnJson.fail("材料列表系统异常");
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 新增材料
	 * @param model
	 * @return
	 * @author niejing
	 * @date 2017年12月6日 下午2:39:48
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		return new ModelAndView("material/add");
	}

	@RequestMapping("add")
	@ResponseBody
	public ReturnJson add(MaterialVo vo) {
		ReturnJson returnJson = null;
		try {
			materialService.add(vo);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			if(e instanceof EscstException){
				returnJson = ReturnJson.fail(e.getMessage());
			}else{
				returnJson = ReturnJson.fail("新增材料系统异常");
			}
			logger.error("新增材料系统异常：{}", e.getMessage());
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 查询所有的材料列表 
	 * @return 
	 * @author niejing
	 * @date 2017年12月7日 下午2:37:26
	 */
	@RequestMapping("queryAllMaterial")
	@ResponseBody
	public ReturnJson queryAllMaterial(){
		ReturnJson returnJson = null;
		try{
			List<Map<String,Object>> list = materialService.queryAllMaterial();
			returnJson = ReturnJson.success(list);
		}catch(Exception e){
			logger.error("查询材料列表异常",e);
			returnJson = ReturnJson.fail("查询材料列表异常");
		}
		return returnJson;
	}
	
	@RequestMapping("toImport")
	public ModelAndView toImport(Model model, String constructionId) {
		return new ModelAndView("material/toImportMaterial");
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
	public void saveFile(MultipartFile file,HttpServletResponse response) throws IOException {
		ReturnJson returnJson = null;
		String result = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			result = materialService.importDataFromExcel(file);
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
	
	/**
	 * @desc 查询材料基本信息
	 * @param entity
	 * @return 
	 * @author zhouwei
	 * @date 2017年12月29日 上午11:32:29
	 */
	@RequestMapping("queryBaseMaterial")
	@ResponseBody
	public ReturnJson queryBaseMaterial(MaterialEntity entity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = materialService.queryBaseMaterialList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询材料基本信息异常", e);
			returnJson = ReturnJson.fail("查询材料基本信息异常");
		}
		return returnJson;
	}
}
