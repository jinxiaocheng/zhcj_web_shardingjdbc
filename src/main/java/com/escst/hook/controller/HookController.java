package com.escst.hook.controller;

import com.escst.commons.exception.EscstException;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.hook.entity.HookEquipmentEntity;
import com.escst.hook.entity.HookNvrEntity;
import com.escst.hook.service.HookService;
import com.escst.hook.vo.HookEquipmentVo;
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
 * @date 10:39 19/10/2018
 */
@Controller
@RequestMapping("hook")
public class HookController {

    private final static Logger logger = LoggerFactory.getLogger(HookController.class);

    @Autowired
    private HookService hookService;


    /**
    * @desc 获取当前项目下吊钩设备
    * @param equipmentEntity
    * @return
    * @author dwj
    * @date 19/10/2018 10:45
    */
    @RequestMapping("getAllHookEquipmentByConstruction")
    @ResponseBody
    public ReturnJson getAllHookEquipmentByConstruction(HookEquipmentEntity equipmentEntity){
        ReturnJson returnJson = null;
        try{
            PageVo vo =  hookService.getAllHookEquipmentByConstruction(equipmentEntity);
            returnJson = ReturnJson.success(vo.getRows(),vo.getTotalRecord());
        }catch (Exception e){
            logger.error("获取当前项目下吊钩设备异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 新增或修改吊钩设备
    * @param equipmentEntity
    * @return
    * @author dwj
    * @date 19/10/2018 10:48
    */
    @RequestMapping("saveHookEquipment")
    @ResponseBody
    public ReturnJson saveHookEquipment(HookEquipmentEntity equipmentEntity){
        ReturnJson returnJson = null;
        try{
            hookService.saveHookEquipment(equipmentEntity);
            returnJson = ReturnJson.success();
        }catch (Exception e){
            logger.error("新增或修改吊钩设备异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 获取吊钩设备详情
    * @param equipmentEntity
    * @return
    * @author dwj
    * @date 19/10/2018 10:59
    */
    @RequestMapping("getHookEquipmentDetail")
    @ResponseBody
    public ReturnJson getHookEquipmentDetail(HookEquipmentEntity equipmentEntity){
        ReturnJson returnJson = null;
        try{
            HookEquipmentVo vo = hookService.getHookEquipmentDetail(equipmentEntity.getId());
            returnJson =  ReturnJson.success(vo);
        }catch (Exception e){
            logger.error("查看吊钩设备详情异常"+e.getMessage(),e);
            returnJson =  ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 获取当前项目下有效吊钩设备
    * @param equipmentEntity
    * @return 
    * @author dwj
    * @date 22/10/2018 14:12
    */
    @RequestMapping("getVaildHookEquipmet")
    @ResponseBody
    public ReturnJson getVaildHookEquipmet(HookEquipmentEntity equipmentEntity){
        ReturnJson returnJson = null;
        try{
            List<BaseVO> vos = hookService.getVaildHookEquipmet(equipmentEntity.getConstructionId());
            returnJson =  ReturnJson.success(vos);
        }catch (Exception e){
            logger.error("获取当前项目下有效吊钩设备异常"+e.getMessage(),e);
            returnJson =  ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 获取Nvr
    * @param entity
    * @return
    * @author dwj
    * @date 24/10/2018 13:31
    */
    @RequestMapping("getNvrList")
    @ResponseBody
    public ReturnJson getNvrList(HookNvrEntity entity){
        ReturnJson returnJson = null;
        try{
            PageVo vo = hookService.getNvrList(entity);
            returnJson =  ReturnJson.success(vo.getRows(),vo.getTotalRecord());
        }catch (Exception e){
            logger.error("获取Nvr信息异常"+e.getMessage(),e);
            returnJson =  ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 新增Nvr
    * @param entity
    * @return
    * @author dwj
    * @date 24/10/2018 13:31
    */
    @RequestMapping("saveNvr")
    @ResponseBody
    public ReturnJson saveNvr(@RequestBody HookNvrEntity entity){
        ReturnJson returnJson = null;
        try{
            hookService.save(entity);
            returnJson =  ReturnJson.success();
        }catch (Exception e){
            logger.error("新增Nvr信息异常"+e.getMessage(),e);
            if(e instanceof EscstException){
                returnJson = ReturnJson.fail(e.getMessage());
            }else {
                returnJson = ReturnJson.fail("系统异常");
            }
        }
        return returnJson;
    }


    /**
    * @desc 查看Nvr详情信息
    * @param entity
    * @return
    * @author dwj
    * @date 24/10/2018 13:39
    */
    @RequestMapping("getNvrDetail")
    @ResponseBody
    public ReturnJson getNvrDetail(HookNvrEntity entity){
        ReturnJson returnJson = null;
        try{
            HookNvrEntity nvrEntity = hookService.getNvrDetail(entity.getId());
            returnJson =  ReturnJson.success(nvrEntity);
        }catch (Exception e){
            logger.error("查看Nvr详情信息异常"+e.getMessage(),e);
            returnJson =  ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

}
