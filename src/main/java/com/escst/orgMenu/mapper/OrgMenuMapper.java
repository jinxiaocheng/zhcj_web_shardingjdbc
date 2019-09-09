package com.escst.orgMenu.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.menu.entity.MenuEntity;
import com.escst.orgMenu.entity.OrgMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 机构菜单相关mapper
 * @date 2018/1/17 12:01
 */
@Repository
public interface OrgMenuMapper extends BaseMapper<OrgMenuEntity> {

    /**
     * @param orgId 机构id
     * @return java.util.List<com.escst.orgMenu.entity.OrgMenuEntity>
     * @desc 根据机构id，查询出机构菜单表中的数据
     * @author jincheng
     * @date 2018/1/17 12:01
     */
    List<OrgMenuEntity> selectByOrgId(String orgId);

    void removeByIdAndOrgId(MenuEntity entity);

}
