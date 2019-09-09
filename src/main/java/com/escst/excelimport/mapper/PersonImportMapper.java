package com.escst.excelimport.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @desc 人员导入
 * @author zhouwei
 * @date 2017年12月5日 上午9:28:27
 */
@Repository
public interface PersonImportMapper  {
	
	void createTempTable(String tableName);
	
	void batchInsert(Map<String, Object> params);
	
	void updateUserId(String tableName);
	
	void updateConstructionPersonId(String tableName);
	
	void updateCompanyId(String tableName);
	
	void updateTeamInfo(String tableName);
	
	void updateNewTeam(String tableName);
	
	void disabledPerson(String tableName);
	
	void disabledConstructionPerson(String tableName);
	
	void insertUser(String tableName);
	
	void insertPerson(String tableName);
	
	void insertConstructionPerson(String tableName);
	
	void insertCompany(String tableName);
	
	void insertTeam(String tableName);
}
