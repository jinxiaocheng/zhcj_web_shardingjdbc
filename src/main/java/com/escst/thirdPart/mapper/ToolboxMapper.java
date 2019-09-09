package com.escst.thirdPart.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.thirdPart.entity.CodeContentEntity;
import com.escst.thirdPart.entity.DepartEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月30日 下午2:40:52
 */
@Repository
public interface ToolboxMapper {

	void batchInsert(List<DepartEntity> list);
	
	void batchInsertCode(List<CodeContentEntity> list);
	
	List<DepartEntity> queryList();
}
