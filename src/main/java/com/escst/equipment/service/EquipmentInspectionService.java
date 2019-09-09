package com.escst.equipment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.vo.PageVo;
import com.escst.equipment.mapper.EquipmentInspectionMapper;
import com.escst.equipment.vo.InspectionQueryVO;
import com.escst.equipment.vo.InspectionVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月23日 下午1:52:12
 */
@Service
@Transactional
public class EquipmentInspectionService {

	@Autowired
	private EquipmentInspectionMapper rquipmentInspectionMapper;

	@Autowired
	private FileService fileService;
	
	/**
	 * @desc 查询设备巡查记录
	 * @param queryVO
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月23日 下午2:29:07
	 */
	public PageVo queryEquipmentInspectionList(InspectionQueryVO queryVO) {
		List<InspectionVO> list = null;
		int totalQty = rquipmentInspectionMapper.getEquipmentInspectionCount(queryVO);
		if (totalQty > 0) {
			list = rquipmentInspectionMapper.queryEquipmentInspectionList(queryVO);
			
			// 获取巡检的图片
			List<String> idList = new ArrayList<String>();
			for (InspectionVO vo : list) {
				if (vo.getIsAttach() == 1) {
					idList.add(vo.getId());
				}
			}
			if (idList.size() > 0) {
				Map<String, List<FilePathVO>> filePathMap = fileService.getPicturePathMapByBusinessIds(idList);
				for (InspectionVO vo : list) {
					if (vo.getIsAttach() == 1) {
						vo.setFilePathList(filePathMap.get(vo.getId()));
						continue;
					}
				}
			}
		}
		PageVo rst = new PageVo();
		rst.setCurrentPage(queryVO.getPage());
		rst.setPageSize(queryVO.getRowNum());
		rst.setTotalRecord(totalQty);
		rst.setRows(list);
		return rst;
	}
	
	/**
	 * @desc 得到设备巡检详情
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月24日 下午3:38:19
	 */
	public InspectionVO getEquipmentInspection(String id) {
		InspectionVO vo = rquipmentInspectionMapper.getEquipmentInspection(id);
		if (vo.getIsAttach() == 1) {
			List<FilePathVO> filePathList = fileService.queryFilePath(vo.getId());
			vo.setFilePathList(filePathList);
		}
		return vo;
	}
}
