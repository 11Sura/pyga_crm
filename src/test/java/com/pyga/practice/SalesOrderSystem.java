package com.pyga.practice;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.pyga_crm.ObjectRepository.CreateNewProductPage;
import com.pyga_crm.ObjectRepository.CreateNewQuotePage;
import com.pyga_crm.ObjectRepository.CreateNewSalesOrderPage;
import com.pyga_crm.ObjectRepository.CreateNewVendorPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.LoginPage;
import com.pyga_crm.ObjectRepository.ProductInformationPage;
import com.pyga_crm.ObjectRepository.ProductsPage;
import com.pyga_crm.ObjectRepository.QuoteInformationPage;
import com.pyga_crm.ObjectRepository.QuotesPage;
import com.pyga_crm.ObjectRepository.SalesOrderInformationPage;
import com.pyga_crm.ObjectRepository.SalesOrderPage;
import com.pyga_crm.ObjectRepository.VendorInformationPage;
import com.pyga_crm.ObjectRepository.VendorsPage;
import com.pyga_crm.genericLib.CommondataFileLib;
import com.pyga_crm.genericLib.ExcelLib;
import com.pyga_crm.genericLib.JavaLib;
import com.pyga_crm.genericLib.WebActionsLib;

import net.bytebuddy.utility.RandomString;

public class SalesOrderSystem {


	public static void main(String[] args) throws Throwable {
	
		CommondataFileLib clib = new CommondataFileLib();
		WebActionsLib wlib = new WebActionsLib();
		JavaLib jlib=new JavaLib();
		ExcelLib elib=new ExcelLib();
		
		String FILEPATH = clib.getFilePathFromPropertiesFile("commondataConfigFilePath");
		String BROWSER = clib.getDataFromProperties(FILEPATH, "browser");
//String BROWSER="firefox";
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
		
		//Create Vendor
		homePage.clickOnVendors(wlib, driver);

		VendorsPage vendorsPage=new VendorsPage(driver);
		vendorsPage.clickVendorsLookup();

		int random = jlib.getRandomNumber();
		//vendor details
		String ven = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_01", "Vendor_Name")+random;
		String email = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_01", "Email");
		String category = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_01", "Category");
		String phone = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_01", "Phone");


		CreateNewVendorPage createNewVendor=new CreateNewVendorPage(driver);
		createNewVendor.vendorDetails(ven, email, category, phone);

		//verify vendor is created or not
		VendorInformationPage vendorInformation=new VendorInformationPage(driver);
		vendorInformation.verify(ven);


		//Create Product
		homePage.clickOnProducts();
		//product details
		ProductsPage products=new ProductsPage(driver);
		products.clickProductsLookup();
		String pro = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_02", "Product_Name")+random;
		CreateNewProductPage createNewProduct =new CreateNewProductPage(driver);
		createNewProduct.productDetails(pro);
		wlib.swithToWindowBasedOnURL(driver, "module=Vendors");
		createNewProduct.vendorPopup(ven);
		
		WebElement in = createNewProduct.getInDropdown();
		in.click();
		wlib.select(in, "vendorname");

		createNewProduct.search();

		createNewProduct.selectVendor(driver, ven);
		
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		createNewProduct.save();
		//verify if product is created
		ProductInformationPage productInformation = new ProductInformationPage(driver);
		productInformation.verify(pro);

		//Create a quote
        homePage.clickOnQuotes(wlib, driver);		
		
        QuotesPage quotes=new QuotesPage(driver);
        quotes.clickQuotesLookup();
        CreateNewQuotePage newQuote=new CreateNewQuotePage(driver);
		String quotename = "quote"+random;
		String orgname="organi1";
		newQuote.quotedetails(quotename);
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newQuote.organizationPopup(orgname);
		WebElement inOrg = newQuote.getInDropdown();
		inOrg.click();
		wlib.select(inOrg, "accountname");
		newQuote.search();
		newQuote.selectOrganization(driver, orgname);
		wlib.acceptAlert(driver);
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newQuote.addProduct();
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		
//Thread.sleep(2000);	
		
        newQuote.productPopup(pro);
		
//Thread.sleep(2000);		
		
		WebElement in1 = newQuote.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		

		
		newQuote.search();

		newQuote.selectProduct(driver, pro);
		
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

		//click on add organization and select
				wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
				newSalesOrder.organizationPopup(orgname);
				WebElement inOrgDDn = newQuote.getInDropdown();
				inOrgDDn.click();
				wlib.select(inOrgDDn, "accountname");
				newSalesOrder.search();
				newSalesOrder.selectOrganization(driver, orgname);
				wlib.acceptAlert(driver);
				wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		
		//click on quote and select
		newSalesOrder.addQuote();
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newSalesOrder.quotePopup(quotename);
		WebElement inOrgDD = newQuote.getInDropdown();
		inOrgDD.click();
		wlib.select(inOrgDD, "subject");
		newSalesOrder.search();
		newSalesOrder.selectOrganization(driver, quotename);
		
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		
//Thread.sleep(2000);		
		newSalesOrder.save();
		
		//Verify Vendor created
		SalesOrderInformationPage salesOrderInformation =new SalesOrderInformationPage(driver);
		salesOrderInformation.verify(salesordersub);
		
		//logout
		homePage.signOut(wlib, driver);
        driver.close();
		System.out.println("Testscript passed");
	}
}
