package com.escst.attendance.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.escst.attendance.bean.AttendanceVo;
import com.escst.commons.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.entity.AttendanceCountEntity;
import com.escst.attendance.mapper.AttendanceCountMapper;
import com.escst.attendance.vo.AttendanceNumQueryVO;
import com.escst.attendance.vo.AttendanceNumRstVO;
import com.escst.attendance.vo.AttendanceNumVO;
import com.escst.attendance.vo.AttendanceNumYDataVO;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.QtyVO;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.user.entity.UserEntity;

/**
 * @author dwj
 * @desc
 * @date 10:39 2017/10/30
 */
@Service
@Transactional
public class AttendanceCountService {

    @Autowired
    private AttendanceCountMapper attendanceCountMapper;

    /**
    * @desc 查询考勤统计列表
    * @param bean
    * @return
    * @author dwj
    * @date 2017/10/30 11:19
    */
    public PageVo queryList(AttendanceBean bean){
        PageVo pageVo = new PageVo();
        try{
            Map<String,Object> paraMap = new HashMap<String, Object>();
            String constructionId = bean.getConstructionId();
            String belongArea = bean.getBelongArea();
            String startDate = bean.getStartDate();
            String endDate = bean.getEndDate();
            String id = bean.getId();
            String date = bean.getDate();
            //当前页号
            int pageNum = bean.getPage();

            //每页显示记录数
            int pageSize = bean.getRowNum();

            //每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;
            paraMap.put("id", id);
            paraMap.put("belongArea", belongArea);
            paraMap.put("startDate", startDate);
            paraMap.put("endDate", endDate);
            paraMap.put("date", date);
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            if(StringUtils.isEmpty(constructionId)){
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList",constructionList);
            }else {
                paraMap.put("constructionId",constructionId);
            }
            int count = attendanceCountMapper.selectCount(paraMap);
            List<Map<String,Object>> list = attendanceCountMapper.queryList(paraMap);
            if(!CollectionUtils.isEmpty(list)){

            }
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        }catch (Exception e){
            throw new EscstException("查询考勤统计失败"+e.getMessage(),e);
        }
        return pageVo;
    }

    /**
    * @desc 批量新增
    * @param entity
    * @return
    * @author dwj
    * @date 2017/10/30 11:22
    */
    public void batchSave(AttendanceCountEntity entity){
        try{
            List<AttendanceCountEntity> entityList = new ArrayList<AttendanceCountEntity>();
            String id = entity.getId();
            if(StringUtils.isEmpty(id)){
                String constructionId = entity.getConstructionId();
                String attendanceDate = entity.getAttendanceDate();
                String []teamName = entity.getTeamNameArray();
                List<String> nameList = attendanceCountMapper.queryCountByAttendanceDate(entity);
                if(!StringUtils.isEmpty(teamName)){
                    for(int i= 0; i< teamName.length; i++){
                        AttendanceCountEntity attendanceCountEntity = new AttendanceCountEntity();
                        attendanceCountEntity.setTeamName(teamName[i]);
                        attendanceCountEntity.setCount(Integer.valueOf(entity.getCountArray()[i]));
                        attendanceCountEntity.setAttendanceDate(attendanceDate);
                        attendanceCountEntity.setConstructionId(constructionId);
                        if(CollectionUtils.isEmpty(nameList)){
                            attendanceCountEntity.setCreateTime(new Date());
                            attendanceCountEntity.setId(UuidUtils.getUuid());
                            entityList.add(attendanceCountEntity);
                        }else{
                            if(nameList.contains(teamName[i])){
                                AttendanceCountEntity countEntity = new AttendanceCountEntity();
                                countEntity.setTeamName(teamName[i]);
                                countEntity.setCount(Integer.valueOf(entity.getCountArray()[i]));
                                countEntity.setAttendanceDate(attendanceDate);
                                countEntity.setConstructionId(constructionId);
                                attendanceCountMapper.updateCount(countEntity);

                            }else{
                                attendanceCountEntity.setCreateTime(new Date());
                                attendanceCountEntity.setId(UuidUtils.getUuid());
                                entityList.add(attendanceCountEntity);
                            }
                        }
                    }
                }
                if(!CollectionUtils.isEmpty(entityList)){
                    attendanceCountMapper.batchSave(entityList);
                }
            }
        }catch (Exception e){
            throw  new EscstException("新增考勤统计失败"+e.getMessage(),e);
        }

    }

