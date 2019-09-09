package com.escst.construction.service;

import java.math.BigDecimal;
import java.util.*;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.construction.vo.ConstructionScheduleVo;
import com.escst.organization.mapper.OrgMapper;

import org.apache.commons.collections.MapUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.QtyVO;
import com.escst.construction.entity.ConstructionEntity;
import com.escst.construction.entity.ConstructionLicenseEntity;
import com.escst.construction.mapper.ConstructionLicenseMapper;
import com.escst.construction.mapper.ConstructionMapper;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.file.service.FileService;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;
import com.escst.redis.annotation.RedisCache;
import com.escst.territory.entity.TerritoryEntity;
import com.escst.territory.service.TerritoryService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 18:07
 */
@Service
public class ConstructionService {

    @Autowired
    private ConstructionMapper constructionMapper;

    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private ConstructionLicenseMapper constructionLicenseMapper;

    @Autowired
    private OrgService orgService;

    @Autowired
    private FileService fileService;

    @Autowired
    private OrgMapper orgMapper;

    public PageVo queryConstructionInfo(ConstructionEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            // 根据工地id查询人员总记录数
            int count = constructionMapper.selectConstructionCount(entity);
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);

            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<Map<String, Object>> list = constructionMapper.selectConstructionList(entity);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("查询人员列表信息失败！", e);
        }
        return pageVo;
    }

    public Map<String, Object> queryCount(String areaId) {
        Map<String, Object> map = new HashMap<String, Object>();
        ConstructionEntity entity = new ConstructionEntity();
        entity.setStatus(1);
        entity.setBelongArea(areaId);
        int count = constructionMapper.selectCount(entity);
        entity.setIsOnline(1);
        int isonlineCount = constructionMapper.selectCount(entity);
        map.put("count", count);
        map.put("isonlineCount", isonlineCount);
        return map;
    }

    /**
     * 查询该区下面的所有工地信息
     *
     * @param areaId
     * @return
     */
    public List<ConstructionEntity> queryConstructionList(String areaId) {
        ConstructionEntity entity = new ConstructionEntity();
        entity.setStatus(1);
        entity.setBelongArea(areaId);
        List<ConstructionEntity> list = constructionMapper.selectList(entity);
        return list;
    }

    /**
     * 根据工地id查询工地信息
     *
     * @param id
     * @return
     */
    @RedisCache
    public Map<String, Object> queryConstructionById(String id) {
        Map<String, Object> map = null;
        try {
            map = constructionMapper.selectByConstructionId(id);
        } catch (EscstException e) {
            throw new EscstException("查询工地信息出现异常:" + e.getMessage(), e);
        }
        return map;
    }

    /**
     * @return
     * @desc 查询工地树
     * @author niejing
     * @date 2017年4月26日 上午10:06:17
     */
    @RedisCache
    public List<TreeEntity> queryConstructionTree() {
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        try {
            //查询工地所有的城市
            List<Map<String, Object>> cityList = constructionMapper.selectConstructionCity();

            for (Map<String, Object> map : cityList) {
                String name = (String) map.get("name");
                String belongCity = (String) map.get("belongCity");
                TreeEntity tree1 = new TreeEntity();
                tree1.setId(belongCity);
                tree1.setpId("1");
                tree1.setName(name);
                tree1.setOpen(false);
                tree1.setNocheck(false);
                list.add(tree1);
                //根据城市ID查询所有的区域信息
                List<Map<String, Object>> areaList = constructionMapper.selectAreaByCityCode(belongCity);
                for (Map<String, Object> areamap : areaList) {
                    String areaName = (String) areamap.get("name");
                    String belongArea = (String) areamap.get("belongArea");
                    String parentId = (String) areamap.get("parentId");
                    TreeEntity tree2 = new TreeEntity();
                    tree2.setId(belongArea);
                    tree2.setpId(parentId);
                    tree2.setName(areaName);
                    tree2.setOpen(true);
                    tree2.setNocheck(false);
                    list.add(tree2);
                    //根据区域信息查询该区域下的工地
                    List<ConstructionEntity> constList = constructionMapper.selectConstByAreaCode(belongArea);
                    for (ConstructionEntity entity : constList) {
                        TreeEntity tree3 = new TreeEntity();
                        tree3.setId(entity.getId());
                        tree3.setpId(entity.getBelongArea());
                        tree3.setName(entity.getName());
                        tree3.setOpen(true);
                        list.add(tree3);
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("查询工地树异常：" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * @param userId
     * @return
     * @desc 获取用户有权限访问的工地
     * @author zhouwei
     * @date 2017年8月2日 上午9:55:35
     */
//	@RedisCache
    public List<SimpleConstructionVO> queryAuthConstructionByUserId(String userId) {
        List<SimpleConstructionVO> list = constructionMapper.selectAuthConstructionByUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            SimpleConstructionVO vo = list.get(i);
            String iconId = vo.getIconId();
            if (StringUtils.isNotBlank(iconId)) {
//                String iconPath = fileService.getFilePath(iconId);
                String iconPath = fileService.queryFilePathById(iconId);
                vo.setIconPath(iconPath);
            }
        }
        return list;
    }

    @Transactional
    public void addConstruction(ConstructionLicenseEntity licenseEntity) {
        try {
            String id = licenseEntity.getConstructionId();
            if (StringUtils.isBlank(id)) {
                //新增施工许可证
                licenseEntity.setId(UuidUtils.getUuid());
                licenseEntity.setCreateTime(new Date());
                constructionLicenseMapper.insert(licenseEntity);
                MultipartFile file = licenseEntity.getFile();
                //新增工程信息
                ConstructionEntity entity = new ConstructionEntity();
                entity.setId(UuidUtils.getUuid());
                entity.setConstructLicenseId(licenseEntity.getId());
                entity.setConstructLicenseNo(licenseEntity.getConstructLicenseNo());
                entity.setName(licenseEntity.getProjectName());
                entity.setBelongCity(licenseEntity.getCityId());
                entity.setBelongArea(licenseEntity.getAreaId());
                entity.setLicenseDate(licenseEntity.getLicenseDate());
                entity.setDevelopment(licenseEntity.getDevelopment());
                entity.setBuilding(licenseEntity.getBuilding());
                entity.setSupervision(licenseEntity.getSupervision());
                entity.setProjectManager(licenseEntity.getProjectManager());
                entity.setContractStartDate(licenseEntity.getContractStartDate());
                entity.setContractEndDate(licenseEntity.getContractEndDate());
                entity.setPlanContractStartDate(licenseEntity.getPlanContractStartDate());
                entity.setPlanContractEndDate(licenseEntity.getPlanContractEndDate());
                entity.setLng(licenseEntity.getLng());
                entity.setLat(licenseEntity.getLat());
                entity.setIsOnline(1);
                entity.setStatus(1);
                if (file != null) {
                    //上传附件
                    String fileId = fileService.uploadFile("map", file);
                    entity.setIconId(fileId);
                }
                entity.setCreateTime(new Date());
                constructionMapper.insert(entity);
                //查询该区域下所有的工地对应的组织机构
                List<String> parentIds = orgService.queryParentIdByArea(licenseEntity.getAreaId());
                for (String parentId : parentIds) {
                	String sysCode = getNextSysCode(parentId);
                	
                    OrgEntity orgEntity = new OrgEntity();
                    orgEntity.setId(UuidUtils.getUuid());
                    orgEntity.setParentId(parentId);
                    orgEntity.setName(licenseEntity.getProjectName());
                    orgEntity.setConstructionId(entity.getId());
                    orgEntity.setCreateTime(new Date());
                    orgEntity.setSysCode(sysCode);
                    orgEntity.setStatus(1);
                    orgEntity.setLevel(3);
//                    orgService.addOrg(orgEntity);
                    orgMapper.insert(orgEntity);
                }
            } else {
                licenseEntity.setUpdateTime(new Date());
                constructionLicenseMapper.update(licenseEntity);
                ConstructionEntity entity = new ConstructionEntity();
                entity.setName(licenseEntity.getProjectName());
                entity.setBelongCity(licenseEntity.getCityId());
                entity.setBelongArea(licenseEntity.getAreaId());
                entity.setConstructLicenseNo(licenseEntity.getConstructLicenseNo());
                entity.setDevelopment(licenseEntity.getDevelopment());
                entity.setBuilding(licenseEntity.getBuilding());
                entity.setSupervision(licenseEntity.getSupervision());
                entity.setProjectManager(licenseEntity.getProjectManager());
                entity.setLicenseDate(licenseEntity.getLicenseDate());
                entity.setContractStartDate(licenseEntity.getContractStartDate());
                entity.setContractEndDate(licenseEntity.getContractEndDate());
                entity.setPlanContractStartDate(licenseEntity.getPlanContractStartDate());
                entity.setPlanContractEndDate(licenseEntity.getPlanContractEndDate());
                entity.setLng(licenseEntity.getLng());
                entity.setLat(licenseEntity.getLat());
                entity.setId(id);
                if (licenseEntity.getFile() != null) {
                    //上传附件
                    String fileId = fileService.uploadFile("map", licenseEntity.getFile());
                    entity.setIconId(fileId);
                }
                entity.setUpdateTime(new Date());
                constructionMapper.update(entity);
            }

        } catch (Exception e) {
            throw new EscstException("新增或者修改工地异常：" + e.getMessage(), e);
        }
    }

    /**
     * @param id
     * @return
     * @desc 根据工地id查询
     * @author niejing
     * @date 2017年8月10日 下午4:43:16
     */
    public ConstructionLicenseEntity queryConstructionLicenseById(String id) {
        ConstructionLicenseEntity entity = null;
        try {
            entity = constructionLicenseMapper.selectConstructionLicenseById(id);
            String iconId = entity.getIconId();
            String filePath = null;
            if (StringUtils.isNotBlank(iconId)) {
                filePath = fileService.queryFilePathByFileId(iconId);
            }
            entity.setPicList(filePath);
        } catch (Exception e) {
            throw new EscstException("查询工地详细信息异常：" + e.getMessage(), e);
        }

        return entity;
    }

    /**
     * @param userId
     * @return
     * @desc 获取用户有权限看到的工地树形结构
     * @author zhouwei
     * @date 2017年8月15日 下午3:23:32
     */
    @RedisCache
    public List<TreeEntity> queryAuthConstructionTree(String userId) {
        List<TreeEntity> rst = new ArrayList<TreeEntity>();
        ConstructionService service = (ConstructionService) AopContext.currentProxy();
        List<SimpleConstructionVO> list = service.queryAuthConstructionByUserId(userId);
        Set<String> cityAreaIdSet = new HashSet<String>();
        for (SimpleConstructionVO construction : list) {
            String cityId = construction.getCityId();
            // 如果市节点没有,则增加市节点
            if (!cityAreaIdSet.contains(cityId)) {
                TreeEntity treeEntity = new TreeEntity();
                treeEntity.setId(cityId);
                treeEntity.setpId("1");
                TerritoryEntity territory = territoryService.selectById(cityId);
                treeEntity.setName(territory.getName());

                treeEntity.setOpen(true);
                cityAreaIdSet.add(cityId);
                rst.add(treeEntity);
            }
            // 如果区节点没有,则增加区节点
            String areaId = construction.getAreaId();
            if (!cityAreaIdSet.contains(areaId)) {
                TreeEntity treeEntity = new TreeEntity();
                treeEntity.setId(areaId);
                treeEntity.setpId(cityId);
                treeEntity.setName(construction.getAreaName());
                treeEntity.setOpen(false);
                cityAreaIdSet.add(areaId);
                rst.add(treeEntity);
            }

            // 工地节点
            TreeEntity constructionTree = new TreeEntity();
            String id = construction.getId();
            constructionTree.setId(id);
            constructionTree.setpId(areaId);
            constructionTree.setName(construction.getName());
            constructionTree.setOpen(false);
            rst.add(constructionTree);
        }
        return rst;
    }

    /**
     * @param userId
     * @return
     * @desc 查询有权限查看的工地在各区的数量
     * @author zhouwei
     * @date 2017年8月25日 下午2:20:14
     */
    public List<QtyVO> queryAuthAreaConstructionQty(String userId) {
        return constructionMapper.selectAuthAreaConstructionQty(userId);
    }

    /**
     * @return
     * @desc 获取用户有权限访问的工地
     * @author niejing
     * @date 2017年8月29日 下午2:24:02
     */
    public PageVo queryVisibleConstruction(SimpleConstructionVO entity) {
        PageVo pageVo = new PageVo();
        try {
            String userId = ContextUtils.getCurrentUserId();
            int count = constructionMapper.queryVisibleConstructionCount(userId);
            // 当前页
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);

            List<SimpleConstructionVO> list = constructionMapper.queryVisibleConstruction(userId);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询可见工地出现异常:" + e.getMessage(), e);
        }
        return pageVo;
    }


    /**
     * @param userId
     * @return
     * @desc 获取用户下工地的进度，剩余天数
     * @author dwj
     * @date 2018/2/23 9:34
     */
    public List<ConstructionScheduleVo> getConstructionSchedule(String userId) {
        List<ConstructionScheduleVo> vos = new ArrayList<ConstructionScheduleVo>();
        try {
            List<SimpleConstructionVO> list = constructionMapper.selectAuthConstructionByUserId(userId);
            for (SimpleConstructionVO vo : list) {
                ConstructionScheduleVo constructionScheduleVo = new ConstructionScheduleVo();
                String startDate = vo.getContractStartDate();
                String endDate = vo.getContractEndDate();
                if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
                    continue;
                }
                String name = vo.getName();
                Date contractStartDate = DateUtils.parse(startDate, DateUtils.TO_DATE);
                Date contractEndDate = DateUtils.parse(endDate, DateUtils.TO_DATE);
                int totalDays = DateUtils.daysBetween(contractStartDate, contractEndDate);
                int currentDays = DateUtils.daysBetween(contractStartDate, new Date());
                // 项目进度
                String percent = DateUtils.getPercent(currentDays, totalDays);
                BigDecimal dataA = new BigDecimal(percent);
                BigDecimal dataB = new BigDecimal("100.00");
                if (dataA.compareTo(dataB) == 1) {
                    constructionScheduleVo.setPercent("100%");
                } else {
                    constructionScheduleVo.setPercent(percent + "%");
                }
                //剩余天数
                int dayesRemain = totalDays - currentDays;
                if (dayesRemain >= 0) {
                    constructionScheduleVo.setDaysRemain(totalDays - currentDays);
                } else {
                    constructionScheduleVo.setDaysRemain(0);
                }
                constructionScheduleVo.setName(name);
                constructionScheduleVo.setStartDate(startDate);
                constructionScheduleVo.setEndDate(endDate);
                vos.add(constructionScheduleVo);
            }
        } catch (Exception e) {
            throw new EscstException("获取用户下的项目进度异常",e);
        }
        return vos;
    }

    /**
     * @param constructionId
     * @return
     * @desc 通过工地ID查询工地的进度，剩余天数
     * @author caozx
     * @date 2018/2/24 17:00
     */
    public SimpleConstructionVO queryScheduleByConstructionId(String constructionId) {
        SimpleConstructionVO vo = constructionMapper.queryById(constructionId);
        String startDate = vo.getContractStartDate();
        String endDate = vo.getContractEndDate();
        if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
            return vo;
        }
        //计算项目进度，设置到工地信息中
        ConstructionScheduleVo constructionScheduleVo = new ConstructionScheduleVo();
        String name = vo.getName();
        Date contractStartDate = DateUtils.parse(startDate, DateUtils.TO_DATE);
        Date contractEndDate = DateUtils.parse(endDate, DateUtils.TO_DATE);
        int totalDays = DateUtils.daysBetween(contractStartDate, contractEndDate);
        int currentDays = DateUtils.daysBetween(contractStartDate, new Date());
        if (currentDays > totalDays) {
            constructionScheduleVo.setPercent("100%");
        } else {
            // 项目进度
            String percent = DateUtils.getPercent(currentDays, totalDays);
            //精确表示
            BigDecimal dataA = new BigDecimal(percent);
            BigDecimal dataB = new BigDecimal("100.00");
            if (dataA.compareTo(dataB) == 1) {
                constructionScheduleVo.setPercent("100%");
            } else {
                constructionScheduleVo.setPercent(DateUtils.getPercent(currentDays, totalDays) + "%");
            }
        }
        //剩余天数
        int dayesRemain = totalDays - currentDays;
        if (dayesRemain >= 0) {
            constructionScheduleVo.setDaysRemain(totalDays - currentDays);
        } else {
            constructionScheduleVo.setDaysRemain(0);
        }
        constructionScheduleVo.setName(name);
        constructionScheduleVo.setStartDate(startDate);
        constructionScheduleVo.setEndDate(endDate);

        vo.setConstructionScheduleVo(constructionScheduleVo);
        return vo;
    }

    public Map<String, Object> queryConstructionInfoByUserId(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = constructionMapper.queryByUserId(id);
        } catch (Exception e) {
            throw new EscstException("查询当前用户所在公司信息异常", e);
        }
        return map;
    }

    /**
     * 通过工地ID查询平面图路径
     *
     * @param id
     * @return
     */
    public String queryFloorPlanById(String id) {
        SimpleConstructionVO vo = constructionMapper.queryById(id);
        String floorPlanId = vo.getFloorPlanId();
        if (StringUtils.isBlank(floorPlanId)) {
            return "";
        }
        String path = fileService.queryFilePathById(floorPlanId);
        return path;
    }
    
    /**
     * @param parentId
     * @return
     * @desc 得到下一个子节点的编码
     * @author zhouwei
     * @date 2017年8月1日 下午2:47:13
     */
    public String getNextSysCode(String parentId) {
        Map<String, Object> map = getSysCodeAndChildrenNum(parentId);
        if (MapUtils.isEmpty(map)) {
            throw new EscstException("父节点[" + parentId + "]不存在");
        }
        String sysCode = (String) map.get("sys_code");
        Long num = (Long) map.get("num");
        String[] strs = sysCode.split("_");
        int strLen = strs.length > 2 ? 3 : strs.length + 1;// 树形结构的第一级用1个字符,第二级用2个字符,三级或以上的用3个字符
        String index = convertString(num.intValue() + 1, strLen);
        return sysCode + "_" + index;
    }
    
    /**
     * @param parentId
     * @return
     * @desc 根据父节点ID得到父节点编码以及子节点数量
     * @author zhouwei
     * @date 2017年8月1日 下午3:11:05
     */
    public Map<String, Object> getSysCodeAndChildrenNum(String parentId) {
        return orgMapper.getSysCodeAndChildrenNum(parentId);
    }
    
    /**
     * @param val
     * @param stringLength
     * @return
     * @desc 将数字转换为指定长度的字符串, 如果数字长度不够, 则在前面用0补齐
     * @author zhouwei
     * @date 2017年8月1日 下午2:41:28
     */
    private String convertString(int val, int stringLength) {
        String str = String.valueOf(val);
        if (str.length() >= stringLength) {
            return str;
        }
        int len = stringLength - str.length();
        for (int i = 0; i < len; i++) {
            str = "0" + str;
        }
        return str;
    }
}
