package com.icv.clientNotification;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



public class TestBase {

public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	
	public static WebDriver driver;
	public String browser="chrome";
	public String url="https://api.whatsapp.com/send?phone=91";
	
	public void init()
	{
		String log4jConfigPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
		
		selectBrowser(browser);
		log.info("Selecting the browser "+browser.toString());
		
		//getUrl(url,number);
		log.info("Loading URL "+url.toString());
		
		
		
	}
	
	public void selectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			//System.setProperty("webdriver.gecko.driver" , "C:\\Users\Bipin\eclipse-workspace\PassportAutomation\Drivers\geckodriver");
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	public void getUrl(String url,String number)
	{
		driver.get(url+number);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
	}

	public String[][] getExcelData(String fileName, String sheetName) throws BiffException {
		String[][] arrayExcelData = null;
		try {
			
			File file = new File(fileName);
			System.out.println("File location is "+fileName);
			
			FileInputStream fs = new FileInputStream(file);
			
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			
			int totalNoOfCols = sh.getColumns();
			System.out.println("Total columns "+totalNoOfCols);
			int totalNoOfRows = sh.getRows();
			System.out.println("Total Rows "+totalNoOfRows);
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	

public static void writePassToExcelSheet(String fileName, Double testMobNo) throws  IOException {
		
		File file = new File(fileName);
		System.out.println("File location is "+fileName);
		
		FileInputStream fs = new FileInputStream(file);
		
		
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Sheet1");
		
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		System.out.println("Reached out of for loop"+rowCount);
		
		for(int i =1; i<= rowCount; i++)
		{
			
			System.out.println("Reached for loop");
			Row row = sh.getRow(i);
			
			
			Cell Statuscell;
			Row newRow;
			Cell PhoneNoCell;
			
			System.out.println("Value of double is "+testMobNo);
			
				if(row.getCell(0).getNumericCellValue()==testMobNo)
				{
					newRow = sh.createRow(i);
					PhoneNoCell = newRow.createCell(0);
					Statuscell = newRow.createCell(2);
					System.out.println("reached if condition"+row.getCell(0).getNumericCellValue());
					Statuscell.setCellValue("Pass");
					PhoneNoCell.setCellValue(testMobNo);
				}
				
			
		}
		
		fs.close();
		
		FileOutputStream outStream = new FileOutputStream(file);
		
		wb.write(outStream);
		
		outStream.close();
	}

	
	public static void writeFailToExcelSheet(String fileName, Double testMobNo) throws  IOException {
	
	File file = new File(fileName);
	System.out.println("File location is "+fileName);
	
	FileInputStream fs = new FileInputStream(file);
	
	
	HSSFWorkbook wb = new HSSFWorkbook(fs);
	
	org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Sheet1");
	
	int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
	System.out.println("Reached out of for loop"+rowCount);
	
	for(int i =1; i<= rowCount; i++)
	{
		
		System.out.println("Reached for loop");
		Row row = sh.getRow(i);
		
		
		Cell Statuscell;
		Row newRow;
		Cell PhoneNoCell;
		
		System.out.println("Value of double is "+testMobNo);
		
			if(row.getCell(0).getNumericCellValue()==testMobNo)
			{
				newRow = sh.createRow(i);
				PhoneNoCell = newRow.createCell(0);
				Statuscell = newRow.createCell(2);
				System.out.println("reached if condition"+row.getCell(0).getNumericCellValue());
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
