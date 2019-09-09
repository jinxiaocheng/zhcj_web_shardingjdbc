package com.escst.lifter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.commons.vo.PageVo;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.mapper.UnloadMapper;
import com.escst.lifter.vo.UnloadRealtimeVO;

/**
 * @desc 卸料平台服务类
 * @author niejing
 * @date 2018年4月23日 下午2:30:02
 */
@Service
public class UnloadService {

	@Autowired
	private UnloadMapper unloadMapper;

	/**
	 * 
	 * @desc 查询卸料平台的实时数据 
	 * @param queryVO
	 * @return 
	 * @author niejing
	 * @date 2018年4月23日 下午3:11:27
	 */
	public PageVo queryRealtimeList(AcquisitionDataQueryVO queryVO) {
		int totalQty = unloadMapper.getRealtimeCount(queryVO);
		List<UnloadRealtimeVO> list = null;
		if (totalQty > 0) {
			list = unloadMapper.queryRealtimeList(queryVO);
		}
		PageVo rst = new PageVo();
		rst.setCurrentPage(queryVO.getPage());
		rst.setPageSize(queryVO.getRowNum());
		rst.setTotalRecord(totalQty);
		rst.setRows(list);
		return rst;
	}
}
