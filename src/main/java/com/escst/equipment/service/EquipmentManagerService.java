package com.escst.equipment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escst.commons.vo.BaseAuthVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.PinyinUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.equipment.entity.EquipmentDisassemblyEntity;
import com.escst.equipment.entity.EquipmentInOutRecordEntity;
import com.escst.equipment.entity.EquipmentInspectionEntity;
import com.escst.equipment.entity.EquipmentMaintenanceEntity;
import com.escst.equipment.entity.EquipmentManagerEntity;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.mapper.EquipmentManagerMapper;
import com.escst.equipment.mapper.FaceMapper;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.equipment.vo.FaceQueryVO;
import com.escst.equipment.vo.FaceQueryVO;
import com.escst.equipment.vo.QueryVO;
import com.escst.equipment.vo.SimpleEquipmentVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.lifter.mapper.LifterMapper;
import com.escst.lifter.vo.LifterExcelVo;
import com.escst.lifter.vo.LifterRealtimeVO;
import com.escst.organization.entity.OrgEntity;
import com.escst.territory.entity.TerritoryEntity;
import com.escst.territory.service.TerritoryService;
import com.escst.towerCrane.mapper.TowerCraneMapper;
import com.escst.towerCrane.vo.TowerCraneExcelVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;

/**
 * @author dwj
 * @desc
 * @date 10:45 2017/3/13
 */
@Service
public class EquipmentManagerService {

    private static Logger logger = LoggerFactory.getLogger(EquipmentManagerService.class);
    //有效
    private static final int VALID = 1;
    //无效
    private static final int INVALID = 0;

    @Autowired
    private EquipmentManagerMapper equipmentManagerMapper;

    @Autowired
    private FileService fileService;
    
    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private TowerCraneMapper towerCraneMapper;

    @Autowired
    private LifterMapper lifterMapper;
    
    @Autowired
    private FaceMapper faceMapper;
    
