package com.escst.environment.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.environment.vo.EnvironmentRealtimeVO;
import com.escst.equipment.vo.AcquisitionDataQueryVO;

/**
 * @desc 环境
 * @author zhouwei
 * @date 2017年8月17日 下午2:43:34
 */
@Repository
public interface EnvironmentMapper  {

	/**
	 * @desc 查询环境实时数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月17日 下午2:45:21
	 */
	List<EnvironmentRealtimeVO> queryRealtimeList(AcquisitionDataQueryVO queryVO);
	
	/**
	 * @desc 得到实时数据的总数
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月17日 下午6:15:19
	 */
	int getRealtimeCount(AcquisitionDataQueryVO queryVO);
	
	int selectTimeDiffByIds(Map<String,Object> map);
}
