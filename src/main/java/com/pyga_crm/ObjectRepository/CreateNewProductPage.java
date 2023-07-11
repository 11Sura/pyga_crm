package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CreateNewProductPage {
	@FindBy(name="productname")
	private WebElement productName;
	
	@FindBy(id="jscal_trigger_sales_start_date")
	private WebElement saleStart;
	
	@FindBy(xpath="//td[contains(text(),'›')]")
	private WebElement changeMonth;
	
	@FindBy(xpath="//td[.='13']")
	private WebElement changeDate;
	
	@FindBy(name="productcategory")
	private WebElement categoryDropdown;
	
	@FindBy(id="jscal_field_sales_end_date")
	private WebElement saleEnd;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement addVendor;
	
	@FindBy(id="search_txt")
	private WebElement searchTxtBx;
	
	@FindBy(xpath="//select[@class='txtBox']")
	private WebElement inDropdown;
	
	@FindBy(xpath="//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement saveBtn;
	
	
	//initialization
	public CreateNewProductPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	
	//utilization
	public WebElement getProductName() {
		return productName;
	}


	public WebElement getSaleStart() {
		return saleStart;
	}


	public WebElement getChangeMonth() {
		return changeMonth;
	}


	public WebElement getChangeDate() {
		return changeDate;
	}


	public WebElement getCategoryDropdown() {
		return categoryDropdown;
	}


	public WebElement getSaleEnd() {
		return saleEnd;
	}


	public WebElement getAddVendor() {
		return addVendor;
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


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	//business logic
	public void productDetails(String productsName) {
		productName.sendKeys(productsName);
		saleStart.click();
		changeMonth.click();
		changeDate.click();
		categoryDropdown.click();
		saleEnd.sendKeys("2023-08-21");
		addVendor.click();
		
	}
	public void vendorPopup(String vendorsName) {
		searchTxtBx.sendKeys(vendorsName);
		
	}
	public void selectVendor(WebDriver driver,String vendorsName) {
		driver.findElement(By.xpath("//a[.='"+vendorsName+"']")).click();
	}
	
	public void search() {
		searchBtn.click();
	}
	
	public void save() {
		saveBtn.click();
	}	
		
	
	

}
