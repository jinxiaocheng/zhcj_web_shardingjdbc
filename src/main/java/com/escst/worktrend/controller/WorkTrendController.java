package com.escst.worktrend.controller;

import com.escst.commons.vo.ReturnJson;
import com.escst.worktrend.entity.WorkTrendEntity;
import com.escst.worktrend.service.WorkTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 15:14 2018/2/23
 */
@Controller
@RequestMapping("workTrend")
public class WorkTrendController {

    private static final Logger logger = LoggerFactory.getLogger(WorkTrendController.class);

    @Autowired
    private WorkTrendService workTrendService;



    /**
    * @desc 查询工作动态列表
    * @param entity
    * @return
    * @author dwj
    * @date 2018/2/23 15:20
    */
    @RequestMapping("queryList")
    @ResponseBody
    public ReturnJson queryList(WorkTrendEntity entity, @RequestParam int type, @RequestParam int number){
        ReturnJson returnJson =null;
        try{
            List<WorkTrendEntity> list = workTrendService.queryList(entity,type,number);
            returnJson = ReturnJson.success(list);
        }catch (Exception e){
            returnJson = ReturnJson.fail("查询工作动态异常：");
            logger.error("查询工作动态异常："+e.getMessage(),e);
        }
        return  returnJson;
    }
}
