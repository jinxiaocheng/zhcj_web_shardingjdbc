package com.escst.excelimport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escst.commons.exception.EscstException;
import com.escst.excelimport.mapper.PersonImportMapper;
import com.escst.excelimport.vo.IExcelImportVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月5日 下午1:26:26
 */
@Service
public class PersonExcelImportService extends AbstractExcelImportService {
	
	@Autowired
	private PersonImportMapper personImportMapper;
	
	/**
	 * @desc 创建临时表
	 * @param tableName 
	 * @author zhouwei
	 * @date 2018年1月5日 下午1:50:05
	 */
	@Override
	protected void createTempTable(String tableName) {
		personImportMapper.createTempTable(tableName);
	}
	
	@Override
	protected <T extends IExcelImportVO> void insertData(String tableName, List<T> list) throws EscstException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("list", list);
		personImportMapper.batchInsert(params);
	}

	@Override
	protected void processData(String tableName) throws EscstException {
		// 根据手机号码更新user_id
//		personImportMapper.updateUserId(tableName);
		// 更新person_id和t_basic_construction_person的ID
		personImportMapper.updateConstructionPersonId(tableName);
		// 更新company_id
		personImportMapper.updateCompanyId(tableName);
		// 更新team_id和team_name
		personImportMapper.updateTeamInfo(tableName);
		personImportMapper.updateNewTeam(tableName);
	}

	@Override
	protected void importData(String tableName) throws EscstException {
		personImportMapper.disabledPerson(tableName);
		personImportMapper.disabledConstructionPerson(tableName);
//		personImportMapper.insertUser(tableName);
		personImportMapper.insertPerson(tableName);
		personImportMapper.insertCompany(tableName);
		personImportMapper.insertTeam(tableName);
		personImportMapper.insertConstructionPerson(tableName);
	}
}
