package com.escst.task.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.FileUploadUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.person.mapper.PersonMapper;
import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.projectCompany.mapper.ProjectCompanyMapper;
import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.task.entity.ProjectTaskProcessEntity;
import com.escst.task.mapper.ProjectTaskMapper;
import com.escst.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author dwj
 * @desc
 * @date 19:30 2017/4/10
 */
@Service
public class ProjectTaskService {

    private static final String SEPARATOR_CHAR = "/";

    //任务派发初始单号
    public static final String RW_ORDER_NO = "RW-0001";

    @Autowired
    private ProjectTaskMapper projectTaskMapper;

    @Autowired
    private FileService fileService;
    @Autowired
    private ProjectCompanyMapper projectCompanyMapper;


    /**
     * 查询任务派发列表
     */
    public PageVo queryTaskList(ProjectTaskEntity projectTaskEntity) {
        PageVo pageVo = new PageVo();
        try {
//            String userId = projectTaskEntity.getUserId();
            String constructionId = projectTaskEntity.getConstructionId();
            String areaId = projectTaskEntity.getAreaId();
            //当前页号
            int pageNum = projectTaskEntity.getPage();

            //每页显示记录数
            int pageSize = projectTaskEntity.getRowNum();

            //每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;

            Map<String, Object> paraMap = new HashMap<String, Object>();
//            paraMap.put("userId", userId);
            paraMap.put("areaId",areaId);
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            paraMap.put("status", projectTaskEntity.getStatus());
            if(StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList",constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            int count = projectTaskMapper.selectCount(paraMap);

            List<Map<String, Object>> list = projectTaskMapper.queryList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {
            }
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询任务列表信息失败" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * /**
     * 通过任务ID任务查询信息
     */
    public List<ProjectTaskProcessEntity> queryList(String taskId) {
        List<ProjectTaskProcessEntity> entityList = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("taskId", taskId);
            entityList = projectTaskMapper.selectList(paramMap);
            if (!CollectionUtils.isEmpty(entityList)) {
                for (ProjectTaskProcessEntity list : entityList) {
                    Integer isAttach = list.getIsAttach();
                    List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
                    if (isAttach == 1) {
                        // 通过id获取上传的附件图片的路径集合
                        filePathVOs = fileService.queryFilePath(list.getId());
                    }
                    list.setPicList(filePathVOs);
                }
            }
        } catch (Exception e) {
            throw new EscstException("通过任务ID查询任务信息" + e.getMessage(), e);
        }
        return entityList;
    }

    /**
     * 通过工地ID 查找分包公司结构
     */
    public List<Map<String, Object>> queryProjectCompanyList(ProjectCompanyEntity projectCompanyEntity) {
        List<Map<String, Object>> list;
        try {
            list = projectCompanyMapper.selectList(projectCompanyEntity);

        } catch (Exception e) {
            throw new EscstException("查询分包公司信息失败" + e.getMessage(), e);
        }
        return list;

    }

    /**
     * 通过检查部位id查询信息
     */
    public Map<String, Object> queryProjectStructureById(String projectStrutureId) {
        Map<String, Object> map;
        try {
            map = projectTaskMapper.selectProjectStructureById(projectStrutureId);
        } catch (Exception e) {
            throw new EscstException("通过任务ID查询任务信息" + e.getMessage(), e);
        }
        return map;
    }

    /**
     * 查询检查部位
     */
    public List<TreeEntity> queryProjectStrutrue(ProjectStructureEntity projectStructrueEntity) {
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        try {
            //工地Id
            String constructionId = projectStructrueEntity.getConstructionId();
            //查询该工地下的所有的检查部位
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("constructionId", constructionId);
            List<TreeEntity> treeList = projectTaskMapper.queryParentProjectStructrue(paramMap);
            if (!CollectionUtils.isEmpty(treeList)) {
                for (TreeEntity treeEntity : treeList) {
                    String id = treeEntity.getId();
                    List<TreeEntity> treeEntityList = getChildrenList(constructionId, id);
                    list.add(treeEntity);
                    list.addAll(treeEntityList);
                }
            }

        } catch (Exception e) {
            throw new EscstException("查询检查部位失败" + e.getMessage());
        }
        return list;
    }

    private List<TreeEntity> getChildrenList(String constructionId, String parentId) {
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("constructionId", constructionId);
        paramMap.put("parentId", parentId);
        List<TreeEntity> treeList = projectTaskMapper.queryProjectStructrue(paramMap);
        if (!CollectionUtils.isEmpty(treeList)) {
            for (TreeEntity treeEntity : treeList) {
                String id = treeEntity.getId();
                List<TreeEntity> childrenList = getChildrenList(constructionId, id);
                list.addAll(childrenList);
            }
        }
        return list;
    }


    /**
     * 添加任务派发
     */
    @Transactional
    public void addProjectTask(ProjectTaskEntity projectTaskEntity) {
        try {
            String id = projectTaskEntity.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                projectTaskEntity.setId(id);
                projectTaskEntity.setStatus(1);
                projectTaskEntity.setOrderNo(getLatestOrderNo(projectTaskEntity.getConstructionId()));
                projectTaskEntity.setCreateTime(new Date());
                projectTaskEntity.setUpdateTime(new Date());
                projectTaskMapper.insertProjectTask(projectTaskEntity);
                ProjectTaskProcessEntity projectTaskProcessEntity = new ProjectTaskProcessEntity();
                projectTaskProcessEntity.setId(UuidUtils.getUuid());
                projectTaskProcessEntity.setTaskId(id);
                projectTaskProcessEntity.setTaskStatus(1);
                projectTaskProcessEntity.setPersonId(projectTaskEntity.getPersonId());
                projectTaskProcessEntity.setCreateTime(DateUtils.format(new Date(), DateUtils.TO_SECOND));
                projectTaskMapper.insertProjectTaskProcess(projectTaskProcessEntity);
            }
        } catch (Exception e) {
            throw new EscstException("新增或者修改任务派发失败" + e.getMessage(), e);
        }
    }

    /**
     * 提交，处理任务派发
     */
    @Transactional
    public void addProjectTaskProcess(ProjectTaskProcessEntity projectTaskProcessEntity) {
        try {
            String id = projectTaskProcessEntity.getId();
            MultipartFile[] files = projectTaskProcessEntity.getFiles();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                projectTaskProcessEntity.setId(id);
                projectTaskProcessEntity.setCreateTime(DateUtils.format(new Date(), DateUtils.TO_SECOND));
                if (files != null && files.length > 0) {
                    //是否有附件设置为1
                    projectTaskProcessEntity.setIsAttach(1);
                    String projectTaskProcessId = projectTaskProcessEntity.getId();
                    // 上传文件
                    Map<String, Object> map = new HashMap<String, Object>();
                    // 文件存储的存到数据库的路径
                    String savePath = "upload" + SEPARATOR_CHAR + "task" + SEPARATOR_CHAR + DateUtils.format(new Date(), "yyyyMM");
                    map.put("businessId", projectTaskProcessId);
                    map.put("filePath", savePath);
                    String result = FileUploadUtils.uploadFiles(files, map);
                    JSONObject josnObject = JSONObject.parseObject(result);
                    Integer status = josnObject.getInteger("status");
                    if (status == 0) {
                        throw new EscstException("上传文件失败");
                    }
                }
                projectTaskMapper.insertProjectTaskProcess(projectTaskProcessEntity);
                String taskId = projectTaskProcessEntity.getTaskId();
                int taskStatus = projectTaskProcessEntity.getTaskStatus();
                if (taskStatus == 4) {
                    ProjectTaskEntity ProjectTaskEntity = new ProjectTaskEntity();
                    ProjectTaskEntity.setStatus(2);
                    ProjectTaskEntity.setId(taskId);
                    projectTaskMapper.updateTaskStatus(ProjectTaskEntity);
                }
                ProjectTaskEntity ProjectTaskEntity = new ProjectTaskEntity();
                ProjectTaskEntity.setStatus(taskStatus);
                ProjectTaskEntity.setId(taskId);
                projectTaskMapper.updateTaskStatus(ProjectTaskEntity);
            }
        } catch (Exception e) {
            throw new EscstException("提交处理任务失败" + e.getMessage(), e);
        }
    }

