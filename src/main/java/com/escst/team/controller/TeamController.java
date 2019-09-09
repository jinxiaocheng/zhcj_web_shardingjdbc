package com.escst.team.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.team.bean.TeamBean;
import com.escst.team.entity.TeamEntity;
import com.escst.team.service.TeamService;
import com.escst.territory.service.TerritoryService;

/**
 * @desc 班组
 * @author zhouwei
 * @date 2017年4月27日 上午11:06:09
 */
@Controller
@RequestMapping("team")
public class TeamController {
	private static Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamService teamService;

	@Autowired
	public TerritoryService territoryService;

	@Autowired
	public ProjectCompanyService projectCompanyService;
	
	/**
	 * @desc 列表
	 * @return
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:06:41
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		return new ModelAndView("team/list");
	}

	/**
	 * 
	 * @desc 班组列表
	 * @param entity
	 * @return
	 * @author niejing
	 * @date 2017年7月13日 下午3:12:34
	 */
	@RequestMapping("listData")
	@ResponseBody
	public ReturnJson listData(TeamEntity entity) {
		ReturnJson returnJson = null;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			PageVo vo = teamService.listData(entity);
			returnJson = ReturnJson.success(vo);
		} catch (Exception e) {
			logger.error("查询班组列表异常", e.getMessage());
			returnJson = ReturnJson.fail("查询班组列表异常");
		}
		return returnJson;
	}

	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		return new ModelAndView("team/add");
	}

	/**
	 * 
	 * @desc 新增班组 
	 * @param bean
	 * @return 
	 * @author niejing
	 * @date 2017年7月13日 下午2:01:28
	 */
	@RequestMapping("add")
	@ResponseBody
    public ReturnJson add(TeamEntity entity) {
		ReturnJson returnJson = new ReturnJson();
		try{
			teamService.save(entity);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("新增班组异常", e);
			returnJson = ReturnJson.fail("新增班组异常");
		}
        return returnJson;
    }
	
	/**
	 * 
	 * @desc 跳转到新增界面 
	 * @param model
	 * @return 
	 * @author niejing
	 * @date 2018年1月29日 下午3:06:49
	 */
	@RequestMapping("toEdit")
	public ModelAndView toEdit(Model model, String id) {
		Map<String,Object> map = teamService.getMapById(id);
		model.addAttribute("id", id);
		model.addAttribute("map", map);
		return new ModelAndView("team/edit");
	}
	
	@RequestMapping("edit")
	@ResponseBody
    public ReturnJson edit(TeamEntity entity) {
		ReturnJson returnJson = new ReturnJson();
		try{
			teamService.update(entity);
			returnJson = ReturnJson.success();
		}catch(Exception e){
			logger.error("修改班组异常", e);
			returnJson = ReturnJson.fail("修改班组异常");
		}
        return returnJson;
	}
	
	/**
	 * 
	 * @desc 通过工地Id查询合作单位列表
	 * @param constructionId
	 * @return 
	 * @author niejing
	 * @date 2017年7月13日 下午3:28:56
	 */
	@RequestMapping("getProjectCompanyList")
	@ResponseBody
	public ReturnJson getProjectCompanyList(String constructionId){
		ReturnJson returnJson = null;
		try{
			List<Map<String,Object>> projectCompanyList = projectCompanyService.listByConstructionId(constructionId, ContextUtils.getCurrentUserId());
			returnJson = ReturnJson.success(projectCompanyList);
		}catch(Exception e){
			logger.error("通过工地ID查询合作单位信息异常",e);
			returnJson = ReturnJson.fail("通过工地ID查询合作单位信息异常");
		}
		return returnJson;
	}

	@RequestMapping("getTeamList")
	@ResponseBody
	public ReturnJson getTeamList(String projectCompanyId) {
		ReturnJson returnJson = null;
		try {
			List<Map<String, Object>> list = teamService.listByProjectCompanyId(projectCompanyId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			logger.error("根据公司id查询班组信息异常", e);
			returnJson = ReturnJson.fail("根据公司id查询班组信息异常");
		}
		return returnJson;
	}

	/**
	* @desc 根据工地ID得到班组信息
	* @return
	* @author dwj
	* @date 2017/10/30 14:40
	*/
	@RequestMapping("getDefaultTeamName")
	@ResponseBody
	public ReturnJson getDefaultTeamName(){
		ReturnJson returnJson = null;
		try{
			List<TeamEntity> list = teamService.queryDefaultTeamList();
			List<String> nameList = new ArrayList<String>();
			for (TeamEntity entity : list) {
				nameList.add(entity.getName());
			}
			returnJson = ReturnJson.success(nameList);
		}catch (Exception e){
			returnJson = ReturnJson.fail("根据工地id查询班组信息异常");
			logger.error("根据工地ID查询班组信息异常",e);
		}
		return returnJson;
	}

	/**
	 * @return
	 * @desc 得到默认的班组集合，包含班组名称和工种
	 * @author caozx
	 * @date 2017/10/31 14:38
	 */
	@RequestMapping("getDefaultTeamList")
	@ResponseBody
	public ReturnJson getDefaultTeamList(){
		ReturnJson returnJson = null;
		try{
			List<TeamEntity> list = teamService.queryDefaultTeamList();
			returnJson = ReturnJson.success(list);
		}catch (Exception e){
			returnJson = ReturnJson.fail("根据工地id查询班组信息异常");
			logger.error("根据工地ID查询班组信息异常",e);
		}
		return returnJson;
	}

	/**
	 * 批量创建班组
	 *
	 * @param teamBean 班组信息
	 * @return
	 * @author caozx
	 * @date 2017/10/31 14:40
	 */
	@RequestMapping("batchAddTeam")
	@ResponseBody
	public ReturnJson batchAddTeam(@RequestBody TeamBean teamBean) {
		ReturnJson returnJson;
		try {
			teamService.batchAddTeam(teamBean);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			if(e instanceof EscstException){
				returnJson = ReturnJson.fail(e.getMessage());	
			}else{
				returnJson = ReturnJson.fail("系统异常");
			}
			logger.error("创建班组异常:" + e.getMessage(), e);
		}
		return returnJson;
	}
}
