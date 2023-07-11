package com.pyga_crm.ObjectRepository;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class CampaignInformationPage {
	//declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerTxt;

	//initialization
	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}


	//business logic
	public void verify(String campaignsName) {
		String actHeader = headerTxt.getText();
		Assert.assertTrue(actHeader.contains(campaignsName), "Campaign is not created: Fail");
		Reporter.log("Campaign is created and verified: PASS",true);

	}

}
