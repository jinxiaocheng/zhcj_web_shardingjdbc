package com.escst.highformwork.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.highformwork.bean.RealTimeRequestBean;
import com.escst.highformwork.entity.HighformworkThresholdValueEntity;
import com.escst.highformwork.service.HighformworkRealTimeService;
import com.escst.highformwork.vo.ConstructionFlowVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import com.escst.highformwork.vo.RealTimeVo;
import com.escst.highformwork.vo.TrendRequestVo;
import com.escst.highformwork.vo.TrendVo;

/**
 * @desc 高支模控制器
 * @author niejing
 * @date 2018年7月16日 下午3:30:39
 */
@Controller
@RequestMapping("highformworkRealTimeController")
public class HighformworkRealTimeController {

	private static Logger logger = LoggerFactory.getLogger(HighformworkRealTimeController.class);

	@Autowired
	private HighformworkRealTimeService realTimeService;

	/**
	 * 
	 * @desc 通过控制器查询标段数据
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年7月17日 下午2:32:57
	 */
	@RequestMapping("queryFlowList")
	@ResponseBody
	public ReturnJson queryFlowList(String constructionId) {
		ReturnJson returnJson = null;
		try {
			List<RealTimeVo> list = realTimeService.queryRealTimeDataList(constructionId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("通过工地id查询标段数据异常");
			logger.error("根据工地id查询实时数据异常", e);
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 根据设备id查询阈值
	 * @param equipmentId
	 * @return
	 * @author niejing
	 * @date 2018年7月17日 下午5:03:10
	 */
	@RequestMapping("queryAlarmInfo")
	@ResponseBody
	public ReturnJson queryAlarmInfo(String equipmentId) {
		ReturnJson returnJson = null;
		try {
			List<HighformworkThresholdValueEntity> list = realTimeService.queryAlarmInfo(equipmentId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("根据设备id查询阈值异常");
			logger.error("根据设备id查询阈值异常", e);
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 根据预警，报警，控制次数查询详情
	 * @param type
	 * @param flowId
	 * @return
	 * @author niejing
	 * @date 2018年7月18日 下午5:15:13
	 */
	@RequestMapping("alarmDetail")
	@ResponseBody
	public ReturnJson alarmDetail(int type, String flowId) {
		ReturnJson returnJson = null;
		try {
			List<HighformworkRealTimeVo> list = realTimeService.alarmDetail(type, flowId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("根据预警，报警，控制次数查询详情 异常");
			logger.error("根据预警，报警，控制次数查询详情 异常", e);
		}
		return returnJson;
	}

	/**
	 * 
	 * @desc 获取趋势图
	 * @return
	 * @author niejing
	 * @date 2018年7月19日 下午3:30:33
	 */
	@RequestMapping("queryTrend")
	@ResponseBody
	public ReturnJson queryTrend(@RequestBody TrendRequestVo vo) {
		ReturnJson returnJson = null;
		try {
			List<TrendVo> list = realTimeService.queryTrend(vo);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取趋势图异常");
			logger.error("获取趋势图异常", e);
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 通过工地ID获取流水段列表
	 * @param uuid
	 * @param constructionId
	 * @return 
	 * @author niejing
	 * @date 2018年7月20日 下午5:16:57
	 */
	@RequestMapping("queryConstructionFlowList")
	@ResponseBody
	public ReturnJson queryConstructionFlowList(String constructionId){
		ReturnJson returnJson = null;
		try {
			List<ConstructionFlowVo> list = realTimeService.selectConstructionFlowList(constructionId);
			returnJson = ReturnJson.success(list);
		}catch (Exception e) {
			returnJson = ReturnJson.fail("通过工地ID获取流水段列表异常");
			logger.error("通过工地ID获取流水段列表异常", e);
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 根据flowId查询实时数据 
	 * @param flowId
	 * @return 
	 * @author niejing
	 * @date 2018年10月12日 下午3:27:57
	 */
	@RequestMapping("queryByFlowId")
	@ResponseBody
	public ReturnJson queryByFlowId(RealTimeRequestBean bean){
		ReturnJson returnJson = null;
		try {
			PageVo vo = realTimeService.queryByFlowId(bean);
			returnJson = ReturnJson.success(vo.getRows(), vo.getCount());
		}catch (Exception e) {
			returnJson = ReturnJson.fail("根据flowId查询实时数据异常");
			logger.error("根据flowId查询实时数据异常", e);
		}
		return returnJson;
	}
	
	
}
