package com.escst.energy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.energy.entity.EnergyEntity;
import com.escst.energy.mapper.EnergyMapper;
import com.escst.energy.vo.ChartResultVO;
import com.escst.energy.vo.DateQtyVO;
import com.escst.energy.vo.EnergyQueryVO;
import com.escst.equipment.service.EquipmentManagerService;
import com.escst.equipment.vo.SimpleEquipmentVO;

/**
 * @author dwj
 * @desc
 * @date 10:02 2017/9/13
 */
@Service
@Transactional
public class ElectricService extends AbstractChartService {

    @Autowired
    private EnergyMapper energyMapper;

    @Autowired
    private EquipmentManagerService equipmentManagerService;

    /**
     * @param entity
     * @desc 得到电量的图表数据结果
     */
    public ChartResultVO queryChartResult(EnergyEntity entity) {
        int type = entity.getType();
        if (type < 1 || type > 3) {
            String msg = "统计类型[%d]不存在";
            throw new EscstException(String.format(msg, type));
        }
        String[] dateSection = DateUtils.getDateSection(type);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", type);
        params.put("constructionId", entity.getConstructionId());
        params.put("startDate", DateUtils.parse(dateSection[0], DateUtils.TO_DATE));
        params.put("endDate", DateUtils.parse(dateSection[1], DateUtils.TO_DATE));
        List<DateQtyVO> list = energyMapper.queryElectricQty(params);
        return convertToChartResultVO(list, type);
    }
    
    /**
     * @desc 获取数据的总数
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午1:09:50
     */
    public int getEnergyCount(EnergyQueryVO queryVO) {
    	return energyMapper.getElectricCount(queryVO);
    }
    
    /**
     * @desc 获取数据
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午1:10:14
     */
    public List<DateQtyVO> queryEnergyList(EnergyQueryVO queryVO) {
    	List<DateQtyVO> list = energyMapper.queryElectricList(queryVO);
    	if (CollectionUtils.isNotEmpty(list)) {
    		Map<String, SimpleEquipmentVO> equipmentMap = equipmentManagerService.getSimpleEquipmentMap();
    		for (int i = 0; i < list.size(); i++) {
    			DateQtyVO dateQtyVO = list.get(i);
    			String id = dateQtyVO.getId();
    			if (equipmentMap.containsKey(id)) {
    				dateQtyVO.setName(equipmentMap.get(id).getName());
    			}
    		}
    	}
    	return list;
    }
}
