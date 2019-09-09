package com.escst.person.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.person.entity.AcsControlEntity;
import com.escst.person.mapper.AcsControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc 设备相关
 * @date 13:56 2017/12/18
 */
@Service
public class AcsControlService {

    @Autowired
    private AcsControlMapper acsControlMapper;

    /**
     * @param entity
     * @return
     * @desc 查询考勤控制器列表
     * @author dwj
     * @date 2017/12/18 14:17
     */
    public PageVo queryList(AcsControlEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            int count = acsControlMapper.queryCount(entity.getConstructionId());
            if (count == 0) {
                return pageVo;
            }
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);

            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<AcsControlEntity> controlEntityList = acsControlMapper.queryList(entity);
            pageVo.setRows(controlEntityList);
        } catch (Exception e) {
            throw new EscstException("查询控制器列表失败" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * @param entity
     * @return
     * @desc 新增或者修改考勤控制器
     * @author dwj
     * @date 2017/12/18 14:17
     */
    @Transactional
    public void saveOrUpdate(AcsControlEntity entity) {
        try {
            String id = entity.getId();
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                entity.setId(id);
                entity.setCreateTime(new Date());
                acsControlMapper.save(entity);
            } else {
                acsControlMapper.update(entity);
            }
        } catch (Exception e) {
            throw new EscstException("新增或修改设备控制器失败" + e.getMessage(), e);
        }
    }

    /**
     * @param id
     * @return
     * @desc 通过Id查询控制器
     * @author dwj
     * @date 2017/12/19 11:09
     */
    public AcsControlEntity queryById(String id) {
        AcsControlEntity entity = new AcsControlEntity();
        try {
            entity = acsControlMapper.queryById(id);
        } catch (Exception e) {
            throw new EscstException("通过Id查询控制器失败" + e.getMessage(), e);
        }
        return entity;
    }

    /**
     * @param
     * @return
     * @desc 查询没有分配大门的控制器
     * @author jincheng
     * @date 2018-5-20 14:48
     */
    public List<AcsControlEntity> listControllerNoDoor(String constructionId) {
        return acsControlMapper.listControllerNoDoor(constructionId);
    }


}
