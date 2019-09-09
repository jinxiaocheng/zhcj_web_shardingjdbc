package com.escst.thirdPart.controller;

import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.thirdPart.bean.CountBean;
import com.escst.thirdPart.bean.PersonInfoBean;
import com.escst.thirdPart.bean.ThridPersonBean;
import com.escst.thirdPart.entity.TrainPersonExcelEntity;
import com.escst.thirdPart.service.ThirdPersonService;
import com.escst.thirdPart.vo.ChartResultVo;
import com.escst.thirdPart.vo.CountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri.PersonInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 16:44 25/7/2018
 */
@Controller
@RequestMapping("thridPerson")
public class ThirdPersonController {

    private final static Logger logger = LoggerFactory.getLogger(ThirdPersonController.class);
    @Autowired
    private ThirdPersonService service;

    /**
     * @desc 获取人员图像
     * @param id
     * @return ReturnJson
     * @author dwj
     * @date 25/7/2018 16:47
     */
    @RequestMapping("getPersonPic")
    @ResponseBody
    public ReturnJson getPersonPic(String id) {
        ReturnJson returnJson = null;
        try {
            byte[] by = service.getPersonPicture(id);
            returnJson = ReturnJson.success(by);
        } catch (Exception e) {
            logger.error("获取人员图像异常", e);
            returnJson = ReturnJson.fail("获取人员图像异常");
        }
        return returnJson;
    }

    /**
    * @desc 获取人员信息
    * @param bean
    * @return
    * @author dwj
    * @date 30/7/2018 17:26
    */
    @RequestMapping("getAllPerson")
    @ResponseBody
    public ReturnJson getAllPerson(ThridPersonBean bean){
        ReturnJson returnJson = null;
        try{
            PageVo vo = service.getAllPerson(bean);
            returnJson = ReturnJson.success(vo);
        }catch (Exception e){
            logger.error("获取人员信息异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
    * @desc 获取人员详情信息
    * @param bean
    * @return
    * @author dwj
    * @date 1/8/2018 10:45
    */
    @RequestMapping("getPersonDetail")
    @ResponseBody
    public ReturnJson getPersonDetail(ThridPersonBean bean){
        ReturnJson returnJson = null;
        try{
            Map<String,Object> map = service.getPersonDetail(bean);
            returnJson = ReturnJson.success(map);
        }catch (Exception e){
            logger.error("获取人员详情信息异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @desc 获取工种
     * @param type
     * @return
     * @author dwj
     * @date 1/8/2018 10:45
     */
    @RequestMapping("getCode")
    @ResponseBody
    public ReturnJson getCode(int type){
        ReturnJson returnJson = null;
        try{
            List<String> names = service.getCode(type);
            returnJson = ReturnJson.success(names);
        }catch (Exception e){
            logger.error("获取人员详情信息异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @desc 获取安全人员培训统计
     * @param bean
     * @return
     * @author dwj
     * @date 1/8/2018 10:45
     */
    @RequestMapping("getCountData")
    @ResponseBody
    public ReturnJson getCountData(CountBean bean){
        ReturnJson returnJson = null;
        try{
            List<CountVo> vos = service.getCountData(bean);
            returnJson = ReturnJson.success(vos);
        }catch (Exception e){
            logger.error("获取安全人员培训统计异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @desc 获取统计单位
     * @param
     * @return
     * @author dwj
     * @date 1/8/2018 10:45
     */
    @RequestMapping("getDepart")
    @ResponseBody
    public ReturnJson getDepart(){
        ReturnJson returnJson = null;
        try{
            List<BaseVO> vos = service.getDepart();
            returnJson = ReturnJson.success(vos);
        }catch (Exception e){
            logger.error("获取统计单位异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @desc 获取统计图数据
     * @param
     * @return
     * @author dwj
     * @date 1/8/2018 10:45
     */
    @RequestMapping("getChartResult")
    @ResponseBody
    public ReturnJson getChartResult(CountBean bean){
        ReturnJson returnJson = null;
        try{
            List<ChartResultVo> vos = service.getChartData(bean);
            returnJson = ReturnJson.success(vos);
        }catch (Exception e){
            logger.error("获取统计图数据异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
    * @desc 导出Excel
    * @param bean
    * @return
    * @author dwj
    * @date 7/8/2018 17:05
    */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public ReturnJson exportExcel(ThridPersonBean bean) {
        ReturnJson returnJson = null;
        try {
            List<TrainPersonExcelEntity> list = service.queryExportPersonList(bean);
            Map<String, Object> beanParams = new HashMap<String, Object>();
            beanParams.put("list", list);
            String filePath = ExcelUtils.createExcel("safetyTrainPerson.xls", beanParams);
            returnJson = ReturnJson.success(filePath);
        } catch (Exception e) {
            logger.error("导出excel异常:" + e.getMessage(), e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

}
