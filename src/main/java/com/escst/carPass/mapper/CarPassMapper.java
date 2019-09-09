package com.escst.carPass.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.carPass.entity.CarPassEntity;
import com.escst.carPass.vo.CarPassVo;
import com.escst.commons.mapper.BaseMapper;

/**
 * @desc 
 * @author niejing
 * @date 2018年8月16日 上午10:03:15
 */
@Repository
public interface CarPassMapper extends BaseMapper<CarPassEntity>{

	List<CarPassEntity> list(CarPassEntity entity);
	
	CarPassVo selectDetail(String id);
}
