package com.escst.dictionary.controller;

import com.escst.commons.vo.ReturnJson;
import com.escst.dictionary.entity.DictionaryEntity;
import com.escst.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/10/31 15:34
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * @param entity
     * @return
     * @desc  通过类型查询数据字典
     * @author caozx
     * @date 2017/10/31 15:38
     */
    @RequestMapping("queryByType")
    @ResponseBody
    public ReturnJson queryByType(@RequestBody DictionaryEntity entity) {
        ReturnJson returnJson = null;
        try {
            String type = entity.getType();
            List<DictionaryEntity> list =  dictionaryService.queryByType(type);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail(e.getMessage());
            logger.error(e.getMessage(),e);
        }
        return returnJson;
    }
}
