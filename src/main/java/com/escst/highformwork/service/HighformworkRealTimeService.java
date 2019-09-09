package com.escst.highformwork.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.highformwork.bean.RealTimeRequestBean;
import com.escst.highformwork.entity.HighformworkThresholdValueEntity;
import com.escst.highformwork.mapper.HighformworkRealTimeMapper;
import com.escst.highformwork.vo.ConstructionFlowVo;
import com.escst.highformwork.vo.EquipmentVo;
import com.escst.highformwork.vo.HighformworkFlowVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import com.escst.highformwork.vo.RealTimeVo;
import com.escst.highformwork.vo.TrendRequestVo;
import com.escst.highformwork.vo.TrendResultVo;
import com.escst.highformwork.vo.TrendVo;

/**
 * @desc
 * @author niejing
 * @date 2018年7月17日 下午4:36:26
 */
@Service
public class HighformworkRealTimeService {

	@Autowired
	HighformworkRealTimeMapper HighformworkRealTimeMapper;

	@Autowired
	private FileService fileService;

	private static final int WARNING = 2;
	private static final int ALARM = 3;
	private static final int CONTROL = 4;

	/**
	 * 
	 * @desc 根据工地id查询实时数据
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年7月17日 下午5:12:10
	 */
	public List<RealTimeVo> queryRealTimeDataList(String constructionId) {
		List<RealTimeVo> list = new ArrayList<RealTimeVo>();

		Map<HighformworkFlowVo, List<HighformworkRealTimeVo>> map = queryFlowList(constructionId);
		for (Map.Entry<HighformworkFlowVo, List<HighformworkRealTimeVo>> entry : map.entrySet()) {
			RealTimeVo vo = new RealTimeVo();
			HighformworkFlowVo flowVo = entry.getKey();

			List<HighformworkRealTimeVo> warningVo = getAlarmList(WARNING, flowVo.getFlowId());
			List<HighformworkRealTimeVo> alarmVo = getAlarmList(ALARM, flowVo.getFlowId());
			List<HighformworkRealTimeVo> controlVo = getAlarmList(CONTROL, flowVo.getFlowId());
			flowVo.setWarning(warningVo.size());
			flowVo.setAlarm(alarmVo.size());
			flowVo.setControl(controlVo.size());

			vo.setFlowVo(flowVo);
			vo.setList(entry.getValue());
			list.add(vo);
		}
		return list;

	}