    /***
     * 分页查询设备信息
     */
    public PageVo queryList(EquipmentManagerEntity equipmentManagerEntity) {
        PageVo pageVo = new PageVo();
        try {
            int count = equipmentManagerMapper.selectCount(equipmentManagerEntity);
            // 当前页号
            pageVo.setCurrentPage(equipmentManagerEntity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(equipmentManagerEntity.getRowNum());
            // 每页的第一条记录索引
            int startIndex = (equipmentManagerEntity.getPage() - 1) * (equipmentManagerEntity.getRowNum());
            equipmentManagerEntity.setStartIndex(startIndex);
            // 根据工地ID查询总页数

            List<Map<String, Object>> equipmentList = equipmentManagerMapper.queryList(equipmentManagerEntity);
            if (!CollectionUtils.isEmpty(equipmentList)) {
                pageVo.setRows(equipmentList);
            }
        } catch (Exception e) {
            logger.error("查询设备列表失败:" + e.getMessage(), e);
            throw new EscstException("查询设备信息列表失败");
        }
        return pageVo;
    }


    /***
     * 分页通过设备Id查询设备信息
     */
    public PageVo queryListById(EquipmentManagerEntity equipmentManagerEntity) {
        PageVo pageVo = new PageVo();
        int type = equipmentManagerEntity.getType();
        try {
            if (type == 2) {
                // 根据工地ID查询总页数
                int count = equipmentManagerMapper.selectCountByDisassembly(equipmentManagerEntity);
                // 当前页号
                pageVo.setCurrentPage(equipmentManagerEntity.getPage());
                // 总记录数
                pageVo.setTotalRecord(count);
                pageVo.setPageSize(equipmentManagerEntity.getRowNum());
                // 每页的第一条记录索引
                int startIndex = (equipmentManagerEntity.getPage() - 1) * (equipmentManagerEntity.getRowNum());
                equipmentManagerEntity.setStartIndex(startIndex);
                List<Map<String, Object>> equipmentList = equipmentManagerMapper.queryByequipemntId(equipmentManagerEntity);
                if (!CollectionUtils.isEmpty(equipmentList)) {
                    pageVo.setRows(equipmentList);
                }
            } else if (type == 3) {
                // 根据工地ID查询总页数
                int count = equipmentManagerMapper.selectCountByMaintenance(equipmentManagerEntity);
                // 当前页号
                pageVo.setCurrentPage(equipmentManagerEntity.getPage());
                // 总记录数
                pageVo.setTotalRecord(count);
                pageVo.setPageSize(equipmentManagerEntity.getRowNum());
                // 每页的第一条记录索引
                int startIndex = (equipmentManagerEntity.getPage() - 1) * (equipmentManagerEntity.getRowNum());
                equipmentManagerEntity.setStartIndex(startIndex);
                List<Map<String, Object>> equipmentList = equipmentManagerMapper.queryMaintenanceByequipemntId(equipmentManagerEntity);
                if (!CollectionUtils.isEmpty(equipmentList)) {
                    pageVo.setRows(equipmentList);
                }
            }

        } catch (Exception e) {
            logger.error("查询设备列表失败:" + e.getMessage(), e);
            throw new EscstException("查询设备信息列表失败");
        }
        return pageVo;
    }

    /**
     * 查询设备详细信息
     */
    public Map<String, Object> queryListDtail(String equipmentId) {
        Map<String, Object> map;
        try {
            map = equipmentManagerMapper.queryListDtail(equipmentId);
        } catch (Exception e) {
            throw new EscstException("查询设备详细信息失败");
        }
        return map;
    }

    /**
     * 分页查询设备进出场信息
     */
    public PageVo queryEquipmentInOutRecordList(EquipmentInOutRecordEntity equipmentInOutRecordEntity) {
        PageVo pageVo = new PageVo();
        try {
            int count = equipmentManagerMapper.selectEquipmentInOutRecordCount(equipmentInOutRecordEntity);
            // 当前页数
            pageVo.setCurrentPage(equipmentInOutRecordEntity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(equipmentInOutRecordEntity.getRowNum());
            // 每页第一条索引
            int startIndex = (equipmentInOutRecordEntity.getPage() - 1) * (equipmentInOutRecordEntity.getRowNum());
            equipmentInOutRecordEntity.setStartIndex(startIndex);
            List<Map<String, Object>> equipmentInOutRecordList = equipmentManagerMapper.queryEquipmentInOutRecordList(equipmentInOutRecordEntity);
            if (!CollectionUtils.isEmpty(equipmentInOutRecordList)) {
                for (Map<String, Object> map : equipmentInOutRecordList) {
                    String id = (String) map.get("id");
                    Long isAttach = (Long) map.get("isAttach");
                    List<FilePathVO> filePathList = null;
                    if (isAttach != null && isAttach == 1) {
                        // 通过id获取上传的附件图片的路径集合
                        filePathList = fileService.queryFilePath(id);
                    }
                    map.put("picList", filePathList);
                }
                pageVo.setRows(equipmentInOutRecordList);
            }
        } catch (Exception e) {
            logger.error("查询设备进出场失败" + e.getMessage(), e);
            throw new EscstException("查询设备进出场信息失败");
        }
        return pageVo;
    }

    /**
     * 查询设备进出场详细信息
     **/
    public EquipmentInOutRecordEntity queryEquipmentInOutRecordDtail(String equipmentId) {
        EquipmentInOutRecordEntity entity = new EquipmentInOutRecordEntity();
        try {
            entity = equipmentManagerMapper.queryEquipmentInOutRecordDtail(equipmentId);
            if (!StringUtils.isEmpty(entity)) {
                Integer isAttach = entity.getIsAttach();
                List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
                if (isAttach == 1) {
                    // 通过id获取上传的附件图片的路径集合
                    filePathVOs = fileService.queryFilePath(entity.getId());
                }
                entity.setPicList(filePathVOs);
            }
        } catch (Exception e) {
            throw new EscstException("查询设备进出场详细信息失败");
        }
        return entity;
    }

    /**
     * 分页查询设备维修与保养信息
     */
    public PageVo queryEquipmentMaintenanceList(EquipmentMaintenanceEntity equipmentMaintenanceEntity) {
        PageVo pageVo = new PageVo();
        try {
            int count = equipmentManagerMapper.selectEquipmentMaintenanceCount(equipmentMaintenanceEntity);
            // 当前页数
            pageVo.setCurrentPage(equipmentMaintenanceEntity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(equipmentMaintenanceEntity.getRowNum());
            // 每页第一条索引
            int startIndex = (equipmentMaintenanceEntity.getPage() - 1) * (equipmentMaintenanceEntity.getRowNum());
            equipmentMaintenanceEntity.setStartIndex(startIndex);
            List<Map<String, Object>> list = equipmentManagerMapper.queryEquipmentMaintenanceList(equipmentMaintenanceEntity);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    Long isAttach = (Long) map.get("isAttach");
                    List<FilePathVO> filePathList = null;
                    if (isAttach != null && isAttach == 1) {
                        // 通过id获取上传的附件图片的路径集合
                        filePathList = fileService.queryFilePath(id);
                    }
                    map.put("picList", filePathList);
                }
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("分页查询设备维修与保养失败");
        }
        return pageVo;
    }

    /**
     * 查看设备维修保养详细信息
     */
    public EquipmentMaintenanceEntity querytEquipmentMaintenanceDtail(String equipmentId) {
        EquipmentMaintenanceEntity entity = new EquipmentMaintenanceEntity();
        try {
            entity = equipmentManagerMapper.querytEquipmentMaintenanceDtail(equipmentId);
            if (!StringUtils.isEmpty(entity)) {
                Integer isAttach = entity.getIsAttach();
                List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
                if (isAttach == 1) {
                    // 通过id获取上传的附件图片的路径集合
                    filePathVOs = fileService.queryFilePath(entity.getId());
                }
                entity.setPicList(filePathVOs);
            }


        } catch (Exception e) {

            throw new EscstException("查询设备维修与保养详细信息失败");
        }
        return entity;
    }

    /**
     * 分页查询设备拆卸信息
     */
    public PageVo queryEquipmentDisassemblyList(EquipmentDisassemblyEntity equipmentDisassemblyEntity) {
        PageVo pageVo = new PageVo();
        try {
            int count = equipmentManagerMapper.selectEquipmentDisassemblyCount(equipmentDisassemblyEntity);
            // 当前页数
            pageVo.setCurrentPage(equipmentDisassemblyEntity.getPage());
            // 总页数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(equipmentDisassemblyEntity.getRowNum());
            // 每页第一条索引
            int startIndex = (equipmentDisassemblyEntity.getPage() - 1) * (equipmentDisassemblyEntity.getRowNum());
            equipmentDisassemblyEntity.setStartIndex(startIndex);
            List<Map<String, Object>> list = equipmentManagerMapper.queryEquipmentDisassemblyList(equipmentDisassemblyEntity);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    Long isAttach = (Long) map.get("isAttach");
                    List<FilePathVO> filePathList = null;
                    if (isAttach != null && isAttach == 1) {
                        // 通过id获取上传的附件图片的路径集合
                        filePathList = fileService.queryFilePath(id);
                    }
                    map.put("picList", filePathList);
                }
                pageVo.setRows(list);
            }

        } catch (Exception e) {
            throw new EscstException("分页查询设备拆卸信息失败");
        }
        return pageVo;
    }

