package com.escst.attendance.service;

import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.bean.AttendanceNewBean;
import com.escst.attendance.entity.AttendanceExcelEntity;
import com.escst.attendance.entity.AttendanceResultEntity;
import com.escst.attendance.mapper.AttendanceClockRecordMapper;
import com.escst.attendance.mapper.AttendanceResultMapper;
import com.escst.attendance.vo.AttendanceClockRecordVo;
import com.escst.attendance.vo.AttendanceNewVo;
import com.escst.attendance.vo.AttendanceNumNewVO;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.person.mapper.PersonMapper;
import com.escst.user.entity.UserEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 考勤
 * @author zhouwei
 * @date 2017年7月12日 下午1:30:27
 */
@Service
@Transactional
public class AttendanceService {

	@Autowired
	private AttendanceClockRecordMapper attendanceClockRecordMapper;

	@Autowired
	private AttendanceResultMapper attendanceResultMapper;

	@Autowired
	private PersonMapper personMapper;

	public boolean hasNullClockDate() {
		Map<String, Object> map = attendanceClockRecordMapper.getNullClockDateNum();
		return map == null || map.size() == 0 ? false : true;
	}

	/**
	 * @desc 更新打卡日期和打卡的分钟数
	 * @author zhouwei
	 * @date 2017年7月13日 上午10:20:06
	 */
	public void updateClockDateMinute() {
		boolean hasNull = hasNullClockDate();
		if (hasNull) {
			attendanceClockRecordMapper.updateClockDateMinute();
		}
	}

	/**
	 * @desc 为考勤明细表创建月分区表
	 * @author zhouwei
	 * @date 2017年7月13日 下午1:14:30
	 */
	public void createMonthPartitionClockRecord() {

	}

	public PageVo queryAttendanceList(AttendanceResultEntity entity) {
		PageVo pageVo = new PageVo();
		try {
			// 根据工地id查询人员总记录数
			int count = attendanceResultMapper.selectCount(entity);
			// 当前页号
			pageVo.setCurrentPage(entity.getPage());
			// 总记录数
			pageVo.setTotalRecord(count);

			pageVo.setPageSize(entity.getRowNum());
			// 每页的第一条记录的索引
			int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
			entity.setStartIndex(startIndex);
			List<Map<String, Object>> list = attendanceResultMapper.selectAttendanceList(entity);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
		} catch (Exception e) {
			throw new EscstException("查询人员列表信息失败！", e);
		}
		return pageVo;
	}

	/**
	 * @desc 工地Id获取打卡信息
	 * @param vo
	 * @return
	 * @author dwj
	 * @date 2018/1/17 11:03
	 */
	public PageVo queryList(AttendanceClockRecordVo vo){
		PageVo pageVo = new PageVo();
		try{
			String constructionId = vo.getConstructionId();
			String areaId = vo.getAreaId();
			String personName = vo.getPersonName();
			String cardNum = vo.getCardNum();
			String positionWorktypeName = vo.getPositionWorktype();
			String startDate = vo.getStartDate();
			String endDate = vo.getEndDate();
			String companyId = vo.getCompanyId();
			String positionWorkId = vo.getPositionWorkId();
			//当前页号
			int pageNum = vo.getPage();
			//每页显示记录数
			int pageSize = vo.getRowNum();
			//每页的第一条记录的索引
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("offset", offset);
			paraMap.put("rows", pageSize);
			paraMap.put("areaId", areaId);
			paraMap.put("personName", personName);
			paraMap.put("cardNum", cardNum);
			paraMap.put("companyId", companyId);
			paraMap.put("positionWorkId", positionWorkId);
			paraMap.put("type", 0);
			paraMap.put("userId",ContextUtils.getCurrentUserId());
			if(StringUtils.isEmpty(constructionId)) {
				UserEntity userEntity = ContextUtils.getCurrentUser();
				List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
				paraMap.put("constructionList",constructionList);
			} else {
				paraMap.put("constructionId", constructionId);
			}
			paraMap.put("startDate", startDate.substring(0,13));
			paraMap.put("endDate", endDate.substring(0,13));

			int count = attendanceClockRecordMapper.count(paraMap);
			List<AttendanceClockRecordVo> list = attendanceClockRecordMapper.queryList(paraMap);
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
			pageVo.setCurrentPage(pageNum);
			pageVo.setPageSize(pageSize);
			pageVo.setTotalRecord(count);
		}catch (Exception e){
			throw new EscstException("查询人员列表信息失败！"+e.getMessage());
		}

		return pageVo;
	}

