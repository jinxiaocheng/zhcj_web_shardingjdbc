package com.escst.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.exception.EscstException;
import com.escst.commons.manager.ClientManager;
import com.escst.commons.utils.BrowserUtils;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.user.entity.LogEntity;
import com.escst.user.mapper.LogMapper;

/**
 * @desc 日志服务类
 * @author caozx
 * @date 2017年2月14日 下午2:05:38
 */
@Service
@Transactional
public class LogService {

    @Autowired
    private LogMapper logMapper;

    public String add(String content, Short loglevel, Short operatetype) {
        try {
            HttpServletRequest request = ContextUtils.getRequest();
            String broswer = BrowserUtils.checkBrowse(request);
            LogEntity logEntity = new LogEntity();
            logEntity.setId(UuidUtils.getUuid());
            logEntity.setBroswe(broswer);
            logEntity.setContent(content);
            logEntity.setLevel(loglevel);
            logEntity.setOperateType(operatetype);
            logEntity.setCreateTime(DateUtils.getCurrentDate());
            logEntity.setUserEntity(ClientManager.getUserEntity());
            logMapper.insert(logEntity);
            return logEntity.getId();
        } catch (Exception e) {
             throw new EscstException(e.getMessage(), e);
        }
    }
}
