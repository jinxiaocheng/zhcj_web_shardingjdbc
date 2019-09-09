package com.escst.projectStructure.controller;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.BrowserUtils;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.projectStructure.service.ProjectStructureService;
import com.escst.quality.service.QualityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 11:59 2017/4/24
 */
@Controller
@RequestMapping("projectStructure")
public class ProjectStructureController {

    private static Logger logger = LoggerFactory.getLogger(ProjectStructureController.class);

    @Autowired
    private ProjectStructureService projectStructureService;

    @Autowired
    private ConstructionService constructionService;
    @Autowired
    private QualityService qualityService;

    @RequestMapping("list")
    public ModelAndView list(Model model) {
        return new ModelAndView("projectStructure/visibleConstructionList");
    }

    @RequestMapping("toSave")
    public ModelAndView add(Model model, @RequestParam("constructionId") String constructionId, @RequestParam("type") int type) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        model.addAttribute("type", type);
        model.addAttribute("constructionId", constructionId);
        Map<String, Object> map = constructionService.queryConstructionById(constructionId);
        if (map != null) {
            model.addAttribute("constructionName", map.get("name"));
            model.addAttribute("areaName", map.get("areaName"));
        }
        return new ModelAndView("projectStructure/structureAdd");
    }

    @RequestMapping("visibleConstructionList")
    @ResponseBody
    public ReturnJson visibleConstructionList(SimpleConstructionVO entity) {
        ReturnJson returnJson;
        try {
            PageVo vo = constructionService.queryVisibleConstruction(entity);
            returnJson = ReturnJson.success(vo);
        } catch (Exception e) {
            logger.error("查询检查部位失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询检查部位信息失败");
        }
        return returnJson;
    }

    /**
     * 查看检查部位
     */
    @RequestMapping("toProjectStructureList")
    public ModelAndView toProjectStructureList(Model model, String constructionId) {
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("projectStructure/list");
    }

    /**
     * 查询检查部位
     */
    @RequestMapping("projectStructureList")
    @ResponseBody
    public ReturnJson projectStructureList(ProjectStructureEntity projectStructrueEntity) {
        ReturnJson returnJson;
        try {
            List<TreeEntity> list = projectStructureService.queryTreeByConstructionId(projectStructrueEntity.getConstructionId());
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询检查部位失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询检查部位信息失败");
        }
        return returnJson;
    }

    /**
     * 查询检查部位子结构
     */
    @RequestMapping("queryByNodeId")
    @ResponseBody
    public ReturnJson queryByNodeId(ProjectStructureEntity projectStructrueEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = projectStructureService.queryByNodeId(projectStructrueEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询检查部位子结构出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询检查部位子结构出现异常：" + e.getMessage());
        }
        return returnJson;
    }

    @RequestMapping("fetchStartPreviewParam")
    public ModelAndView fetchStartPreviewParam(Model model, String nodeId) {
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("projectStructure/treeList");
    }

    /**
     * 检查部位导数据导出
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, ProjectStructureEntity projectStructrueEntity) {
        String codedFileName;
        try {
            String dateStr = DateUtils.getCurrentDate();
            String parentId = java.net.URLDecoder.decode(projectStructrueEntity.getParentId(), "utf-8");
            projectStructrueEntity.setName(parentId);
            codedFileName = "检查部位信息导出" + dateStr;
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xlsx");
            } else {
                String newTitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newTitle + ".xlsx");
            }
            projectStructrueEntity.setConstructionId("8aad89d1545b00420154c1b4573a0364");
            projectStructureService.exportExcel(request, response, projectStructrueEntity);
        } catch (Exception e) {
            logger.error("检查部位导出异常：" + e.getMessage(), e);
        }
    }

    /**
     * @param model
     * @param constructionId
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到工程结构导入界面
     * @author jincheng
     * @date 2018/5/4 9:31
     */
    @RequestMapping("toImport")
    public ModelAndView toImport(Model model, String constructionId) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("projectStructure/toImport");
    }

    /**
     * 检查部位数据导入
     *
     * @param constructionId
     * @param file           文件
     * @param constructionId 工地ID
     * @return
     */
    @RequestMapping("saveFile")
    @ResponseBody
    public ReturnJson saveFile(MultipartFile file, String constructionId) {
        ReturnJson returnJson = null;
        String result = null;
        try {
            result = projectStructureService.importDataFromExcel(file, constructionId);
//            qualityService.importDataFromExcel(file,constructionId);
            logger.info(result);
            if (!StringUtils.isEmpty(result)) {
                returnJson = ReturnJson.fail(result);
            } else {
                returnJson = ReturnJson.success();
            }
        } catch (EscstException e) {
            logger.error("导入检查部位数据出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("导入数据出现异常");
        }
        return returnJson;
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(Model model, String nodeId) {
        model.addAttribute("nodeId", nodeId);
        return new ModelAndView("projectStructure/add");
    }

    @RequestMapping("add")
    @ResponseBody
    public ReturnJson add(ProjectStructureEntity projectStructureEntity) {
        ReturnJson returnJson = null;
        try {
            projectStructureService.addProjectStructure(projectStructureEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增检查部位信息异常", e);
            returnJson = ReturnJson.fail("新增检查部位信息异常：" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param projectStructureEntity
     * @return
     * @desc 查询工程结构或者进度树形结构
     * @author jincheng
     * @date 2018/1/23 10:18
     */
    @RequestMapping("loadTree")
    @ResponseBody
    public ReturnJson listProjectStructureTree(ProjectStructureEntity projectStructureEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = projectStructureService.listProjectStructureTree(projectStructureEntity);
            returnJson = ReturnJson.easyUI(pageVo);
        } catch (Exception e) {
            logger.error("查询工程结构或者进度计划异常", e);
            returnJson = ReturnJson.fail("查询工程结构或者进度计划异常:" + e.getMessage());

        }
        return returnJson;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 添加工程结构
     * @author jincheng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("addStructure")
    @ResponseBody
    public ReturnJson addStructure(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            projectStructureService.addStructure(treeEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("批量添加工程结构异常", e);
            returnJson = ReturnJson.fail("批量添加工程结构异常" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 修改工程结构
     * @author jincheng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("updateStructure")
    @ResponseBody
    public ReturnJson updateStructure(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            projectStructureService.updateStructure(treeEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("修改工程结构异常", e);
            returnJson = ReturnJson.fail("修改工程结构异常" + e.getMessage());
        }
        return returnJson;
    }

    /**
     * @param treeEntity
     * @return
     * @desc 删除工程结构
     * @author jinchng
     * @date 2018/1/23 10:33
     */
    @RequestMapping("deleteStructure")
    @ResponseBody
    public ReturnJson deleteStructure(TreeEntity treeEntity) {
        ReturnJson returnJson = null;
        try {
            projectStructureService.deleteStructure(treeEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("删除工程结构异常", e);
            returnJson = ReturnJson.fail("删除工程结构异常" + e.getMessage());
        }
        return returnJson;
    }


}
