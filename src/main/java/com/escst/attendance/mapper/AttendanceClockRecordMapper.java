package com.escst.attendance.mapper;

import java.util.List;
import java.util.Map;

import com.escst.attendance.bean.AttendanceNewBean;
import com.escst.attendance.vo.AttendanceClockRecordVo;
import com.escst.attendance.vo.AttendanceNumNewVO;
import org.springframework.stereotype.Repository;
import com.escst.attendance.entity.AttendanceClockRecordEntity;
import com.escst.commons.mapper.BaseMapper;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年7月12日 下午1:31:42
 */
@Repository
public interface AttendanceClockRecordMapper extends BaseMapper<AttendanceClockRecordEntity> {

	/**
	 * @desc 根据打开时间更新打卡日期和打卡的分钟
	 * @author zhouwei
	 * @date 2017年7月12日 下午2:14:44
	 */
	void updateClockDateMinute();

	/**
	 * @desc 得到打卡日期和打卡分钟为空的记录数
	 * @return 
	 * @author zhouwei
	 * @date 2017年7月12日 下午2:20:55
	 */
	Map<String, Object> getNullClockDateNum();

	/**
	* @desc 通过工地Id获取打卡信息
	* @param map
	* @return
	* @author dwj
	* @date 2018/1/17 10:24
	*/
	List<AttendanceClockRecordVo> queryList(Map<String,Object> map);


	List<String> queryRecordPersonId(Map<String,Object> map);


	int count(Map<String,Object> map);

	/**
	* @desc 获取当前工地下每个分包公司下总人数
	* @param map
	* @return
	* @author dwj
	* @date 20/7/2018 14:36
	*/
	List<AttendanceNewBean> selectTotalByCompanyId(Map<String,Object> map);

	/**
	* @desc 获取当前用下分包公司总数
	* @param map
	* @return
	* @author dwj
	* @date 20/7/2018 16:17
	*/
	int countNew(Map<String,Object> map);

	List<AttendanceNumNewVO> selectByCompanyId(String id);



}


