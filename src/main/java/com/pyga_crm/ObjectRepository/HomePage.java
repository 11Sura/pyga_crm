package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pyga_crm.genericLib.WebActionsLib;

public class HomePage {
	//declaration
	@FindBy(xpath="//a[.='More']")
	private WebElement moreLink;
	
	@FindBy(name="Vendors")
	private WebElement vendorsLink;
	
	@FindBy(xpath="//td[@class='tabUnSelected']/a[.='Products']")
	private WebElement productsLink;
	
	@FindBy(name="Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(xpath="//td[@class='tabUnSelected']/a[.='Organizations']")
	private WebElement organizationsLink;
	
	@FindBy(xpath="//td[@class='tabUnSelected']/a[.='Contacts']")
	private WebElement contactsLink;
	
	@FindBy(xpath="//td[@class='tabUnSelected']/a[.='Opportunities']")
	private WebElement opportunitiesLink;
	
	@FindBy(name="Quotes")
	private WebElement quotesLink;
	
	@FindBy(name="Sales Order")
	private WebElement salesOrdersLink;
	
	@FindBy(name="Invoice")
	private WebElement invoiceLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminstrator;
	
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement signoutLink;
	
	//initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	
   //utilization
	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getVendorsLink() {
		return vendorsLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}
	
	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}


	public WebElement getContactsLink() {
		return contactsLink;
	}


	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	
	public WebElement getQuotesLink() {
		return quotesLink;
	}



	public WebElement getSalesOrdersLink() {
		return salesOrdersLink;
	}



	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	
	public WebElement getAdminstrator() {
		return adminstrator;
	}
	
	public WebElement getSignoutLink() {
		return signoutLink;
	}
	
	


	//business logic
	public void clickOnVendors(WebActionsLib wlib, WebDriver driver) {
		wlib.mouseHoverOnElement(driver, moreLink);
		vendorsLink.click();
	}
	
	public void clickOnProducts() {
		productsLink.click();
	}
	
	public void clickOnCampaigns(WebActionsLib wlib, WebDriver driver) {
		wlib.mouseHoverOnElement(driver, moreLink);
        campaignsLink.click();
	}
	
	public void clickOnOrganizations() {
		organizationsLink.click();
	}
	
	public void clickOnContacts() {
		contactsLink.click();
	}
	
	public void clickOnOpportunities() {
		opportunitiesLink.click();
	}
	
	public void clickOnQuotes(WebActionsLib wlib, WebDriver driver) {
		wlib.mouseHoverOnElement(driver, moreLink);
		quotesLink.click();
	}
	
	public void clickOnSalesOrders(WebActionsLib wlib, WebDriver driver) {
		wlib.mouseHoverOnElement(driver, moreLink);
		salesOrdersLink.click();
	}
	
	public void clickOnInvoice(WebActionsLib wlib, WebDriver driver) {
		wlib.mouseHoverOnElement(driver, moreLink);
		invoiceLink.click();
	}
	
	public void signOut(WebActionsLib wLib,WebDriver driver)
	{
		wLib.mouseHoverOnElement(driver, adminstrator);
		signoutLink.click();
	}
	
}















