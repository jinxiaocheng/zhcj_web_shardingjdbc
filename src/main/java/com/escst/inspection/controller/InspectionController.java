package com.escst.inspection.controller;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.WordUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.commons.vo.TreeVO;
import com.escst.equipment.vo.InspectionVO;
import com.escst.inspection.bean.InspectionRequestBean;
import com.escst.inspection.entity.InspectionCorrectiveProcessEntity;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.enums.InspectionTypeEnum;
import com.escst.inspection.service.InspectionService;
import com.escst.inspection.vo.*;
import com.escst.task.entity.ScheduledPlanEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author caozx
 * @desc 质量检查、安全检查控制器
 * @date 2017年8月8日 上午11:13:50
 */
@Controller
@RequestMapping("inspection")
public class InspectionController {

    private static final Logger logger = LoggerFactory.getLogger(InspectionController.class);

    @Autowired
    private InspectionService inspectionService;

    /**
     * @return
     * @desc 跳转到检查列表页面
     * @author caozx
     * @date 2017年8月8日 上午11:16:50
     */
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        // 质量检查
        if (type.intValue() == 1) {
            mv.setViewName("inspection/list_quality");
        }
        // 施工安全分项检查
        else if (type.intValue() == 2) {
            mv.setViewName("inspection/list");
        }
        // 日常安全检查
        else {
            mv.setViewName("inspection/list_safety_everyday");
        }
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 跳转到查看页面
     *
     * @param type
     * @return
     */
    @RequestMapping("toView")
    public ModelAndView toView(@RequestParam(value = "type") Integer type, InspectionVO inspectionVO) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("type", type);

        //根据id查询详细信息
        InspectionDetailVO vo = inspectionService.queryDetailInfoById(inspectionVO);

        mv.addObject("vo", vo);

        mv.setViewName("inspection/view");

