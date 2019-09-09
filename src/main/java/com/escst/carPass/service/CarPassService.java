package com.escst.carPass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.carPass.entity.CarPassEntity;
import com.escst.carPass.mapper.CarPassMapper;
import com.escst.carPass.vo.CarPassVo;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;

/**
 * @desc
 * @author niejing
 * @date 2018年8月16日 上午10:03:26
 */
@Service
public class CarPassService {

	@Autowired
	CarPassMapper carPassMapper;
	@Autowired
	FileService fileService;
	
	public PageVo list(CarPassEntity entity) {
		PageVo pageVo = new PageVo();
		List<CarPassEntity> list = new ArrayList<CarPassEntity>();

		// 因为查出自己，所以加1
		int count = carPassMapper.selectCount(entity);
		// 当前页
		pageVo.setCurrentPage(entity.getPage());
		// 总记录数
		pageVo.setTotalRecord(count);
		pageVo.setPageSize(entity.getRowNum());
		// 每页第一条记录的索引
		int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
		entity.setStartIndex(startIndex);
		list = carPassMapper.selectList(entity);
		if (!CollectionUtils.isEmpty(list)) {
			pageVo.setRows(list);
		}
		return pageVo;
	}
	
	public CarPassVo queryDetail(String id){
		CarPassVo vo = carPassMapper.selectDetail(id);
//		vo.setCarPath(fileService.queryFilePathById(vo.getFileId()));
		return vo;
	}
}
