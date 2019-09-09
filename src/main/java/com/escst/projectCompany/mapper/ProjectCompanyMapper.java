package com.escst.projectCompany.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.entity.BaseEntity;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.BaseVO;
import org.apache.ibatis.annotations.Param;

import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.task.entity.ProjectTaskEntity;
import org.springframework.stereotype.Repository;

/**
 * @author dwj
 * @desc
 * @date 9:43 2017/4/11
 */
@Repository
public interface ProjectCompanyMapper{

    public List<Map<String, Object>> selectList(ProjectCompanyEntity projectCompanyEntity);

    public int selectCount(ProjectCompanyEntity projectCompanyEntity);

    public int insert(ProjectCompanyEntity projectCompany);

    public int update(ProjectCompanyEntity projectCompany);
    
    public List<Map<String,Object>> listByConstructionId(@Param("constructionId") String constructionId, @Param("userId") String userId);

    public List<Map<String, Object>> queryByProjectStructionId(ProjectTaskEntity projectTaskEntity);
    
    /**
     * @desc 查询公司的基本信息
     * @param params
     * @return 
     * @author zhouwei
     * @date 2017年10月24日 上午8:46:39
     */
    List<ProjectCompanyEntity> selectCompanyList(Map<String, Object> params);

    /**
     * @desc 查询所有公司及公司下的班组
     * @param constructionId
     * @return
     * @author zhouwei
     * @date 2017年8月31日 下午6:06:43
     */
    List<Map<String, Object>> queryCompanyDetail(String constructionId);
    
    ProjectCompanyEntity selectById(String id);

    List<Map<String,Object>> selectDetailInfoById(String id);

	/**
	 * @desc 批量插入公司
	 * @param list 
	 * @author zhouwei
	 * @date 2017年11月8日 下午9:08:12
	 */
	void batchInsert(List<ProjectCompanyEntity> list);

	/**
	* @desc 查询工地下的分包公司
	* @param vo
	* @return
	* @author dwj
	* @date 2018/5/21 9:56
	*/
    List<BaseVO> selectCompanyByUserId(BaseAuthVO vo);
}
