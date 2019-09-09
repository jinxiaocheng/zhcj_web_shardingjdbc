package com.escst.safeStudy.mapper;

import com.escst.safeStudy.entity.SafetyStudyEntity;
import com.escst.safeStudy.entity.SafetyStudyPersonEntity;
import com.escst.safeStudy.vo.SafetyStudyDetailVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 13:44
 */
@Repository
public interface SafetyStudyMapper {

    public int selectCount(Map<String, Object> map);

    public List<Map<String,Object>> selectList(Map<String, Object> map);

    public List<Map<String,Object>> selectPersonListById(String id);

    public int insert(SafetyStudyEntity safetyStudyEntity);

    public int batchInsertSafetyStudyPerson(List<SafetyStudyPersonEntity> list);

    public int update(SafetyStudyEntity safetyStudyEntity);

    public SafetyStudyDetailVO selectDetailInfoById(String id);

}
