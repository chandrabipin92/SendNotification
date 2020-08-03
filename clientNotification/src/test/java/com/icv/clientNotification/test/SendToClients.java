package com.icv.clientNotification.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.icv.clientNotification.TestBase;
import com.icv.pages.AddContact;

import jxl.read.biff.BiffException;


public class SendToClients extends TestBase{

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public String url="https://api.whatsapp.com/send?phone=91";
	public String ISO9001 ="Thanks and Regards%0a"+
			"Seema Sandesh%0a" + 
			"ICV Certifications Services " + 
			"services like different ISO Standard ,Trademarks and other quality " + 
			"certification etc.%0a" + 
			"For ISO 9001-2015-%0a" + 
			"Please fill  below information's  for ISO 9001, 14000,18000.22000,27000 etc.%0a" + 
			"BASIC  INFORMATION  REQUIRED---Please send above docs. asap.along with following details-%0a1.Registration copy%0a2.Sale purchase bill %0a3.Identity proof of Authorized signatory %0a4.Letter head with sign. and stamp &0a5. Company/School/ Firm Name %0a6.Company/School/Firm address with PIN code-&0a7. Business Activities-%0a8. Government Issued Doc (GST registration etc. in name of Firm;" + 
			"Photocopy required) -  our best quote for ISO-9001:2015 (IAF) - Rs.7,000/-%0a";
			
	AddContact contact;
	
	@BeforeTest
	public void initMethod()
	{
		init();
	}

	@Test(dataProvider="clientDetails")
	public void testToSendMessage(String number,String CompanyName) throws InterruptedException, IOException
	{
		String imageLocation = "C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\img1.jpg";
		String secondImage = "C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\img2.jpg";
		String thirdImage = "C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\img3.jpg";
		
		contact = new AddContact();
	
		driver.get("http://api.whatsapp.com/send?phone=91"+number);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		//log.info("Url is "+url+number);
		
		contact.sendMessage(imageLocation,secondImage,thirdImage,number);
	}
	
	@AfterClass
	public void endMessageSending()
	{
		driver.quit();
	}
	@DataProvider(name="clientDetails")
	public Object[][] loginData() throws BiffException {
		
		System.out.println("reached to data provider");
		
		Object[][] arrayObject = getExcelData("C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\ICV.xls","Sheet1");
		return arrayObject;
	}
	
}
