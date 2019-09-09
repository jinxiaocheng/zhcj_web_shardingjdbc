package com.escst.file.mapper;

import com.escst.file.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dwj
 * @desc
 * @date 13:43 2018/3/6
 */
@Repository
public interface FileMapper {

    List<FileEntity> queryByBusinessId(Map<String,Object> map);

    FileEntity queryById(Map<String,Object> map);
    
    void batchInsert(List<FileEntity> entity);
    
    List<FileEntity> queryListByConstructionId(String constructionId);
    
    void batchUpdatePath(List<FileEntity> list);

    void batchDelete(List<String> list);

}
