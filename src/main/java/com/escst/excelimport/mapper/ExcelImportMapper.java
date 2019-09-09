package com.escst.excelimport.mapper;

import org.springframework.stereotype.Repository;

/**
 * @desc excel导入
 * @author zhouwei
 * @date 2017年12月5日 上午9:28:27
 */
@Repository
public interface ExcelImportMapper  {
	
	/**
	 * @desc 根据sql创建临时表
	 * @param sql 
	 * @author zhouwei
	 * @date 2017年12月5日 上午9:34:25
	 */
	void executeSql(String sql);
	
	/**
	 * @desc 删除临时表
	 * @param tableName 
	 * @author zhouwei
	 * @date 2017年12月5日 上午9:34:35
	 */
	void dropTempTable(String tableName);
}
