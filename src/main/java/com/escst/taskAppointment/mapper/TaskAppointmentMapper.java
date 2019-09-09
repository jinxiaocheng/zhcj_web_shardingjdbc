package com.escst.taskAppointment.mapper;

import com.escst.taskAppointment.entity.TaskAppointmentEntity;
import com.escst.taskAppointment.entity.TaskAppointmentProcessEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 16:30 2017/4/18
 */
@Repository
public interface TaskAppointmentMapper {

    //新增任务预约
    int insertTaskAppointmnet(TaskAppointmentEntity taskAppointmentEntity);

    int insertTaskAppointmnetProcess(TaskAppointmentProcessEntity TaskAppointmentProcessEntity);

    List<Map<String,Object>> queryList(Map<String, Object> map);

    List<Map<String,Object>> queryInfoList(TaskAppointmentEntity taskAppointmentEntity);

    List<Map<String, Object>> queryListDetial(Map<String, Object> map);

    int updateTaskAppointment(TaskAppointmentEntity taskAppointmentEntity);

    int  queryCount(Map<String,Object> map);



}
