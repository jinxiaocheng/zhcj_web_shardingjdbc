package com.escst.home.mapper;

import java.util.Map;

import com.escst.home.entity.WeatherEntity;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年6月8日 上午10:25:40
 */
public interface WeatherMapper {

	/**
	 * @desc 获取城市最新的天气信息
	 * @param params
	 * @return 
	 * @author zhouwei
	 * @date 2017年6月8日 下午12:13:32
	 */
	WeatherEntity getLastestWeather(Map<String, Object> params);
	
}
