package com.escst.easyDarwin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.HttpClientUtils;
import com.escst.easyDarwin.entity.ChannelEntity;
import com.escst.easyDarwin.mapper.ChannelMapper;

/**
 * @desc 
 * @author niejing
 * @date 2018年3月9日 下午3:21:40
 */
@Service
public class ChannelService {

	@Autowired
	private ChannelMapper channelMapper;
	
	/**
	 * 
	 * @desc 获取通道信息
	 * @author niejing
	 * @date 2018年3月6日 下午4:20:34
	 */
	public List<ChannelEntity> queryChannelList(String constructionId) {
		try {
			return channelMapper.selectChannelList(constructionId);
		} catch (Exception e) {
			throw new EscstException("新增通道数据异常", e);
		}
	}
	
	/**
	 * 
	 * @desc 获取rtmpUrl列表
	 * 	（15秒没播放流就会自动断流）
	 * @param constructionId
	 * @return 
	 * @author niejing
	 * @date 2018年3月7日 下午3:03:17
	 */
	public List<String> queryChannelStream(String constructionId){
		List<String> list = new ArrayList<String>();
		List<ChannelEntity> cameraList = queryChannelList(constructionId);
		for(ChannelEntity entity : cameraList){
//			startDeviceStream(entity.getSerial(),entity.getChannelNo());
//			getDeviceStream("47.94.161.17","10088",entity.getSerial(),entity.getChannelNo());
			list.add(entity.getRtmpUrl());
		}
		return list;
	}
	
	
	/**
	 * 
	 * @desc EasyClient 向 EasyCMS 请求码流推送
	 * @param serial
	 * @param channel
	 * @return 
	 * @author niejing
	 * @date 2018年3月21日 下午3:57:20
	 */
	private Object startDeviceStream(String serial, int channel) {
		String url = "http://47.94.161.17:10000/api/v1/startdevicestream?device=" + serial + "&channel=" + channel
				+ "&reserve=1";
		return HttpClientUtils.getHttpGetResult(url);
	}
	
	/**
	 * 
	 * @desc EasyClient 向 EasyDarwin 请求设备的码流 
	 * @param ip
	 * @param port
	 * @param serial
	 * @param channel
	 * @return 
	 * @author niejing
	 * @date 2018年3月21日 下午3:57:56
	 */
	private Object getDeviceStream(String ip, String port, String serial, int channel) {
		String url = "http://" + ip + ":" + port + "/api/v1/getdevicestream?device=" + serial + "&channel=" + channel
				+ "&protocol=rtmp&reserve=1";
		return HttpClientUtils.getHttpGetResult(url);
	}
}
