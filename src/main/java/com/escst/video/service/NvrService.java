package com.escst.video.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.EncryUtil;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.mapper.ConstructionMapper;
import com.escst.construction.service.ConstructionService;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.redis.annotation.RedisCache;
import com.escst.user.entity.UserEntity;
import com.escst.video.entity.CameraEntity;
import com.escst.video.entity.NvrDeployEntity;
import com.escst.video.entity.RoleCameraEntity;
import com.escst.video.mapper.CameraMapper;
import com.escst.video.mapper.HikPlatformMapper;
import com.escst.video.vo.CameraVo;
import com.escst.video.vo.NvrVO;
import com.escst.video.vo.RoleCameraVo;
import com.escst.video.vo.VideoLoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author caozx
 * @desc
 * @date 2017/8/24 16:32
 */
@Service
public class NvrService {


    @Autowired
    private ConstructionService constructionService;

    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private ConstructionMapper constructionMapper;

    @Autowired
    private HikPlatformMapper hikPlatformMapper;


    /**
     * @param cityId
     * @return
     * @desc 得到用户有权限访问的工地及摄像头
     * @author caozx
     * @date 2017年8月24日 上午11:09:33
     */
    public List<TreeEntity> queryNVRNodeListByUser(String cityId) {
        List<TreeEntity> rst = new ArrayList<TreeEntity>();

        //区域和工地图标路径
        String areaIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_area");
        //工地在线图标路径
        String constructionOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_on_line");
        //工地离线图标路径
        String constructionOfflineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_off_line");

        //当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        List<SimpleConstructionVO> list = constructionService.queryAuthConstructionByUserId(userId);

        Map<String, TreeEntity> areaMap = new HashMap<String, TreeEntity>();
        Map<String, Integer> onlineMap = new HashMap<String, Integer>();
        Map<String, Integer> totalMap = new HashMap<String, Integer>();
        for (SimpleConstructionVO construction : list) {
            String areaId = construction.getAreaId();
            if (areaMap.containsKey(areaId)) {
                TreeEntity constructionTree = new TreeEntity();
                String id = construction.getId();
                constructionTree.setId(id);
                constructionTree.setpId(areaId);
                constructionTree.setName(construction.getName());
                constructionTree.setOpen(true);
                //查询工地下面的nvr信息
//                NvrVO nvrVO = cameraMapper.getNvr(id);
//                constructionTree.setNvrVO(nvrVO);

                int isOnline = construction.getIsOnline();
                if (isOnline == 1) {
                    constructionTree.setIcon(constructionOnlineIcon);
                    int num = onlineMap.get(areaId);
                    onlineMap.put(areaId, num + 1);
                } else {
                    constructionTree.setIcon(constructionOfflineIcon);
                }
                int num = totalMap.get(areaId);
                totalMap.put(areaId, num + 1);
                rst.add(constructionTree);
            } else {
                // 增加区域节点
                TreeEntity areaTree = new TreeEntity();
                areaTree.setId(areaId);
                areaTree.setpId(cityId);
                areaTree.setName(construction.getAreaName());
                if (areaMap.size() > 0) {
                    areaTree.setOpen(false);
                } else {
                    areaTree.setOpen(true);
                }
                areaTree.setIcon(areaIcon);

                areaMap.put(areaId, areaTree);
                onlineMap.put(areaId, 0);
                totalMap.put(areaId, 0);
                rst.add(areaTree);

                //工地节点Tree
                TreeEntity constructionTree = new TreeEntity();
                constructionTree.setId(construction.getId());
                constructionTree.setpId(areaId);
                constructionTree.setName(construction.getName());
                constructionTree.setOpen(true);

                //查询工地下面的nvr信息
//                NvrVO nvrVO = cameraMapper.getNvr(construction.getId());
//                constructionTree.setNvrVO(nvrVO);

                int isOnline = construction.getIsOnline();
                if (isOnline == 1) {
                    constructionTree.setIcon(constructionOnlineIcon);
                    int num = onlineMap.get(areaId);
                    onlineMap.put(areaId, num + 1);
                } else {
                    constructionTree.setIcon(constructionOfflineIcon);
                }

                int num = totalMap.get(areaId);
                totalMap.put(areaId, num + 1);
                rst.add(constructionTree);
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
     * @param userId 查看工地的用户ID
     * @return
     * @desc 根据用户查询工地和摄像头
     * @author zhouwei
     * @date 2017年11月10日 下午7:53:14
     */
//    @RedisCache
    public List<TreeEntity> queryVideoConstructionTree(String userId) {
        //区域和工地图标路径
        String areaIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_area");
        //工地在线图标路径
        String constructionOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_on_line");
        List<TreeEntity> list = new ArrayList<TreeEntity>();
        List<SimpleConstructionVO> constructionList = constructionService.queryAuthConstructionByUserId(userId);
        if (CollectionUtils.isEmpty(constructionList)) {
            return list;
        }
        Map<String, List<TreeEntity>> cameraTreeMap = cameraService.getConstructionCameraMap(userId);
        NvrService service = (NvrService) AopContext.currentProxy();
//        Map<String, NvrVO> nvrMap = service.queryNvrMap();
        Map<String, String> nvrMap = service.queryNvrMap();
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
//        	NvrVO nvrVO = nvrMap.get(constructionId);
//        	constructionTree.setNvrVO(nvrVO);
            String encryptNvr = nvrMap.get(constructionId);
//            constructionTree.setEncryptNvr(encryptNvr);

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

    /**
     * @desc 查询所有的NVR信息, 以工地ID为key
     * @return
     * @author zhouwei
     * @date 2017年11月10日 下午9:32:02
     */
//    @RedisCache
//    public Map<String, NvrVO> queryNvrMap() {
//        Map<String, NvrVO> map = new HashMap<String, NvrVO>();
//        List<NvrVO> list = cameraMapper.selectNvr();
//        if (CollectionUtils.isEmpty(list)) {
//            return map;
//        }
//        for (NvrVO nvr : list) {
//            String encryptNvr  = nvr.getIp()+nvr.getPort()+nvr.getUserName()+nvr.getPassword();
//
//            map.put(nvr.getConstructionId(), nvr);
//        }
//        return map;
//    }

    /**
     * @param
     * @return
     * @desc 查询所有的NVR信息, 以工地ID为key
     * @author dwj
     * @date 2018/4/27 10:36
     */
    @RedisCache
    public Map<String, String> queryNvrMap() {
        Map<String, String> map = new HashMap<String, String>();
        List<NvrVO> list = cameraMapper.selectNvr();
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

    /**
     * @return
     * @desc 武铁项目的摄像头
     * @author zhouwei
     * @date 2017年11月20日 下午4:59:21
     */
    @RedisCache
    public List<TreeEntity> queryWuTieTree(String userId) {
        //区域和工地图标路径
        String areaIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_area");
        //工地在线图标路径
        String constructionOnlineIcon = ContextUtils.getRequest().getContextPath() + ResourceUtil.getConfigByName("video_icon_construction_on_line");
        List<TreeEntity> list = new ArrayList<TreeEntity>();

        Map<String, List<TreeEntity>> cameraTreeMap = cameraService.getConstructionCameraMap(userId);
//        NvrService service = (NvrService)AopContext.currentProxy();
//        Map<String, NvrVO> nvrMap = service.queryNvrMap();

        List<WuTieVideo> videoList = new ArrayList<WuTieVideo>();
        videoList.add(new WuTieVideo("1", "固定点视频", "gdd_construction_ids"));
        videoList.add(new WuTieVideo("2", "车载视频", "cz_construction_ids"));
        videoList.add(new WuTieVideo("3", "无人机视频", "wr_construction_ids"));
        for (WuTieVideo video : videoList) {
            String constructionIds = ResourceUtil.getConfigByName(video.getConstructionIds());
            if (StringUtils.isNotBlank(constructionIds)) {
                TreeEntity entity = new TreeEntity();
                entity.setId(video.getId());
                entity.setName(video.getName());
                entity.setpId("0");
                entity.setIcon(areaIcon);
                entity.setOpen(true);
                list.add(entity);

                String[] ids = constructionIds.split(",");
                for (String constructionId : ids) {
                    Map<String, Object> map = constructionService.queryConstructionById(constructionId);

                    TreeEntity constructionEntity = new TreeEntity();
                    constructionEntity.setId(constructionId);
                    constructionEntity.setName((String) map.get("name"));
                    constructionEntity.setpId(video.getId());
                    constructionEntity.setIcon(constructionOnlineIcon);
//        			if (nvrMap.containsKey(constructionId)) {
//        				NvrVO nvrVO = nvrMap.get(constructionId);
//        				constructionEntity.setNvrVO(nvrVO);
//        			}
                    list.add(constructionEntity);

                    List<TreeEntity> cameraList = cameraTreeMap.get(constructionId);
                    if (!CollectionUtils.isEmpty(cameraList)) {
                        list.addAll(cameraList);
                    }
                }
            }

        }
        return list;
    }

    private class WuTieVideo {
        private String id;
        private String constructionIds;
        private String name;

        public WuTieVideo(String id, String name, String constructionIds) {
            this.id = id;
            this.name = name;
            this.constructionIds = constructionIds;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConstructionIds() {
            return constructionIds;
        }

        public void setConstructionIds(String constructionIds) {
            this.constructionIds = constructionIds;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    /**
     * @param entity
     * @return
     * @desc NVR配置信息
     * @author dwj
     * @date 2018/3/20 9:52
     */
    @Transactional
    public void saveNvr(NvrDeployEntity entity) {
        List<CameraEntity> list = new ArrayList<CameraEntity>();
        try {
            String id = entity.getId();
            if (StringUtils.isEmpty(id)) {
                entity.setId(UuidUtils.getUuid());
                entity.setAppPort(entity.getAppPort());
                entity.setWebPort(entity.getPort());
                cameraMapper.save(entity);
                List<CameraEntity> cameraEntities = entity.getCameraEntityList();
                if (!CollectionUtils.isEmpty(cameraEntities)) {
                    for (CameraEntity cameraEntity : cameraEntities) {
                        cameraEntity.setStatus(1);
                        cameraEntity.setConstructionId(entity.getConstructionId());
                        cameraEntity.setConstructionName(entity.getName());
                        list.add(cameraEntity);
                    }
                    cameraMapper.batchSave(list);
                }
            } else {
                //获取数据库有的通道数
                List<String> ids = cameraMapper.selectCameraByConstructionId(entity.getConstructionId());
                List<CameraEntity> entityList = entity.getCameraEntityList();
                NvrVO vo = new NvrVO();
                vo.setId(entity.getId());
                vo.setIp(entity.getIp());
                vo.setPassword(entity.getPassword());
                vo.setUserName(entity.getUserName());
                vo.setAppPort(entity.getAppPort());
                vo.setPort(entity.getPort());
                cameraMapper.updateNvr(vo);
                for (CameraEntity cameraEntity : entityList) {
                    String cameraId = cameraEntity.getId();
                    if (StringUtils.isNotBlank(cameraId)) {
                        //判断数据库通道号是否包含前端传过来的通道号
                        if (ids.contains(cameraId)) {
                            cameraMapper.updateCamera(cameraEntity);
                            //移除摄像头Id
                            ids.remove(cameraId);
                        }

                    } else {
                        cameraEntity.setId(UuidUtils.getUuid());
                        cameraEntity.setConstructionName(entity.getName());
                        cameraEntity.setConstructionId(entity.getConstructionId());
                        cameraEntity.setStatus(1);
                        list.add(cameraEntity);
                    }
                }

                //批量添加摄像头
                if (!CollectionUtils.isEmpty(list)) {
                    cameraMapper.batchSave(list);
                }
                //批量删除摄像头
                if (!CollectionUtils.isEmpty(ids)) {
                    cameraMapper.delete(ids);
                }
            }
        } catch (Exception e) {
            throw new EscstException("新增NVR配置信息失败");
        }
    }


    /**
     * @param vo
     * @return
     * @desc 获取NVR信息列表
     * @author dwj
     * @date 2018/3/26 16:12
     */
    public PageVo getNvr(NvrDeployEntity vo) {
        PageVo pageVo = new PageVo();
        try {
            int count = cameraMapper.getCountNvr(vo);
            // 当前页
            pageVo.setCurrentPage(vo.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(vo.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (vo.getPage() - 1) * (vo.getRowNum());
            vo.setStartIndex(startIndex);
            List<NvrVO> getNvrList = cameraMapper.getNvr(vo);
            if (!CollectionUtils.isEmpty(getNvrList)) {
                for (NvrVO nvrVO : getNvrList) {
                    List<CameraVo> entityList = cameraMapper.queryCameraById(nvrVO.getConstructionId());
                    nvrVO.setVos(entityList);
                }
            }

            pageVo.setRows(getNvrList);

        } catch (Exception e) {
            throw new EscstException("查询Nvr列表异常" + e.getMessage(), e);
        }
        return pageVo;
    }


    public NvrVO getNvrById(NvrDeployEntity entity) {
        NvrVO vo = new NvrVO();
        try {
            vo = cameraMapper.queryById(entity);
            List<CameraVo> vos = cameraMapper.queryCameraById(vo.getConstructionId());
            vo.setVos(vos);
        } catch (Exception e) {
            throw new EscstException("查询Nvr列表异常" + e.getMessage(), e);
        }
        return vo;
    }


    @Transactional
    public void saveRloeCamera(RoleCameraEntity entity) {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            List<String> ids = new ArrayList<String>();
            List<String> list = new ArrayList<String>();
            if (StringUtils.isEmpty(entity.getId())) {
                List<String> fristCameraIds = entity.getFirstCameraIds();
                List<String> lastCameraIds = entity.getLastCameraIds();
                for (String cameraId : lastCameraIds) {
                    if (!fristCameraIds.contains(cameraId)) {
                        list.add(cameraId);
                    }
                }
                for (String cameraId : fristCameraIds) {
                    if (!lastCameraIds.contains(cameraId)) {
                        ids.add(cameraId);

                    }
                }

                if (!CollectionUtils.isEmpty(ids)) {
                    paramMap.put("list", ids);
                    paramMap.put("roleId", entity.getRoleId());
                    cameraMapper.deleteRoleCamera(paramMap);
                }

                if (!CollectionUtils.isEmpty(list)) {
                    for (String cameraId : list) {
                        entity.setId(UuidUtils.getUuid());
                        entity.setCameraId(cameraId);
                        entity.setCreateTime(new Date());
                        cameraMapper.saveRoleCamera(entity);
                    }
                }
            }
        } catch (Exception e) {
            throw new EscstException("新增角色通道异常" + e.getMessage(), e);
        }
    }

    public List<RoleCameraVo> selectCameraByUserId(String userId, RoleCameraEntity roleCameraEntity) {
        List<RoleCameraVo> rst = new ArrayList<RoleCameraVo>();
        Map<String, List<TreeEntity>> map = new HashMap<String, List<TreeEntity>>();
        try {
            List<CameraEntity> list = cameraMapper.selectCameraByUser(userId);
            if (!CollectionUtils.isEmpty(list)) {
                for (CameraEntity entity : list) {
                    String constructionId = entity.getConstructionId();
                    TreeEntity cameraTree = new TreeEntity();
                    cameraTree.setId(entity.getId());
                    cameraTree.setpId(constructionId);
                    cameraTree.setName(entity.getName());
                    cameraTree.setOpen(false);
                    cameraTree.setChannelNo(entity.getChannelNo());
                    cameraTree.setFlag(entity.getFlag());
                    List<String> cameraIds = cameraMapper.selectRoleCameraList(roleCameraEntity.getRoleId());
                    if (cameraIds.contains(entity.getId())) {
                        cameraTree.setChecked(true);
                    }
                    if (map.containsKey(constructionId)) {
                        List<TreeEntity> treeList = map.get(constructionId);
                        treeList.add(cameraTree);
                    } else {
                        List<TreeEntity> treeList = new ArrayList<TreeEntity>();
                        treeList.add(cameraTree);
                        map.put(constructionId, treeList);
                    }

                }
            }
            for (String key : map.keySet()) {
                RoleCameraVo roleCameraVo = new RoleCameraVo();
                roleCameraVo.setId(key);
                roleCameraVo.setName(constructionMapper.queryById(key).getName());
                roleCameraVo.setChildren(map.get(key));
                rst.add(roleCameraVo);
            }

        } catch (Exception e) {
            throw new EscstException("根据用户查询摄像通道异常" + e.getMessage(), e);
        }
        return rst;
    }

    /**
     * @return
     * @desc 判断当前用户所属工地是否配置8700登录信息
     * @author caozx
     * @date 2018年5月29日 上午11:14:35
     */
    public boolean isExistsHik8700Config() throws Exception {
        UserEntity user = ContextUtils.getCurrentUser();
        List<SimpleConstructionVO> list = user.getConstructionList();
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        SimpleConstructionVO vo = list.get(0);
        VideoLoginVO platform = hikPlatformMapper.getPlatform(vo.getId());
        if (platform == null) {
            return false;
        }
        return true;
    }

}

