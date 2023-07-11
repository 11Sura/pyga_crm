package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPage {

	//declaration
	@FindBy(name="accountname")
	private WebElement orgName;
	
	@FindBy(xpath="//input[@name='website']")
	private WebElement websiteTxtBx;
	
	@FindBy(id="tickersymbol")
	private WebElement tickerTxtBx;
	
	@FindBy(id="employees")
	private WebElement employeesTxtBx;
	
	@FindBy(name="industry")
	private WebElement industry;
	
	@FindBy(name="accounttype")
	private WebElement accountType;
	
	@FindBy(id="phone")
	private WebElement phoneTxtBx;
	
	@FindBy(id="fax")
	private WebElement faxTxtBx;
	
	@FindBy(id="email1")
	private WebElement emaillTxtBx;
	
	@FindBy(name="bill_street")
	private WebElement billStreet;
	
	@FindBy(id="bill_pobox")
	private WebElement poBox;
	
	@FindBy(id="bill_city")
	private WebElement billCity;
	
	@FindBy(id="bill_state")
	private WebElement billState;
	
	@FindBy(id="bill_code")
	private WebElement billCode;
	
	@FindBy(id="bill_country")
	private WebElement billCountry;
	
	@FindBy(xpath="//input[@onclick='return copyAddressRight(EditView)']")
	private WebElement copyAddress;
	
	@FindBy(name="description")
	private WebElement description;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	//initialization
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getOrgName() {
		return orgName;
	}


	public WebElement getWebsiteTxtBx() {
		return websiteTxtBx;
	}


	public WebElement getTickerTxtBx() {
		return tickerTxtBx;
	}


	public WebElement getEmployeesTxtBx() {
		return employeesTxtBx;
	}


	public WebElement getIndustry() {
		return industry;
	}


	public WebElement getAccountType() {
		return accountType;
	}


	public WebElement getPhoneTxtBx() {
		return phoneTxtBx;
	}


	public WebElement getFaxTxtBx() {
		return faxTxtBx;
	}


	public WebElement getEmaillTxtBx() {
		return emaillTxtBx;
	}


	public WebElement getBillStreet() {
		return billStreet;
	}


	public WebElement getPoBox() {
		return poBox;
	}


	public WebElement getBillCity() {
		return billCity;
	}


	public WebElement getBillState() {
		return billState;
	}


	public WebElement getBillCode() {
		return billCode;
	}


	public WebElement getBillCountry() {
		return billCountry;
	}


	public WebElement getCopyAddress() {
		return copyAddress;
	}


	public WebElement getDescription() {
		return description;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	//business logic
	public void organizationDetails(String organizationName) {
	
	orgName.sendKeys(organizationName);
	websiteTxtBx.sendKeys("www."+organizationName+".com");
	tickerTxtBx.sendKeys("ORG");
	employeesTxtBx.sendKeys("120");
	phoneTxtBx.sendKeys("6789012345");
	faxTxtBx.sendKeys("111-222-3333");
	emaillTxtBx.sendKeys(organizationName+"@mail.com");
	billStreet.sendKeys("street1");
	poBox.sendKeys("city post-345261");
	billCity.sendKeys("Bengaluru");
	billState.sendKeys("Karnataka");
	billCode.sendKeys("345261");
	billCountry.sendKeys("India");
	copyAddress.click();
	description.sendKeys("Testing description");
	
	}
	
	public void save() {
		saveBtn.click();
	}
	
	
	
	
}
