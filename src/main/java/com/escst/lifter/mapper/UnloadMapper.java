package com.escst.lifter.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.vo.UnloadRealtimeVO;

/**
 * @desc
 * @author niejing
 * @date 2018年4月23日 下午2:30:51
 */
@Repository
public interface UnloadMapper {

	/**
	 * @desc 得到升降机的实时数据
	 * @param queryVO
	 * @return
	 * @author zhouwei
	 * @date 2017年8月21日 下午4:22:12
	 */
	List<UnloadRealtimeVO> queryRealtimeList(AcquisitionDataQueryVO queryVO);

	int getRealtimeCount(AcquisitionDataQueryVO queryVO);
}
