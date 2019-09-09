package com.escst.document.controller;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.document.entity.DataManagementEntity;
import com.escst.document.entity.DocTypeEntity;
import com.escst.document.service.DataManagementService;
import com.escst.document.service.DocTypeService;
import com.escst.document.vo.DataManagementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @desc 文档管理
 * @author zhouwei
 * @date 2017年4月27日 下午12:11:08
 */
@Controller
@RequestMapping("document")
public class DocumentController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DataManagementService dataManagementService;

	@Autowired
	private DocTypeService docTypeService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("document/list");
		//获取当前用户ID
		String userId = ContextUtils.getCurrentUserId();
		modelAndView.addObject("userId",userId);
		return modelAndView;
	}

	/**
	 * 通过工地id查询资料文件列表
	 *
	 * @param dataManagementEntity
	 * @return
	 */
	@RequestMapping("queryByConstructionId")
	@ResponseBody
	public ReturnJson queryByConstructionId(DataManagementEntity dataManagementEntity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = dataManagementService.queryByConstructionId(dataManagementEntity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("查询文档列表信息出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	 * 跳转到查看页面
	 * @param id
	 * @return
	 */
	@RequestMapping("toView")
	public ModelAndView toView(@RequestParam String id) {
		ModelAndView modelAndView = new ModelAndView();
		DataManagementVO vo = dataManagementService.queryById(id);
		modelAndView.addObject("vo",vo);
		modelAndView.setViewName("document/view");
		return modelAndView;
	}

	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd() {
		ModelAndView modelAndView = new ModelAndView();
		//获取当前用户ID
		String userId = ContextUtils.getCurrentUserId();
		modelAndView.addObject("userId",userId);
		modelAndView.setViewName("document/add");
		return modelAndView;
	}

	/**
	 * 新增和修改文档资料信息
	 * @param dataManagementEntity
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public ReturnJson save(DataManagementEntity dataManagementEntity) {
		ReturnJson returnJson = null;
		try {
			dataManagementService.save(dataManagementEntity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("保存文档信息出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	@RequestMapping("docTypeTree")
	@ResponseBody
	public ReturnJson DocTypeTree(BaseAuthVO vo){
		ReturnJson returnJson = null;
		try{
			vo.setUserId(ContextUtils.getCurrentUserId());
			List<TreeEntity> treeEntitiy =  dataManagementService.queryDocTypeTree(vo);
			returnJson = ReturnJson.success(treeEntitiy);
		}catch (Exception e){
			logger.error("查询文档树形结构异常:"+e.getMessage(),e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	@RequestMapping("bathSave")
	@ResponseBody
	public ReturnJson bathSave(DataManagementEntity dataManagementEntity){
		ReturnJson returnJson = null;
		try{
			dataManagementEntity.setCreateBy(ContextUtils.getCurrentUserId());
			dataManagementService.bathSave(dataManagementEntity);
			returnJson = ReturnJson.success();
		}catch (Exception e){
			logger.error("新增文档异常:"+e.getMessage(),e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	* @desc 删除用户相关内容
	* @param entity
	* @return
	* @author dwj
	* @date 2018/4/13 16:52
	*/
	@RequestMapping("deleteByUserId")
	@ResponseBody
	public ReturnJson deleteByUserId(DataManagementEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			dataManagementService.deleteByUserId(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("删除文档异常:" + e.getMessage(), e);
			if (e instanceof EscstException) {
				returnJson = ReturnJson.fail(e.getMessage());
			} else {
				returnJson = ReturnJson.fail("系统异常");
			}
		}
		return returnJson;
	}

	/**
	* @desc 新增或修改文档类型
	* @param entity
	* @return
	* @author dwj
	* @date 2018/5/16 10:00
	*/
	@RequestMapping("saveDocType")
	@ResponseBody
	public ReturnJson saveDocType(DocTypeEntity entity){
		ReturnJson returnJson = null;
		try{
			 docTypeService.save(entity);
			 returnJson = ReturnJson.success();
		}catch (Exception e){
			logger.error("新增文档类型异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}


	/**
	* @desc 删除文档类型
	* @param entity
	* @return
	* @author dwj
	* @date 2018/5/16 10:08
	*/
	@RequestMapping("deleteDocType")
	@ResponseBody
	public ReturnJson deleteDocType(TreeEntity entity){
		ReturnJson returnJson = null;
		try{
			docTypeService.deleteDocType(entity);
			returnJson = ReturnJson.success();
		}catch (Exception e){
			logger.error("删除文档类型异常:" + e.getMessage(), e);
			if (e instanceof EscstException) {
				returnJson = ReturnJson.fail(e.getMessage());
			} else {
				returnJson = ReturnJson.fail("系统异常");
			}
		}
		return  returnJson;
	}


	/**
	* @desc 通过工地Id查询文档类型
	* @param entity
	* @return 
	* @author dwj
	* @date 2018/5/16 10:10
	*/
	@RequestMapping("selectDocType")
	@ResponseBody
	public ReturnJson selectDocType(TreeEntity entity){
		ReturnJson returnJson = null;
		try{
			PageVo  pageVo  = docTypeService.selectByConstructionId(entity);
			returnJson = ReturnJson.success(pageVo);
		}catch (Exception e){
			logger.error("查询文档类型异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}


	/**
	* @desc 跳转到文档类型页面
	* @param
	* @return
	* @author dwj
	* @date 2018/5/17 14:40
	*/
	@RequestMapping("toDocType")
	public ModelAndView toDocType() {
		ModelAndView modelAndView = new ModelAndView("document/DocTypeList");
		return modelAndView;
	}

	/**
	* @desc 跳转新增文档类型页面
	* @param
	* @return
	* @author dwj
	* @date 2018/5/17 14:41
	*/
	@RequestMapping("toAddDocType")
	public ModelAndView toAddDocType() {
		ModelAndView modelAndView = new ModelAndView("document/addDocType");
		return modelAndView;
	}

}
