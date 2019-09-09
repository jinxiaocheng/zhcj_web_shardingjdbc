package com.escst.inspection.mapper;

import com.escst.inspection.entity.AccidentLossDetailEntity;
import com.escst.inspection.entity.AccidentReportEntity;
import com.escst.person.vo.PersonVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 13:44
 */
@Repository
public interface AccidentReportMapper {

    public List<Map<String, Object>> selectList(Map<String, Object> map);

    public int queryCount(Map<String, Object> map);

    public int insert(AccidentReportEntity accidentReportEntity);

    public int update(AccidentReportEntity accidentReportEntity);

    public int batchInsertAccidentLoss(List<AccidentLossDetailEntity> list);

    public List<Map<String,Object>> selectByLossType(AccidentLossDetailEntity accidentLossDetailEntity);

    public List<PersonVO> selectPersonNameByLossType(AccidentLossDetailEntity accidentLossDetailEntity);

    public Map<String,Object> selectById(String id);

}
