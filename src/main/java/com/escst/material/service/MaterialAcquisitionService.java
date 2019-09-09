package com.escst.material.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.file.service.FileService;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialAcquisitionEntity;
import com.escst.material.mapper.MaterialAcquisitionMapper;
import com.escst.material.mapper.MaterialApproachMapper;

/**
 * @desc 材料领用服务类
 * @author niejing
 * @date 2017年8月21日 下午5:01:42
 */
@Service
@Transactional
public class MaterialAcquisitionService {

	@Autowired
	private MaterialAcquisitionMapper mapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private MaterialApproachMapper approachMapper;
	
	/**
	 * 材料领用
	 *
	 * @param materialEntity
	 *            参数
	 */
	public void add(MaterialAcquisitionEntity materialEntity) {
		MultipartFile[] files = materialEntity.getFiles();
		String id = UuidUtils.getUuid();
		if (files != null && files.length > 0) {
			materialEntity.setIsAttach(1);
//				saveFiles(files, id);
			fileService.uploadFile("material", id, files);
		}
		// 领用数量
		Float quantity = materialEntity.getQuantity();
		materialEntity.setId(id);
		//TODO 先设置为4
		materialEntity.setStatus(4);
		materialEntity.setCreateBy(ContextUtils.getCurrentUserId());
		materialEntity.setCreateTime(new Date());
		mapper.insertUseMatInfo(materialEntity);
		
		// 状态为4，确认领料
		if (materialEntity.getStatus() == 4) {
			// 获取可用数量
			Float availableQuantity = approachMapper.selectAvailableQuantity(materialEntity.getApproachId());
			if (availableQuantity == null || availableQuantity.floatValue() == 0f) {
				throw new EscstException("可用量不能为0");
			}
			if (availableQuantity.floatValue() < quantity.floatValue()) {
				throw new EscstException("领用数量不能大于" + availableQuantity.floatValue());
			}
			// 更新可用数量
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("approachId", materialEntity.getApproachId());
			map.put("availableQuantity", availableQuantity - quantity);
			approachMapper.updateAvailableQuantity(map);
		}
	}

	/**
	 * 材料领用列表
	 *
	 * @param entity
	 *            参数
	 * @return 返回值
	 */
	public PageVo queryUseMaterialList(MaterialBean entity) {
		PageVo pageVo = new PageVo();
		try {
			// 查询材料领用列表总数
			int count = mapper.selectAcquisitionCount(entity);
			// 总记录数
			pageVo.setTotalRecord(count);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			pageVo.setPageSize(entity.getRowNum());
			// 第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = mapper.selectUseMaterialList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询材料领用列表失败！", e);
		}
		return pageVo;
	}

	/**
	 * 修改材料领用状态
	 *
	 * @param materialAcquisitionEntity
	 *            参数
	 * @return 返回值
	 */
	public ReturnJson updateUseMaterialStatus(MaterialAcquisitionEntity materialAcquisitionEntity) {
		ReturnJson returnJson = new ReturnJson();
		try {
			materialAcquisitionEntity.setUpdateBy(ContextUtils.getCurrentUserId());
			// 状态判断
			int status = materialAcquisitionEntity.getStatus();
			// 状态4，确认领用材料
			if (status == 4) {
				// 领用数量
				Float quantity = materialAcquisitionEntity.getQuantity();
				// 获取材料进场ID
				String approachId = mapper.selectApproachId(materialAcquisitionEntity.getId());
				// 获取可用数量
				Float availableQuantity = approachMapper.selectAvailableQuantity(approachId);
				if (quantity > availableQuantity) {
					returnJson.setStatus(0);
					returnJson.setMsg("确认领用失败，可用材料数量不足！");
				} else {
					// 更新可用数量
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("approachId", approachId);
					map.put("availableQuantity", availableQuantity - quantity);
					approachMapper.updateAvailableQuantity(map);
					// 更新材料领用状态
					mapper.updateStatus(materialAcquisitionEntity);
					returnJson = ReturnJson.success();
				}
			} else {
				// 更新材料领用状态
				mapper.updateStatus(materialAcquisitionEntity);
				returnJson = ReturnJson.success();
			}

		} catch (Exception e) {
			throw new EscstException("修改材料领用状态失败！", e);
		}
		return returnJson;
	}

	/**
	 * 材料领用详情
	 * 
	 * @param entity
	 *            详情
	 * @return 返回值
	 */
	public Map<String, Object> queryAcquisitionDetailInfo(MaterialAcquisitionEntity entity) {
		Map<String, Object> map;
		try {
			map = mapper.queryAcquisitionDetailInfo(entity);
			// 获取附件
			if (map != null && map.size() > 0) {
				selFiles(map, entity.getId());
			}
		} catch (Exception e) {
			throw new EscstException("查询材料领用详情失败！", e);
		}
		return map;
	}

	// 文件保存
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
				List<String> filePathList = fileService.queryFilePathByBusinessId(id);
				if (filePathList != null && filePathList.size() > 0) {
					map.put("filePathList", filePathList);
				}
			} else {
				map.put("filePathList", new ArrayList<String>());
			}
		}
	}
}
