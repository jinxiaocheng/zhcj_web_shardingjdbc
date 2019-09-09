package com.escst.video.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.role.mapper.RoleMapper;
import com.escst.video.entity.CameraEntity;
import com.escst.video.mapper.CameraMapper;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 16:32
 */
@Service
public class CameraService {

    @Autowired
    private ConstructionService constructionService;

    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private RoleMapper roleMapper;
    /**
     * @desc 得到用户有权限访问的工地及摄像头
     * @param userId
     * @return 
     * @author zhouwei
     * @date 2017年8月2日 上午11:09:33
     */
    public List<TreeEntity> queryNodeListByUser(String userId) {
    	List<TreeEntity> rst = new ArrayList<TreeEntity>();
    	List<SimpleConstructionVO> list = constructionService.queryAuthConstructionByUserId(userId);
    	//区域和工地图标路径
        String areaIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_area");
        //工地在线图标路径
        String constructionOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_on_line");
        //工地离线图标路径
        String constructionOfflineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_off_line");
        //摄像头在线图标路径
        String cameraOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_camera_on_line");
        Map<String, List<TreeEntity>> cameraMap = getConstructionCameraMap(userId);
    	Map<String, TreeEntity> areaMap = new HashMap<String, TreeEntity>();
    	Map<String, Integer> onlineMap = new HashMap<String, Integer>();
    	Map<String, Integer> totalMap = new HashMap<String, Integer>();
    	for (SimpleConstructionVO construction : list) {
    		String areaId = construction.getAreaId();
    		TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId(areaId);
            treeEntity.setpId("0");
            treeEntity.setName(construction.getAreaName());
            if (areaMap.size() > 0) {
            	treeEntity.setOpen(false);
            }
            else {
            	treeEntity.setOpen(true);
            }
            treeEntity.setIcon(areaIcon);
            areaMap.put(areaId, treeEntity);
            onlineMap.put(areaId, 0);
            totalMap.put(areaId, 0);
            
            rst.add(treeEntity);
    	}
    	
    	for (SimpleConstructionVO construction : list) {
    		String areaId = construction.getAreaId();
    		if (areaMap.containsKey(areaId)) {
    			TreeEntity constructionTree = new TreeEntity();
    			String id = construction.getId();
                constructionTree.setId(id);
                constructionTree.setpId(areaId);
                constructionTree.setName(construction.getName());
                constructionTree.setOpen(true);
                int isOnline = construction.getIsOnline();
                if (isOnline == 1) {
                	constructionTree.setIcon(constructionOnlineIcon);
                	int num = onlineMap.get(areaId);
                	onlineMap.put(areaId, num + 1);
                }
                else {
                	constructionTree.setIcon(constructionOfflineIcon);
                }
                int num = totalMap.get(areaId);
                totalMap.put(areaId, num + 1);
                
                rst.add(constructionTree);
                
                if (cameraMap.containsKey(id)) {
                	List<TreeEntity> cameraList = cameraMap.get(id);
                	int onlineNum = 0;
                	for (TreeEntity cameraEntity : cameraList) {
                		if (cameraEntity.getIcon().equals(cameraOnlineIcon)) {
                			onlineNum++;
                		}
                	}
                	constructionTree.setName(construction.getName() + "  (" + cameraList.size() + "/" + onlineNum + ")");
                    rst.addAll(cameraList);
                }
    		}
    		else {
    			// 增加区域节点
//    			TreeEntity treeEntity = new TreeEntity();
//                treeEntity.setId(areaId);
//                treeEntity.setpId("0");
//                treeEntity.setName(construction.getAreaName());
//                if (areaMap.size() > 0) {
//                	treeEntity.setOpen(false);
//                }
//                else {
//                	treeEntity.setOpen(true);
//                }
//                treeEntity.setIcon(areaIcon);
//                areaMap.put(areaId, treeEntity);
//                onlineMap.put(areaId, 0);
//                totalMap.put(areaId, 0);
//                
//                rst.add(treeEntity);
    		}
    	}
    	
    	// 更新区域节点的名称
    	Iterator<Entry<String, TreeEntity>> it = areaMap.entrySet().iterator();
    	while (it.hasNext()) {
    		Entry<String, TreeEntity> entry = it.next();
    		TreeEntity areaEntity = entry.getValue();
    		String areaId = areaEntity.getId();
    		String name = areaEntity.getName();
    		int onlineNum = onlineMap.get(areaId);
    		int totalNum = totalMap.get(areaId);
    		areaEntity.setName(name + " (" + totalNum + "/" + onlineNum + ")");
    	}
    	
    	return rst;
    }
    
