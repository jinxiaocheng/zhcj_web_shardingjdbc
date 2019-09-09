package com.escst.team.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.team.entity.TeamEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年7月5日 上午10:18:27
 */
@Repository
public interface TeamMapper extends BaseMapper<TeamEntity> {

	public List<Map<String, Object>> listByProjectCompanyId(String projectCompanyId);

	public List<Map<String, Object>> listTeam(TeamEntity entity);

	/**
	 * @desc 查询班组信息
	 * @param params
	 * @return
	 * @author zhouwei
	 * @date 2017年10月24日 上午9:07:26
	 */
	List<TeamEntity> selectTeamList(Map<String, Object> params);

	/**
	 * @desc 查询默认的班组
	 * @return
	 * @author zhouwei
	 * @date 2017年10月31日 下午1:17:24
	 */
	List<TeamEntity> selectDefaultTeamList();

	/**
	 * @param list
	 * @return
	 * @desc 批量新增班组
	 * @author caozx
	 * @date 2017/10/31 14:01
	 */
	void batchInsert(List<TeamEntity> list);

	TeamEntity selectById(String id);
	
	Map<String,Object> selectMapById(String id);


    void updatePositionWorkId(Map<String,Object> map);
}
