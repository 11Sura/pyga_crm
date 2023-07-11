package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//declaration
			@FindBy(xpath="//img[@title='Create Contact...']")
			private WebElement createContactsLookup;
			
			//initialization
			public ContactsPage(WebDriver driver) {
				PageFactory.initElements(driver,this);
			}
			//utilization

			public WebElement getCreateContactsLookup() {
				return createContactsLookup;
			}
			//business logic
			public void clickContactsLookup() {
				createContactsLookup.click();
			}

}