    /**
     * @desc 得到所有在线工地的摄像头信息
     * @return 
     * @author zhouwei
     * @date 2017年8月2日 上午11:14:21
     */
//    @RedisCache
    public Map<String, List<TreeEntity>> getConstructionCameraMap(String userId) {
    	Map<String, List<TreeEntity>> rst = new HashMap<String, List<TreeEntity>>();
        List<CameraEntity> cameraList = new ArrayList<CameraEntity>();
        List<String> roleIds = roleMapper.queryByUserId(userId);
        if(roleIds.size() >1){
            for(String  roleId: roleIds){
                List<CameraEntity> tmpList = new ArrayList<CameraEntity>();
                tmpList = cameraMapper.selectCameraList(roleId);
                tmpList.removeAll(cameraList);
                cameraList.addAll(tmpList);
            }
        }else {
             cameraList = cameraMapper.selectCameraList(roleIds.get(0));
        }

    	if (CollectionUtils.isEmpty(cameraList)) {
    		return rst;
    	}
    	//摄像头在线图标路径
        String cameraOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_camera_on_line");
        //摄像头离线图标路径
        String cameraOfflineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_camera_off_line");
    	for (CameraEntity camera : cameraList) {
    		String constructionId = camera.getConstructionId();
    		
    		TreeEntity cameraTree = new TreeEntity();
            String cameraName = camera.getName();
            int isOnline = camera.getStatus();
            if(isOnline == 1) {
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
    		}
    		else {
    			List<TreeEntity> treeList = new ArrayList<TreeEntity>();
    			treeList.add(cameraTree);
    			rst.put(constructionId, treeList);
    		}
    	}
    	
    	return rst;
    }

    /**
     * 拼凑查看视频的xml数据
     *
     * @param cameraId
     * @return
     */
    public String queryPreviewXml(String cameraId) {
//        int cameraChannelNum = cameraEntity.getChannelNo();  //通道号
//        int channelNo = cameraChannelNum - 1;   //通道序号
//        String HikGbIndexCode = cameraEntity.getHikGbIndexcode();
//        String hikSysCode = cameraEntity.getHikSysCode();
//        String ip = cameraEntity.getHikOutsideIp();
//        String username = cameraEntity.getHikCameraUsername();
//        String password = cameraEntity.getHikCameraPassword();
//        String name = cameraEntity.getName();
//        int hikCameraId = cameraEntity.getHikCameraId();
//        String url = "rtsp://" + ip + ":554/pag://" + ip + ":7302:" + hikSysCode + ":" + channelNo + ":MAIN:TCP?cnid=4&amp;pnid=6";
//        StringBuffer sb = new StringBuffer();
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        sb.append("<previewInfo>");
//        sb.append("<camera encoderPwd=\"\" encoderUserName=\"\" cameraChannelNum=\"" + cameraChannelNum + "\" ");
//        sb.append("encoderModel=\"134747648\" cascade=\"0\" decodeTag=\"hikvision\" ");
//        sb.append("gbIndexCode=\"" + HikGbIndexCode + "\" installPosition=\"0\" supportFishEye=\"0\" extraSupport=\"0\" ");
//        sb.append("url=\"" + url + "\" ");
//        sb.append("sysCode=\"" + hikSysCode + "\" ");
//        sb.append("name=\"" + name + "\" id=\"" + hikCameraId + "\" />");
//        sb.append("<presetlist /><cruiselist />");
//        sb.append("<server password=\"" + password + "\" username=\"" + username + "\" port=\"7302\" ip=\"" + ip + "\" id=\"2\"/>");
//        sb.append("<right ptzcfg=\"1\" ptzcontrol=\"1\" videoParamcfg=\"1\" record=\"1\" />");
//        sb.append("<user locktime=\"30\" priority=\"50\" id=\"4\" />");
//        sb.append("<previewparam showsmart=\"false\" ezvizVag=\"false\" />");
//        sb.append("</previewInfo>");
    	String hikxml = "";
    	try{
    		CameraEntity cameraEntity = cameraMapper.selectById(cameraId);
    		hikxml = cameraEntity.getHikXml();
    	}catch(Exception e){
    		throw new EscstException("获取海康xml字符串异常",e);
    	}
        return hikxml;
    }

    /**
     * 通过工地id查询工地下面的摄像头信息
     *
     * @param constructionId
     * @return
     */
    public List<CameraEntity> queryCameraList(String constructionId) {
        CameraEntity cameraEntity = new CameraEntity();
        cameraEntity.setConstructionId(constructionId);
        List<CameraEntity> list = cameraMapper.selectList(cameraEntity);
        return list;
    }

    /**
     * 查询该工地下面的摄像头数量
     *
     * @param constructionId
     * @return
     */
    public Map<String, Object> queryCameraCount(String constructionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        CameraEntity cameraEntity = new CameraEntity();
        cameraEntity.setConstructionId(constructionId);
        int count = cameraMapper.selectCount(cameraEntity);
        cameraEntity.setStatus(1);
        int isonlineCount = cameraMapper.selectCount(cameraEntity);
        map.put("count", count);
        map.put("isonlineCount", isonlineCount);
        return map;
    }

    /**
     * 
     * @desc 
     * @param constructionId
     * @return 
     * @author niejing
     * @date 2018年8月3日 上午9:11:19
     */
    public int queryCameraCount(String constructionId,int status) {
    	int count = 0;
    	
    	CameraEntity cameraEntity = new CameraEntity();
    	cameraEntity.setConstructionId(constructionId);
    	cameraEntity.setStatus(status);
    	try{
    		count = cameraMapper.selectCount(cameraEntity);
    	}catch(Exception e){
    		throw new EscstException("查询摄像头数量异常");
    	}
    	return count;
    }
}

