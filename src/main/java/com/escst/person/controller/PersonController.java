package com.escst.person.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escst.projectCompany.enums.CompanyTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.escst.attendance.service.AttendanceCountService;
import com.escst.attendance.vo.AttendanceNumQueryVO;
import com.escst.attendance.vo.AttendanceNumRstVO;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.PageAuthVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.QtyVO;
import com.escst.commons.vo.ReturnJson;
import com.escst.person.entity.PersonConditionBean;
import com.escst.person.entity.PersonEntity;
import com.escst.person.entity.PersonExcelEntity;
import com.escst.person.service.PersonService;
import com.escst.person.vo.PersonQueryVO;
import com.escst.person.vo.WorkTypeQtyVO;
import com.escst.person.vo.WorkTypeQueryVO;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.territory.service.TerritoryService;

/**
 * @author zhouwei 2017-3-6
 * @desc 人员管理
 */
@Controller
@RequestMapping("person")
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    public TerritoryService territoryService;

    @Autowired
    public ProjectCompanyService projectCompanyService;

    @Autowired
    private AttendanceCountService attendanceCountService;

    @RequestMapping("list")
    public ModelAndView toPersonList(Model model) {
        // 查询所有区
//		List<TerritoryEntity> terrList = territoryService.queryTerritoryListByParentId(WUHAN_ID);
//		model.addAttribute("terrList", terrList);
        return new ModelAndView("person/list");
    }

    /**
     * @param
     * @return
     * @desc
     * @author zhangkaiqiang
     * @date 2017年4月5日 上午11:08:34
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("person/add");
        view.addObject("personId", request.getParameter("personId"));
        return view;
    }

    @RequestMapping("toView")
    public ModelAndView toView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("person/view");
        view.addObject("personId", request.getParameter("personId"));
        return view;
    }

    /**
     * @param request
     * @return
     * @desc 跳转到人员选择列表
     * @author zhouwei
     * @date 2017年11月3日 上午9:06:06
     */
    @RequestMapping("toSelectList")
    public ModelAndView toSelectList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("person/selectList");
        view.addObject("constructionId", request.getParameter("constructionId"));
        return view;
    }


    /**
     * @param null
     * @return
     * @desc 跳转到工种选择页面
     * @author kz
     * @date 2018/1/30 10:21
     */
    @RequestMapping("toWorkTable")
    public ModelAndView toWorkTable() {
        ModelAndView view = new ModelAndView("person/workTable");
        return view;
    }

    /**
     * 工地人员列表
     *
     * @param bean 参数
     * @return 返回值
     */
    @RequestMapping("personList")
    @ResponseBody
    public ReturnJson queryPersonList(HttpServletRequest request, PersonConditionBean bean) {
        ReturnJson returnJson;
        try {
            bean.setUserId(ContextUtils.getCurrentUserId());
            // 获取人员列表
            PageVo pageVo = personService.queryPersonPageList(bean);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("查询人员列表失败:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * 查询人员详细信息
     *
     * @param personId 人员ID
     * @return 返回值
     */
    @RequestMapping("detailInfo")
    @ResponseBody
    public ReturnJson detailInfo(@RequestParam String personId) {
        ReturnJson returnJson;
        try {
            PersonEntity entity = personService.getPersonById(personId);
            returnJson = ReturnJson.success(entity);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("查询人员详细信息失败:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * 删除人员
     *
     * @param personId 人员ID
     * @return 返回值
     */
    @RequestMapping("delPerson")
    @ResponseBody
    public ReturnJson delPerson(@RequestParam String personId, @RequestParam String constructionId, @RequestParam String clientUserId, @RequestParam String personName) {
        ReturnJson returnJson;
        try {
            personService.delPerson(personId, constructionId, clientUserId, personName);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常!");
            logger.error("删除人员失败:" + e.getMessage(), e);
        }
        return returnJson;
    }


    @RequestMapping("addOrUpdatePerson")
    @ResponseBody
    public ReturnJson addOrUpdatePerson(PersonEntity personEntity) {
        ReturnJson returnJson;
        try {
            personService.addOrUpdatePerson(personEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            String msg = null;
            if (e instanceof EscstException) {
                msg = e.getMessage();
            } else {
                msg = "保存用户出现异常!";
            }
            returnJson = ReturnJson.fail(msg, ReturnJson.STATUS_CARNO_ISEXIST);
            logger.error("新增或修改人员失败:" + msg, e);
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 根据person生成user
     * @author niejing
     * @date 2018年3月14日 下午2:10:50
     */
    @RequestMapping("generateUser")
    @ResponseBody
    public ReturnJson generateUser(String[] ids) {
        ReturnJson returnJson;
        try {
            personService.generateUser(ids);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("生成user异常", e);
            returnJson = ReturnJson.fail("生成user异常");
        }
        return returnJson;
    }

    @RequestMapping("toImport")
    public ModelAndView toImport(HttpServletRequest request) {
        return new ModelAndView("person/toImport");
    }

    @RequestMapping("personComposition")
    public ModelAndView personComposition(HttpServletRequest request) {
        return new ModelAndView("person/composition");
    }

    /**
     * 人员数据导入
     *
     * @param request
     * @param response
     * @param file           文件
     * @param constructionId 工地ID
     * @return
     */
    @RequestMapping("import")
    @ResponseBody
    public void importPerson(MultipartRequest request, HttpServletResponse response, String constructionId, MultipartFile file) throws IOException {
        ReturnJson returnJson = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            String msg = personService.importDataFromExcel(file, constructionId);
            returnJson = ReturnJson.success(msg);
        } catch (Exception e) {
            logger.error("导入数据出现错误：" + e.getMessage(), e);
            if (e instanceof EscstException) {
                returnJson = ReturnJson.fail(e.getMessage());
            } else {
                returnJson = ReturnJson.fail("导入数据出现异常");
            }
        }
        String data = JSONObject.toJSONString(returnJson);
        response.getWriter().write(data);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * @param bean
     * @return
     * @desc 导出人员信息
     * @author zhouwei
     * @date 2017年10月30日 上午11:02:31
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public ReturnJson exportExcel(PersonConditionBean bean) {
        ReturnJson returnJson = null;
        try {
            bean.setRowNum(Integer.MAX_VALUE);//导出就查询所有符合条件的数据,不用做分页
            bean.setUserId(ContextUtils.getCurrentUserId());
            List<PersonExcelEntity> list = personService.queryExportPersonList(bean);
            Map<String, Object> beanParams = new HashMap<String, Object>();
            beanParams.put("list", list);
            String filePath = ExcelUtils.createExcel("person.xls", beanParams);
            returnJson = ReturnJson.success(filePath);
        } catch (Exception e) {
            logger.error("导出excel异常:" + e.getMessage(), e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param id 市ID或者区ID或者工地ID
     * @return
     * @desc 获取有权限访问的工地的各工种的人数
     * @author zhouwei
     * @date 2017年8月15日 下午4:30:04
     */
    @RequestMapping("/queryWorkTypePersonQtyPage")
    @ResponseBody
    public ReturnJson queryWorkTypePersonQtyPage(PageAuthVO authVO) {
        ReturnJson returnJson = null;
        try {
            authVO.setUserId(ContextUtils.getCurrentUserId());
            PageVo page = personService.queryAuthWorkTypePersonQtyPage(authVO);

            returnJson = ReturnJson.success(page);
        } catch (EscstException e) {
            logger.error("获取工种数时出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取工种数时出现异常");
        }
        return returnJson;
    }

    /**
     * @return
     * @desc 得到各工种的人数
     * @author zhouwei
     * @date 2017年8月25日 下午2:12:53
     */
    @RequestMapping("/queryWorkTypePersonQty")
    @ResponseBody
    public ReturnJson queryWorkTypePersonQty(BaseAuthVO authVO) {
        ReturnJson returnJson = null;
        try {
            authVO.setUserId(ContextUtils.getCurrentUserId());
            List<QtyVO> list = attendanceCountService.queryAuthWorkTypePersonQty(authVO);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            logger.error("获取工种数时出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取工种数时出现异常");
        }
        return returnJson;
    }

    /**
     * @param model
     * @return
     * @desc 工种人数统计页面
     * @author zhouwei
     * @date 2017年8月15日 下午7:16:59
     */
    @RequestMapping("toWorkTypePersonQty")
    public ModelAndView toWorkTypePersonQty(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("person/workTypePersonQty");
        view.addObject("id", request.getParameter("id"));
        return view;
    }

    /**
     * 查询工地上的人员
     *
     * @param entity
     * @return
     */
    @RequestMapping("queryByConstructionId")
    @ResponseBody
    public ReturnJson queryByConstructionId(PersonEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = personService.queryPersonListByPage(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (EscstException e) {
            logger.error("查询工地上的人员出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询工地上的人员出现异常！工地Id：" + entity.getConstructionId());
        }
        return returnJson;
    }


    /**
     * 查询工地上的人员
     *
     * @param entity
     * @return
     */
    @RequestMapping("queryPersonByConstructionId")
    @ResponseBody
    public ReturnJson queryPersonByConstructionId(PersonEntity entity) {
        ReturnJson returnJson = null;
        try {
            List<PersonEntity> entityList = personService.getPersonByConstructionId(entity);
            returnJson = ReturnJson.success(entityList);
        } catch (EscstException e) {
            logger.error("查询工地上的人员出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询工地上的人员出现异常！工地Id：" + entity.getConstructionId());
        }
        return returnJson;
    }

    /**
     * @param personQueryVO
     * @return
     * @desc 查询可以选择的工地人员列表
     * @author zhouwei
     * @date 2017年11月3日 上午9:30:36
     */
    @RequestMapping("constructionPersonList")
    @ResponseBody
    public ReturnJson constructionPersonList(PersonQueryVO personQueryVO) {
        ReturnJson returnJson;
        try {
            // 获取人员列表
            String companyTypes = String.valueOf(CompanyTypeEnum.BUILDING.getValue());
            personQueryVO.setCompanyTypes(companyTypes);
            PageVo pageVo = personService.queryConstructionPersonList(personQueryVO);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询工地人员列表异常!");
            logger.error("查询工地人员列表异常:" + e.getMessage(), e);
        }
        return returnJson;
    }

    /**
     * @param authVO
     * @return
     * @desc 得到最近七天所有工种数量
     * @author niejing
     * @date 2017年11月6日 下午4:44:12
     */
    @RequestMapping("/query7DaysWorkTypeQty")
    @ResponseBody
    public ReturnJson query7DaysWorkTypeQty(@RequestBody WorkTypeQueryVO authVO) {
        ReturnJson returnJson = null;
        try {
            authVO.setUserId(ContextUtils.getCurrentUserId());
            WorkTypeQtyVO list = personService.query7DaysWorkTypeQty(authVO);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            logger.error("获取工种数量异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取工种数量异常");
        }
        return returnJson;
    }

    /**
     * @param attendanceNumVO
     * @return
     * @desc 得到最近七天工地的考勤统计信息
     * @author niejing
     * @date 2017年11月6日 下午5:01:22
     */
    @RequestMapping("/queryAttendanceNumQty")
    @ResponseBody
    public ReturnJson queryAttendanceNumQty(@RequestBody AttendanceNumQueryVO attendanceNumVO) {
        ReturnJson returnJson = null;
        try {
            attendanceNumVO.setUserId(ContextUtils.getCurrentUserId());
            AttendanceNumRstVO vo = this.attendanceCountService.queryAttendanceNumQty(attendanceNumVO);
            returnJson = ReturnJson.success(vo);
        } catch (EscstException e) {
            logger.error("获取最近七天个工地的考勤统计信息异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取最近七天个工地的考勤统计信息异常");
        }
        return returnJson;
    }


    /**
     * @return
     * @desc 获取工施工单位人员
     * @author dwj
     * @date 2017年11月13日 下午10:48
     */
    @RequestMapping("queryWorkTypeByConstructionId")
    @ResponseBody
    public ReturnJson queryWorkTypeByConstructionId(String constructionId) {
        ReturnJson returnJson = null;
        try {
            //获取人员列表
            String companyTypes = String.valueOf(CompanyTypeEnum.BUILDING.getValue());
            List<Map<String, Object>> list = personService.querypersonByConstructionId(constructionId,companyTypes);
            returnJson = ReturnJson.success(list);
        } catch (EscstException e) {
            logger.error("获取工施工单位人员出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获获取工施工单位人员出现异常");
        }
        return returnJson;
    }

    /**
     * @param
     * @return
     * @desc 通过身份证阅读器获取人员信息
     * @author dwj
     * @date 2018/1/22 14:14
     */
    @RequestMapping("getPersonByIdCard")
    @ResponseBody
    public ReturnJson getPersonByIdCard() {
        ReturnJson returnJson = null;
        try {
            PersonEntity entity = personService.getPersonByIdCard();
            returnJson = ReturnJson.success(entity);
        } catch (Exception e) {
            logger.error("获取身份证阅读器人员信息异常:" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取身份证阅读器人员信息异常");
        }
        return returnJson;
    }

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到人脸抓拍页面
     * @author jincheng
     * @date 2018/3/7 10:16
     */
    @RequestMapping("toFace")
    public ModelAndView toFace() {
        ModelAndView view = new ModelAndView("person/face");
        return view;
    }

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到人脸对比页面
     * @author jincheng
     * @date 2018/3/7 10:16
     */
    @RequestMapping("toFaceContrast")
    public ModelAndView toFaceContrast() {
        ModelAndView view = new ModelAndView("person/faceContrast");
        return view;
    }


    /**
     * @param projectCompanyId 分包公司ID
     * @return
     * @desc 根据分包公司获得班组列表
     * @author jincheng
     * @date 2018-6-4 10:55
     */
    @RequestMapping("listTeam")
    @ResponseBody
    public ReturnJson listTeam(String projectCompanyId) {
        ReturnJson returnJson = null;
        try {
            List<PersonEntity> list = personService.listTeam(projectCompanyId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("根据分包公司获得班组列表异常:" + e.getMessage(), e);
            returnJson = ReturnJson.fail("根据分包公司获得班组列表异常");
        }
        return returnJson;

    }

}
