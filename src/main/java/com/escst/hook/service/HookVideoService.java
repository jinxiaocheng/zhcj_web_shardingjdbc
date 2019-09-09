package com.escst.hook.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.UuidUtils;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;

/**
 * @desc
 * @author niejing
 * @date 2018年10月24日 上午9:37:45
 */
@Service
@Transactional
public class HookVideoService {

	@Autowired
	private ConstructionService constructionService;

	@Autowired
	private HookCameraService hookCameraService;

	@Autowired
	private HookNvrService hookNvrService;
	
	/**
	 * 
	 * @desc 根据工地Id查询摄像头列表
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年10月22日 下午3:37:50
	 */
	public List<TreeEntity> queryVideoConstructionTree(String userId) {
		// 区域和工地图标路径
		String areaIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_area");
		// 工地在线图标路径
		String constructionOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_on_line");
		List<TreeEntity> list = new ArrayList<TreeEntity>();
		List<SimpleConstructionVO> constructionList = constructionService.queryAuthConstructionByUserId(userId);
		if (CollectionUtils.isEmpty(constructionList)) {
			return list;
		}
		Map<String, List<TreeEntity>> cameraTreeMap = hookCameraService.getConstructionCameraMap(userId);
		Map<String, String> nvrMap = hookNvrService.queryNvrMap();
		Set<String> areaMap = new HashSet<String>();
		for (SimpleConstructionVO constructionVO : constructionList) {
			String constructionId = constructionVO.getId();
			// 工地没有摄像头则不显示出来
			if (!cameraTreeMap.containsKey(constructionId)) {
				continue;
			}
			if (!nvrMap.containsKey(constructionId)) {
				continue;
			}
			String areaId = constructionVO.getAreaId();
			String areaName = constructionVO.getAreaName();
			if (!areaMap.contains(areaId)) {
				TreeEntity areaEntity = new TreeEntity();
				areaEntity.setId(areaId);
				areaEntity.setpId("0");
				areaEntity.setName(areaName);
				areaEntity.setOpen(true);
				areaEntity.setIcon(areaIcon);
				list.add(areaEntity);

				areaMap.add(areaId);
			}
			// 增加工地节点
			TreeEntity constructionTree = new TreeEntity();
			constructionTree.setId(constructionId);
			constructionTree.setpId(areaId);
			constructionTree.setName(constructionVO.getName());
			constructionTree.setOpen(false);
			constructionTree.setIcon(constructionOnlineIcon);
			// NvrVO nvrVO = nvrMap.get(constructionId);
			// constructionTree.setNvrVO(nvrVO);
			String encryptNvr = nvrMap.get(constructionId);
			// constructionTree.setEncryptNvr(encryptNvr);

			// 获得工地下的摄像头列表
			List<TreeEntity> cameraList = cameraTreeMap.get(constructionId);

			if (cameraList.size() > 16) {
				constructionTree.setEncryptNvr("");
			} else {
				constructionTree.setEncryptNvr(encryptNvr);
			}

			list.add(constructionTree);

			if (cameraList.size() > 16) {
				// 增加第一个组节点
				TreeEntity oneGroupTree = new TreeEntity();
				String oneGroupTreeId = UuidUtils.getUuid();
				oneGroupTree.setId(oneGroupTreeId);
				oneGroupTree.setName("一组");
				oneGroupTree.setpId(constructionTree.getId());
				oneGroupTree.setOpen(false);
				oneGroupTree.setIcon(constructionOnlineIcon);
				oneGroupTree.setEncryptNvr(encryptNvr);
				// 增加第二个组节点
				TreeEntity twoGroupTree = new TreeEntity();
				String twoGroupTreeId = UuidUtils.getUuid();
				twoGroupTree.setId(twoGroupTreeId);
				twoGroupTree.setName("二组");
				twoGroupTree.setpId(constructionTree.getId());
				twoGroupTree.setOpen(false);
				twoGroupTree.setIcon(constructionOnlineIcon);
				twoGroupTree.setEncryptNvr(encryptNvr);

				int size = cameraList.size();
				for (int i = 0; i < size; i++) {
					if (i < 16) {
						cameraList.get(i).setpId(oneGroupTreeId);
					} else {
						// 16之后添加到第二组中
						cameraList.get(i).setpId(twoGroupTreeId);
					}
				}
				cameraList.add(0, oneGroupTree);
				cameraList.add(17, twoGroupTree);
				list.addAll(cameraList);
			} else {
				list.addAll(cameraList);
			}
		}
		return list;
	}

}
