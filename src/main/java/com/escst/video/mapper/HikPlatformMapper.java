package com.escst.video.mapper;

import org.springframework.stereotype.Repository;
import com.escst.video.vo.VideoLoginVO;


/**
 * @author caozx
 * @desc
 * @date 2017/2/21 9:30
 */
@Repository
public interface HikPlatformMapper {

    /**
     * @desc 得到海康平台信息
     * @param constructionId
     * @return 
     * @author zhouwei
     * @date 2017年8月25日 下午4:07:52
     */
    VideoLoginVO getPlatform(String constructionId);
}
