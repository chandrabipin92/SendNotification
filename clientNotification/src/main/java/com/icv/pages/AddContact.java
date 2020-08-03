package com.icv.pages;

import java.io.IOException;
import java.sql.Time;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.icv.clientNotification.TestBase;


public class AddContact extends TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public void sendMessage(String imageLocation,String secondImage, String thirdImage,String number) throws InterruptedException, IOException
	{
		TestBase.driver.findElement(By.id("action-button")).click();
		//log.info("Clicked on continue on chat" +ContinueToChat.getText());
		
		//usehatAppWeb.click();
		TestBase.driver.findElement(By.linkText("use WhatsApp Web")).click();
		log.info("Clikced on useWhatsApp");
		
		try {
			TestBase.driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div[1]/div")).isDisplayed();
			
			Thread.sleep(5000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		
			if(TestBase.driver.findElements(By.xpath("//*[@id=\"app\"]/div/span[2]/div/span/div/div/div/div/div/div[1]")).size()>0)
			{
				TestBase.driver.findElement(By.xpath("//*[@id=\"app\"]/div/span[2]/div/span/div/div/div/div/div/div[2]/div")).click();
				double num = Double.parseDouble(number);
				writeFailToExcelSheet("C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\ICV.xls", num);
			}
			else
			{
				if(TestBase.driver.findElement(By.xpath("//*[@id='main']/header/div[3]/div/div[2]/div/span")).isDisplayed())
				{
					double num = Double.parseDouble(number);
					writePassToExcelSheet("C:\\Users\\Bipin\\Desktop\\Untitled Folder\\ICV\\ICV.xls", num);
				}
			
			TestBase.driver.findElement(By.xpath("//*[@id='main']/header/div[3]/div/div[2]/div/span")).click();
			log.info("Clicked on attachments");
			
			//TestBase.driver.findElement(By.xpath("//*[@id='main']/header/div[3]/div/div[2]/span/div/div/ul/li[1]")).click();
			log.info("Clicked on attchment photo");
			
			TestBase.driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imageLocation);
			Thread.sleep(10);
			
			
			TestBase.driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/span/div/span/div/div/div[2]/div[2]/div"));
			log.info("Clicked on additional image");
			
			TestBase.driver.findElement(By.cssSelector("input[type='file']")).sendKeys(secondImage);
			Thread.sleep(10);
			log.info("Second Image uploaded");
			
			TestBase.driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thirdImage);
			Thread.sleep(10);
			log.info("Third Image uploaded");
			//TestBase.driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div[3]/button")).click();
			//log.info("Message Submitted");
			
			TestBase.driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/span/div/span/div/div/div[2]/span/div/div")).click();
			log.info("Clicked On send file");

			Thread.sleep(10000);
				
			}
		
	}
}
