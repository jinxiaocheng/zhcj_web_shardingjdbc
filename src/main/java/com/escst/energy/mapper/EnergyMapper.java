package com.escst.energy.mapper;

import com.escst.energy.vo.DateQtyVO;
import com.escst.energy.vo.EnergyQueryVO;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 9:57 2017/9/13
 */
public interface EnergyMapper {

    List<DateQtyVO> queryElectricQty(Map<String,Object> params);

    List<DateQtyVO> queryWaterQty(Map<String,Object> params);

    int getElectricCount(EnergyQueryVO queryVO);
    
    List<DateQtyVO> queryElectricList(EnergyQueryVO queryVO);

    int getWaterCount(EnergyQueryVO queryVO);
    
    List<DateQtyVO> queryWaterList(EnergyQueryVO queryVO);
}
