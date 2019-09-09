package com.escst.easyDarwin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.escst.easyDarwin.entity.ChannelEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年3月9日 下午1:38:47
 */
@Component
public interface ChannelMapper {

	
	List<ChannelEntity> selectChannelList(String constuctionId);
	
}
