package com.escst.hook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.hook.entity.HookCameraEntity;
import com.escst.hook.mapper.HookCameraMapper;
import com.escst.role.mapper.RoleMapper;

/**
 * @desc
 * @author niejing
 * @date 2018年10月24日 下午4:35:22
 */
@Service
public class HookCameraService {

	@Autowired
	private HookCameraMapper hookCameraMapper;

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * @desc 得到所有在线工地的摄像头信息
	 * @return
	 * @author zhouwei
	 * @date 2017年8月2日 上午11:14:21
	 */
	public Map<String, List<TreeEntity>> getConstructionCameraMap(String userId) {
		Map<String, List<TreeEntity>> rst = new HashMap<String, List<TreeEntity>>();
		List<HookCameraEntity> cameraList = new ArrayList<HookCameraEntity>();
		List<String> roleIds = roleMapper.queryByUserId(userId);
		if (roleIds.size() > 1) {
			for (String roleId : roleIds) {
				List<HookCameraEntity> tmpList = new ArrayList<HookCameraEntity>();
				tmpList = hookCameraMapper.selectCameraList(roleId);
				tmpList.removeAll(cameraList);
				cameraList.addAll(tmpList);
			}
		} else {
			cameraList = hookCameraMapper.selectCameraList(roleIds.get(0));
		}

		if (CollectionUtils.isEmpty(cameraList)) {
			return rst;
		}
		// 摄像头在线图标路径
		String cameraOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_camera_on_line");
		// 摄像头离线图标路径
		String cameraOfflineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_camera_off_line");
		for (HookCameraEntity camera : cameraList) {
			String constructionId = camera.getConstructionId();

			TreeEntity cameraTree = new TreeEntity();
			String cameraName = camera.getName();
			int isOnline = camera.getStatus();
			if (isOnline == 1) {
				cameraTree.setIcon(cameraOnlineIcon);
			} else {
				cameraTree.setIcon(cameraOfflineIcon);
			}
			cameraTree.setId(camera.getId());
			cameraTree.setpId(constructionId);
			cameraTree.setName(cameraName);
			cameraTree.setOpen(false);
			cameraTree.setChannelNo(camera.getChannelNo());
			cameraTree.setFlag(camera.getFlag());
			cameraTree.setXml(camera.getHikXml());
			if (rst.containsKey(constructionId)) {
				List<TreeEntity> treeList = rst.get(constructionId);
				treeList.add(cameraTree);
			} else {
				List<TreeEntity> treeList = new ArrayList<TreeEntity>();
				treeList.add(cameraTree);
				rst.put(constructionId, treeList);
			}
		}

		return rst;
	}

}
