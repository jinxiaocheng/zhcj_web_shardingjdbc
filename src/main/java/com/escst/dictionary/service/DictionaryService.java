package com.escst.dictionary.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.escst.dictionary.entity.DictionaryEntity;
import com.escst.dictionary.mapper.DictionaryMapper;
import com.escst.redis.annotation.RedisCache;

/**
 * @author caozx
 * @desc
 * @date 2017/9/8 13:29
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

	/**
	 * @desc 得到数据字典的map
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月14日 上午11:45:58
	 */
	@RedisCache
	public Map<String, List<DictionaryEntity>> queryDictionaryListMap() {
		Map<String, List<DictionaryEntity>> map = new HashMap<String, List<DictionaryEntity>>();
		List<DictionaryEntity> list = dictionaryMapper.selectAll();
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		for (DictionaryEntity entity : list) {
			String type = entity.getType();
			if (map.containsKey(type)) {
				List<DictionaryEntity> entityList = map.get(type);
				entityList.add(entity);
			}
			else {
				List<DictionaryEntity> entityList = new ArrayList<DictionaryEntity>();
				entityList.add(entity);
				map.put(type, entityList);
			}
		}
		return map;
	}

	/**
	 * @desc 将数据字典的对象转换为map
	 * @return 
	 * @author zhouwei
	 * @date 2017年11月14日 下午12:01:22
	 */
	@RedisCache
	public Map<String, DictionaryEntity> getDictionaryMap() {
		Map<String, DictionaryEntity> map = new HashMap<String, DictionaryEntity>();
		List<DictionaryEntity> list = dictionaryMapper.selectAll();
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		for (DictionaryEntity entity : list) {
			String type = entity.getType();
			int value = entity.getValue();
			map.put(type + "_" + value, entity);
		}
		return map;
	}

	/**
	 * @desc 将数据字典的对象转换为map
	 * @return
	 * @author zhouwei
	 * @date 2017年11月14日 下午12:01:22
	 */
	@RedisCache
	public Map<String, DictionaryEntity> getDictionaryMapByType(String type) {
		Map<String, DictionaryEntity> map = new HashMap<String, DictionaryEntity>();
		List<DictionaryEntity> list = dictionaryMapper.selectByType(type);
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		for (DictionaryEntity entity : list) {
			int value = entity.getValue();
			map.put(type + "_" + value, entity);
		}
		return map;
	}
    
	/**
	 * @desc 根据类型得到所有的选项值
	 * @param type
	 * @return 
	 * @author zhouwei
	 * @date 2017年10月23日 下午4:52:39
	 */
	@RedisCache
	public List<DictionaryEntity> queryByType(String type) {
		DictionaryService service = (DictionaryService)AopContext.currentProxy();
		Map<String, List<DictionaryEntity>> map = service.queryDictionaryListMap();
		if (map.containsKey(type)) {
			return map.get(type);
		}
		return new ArrayList<DictionaryEntity>();
	}
	
	/**
	 * @desc 根据类型和值得到数据字典对象
	 * @param type
	 * @param value
	 * @return 
	 * @author zhouwei
	 * @date 2017年10月30日 下午2:06:33
	 */
	public DictionaryEntity getByTypeValue(String type, int value) {
		DictionaryService service = (DictionaryService)AopContext.currentProxy();
		Map<String, DictionaryEntity> map = service.getDictionaryMap();
		String key = type + "_" + value;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		return new DictionaryEntity();
	}
	
	/**
	 * @desc 根据类型和名称得到对应的value值
	 * @param type
	 * @param name
	 * @return 
	 * @author zhouwei
	 * @date 2017年10月23日 下午4:53:10
	 */
	@RedisCache
	public Integer getValueByTypeName(String type, String name) {
		DictionaryService service = (DictionaryService)AopContext.currentProxy();
		List<DictionaryEntity> list = service.queryByType(type);
		for (DictionaryEntity entity : list) {
			if (entity.getName().equals(name)) {
				return entity.getValue();
			}
		}
		return null;
	}
}
