package com.escst.construction.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.escst.commons.tree.TreeEntity;
import com.escst.construction.vo.ScheduledPlanTree;
import com.escst.menu.entity.MenuEntity;
import com.escst.redis.annotation.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.entity.ScheduledPlanExcelEntity;
import com.escst.construction.mapper.ProjectScheduleMapper;
import com.escst.construction.mapper.ScheduledPlanMapper;
import com.escst.file.service.FileService;

/**
 * @author niejing
 * @desc
 * @date 2017年8月18日 下午1:26:55
 */
@Service
public class ScheduledPlanService {

    private static final String EXCEL = "excel";

    @Autowired
    private FileService fileService;

    @Autowired
    public ScheduledPlanMapper mapper;

    @Autowired
    public ProjectScheduleMapper psMapper;


    /**
     * @param entity
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询工地下的进度计划树
     * @author jincheng
     * @date 2018-4-8 11:48
     */
    public PageVo listScheduledPlanTree(ScheduledPlanEntity entity) {
        try {
            PageVo pageVo = new PageVo();
            if (!StringUtils.isEmpty(entity.getId())) {
                // 查子节点，不分页
                entity.setStartIndex(0);
                entity.setRowNum(Integer.MAX_VALUE);
            }
            // 根据工地id查询总记录数
            int count = mapper.selectCount(entity);
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
            if (!StringUtils.isEmpty(entity.getId())) {
                entity.setName("");
            }

            // 原始数据
            List<ScheduledPlanTree> scheduledPlanTrees = mapper.listScheduledPlanTree(entity);
            if (CollectionUtils.isEmpty(scheduledPlanTrees)) {
                return pageVo;
            }

            // 获得节点下的叶子节点数量
            List<ScheduledPlanTree> parentList = mapper.isParent(scheduledPlanTrees);

            // 组装树
            for (ScheduledPlanTree scheduledPlanTree : scheduledPlanTrees) {
                for (ScheduledPlanTree planTree : parentList) {
                    if (scheduledPlanTree.getId().equals(planTree.getpId()) && !planTree.getCount().equals("0")) {
                        scheduledPlanTree.setState("closed");
                    }
                }
            }

            pageVo.setRows(scheduledPlanTrees);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("查询工地下的进度计划树异常！", e);
        }
    }

    /**
     * @param scheduledPlanTree
     * @return void
     * @desc 修改工地下的进度计划树节点名称
     * @author jincheng
     * @date 2018-4-8 14:57
     */
    @Transactional
    public void updateScheduledPlanTreeName(ScheduledPlanTree scheduledPlanTree) {
        try {
            scheduledPlanTree.setUpdateBy(ContextUtils.getCurrentUserId());
            scheduledPlanTree.setUpdateTime(new Date());
            // 修改总体进度计划表
            mapper.updateScheduledPlanTreeName(scheduledPlanTree);

            // 设置进度计划表，名称，计划工期，实际工期
            ProjectScheduleEntity projectScheduleEntity = new ProjectScheduleEntity();
            projectScheduleEntity.setScheduledPlanId(scheduledPlanTree.getId());
            projectScheduleEntity.setName(scheduledPlanTree.getName());
            projectScheduleEntity.setPlanDays(getPlanDays(DateUtils.parse(scheduledPlanTree.getStartTime(), DateUtils.TO_DATE), DateUtils.parse(scheduledPlanTree.getEndTime(), DateUtils.TO_DATE)));
//            projectScheduleEntity.setRealDays(getPlanDays(new Date(), DateUtils.parse(scheduledPlanTree.getStartTime(), DateUtils.TO_DATE)));
            projectScheduleEntity.setUpdateBy(ContextUtils.getCurrentUserId());
            projectScheduleEntity.setUpdateTime(new Date());
            // 修改工程进度表
            psMapper.updateNameAndDays(projectScheduleEntity);
        } catch (Exception e) {
            throw new EscstException("修改工地下的进度计划树节点名称异常！", e);
        }
    }

