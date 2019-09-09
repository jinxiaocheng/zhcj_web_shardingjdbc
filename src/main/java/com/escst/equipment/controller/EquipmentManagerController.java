package com.escst.equipment.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.equipment.entity.EquipmentDisassemblyEntity;
import com.escst.equipment.entity.EquipmentInOutRecordEntity;
import com.escst.equipment.entity.EquipmentMaintenanceEntity;
import com.escst.equipment.entity.EquipmentManagerEntity;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.service.EquipmentInspectionService;
import com.escst.equipment.service.EquipmentManagerService;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.equipment.vo.FaceQueryVO;
import com.escst.equipment.vo.InspectionQueryVO;
import com.escst.equipment.vo.InspectionVO;
import com.escst.equipment.vo.QueryVO;
import com.escst.person.service.PersonService;

/**
 * @author dwj测试
 * @desc
 * @date 10:45 2017/3/13
 */
@Controller
@RequestMapping("equipment")
public class EquipmentManagerController {

    private static Logger logger = LoggerFactory.getLogger(EquipmentManagerController.class);

    @Autowired
    private EquipmentManagerService equipmentManagerService;

    @Autowired
    private EquipmentInspectionService inspectionService;

    @Autowired
    private PersonService personService;


    /**
     * 分页查询设备信息
     *
     * @param equipmentManagerEntity 参数
     * @return 返回值
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ReturnJson queryEquipmentList(HttpServletRequest request, EquipmentManagerEntity equipmentManagerEntity) {
        ReturnJson returnJson;
        try {
        	String userId = ContextUtils.getCurrentUserId();
        	equipmentManagerEntity.setUserId(userId);
            //获取设备信息列表
            PageVo pageVo = equipmentManagerService.queryList(equipmentManagerEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询设备列表异常!");
            logger.error("查询设备列表失败:" + e.getMessage(), e);
        }
        return returnJson;
    }


    /**
     * 分页查询设备信息
     *
     * @param equipmentManagerEntity 参数
     * @return 返回值
     */
    @RequestMapping("queryListById")
    @ResponseBody
    public ReturnJson queryListById(HttpServletRequest request, EquipmentManagerEntity equipmentManagerEntity) {
        ReturnJson returnJson;
        try {
            String userId = ContextUtils.getCurrentUserId();
            equipmentManagerEntity.setUserId(userId);
            //获取设备信息列表
            PageVo pageVo = equipmentManagerService.queryListById(equipmentManagerEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("查询设备列表失败:" + e.getMessage(), e);
        }
        return returnJson;
    }


    /**
     * 分页查询设备信息
     *
     * @param equipmentId 参数
     * @return 返回值
     */
    @RequestMapping("toView")
    @ResponseBody
    public ModelAndView queryEquipmentListDatil(@RequestParam(value = "type") int type,
                                                @RequestParam(value = "equipmentId") String equipmentId) {

        ModelAndView mv = new ModelAndView();
        try {
            if (type == 1) {
                //获取设备信息详细列表
                Map<String, Object> list = equipmentManagerService.queryListDtail(equipmentId);
                mv.addObject("list", list);
                mv.setViewName("equipment/equipmentView");
            } else if (type == 2) {
                //获取设备进出场信息详细列表
                EquipmentInOutRecordEntity entity = equipmentManagerService.queryEquipmentInOutRecordDtail(equipmentId);
                mv.addObject("list", entity);
                mv.setViewName("equipment/inAndOutView");
            } else if (type == 3) {
                EquipmentMaintenanceEntity entity = equipmentManagerService.querytEquipmentMaintenanceDtail(equipmentId);
                mv.addObject("list", entity);
                mv.setViewName("equipment/maintenanceView");
            } else if (type == 4) {
                EquipmentDisassemblyEntity entity = equipmentManagerService.querytEquipmentDisassemblyDtail(equipmentId);
                mv.addObject("list", entity);
                mv.setViewName("equipment/disassemblyView");
            }

        } catch (Exception e) {
            logger.error("查询设备列表失败:" + e.getMessage(), e);
        }
        return mv;
    }

    /**
     * 分页查询设备进出场记录
     *
     * @param equipmentInOutRecordEntity 参数
     * @return 返回值
     */
    @RequestMapping("queryInAndOutList")
    @ResponseBody
    public ReturnJson queryEquipmentInOutRecordList(EquipmentInOutRecordEntity equipmentInOutRecordEntity) {
        ReturnJson returnJson;
        try {
        	String userId = ContextUtils.getCurrentUserId();
        	equipmentInOutRecordEntity.setUserId(userId);
            PageVo pageVo = equipmentManagerService.queryEquipmentInOutRecordList(equipmentInOutRecordEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询设备进出场记录失败", e);
        }
        return returnJson;
    }

    /**
     * 根据工地ID查找人员信息
     * */
    @RequestMapping("queryPersonByConstructionId")
    @ResponseBody
    public ReturnJson queryPersonByConstructionId(@RequestParam String constructionId) {
        ReturnJson returnJson;
        try {
            List<Map<String, Object>> map = personService.queryPersonList(constructionId);
            returnJson = ReturnJson.success(map);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询设备进出场记录失败", e);
        }
        return returnJson;
    }

    /**
     * 分页查询设备维修保养记录
     *
     * @param equipmentMaintenanceEntity 参数
     * @return 返回值
     */
    @RequestMapping("queryMaintenanceList")
    @ResponseBody
    public ReturnJson queryMaintenanceList(EquipmentMaintenanceEntity equipmentMaintenanceEntity) {
        ReturnJson returnJson;
        try {
        	String userId = ContextUtils.getCurrentUserId();
        	equipmentMaintenanceEntity.setUserId(userId);
            PageVo pageVo = equipmentManagerService.queryEquipmentMaintenanceList(equipmentMaintenanceEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询设备维修保养记录失败", e);
        }
        return returnJson;
    }

    /**
     * 分页查询设备安装拆卸记录
     *
     * @param equipmentDisassemblyEntity 参数
     * @return 返回值
     */
    @RequestMapping("queryDisassemblyList")
    @ResponseBody
    public ReturnJson queryDisassemblyList(EquipmentDisassemblyEntity equipmentDisassemblyEntity) {
        ReturnJson returnJson;
        try {
        	String userId = ContextUtils.getCurrentUserId();
        	equipmentDisassemblyEntity.setUserId(userId);
            PageVo pageVo = equipmentManagerService.queryEquipmentDisassemblyList(equipmentDisassemblyEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询设备安装拆卸记录失败", e);
        }
        return returnJson;
    }


    /**
     * 设备进出场添加
     *
     * @param equipmentManagerEntity 参数
     * @return 返回值
     */
    @RequestMapping("saveInOutRecord")
    @ResponseBody
    public ReturnJson saveInOutRecord(EquipmentManagerEntity equipmentManagerEntity) {
        ReturnJson returnJson;
        try {
            equipmentManagerService.saveInOutRecord(equipmentManagerEntity);
            String id = equipmentManagerEntity.getEquipmentId();
            returnJson = ReturnJson.success(id);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常");
            logger.error("设备进出场添加失败", e);
        }
        return returnJson;
    }

    /**
     * 设备维修保养添加
     *
     * @param equipmentMaintenanceEntity 参数
     * @return 返回值
     */
    @RequestMapping("saveMaintenance")
    @ResponseBody
    public ReturnJson saveMaintenance(EquipmentMaintenanceEntity equipmentMaintenanceEntity) {
        ReturnJson returnJson;
        try {
            equipmentManagerService.saveMaintenance(equipmentMaintenanceEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常");
            logger.error("设备维修保养添加失败", e);
        }
        return returnJson;
    }

    /**
     * 设备安装拆卸添加
     *
     * @param equipmentDisassemblyEntity 参数
     * @return 返回值
     */
    @RequestMapping("saveDisassembly")
    @ResponseBody
    public ReturnJson saveDisassembly(EquipmentDisassemblyEntity equipmentDisassemblyEntity) {
        ReturnJson returnJson;
        try {
            equipmentManagerService.saveDisassembly(equipmentDisassemblyEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常");
            logger.error("设备安装拆卸添加失败", e);
        }
        return returnJson;
    }


    /**
     * @return
     * @desc 设备查询
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("search")
    public ModelAndView search() {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId", userId);
        mv.setViewName("equipment/search");
        return mv;
    }

    /**
     * @return
     * @desc 设备进出场
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("inAndOut")
    public ModelAndView inAndOut() {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId", userId);
        mv.setViewName("equipment/inAndOut");
        return mv;
    }

    /**
     * @return
     * @desc 设备保养
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("maintenance")
    public ModelAndView maintenance() {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId", userId);
        mv.setViewName("equipment/maintenance");
        return mv;
    }

    /**
     * @return
     * @desc 设备拆卸
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("disassembly")
    public ModelAndView disassembly() {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId", userId);
        mv.setViewName("equipment/disassembly");
        return mv;
    }

    /**
     * @return
     * @desc 设备巡检
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("inspection")
    public ModelAndView inspection() {
        return new ModelAndView("equipment/inspection/list");
    }

    /**
     * @return
     * @desc 设备监测
     * @author zhouwei
     * @date 2017年4月27日 上午11:40:37
     */
    @RequestMapping("monitor")
    public ModelAndView monitor() {
        return new ModelAndView("equipment/monitor");
    }

    /**
     * @desc 设备选择
     * @author dwj
     */
    @RequestMapping("select")
    public ModelAndView select(@RequestParam(value = "type") int type,@RequestParam(value = "constructionId",required=false) String constructionId) {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId",userId);
        mv.addObject("type",type);
        mv.addObject("constructionId",constructionId);
        mv.setViewName("equipment/selectView");
        return mv;
    }

    /**
     * @param type
     * @return
     * @desc 跳转到新增页面
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(@RequestParam(value = "type") Integer type, @RequestParam(value = "constructionId",required=false) String constructionId) {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        if (type == 1) {

            mv.addObject("userId", userId);
            mv.setViewName("equipment/addInAndOut");
        } else if (type == 2) {
            mv.addObject("userId", userId);
            mv.setViewName("equipment/addMaintenance");
        } else if (type == 3) {
            mv.addObject("userId", userId);
            mv.setViewName("equipment/addDisasssembly");
        }
        return mv;
    }

    /**
     * @param queryVO
     * @return
     * @desc 查询环境的设备
     * @author zhouwei
     * @date 2017年8月18日 下午3:50:46
     */
    @RequestMapping("/queryEnvironmentEquipmentList")
    @ResponseBody
    public ReturnJson queryEnvironmentEquipmentList(QueryVO queryVO) {
        ReturnJson returnJson;
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            queryVO.setType(EquipmentTypeEnum.ENVIRONMENT.getValue());
            PageVo pageVO = equipmentManagerService.queryAuthEquipmentList(queryVO);
            returnJson = ReturnJson.success(pageVO);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询环境的设备出现异常");
            logger.error("查询环境的设备出现异常", e);
        }
        return returnJson;
    }

    /**
     * @param queryVO
     * @return
     * @desc 查询塔吊、升降机的监测设备列表,
     * @author zhouwei
     * @date 2017年8月21日 下午3:34:10
     */
    @RequestMapping("/queryMonitorEquipmentList")
    @ResponseBody
    public ReturnJson queryMonitorEquipmentList(QueryVO queryVO) {
        ReturnJson returnJson;
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            String types = EquipmentTypeEnum.TOWERCRANE.getValue() + "," + EquipmentTypeEnum.LIFTER.getValue() + "," + EquipmentTypeEnum.UNLOAD.getValue();
            queryVO.setTypes(types);
            PageVo pageVO = equipmentManagerService.queryAuthEquipmentList(queryVO);
            returnJson = ReturnJson.success(pageVO);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询塔吊、升降机的监测设备列表出现异常");
            logger.error("查询塔吊、升降机的监测设备列表出现异常", e);
        }
        return returnJson;
    }

    /**
     * @param queryVO
     * @return
     * @desc 查询设备巡检记录
     * @author zhouwei
     * @date 2017年8月24日 下午3:18:51
     */
    @RequestMapping("/queryEquipmentInspectionList")
    @ResponseBody
    public ReturnJson queryEquipmentInspectionList(InspectionQueryVO queryVO) {
        ReturnJson returnJson;
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            PageVo pageVO = inspectionService.queryEquipmentInspectionList(queryVO);
            returnJson = ReturnJson.success(pageVO);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询设备巡检记录列表出现异常");
            logger.error("查询设备巡检记录列表出现异常", e);
        }
        return returnJson;
    }

    /**
     * @param request
     * @return
     * @desc 进入巡检详情界面
     * @author zhouwei
     * @date 2017年8月24日 下午3:28:32
     */
    @RequestMapping("/inspection/toView")
    public ModelAndView toInspectionDetail(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("equipment/inspection/view");
        view.addObject("id", request.getParameter("id"));
        return view;
    }

    /**
     * @param id
     * @return
     * @desc 查看巡检详情
     * @author zhouwei
     * @date 2017年8月24日 下午3:36:54
     */
    @RequestMapping("/getEquipmentInspection/{id}")
    @ResponseBody
    public ReturnJson getEquipmentInspection(@PathVariable String id) {
        ReturnJson returnJson;
        try {
            InspectionVO inspectionVO = inspectionService.getEquipmentInspection(id);
            returnJson = ReturnJson.success(inspectionVO);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取设备巡检详情时出现异常");
            logger.error("获取设备巡检详情时出现异常", e);
        }
        return returnJson;
    }



    /**
    * @desc 导出监测设备实时数据
    * @param type
    * @return
    * @author dwj
    * @date 2018/3/16 9:41
    */
    @RequestMapping("exportExcel")
    public void exportExcel(@RequestParam int type, @RequestParam String startDate, @RequestParam String endDate,@RequestParam String equipmentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = null;
        try{
        AcquisitionDataQueryVO vo = new AcquisitionDataQueryVO();
        vo.setEndDate(endDate);
        vo.setStartDate(startDate);
        vo.setEquipmentId(equipmentId);
        // 导出就查询所有符合条件的数据,不用做分页
        vo.setRowNum(Integer.MAX_VALUE);
        Workbook workbook = equipmentManagerService.exportExcel(vo,type);
            String fileName = "";
        if(type == EquipmentTypeEnum.TOWERCRANE.getValue()){
                fileName = "towerCrane_realtime"+DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
        }else if(type == EquipmentTypeEnum.LIFTER.getValue()){
                fileName = "lifter_realtime"+DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
        }
        response.setCharacterEncoding("utf-8");
        // 设置浏览器以附件下载
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        sos = response.getOutputStream();
        workbook.write(sos);
    } catch (Exception e) {
        logger.error("导出excel异常:" + e.getMessage(), e);
    } finally {
            if (sos != null) {
                sos.close();
            }

        }
    }

    /**
    * @desc 塔吊监测
    * @param
    * @return
    * @author dwj
    * @date 2018/4/9 9:22
    */
    @RequestMapping("towerCraneMonitor")
    public ModelAndView towerCraneMonitor() {
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userId", userId);
        mv.setViewName("equipment/towerCraneMonitor");
        return mv;
    }
    
    /**
     * 
     * @desc 根据设备ID查询人脸信息 
     * @param equipmentId
     * @return 
     * @author niejing
     * @date 2018年4月10日 下午2:06:32
     */
	@RequestMapping("/queryByEquipmentId")
	@ResponseBody
	public ReturnJson queryByEquipmentId(FaceQueryVO vo) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = equipmentManagerService.queryByEquipmentId(vo);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("根据设备ID查询人脸信息异常", e);
			returnJson = ReturnJson.fail("根据设备ID查询人脸信息异常");
		}
		return returnJson;
	}
	
	/**
	 * 
	 * @desc 跳转到人脸识别列表页面 
	 * @return 
	 * @author niejing
	 * @date 2018年4月12日 下午1:55:31
	 */
	 @RequestMapping("toFaceList")
	 public ModelAndView toFaceList(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
        mv.setViewName("equipment/faceList");
        return mv;
	 }
	 
}

