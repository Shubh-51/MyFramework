package com.comcast.crm.generic.excelutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./TestScriptData/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String value=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).toString();
		wb.close();
	return value;
	}
	
	public int getLastRowCount(String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./TestScriptData/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowcount=wb.getSheet(sheet).getLastRowNum();
		wb.close();
	return rowcount;
	}
	
	public void setStringDataBackIntoExcelFile(String sheet,int rownum ,int cellnum,String cellvalue) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./TestScriptData/testScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheet).getRow(rownum).createCell(cellnum).setCellValue(cellvalue);
		FileOutputStream fos=new FileOutputStream("./TestScriptData/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
