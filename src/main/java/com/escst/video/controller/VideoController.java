package com.escst.video.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.video.entity.NvrDeployEntity;
import com.escst.video.entity.RoleCameraEntity;
import com.escst.video.service.CameraService;
import com.escst.video.service.NvrInfoService;
import com.escst.video.service.NvrService;
import com.escst.video.vo.NvrVO;
import com.escst.video.vo.RoleCameraVo;

/**
 * @desc 视频
 * @author zhouwei
 * @date 2017年2月14日 下午2:05:38
 */
@Controller
@RequestMapping("video")
public class VideoController {

	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

	@Autowired
	private CameraService cameraService;

	@Autowired
	private NvrService nvrService;

	/**
	 * @desc 跳转到EasyNVR视频预览界面
	 * @return
	 * @author caozx
	 * @date 2017年3月9日 下午2:09:57
	 */
	@RequestMapping("toEasyNvrPreview")
	public ModelAndView toEasyNvrPreview(Model model) {
		return new ModelAndView("easyNVR/video");
	}


	@RequestMapping("toNvrConfigure")
	public ModelAndView toNvrConfigure(Model model) {
		return new ModelAndView("easyNVR/nvr_configure");
	}

	@RequestMapping("toNvrList")
	public ModelAndView toNvrList(Model model) {
		return new ModelAndView("easyNVR/nvr_list");
	}

	@RequestMapping("toAllot")
	public ModelAndView toAllot(Model model) {
		return new ModelAndView("video/allot_camera");
	}


	/**
	 * @desc 跳转到视频预览界面
	 * @return 
	 * @author zhouwei
	 * @date 2017年2月14日 下午2:09:57
	 */
	@RequestMapping("toPreview")
	public ModelAndView toPreview(Model model) {
		return new ModelAndView("video/preview");
	}

	/**
	 * @desc 获取树节点请求
	 * @author caozx
	 * @date 2017年2月16日 下午2:09:57
	 * @return
	 */
	@RequestMapping("preview/fetchTreeNodeList")
	@ResponseBody
	public ReturnJson fetchTreeNodeList() {
		ReturnJson returnJson = null;
		try {
			String userId = ContextUtils.getCurrentUserId();
			List<TreeEntity> list = cameraService.queryNodeListByUser(userId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(),e);
		}
		return returnJson;
	}

	/**
	 * @desc 查看视频
	 * @author caozx
	 * @date 2017年2月16日 下午2:09:57
	 * @return
	 */
	@RequestMapping("preview/fetchStartPreviewParam")
	@ResponseBody
	public ReturnJson fetchStartPreviewParam(String cameraId) {
		ReturnJson returnJson = null;
		try {
			String data = cameraService.queryPreviewXml(cameraId);
			returnJson = ReturnJson.success(data);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("查看视频异常：");
			logger.error("查看视频异常：" + e.getMessage(),e);
		}
		return returnJson;
	}

