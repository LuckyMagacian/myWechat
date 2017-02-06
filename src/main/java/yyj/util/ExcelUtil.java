package yyj.util;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelUtil {
	/** 工作本 */
	private static HSSFWorkbook workbook;
	/** 标题样式 */
	private static HSSFCellStyle titleStyle;
	/** 标题字体 */
	private static HSSFFont titleFont;
	/** 普通样式 */
	private static HSSFCellStyle commonStyle;
	/** 普通字体 */
	private static HSSFFont commonFont;
	/** 加载初始化 */
	static {
		init();
	}

	/**
	 * 初始化工作簿
	 */
	@SuppressWarnings("deprecation")
	public static void init() {
		workbook = new HSSFWorkbook();

		titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 16); // 标题字体字号
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 标题字体加粗

		titleStyle = workbook.createCellStyle();
		titleStyle.setFont(titleFont);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 标题上下居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 标题左右居中
		titleStyle.setFillBackgroundColor(HSSFColor.BRIGHT_GREEN.index);
		; // 设置背景颜色为浅绿

		commonFont = workbook.createFont();
		commonFont.setFontHeightInPoints((short) 14); // 普通字体字号

		commonStyle = workbook.createCellStyle();
		commonStyle.setFont(commonFont);
		commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 普通上下居中
		commonStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 普通左右居中
	}

	/**
	 * 设置页名
	 * 
	 * @param name
	 */
	public static void setSheetName(int index, String name) {
		workbook.setSheetName(0, name);
	}

	/**
	 * 获取普通样式
	 * 
	 * @return
	 */
	public static HSSFCellStyle getCommonStyle() {
		return commonStyle;
	}

	/**
	 * 获取标题样式
	 * 
	 * @return
	 */
	public static HSSFCellStyle getTitleStyle() {
		return titleStyle;
	}

	/**
	 * 获取工作簿
	 * 
	 * @return
	 */
	public static HSSFWorkbook getWorkBook() {
		return workbook;
	}

	/**
	 * 根据索引获取页
	 * 
	 * @param index
	 * @return
	 */
	public static HSSFSheet getSheet(int index) {
		if (index < 0)
			throw new AppException("页号不能小于0");
		// 没有sheet count=0 1个sheet count=1 两个count number=2
		int count = workbook.getNumberOfSheets();

		if (count > index)
			return workbook.getSheetAt(index);
		if (count == index) {
			workbook.createSheet().setDefaultColumnWidth(20);// 设置默认单元格宽度
			return workbook.getSheetAt(index);
		}
		if (count < index) {
			while (count <= index) {
				workbook.createSheet().setDefaultColumnWidth(20);
				count++;
			}
			return workbook.getSheetAt(index);
		}
		return null;
	}

	/**
	 * 根据索引获取行
	 * 
	 * @param sheetIndex
	 * @param rowIndex
	 * @return
	 */
	public static HSSFRow getRow(int sheetIndex, int rowIndex) {
		if (rowIndex < 0)
			throw new AppException("获取行号不能小于0");
		HSSFSheet sheet = getSheet(sheetIndex);
		// 可以隔行创建行,未创建的行依然是null,
		// 最终行号为创建的行号最大值
		// 没有创建时 最大行号为0
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null)
			sheet.createRow(rowIndex);
		return sheet.getRow(rowIndex);
	}

	/**
	 * 根据索引获取 单元格
	 * 
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static HSSFCell getCell(int sheetIndex, int rowIndex, int cellIndex) {
		HSSFRow row = getRow(sheetIndex, rowIndex);
		if (cellIndex < 0)
			throw new AppException("单元格编号不能小于0");
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null)
			row.createCell(cellIndex);
		return row.getCell(cellIndex);
	}

	/**
	 * 根据参数数量获取内容 1个 获取sheet 2个获取row 3个获取cell
	 * 
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static Object get(Integer sheetIndex, Integer rowIndex, Integer cellIndex) {
		if (sheetIndex != null && rowIndex == null && cellIndex == null)
			return getSheet(sheetIndex);
		else if (sheetIndex != null && rowIndex != null && cellIndex == null)
			return getRow(sheetIndex, rowIndex);
		else if (sheetIndex != null && rowIndex != null && cellIndex != null)
			return getCell(sheetIndex, rowIndex, cellIndex);
		else
			return null;
	}

	/**
	 * 将指定表中的旧字符串替换为新字符串
	 * 
	 * @param sheetIndex
	 * @param oldStr
	 * @param newStr
	 */
	public static void sheetReplace(HSSFSheet sheet, String oldStr, String newStr) {
		if (oldStr == null || oldStr.trim().isEmpty() || newStr == null)
			return;
		for (int i = 0; i < sheet.getLastRowNum(); i++)
			rowReplace(sheet.getRow(i), oldStr, newStr);

	}

	/**
	 * 将行中的旧字符串替换为新字符串
	 * 
	 * @param row
	 * @param oldStr
	 * @param newStr
	 */
	public static void rowReplace(HSSFRow row, String oldStr, String newStr) {
		for (int j = 0; j < row.getLastCellNum(); j++)
			cellReplace(row.getCell(j), oldStr, newStr);
	}

	/**
	 * 将单元格中的旧字符串替换为新字符串
	 * 
	 * @param cell
	 * @param oldStr
	 * @param newStr
	 */
	public static void cellReplace(HSSFCell cell, String oldStr, String newStr) {
		String str = cell.getStringCellValue();
		cell.setCellValue(str.equals(oldStr) ? newStr : str);
	}

	/**
	 * 生成excel文件 若path=null -> 生成到classPath下 年月日时分秒4位随机数.xls 若path为目录 -> 生成到目录下
	 * 年月日时分秒4位随机数.xls 若path为文件 -> 生成内容到该文件中
	 * 
	 * @param path
	 */
	public static File generatorExcel(String path) {
		if (path == null)
			path = ExcelUtil.class.getResource("/").getPath();
		try {
			File file = new File(path);
			File excel = null;
			if (file.isDirectory())
				excel = new File(path + "/" + TimeUtil.getDateTime() + RandomUtil.getRandomNumber(4) + ".xls");
			else
				excel = file;
			return generatorExcel(excel);
		} catch (Exception e) {
			throw new AppException("构建excel文件异常", e);
		}
	}

	/**
	 * 将工作簿写入到指定的文件并返回
	 * 
	 * @param file
	 * @return
	 */
	public static File generatorExcel(File file) {
		try {
			workbook.write(file);
			return file;
		} catch (Exception e) {
			throw new AppException("写入excel到指定文件异常", e);
		}
	}
}
