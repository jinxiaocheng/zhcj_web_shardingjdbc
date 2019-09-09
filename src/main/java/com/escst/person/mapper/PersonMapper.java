package com.escst.person.mapper;

import java.util.List;
import java.util.Map;

import com.escst.attendance.vo.AttendanceClockRecordVo;
import com.escst.excelimport.vo.UpdatePersonVo;
import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.commons.vo.PageAuthVO;
import com.escst.commons.vo.QtyVO;
import com.escst.person.entity.ConstructionPersonEntity;
import com.escst.person.entity.PersonConditionBean;
import com.escst.person.entity.PersonEntity;
import com.escst.person.vo.PersonQueryVO;
import com.escst.person.vo.WorkTypeDateVO;
import com.escst.person.vo.WorkTypeQueryVO;
import com.escst.team.bean.TeamLeaderVO;

/**
 * @desc
 * @Author wy
 * @createDate 2017/3/9 11:27
 */
@Repository
public interface PersonMapper extends BaseMapper<PersonEntity> {

    List<Map<String, Object>> selectPersonList(PersonConditionBean personEntity);


    List<Map<String, Object>> queryPersonList(String constructionId);


    List<PersonEntity> getPersonById(String personId);

    void updatePersonStatus(String personId);

    void updateConstructionPersonStatus(String personId);

    void updateUserStatus(String userId);

    String selUserId(String personId);

    int selectCount(PersonConditionBean personEntity);

    int selectMaxJobNumber(String constructionId);

    void batchInsertConstructionPerson(List<ConstructionPersonEntity> list);


    String selectEnum(Map<String, Object> map);

    /**
     * 分页查询工地下的人员列表
     *
     * @param entity
     * @return
     */
    public List<PersonEntity> selectPersonByConstructionId(PersonEntity entity);

    /**
     * 查询工地下的人员总数
     *
     * @param entity
     * @return
     */
    public int selectPersonCount(PersonEntity entity);

    public int insertConstructionPersonInfo(ConstructionPersonEntity constructionPerson);

    /**
     * @param constructionPerson
     * @return
     * @desc 更新工地人员的所属公司和班组信息
     * @author zhouwei
     * @date 2017年4月25日 下午3:21:13
     */
    public int updateConstructionPersonInfo(ConstructionPersonEntity constructionPerson);

    public int update(PersonEntity personEntity);

    /**
     * @param constructionId
     * @return
     * @desc 根据工地ID得到班组或者工长的信息
     * @author zhouwei
     * @date 2017年7月12日 下午4:29:07
     */
    List<TeamLeaderVO> getTeamLeaderList(String constructionId);

    /**
     * @param authVO
     * @return
     * @desc 获取有权限查看的工地工种数
     * @author zhouwei
     * @date 2017年8月15日 下午4:43:23
     */
    List<QtyVO> queryAuthWorkTypePersonQty(PageAuthVO authVO);

    int getAuthWorkTypePersonQtyCount(PageAuthVO authVO);

    /**
     * @param authVO
     * @return
     * @desc
     * @author niejing
     * @date 2017年11月6日 下午1:48:48
     */
    List<WorkTypeDateVO> query7DaysWorkTypeQty(WorkTypeQueryVO authVO);

    /**
     * @param queryVO
     * @desc 获取工地上塔吊和司索工
     * @author dwj
     */
    List<Map<String, Object>> selectPersonByWorkType(PersonQueryVO queryVO);


    /**
     * @param userId
     * @return
     * @desc 根据当前用户ID查询有权限的人员
     * @author niejing
     * @date 2017年8月31日 上午9:59:04
     */
    List<PersonEntity> selectAuthPerson(String userId);

    /**
     * @param personQueryVO
     * @return
     * @desc 查询工地人员信息
     * @author zhouwei
     * @date 2017年11月2日 下午9:11:24
     */
    List<Map<String, Object>> selectConstructionPersonList(PersonQueryVO personQueryVO);

    /**
     * @param personQueryVO
     * @return
     * @desc 查询工地总的人数
     * @author zhouwei
     * @date 2017年11月2日 下午9:11:52
     */
    int selectConstructionPersonCount(PersonQueryVO personQueryVO);

