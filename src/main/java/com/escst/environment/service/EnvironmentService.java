package com.escst.environment.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.environment.mapper.EnvironmentMapper;
import com.escst.environment.vo.EnvironmentRealtimeVO;
import com.escst.equipment.vo.AcquisitionDataQueryVO;

/**
 * @desc 环境的业务处理类
 * @author zhouwei
 * @date 2017年8月17日 下午2:31:17
 */
@Service
@Transactional
public class EnvironmentService {

	@Autowired
	private EnvironmentMapper environmentMapper;
	
	/**
	 * @desc 查询环境实时数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月17日 下午2:45:21
	 */
	public PageVo queryRealtimeListPage(AcquisitionDataQueryVO queryVO) {
		int totalQty = environmentMapper.getRealtimeCount(queryVO);
		List<EnvironmentRealtimeVO> list = null;
		if (totalQty > 0) {
			list = environmentMapper.queryRealtimeList(queryVO);
		}
		PageVo rst = new PageVo();
		rst.setCurrentPage(queryVO.getPage());
		rst.setPageSize(queryVO.getRowNum());
		rst.setTotalRecord(totalQty);
		rst.setRows(list);
		return rst;
	}
	
	/**
	 * @desc 查询环境的实时数据
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月27日 上午11:29:43
	 */
	public List<EnvironmentRealtimeVO> queryRealtimeList(AcquisitionDataQueryVO queryVO) {
		return environmentMapper.queryRealtimeList(queryVO);
	}
	
	/**
	 * 
	 * @desc 根据工地id和设备id查询该工地是否在线
	 * @param constructionId
	 * @param equipmentId
	 * @return
	 * @author niejing
	 * @date 2018年2月27日 上午10:10:29
	 */
	public boolean isOline(String constructionId, String equipmentId) {
		boolean boo = false;
		// 获取最新一条数据
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("constructionId", constructionId);
		map.put("equipmentId", equipmentId);
		map.put("createTime", DateUtils.getXminutesAgoDate(5));
		int count = environmentMapper.selectTimeDiffByIds(map);
		if(count == 0){
			return boo;
		} else {
			boo = true;
		}
		return boo;
	}

	/**
	 * 
	 * @desc 
	 * @param createTime
	 * @return 
	 * @author niejing
	 * @date 2018年2月27日 上午10:27:28
	 */
	public long diffMinute(String createTime) {
		long minute = 0L;
		try {
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			long nowTime = ca.getTimeInMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long entTime = sdf.parse(createTime).getTime();
			minute = (nowTime - entTime) / (1000 * 60);
		} catch (Exception e) {
			throw new EscstException("获取间隔时间异常");
		}
		return minute;
	}
}
