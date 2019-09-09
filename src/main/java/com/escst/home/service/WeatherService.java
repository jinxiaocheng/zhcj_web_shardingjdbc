package com.escst.home.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.home.entity.WeatherEntity;
import com.escst.home.mapper.WeatherMapper;
import com.escst.redis.annotation.RedisCache;

/**
 * @desc
 * @author zhouwei
 * @date 2017年6月8日 下午12:12:03
 */
@Service
public class WeatherService {

	@Autowired
	private WeatherMapper weatherMapper = null;

	/**
	 * @desc 获取城市最新的天气信息
	 * @param cityId
	 * @param date
	 *            格式：yyyy-MM-dd HH
	 * @return
	 * @author zhouwei
	 * @date 2017年6月8日 下午12:17:52
	 */
	@RedisCache(expire = 70, timeUnit = TimeUnit.MINUTES)
	public WeatherEntity getLastestWeather(String cityId, String date) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", cityId);
		params.put("date", date.substring(0, 10));
		return weatherMapper.getLastestWeather(params);
	}

}
