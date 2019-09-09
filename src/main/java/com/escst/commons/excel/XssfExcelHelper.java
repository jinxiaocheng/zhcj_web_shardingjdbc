package com.escst.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.escst.commons.reflect.ReflectUtil;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.projectStructure.entity.ProjectStructureExcelEntity;

/**
 * @author caozx
 * @desc 基于POI实现的Excel工具类
 * @date 2017/3/14 17:14
 */
public class XssfExcelHelper extends ExcelHelper {

	private static XssfExcelHelper instance = null; // 单例对象

	private File file; // 操作文件

	/**
	 * 私有化构造方法
	 *
	 * @param file 文件对象
	 */
	private XssfExcelHelper(File file) {
		super();
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 获取单例对象并进行初始化
	 *
	 * @param file 文件对象
	 * @return 返回初始化后的单例对象
	 */
	public static XssfExcelHelper getInstance(File file) {
		if (instance == null) {
			// 当单例对象为null时进入同步代码块
			synchronized (XssfExcelHelper.class) {
				// 再次判断单例对象是否为null，防止多线程访问时多次生成对象
				if (instance == null) {
					instance = new XssfExcelHelper(file);
				}
			}
		} else {
			// 如果操作的文件对象不同，则重置文件对象
			if (!file.equals(instance.getFile())) {
				instance.setFile(file);
			}
		}
		return instance;
	}

	/**
	 * 获取单例对象并进行初始化
	 *
	 * @param filePath 文件路径
	 * @return 返回初始化后的单例对象
	 */
	public static XssfExcelHelper getInstance(String filePath) {
		return getInstance(new File(filePath));
	}

	@Override
	public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames, int sheetNo, boolean hasTitle) throws Exception {
		List<T> dataModels = new ArrayList<T>();
		// 获取excel工作簿
		// XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		// XSSFSheet sheet = workbook.getSheetAt(sheetNo);
		// 解决excel兼容性问题 http://blog.csdn.net/blueheart20/article/details/45028311
		Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
		Sheet sheet = workbook.getSheetAt(sheetNo);
		int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // 如果有标题则从第二行开始
		for (int i = start; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			// 生成实例并通过反射调用setter方法
			T target = clazz.newInstance();
			for (int j = 0; j < fieldNames.length; j++) {
				String fieldName = fieldNames[j];
				if (fieldName == null || UID.equals(fieldName)) {
					continue; // 过滤serialVersionUID属性
				}
				// 获取excel单元格的内容
				Cell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				String content = getCellContent(cell);
				// 如果属性是日期类型则将内容转换成日期对象
				if (isDateType(clazz, fieldName)) {
					// 如果属性是日期类型则将内容转换成日期对象
					ReflectUtil.invokeSetter(target, fieldName, DateUtils.parse(content, "yyyy-MM-dd"));
				} else {
					Field field = clazz.getDeclaredField(fieldName);
					// 将导入的字符去掉空格 2017-12-06 zhouwei
					content = StringUtils.isBlank(content) ? "" : content.trim();
					ReflectUtil.invokeSetter(target, fieldName, parseValueWithType(content, field.getType()));
				}
			}
			dataModels.add(target);
		}
		return dataModels;
	}

