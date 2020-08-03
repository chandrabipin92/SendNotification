package com.icv.clientNotification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UpdateExcelProject {

public static void writePassToExcelSheet(String fileName, long testMobNo) throws  IOException {
		
		File file = new File(fileName);
		System.out.println("File location is "+fileName);
		
		FileInputStream fs = new FileInputStream(file);
		
		
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Sheet1");
		
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		System.out.println("Reached out of for loop");
		for(int i =1; i< rowCount; i++)
		{
			
			System.out.println("Reached for loop");
			Row row = sh.getRow(i);
			
			
			Cell Statuscell;
			Row newRow;
			Cell PhoneNoCell;
			
				if(row.getCell(0).getNumericCellValue()==testMobNo)
				{
					newRow = sh.createRow(i);
					PhoneNoCell = newRow.createCell(0);
					Statuscell = newRow.createCell(2);
					System.out.println("reached if condition"+row.getCell(0).getNumericCellValue());
					Statuscell.setCellValue("Pass");
					PhoneNoCell.setCellValue(testMobNo);
				}
				else
				{
					System.out.println("reached else condition");
					
					newRow = sh.createRow(i);
					PhoneNoCell = newRow.createCell(0);
					Statuscell = newRow.createCell(2);
					Statuscell.setCellValue("Fail");
					PhoneNoCell.setCellValue(testMobNo);
				}
			
		}
		
		fs.close();
		
		FileOutputStream outStream = new FileOutputStream(file);
		
		wb.write(outStream);
		
		outStream.close();
	}


}
