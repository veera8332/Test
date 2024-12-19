package com.ninza.hrm.api.genericutilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/Testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		return data;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/Testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	public int getCellCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/Testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int cellcount = wb.getSheet(sheetName).getRow(1).getLastCellNum();
		wb.close();
		return cellcount;
	}

	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data)
	//here data is what data we want to write back to excel
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testdata/Testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./testdata/Testscriptdata.xlsx");
		wb.write(fos);
		wb.close();

	}
}