    /**
     * 设备拆卸详细信息
     */
    public EquipmentDisassemblyEntity querytEquipmentDisassemblyDtail(String equipmentId) {
        EquipmentDisassemblyEntity entity = new EquipmentDisassemblyEntity();
        try {
            entity = equipmentManagerMapper.querytEquipmentDisassemblyDtail(equipmentId);
            if (!StringUtils.isEmpty(entity)) {
                Integer isAttach = entity.getIsAttach();
                List<FilePathVO> filePathVOs = new ArrayList<FilePathVO>();
                if (isAttach == 1) {
                    // 通过id获取上传的附件图片的路径集合
                    filePathVOs = fileService.queryFilePath(equipmentId);
                }
                entity.setPicList(filePathVOs);

            }
        } catch (Exception e) {
            throw new EscstException("查询设备拆卸详细信息失败");
        }
        return entity;
    }

    /**
     * 分页查询设备巡查信息
     */
    public PageVo queryEuipmentInspection(EquipmentInspectionEntity equipmentInspectionEntity) {
        PageVo pageVo = new PageVo();
        try {
            int count = equipmentManagerMapper.selectEquipmentInspection(equipmentInspectionEntity);
            // 当前页数
            pageVo.setCurrentPage(equipmentInspectionEntity.getPage());
            // 总页数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(equipmentInspectionEntity.getRowNum());
            // 每页第一条索引
            int startIndex = (equipmentInspectionEntity.getPage() - 1) * (equipmentInspectionEntity.getRowNum());
            List<Map<String, Object>> inspectionList = equipmentManagerMapper.queryEuipmentInspection(equipmentInspectionEntity);
            if (!CollectionUtils.isEmpty(inspectionList)) {
                for (Map<String, Object> map : inspectionList) {
                    String id = (String) map.get("id");
                    int isAttach = (Integer) map.get("isAttach");
                    List<String> filePathList = null;
                    if (isAttach == 1) {
                        filePathList = fileService.queryFilePathByBusinessId(id);
                    } else {
                        filePathList = new ArrayList<String>();
                    }
                    map.put("picList", filePathList);
                }
                pageVo.setRows(inspectionList);
            }
        } catch (Exception e) {
            throw new EscstException("分页查询设备信息失败");

        }
        return pageVo;
    }

