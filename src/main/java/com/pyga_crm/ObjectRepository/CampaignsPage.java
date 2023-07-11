package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	//declaration
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement createCampaignsLookup;

	//initialization
	public CampaignsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	//utilization

	public WebElement getCreateCampaignsLookup() {
		return createCampaignsLookup;
	}
	//business logic
	public void clickCampaignsLookup() {
		createCampaignsLookup.click();

	}
}