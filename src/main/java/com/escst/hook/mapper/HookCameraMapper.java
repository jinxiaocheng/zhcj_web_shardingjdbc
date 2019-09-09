package com.escst.hook.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.escst.hook.entity.HookCameraEntity;
import com.escst.video.vo.NvrVO;

/**
 * @desc 
 * @author niejing
 * @date 2018年10月22日 下午3:17:47
 */
@Repository
public interface HookCameraMapper {

	/**
	 * @desc 查询所有有效工地的摄像头
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月2日 上午11:30:20
	 */
	List<HookCameraEntity> selectCameraList(String roleId);
	
	
	List<NvrVO> selectNvr();
}
