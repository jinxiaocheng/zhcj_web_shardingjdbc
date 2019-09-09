package com.escst.person.service;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.escst.attendance.mapper.AttendanceClockRecordMapper;
import com.escst.commons.constant.Globals;
import com.escst.commons.excel.XssfExcelHelper;
import com.escst.commons.exception.EscstException;
import com.escst.commons.threadpool.SimpleThreadPool;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.HttpClientUtils;
import com.escst.commons.utils.MD5Util;
import com.escst.commons.utils.PinyinUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageAuthVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.QtyVO;
import com.escst.construction.mapper.ConstructionMapper;
import com.escst.dictionary.entity.DictionaryEntity;
import com.escst.dictionary.service.DictionaryService;
import com.escst.excelimport.service.PersonExcelImportService;
import com.escst.excelimport.service.PersonExcelImportSyncService;
import com.escst.excelimport.vo.PersonImportVO;
import com.escst.file.service.FileService;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.person.entity.ConstructionPersonEntity;
import com.escst.person.entity.PersonConditionBean;
import com.escst.person.entity.PersonEntity;
import com.escst.person.entity.PersonExcelEntity;
import com.escst.person.entity.QualificatioEntity;
import com.escst.person.mapper.PersonMapper;
import com.escst.person.vo.PersonQueryVO;
import com.escst.person.vo.WorkTypeDateVO;
import com.escst.person.vo.WorkTypeQtyVO;
import com.escst.person.vo.WorkTypeQueryVO;
import com.escst.projectCompany.bean.CompanyDetailVO;
import com.escst.projectCompany.entity.ProjectCompanyEntity;
import com.escst.projectCompany.service.ProjectCompanyService;
import com.escst.team.bean.TeamLeaderVO;
import com.escst.team.entity.TeamEntity;
import com.escst.team.service.TeamService;
import com.escst.territory.entity.TerritoryEntity;
import com.escst.territory.service.TerritoryService;
import com.escst.thread.ExcelPersonSyncThread;
import com.escst.thread.PersonSyncThread;
import com.escst.user.entity.UserEntity;
import com.escst.user.service.UserService;

/**
 * @desc
 * @Author
 * @createDate 2017/3/9 11:21
 */
@Service
@Transactional
public class PersonService {

    private static final String PERSON = "person";

    private static final String EXCEL = "excel";

    private static final String IDCARD_READER = ResourceUtil.getConfigByName("IdCard_Reader");

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ProjectCompanyService projectCompanyService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private QualificatioService qualificatioService;

    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private PersonExcelImportService personExcelImportService;

    @Autowired
    private PersonSyncService personSyncService;

    @Autowired
    private PersonExcelImportSyncService personExcelImportSyncService;

    @Autowired
    private ConstructionMapper constructionMapper;

    private AttendanceClockRecordMapper attendanceClockRecordMapper;

    //分配默认密码
    private static final String DEFAULT_PASSWORD = "123456";

    //有效
    private static final int VALID = 1;

    /**
     * 分页查询人员列表信息
     *
     * @param bean
     * @return
     */
    public PageVo queryPersonPageList(PersonConditionBean bean) {
        PageVo pageVo = new PageVo();
        try {
            // 根据工地id查询人员总记录数
            int count = personMapper.selectCount(bean);
            // 当前页号
            pageVo.setCurrentPage(bean.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);

            pageVo.setPageSize(bean.getRowNum());
            // 每页的第一条记录的索引
            int startIndex = (bean.getPage() - 1) * (bean.getRowNum());
            bean.setStartIndex(startIndex);
            List<Map<String, Object>> list = null;

            if (count > 0) {
                list = personMapper.selectPersonList(bean);
            }

            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("查询人员列表信息失败！", e);
        }
        return pageVo;
    }

    /**
     * 查询人员详细信息
     *
     * @param personId 人员ID
     * @return 返回值
     */
    public PersonEntity getPersonById(String personId) {
        PersonEntity entity = null;
        List<PersonEntity> list = personMapper.getPersonById(personId);
        if (CollectionUtils.isEmpty(list)) {
            throw new EscstException("人员ID不存在");
        }
        if (list.size() > 1) {
            //TODO t_basic_construction_person 里面有超过1条的记录怎么处理
        }
        entity = list.get(0);

        String headPortrait = entity.getHeadPortraitId();
        if (!StringUtils.isEmpty(headPortrait)) {
            entity.setHeadFilePath(fileService.queryFilePathById(headPortrait));
        }
        String idCardFront = entity.getIdCardFrontId();
        if (!StringUtils.isEmpty(idCardFront)) {
            entity.setIdCardFrontFilePath(fileService.queryPathByBusinessId(idCardFront));
        }
        String idCardBack = entity.getIdCardBackId();
        if (!StringUtils.isEmpty(idCardBack)) {
            entity.setIdCardBackFilePath(fileService.queryPathByBusinessId(idCardBack));
        }
        String qualificationCertificate = entity.getQualificationCertificateId();
        if (!StringUtils.isEmpty(qualificationCertificate)) {
            entity.setQualificationCertificateFilePath(fileService.queryPathByBusinessId(qualificationCertificate));
        }
        // add by zhouwei 2017-11-02 增加公司和班组信息
        if (StringUtils.isNotBlank(entity.getProjectCompanyId())) {
            String projectCompanyId = entity.getProjectCompanyId();
            ProjectCompanyEntity company = projectCompanyService.getById(projectCompanyId);
            entity.setProjectCompanyName(company.getName());
        }
        if (StringUtils.isNotBlank(entity.getTeamId())) {
            String teamId = entity.getTeamId();
            TeamEntity team = teamService.getById(teamId);
            entity.setTeamName(team.getName());
        }

        // 资质信息
//		String userId = entity.getUserId();
        String id = entity.getId();
        List<QualificatioEntity> qualificatioList = qualificatioService.queryByUserId(id);
        if (!CollectionUtils.isEmpty(qualificatioList)) {
            QualificatioEntity qualificatioEntity = qualificatioList.get(0);
            entity.setCertificateId(qualificatioEntity.getId());
            entity.setCertificateNumber(qualificatioEntity.getNumber());
            entity.setCertificateIssueDate(qualificatioEntity.getIssueDate());
            entity.setCertificateIssueOrgans(qualificatioEntity.getIssueOrgans());
            Integer certificateType = qualificatioEntity.getType();
            if (certificateType != null && certificateType.intValue() != 0) {
                entity.setCertificateType(certificateType);
                DictionaryEntity dictionaryEntity = dictionaryService.getByTypeValue("certificateType", certificateType);
                if (dictionaryEntity != null) {
                    entity.setCertificateTypeName(dictionaryEntity.getName());
                }
            }
            entity.setCertificateValidDate(qualificatioEntity.getValidDate());
        }
        String areaId = entity.getAreaId();
        if (StringUtils.isNotBlank(areaId)) {
            Map<String, TerritoryEntity> territoryMap = territoryService.getTerritoryMap();
            if (territoryMap.containsKey(areaId)) {
                String areaName = territoryMap.get(areaId).getName();
                entity.setAreaName(areaName);
            }
        }
        Integer positionWorkType = entity.getPositionWorkType();
        if (positionWorkType != null && positionWorkType.intValue() != 0) {
            DictionaryEntity dictionaryEntity = dictionaryService.getByTypeValue("positionWorkType", positionWorkType);
            if (dictionaryEntity != null) {
                entity.setPositionWorkTypeName(dictionaryEntity.getName());
            }
        }

        return entity;
    }

