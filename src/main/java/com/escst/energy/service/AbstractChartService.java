package com.escst.energy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.energy.vo.ChartResultVO;
import com.escst.energy.vo.DateQtyVO;
import com.escst.energy.vo.EnergyQueryVO;
import com.escst.energy.vo.YDataVO;
/**
 * @author dwj
 * @desc
 * @date 15:26 2017/9/19
 */
public abstract class AbstractChartService {

	/**
     * @desc 将list对象转换为图标对象
     * @param list
     * @param type
     */
    public ChartResultVO convertToChartResultVO(List<DateQtyVO> list, int type) {
    	ChartResultVO rst = new ChartResultVO();
    	Map<String, String[]> nameMap = getXNameDateSectionTreeMap(type);
    	Iterator<Map.Entry<String, String[]>> it = nameMap.entrySet().iterator();
    	List<String> xdata = new ArrayList<String>();
    	Map<String, List<String>> ydataMap = new TreeMap<String, List<String>>();//设备的名称及设备的值数组
    	List<String> countList = initValueList(nameMap.size());
    	int xIndex = 0;
    	while (it.hasNext()) {//遍历x轴的坐标
    		Map.Entry<String, String[]> entry = it.next();
    		xdata.add(entry.getKey());
    		String[] dateSections = entry.getValue();
    		for (int i = 0; i < list.size(); i++) {
    			DateQtyVO dateQtyVO = list.get(i);
    			 String equipmentName = dateQtyVO.getName();
    			// 处理日、月的统计
    			if (dateSections.length == 1 && dateQtyVO.getDate().equals(dateSections[0])) {
    				if (ydataMap.containsKey(equipmentName)) {
    					List<String> ydataList = ydataMap.get(equipmentName);
    					ydataList.set(xIndex, dateQtyVO.getQty().toString());
    				}
    				else {
    					List<String> ydataList = initValueList(nameMap.size());
    					ydataList.set(xIndex, dateQtyVO.getQty().toString());
    					ydataMap.put(equipmentName, ydataList);
    				}
    				
    				// 计算统计的值
        			String countValue = countList.get(xIndex);
        			BigDecimal countValueBg = new BigDecimal(countValue);
        			countList.set(xIndex, dateQtyVO.getQty().add(countValueBg).toString());

    				continue;
    			}
    			if (dateSections.length == 1) {
    				continue;
    			}
    			int date = Integer.parseInt(dateQtyVO.getDate());
    			int startDate = Integer.parseInt(dateSections[0]);
    			int endDate = Integer.parseInt(dateSections[1]);
    			// 处理周的统计
    			if (dateSections.length == 2 && date >= startDate && date <= endDate) {
    				if (ydataMap.containsKey(equipmentName)) {
    					List<String> ydataList = ydataMap.get(equipmentName);
    					String value = ydataList.get(xIndex);
    					BigDecimal valueBg = new BigDecimal(value);
    					ydataList.set(xIndex, dateQtyVO.getQty().add(valueBg).toString());
    				}
    				else {
    					List<String> ydataList = initValueList(nameMap.size());
    					ydataList.set(xIndex, dateQtyVO.getQty().toString());
    					ydataMap.put(equipmentName, ydataList);
    				}
    				
    				// 计算统计的值
        			String countValue = countList.get(xIndex);
        			BigDecimal countValueBg = new BigDecimal(countValue);
        			countList.set(xIndex, dateQtyVO.getQty().add(countValueBg).toString());
    				
    				continue;
    			}
    		}
    		xIndex++;
    	}
    	List<YDataVO> ydataList = new ArrayList<YDataVO>();
    	
    	Iterator<Map.Entry<String, List<String>>> ydataIt = ydataMap.entrySet().iterator();
    	while (ydataIt.hasNext()) {
    		Map.Entry<String, List<String>> entry = ydataIt.next();
    		YDataVO yDataVO = new YDataVO();
    		yDataVO.setName(entry.getKey());
    		List<String> valueList = entry.getValue();
    		yDataVO.setData(valueList);
    		ydataList.add(yDataVO);
    	}
    	
    	rst.setXdata(xdata);
    	rst.setYdata(ydataList);
    	rst.setCount(countList);
    	return rst;
    }
	
	/**
     * @desc 得到统计的名称以及统计的开始时间和截止时间
     * @param type
     * @return
     */
    private Map<String, String[]> getXNameDateSectionTreeMap(int type) {
    	Map<String, String[]> map = new TreeMap<String, String[]>();
		String name = null;
    	if (type == 1) {
    		Calendar cal = Calendar.getInstance();
    		int day = cal.get(Calendar.DATE);
    		name = null;
    		String yearMonth = DateUtils.format(cal.getTime(), DateUtils.TO_MONTH_N);
    		for (int i = 1; i < day; i++) {//当天不统计
    			name = converString(i);
    			map.put(name, new String[]{yearMonth + name});
    		}
    	}
    	else if (type == 2) {//周
    		Calendar cal = Calendar.getInstance();
    		int day = cal.get(Calendar.DATE);
    		int preDay = day - 1;
    		name = null;
    		String yearMonth = DateUtils.format(cal.getTime(), DateUtils.TO_MONTH_N);
    		int startIndex = 1;
    		int endIndex = 0;
    		while (endIndex < preDay) {
    			endIndex = startIndex + 6;//每次加6
    			String startIndexStr = converString(startIndex);
    			String endIndexStr = converString(endIndex);
    			name = startIndexStr + "~" + endIndexStr;
    			map.put(name, new String[]{yearMonth + startIndexStr, yearMonth + endIndexStr});
    			
    			startIndex = endIndex + 1;
    		}
    	}
    	else if (type == 3) {
    		Calendar cal = Calendar.getInstance();
    		int month = cal.get(Calendar.MONTH);
    		int year = cal.get(Calendar.YEAR);
    		for (int i = 0; i <= month; i++) {
    			name = converString(i + 1);
    			String monthStr = year + name + "00";
    			map.put(name, new String[]{monthStr});
    		}
    	}
    	return map;
    }
    
    private String converString(int num) {
    	if (num < 10) {
    		return "0" + num;
    	}
    	return String.valueOf(num);
    }
    
    private List<String> initValueList(int len) {
    	List<String> rst = new ArrayList<String>();
    	for (int i =0; i < len; i++) {
    		rst.add("0");
    	}
    	return rst;
    }
    
    /**
     * @desc 查询水电的消耗情况
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午12:59:31
     */
    public PageVo queryEnergyListPage(EnergyQueryVO queryVO) {
    	int totalQty = getEnergyCount(queryVO);
		List<DateQtyVO> list = null;
        if (totalQty > 0) {
            list = queryEnergyList(queryVO);
        }
		PageVo pageVo = new PageVo();
        pageVo.setCurrentPage(queryVO.getPage());
        pageVo.setPageSize(queryVO.getRowNum());
        pageVo.setTotalRecord(totalQty);
        pageVo.setRows(list);
		return pageVo;
    }
    
    /**
     * @desc 获取数据的总数
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午1:09:50
     */
    public abstract int getEnergyCount(EnergyQueryVO queryVO);
    
    /**
     * @desc 获取数据
     * @param queryVO
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午1:10:14
     */
    public abstract List<DateQtyVO> queryEnergyList(EnergyQueryVO queryVO);
}
