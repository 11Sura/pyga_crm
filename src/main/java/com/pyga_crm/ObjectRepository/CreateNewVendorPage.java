package com.pyga_crm.ObjectRepository;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CreateNewVendorPage {
	//declaration
	@FindBy(name="vendorname")
	private WebElement vendorName;
	
	@FindBy(id="email")
	private WebElement emailID;
	
	@FindBy(id="category")
	private WebElement categoryTxtBx;
	
	@FindBy(id="phone")
	private WebElement phoneNo;
	
	@FindBy(xpath="//input[@name='website']")
	private WebElement websiteTxtBx;
	
	@FindBy(xpath="//textarea[@name='street']")
	private WebElement streetTxtBx;
	
	@FindBy(id="city")
	private WebElement cityTxtBX;
	
	@FindBy(id="postalcode")
	private WebElement postalCode;
	
	@FindBy(id="pobox")
	private WebElement poBox;
	
	@FindBy(id="state")
	private WebElement state;
	
	@FindBy(id="country")
	private WebElement country;
	
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement description;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	
	//initialization
	public CreateNewVendorPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	
	//utilization
    public WebElement getVendorName() {
		return vendorName;
	}


	public WebElement getEmailID() {
		return emailID;
	}


	public WebElement getCategoryTxtBx() {
		return categoryTxtBx;
	}


	public WebElement getPhoneNo() {
		return phoneNo;
	}


	public WebElement getWebsiteTxtBx() {
		return websiteTxtBx;
	}


	public WebElement getStreetTxtBx() {
		return streetTxtBx;
	}


	public WebElement getCityTxtBX() {
		return cityTxtBX;
	}


	public WebElement getPostalCode() {
		return postalCode;
	}


	public WebElement getPoBox() {
		return poBox;
	}


	public WebElement getState() {
		return state;
	}


	public WebElement getCountry() {
		return country;
	}


	public WebElement getDescription() {
		return description;
	}


	public WebElement getSave() {
		return save;
	}
	
	
	
	//business logic
	public void vendorDetails(String vendorsName,String email,String category,String phone) {
        vendorName.sendKeys(vendorsName);
        emailID.sendKeys(email);
        categoryTxtBx.sendKeys(category);
        phoneNo.sendKeys(phone);
        websiteTxtBx.sendKeys("www."+vendorsName+".com");
        streetTxtBx.sendKeys("abc123 street");
        cityTxtBX.sendKeys("Bengaluru");
        postalCode.sendKeys("345678");
        poBox.sendKeys("xyz-123456");
        state.sendKeys("Karnataka");
        country.sendKeys("India");
        description.sendKeys("Testing description");
        save.click();
		
	}
	

}
