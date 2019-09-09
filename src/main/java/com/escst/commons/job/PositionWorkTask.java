package com.escst.commons.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.escst.commons.utils.UuidUtils;
import com.escst.person.mapper.PersonMapper;
import com.escst.positionWork.entity.PositionWorkEntity;
import com.escst.positionWork.mapper.PositionWorkMapper;
import com.escst.team.mapper.TeamMapper;

/**
 * 
 * @desc 从人员表里提取工地对应的岗位工种数据，保存到岗位工种表里
 * @author niejing
 * @date 2018年4月25日 下午3:17:21
 */
@Component
public class PositionWorkTask {
	private static Logger logger = LoggerFactory.getLogger(PositionWorkTask.class);
	@Autowired
	public PersonMapper personMapper;

	@Autowired
	public PositionWorkMapper positionWorkMapper;

	@Autowired
	public TeamMapper teamMapper;
	
	
	public void execute() {
		try{
			// 查询所有的工地对应的人员的岗位工种
			List<Map<String, Object>> list = personMapper.getConstructionInfoAll();
			
			List<PositionWorkEntity> positionWorkList = new ArrayList<PositionWorkEntity>();
			for (Map<String, Object> map : list) {
				if(map.get("constructionId")==null){
					continue;
				}
				String constructionId = map.get("constructionId").toString();
				if (StringUtils.isBlank(constructionId)) {
					continue;
				}
				String personId = map.get("id").toString();
				int description = Integer.parseInt(map.get("description").toString());
				String name = map.get("name").toString();
				String constructionName = map.get("constructionName").toString();
				String positionWorktype = map.get("positionWorktype").toString();
				
				PositionWorkEntity entity = new PositionWorkEntity();
				String id = UuidUtils.getUuid();
				entity.setId(id);
				entity.setConstructionId(constructionId);
				entity.setConstructionName(constructionName);
				entity.setType(description);
				entity.setName(name);
				entity.setCreateTime(new Date());
//			entity.setPositionWorktype(positionWorktype);
				positionWorkList.add(entity);
				
				Map<String,Object> mapc = new HashMap<String,Object>();
				mapc.put("positionWorkId", id);
				mapc.put("constructionId", constructionId);
				mapc.put("positionWorkType", positionWorktype);
				personMapper.updatePositionWorkId(mapc);
				
				teamMapper.updatePositionWorkId(mapc);
			}
			//批量新增岗位工种数据
			positionWorkMapper.batchInsert(positionWorkList);
		} catch (Exception e) {
			logger.error("定时任务执行异常:{}",e);
		}

	}
}
