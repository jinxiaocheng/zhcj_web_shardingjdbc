package com.escst.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.person.entity.QualificatioEntity;
import com.escst.person.mapper.QualificatioMapper;
import com.escst.redis.annotation.RedisCache;

/**
 * @desc 资质
 * @author zhouwei
 * @date 2017年11月2日 下午6:13:24
 */
@Service
public class QualificatioService {

	@Autowired
	private QualificatioMapper qualificatioMapper;
	
	public void insert(QualificatioEntity entity) {
		qualificatioMapper.insert(entity);
	}
	
	@RedisCache
	public QualificatioEntity getById(String id) {
		return qualificatioMapper.selectById(id);
	}
	
	public List<QualificatioEntity> queryByUserId(String userId) {
		return qualificatioMapper.selectByUserId(userId);
	}

	public void update(QualificatioEntity entity) {
		qualificatioMapper.update(entity);
	}
}
