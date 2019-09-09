package com.escst.home.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 天气
 * @author zhouwei
 * @date 2017年6月8日 上午10:20:00
 */
public class WeatherEntity implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 5950021999497005947L;

	private String id;
	
	/**
	 * @Fields cityId 城市ID
	 */
	private String cityId;
	
	/**
	 * @Fields cityName 城市名称
	 */
	private String cityName;
	
	/**
	 * @Fields date 日期.格式2017-06-08
	 */
	private String date;
	
	/**
	 * @Fields temperature 温度
	 */
	private String temperature;
	
	/**
	 * @Fields type 0:晴;1:多云;2:雨;3:雪;
	 */
	private int type;
	
	/**
	 * @Fields weather 天气描述
	 */
	private String weather;
	
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
