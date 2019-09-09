package com.escst.teamTime.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.teamTime.VO.TeamTimeVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 班组工时
 * @date 2018/2/5 15:53
 */
@Repository
public interface TeamTimeMapper extends BaseMapper<TeamTimeVO> {


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.PageVo
     * @desc 查询工地下当日此班组里的所有人员工时统计
     * @author jincheng
     * @date 2018/2/7 13:40
     */
    List<TeamTimeVO> selectPersonTimeData(TeamTimeVO teamTimeVO);

    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.PageVo
     * @desc 查询工地下当日此班组里的所有人员工时统计条数
     * @author jincheng
     * @date 2018/2/7 13:40
     */
    int selectPersonTimeDataCount(TeamTimeVO teamTimeVO);


    /**
     * @param id
     * @return
     * @desc 查出班组对应的区域，工地，日期信息
     * @author jincheng
     * @date 2018/2/7 16:59
     */
    TeamTimeVO query(String id);

    /**
     * @param id
     * @return com.escst.teamTime.VO.TeamTimeVO
     * @desc 查询出勤人员的详细信息
     * @author jincheng
     * @date 2018/2/8 9:51
     */
    TeamTimeVO select(String id);

    /**
     * @param teamTimeVO
     * @return java.util.List<com.escst.teamTime.VO.TeamTimeVO>
     * @desc 查询人员考勤明细
     * @author jincheng
     * @date 2018/2/8 10:31
     */
    List<TeamTimeVO> selectPersonData(TeamTimeVO teamTimeVO);

    /**
     * @param teamTimeVO
     * @return
     * @desc 查询人员考勤明细条数
     * @author jincheng
     * @date 2018/2/8 10:31
     */
    int selectPersonCount(TeamTimeVO teamTimeVO);

}
