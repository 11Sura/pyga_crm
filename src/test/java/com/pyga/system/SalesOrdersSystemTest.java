package com.pyga.system;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pyga_crm.ObjectRepository.CreateNewProductPage;
import com.pyga_crm.ObjectRepository.CreateNewQuotePage;
import com.pyga_crm.ObjectRepository.CreateNewSalesOrderPage;
import com.pyga_crm.ObjectRepository.CreateNewVendorPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.ProductInformationPage;
import com.pyga_crm.ObjectRepository.ProductsPage;
import com.pyga_crm.ObjectRepository.QuoteInformationPage;
import com.pyga_crm.ObjectRepository.QuotesPage;
import com.pyga_crm.ObjectRepository.SalesOrderInformationPage;
import com.pyga_crm.ObjectRepository.SalesOrderPage;
import com.pyga_crm.ObjectRepository.VendorInformationPage;
import com.pyga_crm.ObjectRepository.VendorsPage;
import com.pyga_crm.genericLib.BaseClass;

public class SalesOrdersSystemTest extends BaseClass {
	
	@Test(groups = "regressionTest")
	public void salesOrdersSystemTest() throws Throwable {

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
		
		//verify if product is created
		ProductInformationPage productInformation = new ProductInformationPage(driver);
		productInformation.verify(pro);
		

		//Create Quote(using products)
		homePage.clickOnQuotes(wlib, driver);		
		QuotesPage quotes=new QuotesPage(driver);
		quotes.clickQuotesLookup();
		//quote details
		CreateNewQuotePage newQuote=new CreateNewQuotePage(driver);
		String quotename = "quote"+random;
		String orgname="organi1";
		newQuote.quotedetails(quotename);
		//switch to organization popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newQuote.organizationPopup(orgname);
		WebElement inOrg = newQuote.getInDropdown();
		inOrg.click();
		wlib.select(inOrg, "accountname");
		newQuote.search();
		newQuote.selectOrganization(driver, orgname);
		wlib.acceptAlert(driver);
		//switch back to create quote window
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newQuote.addProduct();
		//switch to product popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		
		
		
		WebElement in1 = newQuote.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		

		newQuote.productPopup(pro);
		

			
		newQuote.search();
		newQuote.selectProduct(driver, pro);
		//switch back to create quote window
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		//save
		newQuote.save();
		
		//verify if quote is created
		QuoteInformationPage quoteInformation=new QuoteInformationPage(driver);
		quoteInformation.verify(quotename);


		//Create a Sales order(using quote)
		homePage.clickOnSalesOrders(wlib, driver);
		SalesOrderPage salesOrder = new SalesOrderPage(driver);
		salesOrder.clickSalesOrderLookup();
		//sales order details
		CreateNewSalesOrderPage newSalesOrder = new CreateNewSalesOrderPage(driver);
		String salesordersub = "sale"+random;
		newSalesOrder.salesOrderdetails(salesordersub);
        //switch to organization popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newSalesOrder.organizationPopup(orgname);
		WebElement inOrgDDn = newQuote.getInDropdown();
		inOrgDDn.click();
		wlib.select(inOrgDDn, "accountname");
		newSalesOrder.search();
		newSalesOrder.selectOrganization(driver, orgname);
		wlib.acceptAlert(driver);
		//switch back to create sales order window
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		newSalesOrder.addQuote();
		//switch to quote popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newSalesOrder.quotePopup(quotename);
		WebElement inOrgDD = newQuote.getInDropdown();
		inOrgDD.click();
		wlib.select(inOrgDD, "subject");
		newSalesOrder.search();
		newSalesOrder.selectOrganization(driver, quotename);
        //switch back to create sales order window
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		//save
		newSalesOrder.save();
		
		//Verify Vendor created
		SalesOrderInformationPage salesOrderInformation =new SalesOrderInformationPage(driver);
		salesOrderInformation.verify(salesordersub);

		Reporter.log("Testscript passed",true);

	}

}
