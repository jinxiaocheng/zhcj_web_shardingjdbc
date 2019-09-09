package com.escst.hook.mapper;

import org.springframework.stereotype.Repository;

import com.escst.hook.entity.HookNvrEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年10月22日 下午3:07:42
 */
@Repository
public interface HookNvrMapper {

	HookNvrEntity queryNvrByConstructionId(String constructionId);
}
