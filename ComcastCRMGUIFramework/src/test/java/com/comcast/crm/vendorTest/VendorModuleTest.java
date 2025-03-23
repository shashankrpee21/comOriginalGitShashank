package com.comcast.crm.vendorTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.objectRepositoryUtils.HomePage;
import com.comcast.objectRepositoryUtils.VendorCreatePage;
import com.comcast.objectRepositoryUtils.VendorInfoPage;
import com.comcast.objectRepositoryUtils.VendorPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class VendorModuleTest extends BaseClass{

	@Test(groups = "smokeTest")
	public void createVendorTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String vendorName 	= elib.getDataFromExcel("vendor", 1, 1) + jlib.getRandonNumber();
		String emailId 		= jlib.getEmailID();

		//Step 2 : navigate to Product module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Vendor module");
		HomePage hp = new HomePage(driver);
		hp.getMoreLink().click();
		hp.getVendorsLink().click();
		
		//Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Vendor Icon button");
		VendorPage vp = new VendorPage(driver);
		vp.getCreateVendorIcon().click();
		
		// Step 4 : Enter all the details and Create new Vendor
		UtilityClassObject.getTest().log(Status.INFO, "Create new Vendor");
		VendorCreatePage vcp = new VendorCreatePage(driver);
		vcp.getVendorNameTF().sendKeys(vendorName);
		vcp.getEmailTF().sendKeys(emailId);
		vcp.getSaveBtn().click();

		//Verifying Vendor Header Info and Vendor Name
		VendorInfoPage vip = new VendorInfoPage(driver);
		String VendorHeaderInfo = vip.getVendorHeaderMsg().getText();		
		boolean VerifyVendorHeaderInfo = VendorHeaderInfo.contains(vendorName);
		Assert.assertEquals(VerifyVendorHeaderInfo, true);

		String actualVendorName = vip.getVerifyVendorName().getText();
		boolean VerifyactualVendorName = actualVendorName.equals(vendorName);
		Assert.assertEquals(VerifyactualVendorName, true);
	}
}