    public void update(AttendanceCountEntity entity){
        try{
            String id = entity.getId();
            if(!StringUtils.isEmpty(id)){
                entity.setCreateTime(new Date());
                attendanceCountMapper.update(entity);
            }
        }catch (Exception e){
           throw new EscstException("修改考勤统计失败"+e.getMessage(),e);
        }

    }

    /**
    * @desc 通过ID查看详情
    * @param entity
    * @return
    * @author dwj
    * @date 2017/10/30 16:57
    */
    public AttendanceCountEntity queryById(AttendanceCountEntity entity){
        return attendanceCountMapper.queryById(entity);
    }
    
    /**
     * @desc 查询有权限的工地的岗位工种人数
     * @param authVO
     * @return 
     * @author zhouwei
     * @date 2017年11月6日 下午1:54:34
     */
    public List<QtyVO> queryAuthWorkTypePersonQty(BaseAuthVO authVO) {
    	List<QtyVO> list = attendanceCountMapper.queryAuthWorkTypePersonQty(authVO);
    	for (QtyVO vo : list) {
    		String teamName = vo.getName();
    		String name = teamName.replaceAll("组", "");
    		vo.setName(name);
    	}
    	return list;
    }
    
	public AttendanceNumRstVO queryAttendanceNumQty(AttendanceNumQueryVO attendanceNumVO) {
		AttendanceNumRstVO rstVo = new AttendanceNumRstVO();

		//设置开始时间和结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -6);
		Date dateDiff = calendar.getTime();
		attendanceNumVO.setStartDate(sdf.format(dateDiff));
		attendanceNumVO.setEndDate(sdf.format(new Date()));
		//获取原始数据
		List<AttendanceNumVO> list = attendanceCountMapper.queryAttendanceNumQty(attendanceNumVO);
		
		Map<String, List<String>> ydataMap = new TreeMap<String, List<String>>();// 工种的名称及工种的值数组
		//获取x轴时间数据
		List<String> dayList = DateUtils.getDayList(attendanceNumVO.getStartDate(), attendanceNumVO.getEndDate());
		//组装y轴数据
		int xIndex = 0;
		for (String day : dayList) {
			for (AttendanceNumVO ydataVo : list) {
				if (day.equals(ydataVo.getDate())) {
					if (ydataMap.containsKey(ydataVo.getTeamName().replace("组", ""))) {
						String teamName = ydataVo.getTeamName().replace("组", "");
						List<String> ydataList = ydataMap.get(teamName);
						ydataList.set(xIndex, ydataVo.getCount());
					} else {
						List<String> ydataList = initValueList(7);
						ydataList.set(xIndex, ydataVo.getCount());
						String teamName = ydataVo.getTeamName().replace("组", "");
						ydataMap.put(teamName, ydataList);
					}
				}
			}
			xIndex++;
		}

		List<AttendanceNumYDataVO> ydataList = new ArrayList<AttendanceNumYDataVO>();
		Iterator<Map.Entry<String, List<String>>> ydataIt = ydataMap.entrySet().iterator();
		while (ydataIt.hasNext()) {
			Map.Entry<String, List<String>> entry = ydataIt.next();
			AttendanceNumYDataVO yDataVO = new AttendanceNumYDataVO();
			yDataVO.setName(entry.getKey());
			List<String> valueList = entry.getValue();
			yDataVO.setData(valueList);
			ydataList.add(yDataVO);
		}

		rstVo.setXdata(dayList);
		rstVo.setyDataVo(ydataList);
		return rstVo;
	}

