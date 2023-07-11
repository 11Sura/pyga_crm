package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewInvoicePage {
	//declaration
		@FindBy(xpath="//input[@name='subject']")
		private WebElement invoiceName;
		
		@FindBy(xpath="//input[@name='account_name']/following-sibling::img[@title='Select']")
		private WebElement addOrganization;
		
		@FindBy(xpath="//img[@onclick='selectSalesOrder();']")
		private WebElement addSalesOrder;
		
		@FindBy(id="search_txt")
		private WebElement searchTxtBx;

		@FindBy(name="search_field")
		private WebElement inDropdown;

		@FindBy(xpath="//input[@name='search']")
		private WebElement searchBtn;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		
		//initialization
		public CreateNewInvoicePage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}


		//utilization
		public WebElement getInvoiceName() {
			return invoiceName;
		}


		public WebElement getAddOrganization() {
			return addOrganization;
		}


		public WebElement getAddSalesOrder() {
			return addSalesOrder;
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
		public void invoiceDetails(String InvoiceName) {
			invoiceName.sendKeys(InvoiceName);
			addOrganization.click();
		}
		
		public void organizationPopup(String organizationName) {
			searchTxtBx.sendKeys(organizationName);
		}
		public void selectOrganization(WebDriver driver, String organizationName) {
			driver.findElement(By.xpath("//a[.='"+organizationName+"']")).click();

		}
		
		public void addSalesOrder() {
			addSalesOrder.click();
		}
		public void salesOrderPopup(String salesOrderName) {
			searchTxtBx.sendKeys(salesOrderName);
		}
		public void selectSalesOrder(WebDriver driver, String salesOrderName) {
			driver.findElement(By.xpath("//a[.='"+salesOrderName+"']")).click();

		}

		public void search() {
			searchBtn.click();
		}
		public void save() {
			saveBtn.click();
		}
		

}
