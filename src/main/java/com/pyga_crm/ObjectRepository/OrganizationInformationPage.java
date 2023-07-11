package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class OrganizationInformationPage {

	//declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerTxt;

	//initialization
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}


	//business logic
	public void verify(String organizationName) {
		String actHeader = headerTxt.getText();
		Assert.assertTrue(actHeader.contains(organizationName), "Organization is not created: Fail");
		Reporter.log("Organization is created and verified: PASS",true);

	}

}
