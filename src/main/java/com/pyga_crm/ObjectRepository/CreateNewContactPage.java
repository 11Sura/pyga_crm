package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	//declaration
	@FindBy(name="lastname")
	private WebElement lastNameTxtBx;
	
	@FindBy(xpath="//td[normalize-space()='Organization Name']/..//img[@alt='Select']")
	private WebElement addOrganization;
	
	@FindBy(id="search_txt")
	private WebElement searchTxtBx;
	
	@FindBy(name="search_field")
	private WebElement inDropdown;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBtn;
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement saveBtn;
	
	
	//initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getLastNameTxtBx() {
		return lastNameTxtBx;
	}


	public WebElement getAddOrganization() {
		return addOrganization;
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
	public void contactDetails(String lastName) {
		lastNameTxtBx.sendKeys(lastName);
		addOrganization.click();
		
	}
	public void organizationPopup(String organizationName) {
		searchTxtBx.sendKeys(organizationName);
	}
	public void selectOrganization(WebDriver driver, String organizationName) {
		driver.findElement(By.xpath("//a[.='"+organizationName+"']")).click();

	}
	public void search() {
		searchBtn.click();
	}
	public void save() {
		saveBtn.click();
	}

}