    /**
     * @param scheduledPlanTree
     * @return void
     * @desc 添加工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    @Transactional
    public void addScheduledPlanTree(ScheduledPlanTree scheduledPlanTree) {
        try {
            String id = UuidUtils.getUuid();
            scheduledPlanTree.setId(id);
            if (StringUtils.isEmpty(scheduledPlanTree.getpId())) {
                scheduledPlanTree.setpId("0");
            }
            scheduledPlanTree.setCreateTime(new Date());
            scheduledPlanTree.setCreateBy(ContextUtils.getCurrentUserId());
            // 添加进度计划表
            mapper.addScheduledPlanTree(scheduledPlanTree);
            // 添加工程进度表
            ProjectScheduleEntity projectScheduleEntity = new ProjectScheduleEntity();
            projectScheduleEntity.setId(UuidUtils.getUuid());
            projectScheduleEntity.setScheduledPlanId(id);
            projectScheduleEntity.setConstructionId(scheduledPlanTree.getConstructionId());
            projectScheduleEntity.setName(scheduledPlanTree.getName());
            projectScheduleEntity.setPlanDays(getPlanDays(DateUtils.parse(scheduledPlanTree.getStartTime(), DateUtils.TO_DATE), DateUtils.parse(scheduledPlanTree.getEndTime(), DateUtils.TO_DATE)));
//            projectScheduleEntity.setRealDays(getPlanDays(DateUtils.parse(scheduledPlanTree.getStartTime(), DateUtils.TO_DATE), new Date()));
            projectScheduleEntity.setStatus("0");
            projectScheduleEntity.setCreateTime(new Date());
            projectScheduleEntity.setCreateBy(ContextUtils.getCurrentUserId());
            psMapper.insert(projectScheduleEntity);
        } catch (Exception e) {
            throw new EscstException("添加工地下的进度计划树节点异常！", e);
        }
    }

    /**
     * @param scheduledPlanTree
     * @return void
     * @desc 删除工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    @Transactional
    public void deleteScheduledPlanTree(ScheduledPlanTree scheduledPlanTree) {
        try {
            // 获得所有进度计划的ID，父ID集合
            List<ScheduledPlanTree> allList = mapper.listAll(scheduledPlanTree.getConstructionId());
            // 获得Id下的所有子节点ID
            List<ScheduledPlanTree> idList = this.listAllNode(allList, scheduledPlanTree.getId());
            // 添加父节点ID
            idList.add(scheduledPlanTree);
            // 删除进度计划表
            mapper.batchDelete(idList);
            // 删除工程进度表
            psMapper.batchDelete(idList);
        } catch (Exception e) {
            throw new EscstException("删除工地下的进度计划树节点异常！", e);
        }
    }


    // 子节点
    private List<ScheduledPlanTree> childList = new ArrayList<ScheduledPlanTree>();

    /**
     * 递归，通过父节点ID 获得所有的子节点ID集合
     *
     * @param allList
     * @return
     */

    private List<ScheduledPlanTree> listAllNode(List<ScheduledPlanTree> allList, String id) {

        for (ScheduledPlanTree scheduledPlanTree : allList) {
            if (scheduledPlanTree.getpId().equals(id)) {
                // 递归
                listAllNode(allList, scheduledPlanTree.getId());
                childList.add(scheduledPlanTree);
            }
        }

        return childList;

    }

    public void add(ScheduledPlanEntity entity) {
        entity.setId(UuidUtils.getUuid());
        entity.setCreateBy(ContextUtils.getCurrentUserId());
        entity.setCreateTime(new Date());
        mapper.insert(entity);
    }

    public void batchInsert(List<ScheduledPlanEntity> list) {
        for (ScheduledPlanEntity entity : list) {
            entity.setId(UuidUtils.getUuid());
            entity.setCreateBy(ContextUtils.getCurrentUserId());
            entity.setCreateTime(new Date());
        }
        mapper.batchInsert(list);
    }