    /**
     * 删除人员
     *
     * @param personId 人员ID
     */
    public void delPerson(String personId, String constructionId, String clientUserId, String personName) {
        try {
            // 根据人员ID，拿到该人员的用户ID，同时修改人员和用户状态
//			String userId = personMapper.selUserId(personId);
            // 更新用户状态
//			personMapper.updateUserStatus(userId);

            // 更新人员状态
            personMapper.updatePersonStatus(personId);
            // 更新人员工地状态
            personMapper.updateConstructionPersonStatus(personId);

            PersonEntity personEntity = new PersonEntity();
            personEntity.setState(3);
            personEntity.setClientUserId(clientUserId);
            personEntity.setName(URLDecoder.decode(personName, "UTF-8"));
            personEntity.setConstructionId(constructionId);
            //取得单例线程池
            SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
            //创建一个新的线程实例
            PersonSyncThread thread = new PersonSyncThread(personEntity, personSyncService);
            //用线程池中的线程运行线程实例
            simpleThreadPool.executorService.execute(thread);

        } catch (Exception e) {
            throw new EscstException("删除人员失败！", e);
        }
    }

    public void addOrUpdatePerson(PersonEntity personEntity) {
        // 头像图片数据
        MultipartFile headFile = personEntity.getHeadFile();
        //头像图片数据
        String headFilePath = personEntity.getHeadFilePath();
        // 身份证正面图片数据
        MultipartFile idCardFrontFile = personEntity.getIdCardFrontFile();
        // 身份证反面图片数据
        MultipartFile idCardBackFile = personEntity.getIdCardBackFile();
        // 资质证书图片数据
        MultipartFile qualificationCertificateFile = personEntity.getQualificationCertificateFile();
        if (headFile != null) {
            //文件存储的存到数据库的路径
            String headFileId = fileService.uploadFile(PERSON, headFile);
            personEntity.setHeadPortraitId(headFileId);
        }
        if (idCardFrontFile != null) {
            String idCardFrontFileId = fileService.uploadFile(PERSON, idCardFrontFile);
            personEntity.setIdCardFrontId(idCardFrontFileId);
        }
        if (idCardBackFile != null) {
            String idCardBackFileId = fileService.uploadFile(PERSON, idCardBackFile);
            personEntity.setIdCardBackId(idCardBackFileId);
        }
        if (qualificationCertificateFile != null) {
            String certificateId = fileService.uploadFile(PERSON, qualificationCertificateFile);
            personEntity.setQualificationCertificateId(certificateId);
        }
        // 人员拼音
        if (StringUtils.isNotBlank(personEntity.getName())) {
            String code = PinyinUtils.getPinYinHeadChar(personEntity.getName());
            personEntity.setCode(code);
        } else {
            personEntity.setCode("");
        }
        // id不为空则执行更新操作，否则执行新增操作
        String id = personEntity.getId();
        if (StringUtils.isEmpty(id)) {
            if (StringUtils.isNoneBlank(personEntity.getCardNumber())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("constructionId", personEntity.getConstructionId());
                map.put("cardNumber", personEntity.getCardNumber());
                int count = this.personMapper.selectByCardNo(map);
                if (count != 0) {
                    throw new EscstException("卡号" + personEntity.getCardNumber() + "已存在");
                }
            }
            personEntity.setId(UuidUtils.getUuid());
            // 添加人员设置有效
            personEntity.setStatus(PersonEntity.STATUS_EFFECT);
            // 设置创建时间
            personEntity.setCreateTime(new Date());

            // 人员信息存到用户表
//			String userId = saveUserInfoByPerson(personEntity);

            // 设置用户id
//			personEntity.setUserId(userId);
            // 添加人员信息
            personMapper.insert(personEntity);
            String personId = personEntity.getId();
            String constructionId = personEntity.getConstructionId();
            ConstructionPersonEntity constructionPerson = new ConstructionPersonEntity();
            constructionPerson.setId(UuidUtils.getUuid());
            constructionPerson.setPersonId(personId);
            constructionPerson.setConstructionId(constructionId);
            constructionPerson.setProjectCompanyId(personEntity.getProjectCompanyId());

            //注释掉，班组信息让用户从页面传过来
//			Integer positionWorkType = personEntity.getPositionWorkType();
//			if (positionWorkType != null && positionWorkType.intValue() != 0) {
//				// add by zhouwei 2017-12-22 如果岗位/工种的值小于100,则为岗位,工种的值设置为0
//				Integer workType = positionWorkType;
//				if (positionWorkType.intValue() < 100) {
//					workType = 0;
//				}
//				TeamEntity teamEntity = teamService.getTeamByPositionWorkType(personEntity.getProjectCompanyId(), workType);
//				String teamId = null;
//				if (teamEntity == null) {
//					teamId = teamService.addTeam(constructionId, personEntity.getProjectCompanyId(), workType);
//				}
//				else {
//					teamId = teamEntity.getId();
//				}
//				constructionPerson.setTeamId(teamId);
//			}
            constructionPerson.setTeamId(personEntity.getTeamId());
//          int jobNumber = getMaxJobNumber(constructionId);
//          jobNumber++;
            if (StringUtils.isNotBlank(personEntity.getCardNumber())) {
                int jobNumber = Integer.parseInt(personEntity.getCardNumber());
                constructionPerson.setJobNumber(jobNumber);
            }
            constructionPerson.setStatus(PersonEntity.STATUS_EFFECT);
            constructionPerson.setIsLeader(personEntity.getIsLeader());
            constructionPerson.setCardNumber(personEntity.getCardNumber());
            constructionPerson.setBankCard(personEntity.getBankCard());
            // 添加工地人员信息数据
            personMapper.insertConstructionPersonInfo(constructionPerson);

            // 增加人员资质信息
            QualificatioEntity qualificatioEntity = new QualificatioEntity();
            qualificatioEntity.setId(UuidUtils.getUuid());
            qualificatioEntity.setIssueDate(personEntity.getCertificateIssueDate());
            qualificatioEntity.setIssueOrgans(personEntity.getCertificateIssueOrgans());
            qualificatioEntity.setValidDate(personEntity.getCertificateValidDate());
            qualificatioEntity.setType(personEntity.getCertificateType());
            qualificatioEntity.setNumber(personEntity.getCertificateNumber());
            qualificatioEntity.setStatus(PersonEntity.STATUS_EFFECT);
            qualificatioEntity.setConstructionId(personEntity.getConstructionId());
//			qualificatioEntity.setUserId(personEntity.getUserId());
            qualificatioEntity.setUserId(personEntity.getId());
            qualificatioEntity.setCreateTime(new Date());
            qualificatioEntity.setCreateBy(ContextUtils.getCurrentUserId());
            qualificatioService.insert(qualificatioEntity);

            if (StringUtils.isNotBlank(personEntity.getHeadPortraitId())) {
                personEntity.setHeadFilePath(fileService.queryFilePathById(personEntity.getHeadPortraitId()));
            }
            //取得单例线程池
            SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
            //创建一个新的线程实例
            PersonSyncThread thread = new PersonSyncThread(personEntity, personSyncService);
            //用线程池中的线程运行线程实例
            simpleThreadPool.executorService.execute(thread);
        }
        // 修改人员信息
        else {
            //修改工种
            personEntity.setPositionWorkType(personEntity.getPositionWorkType());
            personEntity.setUpdateTime(new Date());
            // 更新工地人员新消息
            personMapper.update(personEntity);
            // add by zhouwei 2017-11-06 修改用户表的基本信息
//			Map<String, Object> userMap = new HashMap<String, Object>();
//			userMap.put("id", personEntity.getUserId());
//			userMap.put("name", personEntity.getName());
//			userMap.put("sex", personEntity.getSex());
//			userMap.put("mobile", personEntity.getMobile());
//			userService.updateBaseInfo(userMap);
            Integer positionWorkType = personEntity.getPositionWorkType();
            String teamId = personEntity.getTeamId();
            if (StringUtils.isBlank(personEntity.getTeamId())) {
                if (positionWorkType != null && positionWorkType.intValue() != 0) {
                    TeamEntity teamEntity = teamService.getTeamByPositionWorkType(personEntity.getProjectCompanyId(), positionWorkType);
                    if (teamEntity == null) {
                        teamId = teamService.addTeam(personEntity.getConstructionId(), personEntity.getProjectCompanyId(), positionWorkType);
                    } else {
                        teamId = teamEntity.getId();
                    }
                }
            }
            // modify by zhouwei 2017-4-25 修改分包公司和班组的信息
            ConstructionPersonEntity constructionPerson = getConstructionPersonInfo(personEntity.getConstructionId(), id);
            if (constructionPerson == null) {
                constructionPerson = new ConstructionPersonEntity();
                constructionPerson.setId(UuidUtils.getUuid());
                constructionPerson.setPersonId(id);
                constructionPerson.setConstructionId(personEntity.getConstructionId());
                constructionPerson.setProjectCompanyId(personEntity.getProjectCompanyId());
                constructionPerson.setTeamId(teamId);
//                int jobNumber = getMaxJobNumber(personEntity.getConstructionId());
//                jobNumber++;
                if (StringUtils.isNotBlank(personEntity.getCardNumber())) {
                    int jobNumber = Integer.parseInt(personEntity.getCardNumber());
                    constructionPerson.setJobNumber(jobNumber);
                }
                constructionPerson.setCardNumber(personEntity.getCardNumber());
                constructionPerson.setStatus(PersonEntity.STATUS_EFFECT);
                constructionPerson.setIsLeader(personEntity.getIsLeader());
                // 添加工地人员信息数据
                personMapper.insertConstructionPersonInfo(constructionPerson);
            } else {
                constructionPerson.setPersonId(id);
                constructionPerson.setConstructionId(personEntity.getConstructionId());
                constructionPerson.setProjectCompanyId(personEntity.getProjectCompanyId());
                constructionPerson.setTeamId(teamId);
                constructionPerson.setCardNumber(personEntity.getCardNumber());
                constructionPerson.setIsLeader(personEntity.getIsLeader());
                personMapper.updateConstructionPersonInfo(constructionPerson);
            }
            String certificateId = personEntity.getCertificateId();
            if (StringUtils.isNotBlank(certificateId)) {
                QualificatioEntity qualificatioEntity = new QualificatioEntity();
                qualificatioEntity.setId(certificateId);
                qualificatioEntity.setNumber(personEntity.getCertificateNumber());
                qualificatioEntity.setType(personEntity.getCertificateType());
                qualificatioEntity.setIssueDate(personEntity.getCertificateIssueDate());
                qualificatioEntity.setIssueOrgans(personEntity.getCertificateIssueOrgans());
                qualificatioEntity.setValidDate(personEntity.getCertificateValidDate());
                qualificatioEntity.setUpdateTime(new Date());
                qualificatioService.update(qualificatioEntity);
            }

            personEntity.setState(2);
            if (StringUtils.isNotBlank(personEntity.getHeadPortraitId())) {
                personEntity.setHeadFilePath(fileService.queryFilePathById(personEntity.getHeadPortraitId()));
            }
            //取得单例线程池
            SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
            //创建一个新的线程实例
            PersonSyncThread thread = new PersonSyncThread(personEntity, personSyncService);
            //用线程池中的线程运行线程实例
            simpleThreadPool.executorService.execute(thread);
        }

    }

