package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOpportunityPage {
	
	//declaration
	@FindBy(name="potentialname")
	private WebElement opportunityName;
	
	@FindBy(id="related_to_type")
	private WebElement relatedToDropdown;
	
	@FindBy(xpath="//input[@id='related_to_display']/../img[@title='Select']")
	private WebElement relatedToLookup;
	
	@FindBy(id="search_txt")
	private WebElement searchTxtBx;
	
	@FindBy(name="search_field")
	private WebElement inDropdown;
	
	@FindBy(xpath="//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement saveBtn;
	
	
	//initialization
	public CreateNewOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	//utilization
	public WebElement getOpportunityName() {
		return opportunityName;
	}


	public WebElement getRelatedToDropdown() {
		return relatedToDropdown;
	}


	public WebElement getRelatedToLookup() {
		return relatedToLookup;
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
	public void opportunityDetails(String OpportunityName) {
		opportunityName.sendKeys(OpportunityName);
		
	}
	public void addContact() {
		relatedToLookup.click();
	}
	
	public void contactPopup(String contactsName) {
		searchTxtBx.sendKeys(contactsName);
	}
	public void selectContact(WebDriver driver,String contactsName) {
	driver.findElement(By.xpath("//a[contains(text(),'"+contactsName+"')]")).click();
		
	}
	public void search() {
		searchBtn.click();
	}
	public void save() {
		saveBtn.click();
	}

}
