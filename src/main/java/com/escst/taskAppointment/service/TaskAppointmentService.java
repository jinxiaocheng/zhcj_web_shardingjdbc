package com.escst.taskAppointment.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.taskAppointment.entity.TaskAppointmentEntity;
import com.escst.taskAppointment.entity.TaskAppointmentProcessEntity;
import com.escst.taskAppointment.mapper.TaskAppointmentMapper;
import com.escst.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author dwj
 * @desc
 * @date 16:31 2017/4/18
 */
@Service
@Transactional
public class TaskAppointmentService {


    @Autowired
    private TaskAppointmentMapper taskAppointmentMapper;


    //任务预约列表
    public PageVo queryList(TaskAppointmentEntity taskAppointmentEntity, int type) {
        PageVo pageVo = new PageVo();
        try {
            String appointmentId = taskAppointmentEntity.getAppointmentId();
            int equipmentType = taskAppointmentEntity.getEquipmentType();
            int status = taskAppointmentEntity.getStatus();
            String constructionId = taskAppointmentEntity.getConstructionId();
            String areaId = taskAppointmentEntity.getAreaId();
            Map<String, Object> paraMap = new HashMap<String, Object>();
            if (type == 1) {
                paraMap.put("createTime", DateUtils.format(new Date(), "yyyy-MM-dd"));
            }
            if (StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList", constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            paraMap.put("appointmentId", appointmentId);
            paraMap.put("equipmentType", equipmentType);
            paraMap.put("status", status);
            paraMap.put("areaId",areaId);
            paraMap.put("status",taskAppointmentEntity.getStatus());
            int count = taskAppointmentMapper.queryCount(paraMap);
            //当前页
            int pageNum = taskAppointmentEntity.getPage();

            //每页显示记录数
            int pageSize = taskAppointmentEntity.getRowNum();

            //每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;
            List<Map<String, Object>> list = taskAppointmentMapper.queryList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {

            }
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询任务预约列表失败" + e.getMessage());
        }
        return pageVo;
    }

    //任务预约详细信息
    public List<Map<String, Object>> queryListDetial(String taskAppointmentId) {
        List<Map<String, Object>> list;
        try {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("taskAppointmentId", taskAppointmentId);
            list = taskAppointmentMapper.queryListDetial(paraMap);
        } catch (Exception e) {
            throw new EscstException("查询任务预约详细列表失败" + e.getMessage());
        }
        return list;
    }

    //新增任务预约
    public void insertTaskAppointmnet(TaskAppointmentEntity taskAppointmentEntity) {
        try {
            String id = taskAppointmentEntity.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                taskAppointmentEntity.setId(id);
                taskAppointmentEntity.setUpdateTime(new Date());
                taskAppointmentEntity.setCreateTime(new Date());
                if (taskAppointmentEntity.getIsUrgentTask() == 1) {
                    taskAppointmentEntity.setStatus(2);
                } else {
                    taskAppointmentEntity.setStatus(1);
                }
                taskAppointmentMapper.insertTaskAppointmnet(taskAppointmentEntity);
                TaskAppointmentProcessEntity taskAppointmentProcess = new TaskAppointmentProcessEntity();
                taskAppointmentProcess.setId(UuidUtils.getUuid());
                taskAppointmentProcess.setTaskAppointmentId(id);
                taskAppointmentProcess.setAppointmentId(taskAppointmentEntity.getAppointmentId());
                taskAppointmentProcess.setTaskStatus(taskAppointmentEntity.getStatus());
                taskAppointmentProcess.setRemark(taskAppointmentEntity.getRemark());
                taskAppointmentProcess.setCreateTime(new Date());
                taskAppointmentMapper.insertTaskAppointmnetProcess(taskAppointmentProcess);
            }
        } catch (Exception e) {
            throw new EscstException("新增任务预约失败");
        }

    }

    // 提交，处理任务预约
    public void insertTaskAppointmnetProcess(TaskAppointmentProcessEntity taskAppointmentProcess) {
        try {
            String id = taskAppointmentProcess.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                taskAppointmentProcess.setId(id);
                taskAppointmentProcess.setCreateTime(new Date());
                taskAppointmentMapper.insertTaskAppointmnetProcess(taskAppointmentProcess);
                TaskAppointmentEntity taskAppointmentEntity = new TaskAppointmentEntity();
                String taskAppointmentId = taskAppointmentProcess.getTaskAppointmentId();
                int status = taskAppointmentProcess.getTaskStatus();
                taskAppointmentEntity.setStatus(status);
                taskAppointmentEntity.setId(taskAppointmentId);
                taskAppointmentMapper.updateTaskAppointment(taskAppointmentEntity);
            }
        } catch (Exception e) {
            throw new EscstException("提交处理任务预约失败" + e.getMessage());
        }
    }





}
