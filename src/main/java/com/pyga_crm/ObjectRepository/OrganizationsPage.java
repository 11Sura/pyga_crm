package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	//declaration
		@FindBy(xpath="//img[@title='Create Organization...']")
		private WebElement createOrgsLookup;

		//initialization
		public OrganizationsPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
		//utilization

		public WebElement getCreateOrgsLookup() {
			return createOrgsLookup;
		}
		//business logic
		public void clickOrgsLookup() {
			createOrgsLookup.click();

		}

}
