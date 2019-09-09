package com.escst.video.service;

import com.alibaba.fastjson.JSONArray;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.StringUtils;
import com.escst.video.entity.CameraEntity;
import com.escst.video.mapper.CameraMapper;
import com.escst.video.vo.NvrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2018/1/10 14:06
 */
@Service
public class NvrInfoService {

    @Autowired
    private CameraMapper cameraMapper;

    /***
     * 查询需要导入的nvr信息
     * @return
     */
    public List<NvrVO> queryNvrList() {
        return cameraMapper.selectExistsNvrList();
    }

    @Transactional
    public void saveNvrChannel(String data) {
        if (StringUtils.isBlank(data)) {
            throw new EscstException("通道信息为空");
        }
        List<CameraEntity> list = JSONArray.parseArray(data, CameraEntity.class);

        //批量保存通道信息
        cameraMapper.batchSave(list);
    }
}
