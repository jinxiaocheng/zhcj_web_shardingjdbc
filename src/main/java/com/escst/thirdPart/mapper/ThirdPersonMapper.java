package com.escst.thirdPart.mapper;

import com.escst.commons.vo.BaseVO;
import com.escst.thirdPart.bean.CountBean;
import com.escst.thirdPart.bean.PersonInfoBean;
import com.escst.thirdPart.entity.DepartEntity;
import com.escst.thirdPart.vo.CountVo;
import com.escst.thirdPart.vo.RecordPersonVo;
import com.escst.thirdPart.vo.ThirdPersonVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 10:04 30/7/2018
 */
@Repository
public interface ThirdPersonMapper {

    void batchInsert(List<PersonInfoBean> list);

    List<ThirdPersonVo> selectAll(Map<String,Object> map);

    int count(Map<String,Object> map);

    PersonInfoBean selectById(String id);

    List<String> selectKindName(int type);

    List<RecordPersonVo> selectRecorPersonById(String id);

    //统计查询
    //总人数查询,累计培训人数,累计培训学时
    List<Map<String,Object>> TrainPersonCount(CountBean bean);
    //历史总人数
    int totalPerson(Map<String,Object> map);
    //考试人次或者合格人次
    int TotalExamOrPass(Map<String,Object> map);

    //获取项目部列表
    List<BaseVO> selectDepartList();

}
