package com.comcast.crm.orgTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;
import com.comcast.objectRepositoryUtility.OrganizationCreateNewPage;
import com.comcast.objectRepositoryUtility.HomePage;
import com.comcast.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.objectRepositoryUtility.OrganizationPage;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass{

	@Test //(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandonNumber();
		String descriptionInfo = elib.getDataFromExcel("org", 1, 6);

		//Step 2 : navigate to Organization module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		
		//Step 3 : click on "Create Organization Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Icon button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();
		
		// Step 4 : Enter all the details and Create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization");
		OrganizationCreateNewPage cop = new OrganizationCreateNewPage(driver);
		cop.createOrg(orgName, descriptionInfo);
		
		// verify Header msg & orgname info Excepted Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeader = oip.getHeaderMsg().getText();
		boolean headerInfo = actualHeader.contains(orgName);
		Assert.assertEquals(headerInfo, true); //true - will look for boolean value
		
		String actualOrgName= oip.getVerifyOrgNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualOrgName, orgName);
		soft.assertAll();
	}
	
	@Test //(groups = "regressionTest")
	public void createOrganizationWithIndustryTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		// read testScript data from Excel file
		String orgName = elib.getDataFromExcel("org", 4, 2)+jlib.getRandonNumber();
		String descriptionInfo = elib.getDataFromExcel("org", 4, 6);
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		//Step 2 : navigate to Organization module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		
		//Step 3 : click on "Create Organization Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Icon button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();
		
		// Step 4 : Enter all the details and Create new Organization with Industry
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization with Industry");
		OrganizationCreateNewPage cop = new OrganizationCreateNewPage(driver);
		cop.createOrgWithIndustry(orgName, industry, type, descriptionInfo);
		

		// verify Header msg & orgname info Excepted Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeader = oip.getHeaderMsg().getText();
		boolean headerInfo = actualHeader.contains(orgName);
		Assert.assertEquals(headerInfo, true);
		
		String actualOrgName= oip.getVerifyOrgNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualOrgName, orgName);

		// verify industries & type info Excepted Result
		String actIndustries = oip.getIndutryMsg().getText();
		boolean verifyIndustry = actIndustries.contains(industry);
		Assert.assertEquals(verifyIndustry, true);
		
		String actType = oip.getTypeMsg().getText();
		soft.assertEquals(actType, type);
		soft.assertAll();
	}
	
	@Test //(groups = {"smokeTest","regressionTest"})
	public void createOrganizationWithPhoneNoTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		// read testScript data from Excel file
		String orgName = elib.getDataFromExcel("org", 7, 2)+jlib.getRandonNumber();
		String industry = elib.getDataFromExcel("org", 7, 3);
		String type = elib.getDataFromExcel("org", 7, 4);
		String descriptionInfo = elib.getDataFromExcel("org", 7, 6);
		String phoneNo = elib.getDataFromExcel("org", 7, 5);

		//Step 2 : navigate to Organization module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		
		//Step 3 : click on "Create Organization Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Icon button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();

		// Step 4 : Enter all the details and Create new Organization with Industry and Phone No
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization with Industry and Phone No");
		OrganizationCreateNewPage cop = new OrganizationCreateNewPage(driver);
		cop.createOrgWithIndustryAndPhone(orgName, industry, type, descriptionInfo, phoneNo);
		
		// verify Header msg & orgname info Excepted Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeader = oip.getHeaderMsg().getText();
		boolean headerInfo = actualHeader.contains(orgName);
		Assert.assertEquals(headerInfo, true);
		
		String actualOrgName= oip.getVerifyOrgNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualOrgName, orgName);

		// verify industries & type info Excepted Result
		String actIndustries = oip.getIndutryMsg().getText();
		boolean verifyIndustry = actIndustries.contains(industry);//industry
		Assert.assertEquals(verifyIndustry, true);
		
		String actType = oip.getTypeMsg().getText();
		soft.assertEquals(actType, type);//type

		// verify phoneNo info Excepted Result
		String actualPhoneNo = oip.getPhoneMsg().getText();
		boolean verifyPhoneNo = actualPhoneNo.contains(phoneNo);//
		Assert.assertEquals(verifyPhoneNo, true);
		soft.assertAll();
	}
}