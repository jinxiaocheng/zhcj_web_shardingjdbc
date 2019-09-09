package com.escst.person.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.person.entity.DoorEntity;
import com.escst.person.service.DoorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 门禁，设备相关
 * @date 2018-5-30 14:25
 */
@Controller
@RequestMapping("door")
public class DoorController {

    private static Logger logger = LoggerFactory.getLogger(DoorController.class);

    @Autowired
    private DoorService doorService;


    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到门禁维护页面
     * @author jincheng
     * @date 2018/3/7 10:16
     */
    @RequestMapping("toDoor")
    public ModelAndView toDoor() {
        ModelAndView view = new ModelAndView("person/door");
        return view;
    }

    /**
     * @desc 跳转到分配门列表
     * @param null
     * @return
     * @author kz
     * @date 2018/6/1 16:49
     */
    @RequestMapping("toAllotDoor")
    public ModelAndView toAllotDoor() {
        ModelAndView view = new ModelAndView("person/allotDoor");
        return view;
    }

    /**
     * @param null
     * @return 跳转到门禁新增页面
     * @desc
     * @author kz
     * @date 2018/5/31 16:30
     */
    @RequestMapping("toAddDoor")
    public ModelAndView toAddDoor() {
        ModelAndView view = new ModelAndView("person/addDoor");
        return view;
    }


    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到设备维护页面
     * @author jincheng
     * @date 2018/3/7 10:16
     */
    @RequestMapping("toEquipment")
    public ModelAndView toEquipment() {
        ModelAndView view = new ModelAndView("person/equipment");
        return view;
    }

    /**
     * @desc 跳转到设备新增页面
     * @param null
     * @return
     * @author kz
     * @date 2018/6/1 15:39
     */
    @RequestMapping("toAddEquipment")
    public ModelAndView toAddEquipment() {
        ModelAndView view = new ModelAndView("person/addEquipment");
        return view;
    }

    /**
     * 查询门列表
     *
     * @param entity
     * @return
     */
    @RequestMapping("listAll")
    @ResponseBody
    public ReturnJson listAll(DoorEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = doorService.queryList(entity);
            returnJson = ReturnJson.success(pageVo.getRows(), pageVo.getTotalRecord());
        } catch (Exception e) {
            logger.error("查询门列表异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * 新增门
     *
     * @param entity
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ReturnJson add(@RequestBody DoorEntity entity) {
        ReturnJson returnJson = null;
        try {
            doorService.add(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增门异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * 编辑门时处理
     */
    @RequestMapping("listController")
    @ResponseBody
    public ReturnJson listController(String doorId) {
        ReturnJson returnJson = null;
        try {
            DoorEntity doorEntity = doorService.listController(doorId);
            returnJson = ReturnJson.success(doorEntity);
        } catch (Exception e) {
            logger.error("获取控制器异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @param entity
     * @return com.escst.commons.vo.ReturnJson
     * @desc 编辑门
     * @author jincheng
     * @date 2018-5-21 10:59
     */
    @RequestMapping("updateDoor")
    @ResponseBody
    public ReturnJson updateDoor(@RequestBody DoorEntity entity) {
        ReturnJson returnJson = null;
        try {
            doorService.updateDoor(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("编辑门异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * 查询门列表,不分页(人员分配门时调用)
     *
     * @param
     * @return
     */
    @RequestMapping("listDoor")
    @ResponseBody
    public ReturnJson listDoor(String personId) {
        ReturnJson returnJson = null;
        try {
            Map<Object, Collection> map = doorService.listDoor(personId);
            returnJson = ReturnJson.success(map);
        } catch (Exception e) {
            logger.error("查询门列表异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * 给人员分配门
     *
     * @param
     * @return
     */
    @RequestMapping("allotDoor")
    @ResponseBody
    public ReturnJson allotDoor(@RequestBody DoorEntity entity) {
        ReturnJson returnJson = null;
        try {
            doorService.allotDoor(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("给人员分配门异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


}
