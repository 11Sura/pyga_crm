package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pyga_crm.genericLib.WebActionsLib;

public class CreateNewSalesOrderPage {
	WebActionsLib wlib=new WebActionsLib();
	//declaration
	@FindBy(xpath="//input[@name='subject']")
	private WebElement salesOrderName;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement addOrganization;
	
	@FindBy(xpath="//img[@onclick='selectQuote()']")
	private WebElement quoteNameLookup;
	
	@FindBy(id="search_txt")
	private WebElement searchTxtBx;

	@FindBy(name="search_field")
	private WebElement inDropdown;

	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBtn;

	@FindBy(xpath="//img[@id='searchIcon1']")
	private WebElement productsLookup;

	@FindBy(xpath="//input[@id='qty1']")
	private WebElement quantityTxtBx;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	//initialization
	public CreateNewSalesOrderPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getSalesOrderName() {
		return salesOrderName;
	}


	public WebElement getAddOrganization() {
		return addOrganization;
	}


	public WebElement getQuoteNameLookup() {
		return quoteNameLookup;
	}


	public WebElement getSearchTxtBx() {
		return searchTxtBx;
	}


	public WebElement getInDropdown() {
		return inDropdown;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getProductsLookup() {
		return productsLookup;
	}


	public WebElement getQuantityTxtBx() {
		return quantityTxtBx;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	//business logic
	public void salesOrderdetails(String SalesOrderName) {
		salesOrderName.sendKeys(SalesOrderName);
		quantityTxtBx.sendKeys("46");
		addOrganization.click();
		}
	
	public void organizationPopup(String organizationName) {
		searchTxtBx.sendKeys(organizationName);
	}
	public void selectOrganization(WebDriver driver, String organizationName) {
		driver.findElement(By.xpath("//a[.='"+organizationName+"']")).click();

	}
	public void addProduct() {
		productsLookup.click();
	}
	public void productPopup(String productsName) {
		searchTxtBx.sendKeys(productsName);
	}
	public void selectProduct(WebDriver driver,String productsName) {
		driver.findElement(By.xpath("//a[.='"+productsName+"']")).click();
	}
	
	public void addQuote() {
		quoteNameLookup.click();
	}
	
	public void quotePopup(String quoteName) {
		searchTxtBx.sendKeys(quoteName);
	}
	
	public void selectQuote(WebDriver driver,String quoteName) {
		driver.findElement(By.xpath("//a[.='"+quoteName+"']")).click();
	}

	
	public void search() {
		searchBtn.click();
	}
	public void save() throws Throwable {
		wlib.waitAndClick(saveBtn);
		//saveBtn.click();
	}
	
	
}