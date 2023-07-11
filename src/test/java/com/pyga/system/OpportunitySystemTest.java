package com.pyga.system;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pyga_crm.ObjectRepository.ContactInformationPage;
import com.pyga_crm.ObjectRepository.ContactsPage;
import com.pyga_crm.ObjectRepository.CreateNewContactPage;
import com.pyga_crm.ObjectRepository.CreateNewOpportunityPage;
import com.pyga_crm.ObjectRepository.CreateNewOrganizationPage;
import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.OpportunitiesPage;
import com.pyga_crm.ObjectRepository.OpportunityInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationInformationPage;
import com.pyga_crm.ObjectRepository.OrganizationsPage;
import com.pyga_crm.genericLib.BaseClass;

public class OpportunitySystemTest extends BaseClass {

	@Test(groups = {"smokeTest","regressionTest"})
	public void opportunitySystemTest() throws Throwable {

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


		//Create Contact(using organization)
		WebElement contact = homePage.getContactsLink();
		wlib.waitAndClick(contact);
		ContactsPage contacts=new ContactsPage(driver);
		contacts.clickContactsLookup();
		CreateNewContactPage newContact=new CreateNewContactPage(driver);
		String lname=elib.getDataFromExcelBasedTestId(E_FILEPATH, "OpportunitySystem", "Tc_02", "Contact_Name")+random;
		newContact.contactDetails(lname);
		//switch to organization popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Accounts");
		newContact.organizationPopup(org);
		WebElement in = newContact.getInDropdown();
		in.click();
		wlib.select(in, "accountname");
		newContact.search();
		newContact.selectOrganization(driver, org);
		//switch back to create contact window
		wlib.swithToWindowBasedOnURL(driver, "module=Contacts");
		//save
		newContact.save();

		//verify if contact is created
		ContactInformationPage contactInformation=new ContactInformationPage(driver);
		contactInformation.verify(lname);

		
		//Create Opportunity(using contact)
		homePage.clickOnOpportunities();
		OpportunitiesPage opportunity=new OpportunitiesPage(driver);
		opportunity.clickcreateOpportunitysLookup();
		CreateNewOpportunityPage newOpportunity=new CreateNewOpportunityPage(driver);
		String opp=elib.getDataFromExcelBasedTestId(E_FILEPATH, "OpportunitySystem", "Tc_03", "Opportunity_Name")+random;
		newOpportunity.opportunityDetails(opp);
		WebElement related = newOpportunity.getRelatedToDropdown();
		wlib.select(related, "Contacts");
		newOpportunity.addContact();
        //switch to contact popup window
		wlib.swithToWindowBasedOnURL(driver, "module=Contacts");
		newOpportunity.contactPopup(lname);
		WebElement in1 = newOpportunity.getInDropdown();
		in1.click();
		wlib.select(in1, "lastname");
		newOpportunity.search();
		newOpportunity.selectContact(driver, lname);
		//switch back to create opportunity window
		wlib.swithToWindowBasedOnURL(driver, "module=Potentials");
		//save
		newOpportunity.save();
		

		//verify if opportunity is created
		OpportunityInformationPage opportunityInformation=new OpportunityInformationPage(driver);
		opportunityInformation.verify(opp);

		Reporter.log("Testscript passed",true);

	}

}
