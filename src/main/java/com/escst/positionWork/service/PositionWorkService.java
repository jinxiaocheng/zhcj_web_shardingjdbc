package com.escst.positionWork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.service.ConstructionService;
import com.escst.dictionary.entity.DictionaryEntity;
import com.escst.dictionary.service.DictionaryService;
import com.escst.positionWork.entity.PositionWorkEntity;
import com.escst.positionWork.mapper.PositionWorkMapper;
import com.escst.positionWork.vo.PositionWorkVo;

/**
 * @desc
 * @author niejing
 * @date 2018年4月26日 下午1:23:21
 */
@Service
public class PositionWorkService {

	@Autowired
	private PositionWorkMapper positionWorkMapper;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private ConstructionService constructionService;

	// 岗位工种type
	private static final String TYPE = "positionWork";

	public PageVo list(PositionWorkEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			// 根据工地id查询人员总记录数
			int count = positionWorkMapper.selectCount(entity);
			// 当前页号
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);

			pageVo.setPageSize(entity.getRowNum());
			// 每页的第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = null;
			if (count > 0) {
				list = positionWorkMapper.selectMapList(entity);
			}

			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询岗位工种列表信息失败！", e);
		}
		return pageVo;
	}

	/**
	 * 
	 * @desc 新增岗位工种
	 * @param entity
	 * @author niejing
	 * @date 2018年4月27日 下午1:24:43
	 */
	public void add(PositionWorkEntity entity) {
		validate(entity);
		try {
			entity.setId(UuidUtils.getUuid());
			entity.setCreateTime(new Date());
			entity.setCreateBy(ContextUtils.getCurrentUserId());
			positionWorkMapper.insert(entity);
		} catch (Exception e) {
			throw new EscstException("新增岗位工种异常", e);
		}
	}

	/**
	 * 
	 * @desc 修改岗位工种
	 * @author niejing
	 * @date 2018年4月27日 下午1:25:27
	 */
	public void edit(PositionWorkEntity entity) {
		validate(entity);
		try {
			entity.setUpdateBy(ContextUtils.getCurrentUserId());
			entity.setUpdateTime(new Date());
			positionWorkMapper.update(entity);
		} catch (Exception e) {
			throw new EscstException("新增岗位工种异常", e);
		}
	}

	private void validate(PositionWorkEntity entity) {
		// 名称校验
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("constructionId", entity.getConstructionId());
		map.put("name", entity.getName());
		int count = positionWorkMapper.selectByName(map);
		if (count != 0) {
			throw new EscstException(entity.getName() + "名称重复!");
		}
	}

	/**
	 * 
	 * @desc 根据工地id查询岗位工种
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年4月28日 下午2:54:39
	 */
	public List<PositionWorkEntity> queryByConstructionId(PositionWorkVo vo) {
		List<PositionWorkEntity> list = new ArrayList<PositionWorkEntity>();
		try {
			list = positionWorkMapper.selectByConstructionId(vo);
		} catch (Exception e) {
			throw new EscstException("根据工地ID查询岗位工种异常", e);
		}
		return list;
	}

	public void initData(String constructionId) {
		List<PositionWorkEntity> list = new ArrayList<PositionWorkEntity>();
		try {
			Map<String, Object> map = constructionService.queryConstructionById(constructionId);
			String name = map.get("name").toString();

			List<DictionaryEntity> dicList = dictionaryService.queryByType(TYPE);
			for (DictionaryEntity dicEntity : dicList) {
				PositionWorkEntity entity = new PositionWorkEntity();
				entity.setId(UuidUtils.getUuid());
				entity.setConstructionId(constructionId);
				entity.setConstructionName(name);
				entity.setName(dicEntity.getName());
				entity.setType(Integer.parseInt(dicEntity.getDescription()));
				entity.setCreateTime(new Date());

				list.add(entity);
			}
			// 根据id查询是否存在已有的岗位工种
			PositionWorkVo vo = new PositionWorkVo();
			vo.setConstructionId(constructionId);
			List<PositionWorkEntity> existList = positionWorkMapper.selectByConstructionId(vo);
			// 如果有存在的岗位工种，去掉相同的数据
			if (!CollectionUtils.isEmpty(existList)) {
				list.removeAll(existList);
			}
			if(!CollectionUtils.isEmpty(list)){
				positionWorkMapper.batchInsert(list);
			}
		} catch (Exception e) {
			throw new EscstException("批量插入岗位工种数据异常", e);
		}
	}
}
