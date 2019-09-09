package com.escst.excelimport.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.StringUtils;
import com.escst.excelimport.mapper.ExcelImportMapper;
import com.escst.excelimport.vo.IExcelImportVO;

/**
 * @desc
 * @author zhouwei
 * @date 2017年12月5日 上午9:30:18
 */
public abstract class AbstractExcelImportService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExcelImportMapper excelImportMapper;

	public String getUuid(int length) {
		String str = "abcdefghijklmnopqrstuvwxyz_0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		int charLen = str.length();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(charLen);// [0,62)
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @desc 创建临时表的表名
	 * @return
	 * @author zhouwei
	 * @date 2017年12月5日 上午10:26:22
	 */
	private String getTempTableName() {
		return "Z_" + getUuid(16);
	}

	/**
	 * @desc 创建临时表
	 * @param tableName 
	 * @author zhouwei
	 * @date 2018年1月5日 下午1:50:05
	 */
	protected abstract void createTempTable(String tableName);

	/**
	 * @desc 处理临时表的数据
	 * @param tableName
	 * @author zhouwei
	 * @date 2017年12月5日 上午11:27:30
	 */
	protected abstract void processData(String tableName) throws EscstException;

	protected abstract <T extends IExcelImportVO> void insertData(String tableName, List<T> list) throws EscstException;

	/**
	 * @desc 导入数据
	 * @param tableName
	 * @throws EscstException
	 * @author zhouwei
	 * @date 2017年12月5日 下午12:49:03
	 */
	protected abstract void importData(String tableName) throws EscstException;

	public synchronized <T extends IExcelImportVO> void execute(List<T> list) {
		String tableName = getTempTableName();
		try {
			createTempTable(tableName);
			// 插入数据
			insertData(tableName, list);
			// 对数据做处理
			processData(tableName);
			// 导入数据
			importData(tableName);
		}
		catch (Exception e) {
			if (e instanceof EscstException) {
				throw (EscstException) e;
			} else {
				logger.error("excel导入异常", e);
				throw new EscstException("excel导入异常");
			}
		}
		finally {
			if (StringUtils.isNotBlank(tableName)) {
				try {
					excelImportMapper.dropTempTable(tableName);
				}
				catch (Exception e) {
				}
			}
		}
	}


}
