package com.pyga.system;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pyga_crm.ObjectRepository.CreateNewInvoicePage;
import com.pyga_crm.ObjectRepository.CreateNewOrganizationPage;
import com.pyga_crm.ObjectRepository.CreateNewQuotePage;
import com.pyga_crm.ObjectRepository.CreateNewSalesOrderPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.InvoiceInformationPage;
import com.pyga_crm.ObjectRepository.InvoicePage;
import com.pyga_crm.ObjectRepository.OrganizationInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationsPage;
import com.pyga_crm.ObjectRepository.QuoteInformationPage;
import com.pyga_crm.ObjectRepository.QuotesPage;
import com.pyga_crm.ObjectRepository.SalesOrderInformationPage;
import com.pyga_crm.ObjectRepository.SalesOrderPage;
import com.pyga_crm.genericLib.BaseClass;

public class InvoiceSystemTest extends BaseClass {
	@Test(groups = "regressionTest")
	public void invoiceSystemTest() throws Throwable {

		String E_FILEPATH=clib.getFilePathFromPropertiesFile("testScriptdataFilePath");

		HomePage homePage=new HomePage(driver);
		int random = jlib.getRandomNumber();

		//Create Organization
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

		//verify if organization is created
		OrganizationInformationPage orgInfo= new OrganizationInformationPage(driver);
		orgInfo.verify(org);

		
		//Create a quote(using organization)
		homePage.clickOnQuotes(wlib, driver);		
		QuotesPage quotes=new QuotesPage(driver);
		quotes.clickQuotesLookup();
		//quote details
		CreateNewQuotePage newQuote=new CreateNewQuotePage(driver);
		String quotename = "quote"+random;
		String productName="zProd";
		newQuote.quotedetails(quotename);
		//switch to organization popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newQuote.organizationPopup(org);
		WebElement inOrg = newQuote.getInDropdown();
		inOrg.click();
		wlib.select(inOrg, "accountname");
		newQuote.search();
		newQuote.selectOrganization(driver, org);
		wlib.acceptAlert(driver);
		//switch back to create quote window
		wlib.swithToWindowBasedOnURL(driver, "module=Quotes");
		newQuote.addProduct();
		//switch to product popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Products");
		newQuote.productPopup(productName);
		WebElement in1 = newQuote.getInDropdown();
		in1.click();
		wlib.select(in1, "productname");
		newQuote.search();
		newQuote.selectProduct(driver, productName);
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
		newSalesOrder.organizationPopup(org);
		WebElement inOrgDDn = newQuote.getInDropdown();
		inOrgDDn.click();
		wlib.select(inOrgDDn, "accountname");
		newSalesOrder.search();
		newSalesOrder.selectOrganization(driver, org);
		wlib.acceptAlert(driver);
		//switch back to create sales order window
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		newSalesOrder.addQuote();
		//switch to quotes popup window
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
		
		//verify if vendor is created
		SalesOrderInformationPage salesOrderInformation =new SalesOrderInformationPage(driver);
		salesOrderInformation.verify(salesordersub);

		
		//Create Invoice(using sales order)
		homePage.clickOnInvoice(wlib, driver);
		InvoicePage invoice=new InvoicePage(driver);
		invoice.clickInvoiceLookup();
        //invoice details
		CreateNewInvoicePage newInvoice=new CreateNewInvoicePage(driver);
		String invoiceName="invoice"+random;
		newInvoice.invoiceDetails(invoiceName);
        //switch to organization popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newInvoice.organizationPopup(org);
		WebElement inOrgdd = newInvoice.getInDropdown();
		inOrgdd.click();
		wlib.select(inOrgdd, "accountname");
		newInvoice.search();
		newInvoice.selectOrganization(driver, org);
		wlib.acceptAlert(driver);
		//switch back to create invoice window
		wlib.swithToWindowBasedOnURL(driver, "module=Invoice");
		newInvoice.addSalesOrder();
		//switch to sales order popup window
		wlib.swithToWindowBasedOnURL(driver, "module=SalesOrder");
		newInvoice.salesOrderPopup(salesordersub);
		WebElement inSaleDD = newInvoice.getInDropdown();
		inSaleDD.click();
		wlib.select(inSaleDD, "subject");
		newInvoice.search();
		newInvoice.selectSalesOrder(driver, salesordersub);
		//switch back to create invoice window
		wlib.swithToWindowBasedOnURL(driver, "module=Invoice");
        //save
		newInvoice.save();
		
		//verify if invoice is created
		InvoiceInformationPage invoiceInformation=new InvoiceInformationPage(driver);
		invoiceInformation.verify(invoiceName);

		Reporter.log("Testscript passed",true);

	}

}