	/**
	 * 
	 * @desc 根据工地id查询实时数据
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年7月17日 下午5:12:10
	 */
	private Map<HighformworkFlowVo, List<HighformworkRealTimeVo>> queryFlowList(String constructionId) {
		Map<HighformworkFlowVo, List<HighformworkRealTimeVo>> map = new HashMap<HighformworkFlowVo, List<HighformworkRealTimeVo>>();
		// Map<String, HighformworkFlowVo> fMap = new HashMap<String,
		// HighformworkFlowVo>();
		List<HighformworkRealTimeVo> voList = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("constructionId", constructionId);
			paramMap.put("createTime", DateUtils.getTodayStart());
			voList = HighformworkRealTimeMapper.getRealTimeDataByConstructionId(paramMap);
		} catch (Exception e) {
			throw new EscstException("根据工地ID查询实时数据异常", e);
		}
		for (HighformworkRealTimeVo vo : voList) {
			// int warning = 0;
			// // 报警
			// int alarm = 0;
			// // 控制
			// int control = 0;

			List<Integer> typeList = new ArrayList<Integer>();
			typeList.add(vo.getxType());
			typeList.add(vo.getyType());
			typeList.add(vo.getKpaType());
			typeList.add(vo.getDisplaceType());
			typeList.add(vo.getTemperatureType());
			typeList.add(vo.getElectricType());

			if (typeList.contains(2)) {
				vo.setWarning(WARNING);
				// warning++;
			}
			if (typeList.contains(3)) {
				vo.setAlarm(ALARM);
				// alarm++;
			}
			if (typeList.contains(4)) {
				vo.setControl(CONTROL);
				// control++;
			}

			HighformworkFlowVo flowVo = new HighformworkFlowVo();
			flowVo.setFlowId(vo.getFlowId());
			flowVo.setConstructionName(vo.getConstructionName());
			flowVo.setFlowName(vo.getFlowName());

			// if (fMap.containsKey(vo.getFlowId())) {
			// HighformworkFlowVo fvo = fMap.get(vo.getFlowId());
			// fvo.setWarning(fvo.getWarning() + warning);
			// fvo.setAlarm(fvo.getAlarm() + alarm);
			// fvo.setControl(fvo.getControl() + control);
			// } else {
			// HighformworkFlowVo fvo = new HighformworkFlowVo();
			// fvo.setFlowId(vo.getFlowId());
			// fvo.setConstructionName(vo.getConstructionName());
			// fvo.setFlowName(vo.getFlowName());
			// fvo.setWarning(warning);
			// fvo.setAlarm(alarm);
			// fvo.setControl(control);

			// fMap.put(vo.getFlowId(), fvo);
			// }

			if (map.containsKey(flowVo)) {
				List<HighformworkRealTimeVo> list = map.get(flowVo);
				list.add(vo);
			} else {
				List<HighformworkRealTimeVo> list = new ArrayList<HighformworkRealTimeVo>();
				list.add(vo);
				map.put(flowVo, list);
			}
		}
		return map;
	}

	/**
	 * 
	 * @desc 根据设备id查询报警阈值
	 * @param equipmentId
	 * @return
	 * @author niejing
	 * @date 2018年7月17日 下午5:11:52
	 */
	public List<HighformworkThresholdValueEntity> queryAlarmInfo(String equipmentId) {
		List<HighformworkThresholdValueEntity> list = new ArrayList<HighformworkThresholdValueEntity>();
		try {
			list = HighformworkRealTimeMapper.getAlarmInfo(equipmentId);
		} catch (Exception e) {
			throw new EscstException("查询报警阈值异常", e);
		}
		return list;
	}

	/**
	 * 
	 * @desc
	 * @param type
	 * @param flowId
	 * @return
	 * @author niejing
	 * @date 2018年7月20日 上午9:02:13
	 */
	public List<HighformworkRealTimeVo> alarmDetail(int type, String flowId) {
		List<HighformworkRealTimeVo> list = new ArrayList<HighformworkRealTimeVo>();
		try {
			list = getAlarmList(type, flowId);
			for (HighformworkRealTimeVo entity : list) {
				String path = fileService.queryFilePathById(entity.getFileId());
				entity.setPath(path);
			}
		} catch (Exception e) {
			throw new EscstException("查询告警详情异常", e);
		}
		return list;
	}

	private List<HighformworkRealTimeVo> getAlarmList(int type, String flowId) {
		List<HighformworkRealTimeVo> list = new ArrayList<HighformworkRealTimeVo>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("flowId", flowId);
			map.put("createTime", DateUtils.getTodayStart());
			list = HighformworkRealTimeMapper.selectAlarmDetail(map);
		} catch (Exception e) {
			throw new EscstException("查询告警详情异常", e);
		}
		return list;
	}

	/**
	 * 
	 * @desc 查询趋势图
	 * @param vo
	 * @return
	 * @author niejing
	 * @date 2018年7月20日 下午2:25:14
	 */
	public List<TrendVo> queryTrend(TrendRequestVo requestVo) {
		List<TrendVo> trendVoList = new ArrayList<TrendVo>();
		Map<String, List<TrendResultVo>> map = new TreeMap<String, List<TrendResultVo>>();
		try {
			List<EquipmentVo> equipmentList = HighformworkRealTimeMapper.selectEquipment(requestVo);
			List<TrendResultVo> list = HighformworkRealTimeMapper.selectTrend(requestVo);
			for (TrendResultVo vo : list) {
				if (map.containsKey(vo.getAcquisitionTime())) {
					List<TrendResultVo> volist = map.get(vo.getAcquisitionTime());
					volist.add(vo);
				} else {
					List<TrendResultVo> volist = new ArrayList<TrendResultVo>();
					volist.add(vo);
					map.put(vo.getAcquisitionTime(), volist);
				}
			}

			for (Map.Entry<String, List<TrendResultVo>> entry : map.entrySet()) {
				TrendVo vo = new TrendVo();
				vo.setCreateTime(entry.getKey());
				List<TrendResultVo> value = entry.getValue();
				List<TrendResultVo> newValue = new ArrayList<TrendResultVo>();

				// 如果实时数据中的设备数量和设备表中的数据不一致则补齐该时间段其他设备数据
				if (value.size() != equipmentList.size()) {
					List<String> idList = new ArrayList<String>();
					for (TrendResultVo resultVo : value) {
						idList.add(resultVo.getEquipmentId());
					}

					for (EquipmentVo equipmentVo : equipmentList) {
						if (!idList.contains(equipmentVo.getId())) {
							TrendResultVo reslutVo = new TrendResultVo();
							reslutVo.setEquipmentId(equipmentVo.getId());
							reslutVo.setEquipmentName(equipmentVo.getName());
							newValue.add(reslutVo);
						}

					}
				}
				if (!CollectionUtils.isEmpty(newValue)) {
					value.addAll(newValue);
				}
				vo.setList(value);
				trendVoList.add(vo);
			}
		} catch (Exception e) {
			throw new EscstException("查询趋势图异常", e);
		}
		return trendVoList;
	}

	/**
	 * 
	 * @desc 根据工地di查询工地流水段信息
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年7月20日 下午5:21:35
	 */
	public List<ConstructionFlowVo> selectConstructionFlowList(String constructionId) {
		List<ConstructionFlowVo> list = new ArrayList<ConstructionFlowVo>();
		try {
			list = HighformworkRealTimeMapper.selectConstructionFlowList(constructionId);
		} catch (Exception e) {

			throw new EscstException("根据工地di查询工地流水段信息异常", e);
		}
		return list;
	}

	public PageVo queryByFlowId(RealTimeRequestBean bean) {
		PageVo pageVo = new PageVo();
		try {
			// 根据工地id查询人员总记录数
			int count = HighformworkRealTimeMapper.selectCount(bean);
//			// 当前页号
//			pageVo.setCurrentPage(bean.getPage());
//			// 总记录数
//			pageVo.setTotalRecord(count);
//
//			pageVo.setPageSize(bean.getRowNum());
			// 每页的第一条记录的索引
			int startIndex = (bean.getPage() - 1) * (bean.getRowNum());
			bean.setStartIndex(startIndex);
			List<HighformworkRealTimeVo> list = null;

			if (count > 0) {
				list = HighformworkRealTimeMapper.selectByFlowId(bean);
			}

			pageVo.setCount(count);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询人员列表信息失败！", e);
		}
		return pageVo;
	}

}
