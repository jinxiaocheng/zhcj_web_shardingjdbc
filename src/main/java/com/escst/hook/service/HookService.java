package com.escst.hook.service;

import java.util.*;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.PageVo;
import com.escst.hook.bean.HookCameraBean;
import com.escst.hook.entity.HookCameraEntity;
import com.escst.hook.entity.HookEquipmentEntity;
import com.escst.hook.entity.HookNvrEntity;
import com.escst.hook.vo.HookEquipmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.hook.mapper.HookMapper;
import org.springframework.util.CollectionUtils;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年9月13日 下午5:57:23
 */
@Service
public class HookService {

	@Autowired
	private HookMapper hookMapper;
	
	/**
	 * @desc 将升降机采集的数据复制给吊钩采集的数据
	 * @param hookEquipmentId 吊钩的设备ID
	 * @param towercraneEquipmentId 升降机的设备ID
	 * @author zhouwei
	 * @date 2017年9月20日 下午4:29:07
	 */
	public void copyRealtime(String hookEquipmentId, String towercraneEquipmentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hookEquipmentId", hookEquipmentId);
		params.put("towercraneEquipmentId", towercraneEquipmentId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		params.put("startDate", cal.getTime());//当前时间向前推10分钟
		params.put("endDate", towercraneEquipmentId);
		
		hookMapper.copyRealtime(params);
	}

	/**
	 * @desc 吊钩设备新增
	 * @param equipmentEntity
	 * @return
	 * @author dwj
	 * @date 19/10/2018 10:27
	 */
	@Transactional
	public void saveHookEquipment(HookEquipmentEntity equipmentEntity){
		try{
			String id = equipmentEntity.getId();
			if(StringUtils.isBlank(id)){
				id = UuidUtils.getUuid();
				equipmentEntity.setId(id);
				equipmentEntity.setStatus(1);
				equipmentEntity.setCreateTime(new Date());
				hookMapper.insertEquipment(equipmentEntity);
			}else {
				equipmentEntity.setUpdateTime(new Date());
				hookMapper.updateEquipment(equipmentEntity);
			}
		}catch (Exception e){
			throw new EscstException("新增或修改吊钩设备异常"+e.getMessage(),e);
		}
	}


	/**
	 * @desc 获取当前项目下吊钩设备
	 * @param equipmentEntity
	 * @return
	 * @author dwj
	 * @date 19/10/2018 10:38
	 */
	public PageVo getAllHookEquipmentByConstruction(HookEquipmentEntity equipmentEntity){
		PageVo vo = new PageVo();
		List<HookEquipmentVo> list = new ArrayList<HookEquipmentVo>();
		try{
			int pageNum = equipmentEntity.getPage();
			int pageSize = equipmentEntity.getRowNum();
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("name", equipmentEntity.getName());
			paraMap.put("offset", offset);
			paraMap.put("rows", pageSize);
			paraMap.put("number", equipmentEntity.getNumber());
			paraMap.put("areaId", equipmentEntity.getAreaId());
			paraMap.put("constructionId", equipmentEntity.getConstructionId());
			int count = hookMapper.HookEquipmentCount(paraMap);
			if(count > 0){
				list = hookMapper.selectHookEquipmentList(paraMap);
			}
			//封装到pagevo
			if (!CollectionUtils.isEmpty(list)) {
				vo.setTotalRecord(count);
				vo.setRows(list);
			}
		}catch (Exception e){
			throw new EscstException("获取当前项目下吊钩设备异常"+e.getMessage(),e);
		}
		return vo;
	}

	/**
	 * @desc 获取吊钩设备详情
	 * @param id
	 * @return
	 * @author dwj
	 * @date 19/10/2018 10:57
	 */
	public HookEquipmentVo getHookEquipmentDetail(String id){
		HookEquipmentVo vo = new HookEquipmentVo();
		try{
			vo =hookMapper.selectHookEquipmentDetail(id);
		}catch (Exception e){
			throw new EscstException("获取吊钩设备详情异常"+e.getMessage(),e);
		}
		return vo;
	}

	/**
	* @desc 获取当前项目下有效吊钩设备
	* @param constructionId
	* @return
	* @author dwj
	* @date 22/10/2018 14:11
	*/
	public List<BaseVO> getVaildHookEquipmet(String constructionId){
		List<BaseVO> vos = new ArrayList<BaseVO>();
		try{
			vos = hookMapper.selectHookEquipmentByConstructionId(constructionId);
		}catch (Exception e){
			throw new EscstException("获取当前项目下有效吊钩设备异常"+e.getMessage(),e);
		}
		return vos;
	}


	/**
	* @desc 新增吊钩NVr配置信息
	* @param entity
	* @return
	* @author dwj
	* @date 23/10/2018 10:28
	*/
	@Transactional
	public void save(HookNvrEntity entity){
		List<HookCameraEntity> entities = new ArrayList<HookCameraEntity>();
		List<HookCameraEntity> updateEntities = new ArrayList<HookCameraEntity>();
		List<String> ids = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("constructionId", entity.getConstructionId());
		String id = entity.getId();
		String constructionName = entity.getConstructionName();
		String constructionId = entity.getConstructionId();
		if (StringUtils.isBlank(id)) {
			int count = hookMapper.selectHookNvrCount(map);
			if (count > 0) {
				throw new EscstException("此工地Nvr信息已经录入，请勿重复输入");
			}
			id = UuidUtils.getUuid();
			entity.setId(id);
			entity.setPort(entity.getWebPort());
			entity.setCreateTime(new Date());
			entity.setName(entity.getConstructionName());
			hookMapper.insertHookNvr(entity);
			List<HookCameraBean> list = entity.getList();
			if (!CollectionUtils.isEmpty(list)) {
				for (HookCameraBean bean : list) {
					String equipmentId = bean.getEquipmentId();
					List<HookCameraEntity> cameraEntities = bean.getList();
					for (HookCameraEntity cameraEntity : cameraEntities) {
						cameraEntity.setId(UuidUtils.getUuid());
						cameraEntity.setConstructionId(constructionId);
						cameraEntity.setConstructionName(constructionName);
						cameraEntity.setEquipmentId(equipmentId);
						cameraEntity.setStatus(1);
						cameraEntity.setSupplier(1);
						cameraEntity.setCreateTime(new Date());
						entities.add(cameraEntity);
					}
				}
			}

		} else {
			entity.setPort(entity.getWebPort());
			hookMapper.updateHookNvr(entity);
			List<HookCameraBean> list = entity.getList();
			if (!CollectionUtils.isEmpty(list)) {
				for (HookCameraBean bean : list) {
					String equipmentId = bean.getEquipmentId();
					Map<String, String> dataMap = new HashMap<String, String>();
					dataMap.put("constructionId", constructionId);
					dataMap.put("equipmentId", equipmentId);
					ids = hookMapper.selectCameraIds(dataMap);
					List<HookCameraEntity> cameraEntities = bean.getList();
					for (HookCameraEntity cameraEntity : cameraEntities) {
						String cameraId = cameraEntity.getId();
						if (StringUtils.isBlank(cameraId)) {
							cameraEntity.setId(UuidUtils.getUuid());
							cameraEntity.setConstructionId(constructionId);
							cameraEntity.setConstructionName(constructionName);
							cameraEntity.setEquipmentId(equipmentId);
							cameraEntity.setStatus(1);
							cameraEntity.setSupplier(1);
							cameraEntity.setCreateTime(new Date());
							entities.add(cameraEntity);
						} else {
							if (ids.contains(cameraId)) {
								cameraEntity.setConstructionId(constructionId);
								cameraEntity.setConstructionName(constructionName);
								cameraEntity.setEquipmentId(equipmentId);
								cameraEntity.setStatus(1);
								cameraEntity.setSupplier(1);
								cameraEntity.setUpdateTime(new Date());
								updateEntities.add(cameraEntity);
								ids.remove(cameraId);
							}
						}

					}
					//批量修改
					if (!CollectionUtils.isEmpty(ids)) {
						hookMapper.batchDeleteHookCamera(ids);
					}
				}
			}
		}
		//批量新增
		if (!CollectionUtils.isEmpty(entities)) {
			hookMapper.batchInsertHookCamera(entities);
		}
		//批量修改
		if (!CollectionUtils.isEmpty(updateEntities)) {
			hookMapper.batchUpdateHookCamera(updateEntities);
		}
	}


	/**
	* @desc 获取Nvr信息
	* @param entity
	* @return
	* @author dwj
	* @date 24/10/2018 10:22
	*/
	public PageVo getNvrList(HookNvrEntity entity){
		PageVo vo = new PageVo();
		List<HookNvrEntity> entities = new ArrayList<HookNvrEntity>();
		try{
			int pageNum = entity.getPage();
			int pageSize = entity.getRowNum();
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("offset", offset);
			paraMap.put("rows", pageSize);
			paraMap.put("areaId", entity.getAreaId());
			paraMap.put("constructionId", entity.getConstructionId());
 			int count = hookMapper.selectHookNvrCount(paraMap);
			if(count >0){
				entities = hookMapper.selectHookNvrList(paraMap);
			}

			if(!CollectionUtils.isEmpty(entities)){
				vo.setRows(entities);
				vo.setTotalRecord(count);
			}
		}catch (Exception e){
			throw new EscstException("获取Nvr信息异常"+e.getMessage(),e);
		}
		return  vo;
	}


	/**
	* @desc 查看详情
	* @param id
	* @return
	* @author dwj
	* @date 24/10/2018 13:36
	*/
	public  HookNvrEntity getNvrDetail(String id){
		HookNvrEntity entity = new HookNvrEntity();
		try{
			entity = hookMapper.selectNvrDetail(id);
			if(entity != null){
				String  constructionId = entity.getConstructionId();
				List<HookCameraEntity> entities = hookMapper.selectHookCameraList(constructionId);
				List<HookCameraBean> beans = new ArrayList<HookCameraBean>();
				Map<String,List<HookCameraEntity>> map = new HashMap<String, List<HookCameraEntity>>();
				for(HookCameraEntity cameraEntity : entities){
					String equipmentId = cameraEntity.getEquipmentId();
					if(StringUtils.isNotBlank(equipmentId)){
						if(map.containsKey(equipmentId)){
							List<HookCameraEntity> cameraEntities = map.get(equipmentId);
							cameraEntities.add(cameraEntity);
						}else {
							List<HookCameraEntity> cameraEntities = new ArrayList<HookCameraEntity>();
							cameraEntities.add(cameraEntity);
							map.put(equipmentId,cameraEntities);
						}
					}

				}
				Iterator<Map.Entry<String, List<HookCameraEntity>>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					HookCameraBean bean = new HookCameraBean();
					Map.Entry<String, List<HookCameraEntity>> entry = it.next();
					bean.setList(entry.getValue());
					bean.setEquipmentName(hookMapper.selectEquipmentName(entry.getKey()));
					bean.setEquipmentId(entry.getKey());
					beans.add(bean);
				}
				entity.setList(beans);
			}

		}catch (Exception e){
			throw new EscstException("查看Nvr详情信息异常"+e.getMessage(),e);
		}
		return entity;
	}
}
