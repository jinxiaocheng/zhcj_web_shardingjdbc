package com.escst.hook.mapper;

import java.util.List;
import java.util.Map;

import com.escst.commons.vo.BaseVO;
import com.escst.hook.entity.HookCameraEntity;
import com.escst.hook.entity.HookEquipmentEntity;
import com.escst.hook.entity.HookNvrEntity;
import com.escst.hook.vo.HookEquipmentVo;
import org.springframework.stereotype.Repository;

/**
 * @desc 吊钩
 * @author zhouwei
 * @date 2017年9月13日 下午7:15:02
 */
@Repository
public interface HookMapper {

	/**
	 * @desc 将升降机采集的数据复制给吊钩采集的数据
	 * @param params 
	 * @author zhouwei
	 * @date 2017年9月20日 下午4:30:20
	 */
	void copyRealtime(Map<String, Object> params);

	void insertEquipment(HookEquipmentEntity equipmentEntity);

	void updateEquipment(HookEquipmentEntity equipmentEntity);


	List<HookEquipmentVo> selectHookEquipmentList(Map<String,Object> map);

	int HookEquipmentCount(Map<String,Object> map);

	HookEquipmentVo selectHookEquipmentDetail(String id);

	List<BaseVO> selectHookEquipmentByConstructionId(String constructionId);

	List<String> selectCameraIds(Map<String,String> map);

	void insertHookNvr(HookNvrEntity entity);

	void updateHookNvr(HookNvrEntity entity);

	void batchInsertHookCamera(List<HookCameraEntity> entities);

	void batchUpdateHookCamera(List<HookCameraEntity> entities);

	void batchDeleteHookCamera(List<String> ids);

	List<HookNvrEntity> selectHookNvrList(Map<String,Object> map);

	int selectHookNvrCount(Map<String,Object> map);

	List<HookCameraEntity> selectHookCameraList(String constructionId);

	HookNvrEntity selectNvrDetail(String id);

	String selectEquipmentName(String id);



}
