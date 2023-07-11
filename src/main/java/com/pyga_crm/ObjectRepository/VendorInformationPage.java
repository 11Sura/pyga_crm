package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class VendorInformationPage {
	
	//declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement headerTxt;
	
	//initialization
	public VendorInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}
	
	
	//business logic
	public void verify(String vendorsName) {
		String actHeader = headerTxt.getText();
		Assert.assertTrue(actHeader.contains(vendorsName),"Vendor is not created: Fail");  //assert statements used for verification instead of if-else
		//in the above line, the string msg will be printed only if assertion fails else execution continues and the below msg gets printed
		Reporter.log("Vendor is created and verified: PASS",true);
		
	}

}
