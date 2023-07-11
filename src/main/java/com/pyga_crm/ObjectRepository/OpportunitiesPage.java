package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {
	
	//declaration
		@FindBy(xpath="//img[@title='Create Opportunity...']")
		private WebElement createOpportunitysLookup;

		//initialization
		public OpportunitiesPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		//utilization

		public WebElement getcreateOpportunitysLookup() {
			return createOpportunitysLookup;
		}
		//business logic
		public void clickcreateOpportunitysLookup() {
			createOpportunitysLookup.click();

		}

}
