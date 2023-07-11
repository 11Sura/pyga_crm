package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuotesPage {
	//declaration
			@FindBy(xpath="//img[@title='Create Quote...']")
			private WebElement createQuotesLookup;
			
			//initialization
			public QuotesPage(WebDriver driver) {
				PageFactory.initElements(driver,this);
			}
			//utilization

			public WebElement getCreateQuotesLookup() {
				return createQuotesLookup;
			}
			//business logic
			public void clickQuotesLookup() {
				createQuotesLookup.click();
			}

}
