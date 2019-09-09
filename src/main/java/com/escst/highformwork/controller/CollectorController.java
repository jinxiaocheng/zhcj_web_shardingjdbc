package com.escst.highformwork.controller;
import com.escst.commons.exception.EscstException;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.highformwork.bean.CollectorBean;
import com.escst.highformwork.entity.CollectorEntity;
import com.escst.highformwork.service.HighformworkService;
import com.escst.highformwork.vo.CollectorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author dwj
 * @desc
 * @date 9:21 17/7/2018
 */
@Controller
@RequestMapping("collector")
public class CollectorController {

    private final static Logger logger = LoggerFactory.getLogger(CollectorController.class);

    @Autowired
    private HighformworkService highformworkService;


    /**
     * @param entity
     * @return
     * @desc 获取采集器列表
     * @author dwj
     * @date 17/7/2018 9:30
     */
    @RequestMapping("queryCollectorList")
    @ResponseBody
    public ReturnJson queryList(CollectorEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo vo = highformworkService.queryList(entity);
            returnJson = ReturnJson.success(vo.getRows(),vo.getTotalRecord());
        } catch (Exception e) {
            logger.error("获取采集器列表异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param bean
     * @return
     * @desc 批量新增采集器列表
     * @author dwj
     * @date 17/7/2018 9:33
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnJson save(@RequestBody CollectorBean bean) {
        ReturnJson returnJson = null;
        try {
            highformworkService.save(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增采集器异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 获取项目下有效采集器
    * @param entity
    * @return
    * @author dwj
    * @date 11/10/2018 14:25
    */
    @RequestMapping("getVaildCollector")
    @ResponseBody
    public ReturnJson getVaildCollector(CollectorEntity entity){
        ReturnJson returnJson = null;
        try {
            List<CollectorVo> list = highformworkService.getVaildCollector(entity.getConstructionId());
            returnJson = ReturnJson.success(list);
        }catch (Exception e){
            logger.error("获取项目下有效采集器异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 停用或启用采集器
    * @param entity
    * @return
    * @author dwj
    * @date 11/10/2018 14:54
    */
    @RequestMapping("disableOrEnable")
    @ResponseBody
    public ReturnJson disableOrEnable(CollectorEntity entity){
        ReturnJson returnJson = null;
        try {
            highformworkService.disableOrEnable(entity);
            returnJson = ReturnJson.success();
        }catch (Exception e){
            logger.error("停用或启用采集器异常"+e.getMessage(),e);
            if(e instanceof EscstException){
                returnJson = ReturnJson.fail(e.getMessage());
            }else {
                returnJson = ReturnJson.fail("系统异常");
            }
        }
        return returnJson;
    }


}