	/**
	 * @desc 查询某一时段为打卡的人员
	 * @param vo
	 * @return
	 * @author dwj
	 * @date 2/7/2018 16:21
	 */
	public PageVo querByDate(AttendanceClockRecordVo vo){
		PageVo pageVo = new PageVo();
		List<AttendanceClockRecordVo> list = new ArrayList<AttendanceClockRecordVo>();
		try{
			String constructionId = vo.getConstructionId();
			String startDate = vo.getStartDate();
			String endDate = vo.getEndDate();
			String companyId = vo.getCompanyId();
			String personName = vo.getPersonName();
			//当前页号
			int pageNum = vo.getPage();
			//每页显示记录数
			int pageSize = vo.getRowNum();
			//每页的第一条记录的索引
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("userId", vo.getUserId());
			paraMap.put("companyId", companyId);
			paraMap.put("constructionId", constructionId);
			paraMap.put("startDate", startDate.substring(0,13));
			paraMap.put("endDate", endDate.substring(0,13));
			paraMap.put("personName", personName);
			List<String> clockRecordVos = attendanceClockRecordMapper.queryRecordPersonId(paraMap);
			List<AttendanceClockRecordVo> personList = personMapper.selectByConstructionId(paraMap);
			for(AttendanceClockRecordVo personVo : personList){
				String id = personVo.getPersonId();
				if(!clockRecordVos.contains(id)){
					AttendanceClockRecordVo clockRecordVo = new AttendanceClockRecordVo();
					clockRecordVo.setCardNum(personVo.getCardNum());
					clockRecordVo.setPositionWorktype(personVo.getPositionWorktype());
					clockRecordVo.setTeamName(personVo.getTeamName());
					clockRecordVo.setAreaName(personVo.getAreaName());
					clockRecordVo.setPersonName(personVo.getPersonName());
					clockRecordVo.setCompanyName(personVo.getCompanyName());
					clockRecordVo.setConstructionName(personVo.getConstructionName());
					list.add(clockRecordVo);
				}

			}
			if(!CollectionUtils.isEmpty(list)){
				int count = list.size();
				pageVo.setTotalRecord(count);
				//对list进行截取
				pageVo.setRows(list.subList(offset,count-offset>pageSize?offset+pageSize:count));
			}
			pageVo.setCurrentPage(pageNum);
			pageVo.setPageSize(pageSize);

		}catch (Exception e){
			throw new EscstException("查询某时段未打卡人员列表信息失败！"+e.getMessage(),e);
		}

		return pageVo;
	}


	/**
	 * @desc 获取当前用户下分包公司下出勤人数
	 * @param bean
	 * @return
	 * @author dwj
	 * @date 20/7/2018 15:27
	 */
	public PageVo selectAttendanceByUserId(AttendanceBean bean){
		PageVo pageVo = new PageVo();
		List<AttendanceNewVo> list = new ArrayList<AttendanceNewVo>();
		try{
			int pageNum = bean.getPage();
			//每页显示记录数
			int pageSize = bean.getRowNum();
			//每页的第一条记录的索引
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("userId", bean.getUserId());
			paraMap.put("constructionId", bean.getConstructionId());
			paraMap.put("offset", offset);
			paraMap.put("rows", pageSize);
			int count = attendanceClockRecordMapper.countNew(paraMap);
			List<AttendanceNewBean> beanList = attendanceClockRecordMapper.selectTotalByCompanyId(paraMap);
			for(AttendanceNewBean attendanceNewBean : beanList){
				AttendanceNewVo vo = new AttendanceNewVo();
				String companyId = attendanceNewBean.getCompanyId();
				Integer totalNum = attendanceNewBean.getTotalNum();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("companyId", companyId);
				map.put("startDate",bean.getStartDate());
				map.put("endDate",bean.getEndDate());

				int turnOutNum = personMapper.getTotalAttendanceNum(map);

				int persenceNum = personMapper.getLocalePersonNum(map);

				NumberFormat numberFormat = NumberFormat.getInstance();
				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				String result = numberFormat.format((float)turnOutNum/(float)totalNum*100)+"%";

				vo.setAreaName(attendanceNewBean.getAreaName());
				vo.setCompanyName(attendanceNewBean.getCompanyName());
				vo.setConstructionName(attendanceNewBean.getConstructionName());
				vo.setTotalNum(totalNum);
				vo.setTurnOutNum(turnOutNum);
				vo.setPersenceNum(persenceNum);
				vo.setAttendancePercent(result);
				vo.setCompanyId(attendanceNewBean.getCompanyId());
				list.add(vo);
			}
			if (!CollectionUtils.isEmpty(list)) {
				pageVo.setRows(list);
			}
			pageVo.setCurrentPage(pageNum);
			pageVo.setPageSize(pageSize);
			pageVo.setTotalRecord(count);
		}catch (Exception e){
			throw new EscstException("查询当前用户下出场人数异常:"+e.getMessage(),e);
		}
		return pageVo;
	}



