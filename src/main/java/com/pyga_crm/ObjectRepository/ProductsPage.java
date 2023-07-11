package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	//declaration
		@FindBy(xpath="//img[@title='Create Product...']")
		private WebElement createProductsLookup;
		
		//initialization
		public ProductsPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		//utilization

		public WebElement getCreateProductsLookup() {
			return createProductsLookup;
		}
		//business logic
		public void clickProductsLookup() {
			createProductsLookup.click();
		}

}
