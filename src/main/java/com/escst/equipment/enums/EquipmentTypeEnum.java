package com.escst.equipment.enums;

/**
 * @desc 设备类型
 * @author zhouwei
 * @date 2017年8月16日 下午6:30:46
 */
/**
 * @desc 
 * @author zhouwei
 * @date 2017年9月13日 下午6:54:31
 */
public enum EquipmentTypeEnum {

	/**
	 * @Fields CAMERA 摄像头
	 */
	CAMERA(1, "摄像头"),
	
	/**
	 * @Fields ATTENDANCE 考勤
	 */
	ATTENDANCE(2, "考勤"),
	
	/**
	 * @Fields TOWERCRANE 塔吊
	 */
	TOWERCRANE(3, "塔吊"),
	
	/**
	 * @Fields LIFTER 升降机
	 */
	LIFTER(4, "升降机"),
	
	/**
	 * @Fields ENVIRONMENT 环境
	 */
	ENVIRONMENT(5, "环境"),
	
	/**
	 * @Fields CONTROL 喷淋控制器
	 */
	CONTROL(6, "喷淋控制器"),
	
	/**
	 * @Fields ELECTRIC 电表
	 */
	ELECTRIC(7, "电表"),
	
	/**
	 * @Fields WATER 水表
	 */
	WATER(8, "水表"),
	
	/**
	 * @Fields HOOK 吊钩
	 */
	HOOK(9, "吊钩"),

	/**
	 * @Fields HIGHFORMWORK 高支模
	 */
	HIGHFORMWORK(10, "高支模"),

	/**
	 * @Fields WEIGHBRIDGE 智能地磅
	 */
	WEIGHBRIDGE(11, "智能地磅"),

	/**
	 * @Fields UNLOAD 卸料平台
	 */
	UNLOAD(12, "卸料平台"),
	/**
	 * @Fields PLC plc采集器
	 */
	PLC(13, "plc采集器");
	
	private int value = 0;
	
	private String label = null;
	
	private EquipmentTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	/**
	 * @desc 是否包含该枚举值
	 * @param type
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月21日 下午4:29:46
	 */
	public static boolean contain(Integer type) {
		EquipmentTypeEnum[] types = EquipmentTypeEnum.values();
		if (type == null) {
			return false;
		}
		for (EquipmentTypeEnum ete : types) {
			if (ete.getValue() == type.intValue()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean notContain(Integer type) {
		return !contain(type);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