    private ConstructionPersonEntity getConstructionPersonInfo(String constructionId, String personId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("constructionId", constructionId);
        params.put("personId", personId);
        return personMapper.getConstructionPersonInfo(params);

    }

    /**
     * 获取某个工地下的最大的工号
     *
     * @param constructionId
     * @returni
     */
    private int getMaxJobNumber(String constructionId) {
        int maxNum = 0;
        try {
            maxNum = personMapper.selectMaxJobNumber(constructionId);
        } catch (Exception e) {
            throw new EscstException("获取某个工地下的最大工号异常！", e);
        }
        return maxNum;
    }

    /**
     * @param personEntity
     * @return
     * @desc 生成用户对象
     * @author zhouwei
     * @date 2017年11月8日 下午7:53:00
     */
    private UserEntity toUserEntity(PersonEntity personEntity) {
        String constructionId = personEntity.getConstructionId();
        List<OrgEntity> orgList = orgService.queryByConstructionId(constructionId);
        if (CollectionUtils.isEmpty(orgList)) {
            throw new EscstException("工地没有关联的机构,不能保存用户");
        }
        String orgId = orgList.get(0).getId();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UuidUtils.getUuid());
        userEntity.setStatus(personEntity.getStatus());
        userEntity.setUserName(personEntity.getMobile());
        String userPassword = userEntity.getUserName() + Globals.USER_DEFAULT_PASSWORD;
        userEntity.setUserPassword(MD5Util.md5Encode(userPassword));
        userEntity.setName(personEntity.getName());
        userEntity.setIdCard(personEntity.getIdCard());
        userEntity.setSex(personEntity.getSex());
        userEntity.setMobile(personEntity.getMobile());
        userEntity.setCreateBy(personEntity.getCreateBy());
        userEntity.setOrgId(orgId);
        return userEntity;
    }

    /**
     * 同步人员信息到用户表
     *
     * @param personEntity 人员信息

    private String saveUserInfoByPerson(PersonEntity personEntity) {
    String id;
    // 添加用户之前判断用户表是否有这个用户，如果没有则添加，有则直接查到用户id
    String mobile = personEntity.getMobile().trim();
    Map<String, Object> userMap = userService.selectUserMap(mobile);
    if (!CollectionUtils.isEmpty(userMap)) {
    id = (String) userMap.get("userId");
    String constructionId = personEntity.getConstructionId();     //工地ID
    boolean flag = checkPerson(id,constructionId);                //校验该用户是否已经存在
    if (flag) {
    String msg = "该工地已经有手机号码为[%s]的用户";
    throw new EscstException(String.format(msg, mobile));
    }

    // add by zhouwei 2017-12-22用户新增后,将该用户原来的人员和工地人员信息标识为无效
    disabledConstructionPersonStatus(id, constructionId);
    } else {
    UserEntity userEntity = toUserEntity(personEntity);
    userService.insert(userEntity);
    id = userEntity.getId();
    }
    return id;
    } */

    /**
     * @param userId
     * @param constructionId
     * @return
     * @desc
     * @author caozx
     * @date 2017/12/5 9:51
     */
    private boolean checkPerson(String userId, String constructionId) {
        boolean flag = false;
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("constructionId", constructionId);
        paramsMap.put("userId", userId);
        List<PersonEntity> list = personMapper.queryPersonByConstructionId(paramsMap);
        if (!CollectionUtils.isEmpty(list)) {
            flag = true;
        }
        return flag;
    }

    private void disabledConstructionPersonStatus(String userId, String constructionId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("constructionId", constructionId);
        paramsMap.put("userId", userId);
        personMapper.disabledPersonStatus(paramsMap);
        personMapper.disabledConstructionPersonStatus(paramsMap);
    }

    /**
     * 人员数据导入
     *
     * @param file
     * @return
     */
    public String importDataFromExcel(MultipartFile file, String constructionId) throws Exception {
        try {
            if (file == null) {
                throw new EscstException("文件不存在！");
            }
            // 上传文件，并存到文件数据库表
//		FileEntity entity = fileService.uploadTempFile(EXCEL, file);
//		// 文件的存放路径
//		String savePath = entity.getPath();
            String filePath = fileService.uploadTempFile(EXCEL, file);
            // 读excel文件
            XssfExcelHelper xssfExcelHelper = XssfExcelHelper.getInstance(filePath);
            List<PersonImportVO> list = xssfExcelHelper.readExcel(PersonImportVO.class, 0, true);
            String msg = this.importData(list, constructionId);
            return msg;
        } catch (Exception e) {
            throw new EscstException("导入人员数据失败：" + e.getMessage(), e);
        }
    }


    /**
     * @param list
     * @param constructionId
     * @return java.lang.String
     * @desc 导入EXCEL人员数据
     * @author jincheng
     * @date 2018-6-5 10:54
     */
    @Transactional
    private String importData(List<PersonImportVO> list, String constructionId) {
        List<PersonEntity> personList = new ArrayList<PersonEntity>();

        if (CollectionUtils.isEmpty(list)) {
            return "excel文件中没有数据!";
        }

        Set<String> cardNumberSet = new HashSet<String>();
        // 查询工地下的所有门禁卡号
        List<String> cardNumberList = constructionMapper.listCardNumber(constructionId);

        for (PersonImportVO personImportVO : list) {
            if (StringUtils.isBlank(personImportVO.getName())) {
                continue;
            }
            PersonEntity entity = new PersonEntity();

            // 人员表属性
            String id = UuidUtils.getUuid();
            entity.setId(id);
            entity.setStatus(1);
            entity.setName(personImportVO.getName());
            entity.setMobile(personImportVO.getMobile());
            entity.setPositionWorkTypeName(personImportVO.getPositionWorkTypeName());
//            // 查出岗位工种value
////            Integer positionWorkType = dictionaryService.getValueByTypeName("positionWorkType", personImportVO.getPositionWorkTypeName());
//            entity.setPositionWorkType(positionWorkType);
            entity.setJobNumber(personImportVO.getJobNumber());
            entity.setCardNumber(personImportVO.getJobNumber());
            String cardNumber = entity.getCardNumber();
            entity.setIdCard(personImportVO.getCardNumber());
            entity.setBankCard(personImportVO.getBankCard());
            entity.setCreateTime(new Date());

            if (StringUtils.isNotBlank(cardNumber)) {
                if (cardNumberList.contains(cardNumber)) {
                    throw new EscstException("导入失败！卡号重复" + "【" + cardNumber + "】");
                }

                if (cardNumberSet.contains(cardNumber)) {
                    throw new EscstException("导入失败！卡号重复" + "【" + cardNumber + "】");
                } else {
                    cardNumberSet.add(cardNumber);
                }
            }

            // 工地人员关系表属性
            entity.setPersonId(id);
            entity.setConstructionId(constructionId);
            // 查出分包公司Id
            entity.setProjectCompanyName(personImportVO.getCompanyName());
            try {
                String projectCompanyId = personMapper.getProjectCompanyId(entity);
                if (StringUtils.isBlank(projectCompanyId)) {
                    return "导入失败！请先录入公司" + "【" + personImportVO.getCompanyName() + "】" + ",或者检查EXCEL表格中公司名称是否填写正确";
                }
                entity.setProjectCompanyId(projectCompanyId);
            } catch (Exception e) {
                throw new EscstException("存在名称相同的公司！" + "【" + personImportVO.getCompanyName() + "】");
            }
            // 查出班组Id
            entity.setTeamName(personImportVO.getTeamName());
            try {
                String teamId = personMapper.getTeamId(entity);
                if (StringUtils.isBlank(teamId)) {
                    return "导入失败！请先录入班组" + "【" + personImportVO.getCompanyName() + "】" + "【" + personImportVO.getTeamName() + "】" + ",或者检查EXCEL表格中公司是否与班组对应，名称是否填写正确";
                }
                entity.setTeamId(teamId);
            } catch (Exception e) {
                throw new EscstException("分包公司中，存在名称相同的班组！" + "【" + personImportVO.getCompanyName() + "】" + "【" + personImportVO.getTeamName() + "】");
            }
            // 查出岗位工种ID
            try {
                String positionWorkTypeId = personMapper.getPositionWorkTypeId(entity);
                if (StringUtils.isBlank(positionWorkTypeId)) {
                    return "导入失败！请先录入岗位/工种" + "【" + personImportVO.getPositionWorkTypeName() + "】";
                }
                entity.setPositionWorkId(positionWorkTypeId);
            } catch (Exception e) {
                throw new EscstException("岗位/工种名称相同！" + "【" + personImportVO.getPositionWorkTypeName() + "】");
            }

            personList.add(entity);

        }
        //批量添加人员数据
        if (!CollectionUtils.isEmpty(personList)) {
            personMapper.batchInsertPerson(personList);
            personMapper.batchInsertPersonConstruction(personList);

        }

        //取得单例线程池
        SimpleThreadPool simpleThreadPool = SimpleThreadPool.getInstance();
        //创建一个新的线程实例
        ExcelPersonSyncThread thread = new ExcelPersonSyncThread(personList, constructionId, personExcelImportSyncService);
        //用线程池中的线程运行线程实例
        simpleThreadPool.executorService.execute(thread);
        return "导入成功！";
    }


    /**
     * 校验数据并存到数据库
     *
     * @param list
     * @return
     */
