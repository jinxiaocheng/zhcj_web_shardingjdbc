package com.escst.inspection.service;

import com.alibaba.fastjson.JSONArray;
import com.escst.commons.exception.EscstException;
import com.escst.commons.threadpool.SimpleThreadPool;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.*;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.MessageVo;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.TreeVO;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.equipment.vo.InspectionVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.inspection.bean.InspectionRequestBean;
import com.escst.inspection.bean.ItemsBean;
import com.escst.inspection.entity.InspectionCorrectiveProcessEntity;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.entity.InspectionResultsEntity;
import com.escst.inspection.entity.NotifyEntity;
import com.escst.inspection.enums.InspectionTypeEnum;
import com.escst.inspection.mapper.InspectionMapper;
import com.escst.inspection.vo.*;
import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.projectCompany.mapper.ProjectCompanyMapper;
import com.escst.redis.annotation.RedisCache;
import com.escst.task.entity.ScheduledPlanEntity;
import com.escst.task.service.TaskDistributionService;
import com.escst.thread.WorkTrendThread;
import com.escst.user.entity.UserEntity;
import com.escst.user.mapper.UserMapper;
import com.escst.worktrend.entity.WorkTrendEntity;
import com.escst.worktrend.mapper.WorkTrendMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author caozx
 * @desc
 * @date 2017/8/8 14:13
 */
@Service
@Transactional
public class InspectionService {
    /**
     * 质量整改标题，内容
     **/
    private static final String MESSAGE_QUALITY_TITLE = ResourceUtil.getConfigByName("message_quality_title");
    private static final String MESSAGE_QUALITY_CONTENT = ResourceUtil.getConfigByName("message_quality_content");

    /**
     * 质量复检通知标题，内容
     **/
    private static final String MESSAGE_QUALITY_CHECK_TITLE = ResourceUtil.getConfigByName("message_quality_check_title");
    private static final String MESSAGE_QUALITY_CHECK_CONTENT = ResourceUtil.getConfigByName("message_quality_check_content");

    /**
     * 质量复检不通过标题，内容
     **/
    private static final String MESSAGE_QUALITY_CHECK_NOPASS_TITLE = ResourceUtil.getConfigByName("message_quality_check_nopass_title");
    private static final String MESSAGE_QUALITY_CHECK_NOPASS_CONTENT = ResourceUtil.getConfigByName("message_quality_check_nopass_content");

    /**
     * 质量整改通过通知
     **/
    private static final String MESSAGE_QUALITY_PASS_TITLE = ResourceUtil.getConfigByName("message_quality_pass_title");
    private static final String MESSAGE_QUALITY_PASS_CONTENT = ResourceUtil.getConfigByName("message_quality_pass_content");

    /**
     * 安全整改通知标题，内容
     **/
    private static final String MESSAGE_SAFE_TITLE = ResourceUtil.getConfigByName("message_safe_title");
    private static final String MESSAGE_SAFE_CONTENT = ResourceUtil.getConfigByName("message_safe_content");

    /**
     * 安全复检通知标题，内容
     **/
    private static final String MESSAGE_SAFE_CHECK_TITLE = ResourceUtil.getConfigByName("message_safe_check_title");
    private static final String MESSAGE_SAFE_CHECK_CONTENT = ResourceUtil.getConfigByName("message_safe_check_content");

    /**
     * 安全复检不通过标题，内容
     **/
    private static final String MESSAGE_SAFE_CHECK_NOPASS_TITLE = ResourceUtil.getConfigByName("message_safe_check_nopass_title");
    private static final String MESSAGE_SAFE_CHECK_NOPASS_CONTENT = ResourceUtil.getConfigByName("message_safe_check_nopass_content");

    /**
     * 安全整改通过通知标题，内容
     **/
    private static final String MESSAGE_SAFE_PASS_TITLE = ResourceUtil.getConfigByName("message_safe_pass_title");
    private static final String MESSAGE_SAFE_PASS_CONTENT = ResourceUtil.getConfigByName("message_safe_pass_content");

    /**
     * 系统消息类型
     **/
    public static final Integer MESSAGE_TYPE_SYSTEM = 0;

    /**
     * 质量整改消息类型
     **/
    public static final Integer MESSAGE_TYPE_QUALITY = 1;

    /**
     * 安全整改消息类型
     **/
    public static final Integer MESSAGE_TYPE_SAFE = 2;

    /**
     * 任务派发消息类型
     **/
    public static final Integer MESSAGE_TYPE_TASK = 3;

    /**
     * 塔吊预约消息类型
     **/
    public static final Integer MESSAGE_TYPE_TOWER = 4;

    /**
     * 升降机预约消息类型
     **/
    public static final Integer MESSAGE_TYPE_LIFT = 5;

    /**
     * 工程进度消息推送
     **/
    public static final Integer MESSAGE_TYPE_SCHEDULED = 6;

    /**
     * 环境pm10预警消息推送
     **/
    public static final Integer MESSAGE_TYPE_ENVIRONMENT_PM10 = 7;

    /**
     * 环境noise预警消息推送
     **/
    public static final Integer MESSAGE_TYPE_ENVIRONMENT_NOISE = 8;

    /**
     * 塔吊预警推送
     **/
    public static final Integer MESSAGE_TYPE_TOWER_ALARM = 9;

    /**
     * 升降机预警推送
     **/
    public static final Integer MESSAGE_TYPE_LIFT_ALARM = 10;

    /**
     * 环境pm25预警消息推送
     **/
    public static final Integer MESSAGE_TYPE_ENVIRONMENT_PM25 = 11;

    /**
     * 整改与检查区分
     **/
    public static final Integer TSTATUS_SAFE = 12;

    /**
     * 安全分项检查动态
     */
    public static final String SAFETY_SUB_INSPECTION = ResourceUtil.getConfigByName("message_safety_sub_inspection_title");
    /**
     * 日常安全检查动态
     */
    public static final String SAFETY_DAILY_CHECK = ResourceUtil.getConfigByName("message_safety_daily_check_title");
    /**
     * 安全整改
     */
    public static final String SAFETY_RECTIFICATION = ResourceUtil.getConfigByName("message_safety_rectification_title");
    /**
     * 质量检查
     */
    public static final String QUALITY_INSPECTION = ResourceUtil.getConfigByName("message_quality_inspection_title");
    /**
     * 质量整改
     */
    public static final String QUALITY_IMPROVEMENT = ResourceUtil.getConfigByName("message_quality_improvement_title");

    @Autowired
    private InspectionMapper inspectionMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private WorkTrendMapper workTrendMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TaskDistributionService taskDistributionService;

    @Autowired
    private ProjectCompanyMapper projectCompanyMapper;

    // 检查单对应的检查部位集合
    private List<ScheduledPlanEntity> pickList = new ArrayList<ScheduledPlanEntity>();

    private List<InspectionVO> itemList = new ArrayList<InspectionVO>();


    //质量检查初始单号
    public static final String ZL_ORDER_NO = "ZL-0001";
    //日常安全检查初始单号
    public static final String AQ_RC_ORDER_NO = "AQ-RC-0001";
    //分项安全检查初始单号
    public static final String AQ_FX_ORDER_NO = "AQ-FX-0001";

