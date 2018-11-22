package Task2;


import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	//this class reads data from excel file
HSSFWorkbook wb;
HSSFSheet sheet1;
	//XSSFWorkbook wb;
	//XSSFSheet sheet1;
	
	public ExcelDataConfig(String excelpath)
	{
		
		try {
			
			File src = new File(excelpath);
			
			FileInputStream fis = new FileInputStream(src);
			
		//	wb= new XSSFWorkbook(fis);
			wb= new HSSFWorkbook(fis);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(int sheetNumber, int row, int column) {
		
		sheet1=wb.getSheetAt(sheetNumber);
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		
		return data;
		
	}
	
	public int getRowCount(int sheetindex)
	{
	int row =	wb.getSheetAt(sheetindex).getLastRowNum();
	row =row+1;
	return row;
	}


}
