package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class InvoiceInformationPage {
	//declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement headerTxt;

	//initialization
	public InvoiceInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getHeaderTxt() {
		return headerTxt;
	}


	//business logic
	public void verify(String invoiceName) {
		String actHeader = headerTxt.getText();
		SoftAssert asrt=new SoftAssert();       //using soft assert
		asrt.assertTrue(actHeader.contains(invoiceName), "Invoice is not created: Fail");
		Reporter.log("Invoice is created and verified: PASS",true);
	}

}
