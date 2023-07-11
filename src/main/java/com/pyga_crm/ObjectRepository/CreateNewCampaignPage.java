package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	
	@FindBy(name="campaignname")
	private WebElement campaignName;
	
	@FindBy(name="campaigntype")
	private WebElement campaignType;
	
	@FindBy(id="targetaudience")
	private WebElement audience;
	
	@FindBy(id="sponsor")
	private WebElement sponsor;
	
	@FindBy(id="numsent")
	private WebElement numsent;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement addProduct;
	
	@FindBy(id="search_txt")
	private WebElement searchTxtBx;
	
	@FindBy(xpath="//select[@name='search_field']")
	private WebElement inDropdown;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBtn;
	
    @FindBy(id="targetsize")
	private WebElement targetSize;
    
    @FindBy(name="budgetcost")
   	private WebElement budgetCost;
    
    @FindBy(name="expectedresponse")
   	private WebElement eResponse;
    
    @FindBy(id="expectedsalescount")
   	private WebElement eSaleCount;
    
    @FindBy(id="expectedresponsecount")
   	private WebElement eResCount;
    
    @FindBy(name="actualcost")
   	private WebElement aCost;
    
    @FindBy(name="expectedrevenue")
   	private WebElement eRevenue;
    
    @FindBy(name="description")
   	private WebElement description;
    
    @FindBy(xpath="//input[@title='Save [Alt+S]']")
   	private WebElement saveBtn;
    
    
    //initialization
    public CreateNewCampaignPage(WebDriver driver) {
    	PageFactory.initElements(driver,this);
    }

    
    //utilization
	public WebElement getCampaignName() {
		return campaignName;
	}


	public WebElement getCampaignType() {
		return campaignType;
	}


	public WebElement getAudience() {
		return audience;
	}


	public WebElement getSponsor() {
		return sponsor;
	}


	public WebElement getNumsent() {
		return numsent;
	}


	public WebElement getAddProduct() {
		return addProduct;
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


	public WebElement getTargetSize() {
		return targetSize;
	}


	public WebElement getBudgetCost() {
		return budgetCost;
	}


	public WebElement geteResponse() {
		return eResponse;
	}


	public WebElement geteSaleCount() {
		return eSaleCount;
	}


	public WebElement geteResCount() {
		return eResCount;
	}


	public WebElement getaCost() {
		return aCost;
	}


	public WebElement geteRevenue() {
		return eRevenue;
	}


	public WebElement getDescription() {
		return description;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
    
	//business logic
	public void campaignDetails(String camapignsName,String sponsorName) {
		campaignName.sendKeys(camapignsName);
		audience.sendKeys("Kids");
		sponsor.sendKeys(sponsorName);
		numsent.sendKeys("75");
		targetSize.sendKeys("500");
		budgetCost.clear();
		budgetCost.sendKeys("50000");
		eSaleCount.sendKeys("5000");
		eResCount.sendKeys("4000");
		aCost.clear();
		aCost.sendKeys("47900");
		eRevenue.clear();
		eRevenue.sendKeys("100000");
		description.sendKeys("Testing description");
		addProduct.click();
		
	}
	
	public void productPopup(String productsName) {
		searchTxtBx.sendKeys(productsName);
	}
	public void selectProduct(WebDriver driver,String productsName) {
		driver.findElement(By.xpath("//a[.='"+productsName+"']")).click();
	}
	public void search() {
		searchBtn.click();
	}
	public void save() {
		saveBtn.click();
	}
	

}
