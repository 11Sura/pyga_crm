package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class ProductInformationPage {
	
	//declaration
		@FindBy(xpath="//span[@class='lvtHeaderText']")
		private WebElement headerTxt;
		
		//initialization
		public ProductInformationPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		
		
		//utilization
		public WebElement getHeaderTxt() {
			return headerTxt;
		}
		
		
		//business logic
		public void verify(String productsName) {
			String actHeader = headerTxt.getText();
			Assert.assertTrue(actHeader.contains(productsName),"Product is not created: Fail");
			Reporter.log("Product is created and verified: PASS",true);

		}

}
