package com.escst.organization.mapper;

import java.util.List;
import java.util.Map;

import com.escst.role.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.organization.entity.OrgEntity;

@Repository
public interface OrgMapper extends BaseMapper<OrgEntity> {

	/**
	 * 通过父id查找子机构
	 * @param parentId
	 * @return
	 */
	List<OrgEntity> selectByParentId(String parentId);

	/**
	 * 通过父id查找子机构（查出自己）
	 * @param orgEntity
	 * @return
	 */
	List<OrgEntity> selectBypId(OrgEntity orgEntity);
	
	/**
	 * 通过父id查找子机构数量
	 * @param parentId
	 * @return
	 */
	public int selectByParentIdCount(String parentId);
	
	/**
	 * 查询第一级机构
	 * @return
	 */
	public List<OrgEntity> selectLevelOne(@Param("id") String id);
	
	/**
	 * 根据工地iD查询机构信息
	 * @param constId
	 * @return
	 */
	OrgEntity selectByconstructionId(String constId);
	
	/**
	 * 
	 * @desc 批量插入机构数据 
	 * @param list 
	 * @author niejing
	 * @date 2017年4月27日 下午3:09:54
	 */
	public void insertOrgBatch(List<OrgEntity> list);
	
	public String selectMaxSortNum(String parentId);
	
	public String getSysCodeByLevel(int level);
	
	/**
	 * @desc 根据父节点ID得到父节点编码以及子节点数量
	 * @param parentId
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月1日 下午3:11:05
	 */
	Map<String, Object> getSysCodeAndChildrenNum(String parentId);
	
	List<String> selectParentIdByArea(String areaId);
	
	int checkIsExsit(@Param("parentId") String parentId,@Param("constructionId") String constructionId);
	
	Map<String, Object> selectMapById(String id);
	
	/**
	 * 
	 * @desc 根据用户ID查询组织信息 
	 * @param userId
	 * @return 
	 * @author niejing
	 * @date 2017年10月26日 下午3:00:56
	 */
	List<OrgEntity> selectOrgByUserId(String userId);
	
	/**
	 * @desc 根据工地ID得到机构集合
	 * @param constructionId
	 * @return 
	 * @author zhouwei
	 * @date 2017年10月27日 下午12:49:21
	 */
	List<OrgEntity> selectByConstructionId(String constructionId);
	
	/**
	 * @desc 根据sysCode的得到父节点
	 * @param sysCode
	 * @return 
	 * @author zhouwei
	 * @date 2017年12月13日 下午6:24:00
	 */
	List<OrgEntity> selectParentBySysCode(String sysCode);

	List<RoleEntity> queryRoleByOrgId(String orgId);

	String getParentOrgId(String orgId);
}