    /**
     * 通过工地id查询质量和安全列表
     *
     * @param inspectionRequestBean
     * @return
     */
    public PageVo listByConstructionId(InspectionRequestBean inspectionRequestBean) {
        PageVo pageVo = new PageVo();
        try {
            String constructionId = inspectionRequestBean.getConstructionId();

            Integer type = inspectionRequestBean.getType();
            Integer processingOpinion = inspectionRequestBean.getProcessingOpinion();
            String projectCompanyId = inspectionRequestBean.getProjectCompanyId();

            // 当前页号
            int pageNum = inspectionRequestBean.getPage();

            // 每页显示记录数
            int pageSize = inspectionRequestBean.getRowNum();

            // 每页的第一条记录的索引
            int offset = inspectionRequestBean.getStartIndex();

            Map<String, Object> paraMap = new HashMap<String, Object>();

            paraMap.put("areaId", inspectionRequestBean.getAreaId());
            paraMap.put("type", type);
            paraMap.put("projectCompanyId", projectCompanyId);

            if (StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList", constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            paraMap.put("processingOpinion", processingOpinion);

            // 查询整改列表
            if (processingOpinion == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
                // modify by zhouwei 2017-11-10 暂时不根据用户过滤
                // paraMap.put("contactId",
                // inspectionRequestBean.getContactId());
                paraMap.put("correctiveStatus", inspectionRequestBean.getCorrectiveStatus());
                paraMap.put("correctStartDate", inspectionRequestBean.getCorrectStartDate());
                paraMap.put("correctEndDate", inspectionRequestBean.getCorrectEndDate());
                if (type.equals(InspectionTypeEnum.QUALITY.getValue())) {
                    paraMap.put("types", String.valueOf(type.intValue()));
                } else {
                    String types = InspectionTypeEnum.SAFETY_ITEMS.getValue() + "," + InspectionTypeEnum.SAFETY_EVERYDAY.getValue();
                    paraMap.put("types", types);
                }
            } else {
                // modify by zhouwei 2017-12-20 如果不是整改,则按照类型来过滤
                paraMap.put("types", String.valueOf(type.intValue()));
            }

            // 添加检查日期查询条件
            paraMap.put("startDate", inspectionRequestBean.getStartDate());
            paraMap.put("endDate", inspectionRequestBean.getEndDate());

            // 添加检查项查询
            paraMap.put("items", inspectionRequestBean.getItems());

            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);

            // 查询总的记录数
            int count = inspectionMapper.queryInspectionCount(paraMap);
            List<Map<String, Object>> list = inspectionMapper.selectList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    // 是否有附件
                    // int isAttach = (Integer) map.get("isAttach");
                    Date businessDate = (Date) map.get("businessDate");
                    // 整改日期
                    Date correctiveCompletionDate = (Date) map.get("correctiveCompletionDate");
                    // 检查日期
                    String businessDateStr = "";
                    String correctiveCompletionDateStr = "";
                    if (null != businessDate) {
                        businessDateStr = DateUtils.format(businessDate, "yyyy-MM-dd");
                    }
                    if (null != correctiveCompletionDate) {
                        correctiveCompletionDateStr = DateUtils.format(correctiveCompletionDate, "yyyy-MM-dd");
                    }

                    map.put("businessDate", businessDateStr);
                    map.put("correctiveCompletionDate", correctiveCompletionDateStr);
                    String projectStructureName = inspectionMapper.selectProjectStructureNameByInspectionId(id);
                    //检查部位
                    map.put("projectStructureName", projectStructureName);
                    // List<FilePathVO> filePathVOs = null;
                    // if (isAttach == 1) {
                    // // 通过id获取上传的附件图片的路径集合
                    // filePathVOs = fileService.queryFilePath(id);
                    // } else {
                    // filePathVOs = new ArrayList<FilePathVO>();
                    // }
                    // // 图片url
                    // map.put("picList", filePathVOs);
                }
            }
            // 相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询检查记录出现异常：" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 查询整改处理详细信息
     *
     * @param inspectionId
     * @return
     */
    public List<CorrectiveDetailVO> listCorrectiveProcess(String inspectionId) {
        List<CorrectiveDetailVO> list = null;
        try {
            // 整改记录ID
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("inspectionId", inspectionId);
            list = inspectionMapper.selectCorrectiveProcessList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {
                for (CorrectiveDetailVO correctiveDetailVO : list) {
                    String id = correctiveDetailVO.getId();
                    Integer isAttach = correctiveDetailVO.getIsAttach();
                    Date createTime = correctiveDetailVO.getCreateTime();
                    String createTimeStr = ""; // 检查日期
                    if (null != createTime) {
                        createTimeStr = DateUtils.format(createTime, DateUtils.TO_SECOND);
                        correctiveDetailVO.setCreateTimeStr(createTimeStr);
                    }
                    List<FilePathVO> filePathVOs = null;
                    if (isAttach != null && isAttach == 1) {
                        // 通过id获取上传的附件图片的路径集合
                        filePathVOs = fileService.queryFilePath(id);
                    }
                    correctiveDetailVO.setPicList(filePathVOs);
                }
            }
        } catch (EscstException e) {
            throw new EscstException("查询整改详细信息记录出现异常：" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 根据整改状态查询整改记录数
     *
     * @param inspectionEntity
     * @return
     */
    public Map<String, Object> queryCountByCorrectiveStatus(InspectionEntity inspectionEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            inspectionEntity.setProcessingOpinion(InspectionEntity.PROCESSING_OPINION_CORRECTIVE);
            // 查询待整改记录数
            inspectionEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_ONE);
            int waitCorrectiveCount = inspectionMapper.queryCount(inspectionEntity);
            // 查询待检查记录数
            inspectionEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_TWO);
            int waitCheckCount = inspectionMapper.queryCount(inspectionEntity);
            // 查询检查不通过记录数
            inspectionEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_THREE);
            int checkNoPassCount = inspectionMapper.queryCount(inspectionEntity);
            // 查询已整改记录数
            inspectionEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_FOUR);
            int alreadyCorrectiveCount = inspectionMapper.queryCount(inspectionEntity);
            map.put("waitCorrectiveCount", waitCorrectiveCount);
            map.put("waitCheckCount", waitCheckCount);
            map.put("checkNoPassCount", checkNoPassCount);
            map.put("alreadyCorrectiveCount", alreadyCorrectiveCount);
        } catch (EscstException e) {
            throw new EscstException("根据整改状态查询整改数量出现异常：" + e.getMessage(), e);
        }
        return map;
    }

    /**
     * 新增质量检查和安全检查
     *
     * @param inspectionEntity
     */
    public void save(InspectionEntity inspectionEntity) {
        // TODO 2017-11-21 该方法用于安全检查和质量检查,安全检查和质量检查时增加备注和知会人列表信息. 安全检查的检查结果单独保存
        try {
            String id = inspectionEntity.getId();
            // id为空执行新增操作，不为空执行修改操作
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                inspectionEntity.setId(id);
                inspectionEntity.setCreateTime(new Date());

                MultipartFile[] files = inspectionEntity.getFiles();

                if (files != null && files.length > 0) {
                    inspectionEntity.setIsAttach(1);
                    // 业务id
                    id = inspectionEntity.getId();

                    // 上传附件
                    fileService.uploadFile("inspection", id, files);
                } else {
                    inspectionEntity.setIsAttach(0);
                }

                InspectionCorrectiveProcessEntity inspectionCorrectiveProcessEntity = null;
                int processingOpinion = inspectionEntity.getProcessingOpinion();
                if (processingOpinion == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
                    inspectionEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_ONE);
                    // 添加整改详细信息
                    inspectionCorrectiveProcessEntity = new InspectionCorrectiveProcessEntity();
                    inspectionCorrectiveProcessEntity.setId(UuidUtils.getUuid());
                    inspectionCorrectiveProcessEntity.setInspectionId(id);
                    inspectionCorrectiveProcessEntity.setCorrectiveStatus(InspectionEntity.CORRECTIVE_STATUS_ONE);
                    inspectionCorrectiveProcessEntity.setPersonId(ContextUtils.getCurrentUserId());
                    inspectionCorrectiveProcessEntity.setCreateTime(new Date());
                    inspectionCorrectiveProcessEntity.setIsAttach(0);
                }
                // 如果检查部位不为空，批量添加检查部位（工程结构信息）
                if (StringUtils.isNotBlank(inspectionEntity.getProjectStructureList())) {
                    // 批量添加检查部位（工程结构）
                    this.batchInsertInspectionProjectStructure(id, inspectionEntity.getProjectStructureList());
                }
                inspectionEntity.setCreateBy(ContextUtils.getCurrentUserId());

                if (StringUtils.isNotBlank(inspectionEntity.getItemsVOList())) {
                    // 批量插入检查结果
//                    String itemsVO = inspectionEntity.getItemsVOList();
//                    List<ItemsVO> itemsVOList = JSONArray.parseArray(itemsVO, ItemsVO.class);
//
//                    batchInsertCheckResults(id, itemsVOList);

                    // 添加检查项
                    this.batchInsertInspectionCheckItems(id, inspectionEntity.getItemsVOList());

                    // 将根项目id拼接后保存到数据库
                    // StringBuilder nameSb = new StringBuilder();
                    // StringBuilder idSb = new StringBuilder();
                    // for (ItemsVO itemsVO2 : itemsVOList) {
                    // String itemsName = itemsVO2.getName() + ",";
                    // if (nameSb.indexOf(itemsName) < 0) {
                    // nameSb.append(itemsName);
                    // }
                    // String itemsId = itemsVO2.getId() + ",";
                    // if (idSb.indexOf(itemsId) < 0) {
                    // idSb.append(itemsId);
                    // }
                    // }
                    // inspectionEntity.setItemsId(idSb.substring(0,
                    // idSb.length() - 1));
                    // inspectionEntity.setItems(nameSb.substring(0,
                    // nameSb.length() - 1));
                }
                //设置整改单号
                inspectionEntity.setOrderNo(getLatestOrderNo(inspectionEntity.getConstructionId(), inspectionEntity.getType()));
                inspectionEntity.setIsPush(0);
                inspectionEntity.setOperation(0);
                inspectionMapper.insert(inspectionEntity);
                // 添加通知详细信息
                String notifyList = inspectionEntity.getNotifyEntityList();
                if (StringUtils.isNotBlank(notifyList)) {
                    List<NotifyEntity> notifyEntityList = JSONArray.parseArray(notifyList, NotifyEntity.class);
                    for (NotifyEntity notifyEntity : notifyEntityList) {
                        notifyEntity.setId(UuidUtils.getUuid());
                        notifyEntity.setInspectionId(id);
                        inspectionMapper.insertNotifyEntity(notifyEntity);
                    }
                }
                if (inspectionCorrectiveProcessEntity != null) {
                    inspectionMapper.insertCorrectiveProcess(inspectionCorrectiveProcessEntity);
                }

            } else {
                inspectionEntity.setUpdateTime(new Date());
                inspectionMapper.update(inspectionEntity);
            }
            UserEntity entity = userMapper.selectUserById(inspectionEntity.getCreateBy());
            inspectionEntity.setContactName(entity.getName());
            inspectionEntity.setStatus(TSTATUS_SAFE);
            //取得单例线程池
            SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
            //创建一个新的线程实例
            WorkTrendThread thread = new WorkTrendThread(this, inspectionEntity);
            //用线程池中的线程运行线程实例
            simpleThreadPool.executorService.execute(thread);

        } catch (Exception e) {
            throw new EscstException("保存检查记录出现异常：" + e.getMessage(), e);
        }

    }

    /**
     * 添加整改处理详细信息
     *
     * @param inspectionCorrectiveProcessEntity
     */
    public void saveCorrectiveProcess(InspectionCorrectiveProcessEntity inspectionCorrectiveProcessEntity) {
        try {
            String id = inspectionCorrectiveProcessEntity.getId();
            InspectionEntity inspectionEntity = new InspectionEntity();

            String inspectionId = inspectionCorrectiveProcessEntity.getInspectionId();
            if (StringUtils.isEmpty(id)) {
                MultipartFile[] files = inspectionCorrectiveProcessEntity.getFiles();
                id = UuidUtils.getUuid();
                inspectionCorrectiveProcessEntity.setId(id);
                inspectionCorrectiveProcessEntity.setCreateTime(new Date());
                if (files != null && files.length > 0) {
                    // 是否有附件设置为1
                    inspectionCorrectiveProcessEntity.setIsAttach(1);
                    // 业务id
                    String correctiveProcessId = inspectionCorrectiveProcessEntity.getId();

                    // 上传附件
                    fileService.uploadFile("inspectionCorrective", correctiveProcessId, files);

                } else {
                    inspectionCorrectiveProcessEntity.setIsAttach(0);
                }

                int correctiveStatus = inspectionCorrectiveProcessEntity.getCorrectiveStatus();
                // 添加整改处理详细信息数据
                inspectionMapper.insertCorrectiveProcess(inspectionCorrectiveProcessEntity);

                // 同步修改整改记录信息的整改状态
                inspectionEntity.setCorrectiveStatus(correctiveStatus);
                inspectionEntity.setUpdateTime(new Date());
                inspectionEntity.setId(inspectionId);
                inspectionEntity.setIsPush(0);
                inspectionEntity.setOperation(1);
                inspectionMapper.update(inspectionEntity);
            }
            inspectionEntity = queryById(inspectionId);
            UserEntity entity = userMapper.selectUserById(inspectionCorrectiveProcessEntity.getPersonId());
            inspectionEntity.setContactName(entity.getName());
            inspectionEntity.setContactId(inspectionCorrectiveProcessEntity.getPersonId());
            inspectionEntity.setStatus(inspectionEntity.getType());

            //取得单例线程池
            SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
            //创建一个新的线程实例
            WorkTrendThread thread = new WorkTrendThread(this, inspectionEntity);
            //用线程池中的线程运行线程实例
            simpleThreadPool.executorService.execute(thread);

        } catch (EscstException e) {
            throw new EscstException("保存整改处理详细信息出现异常：" + e.getMessage(), e);
        }

    }

    /**
     * @param type
     * @param constructionId
     * @return
     * @desc 根据工地id和类型查询质量/安全管理数量
     * @author niejing
     * @date 2017年5月27日 下午3:05:25
     */
    public Map<String, Object> listAsCount(int type, String constructionId) {
        Map<String, Object> map = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("type", type);
            paramMap.put("constructionId", constructionId);

            map = inspectionMapper.listAsCount(paramMap);
        } catch (Exception e) {
            throw new EscstException("统计质量/安全管理数据异常：" + e.getMessage(), e);
        }
        return map;
    }

    /**
     * 根据id查询质量/安全管理记录
     *
     * @param inspectionId
     * @return
     */
    public InspectionEntity queryById(String inspectionId) {
        InspectionEntity inspectionEntity = null;
        try {
            inspectionEntity = inspectionMapper.selectById(inspectionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inspectionEntity;
    }

    /**
     * 查询检查详细信息
     *
     * @param inspectionVO
     * @return
     */
    public InspectionDetailVO queryDetailInfoById(InspectionVO inspectionVO) {
        InspectionDetailVO vo = null;
        try {
            vo = inspectionMapper.selectDetailInfoById(inspectionVO.getId());
            // 通过ID查询通知详情
            List<NotifyEntity> notifyEntityList = inspectionMapper.selectNotifyEntityById(inspectionVO.getId());
            vo.setNotifyEntityList(notifyEntityList);

            Integer isAttach = vo.getIsAttach();
            List<FilePathVO> filePathVOs = null;
            if (isAttach == 1) {
                // 通过id获取上传的附件图片的路径集合
                filePathVOs = fileService.queryFilePath(inspectionVO.getId());
            } else {
                filePathVOs = new ArrayList<FilePathVO>();
            }
            vo.setPicList(filePathVOs);

            // 查询检查结果
            /*
             * List<InspectionResultsEntity> checkResultList =
             * inspectionMapper.selectInspectionResultsByInspectionId(id);
             * Map<String, List<InspectionResultsEntity>> checkResultMap = new
             * HashMap<String, List<InspectionResultsEntity>>(); if
             * (!CollectionUtils.isEmpty(checkResultList)) { for
             * (InspectionResultsEntity entity : checkResultList) { if
             * (checkResultMap.containsKey(entity.getItemsName())) {
             * List<InspectionResultsEntity> list =
             * checkResultMap.get(entity.getItemsName()); list.add(entity); }
             * else { List<InspectionResultsEntity> list = new
             * ArrayList<InspectionResultsEntity>(); list.add(entity);
             * checkResultMap.put(entity.getItemsName(), list); } } }
             * vo.setCheckResultList(checkResultList);
             * vo.setCheckResultMap(checkResultMap);
             */


            // System.out.println(JSONObject.toJSONString(rst));

        } catch (EscstException e) {
            throw new EscstException("查询检查详细信息出现异常：" + e.getMessage(), e);
        }
        return vo;
    }


    public List<TreeVO> loadCheckItemTree(InspectionVO inspectionVO) {
        // 查询检查结果和检查项目
        List<InspectionResultsEntity> checkItemsAndcheckResultList = inspectionMapper.selectInspectionItemsAndResultsByInspectionId(inspectionVO.getId());
        List<TreeVO> rst = toCheckItemsTree(checkItemsAndcheckResultList);
        return rst;
    }


    private List<TreeVO> toCheckItemsTree(List<InspectionResultsEntity> checkItemsAndcheckResultList) {
        Map<String, TreeVO> parentItemsMap = new LinkedHashMap<String, TreeVO>();// key
        // 一级的ID,
        // value
        // 一级对象
        Map<String, TreeVO> itemsMap = new HashMap<String, TreeVO>();// key
        // 二级的ID,
        // value
        // 三级
        for (InspectionResultsEntity entity : checkItemsAndcheckResultList) {
            String parentItemsId = entity.getParentId();
            String itemsId = entity.getItemsId();
            String resultsId = entity.getResultsId();

            TreeVO resultsVO = new TreeVO();
            resultsVO.setId(resultsId);
            resultsVO.setName(entity.getResults());

            if (itemsMap.containsKey(itemsId)) {
                TreeVO itemsVO = itemsMap.get(itemsId);
                itemsVO.getData().add(resultsVO);
            } else {
                TreeVO itemsVO = new TreeVO();
                itemsVO.setId(itemsId);
                itemsVO.setName(entity.getItemsName());
                itemsVO.setData(new ArrayList<TreeVO>());
                itemsVO.getData().add(resultsVO);
                itemsMap.put(itemsId, itemsVO);

                if (parentItemsMap.containsKey(parentItemsId)) {
                    TreeVO parentItemsVO = parentItemsMap.get(parentItemsId);
                    parentItemsVO.getData().add(itemsVO);
                } else {
                    TreeVO parentItemsVO = new TreeVO();
                    parentItemsVO.setId(parentItemsId);
                    parentItemsVO.setName(entity.getParentName());
                    parentItemsVO.setData(new ArrayList<TreeVO>());
                    parentItemsVO.getData().add(itemsVO);
                    parentItemsMap.put(parentItemsId, parentItemsVO);
                }
            }
        }
        List<TreeVO> rst = new ArrayList<TreeVO>();
        Iterator<Map.Entry<String, TreeVO>> it = parentItemsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, TreeVO> entry = it.next();
            rst.add(entry.getValue());
        }
        return rst;
    }

    /**
     * @param queryVO
     * @return
     * @desc 按月统计质量或安全检查和整改的数量
     * @author zhouwei
     * @date 2017年8月26日 上午11:05:23
     */
    public List<InspectionQtyVO> queryInspectionMonthQtyList(InspectionQueryVO queryVO) {
        String startDate = queryVO.getStartDate();
        String endDate = queryVO.getEndDate();
        List<String> monthList = DateUtils.getMonthList(startDate, endDate);
        List<InspectionQtyVO> list = inspectionMapper.queryInspectionMonthQtyList(queryVO);
        if (monthList.size() == list.size()) {
            return list;
        }
        // 如果有的月份没有数据,则将该月份的统计数据补充为0
        Map<String, InspectionQtyVO> map = new HashMap<String, InspectionQtyVO>();
        for (InspectionQtyVO vo : list) {
            map.put(vo.getDate(), vo);
        }
        List<InspectionQtyVO> rst = new ArrayList<InspectionQtyVO>();
        for (String month : monthList) {
            if (map.containsKey(month)) {
                rst.add(map.get(month));
            } else {
                InspectionQtyVO qtyVO = new InspectionQtyVO();
                qtyVO.setDate(month);
                qtyVO.setQty(BigDecimal.ZERO);
                qtyVO.setCorrectiveQty(BigDecimal.ZERO);
                rst.add(qtyVO);
            }
        }
        return rst;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.escst.inspection.service.QueryItemsid#queryTopSafetyCheckItems()
     */
//    @RedisCache
    public List<CheckItemsVO> queryTopSafetyCheckItems(CheckItemsVO checkItemsVO) {
        checkItemsVO.setParentId("0");
        return inspectionMapper.selectCheckItemsList(checkItemsVO);
    }

    /**
     * @desc 根据检查项ID得到检查结果
     * @param parentItemsId
     *            检查项ID
     * @return
     * @author zhouwei
     * @date 2017年10月17日 下午2:44:22
     */
    /*
     * @RedisCache public List<ParentVO> queryCheckResultsByParentItemsId(String
     * parentItemsId) { List<CheckResultsVO> list =
     * inspectionMapper.selectCheckResultsByParentItemsId(parentItemsId); if
     * (CollectionUtils.isEmpty(list)) { return null; }
     *
     * List<ParentVO> rst = new ArrayList<ParentVO>(); Map<String, List<BaseVO>>
     * itemsMap = new HashMap<String, List<BaseVO>>(); for (CheckResultsVO
     * checkResultsVO : list) { String itemsId = checkResultsVO.getItemsId(); if
     * (itemsMap.containsKey(itemsId)) { List<BaseVO> resultsList =
     * itemsMap.get(itemsId); TreeEntity itemsTree = new TreeEntity();
     * itemsTree.setId(itemsId); itemsTree.setpId(parentItemsId); BaseVO
     * resultVO = new BaseVO(); resultVO.setId(checkResultsVO.getId());
     * resultVO.setName(checkResultsVO.getName()); resultsList.add(resultVO); }
     * else { List<BaseVO> resultsList = new ArrayList<BaseVO>(); BaseVO
     * resultVO = new BaseVO(); resultVO.setId(checkResultsVO.getId());
     * resultVO.setName(checkResultsVO.getName()); resultsList.add(resultVO);
     * itemsMap.put(itemsId, resultsList);
     *
     * ParentVO parentVO = new ParentVO(); parentVO.setId(itemsId);
     * parentVO.setName(checkResultsVO.getItemsName());
     * parentVO.setData(resultsList); rst.add(parentVO); } } return rst; }
     */

    /**
     * @desc 批量插入检查结构
     * @param inspectionId
     *            检查单ID
     * @param resultsJson
     *            List<BaseVO>的json字符串
     * @author zhouwei
     * @date 2017年10月17日 下午5:05:32
     */
    /*
     * public void batchInsertCheckResults(String inspectionId, String
     * resultsJson) { if (StringUtils.isEmpty(resultsJson)) { return; }
     * List<BaseVO> baseVOList = null; try { baseVOList =
     * JSONArray.parseArray(resultsJson, BaseVO.class); } catch (Exception e) {
     * String msg = "json字符串(%s)解析为数组时出现异常"; throw new
     * EscstException(String.format(msg, resultsJson)); }
     * List<InspectionResultsEntity> list = new
     * ArrayList<InspectionResultsEntity>(); for (BaseVO baseVO : baseVOList) {
     * InspectionResultsEntity entity = new InspectionResultsEntity();
     * entity.setInspectionId(inspectionId);
     * entity.setResultsId(baseVO.getId()); entity.setResults(baseVO.getName());
     * list.add(entity); } inspectionMapper.batchInsertCheckResults(list); }
     */

    /**
     * 更改
     *
     * @param inspectionId 检查单ID
     * @desc 批量插入检查结果
     * @author hukang
     */

    public void batchInsertCheckResults(String inspectionId, List<ItemsVO> itemsVOList) {

        List<InspectionResultsEntity> list = new ArrayList<InspectionResultsEntity>();
        for (ItemsVO itemsVO : itemsVOList) {
            InspectionResultsEntity entity = new InspectionResultsEntity();
            entity.setInspectionId(inspectionId);
            entity.setParentId((itemsVO.getId()));
            entity.setParentName((itemsVO.getName()));
            entity.setResultsId(itemsVO.getResultsId());
            entity.setResults(itemsVO.getResultsName());
            list.add(entity);
        }
        inspectionMapper.batchInsertCheckResults(list);
    }

    /**
     * @return
     * @desc 得到安全检查检查项和检查结果的树形结构  by 修改为不传检查结果给前台 by jincheng
     * @author zhouwei
     * @date 2017年11月22日 上午10:27:11
     */
    public List<TreeEntity> querySafetyItemsResultsTree(CheckItemsVO checkItemsVO) {
        InspectionService service = (InspectionService) AopContext.currentProxy();
        List<CheckItemsVO> itemsList = service.queryCheckItemsList(checkItemsVO);
        // 获取检查项目的树形结构
        if (CollectionUtils.isEmpty(itemsList)) {
            return null;
        }
//        Map<String, List<CheckResultsVO>> resultsMap = service.getCheckResultsMap();
        List<TreeEntity> treeEntityList = new ArrayList<TreeEntity>();
        for (CheckItemsVO item : itemsList) {
            TreeEntity tree = new TreeEntity();
            String itemsId = item.getId();
            tree.setId(itemsId);
            tree.setpId(item.getParentId());
            tree.setName(item.getName());
            treeEntityList.add(tree);
//            if (resultsMap.containsKey(itemsId)) {
//                List<CheckResultsVO> resultsList = resultsMap.get(itemsId);
//                for (CheckResultsVO results : resultsList) {
//                    tree = new TreeEntity();
//                    tree.setId(results.getId());
//                    tree.setpId(results.getItemsId());
//                    tree.setName(results.getName());
//                    // 不查出检查结果
//                    treeEntityList.add(tree);
//                }
//            }
        }
        return treeEntityList;
    }

    //    @RedisCache
    public List<CheckItemsVO> queryCheckItemsList(CheckItemsVO checkItemsVO) {
        return inspectionMapper.selectCheckItemsList(checkItemsVO);
    }

    /**
     * @return
     * @desc 得到检查项下的检查结果
     * @author zhouwei
     * @date 2017年11月22日 上午10:16:40
     */
//    @RedisCache
    public Map<String, List<CheckResultsVO>> getCheckResultsMap() {
        Map<String, List<CheckResultsVO>> map = new HashMap<String, List<CheckResultsVO>>();
        List<CheckResultsVO> list = inspectionMapper.selectAllCheckResults();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        for (CheckResultsVO results : list) {
            String itemsId = results.getItemsId();
            if (map.containsKey(itemsId)) {
                List<CheckResultsVO> resultList = map.get(itemsId);
                resultList.add(results);
            } else {
                List<CheckResultsVO> resultList = new ArrayList<CheckResultsVO>();
                resultList.add(results);
                map.put(itemsId, resultList);
            }
        }
        return map;
    }

    @RedisCache
    public Map<String, CheckItemsVO> queryAllCheckItemsList() {
        Map<String, CheckItemsVO> CheckItemsMap = new HashMap<String, CheckItemsVO>();
        List<CheckItemsVO> CheckItemsList = inspectionMapper.selectAllCheckItemsList();
        for (CheckItemsVO checkItemsVO : CheckItemsList) {
            CheckItemsMap.put(checkItemsVO.getId(), checkItemsVO);
        }
        return CheckItemsMap;
    }


    /**
     * @param entity
     * @return
     * @desc 新增整改任务成功后，向消息记录表添加消息,并给手机端推送消息
     * @author caozx
     * @date 2017/9/28 17:20
     */
    public void savePushMsg(InspectionEntity entity) {
        if (entity.getProcessingOpinion() == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
            String content = "";
            String title = "";
            Integer type = null;

            if (entity.getType() == 1) {
                title = MESSAGE_QUALITY_TITLE;
                content = MESSAGE_QUALITY_CONTENT;
                type = MESSAGE_TYPE_QUALITY;
                // pushMessageService.sendPush(users,
                // entity.getConstructionId(), entity.getId(), title, content,
                // type,
                // entity.getCorrectiveStatus());
            } else if (entity.getType() == 2 || entity.getType() == 3) {
                title = MESSAGE_SAFE_TITLE;
                content = MESSAGE_SAFE_CONTENT;
                type = MESSAGE_TYPE_SAFE;
                // pushMessageService.sendPush(users,
                // entity.getConstructionId(), entity.getId(), title, content,
                // type,
                // entity.getCorrectiveStatus());
            }
            Set<String> users = new HashSet<String>();
            users.add(entity.getContactId());
            MessageVo vo = new MessageVo();
            vo.setUsers(users);
            vo.setConstructionId(entity.getConstructionId());
            vo.setTitle(title);
            vo.setContent(content);
            vo.setType(type);
            vo.setBillId(entity.getId());
            vo.setStatus(entity.getCorrectiveStatus());
            MessagePushUtils.sendMessage(vo);
        }
    }

    /**
     * @param entity
     * @return
     * @desc 提交保存成功后，向消息记录表添加消息,并给手机端推送消息
     * @author caozx
     * @date 2017/9/28 17:25
     */
    public void saveCorrectiveProcessPushMsg(InspectionCorrectiveProcessEntity entity) {
        String inspectionId = entity.getInspectionId();
        InspectionEntity inspectionEntity = queryById(inspectionId);

        // 推送内容
        String content = "";
        // 推送标题
        String title = "";
        // 消息类型
        Integer type = null;

        // 整改状态
        int correctiveStatus = inspectionEntity.getCorrectiveStatus();

        // 推送人员集合
        Set<String> users = new HashSet<String>();

        // 如果登录用户和创建整改单的用户相同，则推送用户为整改单的责任人
        if (entity.getPersonId().equals(inspectionEntity.getCreateBy())) {
            users.add(inspectionEntity.getUserId());
        } else {
            users.add(inspectionEntity.getCreateBy());
        }

        if (inspectionEntity.getType() == 1) {

            switch (correctiveStatus) {
                case InspectionEntity.CORRECTIVE_STATUS_TWO:
                    title = MESSAGE_QUALITY_CHECK_TITLE;
                    content = MESSAGE_QUALITY_CHECK_CONTENT;
                    type = MESSAGE_TYPE_QUALITY;

                    break;
                case InspectionEntity.CORRECTIVE_STATUS_THREE:
                    title = MESSAGE_QUALITY_CHECK_NOPASS_TITLE;
                    content = MESSAGE_QUALITY_CHECK_NOPASS_CONTENT;
                    type = MESSAGE_TYPE_QUALITY;

                    break;
                case InspectionEntity.CORRECTIVE_STATUS_FOUR:
                    title = MESSAGE_QUALITY_PASS_TITLE;
                    content = MESSAGE_QUALITY_PASS_CONTENT;
                    type = MESSAGE_TYPE_QUALITY;

                    break;
                default:
                    break;
            }

        } else if (inspectionEntity.getType() == 2 || inspectionEntity.getType() == 3) {

            switch (correctiveStatus) {
                case InspectionEntity.CORRECTIVE_STATUS_TWO:
                    title = MESSAGE_SAFE_CHECK_TITLE;
                    content = MESSAGE_SAFE_CHECK_CONTENT;
                    type = MESSAGE_TYPE_SAFE;

                    break;
                case InspectionEntity.CORRECTIVE_STATUS_THREE:
                    title = MESSAGE_SAFE_CHECK_NOPASS_TITLE;
                    content = MESSAGE_SAFE_CHECK_NOPASS_CONTENT;
                    type = MESSAGE_TYPE_SAFE;

                    break;
                case InspectionEntity.CORRECTIVE_STATUS_FOUR:
                    title = MESSAGE_SAFE_PASS_TITLE;
                    content = MESSAGE_SAFE_PASS_CONTENT;
                    type = MESSAGE_TYPE_SAFE;

                    break;
                default:
                    break;
            }
        }

        MessageVo vo = new MessageVo();
        vo.setUsers(users);
        vo.setConstructionId(inspectionEntity.getConstructionId());
        vo.setTitle(title);
        vo.setContent(content);
        vo.setType(type);
        vo.setBillId(inspectionId);
        vo.setStatus(entity.getCorrectiveStatus());
        // 发送消息
        MessagePushUtils.sendMessage(vo);
    }

    public Map<String, Object> queryWordInfo(String id, Integer type, Integer flag) {
        Map<String, Object> map = inspectionMapper.selectWordInfo(id);

        List<NotifyEntity> list = inspectionMapper.selectNotifyEntityById(id);
        String notifyName = "";
        if (!CollectionUtils.isEmpty(list)) {
            for (NotifyEntity entity : list) {
                notifyName += entity.getName() + ",";
            }
            notifyName = notifyName.substring(0, notifyName.length() - 1);
        }
        String title = "施工安全分项检查记录表";
        switch (type) {
            case 3:
                title = "日常安全检查记录表";
                break;
            case 2:
                if (flag != null) {
                    title = "安全检查隐患整改单";
                } else {
                    title = "施工安全分项检查记录表";
                }
                break;
            case 1:
                if (flag != null) {
                    title = "质量检查隐患整改单";
                } else {
                    title = "施工安全质量检查记录表";
                }
                break;
        }
        map.put("title", title);
        // 告知人
        map.put("notifyName", notifyName);

        // 检查记录
        String record = queryResultsByInspectionId(id);
        map.put("record", record);

        // 检查记录图片
        Integer isAttach = (Integer) map.get("isAttach");
        List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
        if (isAttach.intValue() == 1) {
            // 通过id获取上传的附件图片的路径集合
            filePathVOs = fileService.queryFilePath(id);
        }

        List<String> imList = new ArrayList<String>();
        for (FilePathVO vo : filePathVOs) {
            String images = getImageStr(vo.getFilePath());
            imList.add(images);
        }
        map.put("images", imList);

        //复检图片
        map.put("retests", getRetest(id));
        return map;
    }

    /**
     * @param id
     * @return
     * @desc 获取复检信息
     * @author niejing
     * @date 2018年1月4日 下午4:25:58
     */
    private List<RetestVo> getRetest(String id) {
        List<RetestVo> retestList = new ArrayList<RetestVo>();
        try {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("inspectionId", id);
            List<CorrectiveDetailVO> detailList = inspectionMapper.selectCorrectiveProcessList(paraMap);
            Collections.reverse(detailList);
            for (CorrectiveDetailVO detailVo : detailList) {
                if (detailVo.getCorrectiveStatus().intValue() == 1) {
                    continue;
                }
                RetestVo vo = new RetestVo();
                List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
                if (detailVo.getIsAttach() == 1) {
                    // 通过id获取上传的附件图片的路径集合
                    filePathVOs = fileService.queryFilePath(detailVo.getId());
                }
                List<String> contentList = new ArrayList<String>();
                for (FilePathVO filevo : filePathVOs) {
                    String images = getImageStr(filevo.getFilePath());
                    contentList.add(images);
                }
                String createDate = DateUtils.format(detailVo.getCreateTime(), DateUtils.TO_SECOND);
                String remark = detailVo.getRemark() != null ? detailVo.getRemark() : "";
                vo.setCreateDate(createDate);
                vo.setReviewContent(contentList);
                vo.setReviewResult(getStatusMsg(detailVo.getCorrectiveStatus()));
                vo.setRemark(remark);
                vo.setTitle(getTitle(detailVo.getCorrectiveStatus()) + "(" + createDate + ")");
                retestList.add(vo);
            }
        } catch (Exception e) {
            throw new EscstException("获取复检信息异常", e);
        }

        return retestList;
    }

    private String getStatusMsg(Integer status) {
        String msg = "";
        switch (status) {
            case 1:
                msg = "待整改";
                break;
            case 2:
                msg = "待检测";
                break;
            case 3:
                msg = "检查不通过";
                break;
            case 4:
                msg = "已整改";
                break;
        }
        return msg;
    }

    private String getTitle(Integer status) {
        String msg = "";
        switch (status) {
            case 1:
            case 2:
                msg = "提交复检";
                break;
            case 3:
            case 4:
                msg = "复查情况";
                break;
        }
        return msg;
    }

    public String queryResultsByInspectionId(String inspectionId) {
        List<InspectionResultsVO> list = inspectionMapper.queryResultsByInspectionId(inspectionId);
        Map<String, TreeVO> parentItemsMap = new LinkedHashMap<String, TreeVO>();// key 一级的ID, value 一级对象
        Map<String, TreeVO> itemsMap = new HashMap<String, TreeVO>();// key 二级的ID, value 三级
        for (InspectionResultsVO resultsVO : list) {
            String parentItemsId = resultsVO.getParentItemsId();
            String itemsId = resultsVO.getItemsId();
            String resultsId = resultsVO.getId();

            TreeVO results = new TreeVO();
            results.setId(resultsId);
            results.setName(resultsVO.getName());

            if (itemsMap.containsKey(itemsId)) {
                TreeVO itemsVO = itemsMap.get(itemsId);
                itemsVO.getData().add(results);
            } else {
                // 新建子检查项
                TreeVO itemsVO = new TreeVO();
                itemsVO.setId(itemsId);
                itemsVO.setName(resultsVO.getItemsName());
                itemsVO.setData(new ArrayList<TreeVO>());
                itemsVO.getData().add(results);
                itemsMap.put(itemsId, itemsVO);

                if (parentItemsMap.containsKey(parentItemsId)) {
                    TreeVO parentItemsVO = parentItemsMap.get(parentItemsId);
                    parentItemsVO.getData().add(itemsVO);
                } else {
                    TreeVO parentItemsVO = new TreeVO();
                    parentItemsVO.setId(parentItemsId);
                    parentItemsVO.setName(resultsVO.getParentItemsName());
                    parentItemsVO.setData(new ArrayList<TreeVO>());
                    parentItemsVO.getData().add(itemsVO);
                    parentItemsMap.put(parentItemsId, parentItemsVO);
                }
            }
        }
        List<TreeVO> rst = new ArrayList<TreeVO>();
        Iterator<Map.Entry<String, TreeVO>> it = parentItemsMap.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        int count = 1;
        while (it.hasNext()) {
            Map.Entry<String, TreeVO> entry = it.next();
            rst.add(entry.getValue());
            TreeVO vo = (TreeVO) entry.getValue();
            String topName = vo.getName() + "--";
            for (TreeVO vo_2 : vo.getData()) {
                topName = topName + vo_2.getName() + "--";
                for (TreeVO vo_3 : vo_2.getData()) {
                    sb.append(count + ":" + topName + vo_3.getName());
                    sb.append('\n');
                    count++;
                }
            }
        }
        return sb.toString();
    }

    private String getImageStr(String filePath) {
        byte[] data = null;
        try {
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            data = readInputStream(inStream);// 得到图片的二进制数据
        } catch (Exception e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    private static String getImageStr() {
        String imgFile = "F:/test.JPG";
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    /**
     * @param entity
     * @return
     * @desc 添加安全和质量检查工作动态
     * @author dwj
     * @date 2018/4/11 9:49
     */
    public void saveWorkState(InspectionEntity entity) {
        WorkTrendEntity workTrendEntity = new WorkTrendEntity();
        workTrendEntity.setId(UuidUtils.getUuid());
        workTrendEntity.setConstructionId(entity.getConstructionId());
        workTrendEntity.setCreateTime(new Date());
        workTrendEntity.setBusinessId(entity.getId());
        //获取项目名称
        if (entity.getType().equals(InspectionTypeEnum.QUALITY.getValue())) {
            if (entity.getProcessingOpinion() == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
                workTrendEntity.setUserId(entity.getContactId());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + QUALITY_IMPROVEMENT);
                workTrendEntity.setConstructionContent(entity.getContactName() + QUALITY_IMPROVEMENT);
            } else {
                workTrendEntity.setUserId(entity.getCreateBy());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + QUALITY_INSPECTION);
                workTrendEntity.setConstructionContent(entity.getContactName() + QUALITY_INSPECTION);
            }
        } else if (entity.getType().equals(InspectionTypeEnum.SAFETY_ITEMS.getValue())) {
            if (entity.getProcessingOpinion() == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
                workTrendEntity.setUserId(entity.getContactId());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + SAFETY_RECTIFICATION);
                workTrendEntity.setConstructionContent(entity.getContactName() + SAFETY_RECTIFICATION);
            } else {
                workTrendEntity.setUserId(entity.getCreateBy());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + SAFETY_SUB_INSPECTION);
                workTrendEntity.setConstructionContent(entity.getContactName() + SAFETY_SUB_INSPECTION);
            }
        } else if (entity.getType().equals(InspectionTypeEnum.SAFETY_EVERYDAY.getValue())) {
            if (entity.getProcessingOpinion() == InspectionEntity.PROCESSING_OPINION_CORRECTIVE) {
                workTrendEntity.setUserId(entity.getContactId());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + SAFETY_RECTIFICATION);
                workTrendEntity.setConstructionContent(entity.getContactName() + SAFETY_RECTIFICATION);
            } else {
                workTrendEntity.setUserId(entity.getCreateBy());
                workTrendEntity.setBusinessTime(DateUtils.format(entity.getCreateTime(),DateUtils.TO_SECOND));
                workTrendEntity.setCompanyContent(entity.getConstructionName() + SAFETY_DAILY_CHECK);
                workTrendEntity.setConstructionContent(entity.getContactName() + SAFETY_DAILY_CHECK);
            }
        }
        workTrendMapper.save(workTrendEntity);
    }


    /**
     * @param id          检查单id
     * @param resultsList 检查项集合
     * @return void
     * @desc 批量添加检查项信息
     * @method batchInsertInspectionCheckItems
     * @author jincheng
     * @date 2018/1/21 18:29
     */
    private void batchInsertInspectionCheckItems(String id, String resultsList) {
        if (StringUtils.isEmpty(resultsList)) {
            return;
        }
        List<BaseVO> baseVOList = null;
        try {
            baseVOList = JSONArray.parseArray(resultsList, BaseVO.class);
        } catch (Exception e) {
            String msg = "json字符串(%s)解析为数组时出现异常";
            throw new EscstException(String.format(msg, resultsList));
        }
        List<InspectionVO> list = new ArrayList<InspectionVO>();
        if (org.apache.commons.collections.CollectionUtils.isEmpty(baseVOList)) {
            return;
        }
        for (BaseVO baseVO : baseVOList) {
            InspectionVO vo = new InspectionVO();
            vo.setInspectionId(id);
            vo.setIteamId(baseVO.getId());
            vo.setName(baseVO.getName());
            list.add(vo);
        }
        // 添加到数据库
        inspectionMapper.batchInsertCheckItems(list);
    }


    /**
     * @param vo
     * @return java.util.List<com.escst.task.entity.ScheduledPlanEntity>
     * @desc 查询检查单对应的检查部位信息
     * @method queryInspectionProjectStructure
     * @author jincheng
     * @date 2018/1/21 18:53
     */
    public List<ScheduledPlanEntity> queryInspectionProjectStructure(InspectionProjectStructureVO vo) {
        try {
            // 获得工地的工程结构
            ScheduledPlanEntity scheduledPlanEntity = new ScheduledPlanEntity();
            if (StringUtils.isNotBlank(vo.getConstructionId())) {
                scheduledPlanEntity.setConstructionId(vo.getConstructionId());
            }
            List<ScheduledPlanEntity> structureList = taskDistributionService.queryScheduledPlanList(scheduledPlanEntity);


            // 检查单Id
            String inspectionId = vo.getInspectionId();
            // 查询检查单对应的检查部位id集合(子表)
            List<ScheduledPlanEntity> sonList = inspectionMapper.selectProjectStructureByInspectionId(inspectionId);
            if (!CollectionUtils.isEmpty(sonList)) {
                // 赋值给全局变量，被选中的子节点集合
                this.pickList = sonList;
            } else {
                // 查询检查单对应的检查部位id集合(主表)
                List<ScheduledPlanEntity> hostList = inspectionMapper.queryProjectStructureByInspectionId(inspectionId);
                // 赋值给全局变量，被选中的子节点集合
                this.pickList = hostList;
            }
            // 调用递归方法，查询出子节点之上的所有父节点
            List<ScheduledPlanEntity> list = this.recursion(structureList);
            return list;
        } catch (Exception e) {
            throw new EscstException("保存检查记录出现异常：" + e.getMessage(), e);
        }

    }

    /**
     * @param list
     * @return java.util.List<com.escst.task.entity.ScheduledPlanEntity>
     * @desc 递归从子节点查出父节点
     * @method recursion
     * @author jincheng
     * @date 2018/1/21 20:37
     */
    private List<ScheduledPlanEntity> recursion(List<ScheduledPlanEntity> list) {
        List<ScheduledPlanEntity> newDatas = new ArrayList<ScheduledPlanEntity>();
        for (int i = 0; i < list.size(); i++) {
            try {
                ScheduledPlanEntity s = list.get(i);
                ScheduledPlanEntity cs = (ScheduledPlanEntity) s.clone();
                List<ScheduledPlanEntity> datas = recursion(s.getData());
                if (pickList.contains(cs)) {
                    newDatas.add(cs);
                } else {
                    if (datas.size() > 0) {
                        cs.setData(datas);
                        newDatas.add(cs);
                    }
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return newDatas;
    }

    /**
     * @param id                   检查单id
     * @param projectStructureList 检查部位集合
     * @return void
     * @desc 批量添加检查部位信息
     * @method batchInsertInspectionProjectStructure
     * @author jincheng
     * @date 2018/1/21 18:29
     */
    private void batchInsertInspectionProjectStructure(String id, String projectStructureList) {
        if (StringUtils.isEmpty(projectStructureList)) {
            return;
        }
        List<BaseVO> baseVOList = null;
        try {
            baseVOList = JSONArray.parseArray(projectStructureList, BaseVO.class);
        } catch (Exception e) {
            String msg = "json字符串(%s)解析为数组时出现异常";
            throw new EscstException(String.format(msg, projectStructureList));
        }
        if (CollectionUtils.isEmpty(baseVOList)) {
            return;
        }
        List<InspectionProjectStructureVO> list = new ArrayList<InspectionProjectStructureVO>();
        for (BaseVO baseVO : baseVOList) {
            InspectionProjectStructureVO entity = new InspectionProjectStructureVO();
            entity.setInspectionId(id);
            entity.setProjectStructureId(baseVO.getId());
            entity.setProjectStructureName(baseVO.getName());
            list.add(entity);
        }
        // 添加到数据库
        inspectionMapper.batchInsertProjectStructure(list);
    }

    /**
     * @return
     * @desc 获取该工地下最新整改单号
     * @author niejing
     * @date 2018年5月15日 下午4:48:39
     */
    private String getLatestOrderNo(String constructionId, int type) {
        String orderNo = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("constructionId", constructionId);
            map.put("type", type);
            orderNo = inspectionMapper.selectLatestOrderNo(map);

            // 初始化整改单号
            if (StringUtils.isBlank(orderNo)) {
                // 质量检查
                if (type == 1) {
                    orderNo = ZL_ORDER_NO;
                }
                // 分项检查
                else if (type == 2) {
                    orderNo = AQ_FX_ORDER_NO;
                }
                // 安全检查
                else if (type == 3) {
                    orderNo = AQ_RC_ORDER_NO;
                }
            } else {
                String[] tmpStr = orderNo.split("-");
                if (tmpStr.length > 2) {
                    orderNo = tmpStr[0] + "-" + tmpStr[1] + "-" + increase(tmpStr[2]);
                } else {
                    orderNo = tmpStr[0] + "-" + increase(tmpStr[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("获取最新整改单号异常：" + e);
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


    /**
     * @param entity
     * @return java.util.List<com.escst.commons.tree.TreeEntity>
     * @desc 查询分项检查
     * @author jincheng
     * @date 2018-4-8 11:48
     */
    public PageVo listCheckItemTree(TreeEntity entity) {
        try {
            PageVo pageVo = new PageVo();
            if (!com.alibaba.druid.util.StringUtils.isEmpty(entity.getId())) {
                // 查子节点，不分页
                entity.setStartIndex(0);
                entity.setRowNum(Integer.MAX_VALUE);
            }
            // 根据工地id查询总记录数
            int count = inspectionMapper.getCheckItemCount(entity);

            if (count == 0) {
                return pageVo;
            }

            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 设置总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引

            // 处理easyUI树翻页的问题
            int startIndex = ((entity.getPage() - 1) * (entity.getRowNum()) == Integer.MAX_VALUE) ? 0 : (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);

            // 原始数据
            List<TreeEntity> treeList = inspectionMapper.listCheckItemTree(entity);

            // 获得节点下的叶子节点数量
            List<TreeEntity> parentList = inspectionMapper.isParent(treeList);

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
            throw new EscstException("查询分项检查异常！", e);
        }
    }

    /**
     * @param treeEntity
     * @return void
     * @desc 添加分项检查
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    public void addCheckItem(TreeEntity treeEntity) {
        try {
            treeEntity.setId(UuidUtils.getUuid());
            if (com.alibaba.druid.util.StringUtils.isEmpty(treeEntity.getpId())) {
                treeEntity.setpId("0");
            }
            treeEntity.setCreateTime(new Date());
            treeEntity.setCreateBy(ContextUtils.getCurrentUserId());
            inspectionMapper.addCheckItem(treeEntity);
        } catch (Exception e) {
            throw new EscstException("添加分项检查异常！", e);
        }
    }


    /**
     * @param treeEntity
     * @return void
     * @desc 修改工程结构
     * @author jincheng
     * @date 2018-4-8 14:57
     */
    public String updateCheckItem(TreeEntity treeEntity) {
        try {

            String constructionId = inspectionMapper.getConstructionId(treeEntity.getId());

            if (StringUtils.isBlank(constructionId)) {
                return "默认检查项,无法修改";
            } else {
                treeEntity.setUpdateBy(ContextUtils.getCurrentUserId());
                treeEntity.setUpdateTime(new Date());
                inspectionMapper.updateCheckItem(treeEntity);
                return "编辑成功";
            }

        } catch (Exception e) {
            throw new EscstException("修改工程结构异常！", e);
        }
    }


    /**
     * @param treeEntity
     * @return void
     * @desc 删除分项检查
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    public String deleteCheckItem(TreeEntity treeEntity) {
        try {

            String constructionId = inspectionMapper.getConstructionId(treeEntity.getId());

            if (StringUtils.isBlank(constructionId)) {
                return "默认检查项,无法删除";
            } else {
                // 获得所有的ID，父ID集合
                List<TreeEntity> allList = inspectionMapper.listAll(treeEntity.getConstructionId());
                // 获得Id下的所有子节点ID
                List<TreeEntity> idList = this.listAllNode(allList, treeEntity.getId());
                // 添加父节点ID
                idList.add(treeEntity);
                inspectionMapper.batchDelete(idList);
                return "删除成功";
            }

        } catch (Exception e) {
            throw new EscstException("删除分项检查异常！", e);
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


    /**
     * @param bean
     * @return
     * @desc 获取工地下分包公司安全，质量检查总数
     * @author dwj
     * @date 2018/5/16 15:32
     */
    public List<ItemsCountVo> selectItemsByConstructionId(InspectionRequestBean bean) {
        List<ItemsCountVo> itemsCountVoList = new ArrayList<ItemsCountVo>();
        Map<String, List<ItemsBean>> rst = new HashMap<String, List<ItemsBean>>();
        try {
            Map<String, List<String>> paramMap = new HashMap<String, List<String>>();
            Integer type = bean.getType();
            String constructionId = bean.getConstructionId();
            Map<String, Object> paraMap = new HashMap<String, Object>();
            if (type.equals(InspectionTypeEnum.QUALITY.getValue())) {
                paraMap.put("types", String.valueOf(type.intValue()));
            } else {
                String types = InspectionTypeEnum.SAFETY_ITEMS.getValue() + "," + InspectionTypeEnum.SAFETY_EVERYDAY.getValue();
                paraMap.put("types", types);
            }
            paraMap.put("constructionId", constructionId);
            paraMap.put("processingOpinion", InspectionEntity.PROCESSING_OPINION_CORRECTIVE);
            paraMap.put("companyId", bean.getProjectCompanyId());
            paraMap.put("name", bean.getItems());
            paraMap.put("startDate", bean.getStartDate());
            paraMap.put("endDate", bean.getEndDate());
            List<BaseVO> list = inspectionMapper.selectItemsByConstructionId(paraMap);
            if (CollectionUtils.isEmpty(list)) {
                return itemsCountVoList;
            }
            for (BaseVO vo : list) {
                String id = vo.getId();
                String name = vo.getName();
                if (StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
                    continue;
                }

                if (paramMap.containsKey(id)) {
                    List<String> resultList = paramMap.get(id);
                    resultList.add(name);
                } else {
                    List<String> resultList = new ArrayList<String>();
                    resultList.add(name);
                    paramMap.put(id, resultList);
                }


            }

            for (String key : paramMap.keySet()) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                List<String> nameList = paramMap.get(key);
                for (String name : nameList) {
                    String[] names = name.split(",");
                    List<String> nameS = Arrays.asList(names);
                    for (String s : nameS) {
                        if (map.containsKey(s)) {
                            int count = map.get(s);
                            count++;
                            map.put(s, count);
                        } else {
                            int count = 1;
                            map.put(s, count);
                        }
                    }

                    List<ItemsBean> ibList = new ArrayList<ItemsBean>();
                    for (String k : map.keySet()) {
                        ItemsBean ib = new ItemsBean();
                        ib.setName(k);
                        ib.setQty(map.get(k));
                        ibList.add(ib);
                    }
                    rst.put(key, ibList);

                }
            }
            for (String key : rst.keySet()) {
                ItemsCountVo itemsCountVo = new ItemsCountVo();
                ProjectCompanyEntity entity = projectCompanyMapper.selectById(key);
                if (entity != null) {
                    itemsCountVo.setCompanyName(entity.getName());
                } else {
                    itemsCountVo.setCompanyName("");
                }
                itemsCountVo.setCompanyId(key);
                itemsCountVo.setData(rst.get(key));
                itemsCountVoList.add(itemsCountVo);
            }
        } catch (Exception e) {
            throw new EscstException("查询检查项数失败" + e.getMessage());
        }
        return itemsCountVoList;
    }


    /**
     * 判断检查单是否有检查结果
     */
    public boolean isHave(String id) {
        int count = inspectionMapper.isHave(id);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param vo
     * @return java.util.List<com.escst.task.entity.ScheduledPlanEntity>
     * @desc 查询检查单对应的检查项
     * @method queryInspectionItem
     * @author jincheng
     * @date 2018/1/21 18:53
     */
    public List<InspectionVO> queryInspectionItem(InspectionVO vo) {
        try {
            // 获得工地的检查项
            List<InspectionVO> voList = new ArrayList<InspectionVO>();

            voList = this.queryInspectionVOList(vo);

            // 检查单Id
            String inspectionId = vo.getId();
            // 查询检查单对应的检查部位id集合(子表)
            List<InspectionVO> sonList = inspectionMapper.selectItemByInspectionId(inspectionId);
            // 去重
//            sonList = sonList.stream().distinct().collect(Collectors.toList());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(sonList)) {
                // 赋值给全局变量，被选中的子节点集合
                this.itemList = sonList;
            }
            // 调用递归方法，查询出子节点之上的所有父节点
            List<InspectionVO> list = this.recursionItem(voList);
            return list;
        } catch (Exception e) {
            throw new EscstException("保存检查记录出现异常：" + e.getMessage(), e);
        }
    }

    /**
     * @param list
     * @return java.util.List<com.escst.task.entity.ScheduledPlanEntity>
     * @desc 递归从子节点查出父节点
     * @method recursion
     * @author jincheng
     * @date 2018/1/21 20:37
     */
    private List<InspectionVO> recursionItem(List<InspectionVO> list) {
        List<InspectionVO> newDatas = new ArrayList<InspectionVO>();
        for (int i = 0; i < list.size(); i++) {
            try {
                InspectionVO s = list.get(i);
                InspectionVO cs = (InspectionVO) s.clone();
                List<InspectionVO> datas = recursionItem(s.getData());
                if (itemList.contains(cs)) {
                    newDatas.add(cs);
                } else {
                    if (datas.size() > 0) {
                        cs.setData(datas);
                        newDatas.add(cs);
                    }
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return newDatas;
    }


    /**
     * @param inspectionVO
     * @return java.util.List<com.escst.task.entity.ScheduledPlanEntity>
     * @desc 查询工地下的检查项
     * @author jincheng
     * @date 2018-7-5 13:24
     */
    public List<InspectionVO> queryInspectionVOList(InspectionVO inspectionVO) {
        List<InspectionVO> list = new ArrayList<InspectionVO>();
        try {
            List<InspectionVO> voList = inspectionMapper.queryInspectionVOList(inspectionVO);
            if (!CollectionUtils.isEmpty(voList)) {
                for (InspectionVO vo : voList) {
                    String id = vo.getId();
                    String parentId = vo.getParentId();
                    if ("0".equals(parentId) || org.springframework.util.StringUtils.isEmpty(parentId)) {
                        List<InspectionVO> childrenList = buildData(voList, vo);
                        vo.setData(childrenList);
                        list.add(vo);
                    }
                }
            }

        } catch (Exception e) {
            throw new EscstException("查询检查项失败" + e.getMessage());
        }
        return list;
    }

    private List<InspectionVO> buildData(List<InspectionVO> list, InspectionVO vo) {
        //获取该父节点下的子节点数据
        List<InspectionVO> childrenList = getChildren(list, vo);
        if (!CollectionUtils.isEmpty(childrenList)) {
            for (InspectionVO child : childrenList) {
                String id = child.getId();
                List<InspectionVO> childList = buildData(list, child);
                child.setData(childList);
            }
        }
        return childrenList;
    }

    private List<InspectionVO> getChildren(List<InspectionVO> list, InspectionVO vo) {
        List<InspectionVO> childrenList = new ArrayList<InspectionVO>();
        String id = vo.getId();
        for (InspectionVO child : list) {
            String parentId = child.getParentId();
            if (id.equals(parentId)) {
                childrenList.add(child);
            }
        }
        return childrenList;
    }


    /**
     * @param entity
     * @return com.escst.commons.vo.ReturnJson
     * @desc 南钢项目数据监控大屏，获取检查单记录
     * @author jincheng
     * @date 2018-8-17 14:59
     */
    public List<InspectionVO> listInspection(InspectionEntity entity) {
        List<InspectionVO> list = new ArrayList<InspectionVO>();
        try {
            // 查询检查单记录
            entity.setDate(DateUtils.getCurrentDate().substring(0, 10));
            if (entity.getType() != null) {
                list = inspectionMapper.listInspectionIsPush(entity);
            } else {
                list = inspectionMapper.listInspection(entity);
            }
            if (CollectionUtils.isEmpty(list)) {
                return list;
            }
            // 封装检查单对应的检查部位，检查项
            for (InspectionVO inspectionVO : list) {
                String projectStructureName = inspectionMapper.selectProjectStructureNameByInspectionId(inspectionVO.getId());
                inspectionVO.setProjectStructureName(projectStructureName);
            }
            // 修改推送状态
            inspectionMapper.batchUpdate(list);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "获取检查单记录异常", e);
        }
    }

}