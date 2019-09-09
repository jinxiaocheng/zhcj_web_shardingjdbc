package com.escst.person.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.person.entity.AcsControlEntity;
import com.escst.person.service.AcsControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 15:20 2017/12/18
 */
@Controller
@RequestMapping("acsControl")
public class AcsControlController {

    private static final Logger logger = LoggerFactory.getLogger(AcsControlController.class);

    @Autowired
    private AcsControlService acsControlService;


    /**
     * @param entity
     * @return
     * @desc 查询考勤控制器列表
     * @author dwj
     * @date 2017/12/18 14:14
     */
    @ResponseBody
    @RequestMapping("queryList")
    public ReturnJson queryList(AcsControlEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo vo = acsControlService.queryList(entity);
            returnJson = ReturnJson.success(vo.getRows(), vo.getTotalRecord());
        } catch (Exception e) {
            logger.info("查询考勤控制器列表失败" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param entity
     * @return
     * @desc 新增或者修改考勤控制器
     * @author dwj
     * @date 2017/12/18 14:15
     */
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public ReturnJson saveOrUpdate(@RequestBody AcsControlEntity entity) {
        ReturnJson returnJson = null;
        try {
            acsControlService.saveOrUpdate(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.info("新增或修改考勤控制器失败" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @param id
     * @return
     * @desc 通过Id查询控制器
     * @author dwj
     * @date 2017/12/19 11:11
     */
    @ResponseBody
    @RequestMapping("queryById")
    public ReturnJson queryById(String id) {
        ReturnJson returnJson = null;
        try {
            AcsControlEntity entity = acsControlService.queryById(id);
            returnJson = ReturnJson.success(entity);
        } catch (Exception e) {
            logger.info("通过Id查询控制器失败" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param
     * @return
     * @desc 查询没有分配大门的控制器
     * @author jincheng
     * @date 2018-5-20 14:48
     */
    @RequestMapping("listControllerNoDoor")
    @ResponseBody
    public ReturnJson listControllerNoDoor(String constructionId) {
        ReturnJson returnJson = null;
        try {
            List<AcsControlEntity> acsControlEntityList = acsControlService.listControllerNoDoor(constructionId);
            returnJson = ReturnJson.success(acsControlEntityList);
        } catch (Exception e) {
            logger.info("查询没有分配大门的控制器失败" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

}
