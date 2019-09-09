package com.escst.team.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.redis.annotation.RedisCache;
import com.escst.team.bean.TeamBean;
import com.escst.team.entity.TeamEntity;
import com.escst.team.mapper.TeamMapper;

/**
 * @desc
 * @author niejing
 * @date 2017年7月5日 上午10:17:30
 */
@Service
@Transactional
public class TeamService {

	@Autowired
	public TeamMapper teamMapper;

	@Autowired
	public ProjectCompanyService projectCompanyService;

	public List<Map<String, Object>> listByProjectCompanyId(String projectCompanyId) {
		List<Map<String, Object>> list = null;
		try {
			list = teamMapper.listByProjectCompanyId(projectCompanyId);
		} catch (Exception e) {
			throw new EscstException("根据公司id查询班组信息异常", e);
		}
		return list;
	}

	public PageVo listData(TeamEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			int count = teamMapper.selectCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = teamMapper.listTeam(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}

		} catch (Exception e) {
			throw new EscstException("查询班组信息失败" + e.getMessage(), e);
		}
		return pageVo;
	}

	public void save(TeamEntity entity) {
		try {
			entity.setId(UuidUtils.getUuid());
			entity.setCreateTime(new Date());
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			teamMapper.insert(entity);
		} catch (Exception e) {
			throw new EscstException("保存班组信息异常", e);
		}
	}

	public void update(TeamEntity entity) {
		try {
			
			entity.setUpdateBy(ContextUtils.getCurrentUserId());
			entity.setUpdateTime(new Date());
			teamMapper.update(entity);
		} catch (Exception e) {
			throw new EscstException("更新班组信息异常", e);
		}
	}

	/**
	 * @desc 查询班组信息
	 * @param params
	 * @return
	 * @author zhouwei
	 * @date 2017年10月24日 上午9:18:54
	 */
	@RedisCache
	public List<TeamEntity> queryTeamList(Map<String, Object> params) {
		return teamMapper.selectTeamList(params);
	}

	/**
	 * @desc 根据班组名称得到班组对象
	 * @param constructionId
	 * @param teamName
	 * @return
	 * @author zhouwei
	 * @date 2017年10月24日 上午9:38:33
	 */
	@RedisCache
	public TeamEntity getTeamByName(String constructionId, String teamName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("constructionId", constructionId);
		params.put("name", teamName);
		List<TeamEntity> list = queryTeamList(params);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @desc 查询默认的班组
	 * @return
	 * @author zhouwei
	 * @date 2017年10月31日 下午1:17:24
	 */
	@RedisCache
	public List<TeamEntity> queryDefaultTeamList() {
		return teamMapper.selectDefaultTeamList();
	}

	/**
	 * @param teamBean
	 * @return
	 * @desc 批量创建班组
	 * @author caozx
	 * @date 2017/10/31 14:28
	 */
	public void batchAddTeam(TeamBean teamBean) {
		List<TeamEntity> list = new ArrayList<TeamEntity>();
		String constructionId = teamBean.getConstructionId();
		String projectCompanyId = teamBean.getProjectCompanyId();
		String[] teams = teamBean.getTeamNames();
		if(teams.length<1){
			throw new EscstException("没有需要添加的班组信息");
		}
		for (int i=0;i<teams.length;i++) {
			TeamEntity entity = new TeamEntity();
			entity.setId(UuidUtils.getUuid());
			entity.setName(teams[i]);
			entity.setConstructionId(constructionId);
			entity.setProjectCompanyId(projectCompanyId);
			entity.setCreateTime(new Date());

			// 判断当前分包公司是否已经创建相同的班组
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("constructionId", constructionId);
			paramsMap.put("projectCompanyId", projectCompanyId);
			paramsMap.put("fullName", teams[i]);
			List<TeamEntity> teamList = queryTeamList(paramsMap);
			if (CollectionUtils.isEmpty(teamList)) {
				list.add(entity);
			}
		}
		try {
			if (CollectionUtils.isEmpty(list)) {
				throw new EscstException("不能重复创建班组！");
			}
			teamMapper.batchInsert(list);
		} catch (EscstException e) {
			throw e;
		} catch (Exception e) {
			throw new EscstException("批量创建班组异常：" + e.getMessage());
		}
	}

	/**
	 * @desc 根据班组ID得到班组信息
	 * @param id
	 * @return
	 * @author zhouwei
	 * @date 2017年11月2日 下午4:54:22
	 */
	@RedisCache
	public TeamEntity getById(String id) {
		return teamMapper.selectById(id);
	}

	public Map<String,Object> getMapById(String id){
		return teamMapper.selectMapById(id);
	}
	
	/**
	 * @desc 根据岗位/工种得到公司下面的班组,如果班组不存在,则新建该班组
	 * @param projectCompanyId
	 * @param positionWorkType
	 * @return
	 * @author zhouwei
	 * @date 2017年11月3日 下午12:53:30
	 */
	public TeamEntity getTeamByPositionWorkType(String projectCompanyId, Integer positionWorkType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCompanyId", projectCompanyId);
		params.put("positionWorkType", positionWorkType);
		List<TeamEntity> list = teamMapper.selectTeamList(params);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @desc 根据岗位/工种得到班组的名称
	 * @param positionWorkType
	 * @return
	 * @author zhouwei
	 * @date 2017年11月3日 下午1:06:48
	 */
	public String getDefaultTeamName(Integer positionWorkType) {
		TeamService service = (TeamService) AopContext.currentProxy();
		List<TeamEntity> list = service.queryDefaultTeamList();
		for (TeamEntity entity : list) {
			if (entity.getPositionWorkType() != null && entity.getPositionWorkType().equals(positionWorkType)) {
				return entity.getName();
			}
		}
		return "";
	}

	/**
	 * @desc 批量插入班组
	 * @param list
	 * @author zhouwei
	 * @date 2017年11月8日 下午9:12:37
	 */
	public void batchInsert(List<TeamEntity> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		teamMapper.batchInsert(list);
	}

	/**
	 * @desc 增加一个班组
	 * @param constructionId
	 * @param companyId
	 * @param positionWorkType
	 * @return
	 * @author zhouwei
	 * @date 2017年11月8日 下午9:27:05
	 */
	public String addTeam(String constructionId, String companyId, Integer positionWorkType) {
		TeamEntity teamEntity = new TeamEntity();
		String id = UuidUtils.getUuid();
		teamEntity.setId(id);
		teamEntity.setConstructionId(constructionId);
		teamEntity.setProjectCompanyId(companyId);
		teamEntity.setPositionWorkType(positionWorkType);
		String teamName = getDefaultTeamName(positionWorkType);
		teamEntity.setName(teamName);
		teamEntity.setCreateTime(new Date());
		teamEntity.setCreateBy(ContextUtils.getCurrentUserId());
		teamMapper.insert(teamEntity);
		return id;
	}


}