package com.escst.dictionary.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.dictionary.entity.DictionaryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/3/15 13:24
 */
@Repository
public interface DictionaryMapper extends BaseMapper<DictionaryEntity> {

    public List<String> selectAllType();

    public List<DictionaryEntity> selectByType(String type);

}
