package com.pyga.practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pyga_crm.ObjectRepository.ContactInformationPage;
import com.pyga_crm.ObjectRepository.ContactsPage;
import com.pyga_crm.ObjectRepository.CreateNewContactPage;
import com.pyga_crm.ObjectRepository.CreateNewOpportunityPage;
import com.pyga_crm.ObjectRepository.CreateNewOrganizationPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.LoginPage;
import com.pyga_crm.ObjectRepository.OpportunitiesPage;
import com.pyga_crm.ObjectRepository.OpportunityInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationsPage;
import com.pyga_crm.genericLib.CommondataFileLib;
import com.pyga_crm.genericLib.ExcelLib;
import com.pyga_crm.genericLib.JavaLib;
import com.pyga_crm.genericLib.WebActionsLib;

import net.bytebuddy.utility.RandomString;

public class OpportunitySystem {

	public static void main(String[] args) throws Throwable {
		CommondataFileLib clib = new CommondataFileLib();
		WebActionsLib wlib = new WebActionsLib();
		JavaLib jlib=new JavaLib();
		ExcelLib elib=new ExcelLib();

		//read data from properties file
		String FILEPATH = clib.getFilePathFromPropertiesFile("commondataConfigFilePath");
		String BROWSER = clib.getDataFromProperties(FILEPATH, "browser");
		String URL = clib.getDataFromProperties(FILEPATH, "url");
		String USERNAME = clib.getDataFromProperties(FILEPATH, "username");
		String PASSWORD = clib.getDataFromProperties(FILEPATH, "password");

		String E_FILEPATH=clib.getFilePathFromPropertiesFile("testScriptdataFilePath");

		WebDriver driver=null;

		//Launch the browser{
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else  if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} 
		else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		wlib.waitForElementInDOM(driver);

		//Login to the application
				driver.get(URL);
				LoginPage loginPage= new LoginPage(driver);
				loginPage.login(USERNAME, PASSWORD);

				HomePage homePage=new HomePage(driver);

		//create organization
		homePage.clickOnOrganizations();
        OrganizationsPage organization=new OrganizationsPage(driver);
        organization.clickOrgsLookup();
        int random = jlib.getRandomNumber();
		//organization details
        String org = elib.getDataFromExcelBasedTestId(E_FILEPATH, "OpportunitySystem", "Tc_01", "Organization_Name")+random;
        
		CreateNewOrganizationPage newOrg=new CreateNewOrganizationPage(driver);
		
		newOrg.organizationDetails(org);
		
		WebElement ind = newOrg.getIndustry();
		ind.click();
		wlib.select(ind, "Food & Beverage");
		//type
		WebElement type = newOrg.getAccountType();
		type.click();
		wlib.select(type, "Customer");

		//save
		newOrg.save();

        //verify
		OrganizationInformationPage orgInfo= new OrganizationInformationPage(driver);
		orgInfo.verify(org);

		
		//create a contact using organization
		WebElement contact = homePage.getContactsLink();
		wlib.waitAndClick(contact);
		
		ContactsPage contacts=new ContactsPage(driver);
		contacts.clickContactsLookup();
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		CreateNewContactPage newContact=new CreateNewContactPage(driver);

		String lname=elib.getDataFromExcelBasedTestId(E_FILEPATH, "OpportunitySystem", "Tc_02", "Contact_Name")+random;
		newContact.contactDetails(lname);
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		
		newContact.organizationPopup(org);
		WebElement in = newContact.getInDropdown();
		in.click();
		wlib.select(in, "accountname");
		newContact.search();
		newContact.selectOrganization(driver, org);
		wlib.swithToWindowBasedOnURL(driver, "module=Contacts");
		newContact.save();

		ContactInformationPage contactInformation=new ContactInformationPage(driver);
		contactInformation.verify(lname);
		
		//create opportunity using created contact
		
		homePage.clickOnOpportunities();
		OpportunitiesPage opportunity=new OpportunitiesPage(driver);
		opportunity.clickcreateOpportunitysLookup();
		CreateNewOpportunityPage newOpportunity=new CreateNewOpportunityPage(driver);
		String opp=elib.getDataFromExcelBasedTestId(E_FILEPATH, "OpportunitySystem", "Tc_03", "Opportunity_Name")+random;
		newOpportunity.opportunityDetails(opp);
		WebElement related = newOpportunity.getRelatedToDropdown();
		wlib.select(related, "Contacts");
		newOpportunity.addContact();

		
		wlib.swithToWindowBasedOnURL(driver, "module=Contacts");
		
		newOpportunity.contactPopup(lname);
		WebElement in1 = newOpportunity.getInDropdown();
		in1.click();
		wlib.select(in1, "lastname");
		newOpportunity.search();
		//driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		newOpportunity.selectContact(driver, lname);
		//driver.findElement(By.xpath(" //a[contains(text(),'"+lname+"')]")).click();
		wlib.swithToWindowBasedOnURL(driver, "module=Potentials");
		newOpportunity.save();
		//driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();

		OpportunityInformationPage opportunityInformation=new OpportunityInformationPage(driver);
		opportunityInformation.verify(opp);
		/*
		WebElement check1 = driver.findElement(By.xpath("//span[contains(text(),'"+opp+" -  Opportunity Information')]"));
		if(check1.isDisplayed()) {
			System.out.println("Opportunity is created");
		}
		else {
			System.out.println("Opportunity is not created");
		}
		*/

		//logout of the application
		homePage.signOut(wlib, driver);
		/*
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

*/

		driver.close();
		System.out.println("Testscript passed");


		}

}

//test script ends here

