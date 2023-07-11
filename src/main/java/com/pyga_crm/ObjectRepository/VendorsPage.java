package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage {
	//declaration
	@FindBy(xpath="//img[@title='Create Vendor...']")
	private WebElement createVendorsLookup;
	
	//initialization
	public VendorsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	//utilization

	public WebElement getCreateVendorsLookup() {
		return createVendorsLookup;
	}
	//business logic
	public void clickVendorsLookup() {
		createVendorsLookup.click();
	}

}
