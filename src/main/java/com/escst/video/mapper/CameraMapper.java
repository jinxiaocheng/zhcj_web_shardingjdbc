package com.escst.video.mapper;

import java.util.List;
import java.util.Map;

import com.escst.video.entity.NvrDeployEntity;
import com.escst.video.entity.RoleCameraEntity;
import com.escst.video.vo.CameraVo;
import com.escst.video.vo.NvrVO;
import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.video.entity.CameraEntity;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 16:32
 */
@Repository
public interface CameraMapper extends BaseMapper<CameraEntity> {

	/**
	 * @desc 查询所有有效工地的摄像头
	 * @return 
	 * @author zhouwei
	 * @date 2017年8月2日 上午11:30:20
	 */
	List<CameraEntity> selectCameraList(String roleId);

	List<NvrVO> getNvr(NvrDeployEntity entity);

	int getCountNvr(NvrDeployEntity entity);
	
	/**
	 * @desc 查询所有的NVR
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月10日 下午9:33:39
	 */
	List<NvrVO> selectNvr();

	/**
	 * @desc 查询需要导入的nvr信息
	 * @return:
	 * @author caozx
	 * @date  2018/1/10 14:10
	 */
	List<NvrVO> selectExistsNvrList();

	NvrVO queryById(NvrDeployEntity entity);

	List<CameraVo> queryCameraById(String constructionId);


	/**
	 * @desc   批量保存通道信息
	 * @return:
	 * @author caozx
	 * @date  2018/1/10 14:10
	 */
	void batchSave(List<CameraEntity> list);

	/**
	* @desc 保存NVR信息
	* @param nvrDeployEntity
	* @return
	* @author dwj
	* @date 2018/3/19 14:16
	*/
	void save(NvrDeployEntity nvrDeployEntity);

	void updateNvr(NvrVO vo);

	void updateCamera(CameraEntity entity);

	void delete(List<String> ids);

	void saveRoleCamera(RoleCameraEntity entity);

	void deleteRoleCamera(Map<String,Object> map);

	List<String> selectCameraByConstructionId(String constructionId);

	List<CameraEntity> selectCameraByUser(String userId);

	List<String> selectRoleCameraList(String roleId);

}
