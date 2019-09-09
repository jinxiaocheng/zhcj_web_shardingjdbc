package com.escst.teamTime.service;


import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.person.entity.PersonConditionBean;
import com.escst.person.entity.PersonExcelEntity;
import com.escst.teamTime.VO.TeamTimeVO;
import com.escst.teamTime.entity.PersonAttendanceExcelEntity;
import com.escst.teamTime.entity.PersonTimeExcelEntity;
import com.escst.teamTime.entity.TeamTimeExcelEntity;
import com.escst.teamTime.mapper.TeamTimeMapper;
import com.escst.user.entity.UserEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author jincheng
 * @desc 班组工时
 * @date 2018/2/5 15:51
 */
@Service
public class TeamTimeService {

    @Autowired
    private TeamTimeMapper teamTimeMapper;


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.PageVo
     * @desc 查询班组工时列表
     * @author jincheng
     * @date 2018/2/5 15:53
     */
    public PageVo listData(TeamTimeVO teamTimeVO) {
        teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
        PageVo pageVo = new PageVo();
        try {
            int count = teamTimeMapper.selectCount(teamTimeVO);
            // 当前页
            pageVo.setCurrentPage(teamTimeVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(teamTimeVO.getRowNum());
            // 导出ecxel时，rowNum为Integer.MAX_VALUE
            int startIndex;
            if (teamTimeVO.getRowNum() == Integer.MAX_VALUE) {
                startIndex = 0;
            } else {
                // 每页第一条记录的索引
                startIndex = (teamTimeVO.getPage() - 1) * (teamTimeVO.getRowNum());
            }
            teamTimeVO.setStartIndex(startIndex);
            List<TeamTimeVO> list = teamTimeMapper.selectList(teamTimeVO);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }

        } catch (Exception e) {
            throw new EscstException("查询班组工时信息失败" + e.getMessage(), e);
        }
        return pageVo;
    }


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.PageVo
     * @desc 查询工地下当日此班组里的所有人员工时统计
     * @author jincheng
     * @date 2018/2/7 13:40
     */
    public PageVo personTimeData(TeamTimeVO teamTimeVO) {
        teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
        PageVo pageVo = new PageVo();
        try {
            int count = teamTimeMapper.selectPersonTimeDataCount(teamTimeVO);
            // 当前页
            pageVo.setCurrentPage(teamTimeVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(teamTimeVO.getRowNum());
            // 导出ecxel时，rowNum为Integer.MAX_VALUE
            int startIndex;
            if (teamTimeVO.getRowNum() == Integer.MAX_VALUE) {
                startIndex = 0;
            } else {
                // 每页第一条记录的索引
                startIndex = (teamTimeVO.getPage() - 1) * (teamTimeVO.getRowNum());
            }
            teamTimeVO.setStartIndex(startIndex);
            List<TeamTimeVO> list = teamTimeMapper.selectPersonTimeData(teamTimeVO);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }

        } catch (Exception e) {
            throw new EscstException("查询人员工时信息失败" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * @param id
     * @return
     * @desc 查出班组对应的区域，工地，日期信息
     * @author jincheng
     * @date 2018/2/7 16:59
     */
    public TeamTimeVO query(String id) {
        TeamTimeVO teamTimeVO = new TeamTimeVO();
        try {
            if (!StringUtils.isEmpty(id)) {
                teamTimeVO = teamTimeMapper.query(id);
            }
        } catch (Exception e) {
            throw new EscstException("查询区域，工地，日期信息失败" + e.getMessage(), e);
        }
        return teamTimeVO;
    }

    /**
     * @param id
     * @return
     * @desc 查询出勤人员的详细信息
     * @author jincheng
     * @date 2018/2/7 16:59
     */
    public TeamTimeVO select(String id) {
        TeamTimeVO teamTimeVO = new TeamTimeVO();
        try {
            if (!StringUtils.isEmpty(id)) {
                teamTimeVO = teamTimeMapper.select(id);
            }
        } catch (Exception e) {
            throw new EscstException("查询出勤人员的详细信息失败" + e.getMessage(), e);
        }
        return teamTimeVO;
    }


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.PageVo
     * @desc 查询人员考勤明细
     * @author jincheng
     * @date 2018/2/7 13:40
     */
    public PageVo selectPersonData(TeamTimeVO teamTimeVO) {
        teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
        PageVo pageVo = new PageVo();
        try {
            int count = teamTimeMapper.selectPersonCount(teamTimeVO);
            // 当前页
            pageVo.setCurrentPage(teamTimeVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(teamTimeVO.getRowNum());
            // 导出ecxel时，rowNum为Integer.MAX_VALUE
            int startIndex;
            if (teamTimeVO.getRowNum() == Integer.MAX_VALUE) {
                startIndex = 0;
            } else {
                // 每页第一条记录的索引
                startIndex = (teamTimeVO.getPage() - 1) * (teamTimeVO.getRowNum());
            }
            teamTimeVO.setStartIndex(startIndex);
            List<TeamTimeVO> list = teamTimeMapper.selectPersonData(teamTimeVO);
            if (!CollectionUtils.isEmpty(list)) {
                pageVo.setRows(list);
            }

        } catch (Exception e) {
            throw new EscstException("查询人员考勤明细信息失败" + e.getMessage(), e);
        }
        return pageVo;

    }

    /**
     * @throws Exception
     * @desc 导出班组工时excel
     * @author jincheng
     * @date 2018年1月3日 下午2:35:07
     */
    public Workbook exportTeamTimeExcel(TeamTimeVO teamTimeVO) throws Exception {
        ExportParams params = new ExportParams();
        List<TeamTimeVO> list = (List<TeamTimeVO>) this.listData(teamTimeVO).getRows();
        List<TeamTimeExcelEntity> voList = new ArrayList<TeamTimeExcelEntity>();
        if (CollectionUtils.isEmpty(list)) {
            TeamTimeExcelEntity excelEntity = new TeamTimeExcelEntity();
            excelEntity.setTime("无数据");
            excelEntity.setName("无数据");
            excelEntity.setTotal("无数据");
            excelEntity.setTotalTime("无数据");
            voList.add(excelEntity);
        } else {
            for (TeamTimeVO vo : list) {
                TeamTimeExcelEntity excelEntity = new TeamTimeExcelEntity();
                excelEntity.setTime(vo.getTime());
                excelEntity.setName(vo.getName());
                excelEntity.setTotal(String.valueOf(vo.getTotal()));
                excelEntity.setTotalTime(String.valueOf(vo.getTotalTime()));
                voList.add(excelEntity);
            }
        }
        Workbook workbook = ExcelExportUtil.exportExcel(params, TeamTimeExcelEntity.class, voList);
        return workbook;
    }

    /**
     * @throws Exception
     * @desc 导出人员工时excel
     * @author jincheng
     * @date 2018年1月3日 下午2:35:07
     */
    public Workbook exportPersonTimeExcel(TeamTimeVO teamTimeVO) throws Exception {
        ExportParams params = new ExportParams();
        List<TeamTimeVO> list = (List<TeamTimeVO>) this.personTimeData(teamTimeVO).getRows();
        List<PersonTimeExcelEntity> voList = new ArrayList<PersonTimeExcelEntity>();
        if (CollectionUtils.isEmpty(list)) {
            PersonTimeExcelEntity excelEntity = new PersonTimeExcelEntity();
            excelEntity.setPersonName("无数据");
            excelEntity.setUpTime("无数据");
            excelEntity.setDownTime("无数据");
            excelEntity.setWorkTime("无数据");
            voList.add(excelEntity);
        } else {
            for (TeamTimeVO vo : list) {
                PersonTimeExcelEntity excelEntity = new PersonTimeExcelEntity();
                excelEntity.setPersonName(vo.getPersonName());
                excelEntity.setUpTime(vo.getUpTime());
                excelEntity.setDownTime(vo.getDownTime());
                excelEntity.setWorkTime(vo.getWorkTime());
                voList.add(excelEntity);
            }
        }
        Workbook workbook = ExcelExportUtil.exportExcel(params, PersonTimeExcelEntity.class, voList);
        return workbook;
    }

    /**
     * @throws Exception
     * @desc 导出人员出勤明细excel
     * @author jincheng
     * @date 2018年1月3日 下午2:35:07
     */
    public Workbook exportPersonAttendanceExcel(TeamTimeVO teamTimeVO) throws Exception {
        ExportParams params = new ExportParams();
        List<TeamTimeVO> list = (List<TeamTimeVO>) this.selectPersonData(teamTimeVO).getRows();
        List<PersonAttendanceExcelEntity> voList = new ArrayList<PersonAttendanceExcelEntity>();
        if (CollectionUtils.isEmpty(list)) {
            PersonAttendanceExcelEntity excelEntity = new PersonAttendanceExcelEntity();
            excelEntity.setPersonName("无数据");
            excelEntity.setCardNumber("无数据");
            excelEntity.setCardTime("无数据");
            excelEntity.setType("无数据");
            voList.add(excelEntity);
        } else {
            for (TeamTimeVO vo : list) {
                PersonAttendanceExcelEntity excelEntity = new PersonAttendanceExcelEntity();
                excelEntity.setPersonName(vo.getPersonName());
                excelEntity.setCardNumber(vo.getCardNumber());
                excelEntity.setCardTime(vo.getCardTime());
                if (vo.getType().equals("1")) {
                    excelEntity.setType("进场");
                } else {
                    excelEntity.setType("出场");
                }
                voList.add(excelEntity);
            }
        }
        Workbook workbook = ExcelExportUtil.exportExcel(params, PersonAttendanceExcelEntity.class, voList);
        return workbook;
    }

}
