package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class OpportunityInformationPage {

	//declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerTxt;

	//initialization
	public OpportunityInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}


	//business logic
	public void verify(String opportunityName) {
		String actHeader = headerTxt.getText();
		Assert.assertTrue(actHeader.contains(opportunityName), "Opportunity is not created: Fail");
		Reporter.log("Opportunity is created and verified: PASS",true);
	}

}
