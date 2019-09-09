package com.escst.projectCompany.controller;

import java.util.List;
import java.util.Map;

import com.escst.commons.entity.BaseEntity;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.BaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.entity.ConstructionEntity;
import com.escst.construction.service.ConstructionService;
import com.escst.person.service.PersonService;
import com.escst.projectCompany.bean.CompanyDetailVO;
import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.territory.service.TerritoryService;

/**
 * @desc 合作单位
 * @author zhouwei
 * @date 2017年4月27日 上午11:00:08
 */
@Controller
@RequestMapping("projectCompany")
public class ProjectCompanyController {

	private static Logger logger = LoggerFactory.getLogger(ProjectCompanyController.class);
	
	@Autowired
	public ProjectCompanyService projectCompanyService;
	
	@Autowired
	public TerritoryService territoryService;
	
	@Autowired
	public ConstructionService constructionService;
	
	@Autowired
	public PersonService personService;
	
	/**
	 * @desc 列表
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:04:41
	 */
	@RequestMapping("list")
    public ModelAndView list() {
        return new ModelAndView("projectCompany/list");
    }
	
	/**
	 * 
	 * @desc 查询分包公司列表 
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2017年7月13日 上午10:44:19
	 */
	@RequestMapping("listData")
	@ResponseBody
	public ReturnJson listData(ProjectCompanyEntity entity){
		ReturnJson returnJson = null;
		try{
			entity.setUserId(ContextUtils.getCurrentUserId());
			PageVo vo = projectCompanyService.queryProjectCompanyList(entity);
			returnJson = ReturnJson.success(vo);
		}catch(Exception e){
			logger.error("查询分包公司列表异常",e);
			returnJson = ReturnJson.fail("查询分包公司列表异常");
		}
		return returnJson;
	}
	
	@RequestMapping("toAdd")
    public ModelAndView toAdd(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("projectCompany/add");
		mv.addObject("id",id);
        return mv;
    }

	/**
	 * @param id
	 * @return
	 * @desc
	 * @author caozx
	 * @date 2017/11/3 20:14
	 */
	@RequestMapping("queryById")
	@ResponseBody
    public ReturnJson queryById(String id) {
		ReturnJson returnJson = null;
		try {
			Map<String,Object> map = projectCompanyService.queryDetailInfo(id);
			returnJson = ReturnJson.success(map);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 新增分包公司信息 
	 * @param entity
	 * @return 
	 * @author niejing
	 * @date 2017年7月13日 下午2:01:28
	 */
	@RequestMapping("add")
	@ResponseBody
    public ReturnJson add(ProjectCompanyEntity entity) {
		ReturnJson returnJson = new ReturnJson();
		try{
			String userId = ContextUtils.getCurrentUserId();
			entity.setCreateBy(userId);
			projectCompanyService.addProjectCompany(entity);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("新增分包公司异常", e);
			returnJson = ReturnJson.fail("新增分包公司异常");
		}
        return returnJson;
    }
	
	/**
	 * 
	 * @desc 通过区域ID查询工地列表 
	 * @param areaId
	 * @return 
	 * @author niejing
	 * @date 2017年7月13日 下午3:29:09
	 */
	@RequestMapping("getConstructionList")
	@ResponseBody
	public ReturnJson getConstructionList(String areaId){
		ReturnJson returnJson = null;
		try{
			List<ConstructionEntity> constructionList = constructionService.queryConstructionList(areaId);
			returnJson = ReturnJson.success(constructionList);
		}catch(Exception e){
			logger.error("通过区域ID获取工地信息异常",e);
			returnJson = ReturnJson.fail("通过区域ID获取工地信息异常");
		}
		return returnJson;
	}

	/**
	 * @desc 查询合作单位及负责人
	 * @param entity
	 * @return
	 * @author zhouwei
	 * @date 2017年7月12日 下午2:56:08
	 */
	@RequestMapping("queryDetail")
	@ResponseBody
	public ReturnJson queryDetail(ProjectCompanyEntity entity) {
		ReturnJson returnJson = null;
		try {
			List<CompanyDetailVO> list = personService.queryDetail(entity);
			returnJson = ReturnJson.success(list);
		} catch (EscstException e) {
			logger.error("查询分包公司列表出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	 * @desc 根据检查部位获取合作单位及班组信息
	 * @param projectTaskEntity
	 * @return
	 * @author zhouwei
	 * @date 2017年7月13日 上午8:34:32
	 */
	@RequestMapping("queryDetailByProjectStructionId")
	@ResponseBody
	public ReturnJson queryDetailByProjectStructionId(ProjectTaskEntity projectTaskEntity) {
		ReturnJson returnJson = null;
		try {
			CompanyDetailVO companyDetailVO = projectCompanyService.queryDetailByProjectStructionId(projectTaskEntity);
			returnJson = ReturnJson.success(companyDetailVO);
		} catch (EscstException e) {
			logger.error("根据检查部位获取合作单位及班组信息出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return returnJson;
	}

	/**
	 * @desc 查询所有公司及公司下的班组
	 * @param entity
	 * @return
	 * @author zhouwei
	 * @date 2017年8月31日 下午5:56:02
	 */
	@RequestMapping("/queryCompanyDetail")
	@ResponseBody
	public ReturnJson queryCompanyDetail(@RequestBody ProjectTaskEntity entity) {
		ReturnJson returnJson = null;
		try {
			List<CompanyDetailVO> list = projectCompanyService.queryCompanyDetail(entity.getConstructionId());
			returnJson = ReturnJson.success(list);
		} catch (EscstException e) {
			logger.error("查询所有公司及公司下的班组出现异常：" + e.getMessage(), e);
			returnJson = ReturnJson.fail("查询所有公司及公司下的班组出现异常");
		}
		return returnJson;
	}


	/**
	* @desc 查询当前用户或者工地下的所属分包公司
	* @param entity
	* @return
	* @author dwj
	* @date 2018/5/21 10:03
	*/
	@RequestMapping("selectCompany")
	@ResponseBody
	public ReturnJson selectCompany(BaseAuthVO vo){
		ReturnJson returnJson = null;
		try{
			if(StringUtils.isBlank(vo.getConstructionId())){
				vo.setUserId(ContextUtils.getCurrentUserId());
			}
			List<BaseVO> baseVOS = projectCompanyService.selectCompanyList(vo);
			returnJson = ReturnJson.success(baseVOS);
		}catch (Exception e){
			logger.error("查询当前公司下的分包公司异常"+e.getMessage(),e);
			returnJson = ReturnJson.fail("系统异常");
		}
		return  returnJson;

	}
}
