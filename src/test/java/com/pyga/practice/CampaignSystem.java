package com.pyga.practice;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.pyga_crm.ObjectRepository.CampaignInformationPage;
import com.pyga_crm.ObjectRepository.CampaignsPage;
import com.pyga_crm.ObjectRepository.CreateNewCampaignPage;
import com.pyga_crm.ObjectRepository.CreateNewProductPage;
import com.pyga_crm.ObjectRepository.CreateNewVendorPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.LoginPage;
import com.pyga_crm.ObjectRepository.ProductInformationPage;
import com.pyga_crm.ObjectRepository.ProductsPage;
import com.pyga_crm.ObjectRepository.VendorInformationPage;
import com.pyga_crm.ObjectRepository.VendorsPage;
import com.pyga_crm.genericLib.CommondataFileLib;
import com.pyga_crm.genericLib.ExcelLib;
import com.pyga_crm.genericLib.JavaLib;
import com.pyga_crm.genericLib.WebActionsLib;


public class CampaignSystem {

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
		/*
		System.out.println("selecting category");
		
		WebElement pcat = driver.findElement(By.name("productcategory"));
		pcat.click();
		Select s= new Select(pcat);
		s.selectByValue("Hardware");
		
		System.out.println("done selecting");
		*/
		createNewProduct.save();
		//verify if product is created
		ProductInformationPage productInformation = new ProductInformationPage(driver);
		productInformation.verify(pro);

		
		//Create Campaign
		homePage.clickOnCampaigns(wlib, driver);
		CampaignsPage campaign=new CampaignsPage(driver);
		campaign.clickCampaignsLookup();
		//campaign details
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		String camp = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_03", "Campaign_Name")+random;
		String sponsor = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_03", "Sponsor");

		newCampaign.campaignDetails(camp, sponsor);


		
		wlib.swithToWindowBasedOnURL(driver, "module=Products");

		newCampaign.productPopup(pro);
		WebElement in1 = newCampaign.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		newCampaign.search();

		newCampaign.selectProduct(driver, pro);
		
		wlib.swithToWindowBasedOnURL(driver, "module=Campaigns");

		WebElement type = newCampaign.getCampaignType();
		type.click();
		wlib.select(type, "Advertisement");
		
		WebElement res = newCampaign.geteResponse();
		wlib.select(res, "Good");
		
		newCampaign.save();

		//verify campaign is created
		CampaignInformationPage campaignInformation =new CampaignInformationPage(driver);
		campaignInformation.verify(camp);
		
		//logout
		homePage.signOut(wlib, driver);
		
		//close the browser
		driver.close();

		System.out.println("Testscript passed");

	}

}
