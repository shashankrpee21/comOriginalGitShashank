package com.comcast.crm.leadsTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.objectRepositoryUtils.HomePage;
import com.comcast.objectRepositoryUtils.LeadsCreatePage;
import com.comcast.objectRepositoryUtils.LeadsInfoPage;
import com.comcast.objectRepositoryUtils.LeadsPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class LeadsModuleTest extends BaseClass{

	@Test (groups = "smokeTest")
	public void createLeadTest() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String firstNameTitleDD = elib.getDataFromExcel("leads", 1, 1);
		String firstName 		= elib.getDataFromExcel("leads", 1, 2)+jlib.getRandonNumber();
		String lastName 		= elib.getDataFromExcel("leads", 1, 3)+jlib.getRandonNumber();
		String companyName 		= elib.getDataFromExcel("leads", 1, 4)+jlib.getRandonNumber();

		//Step 2 : navigate to Leads module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Leads module");
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		
		//Step 3 : click on "Create Lead Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Lead Icon button");
		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadsIcon().click();
		
		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		LeadsCreatePage lcp = new LeadsCreatePage(driver);
		lcp.createLead(firstNameTitleDD, firstName, lastName, companyName);
				
		//Verifying Leads Header Info and Lead Name
		LeadsInfoPage lip = new LeadsInfoPage(driver);
		String actualHeaderLastName = lip.getLeadsHeaderMsg().getText();
		boolean headerInfoLastName = actualHeaderLastName.contains(lastName);
		Assert.assertEquals(headerInfoLastName, true); //true - will look for boolean value
		
		String actualLastName = lip.getVerifyLastNameTF().getText();
		boolean lastNameInfo = actualLastName.contains(lastName);
		Assert.assertEquals(lastNameInfo, true);
		
		String actualCompanyName = lip.getVerifyCompanyTF().getText();
		boolean companyNameInfo = actualCompanyName.contains(companyName);
		Assert.assertEquals(companyNameInfo, true);
	}

	
	@Test (groups = {"smokeTest","regressionTest"})
	public void createLeadwithOtherDetailsTest() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String firstNameTitleDD = elib.getDataFromExcel("leads", 4, 1);
		String firstName 		= elib.getDataFromExcel("leads", 4, 2)+jlib.getRandonNumber();
		String lastName 		= elib.getDataFromExcel("leads", 4, 3)+jlib.getRandonNumber();
		String companyName 		= elib.getDataFromExcel("leads", 4, 4)+jlib.getRandonNumber();
		String leadSourceDD 	= elib.getDataFromExcel("leads", 4, 5);
		String companyDD 		= elib.getDataFromExcel("leads", 4, 6);
		String mobile			= elib.getDataFromExcel("leads", 4, 7);
//		String emailId 			= elib.getDataFromExcel("leads", 4, 8);
		String emailId 			= jlib.getEmailID();
		String description 		= elib.getDataFromExcel("leads", 4, 9);


		//Step 2 : navigate to Leads module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Leads module");
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		
		//Step 3 : click on "Create Lead Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Lead Icon button");
		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadsIcon().click();
		
		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		LeadsCreatePage lcp = new LeadsCreatePage(driver);
		lcp.createLeadWithDetails(firstNameTitleDD, firstName, lastName, companyName, leadSourceDD, companyDD, mobile, emailId, description);
		
		
		//Verifying Leads Header Info and Lead Last Name, Company Name
		LeadsInfoPage lip = new LeadsInfoPage(driver);
		String actualHeaderLastName = lip.getLeadsHeaderMsg().getText();
		boolean headerInfoLastName = actualHeaderLastName.contains(lastName);
		Assert.assertEquals(headerInfoLastName, true); //true - will look for boolean value
		
		String actualLastName = lip.getVerifyLastNameTF().getText();
		boolean lastNameInfo = actualLastName.contains(lastName);
		Assert.assertEquals(lastNameInfo, true);
		
		String actualCompanyName = lip.getVerifyCompanyTF().getText();
		boolean companyNameInfo = actualCompanyName.contains(companyName);
		Assert.assertEquals(companyNameInfo, true);
		
		//Verifying Lead mobile and Lead emailID
		String actualMobileTF= lip.getVerifyMobileTF().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualMobileTF, mobile);
		
		String actualEmailTF= lip.getVerifyEmailTF().getText();
		soft.assertEquals(actualEmailTF, emailId);
		
		soft.assertAll();
	}
	
	@Test (groups = {"smokeTest","regressionTest"})
	public void createLeadConverToOpportunitiesTest() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String firstNameTitleDD = elib.getDataFromExcel("leads", 7, 1);
		String firstName 		= elib.getDataFromExcel("leads", 7, 2)+jlib.getRandonNumber();
		String lastName 		= elib.getDataFromExcel("leads", 7, 3)+jlib.getRandonNumber();
		String companyName 		= elib.getDataFromExcel("leads", 7, 4)+jlib.getRandonNumber();
		String leadSourceDD 	= elib.getDataFromExcel("leads", 7, 5);
		String companyDD 		= elib.getDataFromExcel("leads", 7, 6);
		String mobile			= elib.getDataFromExcel("leads", 7, 7);
//		String emailId 			= elib.getDataFromExcel("leads", 7, 8);
		String emailId 			= jlib.getEmailID();
		String description 		= elib.getDataFromExcel("leads", 7, 9);
		String expectCloseDate  = jlib.getRequiredDateYYYYMMDD(15);
		String salesStage 		= elib.getDataFromExcel("leads", 7, 10);
		String addComment		= elib.getDataFromExcel("leads", 7, 11);

		//Step 2 : navigate to Leads module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Leads module");
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		
		//Step 3 : click on "Create Lead Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Lead Icon button");
		LeadsPage lp = new LeadsPage(driver);
		lp.getCreateLeadsIcon().click();
		
		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		LeadsCreatePage lcp = new LeadsCreatePage(driver);
		lcp.createLeadWithDetails(firstNameTitleDD, firstName, lastName, companyName, leadSourceDD, companyDD, mobile, emailId, description);
		
		
		//Verifying Leads Header Info and Lead Last Name, Company Name
		LeadsInfoPage lip = new LeadsInfoPage(driver);
		String actualHeaderLastName = lip.getLeadsHeaderMsg().getText();
		boolean headerInfoLastName = actualHeaderLastName.contains(lastName);
		Assert.assertEquals(headerInfoLastName, true); //true - will look for boolean value
		
		String actualLastName = lip.getVerifyLastNameTF().getText();
		boolean lastNameInfo = actualLastName.contains(lastName);
		Assert.assertEquals(lastNameInfo, true);
		
		String actualCompanyName = lip.getVerifyCompanyTF().getText();
		boolean companyNameInfo = actualCompanyName.contains(companyName);
		Assert.assertEquals(companyNameInfo, true);
		
		//Verifying Lead mobile and Lead emailID
		String actualMobileTF= lip.getVerifyMobileTF().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualMobileTF, mobile);
		
		String actualEmailTF= lip.getVerifyEmailTF().getText();
		soft.assertEquals(actualEmailTF, emailId);
		soft.assertAll();
		
		UtilityClassObject.getTest().log(Status.INFO, "Convert leads to opportunities");
		lip.covertLeadToOpportunity(expectCloseDate, salesStage);
		lip.addComment(addComment);
		Thread.sleep(1000);
	}
}