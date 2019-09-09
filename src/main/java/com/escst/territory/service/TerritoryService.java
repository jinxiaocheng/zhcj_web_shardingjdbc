package com.escst.territory.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.redis.annotation.RedisCache;
import com.escst.territory.entity.TerritoryEntity;
import com.escst.territory.mapper.TerritoryMapper;
import com.escst.territory.vo.AreaConstructionVO;
import com.escst.territory.vo.ConstructionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class TerritoryService {

	@Autowired
	private TerritoryMapper territoryMapper;

	@RedisCache
	public List<TerritoryEntity> queryTerritoryListByParentId(String parentId) {
		List<TerritoryEntity> listMap;
		try {
			listMap = territoryMapper.selectByParentId(parentId);
		} catch (Exception e) {
			throw new EscstException("根据父id查找区域信息异常", e);
		}
		return listMap;
	}

	@RedisCache
	public List<TreeEntity> queryByParentId(List<TreeEntity> list, String parentId) {
		if (list == null) {
			list = new ArrayList<TreeEntity>();
		}
		if (StringUtils.isBlank(parentId)) {
			parentId = "1";
		}
		List<TerritoryEntity> trritoryList = territoryMapper.selectByParentId(parentId);
		if (CollectionUtils.isEmpty(trritoryList)) {
			return list;
		}
		for (TerritoryEntity entity : trritoryList) {
			TreeEntity treeEntity = new TreeEntity();
			treeEntity.setpId(parentId);
			treeEntity.setId(entity.getId());		
			treeEntity.setName(entity.getName());
			treeEntity.setOpen(false);
			list.add(treeEntity);
			// 递归获取子节点
			queryByParentId(list, entity.getId());
		}
		return list;
	}
	
	/**
	 * @desc 根据区域ID获取区域对象
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月15日 下午4:19:29
	 */
	@RedisCache
	public TerritoryEntity selectById(String id) {
		return territoryMapper.selectById(id);
	}

	/**
	 * 查询有权限访问的区域和工地信息
	 * @param userId
	 * @return
	 */
	public List<AreaConstructionVO> queryAuthAreaByUserId(String userId) {
		List<AreaConstructionVO> list = null;
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId",userId);
			paramMap.put("status",1);
			list = queryAreaConstructionByLevel(paramMap);
		} catch (EscstException e) {
			throw new EscstException("查询有权限访问的区域和工地信息异常");
		}
		return list;
	}

	public List<TreeEntity> queryAreaTreeByUserId(String userId) {
		List<TreeEntity> list = null;
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId",userId);
			paramMap.put("status",1);
			list = queryAreaConstructionByLevel2(paramMap);
		} catch (EscstException e) {
			throw new EscstException("查询有权限访问的区域和工地信息异常");
		}
		return list;
	}
	
	private List<TreeEntity> queryAreaConstructionByLevel2(Map<String,Object> paramMap) {
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		List<Map<String,Object>> areaList = territoryMapper.selectAuthAreaByUserId(paramMap);
		if(!CollectionUtils.isEmpty(areaList)) {
			for(Map<String,Object> map : areaList) {
				String cityId = (String)map.get("cityId");
				String areaId = (String)map.get("areaId");
				String areaName = (String)map.get("areaName");
				
				TreeEntity entity = new TreeEntity();
				entity.setId(areaId);
				entity.setpId("0");
				entity.setName(areaName);
				entity.setOpen(true);
				paramMap.put("areaId",areaId);
				List<ConstructionVO> constructionVOS = territoryMapper.selectAuthConstructionByUserId(paramMap);
				for(ConstructionVO vo :constructionVOS){
					TreeEntity subTree = new TreeEntity();
					subTree.setId(vo.getConstructionId());
					subTree.setpId(areaId);
					subTree.setName(vo.getConstructionName());
					subTree.setOpen(false);
					list.add(subTree);
				}
				list.add(entity);
			}
		}
		return list;
	}
	
	/**
	 * 根据用户ID查询区域工地信息
	 * @param paramMap
	 * @return
	 */
	private List<AreaConstructionVO> queryAreaConstructionByLevel(Map<String,Object> paramMap) {
		List<AreaConstructionVO> list = new ArrayList<AreaConstructionVO>();
		List<Map<String,Object>> areaList = territoryMapper.selectAuthAreaByUserId(paramMap);
		if(!CollectionUtils.isEmpty(areaList)) {
			for(Map<String,Object> map : areaList) {
				AreaConstructionVO vo = new AreaConstructionVO();
				String cityId = (String)map.get("cityId");
				String areaId = (String)map.get("areaId");
				String areaName = (String)map.get("areaName");
				vo.setCityId(cityId);
				vo.setAreaId(areaId);
				vo.setAreaName(areaName);
				paramMap.put("areaId",areaId);
				List<ConstructionVO> constructionVOS = territoryMapper.selectAuthConstructionByUserId(paramMap);
				vo.setData(constructionVOS);
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 通过用户ID查询工地id集合
	 * @param userId
	 * @return
	 */
	public List<String> queryAuthConstructionIds(String userId) {
		List<String> list = null;
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userId",userId);
			paramMap.put("status",1);
			list = territoryMapper.selectAuthConstructionIds(paramMap);
		} catch (EscstException e) {
			throw new EscstException("查询有权限访问工地集合信息异常");
		}
		return list;
	}

	/**
	 * @desc 查询所有的省、市、区
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 上午8:59:04
	 */
	public List<TerritoryEntity> queryAllList() {
		return territoryMapper.queryAllList();
	}
	
	/**
	 * @desc 得到省、市、区的id及对象的map
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 上午9:05:07
	 */
	@RedisCache
	public Map<String, TerritoryEntity> getTerritoryMap() {
		List<TerritoryEntity> list = queryAllList();
		Map<String, TerritoryEntity> map = new HashMap<String, TerritoryEntity>();
		for (TerritoryEntity entity : list) {
			map.put(entity.getId(), entity);
		}
		return map;
	}
}
