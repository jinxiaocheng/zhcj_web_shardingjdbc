package com.escst.environment.vo;

import java.math.BigDecimal;

/**
 * @desc 环境实时数据
 * @author zhouwei
 * @date 2017年8月17日 下午2:47:05
 */
public class EnvironmentRealtimeVO {

	/**
	 * @Fields time 采集时间
	 */
	private String time;
	
	private BigDecimal pm10;
	
	/**
	 * @Fields pm25 pm2.5
	 */
	private BigDecimal pm25;
	
	/**
	 * @Fields noise 噪音
	 */
	private BigDecimal noise;
	
	/**
	 * @Fields temperature 温度
	 */
	private BigDecimal temperature;
	
	/**
	 * @Fields humidity 湿度
	 */
	private BigDecimal humidity;
	
	/**
	 * @Fields windSpeed 风速
	 */
	private BigDecimal windSpeed;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getPm10() {
		return pm10;
	}

	public void setPm10(BigDecimal pm10) {
		this.pm10 = pm10;
	}

	public BigDecimal getNoise() {
		return noise;
	}

	public void setNoise(BigDecimal noise) {
		this.noise = noise;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}

	public BigDecimal getPm25() {
		return pm25;
	}

	public void setPm25(BigDecimal pm25) {
		this.pm25 = pm25;
	}
}
