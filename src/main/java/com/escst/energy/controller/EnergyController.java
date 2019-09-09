package com.escst.energy.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.vo.ConstructionPageVO;
import com.escst.energy.entity.EnergyEntity;
import com.escst.energy.service.ElectricService;
import com.escst.energy.service.WaterService;
import com.escst.energy.vo.ChartResultVO;
import com.escst.energy.vo.DateQtyVO;
import com.escst.energy.vo.EnergyQueryVO;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.service.EquipmentManagerService;
import com.escst.equipment.vo.QueryVO;

/**
 * @author dwj
 * @desc
 * @date 10:15 2017/9/13
 */
@Controller
@RequestMapping("energyMonitor")
public class EnergyController {

    private static final Logger logger = LoggerFactory.getLogger(EnergyController.class);

    @Autowired
    private ElectricService electricService;
    
    @Autowired
    private WaterService waterService;
    
    @Autowired
    private EquipmentManagerService equipmentManagerService;

    @RequestMapping("getEnergy")
    @ResponseBody
    public ReturnJson getEnergy(@RequestBody EnergyEntity entity) {
        ReturnJson returnJson = null;
        try {
            ChartResultVO chartResultVO = null;
            // equipmentType 1 电能 2水能
            if (entity.getEquipmentType() == 1) {
                chartResultVO = electricService.queryChartResult(entity);
            } else if (entity.getEquipmentType() == 2) {
                chartResultVO = waterService.queryChartResult(entity);
            }
            returnJson = ReturnJson.success(chartResultVO);

        } catch (Exception e) {
            logger.error("获取能耗数据异常:" + e.getMessage(), e);
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("energy/list");
        return mv;
    }

    /**
     * @desc 得到有能耗监测的工地
     * @param vo
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 上午9:55:15
     */
	@RequestMapping("/queryEnergyConstruction")
	@ResponseBody
	public ReturnJson queryEnergyConstruction(ConstructionPageVO vo) {
		ReturnJson returnJson = null;
		try {
			QueryVO queryVO = new QueryVO();
			BeanUtils.copyProperties(queryVO, vo);
			queryVO.setUserId(ContextUtils.getCurrentUserId());
			// 查询有水表和电表的工地
			String types = EquipmentTypeEnum.ELECTRIC.getValue() + "," + EquipmentTypeEnum.WATER.getValue();
			queryVO.setTypes(types);
			PageVo pageVo = equipmentManagerService.queryAuthConstructionList(queryVO);
			returnJson = ReturnJson.success(pageVo);
		}
		catch (Exception e) {
			logger.error("有能耗监测的工地异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
	}

    /**
     * @desc 水表、电表的图形界面
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 上午10:09:51
     */
    @RequestMapping("/toChart")
    public ModelAndView toChart(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("energy/chart");
        mv.addObject("constructionId", request.getParameter("id"));
        return mv;
    }
    
    /**
     * @desc 查看电、水每天的消耗数据
     * @param request
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 上午11:25:14
     */
    @RequestMapping("/toDataList")
    public ModelAndView toDataList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("energy/dataList");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
		String currentDate = DateUtils.format(cal.getTime(), DateUtils.TO_DATE);
        mv.addObject("constructionId", request.getParameter("id"));
        mv.addObject("startDate", currentDate);
        mv.addObject("endDate", currentDate);
        return mv;
    }
    
    /**
     * @desc 获取水表或者电表的数据
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午3:54:36
     */
    @RequestMapping("/queryDataList")
	@ResponseBody
	public ReturnJson queryDataList(EnergyQueryVO queryVO) {
    	ReturnJson returnJson = null;
		try {
			PageVo pageVo = null;
			int type = queryVO.getEquipmentType();
			if (type == EquipmentTypeEnum.ELECTRIC.getValue()) {
				pageVo = electricService.queryEnergyListPage(queryVO);
			} else if (type == EquipmentTypeEnum.WATER.getValue()) {
				pageVo = waterService.queryEnergyListPage(queryVO);
			}
			else {
				throw new EscstException("设备类型不正确");
			}
			returnJson = ReturnJson.success(pageVo);
		}
		catch (Exception e) {
			logger.error("获取水表或者电表的数据异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
    }
    
    /**
     * @desc 导出excel
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月22日 上午10:30:07
     */
    @RequestMapping("/exportExcel")
	@ResponseBody
	public ReturnJson exportExcel(EnergyQueryVO queryVO) {
    	ReturnJson returnJson = null;
		try {
			queryVO.setRowNum(Integer.MAX_VALUE);//导出就查询所有符合条件的数据,不用做分页
			List<DateQtyVO> list = null;
			String title = null;
			int type = queryVO.getEquipmentType();
			if (type == EquipmentTypeEnum.ELECTRIC.getValue()) {
				list = electricService.queryEnergyList(queryVO);
				title = "电量消耗(度)";
			} else if (type == EquipmentTypeEnum.WATER.getValue()) {
				list = waterService.queryEnergyList(queryVO);
				title = "水消耗(吨)";
			}
			else {
				throw new EscstException("设备类型不正确");
			}
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("title", title);
			beanParams.put("list", list);
			String filePath = ExcelUtils.createExcel("energy_day.xls", beanParams);
			returnJson = ReturnJson.success(filePath);
		}
		catch (Exception e) {
			logger.error("导出excel异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
    }
}
