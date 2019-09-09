package com.escst.route;

import com.escst.commons.utils.ContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc 
 * @author kz
 * @date 2018年7月25日 下午3:03:55
 */
@Controller
@RequestMapping("route")
public class RouteController {

//++++++++++++++++++++++++++++++++++++++实名制+++++++++++++++++++++++++++++++++++
	//================================首页统计页面
	/**
	 * @desc 实名制首页统计页面
	 * @return
	 * @author kz
	 * @date 2018年7月25日 下午3:05:42
	 */
	@RequestMapping("/smz/main/toindex")
	public ModelAndView toszmlist() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/main/index");
		return mv;
	}

  //================================人员页面
	/**
	 * @desc 实名制人员列表页面
	 * @return 
	 * @author kz
	 * @date 2018年7月25日 下午3:05:42
	 */
	@RequestMapping("/smz/person/tolist")
	public ModelAndView toPlist() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/person/list");
		return mv;
	}
	@RequestMapping("/smz/person/toView")
	public ModelAndView toPView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/person/view");
		return mv;
	}
  //================================培训页面
	/**
	 * @desc 实名制培训列表页面
	 * @return 
	 * @author kz
	 * @date 2018年7月25日 下午3:05:42
	 */
	@RequestMapping("/smz/training/tolist")
	public ModelAndView toTlist() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/training/list");
		return mv;
	}
	@RequestMapping("/smz/training/toView")
	public ModelAndView toTView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/training/view");
		return mv;
	}
	//================================统计查询
	/**
	 * @desc 实名制统计查询页面
	 * @return
	 * @author kz
	 * @date 2018年7月25日 下午3:05:42
	 */
	@RequestMapping("/smz/count/tolist")
	public ModelAndView toCount() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/smz/count/list");
		return mv;
	}

	//================================消息推送
	/**
	 * @desc 消息推送查看页面
	 * @return
	 * @author kz
	 * @date 2018/8/7 11:24
	 */
	@RequestMapping("/message/viewList")
	public ModelAndView toviewList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/message/viewList");
		return mv;
	}


//++++++++++++++++++++++++++++++++++++++南钢+++++++++++++++++++++++++++++++++++
	//================================主页跳转
	/**
	 * @desc 主页跳转
	 * @return
	 * @author kz
	 * @date 2018/8/7 11:24
	 */
	@RequestMapping("/homepage")
	public ModelAndView homepage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/homepage/homepage");
		return mv;
	}

//++++++++++++++++++++++++++++++++++++++高支模+++++++++++++++++++++++++++++++++++
	/**
	 * @desc 实时数据
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowData")
	public ModelAndView nowData() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowData");
		return mv;
	}
	/**
	 * @desc 实时数据-预警数据
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowDataWarningData")
	public ModelAndView nowDataWarning() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowDataWarningData");
		return mv;
	}
	/**
	 * @desc 实时数据-趋势图
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowDataLineChart")
	public ModelAndView nowDataLineChart() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowDataLineChart");
		return mv;
	}
	/**
	 * @desc 实时数据-历史数据
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowDataHistoryData")
	public ModelAndView nowDataHistoryData() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowDataHistoryData");
		return mv;
	}
	/**
	 * @desc 实时数据-测点部署图
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowDataTestPicture")
	public ModelAndView nowDataTestPicture() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowDataTestPicture");
		return mv;
	}
	/**
	 * @desc 实时数据-查看阈值
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/nowDataViewWarning")
	public ModelAndView nowDataViewWarning() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/nowDataViewWarning");
		return mv;
	}

	/**
	 * @desc 流水段维护
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/lsd")
	public ModelAndView lsd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gzm/lsd");
		return mv;
	}

	/**
	 * @desc 流水段维护-新增
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/lsdAdd")
	public ModelAndView lsdAdd() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/lsdAdd");
		return mv;
	}

	/**
	 * @desc 流水段维护-查看
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/lsdView")
	public ModelAndView lsdView() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/lsdView");
		return mv;
	}

	/**
	 * @desc 采集仪维护
	 * @return
	 * @author  kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/cjy")
	public ModelAndView cjy() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gzm/cjy");
		return mv;
	}

	/**
	 * @desc 采集仪维护-新增
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/gzm/cjyAdd")
	public ModelAndView cjyAdd() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/gzm/cjyAdd");
		return mv;
	}
//++++++++++++++++++++++++++++++++++++++吊钩监测+++++++++++++++++++++++++++++++++++
	/**
	 * @desc 数据监测
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/data")
	public ModelAndView liftingData() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/data");
		return mv;
	}

	/**
	 * @desc 数据监测-历史数据
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/dataHistory")
	public ModelAndView liftingDataHistory() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/dataHistory");
		return mv;
	}

	/**
	 * @desc 可视化监控
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/video")
	public ModelAndView liftingVideo() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/video");
		return mv;
	}

	/**
	 * @desc 可视化配置
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/visual")
	public ModelAndView liftingVisual() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/visual");
		return mv;
	}

	/**
	 * @desc 可视化配置-新增
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/visualAdd")
	public ModelAndView liftingVisualAdd() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/visualAdd");
		return mv;
	}

	/**
	 * @desc 可视化配置-查看
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/visualView")
	public ModelAndView liftingVisualView() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/visualView");
		return mv;
	}

	/**
	 * @desc 设备登记
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/equipment")
	public ModelAndView liftingEquipment() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/equipment");
		return mv;
	}

	/**
	 * @desc 设备登记-新增
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/equipmentAdd")
	public ModelAndView liftingEquipmentAdd() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/equipmentAdd");
		return mv;
	}

	/**
	 * @desc 设备登记-查看
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifting/equipmentView")
	public ModelAndView liftingEquipmentView() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifting/equipmentView");
		return mv;
	}

//++++++++++++++++++++++++++++++++++++++设备管理+++++++++++++++++++++++++++++++++++
	/**
	 * @desc 设备登记-查看
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/equipment/liftMonitor")
	public ModelAndView liftMonitor() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/equipment/liftMonitor");
		return mv;
	}

	/**
	 * @desc 设备检查-升降机-历史数据
	 * @return
	 * @author kz
	 * @date 2018/10/9 13:14
	 */
	@RequestMapping("/lifter/historyList")
	public ModelAndView liftHistoryList() {
		String userId = ContextUtils.getCurrentUserId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/lifter/historyList");
		return mv;
	}
}