	/**
	 * @desc 获取当前用户下分包公司下班组出勤人数
	 * @param bean
	 * @return
	 * @author dwj
	 * @date 23/7/2018 10:05
	 */
	public List<Map<String,List<Object>>> createChart(AttendanceBean bean){
		List<Map<String,List<Object>>> dataList = new ArrayList<Map<String, List<Object>>>();
		try{
			String companyId = bean.getId();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("userId", bean.getUserId());
			paraMap.put("type", 0);
			if(StringUtils.isEmpty(companyId)){
				List<AttendanceNewBean> beanList = attendanceClockRecordMapper.selectTotalByCompanyId(paraMap);
				if(CollectionUtils.isEmpty(beanList)){
					return null;
				}
				companyId = beanList.get(0).getCompanyId();
			}
			List<AttendanceNumNewVO> list = attendanceClockRecordMapper.selectByCompanyId(companyId);
			Map<String,List<Object>> paramMap = new HashMap<String, List<Object>>();
			for(AttendanceNumNewVO vo : list){
				List<Object> objs = new ArrayList<Object>();
				String id = vo.getId();
				int count = vo.getCount();
				String name = vo.getTeamName();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("teamId", id);
				map.put("type", 1);
				if(StringUtils.isEmpty(bean.getStartDate())){
					map.put("startDate", DateUtils.getTodayStart());
				}else {
					map.put("startDate",bean.getStartDate());
				}
				if(StringUtils.isEmpty(bean.getEndDate())){
					map.put("endDate", DateUtils.getTodayEnd());
				}else {
					map.put("endDate",bean.getEndDate());
				}

				//出勤人数
				int turnOutNum = personMapper.getTotalAttendanceNum(map);

				//在场人数
				int persenceNum = personMapper.getLocalePersonNum(map);


				NumberFormat numberFormat = NumberFormat.getInstance();
				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				//出勤率
				String result = numberFormat.format((float)turnOutNum/(float)count*100);
				objs.add(count);
				objs.add(turnOutNum);
				objs.add(persenceNum);
				objs.add(result);
				paramMap.put(name,objs);
			}
			dataList.add(paramMap);
		}catch (Exception e){
			throw new EscstException("查询公司下班组出勤人数异常"+e.getMessage(),e);
		}
		return dataList;
	}


	public Workbook exportExcel(AttendanceClockRecordVo vo){
		ExportParams params = new ExportParams();
		params.setType(ExcelType.XSSF);
		List<AttendanceExcelEntity> list = new ArrayList<AttendanceExcelEntity>();

		String constructionId = vo.getConstructionId();
		String areaId = vo.getAreaId();
		String personName = vo.getPersonName();
		String cardNum = vo.getCardNum();
		String positionWorktypeName = vo.getPositionWorktype();
		String startDate = vo.getStartDate();
		String endDate = vo.getEndDate();
		String companyId = vo.getCompanyId();
		String positionWorkId = vo.getPositionWorkId();
		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("offset", offset);
		paraMap.put("rows", null);
		paraMap.put("areaId", areaId);
		paraMap.put("personName", personName);
		paraMap.put("cardNum", cardNum);
		paraMap.put("companyId", companyId);
		paraMap.put("positionWorkId", positionWorkId);
		paraMap.put("type", 0);
		if(StringUtils.isEmpty(constructionId)) {
			UserEntity userEntity = ContextUtils.getCurrentUser();
			List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
			paraMap.put("constructionList",constructionList);
		} else {
			paraMap.put("constructionId", constructionId);
		}
		paraMap.put("startDate", startDate.substring(0,13));
		paraMap.put("endDate", endDate.substring(0,13));
		List<AttendanceClockRecordVo> recordList = attendanceClockRecordMapper.queryList(paraMap);
		for(AttendanceClockRecordVo recordVo : recordList){
			AttendanceExcelEntity entity = new AttendanceExcelEntity();
			entity.setName(recordVo.getPersonName());
			entity.setCardNum(recordVo.getCardNum());
			entity.setClockTime(recordVo.getAttendanceDate());
			String type = recordVo.getType()==1?"进场":"出场";
			entity.setType(type);
			entity.setPosition(recordVo.getPositionWorktype());
			entity.setTeam(recordVo.getTeamName());
			entity.setCompany(recordVo.getCompanyName());

			list.add(entity);
		}

		Workbook workbook = ExcelExportUtil.exportExcel(params, AttendanceExcelEntity.class, list);
		return workbook;
	}
}
