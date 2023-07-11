package com.pyga.system;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.pyga_crm.ObjectRepository.CampaignInformationPage;
import com.pyga_crm.ObjectRepository.CampaignsPage;
import com.pyga_crm.ObjectRepository.CreateNewCampaignPage;
import com.pyga_crm.ObjectRepository.CreateNewProductPage;
import com.pyga_crm.ObjectRepository.CreateNewVendorPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.ProductInformationPage;
import com.pyga_crm.ObjectRepository.ProductsPage;
import com.pyga_crm.ObjectRepository.VendorInformationPage;
import com.pyga_crm.ObjectRepository.VendorsPage;
import com.pyga_crm.genericLib.BaseClass;

@Listeners(com.pyga_crm.genericLib.ListenersImplementation.class)
public class CampaignSystemTest extends BaseClass {

	@Test(groups = "smokeTest",retryAnalyzer = com.pyga_crm.genericLib.RetryImplementation.class)
	public void campaignSystemTest() throws Throwable {

		String E_FILEPATH=clib.getFilePathFromPropertiesFile("testScriptdataFilePath");

		HomePage homePage=new HomePage(driver);
		int random = jlib.getRandomNumber();

		//Create Vendor
		homePage.clickOnVendors(wlib, driver);
		VendorsPage vendorsPage=new VendorsPage(driver);
		vendorsPage.clickVendorsLookup();
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


		//Create Product(using vendor)
		homePage.clickOnProducts();
		//product details
		ProductsPage products=new ProductsPage(driver);
		products.clickProductsLookup();
		String pro = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_02", "Product_Name")+random;
		CreateNewProductPage createNewProduct =new CreateNewProductPage(driver);
		createNewProduct.productDetails(pro);
		//switch to vendor popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Vendors");
		createNewProduct.vendorPopup(ven);
		WebElement in = createNewProduct.getInDropdown();
		in.click();
		wlib.select(in, "vendorname");
		createNewProduct.search();
		createNewProduct.selectVendor(driver, ven);
		//switch back to create product window
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		//save
		createNewProduct.save();
		
//Assert.fail();
		
		//verify if product is created
		ProductInformationPage productInformation = new ProductInformationPage(driver);
		productInformation.verify(pro);


		//Create Campaign(using product)
		homePage.clickOnCampaigns(wlib, driver);
		CampaignsPage campaign=new CampaignsPage(driver);
		campaign.clickCampaignsLookup();
		//campaign details
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		String camp = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_03", "Campaign_Name")+random;
		String sponsor = elib.getDataFromExcelBasedTestId(E_FILEPATH, "CampaignSystem", "Tc_03", "Sponsor");
		newCampaign.campaignDetails(camp, sponsor);
        //switch to product popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		newCampaign.productPopup(pro);
		WebElement in1 = newCampaign.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		newCampaign.search();
		newCampaign.selectProduct(driver, pro);
        //switch back to create campaign window
		wlib.swithToWindowBasedOnURL(driver, "module=Campaigns");
		WebElement type = newCampaign.getCampaignType();
		type.click();
		wlib.select(type, "Advertisement");
		WebElement res = newCampaign.geteResponse();
		wlib.select(res, "Good");
        //save
		newCampaign.save();

		//verify campaign is created
		CampaignInformationPage campaignInformation =new CampaignInformationPage(driver);
		campaignInformation.verify(camp);

		Reporter.log("Testscript passed",true);

	}

}
