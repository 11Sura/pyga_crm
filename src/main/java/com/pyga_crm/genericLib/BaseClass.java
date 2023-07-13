package com.pyga_crm.genericLib;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.pyga_crm.ObjectRepository.HomePage;
import com.pyga_crm.ObjectRepository.LoginPage;


public class BaseClass {
	
	public CommondataFileLib clib = new CommondataFileLib();
	public WebActionsLib wlib = new WebActionsLib();
	public DatabaseLib dlib=new DatabaseLib();
	public JavaLib jlib=new JavaLib();
	public ExcelLib elib=new ExcelLib();
	
	public static WebDriver sDriver;  //for ListenersImplementation
	
	public WebDriver driver=null;
	public String FILEPATH;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void connectToDatabase() throws SQLException {
		dlib.connectDB();
		System.out.println("Connected to database");
	}
	//@Parameters("BROWSER")               //use during parallel(cross browser) execution and pass =>String BROWSER<= as argument in launchBrowser method
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void launchBrowser() throws Throwable {
		FILEPATH = clib.getFilePathFromPropertiesFile("commondataConfigFilePath");
		String BROWSER = clib.getDataFromProperties(FILEPATH, "browser");
//String BROWSER = System.getProperty("browser");      //for command line instuctions

		
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else  if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} 
		else if(BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		
		sDriver=driver;  //for ListenersImplementation
		
		wlib.maximizeWindow(driver);
		wlib.waitForElementInDOM(driver);
		
		System.out.println("Browser successfully launched");
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void loginToApplication() throws Throwable {
		
		Thread.sleep(7000);
		
		String URL = clib.getDataFromProperties(FILEPATH, "url");
String USERNAME = clib.getDataFromProperties(FILEPATH, "username");
String PASSWORD = clib.getDataFromProperties(FILEPATH, "password");
		
//String USERNAME=System.getProperty("username");    //this is to provide parameters during runtime in maven COMMAND LINE execution
//String PASSWORD=System.getProperty("password");    //like: mvn test -Dtest=CampaignSystemTest -Dusername=admin -Dpassword=admin 

		driver.get(URL);
		LoginPage loginPage= new LoginPage(driver);
		loginPage.login(USERNAME, PASSWORD);
		
		System.out.println("Successfully logged into the application");
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void logoutFromApplication() {
		HomePage homePage=new HomePage(driver);
		homePage.signOut(wlib, driver);
		
		System.out.println("Logout successful");
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser closed successfully");
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void closeDatabase() throws SQLException {
		dlib.closeDB();
		System.out.println("Database connection is closed");
	}

}
