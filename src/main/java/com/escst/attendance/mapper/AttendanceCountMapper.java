package com.escst.attendance.mapper;

import java.util.List;
import java.util.Map;

import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.bean.AttendanceVo;
import org.springframework.stereotype.Repository;

import com.escst.attendance.entity.AttendanceCountEntity;
import com.escst.attendance.vo.AttendanceNumQueryVO;
import com.escst.attendance.vo.AttendanceNumVO;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.QtyVO;

/**
 * @author dwj
 * @desc
 * @date 9:54 2017/10/30
 */
@Repository
public interface AttendanceCountMapper {

    void batchSave(List<AttendanceCountEntity> entity);

    void update(AttendanceCountEntity entity);

    List<Map<String,Object>> queryList(Map<String,Object> map);

    int selectCount(Map<String,Object> map);

    AttendanceCountEntity queryById(AttendanceCountEntity entity);
    
    /**
     * @desc 查询有权限的工地的岗位工种人数
     * @param authVO
     * @return 
     * @author zhouwei
     * @date 2017年11月6日 下午1:54:34
     */
    List<QtyVO> queryAuthWorkTypePersonQty(BaseAuthVO authVO);
    
    List<AttendanceNumVO> queryAttendanceNumQty(AttendanceNumQueryVO attendanceNumVO);

    List<AttendanceVo> queryAttendaceQty(Map<String,Object> map);

    List<String> queryTeamNameList(Map<String,Object> map);
    List<AttendanceVo> queryCount(Map<String,Object> map);

    List<String> queryCountByAttendanceDate(AttendanceCountEntity entity);

    void updateCount(AttendanceCountEntity entity);
}
