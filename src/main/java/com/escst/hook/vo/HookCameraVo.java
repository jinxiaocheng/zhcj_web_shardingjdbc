package com.escst.hook.vo;

/**
 * @desc 
 * @author niejing
 * @date 2018年10月22日 下午3:16:59
 */
public class HookCameraVo {


	/**
	 * @Fields id 摄像头ID,对应海康平台camera_id
	 */
	private String id;
	
	private String sysCode;
	
	/**
	 * @Fields name 摄像头名称
	 */
	private String name;
	
	/**
	 * @Fields channelNum 通道号.最小值1
	 */
	private int channelNum;
	
	/**
	 * @Fields isOnline 是否在线.1:在线;0:离线
	 */
	private int isOnline = 1;

	/**
     * @Fields flag 标识.1:可云台控制;0:不能云台控制
     */
    private int flag = 0;
    
    
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public String getSysCode() {
		return sysCode;
	}

	public String getName() {
		return name;
	}

	public int getChannelNum() {
		return channelNum;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	
	
	
}
