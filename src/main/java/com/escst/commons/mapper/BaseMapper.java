package com.escst.commons.mapper;

import com.escst.commons.exception.EscstException;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T>{

	public T selectById(String id);
	
	public List<T> selectByIds(List<?> ids);

	public List<T> selectList(T t);
	
	/**
	 * 通过sql limit start,pageSize实现分页
	 * 解决mybatis nested result mappings bug
	 * @param t
	 * @return
	 */
	public List<T> selectListByPage(T t);
	
	/**
	 * 通过RowBounds实现分页
	 * @param t
	 * @param rowBounds
	 * @return
	 * @throws EscstException
	 */
	public List<T> selectListByPage(T t, RowBounds rowBounds);

	public List<T> selectAll();

	public int selectCount(T t);

	public void insert(T t);

	public void batchInsert(List<T> list);

	public int update(T t);

	public void updateById(T t);

	public void batchUpdate(List<T> list);
	
	public void removeById(String id);

	public void removeByT(T t);
	
	public void removeByCondition(Map<String, Object> paraMap);
	
	public void batchRemove(List<?> ids);

	public List<Map<String,Object>> selectListMap(T t);
}
