package com.escst.quality.mapper;

import com.escst.inspection.vo.CheckItemsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 质量相关
 * @date 2018-7-3 14:12
 */
@Repository
public interface QualityMapper {


    void batchInsert(List<CheckItemsVO> checkItemsVO);


}
