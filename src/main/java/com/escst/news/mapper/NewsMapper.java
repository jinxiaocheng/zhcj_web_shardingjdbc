package com.escst.news.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.mapper.BaseMapper;
import com.escst.news.entity.NewsEntity;
import org.springframework.stereotype.Repository;

/**
 * 
 * @desc
 * @author niejing
 * @date 2017年7月10日 下午1:36:47
 */
@Repository
public interface NewsMapper extends BaseMapper<NewsEntity> {

	/**
	 * 获取广告列表
	 * 
	 * @return 返回值
	 */
	List<Map<String, Object>> queryAdList(NewsEntity entity);

	/**
	 * 新闻图片信息保存
	 * 
	 * @param newsEntity
	 *            信息实体
	 */
	void saveNewsInfo(NewsEntity newsEntity);
}
