package com.escst.hook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.EncryUtil;
import com.escst.hook.mapper.HookCameraMapper;
import com.escst.video.vo.NvrVO;

/**
 * @desc
 * @author niejing
 * @date 2018年10月24日 下午4:35:50
 */
@Service
public class HookNvrService {

	@Autowired
	private HookCameraMapper hookCameraMapper;

	public Map<String, String> queryNvrMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<NvrVO> list = hookCameraMapper.selectNvr();
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		for (NvrVO nvr : list) {
			String encryptNvr = null;
			try {
				encryptNvr = EncryUtil.encryption(nvr.getIp() + "," + nvr.getPort() + "," + nvr.getUserName() + "," + nvr.getPassword());
				map.put(nvr.getConstructionId(), encryptNvr);
			} catch (Exception e) {
				throw new EscstException("加密字符串失败" + e.getMessage(), e);
			}

		}
		return map;
	}
}
