package com.escst.projectStructure.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.service.ScheduledPlanService;
import com.escst.construction.vo.ScheduledPlanTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.projectStructure.entity.ProjectStructureExcelEntity;
import com.escst.projectStructure.mapper.ProjectStructureMapper;
import com.escst.redis.annotation.RedisCache;
import com.escst.task.service.ProjectTaskService;

/**
 * @author dwj
 * @desc
 * @date 11:58 2017/4/24
 */
@Service
@Transactional
public class ProjectStructureService {

    private static final String EXCEL = "excel";

    @Autowired
    private FileService fileService;

    @Autowired
    private ProjectStructureMapper projectStructureMapper;

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ScheduledPlanService scheduledPlanService;

    // private static final String ROOT_ID = "0";
    // private static final String ROOT_PID = "-1";
    // private static final String ROOT_NAME = "检查部位";

    /**
     * @param constructionId
     * @return
     * @desc 查询检查部位的树
     * @author zhouwei
     * @date 2017年11月9日 下午5:42:08
     */
    public List<TreeEntity> queryTreeByConstructionId(String constructionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionId", constructionId);
        List<TreeEntity> list = projectStructureMapper.queryProjectStructrue(map);
        if (!CollectionUtils.isEmpty(list)) {
            for (TreeEntity treeEntity : list) {
                for (TreeEntity entity : list) {
                    if (treeEntity.getId().equals(entity.getpId())) {
                        treeEntity.setOpen(false);
                        continue;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 查询检查部位
     *
     * @param list
     * @return
     */
    public List<TreeEntity> queryByParentId(List<TreeEntity> list, String parentId, String constructionId) {
        if (list == null) {
            list = new ArrayList<TreeEntity>();
        }
        if (StringUtils.isBlank(parentId)) {
            parentId = "";
        }
        List<ProjectStructureEntity> projectStructureList = projectStructureMapper.selectByParentId(parentId, constructionId);
        if (CollectionUtils.isEmpty(projectStructureList)) {
            return list;
        }
        for (ProjectStructureEntity entity : projectStructureList) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setpId(parentId);
            treeEntity.setId(entity.getId());
            treeEntity.setName(entity.getName());
            // if(StringUtils.equals(parentId, "1")){
            if (StringUtils.isBlank(parentId)) {
                treeEntity.setOpen(false);
            } else {
                treeEntity.setOpen(true);
            }
            list.add(treeEntity);
            // 递归获取子节点
            queryByParentId(list, entity.getId(), constructionId);
        }
        return list;
    }

    /**
     * 通过ID查询检查部位信息
     */
    public PageVo queryByNodeId(ProjectStructureEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            int count = projectStructureMapper.selectByParentIdCount(entity.getParentId());
            // 当前页
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            // Map<String, Object> paramMap = new HashMap<String, Object>();
            // String constructionId = "8aad89d1545b00420154c1b4573a0364";
            // List<Map<String, Object>> list = new ArrayList<Map<String,
            // Object>>();
            // if (entity.getParentId().equals("1")) {
            // paramMap.put("constructionId", constructionId);
            // list = projectStructureMapper.selectProjectById(paramMap);
            // } else {
            // paramMap.put("constructionId", constructionId);
            // paramMap.put("parentId", entity.getParentId());
            // list =
            // projectStructureMapper.selectProjectStructureById(paramMap);
            // }
            List<ProjectStructureEntity> projectStructureList = projectStructureMapper.selectByParentId(entity.getParentId(),
                    entity.getConstructionId());
            if (!CollectionUtils.isEmpty(projectStructureList)) {
                pageVo.setRows(projectStructureList);
            }
        } catch (Exception e) {
            throw new EscstException("查询id检查部位信息异常:" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 检查部位数据导入
     *
     * @param file
     * @return
     */
    public String importDataFromExcel(MultipartFile file, String constructionId) {
        String result = "";
        try {
            if (file == null) {
                throw new EscstException("文件不存在！");
            }
            // 上传文件，并存到文件数据库表
//			FileEntity entity = fileService.uploadTempFile(EXCEL, file);
            // 文件的存放路径
//			String savePath = entity.getPath();
            String filePath = fileService.uploadTempFile(EXCEL, file);
//			filePath = ContextUtils.getSession().getServletContext().getRealPath("/") + File.separator + "resources" + File.separator
//					+ savePath;
            // 读excel文件
            XssfExcelHelper xssfExcelHelper = XssfExcelHelper.getInstance(filePath);
            List<ProjectStructureExcelEntity> list = xssfExcelHelper.readExcel(ProjectStructureExcelEntity.class, 0, true);
            result = checkImportDataFromExcel(list, constructionId);
        } catch (Exception e) {
            throw new EscstException("导入检查部位信息出现异常：" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 校验数据并存到数据库
     *
     * @param list
     * @return
     */
    @Transactional
    private String checkImportDataFromExcel(List<ProjectStructureExcelEntity> list, String constructionId) {
        String result = "";
        List<ProjectStructureEntity> projectStructureList = new ArrayList<ProjectStructureEntity>();
        if (!CollectionUtils.isEmpty(list)) {
            String tempId = "";
            String twoId = "";
            String threeId = "";
            String fourId = "";
            String fiveId = "";
            String sexId = "";
            int sort = 0;
            for (ProjectStructureExcelEntity excelEntity : list) {
                if (StringUtils.isBlank(excelEntity.getLevel()) || StringUtils.isBlank(excelEntity.getName())) {
                    continue;
                }
                sort += 10;
                ProjectStructureEntity entity = new ProjectStructureEntity();
                String id = UuidUtils.getUuid();
                entity.setId(id);
                // 如果为一级结构则设置父id为1
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "1")) {
                    entity.setParentId("0");
                    tempId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "2")) {
                    entity.setParentId(tempId);
                    twoId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "3")) {
                    entity.setParentId(twoId);
                    threeId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "4")) {
                    entity.setParentId(threeId);
                    fourId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "5")) {
                    entity.setParentId(fourId);
                    fiveId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "6")) {
                    entity.setParentId(fiveId);
                    sexId = id;
                }
                if (com.alibaba.druid.util.StringUtils.equals(excelEntity.getLevel(), "7")) {
                    entity.setParentId(sexId);
                }

                entity.setSortNum(sort);
                entity.setConstructionId(constructionId);
                entity.setName(excelEntity.getName());
                entity.setCreateBy(ContextUtils.getCurrentUserId());
                entity.setCreateTime(new Date());
                projectStructureList.add(entity);
            }

            // 批量新增检查部位数据
            if (!CollectionUtils.isEmpty(projectStructureList)) {
                projectStructureMapper.batchInsert(projectStructureList);
            }
        } else {
            result = "excel文件中没有数据!";
        }
        return result;
    }

    /**
     * 检查部位导出
     */
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, ProjectStructureEntity projectStructureEntity) {
        try {
            List<ProjectStructureExcelEntity> projectStructureExcelList = new ArrayList<ProjectStructureExcelEntity>();
            // 获取检查部位信息
            String constructionId = projectStructureEntity.getConstructionId();
            String parenId = projectStructureEntity.getParentId();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (parenId.equals("1")) {
                parenId = "";
            } else {
                paramMap.put("constructionId", constructionId);
                paramMap.put("parentId", parenId);
                List<ProjectStructureEntity> projectStructureList = projectStructureMapper.queryScheduledPlanList(paramMap);
                if (!CollectionUtils.isEmpty(projectStructureList)) {
                    for (ProjectStructureEntity entity : projectStructureList) {
                        ProjectStructureExcelEntity projectStructureExcel = new ProjectStructureExcelEntity();
                        String startDate = entity.getStartDate();
                        String endDate = entity.getEndDate();
                        projectStructureExcel.setName(entity.getName());
//                        projectStructureExcel.setEndDate(endDate);
//                        projectStructureExcel.setStartDate(startDate);
                        long childDays = (DateUtils.parse(endDate, "yyyy-MM-dd").getTime()
                                - DateUtils.parse(startDate, "yyyy-MM-dd").getTime() + 1000000) / (3600 * 24 * 1000);
                        String childTotals = childDays + "工作日";
                        // projectStructureExcel.setConstructionPeriod(childTotals);
                        projectStructureExcelList.add(projectStructureExcel);
                    }
                }
            }
            paramMap.put("constructionId", constructionId);
            paramMap.put("parentId", parenId);
            List<ProjectStructureEntity> projectStructureList = projectStructureMapper.queryScheduledPlanList(paramMap);
            if (!CollectionUtils.isEmpty(projectStructureList)) {
                for (ProjectStructureEntity entity : projectStructureList) {
                    String id = entity.getId();
                    String parentId = entity.getParentId();
                    if (StringUtils.isEmpty(parentId)) {
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("constructionId", constructionId);
                        dataMap.put("parentId", id);
                        Map<String, Object> projectStrutureDate = projectStructureMapper.selectProjectStructureByParentId(dataMap);
                        Date minStartDate = (Date) projectStrutureDate.get("minStartDate");
                        Date maxEndDate = (Date) projectStrutureDate.get("maxEndDate");
                        ProjectStructureExcelEntity projectStructureExcelEntity = new ProjectStructureExcelEntity();
                        projectStructureExcelEntity.setName(entity.getName());
//                        projectStructureExcelEntity.setEndDate(DateUtils.format(maxEndDate, "yyyy-MM-dd"));
//                        projectStructureExcelEntity.setStartDate(DateUtils.format(minStartDate, "yyyy-MM-dd"));
                        long days = (maxEndDate.getTime() - minStartDate.getTime() + 1000000) / (3600 * 24 * 1000);
                        String totals = days + "工作日";
                        // projectStructureExcelEntity.setConstructionPeriod(totals);
                        projectStructureExcelList.add(projectStructureExcelEntity);
                        List<ProjectStructureEntity> list = new ArrayList<ProjectStructureEntity>();
                        buildData(list, projectStructureList, entity);
                        if (!CollectionUtils.isEmpty(list)) {
                            for (ProjectStructureEntity childEntity : list) {
                                ProjectStructureExcelEntity projectStructureExcel = new ProjectStructureExcelEntity();
                                projectStructureExcel.setName(childEntity.getName());
//                                projectStructureExcel.setEndDate(childEntity.getEndDate());
//                                projectStructureExcel.setStartDate(childEntity.getStartDate());
                                long childDays = (DateUtils.parse(childEntity.getEndDate(), "yyyy-MM-dd").getTime()
                                        - DateUtils.parse(childEntity.getStartDate(), "yyyy-MM-dd").getTime() + 1000000)
                                        / (3600 * 24 * 1000);
                                String childTotals = childDays + "工作日";
                                // projectStructureExcel.setConstructionPeriod(childTotals);
                                projectStructureExcelList.add(projectStructureExcel);
                            }
                        }
                    }
                }
                String filePath = ContextUtils.getSession().getServletContext().getRealPath("/") + File.separator + "resources"
                        + File.separator;
                File file = new File(filePath + "\\检查部位进度.xlsx");
                String[] fieldNames = {"name", "constructionPeriod", "startDate", "endDate"};
                String[] titles = {"检查部位名称", "工期", "开始时间", "完成时间"};
                XssfExcelHelper.getInstance(file).writeExcel(ProjectStructureExcelEntity.class, projectStructureExcelList, fieldNames,
                        titles, response);
            }
        } catch (Exception e) {
            throw new EscstException("导出工程进度信息失败：" + e.getMessage(), e);
        }
    }

    private void buildData(List<ProjectStructureEntity> dataList, List<ProjectStructureEntity> list, ProjectStructureEntity entity) {
        // 获取该父节点下的子节点数据
        List<ProjectStructureEntity> childrenList = getChildren(list, entity);
        if (!CollectionUtils.isEmpty(childrenList)) {
            for (ProjectStructureEntity child : childrenList) {
                String id = child.getId();
                dataList.add(child);
                buildData(dataList, list, child);
            }
        }
    }

    private List<ProjectStructureEntity> getChildren(List<ProjectStructureEntity> list, ProjectStructureEntity entity) {
        List<ProjectStructureEntity> childrenList = new ArrayList<ProjectStructureEntity>();
        String id = entity.getId();
        for (ProjectStructureEntity child : list) {
            String parentId = child.getParentId();
            if (id.equals(parentId)) {
                childrenList.add(child);
            }
        }
        return childrenList;
    }

    /**
     * 添加检查部位
     */
    public void addProjectStructure(ProjectStructureEntity projectStructureEntity) {
        try {
            String id = projectStructureEntity.getId();
            if (StringUtils.isEmpty(id)) {
                projectStructureEntity.setId(UuidUtils.getUuid());
                projectStructureEntity.setCreateTime(new Date());
                projectStructureEntity.setUpdateTime(new Date());
                projectStructureMapper.insertProjectStructure(projectStructureEntity);
            }
        } catch (Exception e) {
            throw new EscstException("新增或者修改任务派发失败" + e.getMessage(), e);
        }
    }


    /**
     * 查询所有检查部位，key为工地Id，value为工地下的所有检查部位集合
     *
     * @return
     */
    public Map<String, Object> queryAllProjectStructureData() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //查询所有有检查部位数据的工地ID集合
            List<String> construcitonIdList = projectStructureMapper.selectConstructionIdList();
            if (CollectionUtils.isEmpty(construcitonIdList)) {
                return map;
            }
            for (String constructionId : construcitonIdList) {
                ProjectStructureEntity entity = new ProjectStructureEntity();
                entity.setConstructionId(constructionId);
                List<TreeEntity> projectStructureTreeList = projectTaskService.queryProjectStrutrue(entity);
                map.put(constructionId, projectStructureTreeList);
            }
        } catch (EscstException e) {
            throw new EscstException("查询所有的检查部位出现异常！");
        }
        return map;
    }


    /**
     * @param constructionId
     * @return
     * @desc 查询工程结构或者进度计划树形结构
     * @author dwj
     * @date 2018/1/23 10:15
     */
    public List<TreeEntity> queryStructureByConstructionId(String constructionId) {
        List<ProjectStructureEntity> projectStructureEntities = new ArrayList<ProjectStructureEntity>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("constructionId", constructionId);
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        try {
            list = projectStructureMapper.queryProjectStructrue(map);
            if (CollectionUtils.isEmpty(list)) {
                List<ScheduledPlanEntity> scheduledPlanEntities = scheduledPlanService.queryByConstructionId(constructionId);
                for (ScheduledPlanEntity entity : scheduledPlanEntities) {
                    ProjectStructureEntity projectStructureEntity = new ProjectStructureEntity();
                    projectStructureEntity.setId(entity.getId());
                    if (entity.getParentId().equals("1")) {
                        projectStructureEntity.setParentId("");
                    } else {
                        projectStructureEntity.setParentId(entity.getParentId());
                    }
                    projectStructureEntity.setName(entity.getName());
                    projectStructureEntity.setConstructionId(entity.getConstructionId());
                    projectStructureEntity.setSortNum(entity.getSortNum());
                    projectStructureEntity.setCreateTime(new Date());
                    projectStructureEntities.add(projectStructureEntity);

                }
//				if(!CollectionUtils.isEmpty(projectStructureEntities)){
//					projectStructureMapper.batchInsert(projectStructureEntities);
//				}

                list = projectStructureMapper.queryProjectStructrue(map);

            }
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId("1");
            treeEntity.setName("检查部位");
            treeEntity.setpId("0");
            treeEntity.setOpen(true);
            list.add(treeEntity);
        } catch (Exception e) {
            throw new EscstException("查询工程结构或者进度计划异常" + e.getMessage());
        }
        return list;
    }


    /**
     * @param list
     * @return
     * @desc 递归所有节点
     * @author dwj
     * @date 2018/1/26 10:53
     */
    private void getDataList(List<ProjectStructureEntity> list, String id, JSONObject jsonObject, String constructionId) {
        String cid = UuidUtils.getUuid();
        if (!jsonObject.containsKey("children")) {
            ProjectStructureEntity entity = new ProjectStructureEntity();
            entity.setId(cid);
            if (id.equals("1")) {
                entity.setParentId("");
            } else {
                entity.setParentId(id);
            }
            entity.setConstructionId(constructionId);
            entity.setCreateTime(new Date());
            list.add(entity);
        } else {
            ProjectStructureEntity entity = new ProjectStructureEntity();
            entity.setId(cid);
            if (id.equals("1")) {
                entity.setParentId("");
            } else {
                entity.setParentId(id);
            }
            entity.setName(jsonObject.getString("name"));
            entity.setConstructionId(constructionId);
            entity.setCreateTime(new Date());
            list.add(entity);
            JSONArray jsonArray = jsonObject.getJSONArray("children");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                id = cid;
                getDataList(list, id, object, constructionId);

            }
        }
    }


    /**
     * @param list
     * @return
     * @desc 递归得到更新数据
     * @author dwj
     * @date 2018/1/26 11:52
     */
    private void getDataList(List<ProjectStructureEntity> list, String id, JSONObject jsonObject) {
        if (!jsonObject.containsKey("children")) {
            ProjectStructureEntity entity = new ProjectStructureEntity();
            entity.setId(id);
            entity.setName(jsonObject.getString("name"));
            list.add(entity);
        } else {
            ProjectStructureEntity entity = new ProjectStructureEntity();
            entity.setId(id);
            entity.setName(jsonObject.getString("name"));
            list.add(entity);
            JSONArray jsonArray = jsonObject.getJSONArray("children");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String cid = object.getString("id");
                getDataList(list, cid, object);
            }
        }
    }

    /**
     * @param list
     * @return
     * @desc 添加工程结构数据
     * @author dwj
     * @date 2018/1/23 10:24
     */
    public void saveStructure(List<JSONObject> list, String constructionId) {
        List<ProjectStructureEntity> dataList = new ArrayList<ProjectStructureEntity>();
        List<ProjectStructureEntity> updateList = new ArrayList<ProjectStructureEntity>();
        if (!CollectionUtils.isEmpty(list)) {
            for (JSONObject obj : list) {
                String structureId = obj.getString("id");
                String pId = obj.getString("pId");
                if (StringUtils.isEmpty(structureId)) {
                    getDataList(dataList, pId, obj, constructionId);
                } else {
                    getDataList(updateList, structureId, obj);
                }
            }
            //批量新增检查部位
            if (!CollectionUtils.isEmpty(dataList)) {
                projectStructureMapper.batchInsert(dataList);
            }
            //批量更新检查部位
            if (!CollectionUtils.isEmpty(updateList)) {
                projectStructureMapper.updateBatch(updateList);
            }
        }
    }


    public List<String> queryByName(Map<String, Object> map) {
        return projectStructureMapper.queryByName(map);
    }


    public List<ProjectStructureEntity> queryBySortNum(Map<String, Object> map) {
        return projectStructureMapper.queryBySortNum(map);
    }


    /**
     * @param entity
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询工程结构树
     * @author jincheng
     * @date 2018-4-8 11:48
     */
    public PageVo listProjectStructureTree(ProjectStructureEntity entity) {
        try {
            PageVo pageVo = new PageVo();
            if (!com.alibaba.druid.util.StringUtils.isEmpty(entity.getId())) {
                // 查子节点，不分页
                entity.setStartIndex(0);
                entity.setRowNum(Integer.MAX_VALUE);
            }
            // 根据工地id查询总记录数
            int count = projectStructureMapper.selectCount(entity);
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 设置总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引

            // 处理easyUI树翻页的问题
            int startIndex = ((entity.getPage() - 1) * (entity.getRowNum()) == Integer.MAX_VALUE) ? 0 : (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);


            // 处理按名称搜索
            if (!com.alibaba.druid.util.StringUtils.isEmpty(entity.getId())) {
                entity.setName("");
            }

            // 原始数据
            List<TreeEntity> treeList = projectStructureMapper.listProjectStructureTree(entity);
            if (CollectionUtils.isEmpty(treeList)) {
                return pageVo;
            }

            // 获得节点下的叶子节点数量
            List<TreeEntity> parentList = projectStructureMapper.isParent(treeList);

            // 组装树
            for (TreeEntity treeEntity : parentList) {
                for (TreeEntity tree : treeList) {
                    if (tree.getId().equals(treeEntity.getpId()) && !treeEntity.getCount().equals("0")) {
                        tree.setState("closed");
                    }
                }
            }

            pageVo.setRows(treeList);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("查询工程结构树异常！", e);
        }
    }


    /**
     * @param treeEntity
     * @return void
     * @desc 添加工程结构树节点
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    public void addStructure(TreeEntity treeEntity) {
        try {
            treeEntity.setId(UuidUtils.getUuid());
            if (com.alibaba.druid.util.StringUtils.isEmpty(treeEntity.getpId())) {
                treeEntity.setpId("0");
            }
            treeEntity.setCreateTime(new Date());
            treeEntity.setCreateBy(ContextUtils.getCurrentUserId());
            // 添加工程结构
            projectStructureMapper.insertStructure(treeEntity);
        } catch (Exception e) {
            throw new EscstException("添加工程结构树节点异常！", e);
        }
    }

    /**
     * @param treeEntity
     * @return void
     * @desc 修改工程结构
     * @author jincheng
     * @date 2018-4-8 14:57
     */
    public void updateStructure(TreeEntity treeEntity) {
        try {
            treeEntity.setUpdateBy(ContextUtils.getCurrentUserId());
            treeEntity.setUpdateTime(new Date());
            // 修改工程结构
            projectStructureMapper.updateStructure(treeEntity);
        } catch (Exception e) {
            throw new EscstException("修改工程结构异常！", e);
        }
    }

    /**
     * @param treeEntity
     * @return void
     * @desc 删除工程结构
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    public void deleteStructure(TreeEntity treeEntity) {
        try {
            // 获得所有进度计划的ID，父ID集合
            List<TreeEntity> allList = projectStructureMapper.listAll(treeEntity.getConstructionId());
            // 获得Id下的所有子节点ID
            List<TreeEntity> idList = this.listAllNode(allList, treeEntity.getId());
            // 添加父节点ID
            idList.add(treeEntity);
            projectStructureMapper.batchDelete(idList);
        } catch (Exception e) {
            throw new EscstException("删除工地下的进度计划树节点异常！", e);
        }
    }


    // 子节点
    private List<TreeEntity> childList = new ArrayList<TreeEntity>();

    /**
     * 递归，通过父节点ID 获得所有的子节点ID集合
     *
     * @param allList
     * @return
     */

    private List<TreeEntity> listAllNode(List<TreeEntity> allList, String id) {

        for (TreeEntity treeEntity : allList) {
            if (treeEntity.getpId().equals(id)) {
                // 递归
                listAllNode(allList, treeEntity.getId());
                childList.add(treeEntity);
            }
        }

        return childList;

    }


}