    /**
     * 设备查询详细信息
     */
    public List<Map<String, Object>> queryInspectionDatil(String equipmentId) {
        List<Map<String, Object>> list;
        try {
            list = equipmentManagerMapper.queryEquipmentInspectionDatil(equipmentId);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    int attach = (Integer) map.get("isAttach");
                    List<String> filePathList = null;
                    if (attach == 1) {
                        filePathList = fileService.queryFilePathByBusinessId(id);
                    } else {
                        filePathList = new ArrayList<String>();
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("设备巡查详细信息失败");
        }
        return list;
    }

    /**
     * 添加设备进出场信息
     */
    @Transactional
    public void saveInOutRecord(EquipmentManagerEntity equipmentManagerEntity) {
        try {
            EquipmentInOutRecordEntity equipmentInOutRecordEntity = new EquipmentInOutRecordEntity();
            // 人员姓名首字母拼音获取
            if (!StringUtils.isEmpty(equipmentManagerEntity.getName())) {
                String code = PinyinUtils.getPinYinHeadChar(equipmentManagerEntity.getName());
                equipmentManagerEntity.setCode(code);
            } else {
                equipmentManagerEntity.setCode("");
            }
            String equipmentId = equipmentManagerEntity.getEquipmentId();
            MultipartFile[] files = equipmentManagerEntity.getFiles();
            // id为空执行新增操作，不为空执行修改操作
            if (StringUtils.isEmpty(equipmentId)) {
                equipmentManagerEntity.setId(UuidUtils.getUuid());
                equipmentManagerEntity.setUpdateTime(new Date());
                equipmentManagerEntity.setCreateTime(new Date());
                //设备默认有效
                equipmentManagerEntity.setStatus(VALID);
                equipmentInOutRecordEntity.setId(UuidUtils.getUuid());
                equipmentInOutRecordEntity.setConstructionId(equipmentManagerEntity.getConstructionId());
                equipmentInOutRecordEntity.setEquipmentId(equipmentManagerEntity.getId());
                equipmentInOutRecordEntity.setType(equipmentManagerEntity.getType());
                equipmentInOutRecordEntity.setPersonId(equipmentManagerEntity.getPersonId());
                equipmentInOutRecordEntity.setRemark(equipmentManagerEntity.getRemark());
                equipmentInOutRecordEntity.setCreateBy(equipmentManagerEntity.getCreateBy());
                equipmentInOutRecordEntity.setUpdateBy(equipmentManagerEntity.getUpdateBy());
                equipmentInOutRecordEntity.setCreateTime(new Date());
                equipmentInOutRecordEntity.setUpdateTime(new Date());
                if (files != null && files.length > 0) {
                    equipmentInOutRecordEntity.setIsAttach(1);
                    // 上传文件
                    fileService.uploadFile("equipment", equipmentInOutRecordEntity.getId(), files);

                }
                equipmentManagerMapper.insertRegister(equipmentManagerEntity);
                equipmentManagerMapper.insetEuipmentInOutRecord(equipmentInOutRecordEntity);

            } else {
                equipmentManagerMapper.updateEuipmentRegister(equipmentManagerEntity);
                equipmentInOutRecordEntity.setEquipmentId(equipmentId);
                equipmentInOutRecordEntity.setPersonId(equipmentManagerEntity.getPersonId());
                equipmentInOutRecordEntity.setUpdateTime(new Date());
                equipmentInOutRecordEntity.setRemark(equipmentManagerEntity.getRemark());
                equipmentManagerMapper.updateInOutRecord(equipmentInOutRecordEntity);
//                equipmentInOutRecordEntity.setId(UuidUtils.getUuid());
//                equipmentInOutRecordEntity.setConstructionId(equipmentManagerEntity.getConstructionId());
//                equipmentInOutRecordEntity.setEquipmentId(equipmentId);
//                equipmentInOutRecordEntity.setType(equipmentManagerEntity.getType());
//                equipmentInOutRecordEntity.setPersonId(equipmentManagerEntity.getPersonId());
//                equipmentInOutRecordEntity.setRemark(equipmentManagerEntity.getRemark());
//                equipmentInOutRecordEntity.setCreateBy(equipmentManagerEntity.getCreateBy());
//                equipmentInOutRecordEntity.setUpdateBy(equipmentManagerEntity.getUpdateBy());
//                equipmentInOutRecordEntity.setCreateTime(new Date());
//                equipmentInOutRecordEntity.setUpdateTime(new Date());
//                if (files != null && files.length > 0) {
//                    equipmentInOutRecordEntity.setIsAttach(1);
//                    //上传附件
//                    fileService.uploadFile("equipment", equipmentInOutRecordEntity.getId(), files);
//                }
//                equipmentManagerMapper.insetEuipmentInOutRecord(equipmentInOutRecordEntity);
            }
        } catch (Exception e) {
            logger.error("系统异常", e.getMessage(), e);
            throw new EscstException("新增或修改设备进出场信息失败");
        }
    }

    /**
     * 添加设备维修与保养信息
     */
    @Transactional
    public void saveMaintenance(EquipmentMaintenanceEntity equipmentMaintenanceEntity) {
        try {
            String id = equipmentMaintenanceEntity.getId();
            MultipartFile[] files = equipmentMaintenanceEntity.getFiles();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                equipmentMaintenanceEntity.setId(id);
                equipmentMaintenanceEntity.setCreateTime(new Date());
                equipmentMaintenanceEntity.setUpdateTime(new Date());
                if (files != null && files.length > 0) {
                    equipmentMaintenanceEntity.setIsAttach(1);
                    //上传附件
                    fileService.uploadFile("equipment", id, files);
                }
                equipmentManagerMapper.insetEquipmentMaintenance(equipmentMaintenanceEntity);
            }

        } catch (Exception e) {
            throw new EscstException("新增或修改设备进出场信息失败");
        }

    }

