package com.sapient.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static XSSFRow row, Writerow;
	static XSSFCell cell, Writecell;

	static String currentDir = System.getProperty("user.dir");

	
	public static List<String> readXLSFile(int coltoread) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(currentDir + "/src/test/resources/testdata/Test.xlsx");
		wb = new XSSFWorkbook(ExcelFileToRead);

		sheet = wb.getSheetAt(0);
		// XSSFRow row;
		// XSSFCell cell;
		String Test = null;

		List<String> ExcelArray = new ArrayList<String>();
		Iterator<Row> rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				Test = cell.getStringCellValue();

				System.out.println(Test);
				if (cell.getColumnIndex() == coltoread) {
					ExcelArray.add(Test);

				}
			}

		}
		System.out.println(ExcelArray);
		return ExcelArray;

	}

	
	public static List<String> readXLSFileSpeCol(int speccol) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(currentDir + "/src/test/resources/testdata/Test.xlsx");
		 wb = new XSSFWorkbook(ExcelFileToRead);

		 sheet = wb.getSheetAt(0);
		//XSSFRow row;
		//XSSFCell cell;
		String Test = null;
       
        List<String> ExcelArray = new ArrayList<String>();
		Iterator<Row> rows = sheet.rowIterator();

		

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				 Test = cell.getStringCellValue();
				 
				System.out.println(Test);
			if (cell.getColumnIndex()==speccol)
			{
				ExcelArray.add(Test);
				
			}
			}
			
			
		}
		System.out.println(ExcelArray);
		return ExcelArray;
		
	}
	public static void WriteXLSFile(String ScreenshotName, int rowtowrite,int coltowrite) throws IOException {
		InputStream ExcelFileToWrite = new FileInputStream(currentDir + "/src/test/resources/testdata/Test.xlsx");
		wb = new XSSFWorkbook(ExcelFileToWrite);

		sheet = wb.getSheetAt(0);
		// XSSFRow row;
		// XSSFCell cell;

		// Iterator rows = sheet.rowIterator();

		// row = (XSSFRow) rows.next();
		// Iterator cells = row.cellIterator();
		int rowNum = rowtowrite;
		// Writerow = sheet.createRow(rowNum++);
		Writerow = sheet.getRow(rowNum);

		int colNum = coltowrite;
		// Writecell = Writerow.createCell(colNum++);
		Writecell = Writerow.getCell(colNum);

		if (Writecell == null) {
			Writecell = sheet.getRow(rowtowrite).createCell(colNum);
		}
		Writecell.setCellValue(ScreenshotName);
		ExcelFileToWrite.close();
		FileOutputStream outFile = new FileOutputStream(new File(currentDir + "/src/test/resources/testdata/Test.xlsx"));
		wb.write(outFile);
		outFile.close();

	}
	
	

	public static void main(String[] args) throws IOException

	{
		// ReadExcel re =new ReadExcel();
		// ReadExcel.readXLSFile();
		// ReadExcel.WriteXLSFile();
	}
}
