package com.escst.home.entity;

import java.util.List;

/**
 * @desc
 * @author niejing
 * @date 2017年5月27日 上午11:56:43
 */
public class HomeVo {

	// 当前人力
	private int locale;

	// 最高人力
	private int totalNum;

	// 开始时间
	private String contractStartDate;

	// 竣工时间
	private String contractEndDate;
	//剩余天数
	private int remainderDays;
	// 项目进度
	private String projectProgress;

	// 已检查
	private Long qualityChecked = 0L;

	// 待整改
	private Long qualityCorrective = 0L;

	// 已检查
	private Long safeChecked = 0L;

	// 待整改
	private Long safeCorrective = 0L;

	// 未完成任务
	private Long taskNotFinish = 0L;

	// 派发任务
	private Long taskAssign = 0L;

	//温度
	private String temperature;

	/**
	 * 天气类型
	 * 0:晴  1:多云 2：雨  3：雪
	 */
	private int type = 0;
	
	//天气
	private String weather;
	
	/**
	 * @Fields cityName 城市名称
	 */
	private String cityName;

	/**文件集合**/
	private List<String> fileList;

	private int camera_online;
	private int carera_offline;
	
	private int towerCrane_online;
	private int towerCrane_offline;
	
	private int lift_online;
	private int lift_offline;
	
	private int environment_online;
	private int environment_offline;
	
	
	public int getEnvironment_online() {
		return environment_online;
	}

	public void setEnvironment_online(int environment_online) {
		this.environment_online = environment_online;
	}

	public int getEnvironment_offline() {
		return environment_offline;
	}

	public void setEnvironment_offline(int environment_offline) {
		this.environment_offline = environment_offline;
	}

	public int getCamera_online() {
		return camera_online;
	}

	public void setCamera_online(int camera_online) {
		this.camera_online = camera_online;
	}

	public int getCarera_offline() {
		return carera_offline;
	}

	public void setCarera_offline(int carera_offline) {
		this.carera_offline = carera_offline;
	}

	public int getTowerCrane_online() {
		return towerCrane_online;
	}

	public void setTowerCrane_online(int towerCrane_online) {
		this.towerCrane_online = towerCrane_online;
	}

	public int getTowerCrane_offline() {
		return towerCrane_offline;
	}

	public void setTowerCrane_offline(int towerCrane_offline) {
		this.towerCrane_offline = towerCrane_offline;
	}

	public int getLift_online() {
		return lift_online;
	}

	public void setLift_online(int lift_online) {
		this.lift_online = lift_online;
	}

	public int getLift_offline() {
		return lift_offline;
	}

	public void setLift_offline(int lift_offline) {
		this.lift_offline = lift_offline;
	}

	public int getRemainderDays() {
		return remainderDays;
	}

	public void setRemainderDays(int remainderDays) {
		this.remainderDays = remainderDays;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLocale() {
		return locale;
	}

	public void setLocale(int locale) {
		this.locale = locale;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	public Long getQualityChecked() {
		return qualityChecked;
	}

	public void setQualityChecked(Long qualityChecked) {
		this.qualityChecked = qualityChecked;
	}

	public Long getQualityCorrective() {
		return qualityCorrective;
	}

	public void setQualityCorrective(Long qualityCorrective) {
		this.qualityCorrective = qualityCorrective;
	}

	public Long getSafeChecked() {
		return safeChecked;
	}

	public void setSafeChecked(Long safeChecked) {
		this.safeChecked = safeChecked;
	}

	public Long getSafeCorrective() {
		return safeCorrective;
	}

	public void setSafeCorrective(Long safeCorrective) {
		this.safeCorrective = safeCorrective;
	}

	public Long getTaskAssign() {
		return taskAssign;
	}

	public void setTaskAssign(Long taskAssign) {
		this.taskAssign = taskAssign;
	}

	@Override
	public String toString() {
		return "HomeVo [locale=" + locale + ", totalNum=" + totalNum + ", contractStartDate=" + contractStartDate + ", contractEndDate="
				+ contractEndDate + ", projectProgress=" + projectProgress + ", qualityChecked=" + qualityChecked + ", qualityCorrective="
				+ qualityCorrective + ", safeChecked=" + safeChecked + ", safeCorrective=" + safeCorrective + ", taskNotFinish=" + taskNotFinish
				+ ", taskAssign=" + taskAssign + ", weather=" + weather + ", type=" + type + "]";
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getTaskNotFinish() {
		return taskNotFinish;
	}

	public void setTaskNotFinish(Long taskNotFinish) {
		this.taskNotFinish = taskNotFinish;
	}

}
