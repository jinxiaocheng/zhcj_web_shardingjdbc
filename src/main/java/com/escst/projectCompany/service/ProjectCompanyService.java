package com.escst.projectCompany.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escst.commons.entity.BaseEntity;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.projectCompany.bean.CompanyDetailVO;
import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.projectCompany.mapper.ProjectCompanyMapper;
import com.escst.redis.annotation.RedisCache;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.task.entity.ProjectTaskProcessEntity;
import com.escst.team.bean.TeamLeaderVO;

/**
 * @author dwj
 * @desc
 * @date 9:47 2017/4/11
 */
@Service
@Transactional
public class ProjectCompanyService {

	@Autowired
	private ProjectCompanyMapper projectCompanyMapper;

	/**
	 * 查询分包公司列表信息
	 */
	public PageVo queryProjectCompanyList(ProjectCompanyEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			int count = projectCompanyMapper.selectCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = projectCompanyMapper.selectList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}

		} catch (Exception e) {
			throw new EscstException("查询分包公司信息失败" + e.getMessage(), e);
		}
		return pageVo;
	}

	/**
	 * 添加分包公司信息
	 */
	public String addProjectCompany(ProjectCompanyEntity entity) {
		String id = entity.getId();
		if (StringUtils.isEmpty(id)) {
			id = UuidUtils.getUuid();
			entity.setId(id);
			entity.setCreateTime(new Date());
			projectCompanyMapper.insert(entity);
		} else {
			entity.setUpdateTime(new Date());
			projectCompanyMapper.update(entity);
		}
		return id;
	}

	/**
	 * 通过工地ID查询分包公司列表
	 * @param constructionId
	 * @return
	 */
	public List<Map<String, Object>> listByConstructionId(String constructionId, String userId) {
		List<Map<String, Object>> list = null;
		try {
			list = projectCompanyMapper.listByConstructionId(constructionId,userId);
		} catch (Exception e) {
			throw new EscstException("根据工地ID查询分包公司信息异常" + e.getMessage(), e);
		}
		return list;
	}

	/**
	 * @desc 通过检查部位获取合作单位及班组信息
	 * @param entity
	 * @return
	 * @author zhouwei
	 * @date 2017年7月13日 上午8:54:46
	 */
	public CompanyDetailVO queryDetailByProjectStructionId(ProjectTaskEntity entity) {
		CompanyDetailVO companyDetailVO = new CompanyDetailVO();
		Map<String,Object> map = queryByProjectStructionId(entity);
		if (map == null) {
			return companyDetailVO;
		}
		String id = (String)map.get("projectCompanyId");
		String name = (String)map.get("projectCompanyName");
		int type = (Integer) map.get("projectCompanyType");
		companyDetailVO.setId(id);
		companyDetailVO.setName(name);
		companyDetailVO.setType(type);
		TeamLeaderVO teamLeader = new TeamLeaderVO();
		teamLeader.setId((String)map.get("teamId"));
		teamLeader.setName((String)map.get("teamName"));
		teamLeader.setUserId((String)map.get("userId"));
		teamLeader.setUserName((String)map.get("personName"));
		teamLeader.setMobile((String)map.get("personMobile"));
		teamLeader.setCompanyId(id);
		List<TeamLeaderVO> list = new ArrayList<TeamLeaderVO>();
		list.add(teamLeader);
		companyDetailVO.setData(list);
		return companyDetailVO;
	}

	/**
	 * 通过检查部位查询分包公司信息
	 * @param projectTaskEntity
	 * @return
	 */
	public Map<String,Object> queryByProjectStructionId(ProjectTaskEntity projectTaskEntity) {
		Map<String,Object> map = null;
		try {
			projectTaskEntity.setStatus(ProjectTaskProcessEntity.TASK_STATUS_FINISHED);
			List<Map<String,Object>> list = projectCompanyMapper.queryByProjectStructionId(projectTaskEntity);
			if(!CollectionUtils.isEmpty(list)) {
				map = list.get(0);
			}
		} catch (EscstException e) {
			throw new EscstException("通过检查部位查询分包公司信息出现异常：" + e.getMessage(), e);
		}
		return map;
	}

	/**
	 * @desc 根据工地ID和公司名称得到公司
	 * @param constructionId
	 * @param companyName
	 * @return 
	 * @author zhouwei
	 * @date 2017年10月24日 上午8:43:19
	 */
	public ProjectCompanyEntity getCompanyIdByName(String constructionId, String companyName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("constructionId", constructionId);
		params.put("fullName", companyName);
		List<ProjectCompanyEntity> list = projectCompanyMapper.selectCompanyList(params);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * @desc 通过公司ID得到公司对象
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月2日 下午4:49:07
	 */
	@RedisCache
	public ProjectCompanyEntity getById(String id) {
		return projectCompanyMapper.selectById(id);
	}

	/**
	 * @param id
	 * @return
	 * @desc
	 * @author caozx
	 * @date 2017/11/3 20:19
	 */
	public Map<String,Object> queryDetailInfo(String id) {
		List<Map<String,Object>> list = projectCompanyMapper.selectDetailInfoById(id);
		if (CollectionUtils.isEmpty(list)) {
			throw new EscstException("合作单位不存在!");
		}
		Map<String,Object> map = list.get(0);
		return map;
	}

	/**
	 * @desc 查询所有公司及公司下的班组
	 * @param constructionId
	 * @return
	 * @author zhouwei
	 * @date 2017年8月31日 下午6:06:43
	 */
	public List<CompanyDetailVO> queryCompanyDetail(String constructionId) {
		List<CompanyDetailVO> rst = new ArrayList<CompanyDetailVO>();
		List<Map<String, Object>> list = projectCompanyMapper.queryCompanyDetail(constructionId);
		if (CollectionUtils.isEmpty(list)) {
			return rst;
		}
		Map<String, List<TeamLeaderVO>> teamsMap = new HashMap<String, List<TeamLeaderVO>>();
		for (Map<String, Object> map : list) {
			String companyId = (String)map.get("id");
			String companyName = (String)map.get("name");
			int companyType = Integer.parseInt(map.get("type").toString());
			String teamId = (String)map.get("teamId");
			String teamName = (String)map.get("teamName");
			if (teamsMap.containsKey(companyId) && !StringUtils.isEmpty(teamId)) {
				List<TeamLeaderVO> data = teamsMap.get(companyId);
				TeamLeaderVO teamVO = new TeamLeaderVO();
				teamVO.setId(teamId);
				teamVO.setName(teamName);
				data.add(teamVO);
			}
			else {
				CompanyDetailVO companyDetail = new CompanyDetailVO();
				companyDetail.setId(companyId);
				companyDetail.setName(companyName);
				companyDetail.setType(companyType);

				if (!StringUtils.isEmpty(teamId)) {
					List<TeamLeaderVO> data = new ArrayList<TeamLeaderVO>();
					TeamLeaderVO teamVO = new TeamLeaderVO();
					teamVO.setId(teamId);
					teamVO.setName(teamName);
					data.add(teamVO);

					companyDetail.setData(data);
					teamsMap.put(companyId, data);
				}
				rst.add(companyDetail);
			}
		}
		return rst;
	}
	
	/**
	 * @desc 批量插入公司
	 * @param list 
	 * @author zhouwei
	 * @date 2017年11月8日 下午9:08:12
	 */
	public void batchInsert(List<ProjectCompanyEntity> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		projectCompanyMapper.batchInsert(list);
	}



	public List<BaseVO> selectCompanyList(BaseAuthVO vo){
		List<BaseVO> list = new ArrayList<BaseVO>();
		try{
			list = projectCompanyMapper.selectCompanyByUserId(vo);
		}catch (Exception e){
			throw new EscstException("查询当前工地下的分包公司异常"+e.getMessage(),e);
		}
		return list;
	}
}
