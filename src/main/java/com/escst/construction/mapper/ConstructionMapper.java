package com.escst.construction.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.escst.commons.mapper.BaseMapper;
import com.escst.commons.vo.QtyVO;
import com.escst.construction.entity.ConstructionEntity;
import com.escst.construction.vo.SimpleConstructionVO;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 16:57
 */
@Repository
public interface ConstructionMapper extends BaseMapper<ConstructionEntity> {

    /**
     * 通过区id查询该区域下的工地信息
     *
     * @param entity
     * @return
     */
    public List<ConstructionEntity> selectByArea(ConstructionEntity entity);

    public Map<String, Object> selectByConstructionId(String id);

    /**
     * 查询所有工地所在的城市
     *
     * @return
     * @desc
     * @author niejing
     * @date 2017年4月26日 上午11:06:00
     */
    public List<Map<String, Object>> selectConstructionCity();

    /**
     * @return
     * @desc 根据城市Id查询该城市下所有的区域
     * @author niejing
     * @date 2017年4月26日 上午11:09:34
     */
    public List<Map<String, Object>> selectAreaByCityCode(String belongCity);

    /**
     * @param areaCode
     * @return
     * @desc 根据区域id查找区域下的所有工地
     * @author niejing
     * @date 2017年4月26日 上午11:18:42
     */
    public List<ConstructionEntity> selectConstByAreaCode(String areaCode);

    /**
     * @param userId
     * @return
     * @desc 获取用户有权限访问的工地
     * @author zhouwei
     * @date 2017年8月2日 上午9:55:35
     */
    List<SimpleConstructionVO> selectAuthConstructionByUserId(String userId);

    public List<Map<String, Object>> selectConstructionList(ConstructionEntity entity);

    public int selectConstructionCount(ConstructionEntity entity);

    /**
     * @param userId
     * @return
     * @desc 查询有权限查看的工地在各区的数量
     * @author zhouwei
     * @date 2017年8月25日 下午2:20:14
     */
    List<QtyVO> selectAuthAreaConstructionQty(String userId);

    int queryVisibleConstructionCount(String userId);

    /**
     * @param userId
     * @return
     * @desc 查询该用户权限下的有检查部位的工地
     * @author niejing
     * @date 2017年8月30日 上午9:09:01
     */
    List<SimpleConstructionVO> queryVisibleConstruction(String userId);

    /**
     * @param id
     * @return
     * @desc 通过id查询工地信息
     * @author caozx
     * @date 2018/2/24 16:54
     */
    SimpleConstructionVO queryById(String id);

    Map<String, Object> queryByUserId(String id);

    String getSyncUrl(String constructionId);

    /**
     * @param constructionId
     * @return
     * @desc 通过工地id查询所有卡号
     * @author jincheng
     * @date 2018/2/24 16:54
     */
    List<String> listCardNumber(String constructionId);
}
