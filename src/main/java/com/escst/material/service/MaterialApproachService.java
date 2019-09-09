package com.escst.material.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.Base64Util;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.PageVo;
import com.escst.file.mapper.FileMapper;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialApproachEntity;
import com.escst.material.mapper.MaterialApproachMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author niejing
 * @desc 材料进场服务类
 * @date 2017年8月21日 下午5:02:02
 */
@Service
@Transactional
public class MaterialApproachService {

    @Autowired
    private MaterialApproachMapper mapper;

    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;

    public static final String CL_ORDER_NO = "CL-0001";

    /**
     * 查询材料进场列表
     *
     * @param entity 参数
     * @return 返回值
     */
    public PageVo queryMaterialApproachList(MaterialBean entity) {
        PageVo pageVo = new PageVo();
        try {
            // 查询材料进场总记录数
            int count = mapper.selectApproachCount(entity);
            // 当前页
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(entity.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            // 查询数据
            List<Map<String, Object>> list = mapper.selectMaterialApproachList(entity);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }
        } catch (Exception e) {
            throw new EscstException("查询材料进场数据异常：" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 获取可用的材料列表
     *
     * @return
     */
    public List<Map<String, Object>> queryAvailableMApproachList(String userId) {
        List<Map<String, Object>> list = null;
        try {
            list = mapper.selectAvailableMApproachList(userId);
        } catch (Exception e) {
            throw new EscstException("查询材料进场数据异常：" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 材料进场
     *
     * @param materialEntity 参数
     */
    public void addMaterialApproachInfo(MaterialApproachEntity materialEntity) {
        try {
            MultipartFile[] files = materialEntity.getFiles();
            String id = materialEntity.getId();
            if (StringUtils.isBlank(id)) {
                materialEntity.setId(UuidUtils.getUuid());
                // 新增
                if (files != null && files.length > 0) {
                    materialEntity.setIsAttach(1);
                    String busId = materialEntity.getId();
//				saveFiles(files, busId);
                    fileService.uploadFile("material", busId, files);
                }
                materialEntity.setAvailableQuantity(materialEntity.getQuantity());
                materialEntity.setCreateBy(ContextUtils.getCurrentUserId());
                materialEntity.setCreateTime(new Date());
                materialEntity.setOrderNo(getLatestOrderNo(materialEntity.getConstructionId()));
                mapper.insertMaterialApproachInfo(materialEntity);
            } else {
                // 修改
                if (files != null && files.length > 0) {
                    materialEntity.setIsAttach(1);
                    String busId = materialEntity.getId();
//				saveFiles(files, busId);
                    fileService.uploadFile("material", busId, files);
                }

                if (StringUtils.isNotBlank(materialEntity.getFilePathList())) {
                    // 删除地磅进场时的图片
                    List<String> list = Arrays.asList(materialEntity.getFilePathList().split(","));
                    fileMapper.batchDelete(list);
                }
                materialEntity.setUpdateBy(ContextUtils.getCurrentUserId());
                materialEntity.setCreateBy(ContextUtils.getCurrentUserId());
                materialEntity.setUpdateTime(new Date());
                mapper.update(materialEntity);
            }

//            if (CollectionUtils.isNotEmpty(materialEntity.getBase64ImageList())) {
//                materialEntity.setIsAttach(1);
//                List<String> base64ImageList = materialEntity.getBase64ImageList();
//                List<FileEntity> fileList = new ArrayList<FileEntity>();
//                for (String base64Image : base64ImageList) {
//                    // 获得图片的保存路径
//                    String uploadPath = fileService.getUploadRelativePath("material");
//                    String tempUploadPath = ResourceUtil.getFileSavePath() + uploadPath;
//                    // 没有文件夹就创建
//                    Base64Util.createFolder(tempUploadPath);
//                    // 保存图片
//                    String fileId = UuidUtils.getUuid();
//                    //访问路径
//                    String viewPath = uploadPath + "/" + fileId + ".jpg";
//                    uploadPath = tempUploadPath + "/" + fileId + ".jpg";
//                    Base64Util.decoderBase64File(base64Image, uploadPath);
//                    // 封装文件记录数据
//                    FileEntity fileEntity = new FileEntity();
//                    fileEntity.setBusinessId(id);
//                    fileEntity.setId(fileId);
//                    fileEntity.setName(fileId + ".jpg");
//                    fileEntity.setPath(viewPath);
//                    fileEntity.setType("jpg");
//                    fileEntity.setCreateTime(DateUtils.getCurrentDate());
//                    fileList.add(fileEntity);
//                }
//                // 保存文件记录
//                fileMapper.batchInsert(fileList);
//            }


            // 修改地磅数据记录
//            mapper.updateWeighbridge(materialEntity.getWeighbridgeId());
        } catch (Exception e) {
            throw new EscstException("更新材料进出场信息失败！", e);
        }
    }

    /**
     * 材料进场详情
     *
     * @param entity 参数
     * @return 返回值
     */
    public Map<String, Object> queryApproachDetailInfo(MaterialApproachEntity entity) {
        Map<String, Object> map;
        try {
            map = mapper.selectApproachDetailInfo(entity);
            // 获取附件
            if (map != null && map.size() > 0) {
                selFiles(map, entity.getId());
            }
        } catch (Exception e) {
            throw new EscstException("查询材料进场详情失败！", e);
        }
        return map;
    }

//	// 文件保存
//	private void saveFiles(MultipartFile[] files, String busId) {
//		if (files != null && files.length > 0) {
//			// 文件存储的存到数据库的路径
//			String savePath = "upload" + SEPARATOR_CHAR + "material" + SEPARATOR_CHAR + DateUtils.format(new Date(), "yyyyMM");
//			for (int i = 0; i < files.length; i++) {
//				MultipartFile file = files[i];
//				fileService.uploadFile(file, busId, savePath);
//			}
//		}
//	}

    // 获取附件链接
    private void selFiles(Map<String, Object> map, String id) {
        if (map != null && map.size() > 0) {
            Long isAttach = (Long) map.get("isAttach");
            if (isAttach == 1) {
                List<FilePathVO> filePathVOS = fileService.queryFilePath(id);
                if (filePathVOS != null && filePathVOS.size() > 0) {
                    List<BaseVO> filePathList = new ArrayList<BaseVO>();
                    for (FilePathVO filePathVO : filePathVOS) {
                        BaseVO baseVO = new BaseVO();
                        baseVO.setId(filePathVO.getId());
                        baseVO.setName(filePathVO.getFilePath());
                        filePathList.add(baseVO);
                    }
                    map.put("filePathList", filePathList);
                }
            } else {
                map.put("filePathList", new ArrayList<String>());
            }
        }
    }

    /**
     * @param entity
     * @return
     * @desc 查询可用量大于0的进场材料
     * @author zhouwei
     * @date 2017年12月29日 下午1:51:21
     */
    public PageVo queryMaterialAvailableList(MaterialBean entity) {
        PageVo pageVo = new PageVo();
        // 查询材料领用列表总数
        int count = mapper.queryMaterialAvailableCount(entity);
        // 总记录数
        pageVo.setTotalRecord(count);
        // 当前页
        pageVo.setCurrentPage(entity.getPage());
        pageVo.setPageSize(entity.getRowNum());
        // 第一条记录的索引
        int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
        entity.setStartIndex(startIndex);
        if (count > 0) {
            List<Map<String, Object>> list = mapper.queryMaterialAvailableList(entity);
            pageVo.setRows(list);
        }
        return pageVo;
    }

    /**
     * @param entity
     * @return
     * @desc 材料进场，选择数量时，查出地磅记录
     * @author jincheng
     * @date 2018-4-24 9:28
     */
    public PageVo chooseCount(MaterialApproachEntity entity) {
        PageVo pageVo = new PageVo();
        // 查询地磅列表总数
        int count = mapper.getWeighbridgeCount(entity);
        // 总记录数
        pageVo.setTotalRecord(count);
        // 当前页
        pageVo.setCurrentPage(entity.getPage());
        pageVo.setPageSize(entity.getRowNum());
        // 第一条记录的索引
        int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
        entity.setStartIndex(startIndex);
        if (count > 0) {
            List<MaterialApproachEntity> list = mapper.listWeighbridge(entity);
            pageVo.setRows(list);
        }
        return pageVo;
    }

    /**
     * @param
     * @return
     * @desc 新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片
     * @author jincheng
     * @date 2018-6-13 10:39
     */
    @Transactional
    public MaterialApproachEntity getWeighbridge(MaterialApproachEntity entity) {
        try {
            MaterialApproachEntity weighbridge = mapper.getWeighbridge(entity);
            if (weighbridge != null) {
                String[] array = new String[2];
                array[0] = Base64Util.imgBase64(weighbridge.getFrontImagePath());
                array[1] = Base64Util.imgBase64(weighbridge.getLaterImagePath());
                weighbridge.setPathArray(array);
            }
            return weighbridge;
        } catch (Exception e) {
            throw new EscstException("新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片异常", e);
        }
    }


    /**
     * @return
     * @desc 获取该工地下最新材料进场单号
     * @author niejing
     * @date 2018年5月15日 下午4:48:39
     */
    private String getLatestOrderNo(String constructionId) {
        String orderNo = "";
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("constructionId", constructionId);
            orderNo = mapper.selectLatestOrderNo(map);

            // 初始化整改单号
            if (org.apache.commons.lang3.StringUtils.isBlank(orderNo)) {
                orderNo = CL_ORDER_NO;
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
}
