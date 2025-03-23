package com.comcast.crm.campaignTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.crm.objectRepositoryUtils.CalenderCreatePage;
import com.comcast.crm.objectRepositoryUtils.CalenderPage;
import com.comcast.crm.objectRepositoryUtils.CampaignsCreatePage;
import com.comcast.crm.objectRepositoryUtils.CampaignsInfoPage;
import com.comcast.crm.objectRepositoryUtils.CampaignsPage;
import com.comcast.crm.objectRepositoryUtils.HomePage;
import com.comcast.crm.objectRepositoryUtils.ProductsCreatePage;
import com.comcast.crm.objectRepositoryUtils.ProductsInfoPage;
import com.comcast.crm.objectRepositoryUtils.ProductsPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class CampaignModuleTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createCampaignTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String CampaignName = elib.getDataFromExcel("campaign", 1, 1) + jlib.getRandonNumber();

		//Step 2 : navigate to Campaigns module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Campaigns module");
		HomePage hp = new HomePage(driver);
		hp.getMoreLink().click();
		hp.getCampaignsLink().click();

		//Step 3 : click on "Create Campaigns Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Campaigns Icon button");
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignsIcon().click();

		// Step 4 : Enter all the details and Create new Campaigns
		UtilityClassObject.getTest().log(Status.INFO, "Create new Campaigns");
		CampaignsCreatePage ccp = new CampaignsCreatePage(driver);
		ccp.getCampaignNameTF().sendKeys(CampaignName);
		ccp.getCampaignSaveBtn().click();

		//Verifying Campaigns Header Info and Campaigns Name
		CampaignsInfoPage cip = new CampaignsInfoPage(driver);		
		String actualCampaignHeaderInfo = cip.getCampaignHeaderMsg().getText();
		boolean VerifyCampaignHeaderInfo = actualCampaignHeaderInfo.contains(CampaignName);
		Assert.assertEquals(VerifyCampaignHeaderInfo, true);
		
		String actualCampaignName= cip.getVerifyCampaignNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualCampaignName, CampaignName);
		soft.assertAll();
	}

	@Test(groups = "regressionTest")
	public void createCampaignWithProductTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String CampaignName = elib.getDataFromExcel("campaign", 1, 1) + jlib.getRandonNumber();
		String ProductsName = elib.getDataFromExcel("products", 1, 1) + jlib.getRandonNumber();

		//Step 2 : navigate to Product module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Product module");
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		//Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Products Icon button");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsIcon().click();

		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		ProductsCreatePage pcp = new ProductsCreatePage(driver);
		pcp.getProductNameTF().sendKeys(ProductsName);
		pcp.getProductSaveBtn().click();

		//Verifying Product Header Info and Product Name
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String ProductHeaderInfo = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyactualProductsName = ProductHeaderInfo.contains(ProductsName);
		Assert.assertEquals(VerifyactualProductsName, true);

		//Step 5 : navigate to Campaigns module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Campaigns module");
		hp.getMoreLink().click();
		hp.getCampaignsLink().click();

		//Step 6 : click on "Create Campaigns Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Campaigns Icon button");
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignsIcon().click();
		
		// Step 7 : Enter all the details and Create new Campaigns
		UtilityClassObject.getTest().log(Status.INFO, "Create new Campaigns");
		CampaignsCreatePage ccp = new CampaignsCreatePage(driver);
		ccp.getCampaignNameTF().sendKeys(CampaignName);
		
		// Step 8 : In Campaigns and add a Product
		UtilityClassObject.getTest().log(Status.INFO, "In Campaigns and add a Product");
		CampaignsInfoPage cip = new CampaignsInfoPage(driver);
		cip.getAddProductBtn().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Products&action");

		cip.getsearchTF().sendKeys(ProductsName);
		cip.getsearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + ProductsName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=Campaigns&action");
		ccp.getCampaignSaveBtn().click();
		
		//Verifying Campaigns Header Info and Campaigns Name
		String actualCampaignHeaderInfo = cip.getCampaignHeaderMsg().getText();
		boolean VerifyCampaignHeaderInfo = actualCampaignHeaderInfo.contains(CampaignName);
		Assert.assertEquals(VerifyCampaignHeaderInfo, true);
		
		String actualCampaignName= cip.getVerifyCampaignNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualCampaignName, CampaignName);
		soft.assertAll();
	}
	
	@Test(groups = {"smokeTest", "regressionTest"})
	public void createProductWithCampaignAndEventTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String CampaignName = elib.getDataFromExcel("campaign", 1, 1) + jlib.getRandonNumber();
		String ProductsName = elib.getDataFromExcel("products", 1, 1) + jlib.getRandonNumber();
		String eventTypeDD 			= elib.getDataFromExcel("calenderEvent", 1, 1);
		String eventName 			= elib.getDataFromExcel("calenderEvent", 1, 2);
		String descriptionTA 		= elib.getDataFromExcel("calenderEvent", 1, 3);
		String locationTF 			= elib.getDataFromExcel("calenderEvent", 1, 4);
		String eventStartHrDD 		= elib.getDataFromExcel("calenderEvent", 1, 5);
		String eventStartMinDD 		= elib.getDataFromExcel("calenderEvent", 1, 6);
		String eventStartFmtDD 		= elib.getDataFromExcel("calenderEvent", 1, 7);
		String eventEndHrDD 		= elib.getDataFromExcel("calenderEvent", 1, 8);
		String eventEndMinDD 		= elib.getDataFromExcel("calenderEvent", 1, 9);
		String eventEndFmtDD 		= elib.getDataFromExcel("calenderEvent", 1, 10);
		
		String selectAvailableUsersDD1 		= elib.getDataFromExcel("calenderEvent", 1, 11);
		String selectAvailableUsersDD2 		= elib.getDataFromExcel("calenderEvent", 1, 12);
		String selectAvailableUsersDD3 		= elib.getDataFromExcel("calenderEvent", 1, 13);

		//Step 2 : navigate to Product module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Product module");
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		//Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Products Icon button");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsIcon().click();

		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		ProductsCreatePage pcp = new ProductsCreatePage(driver);
		pcp.getProductNameTF().sendKeys(ProductsName);
		pcp.getProductSaveBtn().click();

		//Verifying Product Header Info and Product Name
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String ProductHeaderInfo = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyactualProductsName = ProductHeaderInfo.contains(ProductsName);
		Assert.assertEquals(VerifyactualProductsName, true);

		//Step 5 : navigate to Campaigns module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Campaigns module");
		hp.getMoreLink().click();
		hp.getCampaignsLink().click();

		//Step 6 : click on "Create Campaigns Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Campaigns Icon button");
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignsIcon().click();
		
		// Step 7 : Enter all the details and Create new Campaigns
		UtilityClassObject.getTest().log(Status.INFO, "Create new Campaigns");
		CampaignsCreatePage ccp = new CampaignsCreatePage(driver);
		ccp.getCampaignNameTF().sendKeys(CampaignName);
		
		// Step 8 : In Campaigns and add a Product
		UtilityClassObject.getTest().log(Status.INFO, "In Campaigns and add a Product");
		CampaignsInfoPage cip = new CampaignsInfoPage(driver);
		cip.getAddProductBtn().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Products&action");

		cip.getsearchTF().sendKeys(ProductsName);
		cip.getsearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + ProductsName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=Campaigns&action");
		ccp.getCampaignSaveBtn().click();
		
		//Verifying Campaigns Header Info and Campaigns Name
		String actualCampaignHeaderInfo = cip.getCampaignHeaderMsg().getText();
		boolean VerifyCampaignHeaderInfo = actualCampaignHeaderInfo.contains(CampaignName);
		Assert.assertEquals(VerifyCampaignHeaderInfo, true);
		
		String actualCampaignName= cip.getVerifyCampaignNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualCampaignName, CampaignName);
		soft.assertAll();
		
		
		UtilityClassObject.getTest().log(Status.INFO, "Adding Event");
		CalenderPage cap = new CalenderPage(driver);
		cap.getAddEventLink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Converting from Campaign to Event");
		CalenderCreatePage cpp = new CalenderCreatePage(driver);
		
		cpp.creatingEventInfo(eventTypeDD, eventName, descriptionTA, locationTF);
		cpp.startEvent(eventStartHrDD, eventStartMinDD, eventStartFmtDD);		
		cpp.getEventStartDateTF().clear();
		String startDate = jlib.getSystemDateYYYYMMDD();
		cpp.getEventStartDateTF().sendKeys(startDate);
		cpp.endEvent(eventEndHrDD, eventEndMinDD, eventEndFmtDD);
		cpp.getEventEndDateTF().clear();
		String endDate = jlib.getRequiredDateYYYYMMDD(15);
		cpp.getEventEndDateTF().sendKeys(endDate);
		
		//cpp.availableUsers(selectAvailableUsersDD1, selectAvailableUsersDD2, selectAvailableUsersDD3);
		cpp.getAddBtn().click();
		cpp.getSaveBtn().click();
		
		
	}
}
