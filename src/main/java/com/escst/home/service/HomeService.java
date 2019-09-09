package com.escst.home.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.construction.service.ConstructionService;
import com.escst.environment.service.EnvironmentService;
import com.escst.equipment.entity.EquipmentManagerEntity;
import com.escst.equipment.service.EquipmentManagerService;
import com.escst.home.entity.HomeVo;
import com.escst.home.entity.WeatherEntity;
import com.escst.inspection.bean.InspectionRequestBean;
import com.escst.inspection.service.InspectionService;
import com.escst.lifter.service.LifterService;
import com.escst.person.service.PersonService;
import com.escst.towerCrane.service.TowerCraneService;
import com.escst.video.service.CameraService;

/**
 * @desc 首页服务
 * @author niejing
 * @date 2017年10月30日 上午9:08:21
 */
@Service
public class HomeService {

	private static Logger logger = LoggerFactory.getLogger(HomeService.class);

	@Autowired
	private ConstructionService constructionService;

	@Autowired
	private CameraService cameraService;

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private TowerCraneService towerCraneService;

	@Autowired
	private LifterService lifterService;

	@Autowired
	private EnvironmentService environmentService;
	
	@Autowired
	private EquipmentManagerService equipmentManagerService;

	@Autowired
	private InspectionService inspectionService;

	@Autowired
	private PersonService personService;

	//在线
	private static final int ONLINE=1;
	//离线
	private static final int OFFLINE=0;
	
	public HomeVo queryBasicInfo(String constructionId) {
		// 设置天气
		HomeVo vo = new HomeVo();
		try {
			Map<String, Object> construction = constructionService.queryConstructionById(constructionId);
			if (construction == null) {
				throw new EscstException("工程信息为空,工地ID" + constructionId);
			}

			if (construction.containsKey("belongCity")) {
				String cityId = (String) construction.get("belongCity");
				String date = DateUtils.format(new Date(), DateUtils.TO_HOUR);

				WeatherEntity weather = weatherService.getLastestWeather(cityId, date);
				if (weather != null) {
					vo.setTemperature(weather.getTemperature());
					vo.setType(weather.getType());
					vo.setWeather(weather.getWeather());
					vo.setCityName(weather.getCityName());
				}
			}
		} catch (Exception e) {
			logger.error("获取天气信息异常", e);
			throw new EscstException("获取天气信息异常");
		}
		// 设置整改检查参数
		setManageParam(vo, constructionId);

		// 设置在场人数，出勤人数
		setManPower(vo, constructionId);

		// 设置摄像头在线离线信息
		vo.setCamera_online(cameraService.queryCameraCount(constructionId, ONLINE));
		vo.setCarera_offline(cameraService.queryCameraCount(constructionId, OFFLINE));

		// 设置塔吊/升降机在线离线信息
		int towerCrane_online = 0;
		int towerCrane_offline = 0;

		int lift_online = 0;
		int lift_offline = 0;

		int environment_online = 0;
		int environment_offline = 0;
		List<EquipmentManagerEntity> list = equipmentManagerService.querybyConstructionId(constructionId);
		for (EquipmentManagerEntity equipmentEntity : list) {
			// 塔吊
			if (equipmentEntity.getType() == 3) {
				if (towerCraneService.isOline(constructionId, equipmentEntity.getId())) {
					towerCrane_online++;
				} else {
					towerCrane_offline++;
				}
			}
			// 升降机
			if (equipmentEntity.getType() == 4) {
				if (lifterService.isOline(constructionId, equipmentEntity.getId())) {
					lift_online++;
				} else {
					lift_offline++;
				}
			}
			//环境
			if (equipmentEntity.getType() == 5) {
				if (environmentService.isOline(constructionId, equipmentEntity.getId())) {
					environment_online++;
				} else {
					environment_offline++;
				}
			}
		}
		vo.setTowerCrane_online(towerCrane_online);
		vo.setTowerCrane_offline(towerCrane_offline);
		vo.setLift_online(lift_online);
		vo.setLift_offline(lift_offline);
		vo.setEnvironment_online(environment_online);
		vo.setEnvironment_offline(environment_offline);
		return vo;
	}

	/**
	 * 
	 * @desc 待整改数量：整改状态为待整改
	 * 		    已检测数量包含三种情况：1：处理意见为通过：2，处理意见为警告：3，处理意见为整改+整改状态为已整改
	 * @param vo
	 * @param constructionId 
	 * @author niejing
	 * @date 2018年3月19日 上午10:21:32
	 */
	private void setManageParam(HomeVo vo, String constructionId) {
		try {
			Map<String, Object> qualityCountMap = inspectionService
					.listAsCount(InspectionRequestBean.QUALITY_INSPECTION, constructionId);
			if (MapUtils.isNotEmpty(qualityCountMap)) {
				String qualityChecked = qualityCountMap.get("allCount") == null ? "" :qualityCountMap.get("allCount").toString();// 已检查的数量
				String qualityCorrective = qualityCountMap.get("correctiveCount") == null ? "" : qualityCountMap.get("correctiveCount").toString();// 待整改的数量
				vo.setQualityChecked(Long.parseLong(qualityChecked));
				vo.setQualityCorrective(Long.parseLong(qualityCorrective));
			}

			// 获取安全管理参数
			Map<String, Object> safeCountMap = inspectionService.listAsCount(InspectionRequestBean.SAFETY_INSPECTION,
					constructionId);
			if (MapUtils.isNotEmpty(safeCountMap)) {
				String safeChecked = safeCountMap.get("allCount") == null ? "" : safeCountMap.get("allCount").toString();// 已检查的数量
				String safeCorrective = safeCountMap.get("correctiveCount") == null ? "" : safeCountMap.get("correctiveCount").toString();// 待整改的数量
				vo.setSafeChecked(Long.parseLong(safeChecked));
				vo.setSafeCorrective(Long.parseLong(safeCorrective));
			}
		} catch (Exception e) {
			logger.error("设置设置工程管理参数异常", e);
		}
	}

	private void setManPower(HomeVo vo, String constructionId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("constructionId", constructionId);
			params.put("startDate", DateUtils.getTodayStart());
			params.put("endDate", DateUtils.getTodayEnd());
			int totalNum = personService.getTotalAttendanceNum(params);
			int locale = personService.getLocalePersonNum(params);
			vo.setLocale(locale);
			vo.setTotalNum(totalNum);
		} catch (Exception e) {
			logger.error("设置人力参数异常", e);
		}
	}
}
