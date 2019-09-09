package com.escst.highformwork.mapper;

import com.escst.highformwork.bean.CollectorTimeBean;
import com.escst.highformwork.bean.FlowCollectorBean;
import com.escst.highformwork.entity.CollectorEntity;
import com.escst.highformwork.entity.FlowEntity;
import com.escst.highformwork.vo.CollectorVo;
import com.escst.highformwork.vo.FlowVo;
import com.escst.highformwork.vo.UpdateVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 16:30 16/7/2018
 */
@Repository
public interface HighformworkMapper {

    /**
     *批量添加采集器
     **/
    void batchInsert(List<CollectorEntity> list);
    /**
     *停用启用采集器
     **/
    void disableOrEnable(CollectorEntity entity);

    /**
     *批量修改采集器
     **/
    void batchUpdate(UpdateVo vos);
    /**
     *批量新增流水段采集器中间表
     **/
    void batchFlowCollector(List<FlowCollectorBean> list);
    /**
     *修改流水段采集器中间表
     **/
    void updateFlowCollector(FlowCollectorBean b);

    /**
     *获取采集器列表
     **/
    List<CollectorVo> selectCollectorAll(Map<String, Object> map);

    CollectorEntity selectCollectorById(String id);

    /**
     * 获取采集器列表总数
     * */
    int collectorCount(Map<String,Object> map);

    List<CollectorVo> selectVaildCollectorByConstructionId(String constructionId);

    /**
     *判断采集器是否有flowId
     **/
    String isFlowId(String id);

    /**
     *新增流水段
     **/
    void insertFlow(FlowEntity entity);

    //修改流水段
    void updateFlow(FlowEntity entity);

    /**
     *获取流水段列表
     **/
    List<FlowVo> selectAllByConstructionId(Map<String, Object> map);

    /**
     * 获取流水段数量
     **/
    int flowCount(Map<String,Object> map);

    /**
     *获取当前流水段开始时间
     **/
    String  selectMinTimeByCollectorId(CollectorTimeBean bean);
    /**
     *获取当前流水段结束时间
     **/
    String selectMaxTimeByCollectorId(CollectorTimeBean bean);
    /**
     *获取流水段详情信息
     **/
    FlowVo selectFlowDetail(String id);


    /**
     *获取流水段图
     **/
    String selectFilePath(String flowId);

    List<CollectorVo> selectCollectorByFlowId(String flowId);

    List<String> selectCollectorIdsByFlowId(Map<Object,Object> map);

    String isUse(String id);

    void batchUpdateFlow(UpdateVo vo);

    List<String> selectCollectorFlowId(Map<Object,Object> map);

}
