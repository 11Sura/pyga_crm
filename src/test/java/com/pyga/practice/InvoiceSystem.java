package com.pyga.practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pyga_crm.ObjectRepository.CampaignInformationPage;
import com.pyga_crm.ObjectRepository.CampaignsPage;
import com.pyga_crm.ObjectRepository.CreateNewCampaignPage;
import com.pyga_crm.ObjectRepository.CreateNewInvoicePage;
import com.pyga_crm.ObjectRepository.CreateNewOrganizationPage;
import com.pyga_crm.ObjectRepository.CreateNewProductPage;
import com.pyga_crm.ObjectRepository.CreateNewQuotePage;
import com.pyga_crm.ObjectRepository.CreateNewSalesOrderPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.InvoiceInformationPage;
import com.pyga_crm.ObjectRepository.InvoicePage;
import com.pyga_crm.ObjectRepository.LoginPage;
import com.pyga_crm.ObjectRepository.OrganizationInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationsPage;
import com.pyga_crm.ObjectRepository.ProductInformationPage;
import com.pyga_crm.ObjectRepository.ProductsPage;
import com.pyga_crm.ObjectRepository.QuoteInformationPage;
import com.pyga_crm.ObjectRepository.QuotesPage;
import com.pyga_crm.ObjectRepository.SalesOrderInformationPage;
import com.pyga_crm.ObjectRepository.SalesOrderPage;
import com.pyga_crm.genericLib.CommondataFileLib;
import com.pyga_crm.genericLib.ExcelLib;
import com.pyga_crm.genericLib.JavaLib;
import com.pyga_crm.genericLib.WebActionsLib;

import net.bytebuddy.utility.RandomString;

public class InvoiceSystem {
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
		int random = jlib.getRandomNumber();
		
		//create organization
		homePage.clickOnOrganizations();
        OrganizationsPage organization=new OrganizationsPage(driver);
        organization.clickOrgsLookup();
        
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
		
		//Create a quote
        homePage.clickOnQuotes(wlib, driver);		
		
        QuotesPage quotes=new QuotesPage(driver);
        quotes.clickQuotesLookup();
        CreateNewQuotePage newQuote=new CreateNewQuotePage(driver);
		String quotename = "quote"+random;
		String productName="zProd";
		newQuote.quotedetails(quotename);
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newQuote.organizationPopup(org);
		WebElement inOrg = newQuote.getInDropdown();
		inOrg.click();
		wlib.select(inOrg, "accountname");
		newQuote.search();
		newQuote.selectOrganization(driver, org);
		wlib.acceptAlert(driver);
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newQuote.addProduct();
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		
		newQuote.productPopup(productName);
		WebElement in1 = newQuote.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		newQuote.search();

		newQuote.selectProduct(driver, productName);
		
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newQuote.save();
		QuoteInformationPage quoteInformation=new QuoteInformationPage(driver);
		quoteInformation.verify(quotename);

		
		
		
		//Create a Sales order
		homePage.clickOnSalesOrders(wlib, driver);
		SalesOrderPage salesOrder = new SalesOrderPage(driver);
		salesOrder.clickSalesOrderLookup();
		CreateNewSalesOrderPage newSalesOrder = new CreateNewSalesOrderPage(driver);
		String salesordersub = "sale"+random;
		newSalesOrder.salesOrderdetails(salesordersub);

				wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
				newSalesOrder.organizationPopup(org);
				WebElement inOrgDDn = newQuote.getInDropdown();
				inOrgDDn.click();
				wlib.select(inOrgDDn, "accountname");
				newSalesOrder.search();
				newSalesOrder.selectOrganization(driver, org);
				wlib.acceptAlert(driver);
				wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		
		newSalesOrder.addQuote();
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newSalesOrder.quotePopup(quotename);
		WebElement inOrgDD = newQuote.getInDropdown();
		inOrgDD.click();
		wlib.select(inOrgDD, "subject");
		newSalesOrder.search();
		newSalesOrder.selectOrganization(driver, quotename);
		
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		newSalesOrder.save();
		//Verify Vendor created
		SalesOrderInformationPage salesOrderInformation =new SalesOrderInformationPage(driver);
		salesOrderInformation.verify(salesordersub);
				
				//Create Invoice
		homePage.clickOnInvoice(wlib, driver);
		
				
		InvoicePage invoice=new InvoicePage(driver);
		invoice.clickInvoiceLookup();
				
		CreateNewInvoicePage newInvoice=new CreateNewInvoicePage(driver);
		String invoiceName="invoice"+random;
		newInvoice.invoiceDetails(invoiceName);
		
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newInvoice.organizationPopup(org);
		WebElement inOrgdd = newInvoice.getInDropdown();
		inOrgdd.click();
		wlib.select(inOrgdd, "accountname");
		newInvoice.search();
		newInvoice.selectOrganization(driver, org);
		wlib.acceptAlert(driver);
		wlib.swithToWindowBasedOnURL(driver, "module=Invoice");
			
		newInvoice.addSalesOrder();
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		newInvoice.salesOrderPopup(salesordersub);
		WebElement inSaleDD = newInvoice.getInDropdown();
		inSaleDD.click();
		wlib.select(inSaleDD, "subject");
		newInvoice.search();
		newInvoice.selectSalesOrder(driver, salesordersub);
		wlib.swithToWindowBasedOnURL(driver, "module=Invoice");
		
		newInvoice.save();
				//verify Invoice created
		InvoiceInformationPage invoiceInformation=new InvoiceInformationPage(driver);
		invoiceInformation.verify(invoiceName);
				//logout
		homePage.signOut(wlib, driver);

			
				driver.close();
				
				System.out.println("Testscript passed");
	}


}
