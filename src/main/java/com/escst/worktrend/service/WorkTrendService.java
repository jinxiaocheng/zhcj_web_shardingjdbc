package com.escst.worktrend.service;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.user.entity.UserEntity;
import com.escst.worktrend.entity.WorkTrendEntity;
import com.escst.worktrend.mapper.WorkTrendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 14:45 2018/2/23
 */
@Service
public class WorkTrendService {

    /**
     * 第二次请求
     */
    private static final int REPEAT_REQUEST = 2;

    @Autowired
    private WorkTrendMapper workTrendMapper;

    public List<WorkTrendEntity> queryList(WorkTrendEntity entity, int type, int number) {
        List<WorkTrendEntity> list = new ArrayList<WorkTrendEntity>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String constructionId = entity.getConstructionId();
        if (StringUtils.isNotBlank(constructionId)) {
            dataMap.put("constructionId", constructionId);
        } else {
            UserEntity userEntity = ContextUtils.getCurrentUser();
            List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
            dataMap.put("constructionList", constructionList);
        }
        if (type == REPEAT_REQUEST) {
            int count = workTrendMapper.count(dataMap);
            if (count > number) {
                dataMap.put("offset", count - number);
                list = workTrendMapper.queryList(dataMap);
            }
        } else {
            list = workTrendMapper.queryList(dataMap);
        }

        return list;
    }
}
