package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderPage {
	//declaration
		@FindBy(xpath="//img[@title='Create Sales Order...']")
		private WebElement createSalesOrderLookup;

		//initialization
		public SalesOrderPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		//utilization

		public WebElement getCreateSalesOrderLookup() {
			return createSalesOrderLookup;
		}
		//business logic
		public void clickSalesOrderLookup() {
			createSalesOrderLookup.click();

		}

}
