//package kp.cm.common.file;
//import java.io.BufferedWriter;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.inject.Named;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.apache.poi.ss.util.NumberToTextConverter;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//@Named("XlsxReader")
//public class XlsxReader implements IExcelReader {
//
//	@Override
//	public String readAndWriteFile(String[] sheetInfo, String fileName, String filePath, String rootDirectory) throws IOException {
//		FileInputStream fis=new FileInputStream(filePath);
//		XSSFWorkbook workbook=new XSSFWorkbook(fis);
//		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//		DecimalFormat df = new DecimalFormat();
//		int rowindex = 0;
//		int columnindex = 0;
//		// 시트 수 (첫번째에만 존재하므로 0을 준다)
//		// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
//		SimpleDateFormat sfd = new SimpleDateFormat("__yyyyMMddHHmmss");
//		String strCurTime = sfd.format(new Date());
//
//		String targetFilePath = rootDirectory + fileName.substring(0, fileName.lastIndexOf(".")) + strCurTime + ".upload";
////		FileWriter fw = new FileWriter(targetFilePath, true);
//		FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);
//		OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "euc-kr");
//
//		BufferedWriter bufferedWriter = new BufferedWriter(OutputStreamWriter);
//
//		int beforeCells = 0;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//		for (int a = 0; a < sheetInfo.length; a++) {
//			XSSFSheet sheet = workbook.getSheetAt(a);
//			bufferedWriter.write("SheetNo:" + sheetInfo[a] + System.getProperty("line.separator"));
//			// 행의 수
//			int rows = sheet.getPhysicalNumberOfRows();
//			for (rowindex = 0; rowindex < rows; rowindex++) {
//				// 행을 읽는다
//				XSSFRow row = sheet.getRow(rowindex);
//				if (row != null) {
//					// 셀의 수
//					int cells = row.getPhysicalNumberOfCells();
//
//					if(beforeCells < cells){
//						beforeCells = cells;
//					}
//
//					for (columnindex = 0; columnindex <= beforeCells; columnindex++) {
//						// 셀값을 읽는다
//						XSSFCell cell = row.getCell(columnindex);
//						String value = "";
//						// 셀이 빈값일경우를 위한 널체크
//						if (cell == null) {
//							value = "";
//						} else {
//							// 타입별로 내용 읽기
//							switch (cell.getCellType()) {
//							case HSSFCell.CELL_TYPE_FORMULA:
//								if(cell.getCachedFormulaResultType()==HSSFCell.CELL_TYPE_NUMERIC){
//									if(HSSFDateUtil.isCellDateFormatted(cell)){
//										value = format.format(cell.getDateCellValue());
//									}else{
//										value = NumberToTextConverter.toText(cell.getNumericCellValue());
//									}
//								}else if(cell.getCachedFormulaResultType()==HSSFCell.CELL_TYPE_STRING){
//									value = cell.getRichStringCellValue() + "";
//								}else if(cell.getCachedFormulaResultType()==HSSFCell.CELL_TYPE_BLANK){
//									value = "";
//								}else if(cell.getCachedFormulaResultType()==HSSFCell.CELL_TYPE_ERROR){
//									value = cell.getErrorCellValue() + "";
//								}
//								break;
//							case HSSFCell.CELL_TYPE_NUMERIC:
//								if(HSSFDateUtil.isCellDateFormatted(cell)){
//									value = format.format(cell.getDateCellValue());
//								}else{
//									value = NumberToTextConverter.toText(cell.getNumericCellValue());
//								}
//								break;
//							case HSSFCell.CELL_TYPE_STRING:
//								value = cell.getStringCellValue() + "";
//								break;
//							case HSSFCell.CELL_TYPE_BLANK:
//								value = "";
//								break;
//							case HSSFCell.CELL_TYPE_ERROR:
//								value = cell.getErrorCellValue() + "";
//								break;
//							}
//						}
//
//						value = value.replace(System.getProperty("line.separator"), "");
//						value = value.replace("\n", "");
//						value = value.trim();
//
//						if (columnindex == 0) {
//							bufferedWriter.write(value);
//						} else {
//							bufferedWriter.write(":#:" + value);
//						}
//					}
//					bufferedWriter.write(System.getProperty("line.separator"));
//				}else{
//					bufferedWriter.write(System.getProperty("line.separator"));
//					rows += 1;
//				}
//			}
//		}
//		bufferedWriter.flush();
//		bufferedWriter.close();
//
//		return targetFilePath;
//	}
//}
