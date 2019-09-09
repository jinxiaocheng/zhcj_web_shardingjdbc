package com.escst.news.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.file.service.FileService;
import com.escst.news.entity.NewsEntity;
import com.escst.news.mapper.NewsMapper;
import com.escst.user.entity.UserEntity;

/**
 * 
 * @desc
 * @author niejing
 * @date 2017年7月10日 下午1:36:52
 */
@Service
@Transactional
public class NewsService {
	private static final String NEWS = "news";
	
	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private FileService fileService;
	/**
	 * 获取广告列表
	 *
	 * @return 返回值
	 */
	public PageVo queryAdList(NewsEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			int count = newsMapper.selectCount(entity);
			// 当前页
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);
			pageVo.setPageSize(entity.getRowNum());
			// 每页第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);

			List<Map<String, Object>> menulist = newsMapper.queryAdList(entity);
			pageVo.setRows(menulist);
		} catch (Exception e) {
			throw new EscstException("查询主菜单出现异常:" + e.getMessage(), e);
		}
		return pageVo;
	}

	/**
	 * 新闻图片信息保存
	 *
	 * @param entity
	 *            信息实体
	 */
	public void save(NewsEntity entity) {
		try {
			String fileId = "";
			UserEntity userEntity = ContextUtils.getCurrentUser();
			if (entity.getMultipartFile() != null) {
				//调用文件上传服务上传文件
				fileId = fileService.uploadFile(NEWS, entity.getMultipartFile());
			}

			entity.setId(UuidUtils.getUuid());
			entity.setFileId(fileId);
			entity.setCreateBy(userEntity.getId());
			entity.setCreateTime(new Date());
			newsMapper.saveNewsInfo(entity);
		} catch (Exception e) {
			throw new EscstException("新增资讯异常", e);
		}
	}
}
