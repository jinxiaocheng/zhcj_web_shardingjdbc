package com.escst.material.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialApproachEntity;
import com.escst.material.entity.MaterialEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author niejing
 * @desc
 * @date 2017年8月21日 下午5:02:20
 */
@Repository
public interface MaterialApproachMapper extends BaseMapper<MaterialApproachEntity> {

    List<Map<String, Object>> selectMaterialApproachList(MaterialBean entity);

    int selectApproachCount(MaterialBean entity);

    void insertMaterialApproachInfo(MaterialApproachEntity materialEntity);

    Map<String, Object> selectApproachDetailInfo(MaterialApproachEntity entity);

    Float selectAvailableQuantity(String approachId);

    void updateAvailableQuantity(Map<String, Object> map);

    List<Map<String, Object>> selectAvailableMApproachList(String userId);

    List<Map<String, Object>> selectApproach();

    /**
     * @desc 将材料ID更新到材料进场表
     * @author niejing
     * @date 2017年8月22日 上午10:13:37
     */
    void batchUpdateId();

    List<Map<String, Object>> queryMaterialAvailableList(MaterialBean materialBean);

    int queryMaterialAvailableCount(MaterialBean materialBean);

    /**
     * @param entity
     * @return int
     * @desc 查询地磅列表总数
     * @author jincheng
     * @date 2018-4-24 11:02
     */
    int getWeighbridgeCount(MaterialApproachEntity entity);


    /**
     * @param entity
     * @return java.util.List<com.escst.material.entity.MaterialApproachEntity>
     * @desc 查询地磅列表信息
     * @author jincheng
     * @date 2018-4-24 11:34
     */
    List<MaterialApproachEntity> listWeighbridge(MaterialApproachEntity entity);



    /**
     * @param entity
     * @return
     * @desc 新增材料时，根据工地ID 返回最新一条地磅重量，车辆图片
     * @author jincheng
     * @date 2018-6-13 10:58
     */
    MaterialApproachEntity getWeighbridge(MaterialApproachEntity entity);

    /**
     * @param weighbridgeId
     * @return void
     * @desc 修改地磅材料为已进场
     * @author jincheng
     * @date 2018-6-13 14:05
     */
    void updateWeighbridge(String weighbridgeId);

    String selectLatestOrderNo(Map<String, Object> map);


}
