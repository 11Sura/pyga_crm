package com.pyga_crm.genericLib;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionsLib {
	
		CommondataFileLib fLib = new CommondataFileLib();
		int TIMEOUT;
		
		public WebActionsLib()  {
			try {
			String filepath = fLib.getFilePathFromPropertiesFile("commondataConfigFilePath");
			String STIMEOUT = fLib.getDataFromProperties(filepath, "TimeOut");
			 TIMEOUT = Integer.parseInt(STIMEOUT);
		}
			catch(Throwable e) {
				
			}
		}
		
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void waitForElementInDOM(WebDriver driver) throws Throwable {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
	}
	
	public void waitForPage(WebDriver driver , String partialPageURL) throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		wait.until(ExpectedConditions.urlContains(partialPageURL));
	}
	
	public void waitForElement(WebDriver driver , WebElement element) throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitAndClick(WebElement element) throws Throwable
	   {

		   int count = 0;
	       while(count<TIMEOUT) {
		    	   try {
		    	       element.click();
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	   }
	
	 public void waitAndType(WebElement element, String data) throws InterruptedException
	   {
		   int count = 0;
	       while(count<20) {
		    	   try {
		    	       element.sendKeys(data);
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	   }
	 
	 public void swithToWindow(WebDriver driver , String partialWindowTitle) {
	       Set<String> set = driver.getWindowHandles();
	         Iterator<String> it = set.iterator();

	          while (it.hasNext()) {
			          String wID = it.next();
			          driver.switchTo().window(wID);
			          String currentWindowTitle = driver.getTitle();
			          if(currentWindowTitle.contains(partialWindowTitle)) {
			        	  System.out.println(partialWindowTitle + "Switch to window is done!");
			        	  break;
			          }
		    	}
	}
	 
	 public void swithToWindowBasedOnURL(WebDriver driver , String partialWindowURL) {
	       Set<String> set = driver.getWindowHandles();
	         Iterator<String> it = set.iterator();

	          while (it.hasNext()) {
			          String wID = it.next();
			          driver.switchTo().window(wID);
			          String currentWindowTitle = driver.getCurrentUrl();
			          if(currentWindowTitle.contains(partialWindowURL)) {
			        	  System.out.println(partialWindowURL + "Switch to window is done!");
			        	  break;
			          }
		    	}
	          
	          
	}
	 public void acceptAlert(WebDriver driver) {
		 Alert alt = driver.switchTo().alert(); 
		 alt.accept();
	 }
	 
	 public void swithToAlertWindowAndAccpect(WebDriver driver ,String expectedMsg) {
			Alert alt = driver.switchTo().alert();
			 if(alt.getText().trim().equalsIgnoreCase(expectedMsg.trim())) {
				 System.out.println("Alert Message is verified");
			 }else {
				 System.out.println("Alert Message is not verified");
			 }
			alt.accept();
		}
	 
	 public void swithToAlertWindowAndCancel(WebDriver driver, String expectedMsg) {
			Alert alt = driver.switchTo().alert();
			 if(alt.getText().equals(expectedMsg)) {
				 System.out.println("Alert Message is verified");
			 }else {
				 System.out.println("Alert Message is not verified");
			 }
			 alt.dismiss();
		}
	 
	 public void swithToFrame(WebDriver driver , int index) {
			driver.switchTo().frame(index);
		}
	 
	 public void swithToFrame(WebDriver driver , String id_name_attribute) {
			driver.switchTo().frame(id_name_attribute);
		}
	 
	 public void select(WebElement element , int index) {
			Select sel = new Select(element);
			sel.selectByIndex(index);
		}
	 
	 public void select(WebElement element , String value) {
			Select sel = new Select(element);
			sel.selectByValue(value);
		}
	 
	 public void mouseHoverOnElement(WebDriver driver , WebElement elemnet)
		{
			Actions act = new Actions(driver);
			act.moveToElement(elemnet).perform();
		}
	 
	 public void rightClickOnElement(WebDriver driver , WebElement elemnet)
		{
			Actions act = new Actions(driver);
			act.contextClick(elemnet).perform();
		}
	 
	 public void pressEnterKey(WebDriver driver) {
		   Actions act = new Actions(driver);
		   act.sendKeys(Keys.ENTER).perform();
	   }
	 
	 public static String takeScreenshot(String screenShot) {
		
			//LocalDateTime localDT = LocalDateTime.now();
			//String dateTime = localDT.toString().replace(" ","_").replace(":","_");
			TakesScreenshot ts=(TakesScreenshot) BaseClass.sDriver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest= new File("./ScreenShots/"+screenShot+".png");
			try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				e.getMessage();
				e.printStackTrace();
			}
			return screenShot;
	 }

}
