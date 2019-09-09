package com.escst.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escst.commons.exception.EscstException;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 13:59
 */
public class ExcelUtils {
	
	 private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	
    /**
     * Excel文件到List
     * @param fileName
     * @param sheetIndex    // 工作表索引
     * @param skipRows  // 跳过的表头
     * @return
     * @throws Exception
     */
    public static List<Object> readToList(String fileName, int sheetIndex, int skipRows) throws Exception{
        List<Object> ls = new ArrayList<Object>();
        Workbook wb = loadWorkbook(fileName);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            for (int i = skipRows; i < rows; i++) {
                Row row = sh.getRow(i);
                if(null==row){
                    break;
                }
                int cells = row.getPhysicalNumberOfCells();
                if(cells==0){
                    continue;
                }
                List<String> r = new ArrayList<String>(cells);
                for (int c = 0; c < cells; c++) {
                    if(c == 0 || c== 4){
                        try{
                            r.add(String.format("%.0f", row.getCell(c).getNumericCellValue()));
                        }catch(Exception e){
                            throw new Exception("出现该错误请依次检查：<br>1、【序号】或【端口号】请使用数字<br>2、检查《Webservice信息》是否是第二个sheet页");
                        }
                    }else{
                        r.add(row.getCell(c).getStringCellValue());
                    }
                }
                ls.add(r);
            }
        }

        return ls;
    }

    /**
     * 读取Excel文件，支持2000与2007格式
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook loadWorkbook(String fileName) throws Exception {
        if (null == fileName)
            return null;

        Workbook wb = null;
        if (fileName.toLowerCase().endsWith(".xls")) {
            try {
                InputStream in = new FileInputStream(fileName);
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb = new HSSFWorkbook(fs);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (fileName.toLowerCase().endsWith(".xlsx")) {
            try {
                InputStream in = new FileInputStream(fileName);
                wb = new XSSFWorkbook(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            throw new Exception("不是一个有效的Excel文件");
        }
        return wb;
    }

    public static void writeToExcel(Workbook wb, OutputStream out){
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static enum ExcelType{
        xls, xlsx;
    }
    public static Workbook listToWorkbook(List<?> rows, ExcelType type){
        Workbook wb = null;
        if(ExcelType.xls.equals(type)){
            wb = new HSSFWorkbook();
        }else if(ExcelType.xlsx.equals(type)){
            wb = new XSSFWorkbook();
        }else{
            return null;
        }

        Sheet sh = wb.createSheet();
        if(null!=rows){
            for(int i=0; i<rows.size(); i++){
                Object obj = rows.get(i);
                Row row = sh.createRow(i);

                if (obj instanceof Collection) {
                    Collection<?> r = (Collection<?>) obj;
                    Iterator<?> it = r.iterator();
                    int j = 0;
                    while(it.hasNext()){
                        Cell cell = row.createCell(j++);
                        cell.setCellValue(String.valueOf(it.next()));
                    }
                }else if(obj instanceof Object[]){
                    Object[] r = (Object[]) obj;
                    for(int j=0; j<r.length; j++){
                        Cell cell = row.createCell(j);
                        cell.setCellValue(String.valueOf(r[j]));
                    }
                }else{
                    Cell cell = row.createCell(0);
                    cell.setCellValue(String.valueOf(obj));
                }
            }
        }

        return wb;
    }

    /**
     * @desc 
     * @desc 根据模板生成Excel文件.
     * @param templateFileName 模板文件
     * @param beanParams 模板中存放的数据
     * @return 
     * @author zhouwei
     * @date 2017年9月21日 下午5:44:30
     */
    public static String createExcel(String templateFileName, Map<String, Object> beanParams) {
    	String resultFileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
    	return createExcel(templateFileName, beanParams, resultFileName);
    }
    
    
    /**
     * @desc 根据模板生成Excel文件,返回文件在程序中的相对路径
     * @param templateFileName 模板文件
     * @param beanParams 模板中存放的数据
     * @param resultFileName 生成的文件
     * @author zhouwei
     * @date 2017年9月21日 下午5:16:14
     */
	public static String createExcel(String templateFileName, Map<String, Object> beanParams, String resultFileName) {
		// 创建XLSTransformer对象
		XLSTransformer transformer = new XLSTransformer();
		// 获取java项目编译后根路径
		String localPath = ContextUtils.getSession().getServletContext().getRealPath("/");
		String relativePath = "resources" + File.separator + "static";//文件的相对路径
		String templateRelativePath = relativePath + File.separator + "template" + File.separator + templateFileName;//模版的相对路径
		String downloadRelativePath = relativePath + File.separator + "download" + File.separator + resultFileName;//下载文件的相对路径
		// 得到模板文件路径
		String srcFilePath = localPath + templateRelativePath;
		logger.debug("excel的模版文件路径为:" + srcFilePath);
		String destFilePath = localPath + downloadRelativePath;
		logger.debug("生成的excel的文件路径为:" + destFilePath);
		try {
			// 生成Excel文件
			transformer.transformXLS(srcFilePath, beanParams, destFilePath);
			// 本地访问的路径
			return downloadRelativePath;
		}
		catch (Exception e) {
			logger.error("根据模版生成excel出现异常", e);
			throw new EscstException("根据模版生成excel出现异常");
		}
	}
}
