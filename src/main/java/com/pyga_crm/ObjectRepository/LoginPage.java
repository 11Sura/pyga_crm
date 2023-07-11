package com.pyga_crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//declaration
	@FindBy(name="user_name")
	private WebElement usernameTxtBx;
	
	@FindBy(name="user_password")
	private WebElement passwordTxtBx;
	
	@FindAll({@FindBy(xpath="//input[@type='submit']"),@FindBy(id="submitButton")})
	private WebElement loginBtn;
	
	//initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//utilization
    public WebElement getUsernameTxtBx() {
		return usernameTxtBx;
	}

	public WebElement getPasswordTxtBx() {
		return passwordTxtBx;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//business logic
	public void login(String USERNAME, String PASSWORD) {
		usernameTxtBx.sendKeys(USERNAME);
	    passwordTxtBx.sendKeys(PASSWORD);
	    loginBtn.click();
	}
	
	
}
