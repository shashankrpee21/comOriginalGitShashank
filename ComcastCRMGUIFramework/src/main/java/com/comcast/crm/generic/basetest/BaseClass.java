package com.comcast.crm.generic.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;
import com.comcast.crm.generic.webDriverUtility.WebDriverUtility;
import com.comcast.objectRepositoryUtility.HomePage;
import com.comcast.objectRepositoryUtility.LoginPage;

public class BaseClass {

	/* Create Object */
	public DataBaseUtility dblib = new DataBaseUtility();
	public ExcelUtility elib = new ExcelUtility();
	public FileUtility flib = new FileUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();

	public WebDriver driver = null;
	public static WebDriver staticDriver = null;
	
	
	@BeforeSuite(alwaysRun = true)
	public void configBS() throws SQLException {
		Reporter.log("=====Connect to DB and Report Config=====", true);
		dblib.getDBconnection();
	}

//	@Parameters("Browser")
//	@BeforeClass(groups = {"smokeTest","regressionTest"})
//	public void configBC(String browser) throws Throwable {
//	String Browser = browser;

	@BeforeClass(alwaysRun = true)
	public void configBC() throws Throwable {
	
		Reporter.log("=====Launch Browse=====", true);
		String Browser = System.getProperty("browser", flib.getDataFromPropertiesFile("browser"));

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			Reporter.log("Browser not recognised --- Shashank");
		}
		staticDriver = driver;
		UtilityClassObject.setDriver(driver);
		
	}

	
	@BeforeMethod(alwaysRun = true)
	public void configBM() throws Throwable {
		Reporter.log("=====Login to Appl=====", true);
		String Url = System.getProperty("url", flib.getDataFromPropertiesFile("url"));
		String Username = System.getProperty("username", flib.getDataFromPropertiesFile("username"));
		String Password = System.getProperty("password", flib.getDataFromPropertiesFile("password"));
		
		LoginPage lp = new LoginPage(driver);
		wlib.implicitlyWait(driver);
		driver.get(Url);
		wlib.maximizeWindow(driver);
		lp.loginToApp(Username, Password);
		lp.getLogin().click();
	}

	
	@AfterMethod(alwaysRun = true)
	public void ConfigAM() {
		Reporter.log("=====Logout to Appl=====", true);
		HomePage hp = new HomePage(driver);
		hp.getAdminBtn().click();
		hp.getAdminLogoutBtn().click();
	}

	
	@AfterClass(alwaysRun = true)
	public void ConfigAC() {
		Reporter.log("=====Close Browser=====", true);
		driver.quit();
	}

	
	@AfterSuite(alwaysRun = true)
	public void ConfigAs() throws SQLException {
		Reporter.log("=====Close DB, Report BackUp=====", true);
		dblib.closeDBConnection();
	}
}