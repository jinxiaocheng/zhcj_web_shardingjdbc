package com.escst.thirdPart.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.PageVo;
import org.springframework.stereotype.Service;
import org.tempuri.*;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:42 24/7/2018
 */
@Service
public class TrainRecordService {

    /**
    * @desc 获取培训记录
    * @param
    * @return
    * @author dwj
    * @date 24/7/2018 14:43
    */
    public PageVo getTrainRecord(Record record){
        PageVo pageVo = new PageVo();
        try{
            ItemInfoService it = ThirdPartService.getItemInfoService();
            ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
            String trainName = record.getTrainName();
            DictionaryEntry entry = new DictionaryEntry();
            ArrayOfDictionaryEntry dics = new ArrayOfDictionaryEntry();
            if(StringUtils.isNotBlank(trainName)){
                entry.setKey("TrainName");
                entry.setValue(trainName);
                dics.getDictionaryEntry().add(entry);
            }
            RecordList recordList = soap.getRecordPage(dics,record.getPage(),record.getRowNum());
            List<Record> list  = recordList.getList().getRecord();
            pageVo.setTotalRecord(recordList.getTotal());
            pageVo.setRows(list);
            pageVo.setTotalPage(recordList.getTotal() / record.getRowNum());
            pageVo.setCurrentPage(record.getPage());
            pageVo.setPageSize(record.getRowNum());
        }catch (Exception e){
            throw new EscstException("获取培训记录信息异常"+e.getMessage(),e);
        }

        return pageVo;
    }


}