	private List<String> initValueList(int len) {
		List<String> rst = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			rst.add("0");
		}
		return rst;
	}


    /**
    * @desc 获取每个月每天出勤人数
    * @param map
    * @return
    * @author dwj
    * @date 2017/11/9 14:45
    */
    public List<AttendanceVo> queryAttendaceQty(Map<String,Object> map){
        return attendanceCountMapper.queryAttendaceQty(map);
    }

    /**
    * @desc  获取每个月出勤的班组
    * @param map
    * @return
    * @author dwj
    * @date 2017/11/9 14:45
    */
    public List<String> queryAttendaceTeamName(Map<String,Object> map){
        return attendanceCountMapper.queryTeamNameList(map);
    }

    /**
    * @desc 获取每个月每天出勤人数总数
    * @param map
    * @return
    * @author dwj
    * @date 2017/11/9 14:47
    */
    public List<AttendanceVo> queryCount(Map<String,Object> map){
        return attendanceCountMapper.queryCount(map);
    }

    /**
    * @desc 出勤人数导出
    * @param bean
    * @return
    * @author dwj
    * @date 2017/11/9 15:21
    */
    public  Map<String, Object> getAttendancePerson(AttendanceBean bean){
        List<String> dateList = new ArrayList<String>();
        List<Integer> xList = new ArrayList<Integer>();
        Map<String, List<AttendanceVo>> paramMap = new HashMap<String, List<AttendanceVo>>();
        Map<String,Object> paraMap = new HashMap<String, Object>();
        String constructionId = bean.getConstructionId();
        Map<String,Object> map = new HashMap<String,Object>();
        String time = bean.getDate();
        String dateStr = DateUtils.getDateStr(time);
        String [] dateSolt =null;
        if(StringUtils.isEmpty(constructionId)){
            UserEntity userEntity = ContextUtils.getCurrentUser();
            List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
            paraMap.put("constructionList",constructionList);
            map.put("constructionList",constructionList);
        }else {
            paraMap.put("constructionId",constructionId);
            map.put("constructionId",constructionId);
        }
        if(time.equals(DateUtils.format(new Date(),DateUtils.TO_MONTH))){
            dateList = DateUtils.queryDayListByCurrentHour();
            dateSolt = DateUtils.getDateSlot(1,bean.getDate());
        }else{
            dateList = DateUtils.queryMonthDay(time);
            dateSolt = DateUtils.getDateSlot(2,bean.getDate());
        }
        for(String s: dateList){
            xList.add(Integer.valueOf(s));
        }
        paraMap.put("time",time);
      List<String> entityList = queryAttendaceTeamName(paraMap);
        for (String str:entityList) {
            List<AttendanceVo> list = new ArrayList<AttendanceVo>();
            map.put("startDate",dateSolt[0]);
            map.put("endDate",dateSolt[1]);
            map.put("teamName",str);
            List<AttendanceVo> countVo = queryAttendaceQty(map);
            if(!CollectionUtils.isEmpty(countVo)) {
                Map<String,AttendanceVo> daMap = new HashMap<String, AttendanceVo>();
                for (AttendanceVo vo : countVo) {
                    String date = vo.getDate();
                    daMap.put(date,vo);
                }
                for(String s: dateList){
                    if(daMap.containsKey(s)){
                        list.add(daMap.get(s));
                    }else{
                        AttendanceVo vo = new AttendanceVo();
                        vo.setCount(0);
                        vo.setDate(s);
                        list.add(vo);
                    }
                }
            }

            paramMap.put(str,list);
        }
        List<AttendanceVo> countList = queryCount(map);
        List<AttendanceVo> list = new ArrayList<AttendanceVo>();
        if(!CollectionUtils.isEmpty(countList)) {
            Map<String,AttendanceVo> daMap = new HashMap<String, AttendanceVo>();
            for (AttendanceVo vo : countList) {
                String date = vo.getDate();
                daMap.put(date,vo);
            }
            for(String s: dateList){
                if(daMap.containsKey(s)){
                    list.add(daMap.get(s));
                }else{
                    AttendanceVo vo = new AttendanceVo();
                    vo.setCount(0);
                    vo.setDate(s);
                    list.add(vo);
                }
            }
        }
        Map<String, Object> beanParams = new HashMap<String, Object>();

        //日期
        beanParams.put("dateList", xList);
        //班组出勤人数
        beanParams.put("data", paramMap);
        //合计
        beanParams.put("countList",list);
        beanParams.put("name",bean.getConstructionName());
        beanParams.put("dateStr",dateStr);
       return beanParams;
    }


}