	@Override
	public <T> void writeExcel(Class<T> clazz, List<T> dataModels, String[] fieldNames, String[] titles, HttpServletResponse response)
			throws Exception {
		XSSFWorkbook workbook = null;
		// 检测文件是否存在，如果存在则修改文件，否则创建文件
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} else {
			workbook = new XSSFWorkbook();
		}
		// 根据当前工作表数量创建相应编号的工作表
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow headRow = sheet.createRow(0);
		// 添加表格标题
		for (int i = 0; i < titles.length; i++) {
			XSSFCell cell = headRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(titles[i]);
			// 设置字体加粗
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			// 设置自动换行
			cellStyle.setWrapText(true);
			cell.setCellStyle(cellStyle);
			// 设置单元格宽度
			sheet.setColumnWidth(i, titles[i].length() * 1000);
		}
		// 添加表格内容
		for (int i = 0; i < dataModels.size(); i++) {
			T target = dataModels.get(i);
			XSSFRow row = sheet.createRow(i + 1);
			// 遍历属性列表
			for (int j = 0; j < fieldNames.length; j++) {
				// 通过反射获取属性的值域
				String fieldName = fieldNames[j];
				if (fieldName == null || UID.equals(fieldName)) {
					continue; // 过滤serialVersionUID属性
				}
				Object result = ReflectUtil.invokeGetter(target, fieldName);
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(StringUtils.toString(result));
				// 如果是日期类型则进行格式化处理
				if (isDateType(clazz, fieldName)) {
					cell.setCellValue(DateUtils.format((Date) result, "yyyy-MM-dd"));
				}
			}
		}
		// 将数据写到磁盘上
		OutputStream fos = null;
		try {
			fos = response.getOutputStream();
			workbook.write(fos);
		}
		finally {
			if (fos != null) {
				fos.close(); // 不管是否有异常发生都关闭文件输出流
			}
		}
	}

	@Override
	protected <T> Object parseValueWithType(String value, Class<?> type) {
		// 由于Excel2007的numeric类型只返回double型，所以对于类型为整型的属性，要提前对numeric字符串进行转换
		if (Byte.TYPE == type || Short.TYPE == type || Long.TYPE == type || Integer.TYPE == type) {
			value = String.valueOf((long) Double.parseDouble(value));
		}
		return super.parseValueWithType(value, type);
	}

	/**
	 * 获取单元格的内容
	 *
	 * @param cell 单元格
	 * @return 返回单元格内容
	 */
	private String getCellContent(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC: // 数字
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				// cellValue = cell.getStringCellValue();
				// break;
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
						sdf = new SimpleDateFormat("HH:mm");
					} else {// 日期
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date date = cell.getDateCellValue();
					cellValue = sdf.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 58) {
					// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
					cellValue = sdf.format(date);
				} else {
					double value = cell.getNumericCellValue();
					CellStyle style = cell.getCellStyle();
					DecimalFormat format = new DecimalFormat();
					String temp = style.getDataFormatString();
					// 单元格设置成常规
					if (temp.equals("General")) {
						format.applyPattern("#");
					}
					cellValue = format.format(value);
				}
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN: // 布尔
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_FORMULA: // 公式
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_STRING: // 字符串
				cellValue = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK: // 空值
				cellValue = " ";
				break;
			case XSSFCell.CELL_TYPE_ERROR: // 故障
				cellValue = " ";
				break;
			default:
				throw new IllegalArgumentException("数据格式不对");
		}
		return cellValue;
	}

	public List<ProjectStructureExcelEntity> readParentExcel(int sheetNo, boolean hasTitle) throws Exception {
		List<ProjectStructureExcelEntity> dataModels = new ArrayList<ProjectStructureExcelEntity>();
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet hssfSheet = workbook.getSheetAt(sheetNo);
			if (hssfSheet == null) {
				continue;
			}
			int start = hssfSheet.getFirstRowNum() + (hasTitle ? 1 : 0); // 如果有标题则从第二行开始
			for (int i = start; i <= hssfSheet.getLastRowNum(); i++) {
				ProjectStructureExcelEntity projectStructureExcelEntity = new ProjectStructureExcelEntity();
				XSSFRow row = hssfSheet.getRow(i);
				// String parentId = null;
				if (row == null) {
					continue;
				}
				for (int coluNum = 0; coluNum <= hssfSheet.getLeftCol(); coluNum++) {
					XSSFCell cell = row.getCell(coluNum);
					// 如果该列为null则循环下一列
					if (cell == null) {
						continue;
					}
					String content = getCellContent(cell);
					// String id = UuidUtils.getUuid();
//					if (coluNum == 4) {
//						projectStructureExcelEntity.setStartDate(content);
//					} else if (coluNum == 5) {
//						projectStructureExcelEntity.setStartDate(content);
//					}
					// else if(coluNum == 1) {;
					// projectStructureExcelEntity.setParentId(parentId);
					// } else if(coluNum == 2) {
					// projectStructureExcelEntity.setParentId(parentId);
					// } else if(coluNum == 3) {
					// projectStructureExcelEntity.setParentId(parentId);
					// } else if(coluNum == 0 ) {
					// projectStructureExcelEntity.setParentId(parentId);
					// }
					// projectStructureExcelEntity.setId(id);
					projectStructureExcelEntity.setName(content);
					// parentId = id;
				}
				dataModels.add(projectStructureExcelEntity);
			}
		}
		return dataModels;
	}

}