    /**
     * @param params personId,constructionId
     * @return
     * @desc 根据人员ID和工地ID得到人员的信息
     * @author zhouwei
     * @date 2017年11月3日 上午11:40:39
     */
    ConstructionPersonEntity getConstructionPersonInfo(Map<String, Object> params);

    /**
     * @param params
     * @return
     * @desc 根据用户ID和工地ID得到人员列表
     * @author zhouwei
     * @date 2017年11月9日 下午7:18:36
     */
    List<PersonEntity> queryPersonByConstructionIdUserId(Map<String, Object> params);

    void insertConstructionPerson(ConstructionPersonEntity entity);

    /**
     * @param params
     * @return
     * @desc 根据工地ID查询该工地上的用户信息
     * @author caozx
     * @date 2017/12/5 09:35
     */
    List<PersonEntity> queryPersonByConstructionId(Map<String, Object> params);

    /**
     * @param params
     * @desc 根据用户ID和工地ID将旧工地的人员信息设置为无效
     * @author zhouwei
     * @date 2017年12月22日 下午12:03:07
     */
    void disabledPersonStatus(Map<String, Object> params);

    /**
     * @param params
     * @desc 根据用户ID和工地ID将旧工地的工地人员信息设置为无效
     * @author zhouwei
     * @date 2017年12月22日 下午12:04:04
     */
    void disabledConstructionPersonStatus(Map<String, Object> params);

    /**
     * @param params
     * @return
     * @desc 得到工地考勤总人数
     * @author zhouwei
     * @date 2017年5月2日 下午3:54:03
     */
    public int getTotalAttendanceNum(Map<String, Object> params);

    /**
     * @param params
     * @return
     * @desc 得到现场人员数量
     * @author zhouwei
     * @date 2017年5月2日 下午4:11:04
     */
    public int getLocalePersonNum(Map<String, Object> params);


    /**
     * @param entity
     * @return
     * @desc 添加人员信息
     * @author dwj
     * @date 2018/1/3 15:45
     */
    void insertPerosn(PersonEntity entity);

    /**
     * @return
     * @desc 查询所有的工地对应的value
     * @author niejing
     * @date 2018年4月25日 下午4:14:21
     */
    List<Map<String, Object>> getConstructionInfoAll();

    void updatePositionWorkId(Map<String, Object> map);

    /**
     * @param constructionId
     * @return
     * @desc 查询工地下的同步url
     * @author dwj
     * @date 2018/5/24 15:38
     */
    String selectSyncUrlByConstructionId(String constructionId);


    void updateBatchConstructionClientUserId(List<UpdatePersonVo> list);

    /**
     * @param projectCompanyId 分包公司ID
     * @return
     * @desc 根据分包公司获得班组列表
     * @author jincheng
     * @date 2018-6-4 10:55
     */
    List<PersonEntity> listTeam(String projectCompanyId);


    /**
     * @param entity
     * @return java.lang.String
     * @desc 根据工地ID, 分包公司名称获得分包公司ID
     * @author jincheng
     * @date 2018-6-5 11:27
     */
    String getProjectCompanyId(PersonEntity entity);


    /**
     * @param entity
     * @return java.lang.String
     * @desc 根据工地ID, 班组名称获得班组ID
     * @author jincheng
     * @date 2018-6-5 11:27
     */
    String getTeamId(PersonEntity entity);

    /**
     * @param entity
     * @return java.lang.String
     * @desc 根据工地ID, 岗位工种名称获得岗位工种ID
     * @author jincheng
     * @date 2018-6-5 11:27
     */
    String getPositionWorkTypeId(PersonEntity entity);


    /**
     * @param list
     * @return void
     * @desc 批量添加数据到人员表
     * @author jincheng
     * @date 2018-6-5 13:17
     */
    void batchInsertPerson(List<PersonEntity> list);


    /**
     * @param list
     * @return void
     * @desc 批量添加数据到人员表
     * @author jincheng
     * @date 2018-6-5 13:17
     */
    void batchInsertPersonConstruction(List<PersonEntity> list);

    String selectPersonId(PersonEntity entity);

    void batchUpdateHeadId(List<Map<String, String>> list);


    public List<AttendanceClockRecordVo> selectByConstructionId(Map<String, Object> map);

    int selectByCardNo(Map<String, Object> map);

}