//	@Transactional
    private void checkImportDataFromExcel(List<PersonImportVO> list, String constructionId) {
        String result = "";
        int jobNo = this.getMaxJobNumber(constructionId);
        // 人员idList
        if (CollectionUtils.isEmpty(list)) {
            throw new EscstException("excel文件中没有数据!");
        }
        Set<String> mobileSet = new HashSet<String>();
        for (int i = 0; i < list.size(); i++) {
            PersonImportVO personImportVO = list.get(i);
            if (personImportVO.isNull()) {
                list.remove(i);
                i--;
                continue;
            }
            personImportVO.setConstructionId(constructionId);
            String name = personImportVO.getName(); // 姓名
            if (StringUtils.isBlank(name)) {
                result = "第" + (i + 1) + "行的[姓名]为空, 导入失败";
                throw new EscstException(result);
            }
            String mobile = personImportVO.getMobile(); // 手机号码
            if (StringUtils.isBlank(mobile)) {
                result = "第" + (i + 1) + "行的[手机号码]为空, 导入失败";
                throw new EscstException(result);
            }
            if (mobileSet.contains(mobile)) {
                String msg = "excel中的[手机号码](%s)重复, 导入失败";
                result = String.format(msg, mobile);
                throw new EscstException(result);
            } else {
                mobileSet.add(mobile);
            }
            String positionWorkTypeName = personImportVO.getPositionWorkTypeName(); // 岗位/工种
            Integer positionWorkType = null; // 岗位
            if (StringUtils.isBlank(positionWorkTypeName)) {
                result = "第" + (i + 1) + "行的[岗位/工种]为空, 导入失败";
                throw new EscstException(result);
            } else {
                positionWorkType = dictionaryService.getValueByTypeName("positionWorkType", positionWorkTypeName);
                if (positionWorkType == null) {
                    String msg = "第%d行的[岗位/工种](%s)数据不正确, 导入失败";
                    result = String.format(msg, i + 1, positionWorkTypeName);
                    throw new EscstException(result);
                }
                personImportVO.setPositionWorkTypeId(positionWorkType.toString());
            }
            String companyName = personImportVO.getCompanyName();//所属公司
            if (StringUtils.isBlank(companyName)) {
                result = "第" + (i + 1) + "行的[所属公司]为空, 导入失败";
                throw new EscstException(result);
            }
            String jobNumber = personImportVO.getJobNumber();
            if (StringUtils.isBlank(jobNumber)) {
                personImportVO.setJobNumber(String.valueOf(jobNo));
            }
            jobNo++;
        }
        personExcelImportService.execute(list);


        //取得单例线程池
    }

    /**
     * 批量新增工地人员数据
     *
     * @param
     * @param
     */