	/**
	 * 跳转到nvr视频预览页面
	 * @param model
	 * @return
	 */
	@RequestMapping("toNvrPreview")
	public ModelAndView toNvrPreview(Model model) {
		ModelAndView modelAndView = new ModelAndView("video/preview_nvr");
		try {
			//判断该用户所关联的工地视频登录信息是nvr还是87000
			if(nvrService.isExistsHik8700Config()){
				modelAndView = new ModelAndView("video/preview");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * @desc 获取树节点请求
	 * @author caozx
	 * @date 2017年8月24日 下午2:09:57
	 * @return
	 */
	@RequestMapping("nvrPreview/fetchTreeNodeList")
	@ResponseBody
	public ReturnJson nvrFetchTreeNodeList(String cityId) {
		ReturnJson returnJson = null;
		try {
			String userId = ContextUtils.getCurrentUserId();
			List<TreeEntity> list = nvrService.queryVideoConstructionTree(userId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(),e);
		}
		return returnJson;
	}

	/**
	 * 跳转到nvr视频预览页面
	 * @param model
	 * @return
	 */
	@RequestMapping("escst/toNvrPreview")
	public ModelAndView toEscstNvrPreview(Model model) {
		ModelAndView modelAndView = new ModelAndView("video/preview_nvr_escst");
		return modelAndView;
	}

	/**
	 * @desc 获取树节点请求
	 * @author caozx
	 * @date 2017年8月24日 下午2:09:57
	 * @return
	 */
	@RequestMapping("escst/nvrPreview/fetchTreeNodeList")
	@ResponseBody
	public ReturnJson escstNvrFetchTreeNodeList() {
		ReturnJson returnJson = null;
		try {
			String userId = ContextUtils.getCurrentUserId();
			List<TreeEntity> list = nvrService.queryWuTieTree(userId);
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取树节点信息异常：");
			logger.error("获取树节点信息异常：" + e.getMessage(),e);
		}
		return returnJson;
	}
	
	/**
	 * @desc 使用8700平台查看武铁的视频
	 * @param model
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月20日 下午7:11:14
	 */
	@RequestMapping("escst/platform/preview")
	public ModelAndView toPlatformPreview(Model model) {
		ModelAndView modelAndView = new ModelAndView("video/preview_wutie");
		return modelAndView;
	}

	@Autowired
	private NvrInfoService nvrInfoService;

	/**
	 * @param
	 * @return:
	 * @author caozx
	 * @date: 2018/1/10 14:04
	 */
	@RequestMapping("escst/toList")
	public ModelAndView toView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("video/nvr_list");
		return modelAndView;
	}

	/**
	 * @desc   获取需要导入的nvr数据
	 * @param
	 * @return
	 * @author caozx
	 * @date 2018/1/12 13:21
	 */
	@RequestMapping("escst/queryNvrList")
	@ResponseBody
	public ReturnJson queryNvrList() {
		ReturnJson returnJson = null;
		try {
			//查询nvr信息
			List<NvrVO> list =  nvrInfoService.queryNvrList();
			returnJson = ReturnJson.success(list);
		} catch (Exception e) {
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
	}

	/**
	 * @desc   导入nvr通道数据
	 * @param  data : nvr通道数据
	 * @return
	 * @author caozx
	 * @date 2018/1/12 13:22
	 */
	@RequestMapping("escst/saveNvr")
	@ResponseBody
	public ReturnJson saveNvr(String data) {
		ReturnJson returnJson = null;
		try {
			nvrInfoService.saveNvrChannel(data);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
	}

	/**
	* @desc 配置NVR信息
	* @param entity
	* @return
	* @author dwj
	* @date 2018/3/19 14:38
	*/
	@RequestMapping("saveNvrDeploy")
	@ResponseBody
	public ReturnJson saveNvrDeploy(@RequestBody NvrDeployEntity entity){
		ReturnJson returnJson = null;
		try{
			nvrService.saveNvr(entity);
			returnJson = ReturnJson.success();
		}catch (Exception e){
			returnJson = ReturnJson.fail("添加NVR配置信息失败");
		}
		return returnJson;
	}

	/**
	* @desc 获取Nvr信息
	* @param entity
	* @return
	* @author dwj
	* @date 2018/3/26 16:20
	*/
	@RequestMapping("getNvrList")
	@ResponseBody
	public ReturnJson getNvrList(NvrDeployEntity entity){
		ReturnJson returnJson = null;
		try{
			PageVo vo = nvrService.getNvr(entity);
			returnJson = ReturnJson.success(vo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("查看Nvr配置信息失败");
			logger.error("获取Nvr信息异常"+e.getMessage(),e);
		}
		return  returnJson;
	}

	@RequestMapping("getNvrById")
	@ResponseBody
	public ReturnJson getNvrById(NvrDeployEntity entity){
		ReturnJson returnJson = null;
		try{
			NvrVO vo = nvrService.getNvrById(entity);
			returnJson = ReturnJson.success(vo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("查看Nvr配置信息失败");
			logger.error("获取Nvr信息异常"+e.getMessage(),e);
		}
		return  returnJson;
	}



	/**
	* @desc 用户分配视频
	* @param entity
	* @return
	* @author dwj
	* @date 2018/5/2 16:02
	*/
	@RequestMapping("saveRoleCamera")
	@ResponseBody
	public ReturnJson saveRoleCamera(@RequestBody RoleCameraEntity entity){
		ReturnJson returnJson = null;
		try{
			nvrService.saveRloeCamera(entity);
			returnJson = ReturnJson.success();
		}catch (Exception e){
			returnJson = ReturnJson.fail("用户分配视频失败");
			logger.error("用户分配视频异常"+e.getMessage(),e);

		}
		return returnJson;
	}


	/**
	* @desc 获取用户下的工地视频通道
	* @param
	* @return
	* @author dwj
	* @date 2018/5/2 16:15
	*/
	@RequestMapping("getCameraByUserId")
	@ResponseBody
	public ReturnJson getCameraByUserId(@RequestBody RoleCameraEntity entity){
		ReturnJson returnJson = null;
		try{
			String userId = ContextUtils.getCurrentUserId();
			List<RoleCameraVo> list = nvrService.selectCameraByUserId(userId,entity);
			returnJson = ReturnJson.success(list);
		}catch (Exception e){
			returnJson = ReturnJson.fail("获取用户下的工地视频通道失败");
			logger.error("获取用户下的工地视频通道"+e.getMessage(),e);

		}
		return returnJson;
	}

}
