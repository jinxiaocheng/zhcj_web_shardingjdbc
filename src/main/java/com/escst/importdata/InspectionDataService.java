package com.escst.importdata;

import com.escst.commons.utils.UuidUtils;
import com.escst.inspection.entity.InspectionEntity;
import com.escst.inspection.entity.InspectionResultsEntity;
import com.escst.inspection.mapper.InspectionMapper;
import com.escst.inspection.vo.CheckItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jincheng
 * @desc 检查结果作为子节点插入到检查项表中
 * @date 2018-5-16 10:20
 */
@Service
public class InspectionDataService {

    @Autowired
    private InspectionMapper inspectionMapper;


    @Transactional
    public void data() throws InterruptedException {

        // 查询所有检查项
        List<InspectionEntity> itemsList = inspectionMapper.listItems();

//        int i = 0;
//        for (InspectionEntity inspectionEntity : itemsList) {
//            i += 10;
//            if (StringUtils.isNotBlank(inspectionEntity.getSortNum())) {
//                inspectionEntity.setSortNum(String.valueOf(i));
//            }
//        }

        for (InspectionEntity itemEntity : itemsList) {
            String itemsId = itemEntity.getItemsId();
            // 查询检查项对应的检查结果
            List<InspectionResultsEntity> resultsList = new ArrayList<InspectionResultsEntity>();
            resultsList = inspectionMapper.listResults(itemsId);
            if (CollectionUtils.isEmpty(resultsList)) {
                continue;
            }
            List<CheckItemsVO> checkItemsVOList = new ArrayList<CheckItemsVO>();
            int i = 0;
            for (InspectionResultsEntity resultsEntity : resultsList) {
                i += 10;
                // 把检查结果作为子节点添加到检查项中
                CheckItemsVO checkItemsVO = new CheckItemsVO();
                checkItemsVO.setId(UuidUtils.getUuid());
                checkItemsVO.setParentId(itemsId);
                checkItemsVO.setName(resultsEntity.getResults());
                checkItemsVO.setType(itemEntity.getType());
                checkItemsVO.setIsLeaf(0);
                checkItemsVO.setCreateTime(new Date());
                checkItemsVO.setSortNum(i);
//                Thread.sleep(300);
                checkItemsVOList.add(checkItemsVO);
            }
            if (!CollectionUtils.isEmpty(checkItemsVOList)) {
                inspectionMapper.batchInsertCheckItem(checkItemsVOList);
            }
        }
    }


}