//	private void saveContructionPerson(String constructionId, List<PersonEntity> personEntityList) {
//		List<ConstructionPersonEntity> constructionPersonList = new ArrayList<ConstructionPersonEntity>();
//		try {
//			int jobNumber = getMaxJobNumber(constructionId);
//			for (PersonEntity personEntity : personEntityList) {
//				ConstructionPersonEntity constructionPerson = new ConstructionPersonEntity();
//				constructionPerson.setId(UuidUtils.getUuid());
//				constructionPerson.setConstructionId(constructionId);
//				constructionPerson.setPersonId(personEntity.getId());
//				constructionPerson.setJobNumber(jobNumber++);
//				constructionPerson.setStatus(PersonEntity.STATUS_EFFECT);
//				constructionPerson.setProjectCompanyId(personEntity.getProjectCompanyId());
//				constructionPerson.setTeamId(personEntity.getTeamId());
//				constructionPersonList.add(constructionPerson);
//			}
//			// 批量插入人员工地数据
//			personMapper.batchInsertConstructionPerson(constructionPersonList);
//		}
//		catch (Exception e) {
//			throw new EscstException("批量插入工地人员数据失败：" + e.getMessage(), e);
//		}
//	}
    public List<PersonEntity> queryAuthPerson(String userId) {
        List<PersonEntity> list = null;
        try {
            list = personMapper.selectAuthPerson(userId);
        } catch (Exception e) {
            throw new EscstException("查询人员信息异常：" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 查询工地上人员信息
     *
     * @param entity
     * @return
     */
    public PageVo queryPersonListByPage(PersonEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            Integer page = entity.getPage();
            Integer rowNum = entity.getRowNum();

            // 当前页号
            int pageNum = entity.getPage();

            // 每页显示记录数
            int pageSize = entity.getRowNum();

            // 每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;

            entity.setStartIndex(offset);

            List<PersonEntity> list = personMapper.selectPersonByConstructionId(entity);
            int count = personMapper.selectPersonCount(entity);
            // 相关数据封装到pageVo对象
            pageVo.setCurrentPage(page);
            pageVo.setPageSize(rowNum);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询人员信息异常：" + e.getMessage(), e);
        }
        return pageVo;
    }

    public List<PersonEntity> getPersonByConstructionId(PersonEntity entity) {
        List<PersonEntity> entityList = new ArrayList<PersonEntity>();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("constructionId", entity.getConstructionId());
            entityList = personMapper.queryPersonByConstructionId(map);
        } catch (Exception e) {
            throw new EscstException("查询人员信息失败" + e.getMessage(), e);
        }

        return entityList;
    }

    /**
     * @param personEntity
     * @desc 保存人员信息
     * @author niejing
     * @date 2017年7月5日 上午11:28:34
     */
    public void savePerson(PersonEntity personEntity) {
        try {
            personMapper.insert(personEntity);
        } catch (Exception e) {
            throw new EscstException("保存人员信息异常", e);
        }
    }

    /**
     * @param constructionPerson
     * @desc 保存人员工地信息
     * @author niejing
     * @date 2017年7月5日 上午11:28:21
     */
    public void saveContructionPerson(ConstructionPersonEntity constructionPerson) {
        List<ConstructionPersonEntity> constructionPersonList = new ArrayList<ConstructionPersonEntity>();
        try {
//            int jobNumber = getMaxJobNumber(constructionPerson.getConstructionId());
            constructionPerson.setId(UuidUtils.getUuid());
            if (StringUtils.isNotBlank(constructionPerson.getCardNumber())) {
                int jobNumber = Integer.parseInt(constructionPerson.getCardNumber());
                constructionPerson.setJobNumber(jobNumber);
            }
            constructionPerson.setStatus(1);
            constructionPersonList.add(constructionPerson);
            // 批量插入人员工地数据
            personMapper.batchInsertConstructionPerson(constructionPersonList);
        } catch (Exception e) {
            throw new EscstException("批量插入工地人员数据失败：" + e.getMessage(), e);
        }
    }

    /**
     * @param constructionId
     * @return
     * @desc 获取工地上专业分包和劳务分包的负责人
     * @author zhouwei
     * @date 2017年7月12日 下午4:25:29
     */
    public Map<String, List<TeamLeaderVO>> getCompanyTeamLeaderMap(String constructionId) {
        Map<String, List<TeamLeaderVO>> rst = new HashMap<String, List<TeamLeaderVO>>();
        List<TeamLeaderVO> list = personMapper.getTeamLeaderList(constructionId);
        if (list == null || list.size() == 0) {
            return rst;
        }
        for (TeamLeaderVO teamLeader : list) {
            String companyId = teamLeader.getCompanyId();
            if (rst.containsKey(companyId)) {
                List<TeamLeaderVO> teamLeaderList = rst.get(companyId);
                teamLeaderList.add(teamLeader);
            } else {
                List<TeamLeaderVO> teamLeaderList = new ArrayList<TeamLeaderVO>();
                teamLeaderList.add(teamLeader);
                rst.put(companyId, teamLeaderList);
            }
        }
        return rst;
    }

    /**
     * @param authVO
     * @return
     * @desc 获取有权限查看的工地工种数
     * @author zhouwei
     * @date 2017年8月15日 下午4:43:23
     */
    public PageVo queryAuthWorkTypePersonQtyPage(PageAuthVO authVO) {
        int count = personMapper.getAuthWorkTypePersonQtyCount(authVO);
        List<QtyVO> list = null;
        if (count > 0) {
            list = personMapper.queryAuthWorkTypePersonQty(authVO);
            for (int i = 0; i < list.size(); i++) {
                QtyVO qtyVO = list.get(i);
                if (StringUtils.isBlank(qtyVO.getId())) {
                    list.remove(i);
                    i--;
                    continue;
                }
            }
        }
        PageVo pageVo = new PageVo();
        pageVo.setCurrentPage(authVO.getPage());
        pageVo.setPageSize(authVO.getRowNum());
        pageVo.setTotalRecord(count);
        pageVo.setRows(list);
        return pageVo;
    }

    /**
     * @param authVO
     * @return
     * @desc 获取每一天的各个工种的数量
     * @author niejing
     * @date 2017年11月6日 下午1:49:05
     */
    public WorkTypeQtyVO query7DaysWorkTypeQty(WorkTypeQueryVO authVO) {
        WorkTypeQtyVO retVO = new WorkTypeQtyVO();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -6);
        Date dateDiff = calendar.getTime();
        authVO.setStartDate(sdf.format(dateDiff));
        authVO.setEndDate(sdf.format(new Date()));

        List<WorkTypeDateVO> list = personMapper.query7DaysWorkTypeQty(authVO);
        List<String> dayList = DateUtils.getDayList(authVO.getStartDate(), authVO.getEndDate());
        retVO.setDicList(getWorkTypeMapping());
        if (dayList.size() == list.size()) {
            retVO.setDataVo(list);
            return retVO;
        }

        // 如果有一天没有数据,则将该天统计数据补充为0
        Map<String, WorkTypeDateVO> map = new HashMap<String, WorkTypeDateVO>();
        for (WorkTypeDateVO vo : list) {
            map.put(vo.getDate(), vo);
        }
        List<WorkTypeDateVO> retList = new ArrayList<WorkTypeDateVO>();
        for (String day : dayList) {
            if (map.containsKey(day)) {
                retList.add(map.get(day));
            } else {
                WorkTypeDateVO qtyVO = new WorkTypeDateVO();
                qtyVO.setDate(day);
                qtyVO.setOne(BigDecimal.ZERO);
                qtyVO.setTwo(BigDecimal.ZERO);
                qtyVO.setThree(BigDecimal.ZERO);
                qtyVO.setFour(BigDecimal.ZERO);
                qtyVO.setFive(BigDecimal.ZERO);
                qtyVO.setSix(BigDecimal.ZERO);
                qtyVO.setSeven(BigDecimal.ZERO);
                qtyVO.setEight(BigDecimal.ZERO);
                qtyVO.setNine(BigDecimal.ZERO);
                qtyVO.setTen(BigDecimal.ZERO);
                qtyVO.setEleven(BigDecimal.ZERO);
                qtyVO.setTwelve(BigDecimal.ZERO);
                qtyVO.setThirteen(BigDecimal.ZERO);
                retList.add(qtyVO);
            }

        }
        retVO.setDataVo(retList);
        return retVO;
    }

    /**
     * @return
     * @desc 查找工种的对应关系用于页面显示
     * @author niejing
     * @date 2017年11月7日 上午9:38:27
     */
    private List<Map<String, Object>> getWorkTypeMapping() {
        List<DictionaryEntity> dicList = dictionaryService.queryByType("workType");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (DictionaryEntity entity : dicList) {
            Map<String, Object> map = new HashMap<String, Object>();
            switch (entity.getValue()) {
                case 1:
                    map.put("one", entity.getName());
                    mapList.add(map);
                    break;
                case 2:
                    map.put("two", entity.getName());
                    mapList.add(map);
                    break;
                case 3:
                    map.put("three", entity.getName());
                    mapList.add(map);
                    break;
                case 4:
                    map.put("four", entity.getName());
                    mapList.add(map);
                    break;
                case 5:
                    map.put("five", entity.getName());
                    mapList.add(map);
                    break;
                case 6:
                    map.put("six", entity.getName());
                    mapList.add(map);
                    break;
                case 7:
                    map.put("seven", entity.getName());
                    mapList.add(map);
                    break;
                case 8:
                    map.put("eight", entity.getName());
                    mapList.add(map);
                    break;
                case 9:
                    map.put("nine", entity.getName());
                    mapList.add(map);
                    break;
                case 10:
                    map.put("ten", entity.getName());
                    mapList.add(map);
                    break;
                case 11:
                    map.put("eleven", entity.getName());
                    mapList.add(map);
                    break;
                case 12:
                    map.put("twelve", entity.getName());
                    mapList.add(map);
                    break;
                case 13:
                    map.put("thirteen", entity.getName());
                    mapList.add(map);
                    break;
            }
        }
        return mapList;
    }

    /**
     * @param constructionId
     * @return
     * @desc 通过工地id获取人员信息
     * @author dwj
     */
    public List<Map<String, Object>> queryPersonList(String constructionId) {
        return personMapper.queryPersonList(constructionId);
    }


    /**
     * @param entity
     * @return
     * @desc 查询合作单位及负责人
     * @author zhouwei
     * @date 2017年7月12日 下午2:56:53
     */
    public List<CompanyDetailVO> queryDetail(ProjectCompanyEntity entity) {
        List<CompanyDetailVO> rst = new ArrayList<CompanyDetailVO>();
        // 获取工地所有的合作单位
        String constructionId = entity.getConstructionId();
        List<Map<String, Object>> projectCompanyList = projectCompanyService.listByConstructionId(constructionId, ContextUtils.getCurrentUserId());
        if (projectCompanyList == null || projectCompanyList.size() == 0) {
            return rst;
        }
        Map<String, List<TeamLeaderVO>> teamMap = getCompanyTeamLeaderMap(constructionId);

        for (Map<String, Object> map : projectCompanyList) {
            String companyId = (String) map.get("id");
            CompanyDetailVO detail = new CompanyDetailVO();
            List<TeamLeaderVO> data = teamMap.get(companyId);
            String name = (String) map.get("name");
            int type = (Integer) map.get("type");
            detail.setId(companyId);
            detail.setName(name);
            detail.setType(type);
            detail.setData(data);
            rst.add(detail);
        }
        return rst;
    }

    /**
     * @param bean
     * @return
     * @desc 查询要导出的人员数据
     * @author zhouwei
     * @date 2017年10月30日 上午11:14:36
     */
    public List<PersonExcelEntity> queryExportPersonList(PersonConditionBean bean) {
        List<PersonExcelEntity> rst = new ArrayList<PersonExcelEntity>();
        List<Map<String, Object>> list = personMapper.selectPersonList(bean);
        if (CollectionUtils.isEmpty(list)) {
            return rst;
        }
        for (Map<String, Object> map : list) {
            PersonExcelEntity entity = new PersonExcelEntity();
            entity.setName((String) map.get("name"));
            entity.setMobile((String) map.get("mobile"));
            entity.setCompanyName((String) map.get("projectCompanyName"));
            entity.setTeamName((String) map.get("teamName"));
            entity.setConstructionName((String) map.get("constructionName"));
            entity.setPositionWorkTypeName((String) map.get("positionWorkTypeName"));
            entity.setUserName((String) map.get("userName"));
            rst.add(entity);
        }
        return rst;
    }

    /**
     * @param bean
     * @return
     * @desc 查询用户信息
     * @author zhouwei
     * @date 2017年10月30日 上午11:31:39

    public List<Map<String, Object>> queryPersonList(PersonConditionBean bean, String userId) {
    bean.setUserId(userId);
    List<Map<String, Object>> list = personMapper.selectPersonList(bean);

    if (CollectionUtils.isEmpty(list)) {
    return list;
    }

    return list;
    }*/

    /**
     * @param personQueryVO
     * @return
     * @desc 查询工地人员列表
     * @author zhouwei
     * @date 2017年11月2日 下午9:16:41
     */
    public PageVo queryConstructionPersonList(PersonQueryVO personQueryVO) {
        PageVo pageVo = new PageVo();
        int totalQty = personMapper.selectConstructionPersonCount(personQueryVO);
        if (totalQty > 0) {
            int type = personQueryVO.getType();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            if (type == 2) {
                list = personMapper.selectPersonByWorkType(personQueryVO);
            } else if (type == 1) {
                list = personMapper.selectConstructionPersonList(personQueryVO);
            }
            if (!CollectionUtils.isEmpty(list)) {
                Map<String, TerritoryEntity> territoryMap = territoryService.getTerritoryMap();
                for (Map<String, Object> map : list) {
                    String areaId = (String) map.get("areaId");
                    if (territoryMap.containsKey(areaId)) {
                        TerritoryEntity territoryEntity = territoryMap.get(areaId);
                        map.put("areaName", territoryEntity.getName());
                    }
                }
            }
            pageVo.setRows(list);
        }
        pageVo.setCurrentPage(personQueryVO.getPage());
        pageVo.setPageSize(personQueryVO.getRowNum());
        pageVo.setTotalRecord(totalQty);
        return pageVo;
    }


    /**
     * @param constructionId
     * @return 查询施工单位人员
     * @desc
     * @author dwj
     * @date 2017/11/24 13:55
     */
    public List<Map<String, Object>> querypersonByConstructionId(String constructionId, String companyTypes) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            PersonQueryVO queryVO = new PersonQueryVO();
            queryVO.setCompanyTypes(companyTypes);
            queryVO.setConstructionId(constructionId);
            list = personMapper.selectPersonByWorkType(queryVO);
        } catch (Exception e) {
            throw new EscstException("查询施工单位人员失败" + e.getMessage(), e);
        }

        return list;
    }


    public PersonEntity getCurrent(String userId) {
        PersonEntity entity = new PersonEntity();
        try {
            PersonQueryVO queryVO = new PersonQueryVO();
            queryVO.setUserId(userId);
            List<Map<String, Object>> list = personMapper.selectPersonByWorkType(queryVO);
            Map<String, Object> dataMap = list.get(0);
            entity.setId((String) dataMap.get("personId"));
            entity.setUserId((String) dataMap.get("userId"));
            entity.setName((String) dataMap.get("name"));
            entity.setMobile((String) dataMap.get("mobile"));
            entity.setProjectCompanyName((String) dataMap.get("projectCompanyName"));
            entity.setProjectCompanyId((String) dataMap.get("projectCompanyId"));
            entity.setTeamName((String) dataMap.get("teamName"));
            entity.setTeamId((String) dataMap.get("teamId"));
        } catch (Exception e) {
            throw new EscstException("查询当前用户信息异常：" + e.getMessage(), e);
        }
        return entity;
    }

    /**
     * @param
     * @return
     * @desc 通过身份证阅读器获取人员信息
     * @author dwj
     * @date 2018/1/22 14:15
     */
    public PersonEntity getPersonByIdCard() {
        String url = IDCARD_READER;
        String result = HttpClientUtils.httpGetRequest(url);
        result = result.replace("\\", "/");

        if (StringUtils.isEmpty(result)) {
            return null;
        }
        JSONObject jsonObj = null;
        PersonEntity entity = new PersonEntity();
        try {
            jsonObj = JSONObject.parseObject(result);
        } catch (Exception e) {
            String msg = "json字符串(%s)解析时出现异常";
            throw new EscstException(msg);
        }
        JSONObject obj = JSONObject.parseObject(jsonObj.getString("Certificate"));
        entity.setName(obj.getString("Name"));
        if (obj.getString("Sex").equals("男")) {
            entity.setSex(1);
        } else if (obj.getString("Sex").equals("女")) {
            entity.setSex(2);
        }
        entity.setHeadFilePath(obj.getString("Base64Photo"));
        entity.setIdCard(obj.getString("IDNumber"));

        return entity;
    }

    /**
     * @param params
     * @return
     * @desc 得到工地考勤总人数
     * @author zhouwei
     * @date 2017年5月2日 下午4:11:04
     */
    public int getTotalAttendanceNum(Map<String, Object> params) {
        return personMapper.getTotalAttendanceNum(params);
    }

    /**
     * @param params
     * @return
     * @desc 得到现场人员数量
     * @author zhouwei
     * @date 2017年5月2日 下午9:18:13
     */
    public int getLocalePersonNum(Map<String, Object> params) {
        return personMapper.getLocalePersonNum(params);
    }

    /**
     * @param ids
     * @desc 新增用户
     * @author niejing
     * @date 2018年3月14日 下午2:57:53
     */
    public void generateUser(String[] ids) {
        try {
            List<UserEntity> list = new ArrayList<UserEntity>();
            for (int i = 0; i < ids.length; i++) {
                PersonEntity personEntity = this.personMapper.selectById(ids[i]);
                String userName = personEntity.getMobile();
                // 如果手机号为空，就用cardnumer作为user的用户名
                if (StringUtils.isBlank(userName)) {
                    userName = personEntity.getCardNumber();
                }
                if (StringUtils.isBlank(userName)) {
                	continue;
                }
                userName = StringUtils.deleteWhitespace(userName);
                // 在user表里检查用户名是否唯一
                UserEntity ifExistEntity = userService.checkUserName(userName);
                if (ifExistEntity != null) {
                    //如果用户表里的userId字段为空则将已存在的userId更新进去
                    if (StringUtils.isBlank(personEntity.getUserId())) {
                        personEntity.setUserId(ifExistEntity.getId());
                        personEntity.setUpdateBy(ContextUtils.getCurrentUserId());
                        personEntity.setUpdateTime(new Date());
                        personMapper.update(personEntity);
                    }
                    continue;
                }

                UserEntity currentUser = ContextUtils.getCurrentUser();
                UserEntity userEntity = new UserEntity();
                String userId = UuidUtils.getUuid();
                userEntity.setId(userId);
                userEntity.setOrgId(currentUser.getOrgId());
                userEntity.setUserName(userName);
                userEntity.setName(personEntity.getName());
                userEntity.setUserPassword(MD5Util.md5Encode(userName + DEFAULT_PASSWORD));
                userEntity.setStatus(VALID);
                userEntity.setSex(personEntity.getSex());
                userEntity.setMobile(personEntity.getMobile());
                userEntity.setIdCard(personEntity.getIdCard());
                userEntity.setCreateBy(currentUser.getId());
                userEntity.setCreateTime(new Date());
                list.add(userEntity);

                //更新person表的userId字段
                personEntity.setUserId(userId);
                personEntity.setUpdateBy(ContextUtils.getCurrentUserId());
                personEntity.setUpdateTime(new Date());
                personMapper.update(personEntity);
            }
            if (!CollectionUtils.isEmpty(list)) {
                userService.batchInsert(list);
            }
        } catch (Exception e) {
            throw new EscstException("新增用户异常", e);
        }
    }


    /**
     * @param projectCompanyId 分包公司ID
     * @return
     * @desc 根据分包公司获得班组列表
     * @author jincheng
     * @date 2018-6-4 10:55
     */
    public List<PersonEntity> listTeam(String projectCompanyId) {
        try {
            List<PersonEntity> list = personMapper.listTeam(projectCompanyId);
            return list;
        } catch (Exception e) {
            throw new EscstException("根据分包公司获得班组列表异常", e);
        }
    }
}
