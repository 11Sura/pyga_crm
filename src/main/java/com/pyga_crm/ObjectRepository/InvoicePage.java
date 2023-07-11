package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {
	//declaration
		@FindBy(xpath="//img[@title='Create Invoice...']")
		private WebElement createInvoiceLookup;

		//initialization
		public InvoicePage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		//utilization

		public WebElement getCreateInvoiceLookup() {
			return createInvoiceLookup;
		}
		//business logic
		public void clickInvoiceLookup() {
			createInvoiceLookup.click();

		}

}
