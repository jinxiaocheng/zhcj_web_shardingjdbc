package com.escst.person.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.person.entity.QualificatioEntity;

/**
 * @desc 资质
 * @author zhouwei
 * @date 2017年11月2日 下午6:06:01
 */
@Repository
public interface QualificatioMapper {

	/**
	 * @desc 保存资质信息
	 * @param entity 
	 * @author zhouwei
	 * @date 2017年11月2日 下午6:12:31
	 */
	void insert(QualificatioEntity entity);

	/**
	 * @desc 修改资质信息
	 * @param entity
	 * @author zhouwei
	 * @date 2017年11月2日 下午6:12:31
	 */
	void update(QualificatioEntity entity);

	/**
	 * @desc 根据id查询资质信息
	 * @param id
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月2日 下午6:12:41
	 */
	QualificatioEntity selectById(String id);
	
	/**
	 * @desc 根据用户ID得到资质证书
	 * @param userId
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月2日 下午6:23:38
	 */
	List<QualificatioEntity> selectByUserId(String userId);


}