        return mv;
    }


    /**
     * @param inspectionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 根据检查单ID获取检查项树
     * @author jincheng
     * @date 2018-7-10 9:39
     */
    @RequestMapping("loadCheckItemsTree")
    @ResponseBody
    public ReturnJson loadCheckItemTree(InspectionVO inspectionVO) {
        ReturnJson returnJson = null;
        List<InspectionVO> voList = new ArrayList<InspectionVO>();
        List<TreeVO> rst = new ArrayList<TreeVO>();
        boolean flag = inspectionService.isHave(inspectionVO.getId());
        if (flag) {
            List<TreeVO> treeVOS = inspectionService.loadCheckItemTree(inspectionVO);
            returnJson = ReturnJson.success(treeVOS);
        } else {
            List<InspectionVO> inspectionVOS = inspectionService.queryInspectionItem(inspectionVO);
            returnJson = ReturnJson.success(inspectionVOS);
        }
        return returnJson;
    }


    /**
     * 跳转到新增页面
     *
     * @param type
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("type", type);
        // 质量检查
        if (type == 1) {
            mv.setViewName("inspection/add_quality");
        }
        // 施工安全分项检查
        else if (type == 2) {
            mv.setViewName("inspection/add_safety");
        }
        // 日常安全检查
        else {
            mv.setViewName("inspection/add_safety_everyday");
        }
        return mv;
    }

    /**
     * @return
     * @desc 跳转到整改列表页面
     * @author caozx
     * @date 2017年8月8日 上午11:16:50
     */
    @RequestMapping("correctiveList")
    public ModelAndView correctiveList(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("type", type);
        mv.setViewName("inspection/corrective_list");
        return mv;
    }

    /**
     * @return
     * @desc 跳转到整改查看页面
     * @author caozx
     * @date 2017年8月8日 上午11:16:50
     */
    @RequestMapping("toCorrectiveView")
    public ModelAndView toCorrectiveView(@RequestParam(value = "type") Integer type,
                                         @RequestParam(value = "inspectionId") String inspectionId,
                                         @RequestParam(value = "correctiveStatus") Integer correctiveStatus) {
        ModelAndView mv = new ModelAndView();
        //根据id查询详细信息
        InspectionVO inspectionVO = new InspectionVO();
        inspectionVO.setId(inspectionId);
        InspectionDetailVO vo = inspectionService.queryDetailInfoById(inspectionVO);
        mv.addObject("vo", vo);

        //查询整改详细列表信息
        List<CorrectiveDetailVO> list = inspectionService.listCorrectiveProcess(inspectionId);

        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("inspectionId", inspectionId);
        mv.addObject("correctiveStatus", correctiveStatus);
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.setViewName("inspection/corrective_view");
        return mv;
    }

    /**
     * 跳转处理，检查，提交任务
     */
    @RequestMapping("toCorrectiveHandle")
    public ModelAndView toDispose(@RequestParam(value = "type") Integer type,
                                  @RequestParam(value = "inspectionId") String inspectionId,
                                  @RequestParam(value = "correctiveStatus") Integer correctiveStatus) {
        ModelAndView mv = new ModelAndView();

        //根据id查询详细信息
        InspectionVO inspectionVO = new InspectionVO();
        inspectionVO.setId(inspectionId);
        InspectionDetailVO vo = inspectionService.queryDetailInfoById(inspectionVO);
        mv.addObject("vo", vo);

        //查询整改详细列表信息
        List<CorrectiveDetailVO> list = inspectionService.listCorrectiveProcess(inspectionId);

        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.addObject("inspectionId", inspectionId);
        mv.addObject("correctiveStatus", correctiveStatus);
        mv.addObject("list", list);
        mv.addObject("type", type);
        mv.setViewName("inspection/corrective_handle");
        return mv;
    }

    /**
     * 通过工地id查询质量检查或者安全检查列表
     *
     * @param inspectionRequestBean
     * @return
     */
    @RequestMapping("queryByConstructionId")
    @ResponseBody
    public ReturnJson queryByConstructionId(InspectionRequestBean inspectionRequestBean) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = inspectionService.listByConstructionId(inspectionRequestBean);
            returnJson = ReturnJson.success(pageVo);
        } catch (EscstException e) {
            logger.error("查询检查记录出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

    /**
     * 新增质量检查或者安全检查记录
     *
     * @param inspectionEntity
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnJson save(InspectionEntity inspectionEntity) {
        ReturnJson returnJson = null;
        try {
            inspectionService.save(inspectionEntity);
            returnJson = ReturnJson.success();

            // 新增整改任务成功后，向消息记录表添加消息,并给手机端推送消息
            inspectionService.savePushMsg(inspectionEntity);

        } catch (EscstException e) {
            logger.error("保存检查记录出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

    /**
     * 查询整改处理列表
     *
     * @param inspectionId
     * @return
     */
    @RequestMapping("queryCorrectiveProcessList")
    @ResponseBody
    public ReturnJson queryByConstructionId(String inspectionId) {
        ReturnJson returnJson = null;
        try {
            List<CorrectiveDetailVO> list = inspectionService.listCorrectiveProcess(inspectionId);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            logger.error("查询检查记录出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * 提交保存整改处理详细信息
     *
     * @param inspectionCorrectiveProcessEntity
     * @return
     */
    @RequestMapping("saveCorrectiveProcess")
    @ResponseBody
    public ReturnJson saveCorrectiveProcess(InspectionCorrectiveProcessEntity inspectionCorrectiveProcessEntity) {
        ReturnJson returnJson;
        try {
            inspectionService.saveCorrectiveProcess(inspectionCorrectiveProcessEntity);
            returnJson = ReturnJson.success();

            //提交保存成功后，向消息记录表添加消息,并给手机端推送消息
            inspectionService.saveCorrectiveProcessPushMsg(inspectionCorrectiveProcessEntity);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常");
            logger.error("提交保存整改处理详细信息出现异常:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 质量检查、整改月统计数
     * @author zhouwei
     * @date 2017年8月26日 下午3:11:19
     */
    @RequestMapping("/quality/queryInspectionMonthQtyList")
    @ResponseBody
    public ReturnJson queryQualityInspectionMonthQtyList(@RequestBody InspectionQueryVO queryVO) {
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            queryVO.setTypes(String.valueOf(InspectionTypeEnum.QUALITY.getValue()));

            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH);
            if (month < 5) {//如果当前月份不到6月份,则开始时间从当前时间向前推6个月
                cal.add(Calendar.MONTH, -5);
            } else {
                cal.set(Calendar.MONTH, 0);
            }
            cal.set(Calendar.DATE, 1);//开始时间从月的第一天开始
            queryVO.setStartDate(DateUtils.format(cal.getTime(), DateUtils.TO_DATE));
            queryVO.setEndDate(DateUtils.format(new Date(), DateUtils.TO_DATE));
            List<InspectionQtyVO> list = inspectionService.queryInspectionMonthQtyList(queryVO);
            return ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("质量检查、整改月统计数出现异常:" + e.getMessage(), e);
            return ReturnJson.fail("质量检查、整改月统计数现异常");
        }
    }

    /**
     * @return
     * @desc 安全检查、整改月统计数
     * @author zhouwei
     * @date 2017年8月26日 下午3:11:53
     */
    @RequestMapping("/safety/queryInspectionMonthQtyList")
    @ResponseBody
    public ReturnJson querySafetyInspectionMonthQtyList(@RequestBody InspectionQueryVO queryVO) {
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            String types = InspectionTypeEnum.SAFETY_ITEMS.getValue() + "," + InspectionTypeEnum.SAFETY_EVERYDAY.getValue();
            queryVO.setTypes(types);

            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH);
            if (month < 5) {//如果当前月份不到6月份,则开始时间从当前时间向前推6个月
                cal.add(Calendar.MONTH, -5);
            } else {
                cal.set(Calendar.MONTH, 0);
            }
            cal.set(Calendar.DATE, 1);//开始时间从月的第一天开始
            queryVO.setStartDate(DateUtils.format(cal.getTime(), DateUtils.TO_DATE));
            queryVO.setEndDate(DateUtils.format(new Date(), DateUtils.TO_DATE));
            List<InspectionQtyVO> list = inspectionService.queryInspectionMonthQtyList(queryVO);
            return ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("安全检查、整改月统计数出现异常:" + e.getMessage(), e);
            return ReturnJson.fail("安全检查、整改月统计数现异常");
        }
    }

    /**
     * @return
     * @desc 得到安全检查项目
     * @author zhouwei
     * @date 2017年10月17日 下午2:31:32
     */
    @RequestMapping("/querySafetyCheckItems")
    @ResponseBody
    public ReturnJson querySafetyCheckItems(CheckItemsVO checkItemsVO) {
        ReturnJson returnJson = null;
        try {
            List<CheckItemsVO> list = inspectionService.queryTopSafetyCheckItems(checkItemsVO);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            returnJson = ReturnJson.fail("获取安全检查项目异常");
            logger.error("获取安全检查项目异常" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @desc 根据检查项得到检查结果列表
     * @param vo
     * @return
     * @author zhouwei
     * @date 2017年10月17日 下午2:36:03
     */
    /*@RequestMapping("/querySafetyCheckResults")
	@ResponseBody
	public ReturnJson querySafetyCheckResults(@RequestBody InspectionQueryVO vo) {
		ReturnJson returnJson = null;
		try {
			List<ParentVO> list = InspectionDataService.queryCheckResultsByParentItemsId(vo.getItemsId());
			returnJson = ReturnJson.success(list);
		} catch (EscstException e) {
			returnJson = ReturnJson.fail("根据检查项得到检查结果列表异常");
			logger.error("根据检查项得到检查结果列表异常" + e.getMessage(), e);
		}
		return returnJson;
	}*/


    /**
     * @return
     * @desc 得到安全检查项和检查结果的树
     * @author zhouwei
     * @date 2017年11月21日 上午9:58:42
     */
    @RequestMapping("/querySafetyCheckResultsTree")
    @ResponseBody
    public ReturnJson querySafetyCheckResultsTree(CheckItemsVO checkItemsVO) {
        ReturnJson returnJson = null;
        try {
            List<TreeEntity> list = inspectionService.querySafetyItemsResultsTree(checkItemsVO);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            returnJson = ReturnJson.fail("得到安全检查项和检查结果的树异常");
            logger.error("得到安全检查项和检查结果的树异常" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @param bean
     * @return
     * @throws IOException
     * @desc 施工安全分项检查导出word
     * @author niejing
     * @date 2017年12月14日 下午5:20:31
     */
    @RequestMapping("/exportWord")
    public void exportWord(HttpServletRequest req, HttpServletResponse resp, String id, Integer type, Integer flag) throws ServletException, IOException {
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            req.setCharacterEncoding("utf-8");

            //获取word文档中的数据
            Map<String, Object> map = inspectionService.queryWordInfo(id, type, flag);
            file = WordUtils.createDoc(map, "inspection.ftl");

            fin = new FileInputStream(file);

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件默认名为inspection.doc
            resp.addHeader("Content-Disposition", "attachment;filename=inspection.doc");

            out = resp.getOutputStream();
            byte[] buffer = new byte[512]; // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception e) {
            logger.error("导出word异常:" + e.getMessage(), e);
        } finally {
            if (fin != null)
                fin.close();
            if (out != null)
                out.close();
            if (file != null) {
                file.delete();
            }
        }
    }

    /**
     * @param uuid
     * @return
     * @desc 得到安全检查部位
     * @author jincheng
     * @date 2018年1月21日 下午2:31:32
     */
    @RequestMapping("/queryInspectionProjectStructure")
    @ResponseBody
    public ReturnJson queryInspectionProjectStructure(InspectionProjectStructureVO vo) {
        ReturnJson returnJson = null;
        try {
            List<ScheduledPlanEntity> list = inspectionService.queryInspectionProjectStructure(vo);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            returnJson = ReturnJson.fail("获取安全检查部位异常");
            logger.error("获取安全检查部位异常" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到分项检查项维护页面
     * @author jincheng
     * @date 2018/5/14 15:53
     */
    @RequestMapping("toSubentryCheckItem")
    public ModelAndView toCheckItem() {
        ModelAndView view = new ModelAndView();
        view.setViewName("inspection/subentry_check_item");
        return view;
    }

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到日常检查项维护页面
     * @author jincheng
     * @date 2018/5/14 15:53
     */
    @RequestMapping("toEverydayCheckItem")
    public ModelAndView toEverydayCheckItem() {
        ModelAndView view = new ModelAndView();
        view.setViewName("inspection/everyDay_check_item");
        return view;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 查询分项检查项树
     * @author jincheng
     * @date 2018/1/23 10:18
     */
    @RequestMapping("loadCheckItemTree")
    @ResponseBody
    public ReturnJson listCheckItemTree(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = inspectionService.listCheckItemTree(treeEntity);
            returnJson = ReturnJson.easyUI(pageVo);
        } catch (Exception e) {
            logger.error("查询检查项树异常", e);
            returnJson = ReturnJson.fail("查询检查项树异常:" + e.getMessage());

        }
        return returnJson;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 添加分项检查
     * @author jincheng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("addCheckItem")
    @ResponseBody
    public ReturnJson addCheckItem(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            inspectionService.addCheckItem(treeEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("添加分项检查异常", e);
            returnJson = ReturnJson.fail("添加分项检查异常" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 修改分项检查
     * @author jincheng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("updateCheckItem")
    @ResponseBody
    public ReturnJson updateCheckItem(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            String s = inspectionService.updateCheckItem(treeEntity);
            returnJson = ReturnJson.success(s);
        } catch (Exception e) {
            logger.error("修改分项检查异常", e);
            returnJson = ReturnJson.fail("修改分项检查异常" + e.getMessage());
        }
        return returnJson;
    }


    /**
     * @param treeEntity
     * @return
     * @desc 删除分项检查
     * @author jinchng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("deleteCheckItem")
    @ResponseBody
    public ReturnJson deleteCheckItem(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            String s = inspectionService.deleteCheckItem(treeEntity);
            returnJson = ReturnJson.success(s);
        } catch (Exception e) {
            logger.error("删除分项检查异常", e);
            returnJson = ReturnJson.fail("删除分项检查异常" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param bean
     * @return
     * @desc 获取某个工地的某个公司下的某个检查项的整改数量
     * @author dwj
     * @date 2018/5/16 15:42
     */
    @RequestMapping("selectItemsByCompany")
    @ResponseBody
    public ReturnJson selectItemsByCompany(InspectionRequestBean bean) {
        ReturnJson returnJson = null;
        try {
            List<ItemsCountVo> list = inspectionService.selectItemsByConstructionId(bean);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("获取分包公司安全，质量检查数异常", e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常" + e.getMessage());
        }
        return returnJson;
    }


    /**
     * @param
     * @return ModelAndView
     * @desc 安全检查报表
     * @author dwj
     * @date 2018/5/17 13:16
     */
    @RequestMapping("toSafetyItemReport")
    public ModelAndView toSafetyItemReport() {
        ModelAndView view = new ModelAndView();
        view.setViewName("inspection/safetyItem_report");
        return view;
    }

    /**
     * @param
     * @return ModelAndView
     * @desc 跳转到分项检查项维护页面
     * @author dwj
     * @date 2018/5/17 13:16
     */
    @RequestMapping("toQualityItemReport")
    public ModelAndView toQualityItemReport() {
        ModelAndView view = new ModelAndView();
        view.setViewName("inspection/qualityItem_report");
        return view;
    }


    /**
     * @param entity
     * @return com.escst.commons.vo.ReturnJson
     * @desc 南钢项目数据监控大屏，获取检查单记录
     * @author jincheng
     * @date 2018-8-17 14:59
     */
    @RequestMapping("listInspection")
    @ResponseBody
    public ReturnJson listInspection(InspectionEntity entity) {
        ReturnJson returnJson = null;
        try {
            List<InspectionVO> list = inspectionService.listInspection(entity);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("获取检查单记录异常");
            logger.error("获取检查单记录失败");
        }
        return returnJson;

    }


}