    /**
     * 检查部位数据导入
     *
     * @param file
     * @return
     */
    @Transactional
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
            List<ScheduledPlanExcelEntity> list = xssfExcelHelper.readExcel(ScheduledPlanExcelEntity.class, 0, true);
            result = checkImportDataFromExcel(list, constructionId);

        } catch (Exception e) {
            throw new EscstException("导入检查部位信息出现异常：" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 校验数据并存到数据库
     *
     * @param
     * @return
     */
    @Transactional
    private String checkImportDataFromExcel(List<ScheduledPlanExcelEntity> excelList, String constructionId) {
        String result = "";
        // 总体进度计划
        List<ScheduledPlanEntity> list = new ArrayList<ScheduledPlanEntity>();
        // 工程进度
        List<ProjectScheduleEntity> psList = new ArrayList<ProjectScheduleEntity>();
        if (!CollectionUtils.isEmpty(excelList)) {
            String tempId = "";
            String twoId = "";
            String threeId = "";
            String fourId = "";
            String fiveId = "";
            String sexId = "";
            int sort = 0;
            for (ScheduledPlanExcelEntity excelEntity : excelList) {
                if (org.apache.commons.lang3.StringUtils.isBlank(excelEntity.getLevel()) || org.apache.commons.lang3.StringUtils.isBlank(excelEntity.getName())) {
                    continue;
                }
                sort += 10;
                ScheduledPlanEntity entity = new ScheduledPlanEntity();
                Date startDate = DateUtils.parse(excelEntity.getStartDate(), DateUtils.TO_DATE);
                Date endDate = DateUtils.parse(excelEntity.getEndDate(), DateUtils.TO_DATE);

                String id = UuidUtils.getUuid();
                entity.setId(id);
                // 如果为一级结构则设置父id为1
                if (StringUtils.equals(excelEntity.getLevel(), "1")) {
                    entity.setParentId("0");
                    tempId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "2")) {
                    entity.setParentId(tempId);
                    twoId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "3")) {
                    entity.setParentId(twoId);
                    threeId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "4")) {
                    entity.setParentId(threeId);
                    fourId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "5")) {
                    entity.setParentId(fourId);
                    fiveId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "6")) {
                    entity.setParentId(fiveId);
                    sexId = id;
                }
                if (StringUtils.equals(excelEntity.getLevel(), "7")) {
                    entity.setParentId(sexId);
                }

                entity.setSortNum(sort);
                entity.setConstructionId(constructionId);
                entity.setName(excelEntity.getName());
                entity.setStartTime(startDate);
                entity.setEndTime(endDate);
                entity.setCreateBy(ContextUtils.getCurrentUserId());
                entity.setCreateTime(new Date());
                list.add(entity);

                ProjectScheduleEntity psEntity = new ProjectScheduleEntity();
                psEntity.setId(UuidUtils.getUuid());
                psEntity.setScheduledPlanId(id);
                psEntity.setConstructionId(constructionId);
                psEntity.setName(excelEntity.getName());
                psEntity.setPlanDays(getPlanDays(startDate, endDate));
                psEntity.setStatus("0");// 未开始
                psEntity.setCreateBy(ContextUtils.getCurrentUserId());
                psEntity.setCreateTime(new Date());
                psList.add(psEntity);
            }
            // 批量新增检查部位数据
            if (!CollectionUtils.isEmpty(list)) {
                mapper.batchInsert(list);
            }
            //批量添加工程进度
            if (!CollectionUtils.isEmpty(psList)) {
                psMapper.batchInsert(psList);
            }
        } else {
            result = "excel文件中没有数据!";
        }
        return result;
    }

    public List<ScheduledPlanEntity> queryByConstructionId(String constructionId) {
        return mapper.selectByConstructionId(constructionId);
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @desc 计算开始时间和结束时间相差的天数
     * @author niejing
     * @date 2017年8月24日 下午2:26:25
     */
    public int getPlanDays(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public List<Map<String, Object>> queryByParentId(String parentId) {
        return mapper.selectByParentId(parentId);
    }


    public void sa(List<ScheduledPlanEntity> slist, List<ProjectScheduleEntity> psList) {
        // 批量新增检查部位数据
        if (!CollectionUtils.isEmpty(slist)) {
            mapper.batchInsert(slist);
        }
        //批量添加工程进度
        if (!CollectionUtils.isEmpty(psList)) {
            psMapper.batchInsert(psList);
        }
    }
}
