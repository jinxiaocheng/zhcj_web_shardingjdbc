package com.escst.inspection.mapper;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.vo.BaseVO;
import com.escst.equipment.vo.InspectionVO;
import com.escst.inspection.entity.InspectionCorrectiveProcessEntity;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.entity.InspectionResultsEntity;
import com.escst.inspection.entity.NotifyEntity;
import com.escst.inspection.vo.*;
import com.escst.safety.entity.RiskOperationEntity;
import com.escst.task.entity.ScheduledPlanEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 13:44
 */
@Repository
public interface InspectionMapper {

    public List<Map<String, Object>> selectList(Map<String, Object> map);


    public int insert(InspectionEntity inspectionEntity);

    public int update(InspectionEntity inspectionEntity);

    int queryInspectionCount(Map<String, Object> map);

    int queryCount(InspectionEntity inspectionEntity);

    public int insertCorrectiveProcess(InspectionCorrectiveProcessEntity inspectionCorrectiveProcessEntity);

    public List<CorrectiveDetailVO> selectCorrectiveProcessList(Map<String, Object> map);

    public Map<String, Object> listAsCount(Map<String, Object> map);

    public InspectionEntity selectById(String inspectionId);

    public InspectionDetailVO selectDetailInfoById(String id);

    /**
     * @param queryVO
     * @return
     * @desc 按月统计质量或安全检查和整改的数量
     * @author zhouwei
     * @date 2017年8月26日 上午11:05:23
     */
    List<InspectionQtyVO> queryInspectionMonthQtyList(InspectionQueryVO queryVO);

    /**
     * @param checkItemsVO
     * @return
     * @desc 得到检查项目的集合
     * @author zhouwei
     * @date 2017年10月17日 下午2:58:02
     */
    List<CheckItemsVO> selectCheckItemsList(CheckItemsVO checkItemsVO);

    /**
     * @desc 根据父检查项ID得到检查结果
     * @param parentItemsId
     * @return
     * @author zhouwei
     * @date 2017年10月18日 下午3:06:01
     */
//    List<CheckResultsVO> selectCheckResultsByParentItemsId(String parentItemsId);

    /**
     * @param list
     * @desc 批量插入检查结构
     * @author zhouwei
     * @date 2017年10月17日 下午5:05:32
     */
    void batchInsertCheckResults(List<InspectionResultsEntity> list);

    /**
     * @param inspectionId
     * @return
     * @desc 根据检查单ID得到检查结果
     * @author zhouwei
     * @date 2017年10月19日 上午10:02:25
     */
    List<InspectionResultsEntity> selectInspectionResultsByInspectionId(String inspectionId);


    /**
     * @return
     * @desc 得到安全检查项和检查结果的树形结构
     * @author zhouwei
     * @date 2017年11月21日 上午10:28:03
     */
    public List<CheckResultsVO> selectAllCheckResults();

    /**
     * @return
     * @desc 添加通知详细信息
     * @author hukang
     */

    public void insertNotifyEntity(NotifyEntity notifyEntity);

    /**
     * @param inspectionId
     * @return
     * @desc 根据检查单ID得到通知详情
     * @author hukang
     */
    public List<CheckResultsVO> selectNotifyEntity(String id);

    //	根据ID获取NotifyEntity集合
    public List<NotifyEntity> selectNotifyEntityById(String id);


    public List<CheckItemsVO> selectAllCheckItemsList();

    /**
     * @param inspectionId
     * @return
     * @desc 根据检查单ID查询检查项目和检查结果
     * @author hukang
     */
    public List<InspectionResultsEntity> selectInspectionItemsAndResultsByInspectionId(String inspectionId);

    Map<String, Object> selectWordInfo(String id);

    /**
     * @param inspectionId
     * @return
     * @desc 根据检查单ID得到检查项和检查结果
     * @author zhouwei
     * @date 2017年11月25日 下午3:13:54
     */
    List<InspectionResultsVO> queryResultsByInspectionId(String inspectionId);

    /**
     * @param inspectionId
     * @return void
     * @desc 查询检查单对应的检查部位(主表)
     * @method selectProjectStructureByInspectionId
     * @author jincheng
     * @date 2018/1/21 18:57
     */
    List<ScheduledPlanEntity> queryProjectStructureByInspectionId(String inspectionId);


    /**
     * @param inspectionId
     * @return void
     * @desc 查询检查单对应的检查部位(子表)
     * @method selectProjectStructureByInspectionId
     * @author jincheng
     * @date 2018/1/21 18:57
     */
    List<ScheduledPlanEntity> selectProjectStructureByInspectionId(String inspectionId);

    /**
     * @param list
     * @return void
     * @desc 批量添加检查单对应的检查部位信息
     * @method batchInsertProjectStructure
     * @author jincheng
     * @date 2018/1/21 18:41
     */
    void batchInsertProjectStructure(List<InspectionProjectStructureVO> list);


    /**
     * 查询分项检查数量
     *
     * @param entity
     * @return
     */
    int getCheckItemCount(TreeEntity entity);

    /**
     * 获得分项检查树集合
     *
     * @param entity
     * @return
     */
    List<TreeEntity> listCheckItemTree(TreeEntity entity);


    /**
     * @param list
     * @return com.escst.construction.vo.ScheduledPlanTree
     * @desc 判断是否是子节点
     * @author jincheng
     * @date 2018-4-8 17:25
     */
    List<TreeEntity> isParent(List<TreeEntity> list);

    /**
     * 添加分项检查
     *
     * @param treeEntity
     */
    void addCheckItem(TreeEntity treeEntity);


    void updateCheckItem(TreeEntity treeEntity);


    /**
     * @param constructionId 工地Id
     * @return java.util.List<java.lang.String>
     * @desc 查询所有的ID, 父ID
     * @author jincheng
     * @date 2018-4-9 11:23
     */
    List<TreeEntity> listAll(String constructionId);


    /**
     * @param list
     * @return void
     * @desc 删除分项检查
     * @author jincheng
     * @date 2018-4-9 9:53
     */
    void batchDelete(List<TreeEntity> list);

    String selectLatestOrderNo(Map<String, Object> map);

    List<CheckItemsVO> selectAll();

    void batchInsertCheckItem(List<CheckItemsVO> list);

    String getConstructionId(String id);

    List<BaseVO> selectItemsByConstructionId(Map<String, Object> map);

    String selectProjectStructureNameByInspectionId(String inspectionId);

    List<InspectionEntity> selectByType(Map<String, Object> map);

    void updateOrderNo(InspectionEntity entity);


    void batchInsertCheckItems(List<InspectionVO> list);

    int isHave(String id);

    List<InspectionVO> queryInspectionVOList(InspectionVO inspectionVO);

    List<InspectionVO> selectItemByInspectionId(String inspectionId);

    List<InspectionResultsEntity> listResults(String itemsId);

    List<InspectionEntity> listItems();

    List<InspectionVO> listInspection(InspectionEntity entity);

    List<InspectionVO> listInspectionIsPush(InspectionEntity entity);

    void batchUpdate(List<InspectionVO> list);
}