    public Map<String, Object> queryCount(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", userId);
            paramMap.put("status", ProjectTaskProcessEntity.TASK_STATUS_DISPATCHEDWORKERS);
            int toAccept = projectTaskMapper.selectCount(paramMap);
            paramMap.put("userId", userId);
            paramMap.put("status", ProjectTaskProcessEntity.TASK_STATUS_ALREADYDISPATCHED);
            int Processing = projectTaskMapper.selectCount(paramMap);
            paramMap.put("userId", userId);
            paramMap.put("status", ProjectTaskProcessEntity.TASK_STATUS_INTREATMNET);
            int toCheck = projectTaskMapper.selectCount(paramMap);
            paramMap.put("userId", userId);
            paramMap.put("userId", userId);
            paramMap.put("status", ProjectTaskProcessEntity.TASK_STATUS_PRELIMINARYINSPECTION);
            int completed = projectTaskMapper.selectCount(paramMap);
            map.put("toAccept", toAccept);
            map.put("Processing", Processing);
            map.put("toCheck", toCheck);
            map.put("completed", completed);
        } catch (Exception e) {
            throw new EscstException("查询不通状态下任务总数失败" + e.getMessage(), e);
        }
        return map;
    }



    /**
    * @desc 获取该工地下最新任务派发单号
    * @param constructionId
    * @return
    * @author dwj
    * @date 6/8/2018 14:36
    */
    private String getLatestOrderNo(String constructionId) {
        String orderNo = "";
        try {
            orderNo = projectTaskMapper.selectLatestOrderNo(constructionId);

            // 初始化任务派发单号
            if (StringUtils.isEmpty(orderNo)) {
                    orderNo = RW_ORDER_NO;

            } else {
                String[] tmpStr = orderNo.split("-");
                orderNo = tmpStr[0] + "-" + increase(tmpStr[1]);
            }
        } catch (Exception e) {
            System.out.println("获取最新任务派发单号异常：" + e);
        }
        return orderNo;
    }

    private String increase(String value) {
        int index = 1;
        int n = Integer.parseInt(value.substring(index)) + 1;
        String newValue = String.valueOf(n);
        int len = value.length() - newValue.length() - index;

        for (int i = 0; i < len; i++) {
            newValue = "0" + newValue;
        }
        return value.substring(0, index) + newValue;
    }

}
