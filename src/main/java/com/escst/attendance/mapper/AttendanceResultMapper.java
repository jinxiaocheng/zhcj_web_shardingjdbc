package com.escst.attendance.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.attendance.entity.AttendanceResultEntity;
import com.escst.commons.mapper.BaseMapper;

/**
 * @desc 
 * @author niejing
 * @date 2017年8月9日 下午3:34:10
 */
@Repository
public interface AttendanceResultMapper extends BaseMapper<AttendanceResultEntity> {

	public List<Map<String,Object>> selectAttendanceList(AttendanceResultEntity entity);
}