    /**
     * 添加设备拆卸信息
     */
    @Transactional
    public void saveDisassembly(EquipmentDisassemblyEntity equipmentDisassemblyEntity) {
        try {
            String id = equipmentDisassemblyEntity.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                MultipartFile[] files = equipmentDisassemblyEntity.getFiles();
                equipmentDisassemblyEntity.setId(id);
                equipmentDisassemblyEntity.setCreateTime(new Date());
                if (files != null && files.length > 0) {
                    equipmentDisassemblyEntity.setIsAttach(1);
                    //上传附件
                    fileService.uploadFile("equipment", id, files);
                }
                equipmentManagerMapper.insetEquipmentDisassembly(equipmentDisassemblyEntity);
            }
        } catch (Exception e) {

            throw new EscstException("新增或修改设备拆卸信息失败");
        }

    }

    /**
     * 添加设备巡查信息
     */
    @Transactional
    public void saveInspection(EquipmentInspectionEntity equipmentInspectionEntity) {
        try {
            String id = equipmentInspectionEntity.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                MultipartFile[] files = equipmentInspectionEntity.getFiles();
                equipmentInspectionEntity.setId(id);
                equipmentInspectionEntity.setCreateTime(new Date());
                equipmentInspectionEntity.setUpdateTime(new Date());
            }
        } catch (Exception e) {
            throw new EscstException("新增设备巡查失败");
        }
    }

    /**
     * 查询所有设备SN
     *
     * @return
     * @desc
     * @author niejing
     * @date 2017年4月22日 上午11:30:34
     */
    public List<String> querAllDeviceSN(String constrcutionId) {
        List<String> snList = new ArrayList<String>();
        try {
            QueryVO vo = new QueryVO();
            vo.setConstructionId(constrcutionId);
            snList = this.equipmentManagerMapper.selectAllDeviceSN(vo);
        } catch (Exception e) {
            throw new EscstException("查询设备SN异常", e);
        }
        return snList;
    }

    /**
     * 根据设备sn查询设备详细信息
     */
    public Map<String, Object> queryByNumber(String number) {
        Map<String, Object> map;
        try {
            map = equipmentManagerMapper.selectByNumber(number);
        } catch (Exception e) {
            throw new EscstException("查询设备详细信息失败");
        }
        return map;
    }

    /**
     * @param queryVO
     * @return
     * @desc 查询有权限查看的工地设备
     * @author zhouwei
     * @date 2017年8月16日 下午6:40:49
     */
    public PageVo queryAuthEquipmentList(QueryVO queryVO) {
        List<SimpleEquipmentVO> list = null;
        int totalQty = equipmentManagerMapper.getAuthEquipmentCount(queryVO);
        if (totalQty > 0) {
            list = equipmentManagerMapper.queryAuthEquipmentList(queryVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setCurrentPage(queryVO.getPage());
        pageVo.setPageSize(queryVO.getRowNum());
        pageVo.setTotalRecord(totalQty);
        pageVo.setRows(list);
        return pageVo;
    }
    
    /**
	 * @desc 查询有设备的工地
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 上午8:44:44
	 */
	public PageVo queryAuthConstructionList(QueryVO queryVO) {
		int totalQty = equipmentManagerMapper.getAuthConstructionCount(queryVO);
		List<SimpleConstructionVO> list = null;
        if (totalQty > 0) {
            list = equipmentManagerMapper.queryAuthConstructionList(queryVO);
            if (CollectionUtils.isNotEmpty(list)) {
            	Map<String, TerritoryEntity> territoryMap = territoryService.getTerritoryMap();
            	SimpleConstructionVO constructionVO = null;
            	for (int i = 0; i < list.size(); i++) {
            		constructionVO = list.get(i);
            		String areaId = constructionVO.getAreaId();
            		String cityId = constructionVO.getCityId();
            		if (territoryMap.containsKey(areaId)) {
            			constructionVO.setAreaName(territoryMap.get(areaId).getName());
            		}
            		if (territoryMap.containsKey(cityId)) {
            			constructionVO.setCityName(territoryMap.get(cityId).getName());
            		}
            	}
            }
        }
		PageVo pageVo = new PageVo();
        pageVo.setCurrentPage(queryVO.getPage());
        pageVo.setPageSize(queryVO.getRowNum());
        pageVo.setTotalRecord(totalQty);
        pageVo.setRows(list);
		return pageVo;
	}
	
	/**
	 * @desc 得到特种设备的基本信息
	 * @return 
	 * @author zhouwei
	 * @date 2017年9月21日 下午1:50:07
	 */
	public Map<String, SimpleEquipmentVO> getSimpleEquipmentMap() {
		Map<String, SimpleEquipmentVO> map = new HashMap<String, SimpleEquipmentVO>();
		List<SimpleEquipmentVO> list = equipmentManagerMapper.querySpecialEquipmentList();
		if (CollectionUtils.isNotEmpty(list)) {
			SimpleEquipmentVO vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				map.put(vo.getId(), vo);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @desc 根据工地Id查询该工地下所有的设备 
	 * @param constructionId
	 * @return 
	 * @author niejing
	 * @date 2018年2月27日 上午10:02:26
	 */
	public List<EquipmentManagerEntity> querybyConstructionId(String constructionId){
		return equipmentManagerMapper.querybyConstructionId(constructionId);
	}


	public Workbook exportExcel(AcquisitionDataQueryVO vo,int type){
        ExportParams params = new ExportParams();
        Workbook workbook = null;
        if(type == EquipmentTypeEnum.TOWERCRANE.getValue()){
            List<TowerCraneExcelVo> voList = new ArrayList<TowerCraneExcelVo>();
            List<TowercraneRealtimeVO> list = towerCraneMapper.queryRealtimeList(vo);
            if(CollectionUtils.isEmpty(list)){
                TowerCraneExcelVo towerCraneExcelVo = new TowerCraneExcelVo();
                towerCraneExcelVo.setAcquisitionTime("无数据");
                towerCraneExcelVo.setAngle(new BigDecimal("0.00"));
                towerCraneExcelVo.setExtent(new BigDecimal("0.00"));
                towerCraneExcelVo.setHeight(new BigDecimal("0.00"));
                towerCraneExcelVo.setObliquity(new BigDecimal("0.00"));
                towerCraneExcelVo.setPercent(new BigDecimal("0.00"));
                towerCraneExcelVo.setWeight(new BigDecimal("0.00"));
                towerCraneExcelVo.setWindSpeed(new BigDecimal("0.00"));
                voList.add(towerCraneExcelVo);
            }else{
                for(TowercraneRealtimeVO realtimeVO : list){
                    TowerCraneExcelVo towerCraneExcelVo = new TowerCraneExcelVo();
                    towerCraneExcelVo.setAcquisitionTime(realtimeVO.getTime());
                    towerCraneExcelVo.setAngle(realtimeVO.getAngle());
                    towerCraneExcelVo.setExtent(realtimeVO.getExtent());
                    towerCraneExcelVo.setHeight(realtimeVO.getHeight());
                    towerCraneExcelVo.setObliquity(realtimeVO.getObliquity());
                    towerCraneExcelVo.setPercent(realtimeVO.getPercent());
                    towerCraneExcelVo.setWeight(realtimeVO.getWeight());
                    towerCraneExcelVo.setWindSpeed(realtimeVO.getWindSpeed());
                    voList.add(towerCraneExcelVo);
                }
            }
             workbook = ExcelExportUtil.exportExcel(params, TowerCraneExcelVo.class, voList);
        }else if(type == EquipmentTypeEnum.LIFTER.getValue()){
            List<LifterExcelVo> voList = new ArrayList<LifterExcelVo>();
            List<LifterRealtimeVO> list = lifterMapper.queryRealtimeList(vo);
            if(CollectionUtils.isEmpty(list)){
                LifterExcelVo lifterExcelVo = new LifterExcelVo();
                lifterExcelVo.setTime("无数据");
                lifterExcelVo.setBackDoorLockState(0);
                lifterExcelVo.setDriverName("无数据");
                lifterExcelVo.setDriverNo("无数据");
                lifterExcelVo.setFloorNo(0);
                lifterExcelVo.setFrontDoorLockState(0);
                lifterExcelVo.setHeight(new BigDecimal("0.00"));
                lifterExcelVo.setHighLimitState(0);
                lifterExcelVo.setLowLimitState(0);
                lifterExcelVo.setObliquity(new BigDecimal("0.00"));
                lifterExcelVo.setPeopleNum(0);
                lifterExcelVo.setSpeed(new BigDecimal("0.00"));
                lifterExcelVo.setWeight(new BigDecimal("0.00"));
                voList.add(lifterExcelVo);

            }else {
                for(LifterRealtimeVO lifterRealtimeVO : list){
                    LifterExcelVo lifterExcelVo = new LifterExcelVo();
                    lifterExcelVo.setTime(lifterRealtimeVO.getTime());
                    lifterExcelVo.setBackDoorLockState(lifterRealtimeVO.getBackDoorLockState());
                    lifterExcelVo.setDriverName(lifterRealtimeVO.getDriverName());
                    lifterExcelVo.setDriverNo(lifterRealtimeVO.getDriverNo());
                    lifterExcelVo.setFloorNo(lifterRealtimeVO.getFloorNo());
                    lifterExcelVo.setFrontDoorLockState(lifterRealtimeVO.getFrontDoorLockState());
                    lifterExcelVo.setHeight(lifterRealtimeVO.getHeight());
                    lifterExcelVo.setHighLimitState(lifterRealtimeVO.getHighLimitState());
                    lifterExcelVo.setLowLimitState(lifterRealtimeVO.getLowLimitState());
                    lifterExcelVo.setObliquity(lifterRealtimeVO.getObliquity());
                    lifterExcelVo.setPeopleNum(lifterRealtimeVO.getPeopleNum());
                    lifterExcelVo.setSpeed(lifterRealtimeVO.getSpeed());
                    lifterExcelVo.setWeight(lifterRealtimeVO.getWeight());
                    voList.add(lifterExcelVo);
                }

            }
            workbook = ExcelExportUtil.exportExcel(params, LifterExcelVo.class, voList);
        }


        return workbook;
    }
	
	/**
	 * 
	 * @desc 根据设备ID查询人脸信息 
	 * @param
	 * @return 
	 * @author niejing
	 * @date 2018年4月10日 下午2:01:56
	 */
	public PageVo queryByEquipmentId(FaceQueryVO entity) {
		 PageVo pageVo = new PageVo();
	        try {
	            int count = faceMapper.selectCount(entity.getId());
	            // 当前页
	            pageVo.setCurrentPage(entity.getPage());
	            // 总记录数
	            pageVo.setTotalRecord(count);
	            pageVo.setPageSize(entity.getRowNum());
	            // 每页第一条记录的索引
	            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
	            entity.setStartIndex(startIndex);
	            List<Map<String, Object>> list = faceMapper.selectByEquipmentId(entity.getId());
	            if (!CollectionUtils.isEmpty(list)) {
	                pageVo.setRows(list);
	            }
	        } catch (Exception e) {
	            throw new EscstException("根据ID查询人脸列表异常:" + e.getMessage(), e);
	        }
	        return pageVo;
	}
}
