package com.comcast.crm.contactTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.objectRepositoryUtils.CalenderCreatePage;
import com.comcast.objectRepositoryUtils.CalenderPage;
import com.comcast.objectRepositoryUtils.ContactCreatePage;
import com.comcast.objectRepositoryUtils.ContactInfoPage;
import com.comcast.objectRepositoryUtils.HomePage;
import com.comcast.objectRepositoryUtils.OrganizationCreateNewPage;
import com.comcast.objectRepositoryUtils.OrganizationInfoPage;
import com.comcast.objectRepositoryUtils.OrganizationPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class ContactModuleTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String contactLastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandonNumber();

		//Step 2 : navigate to contact module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Contact module");
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		//Step 3 : click on "Create Contact Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact Icon button");
		ContactCreatePage ccp = new ContactCreatePage(driver);
		ccp.getCreateContactIcon().click();

		// Step 4 : Enter all the details and Create new Contact
		UtilityClassObject.getTest().log(Status.INFO, "Create new Contact");
		ContactInfoPage cip = new ContactInfoPage(driver);
		cip.fileUpload();
		cip.lastNameSavebt(contactLastName);
			
		// verifying Header Name & Last Name Excepted Result
		String actualHeader = cip.getVerifyHeaderMsg().getText();
		boolean status = actualHeader.contains(contactLastName);
		Assert.assertEquals(status, true);
		
		String actLastName = cip.getVerifyLastNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, contactLastName);
		soft.assertAll();
	}
	
	
	@Test(groups = {"smokeTest","regressionTest"})
	public void createContactWithOrgTest() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		
		String orgName 			= elib.getDataFromExcel("contact", 7, 2) + jlib.getRandonNumber();
		String industry 		= elib.getDataFromExcel("org", 7, 3);
		String type 			= elib.getDataFromExcel("org", 7, 4);
		String descriptionInfo 	= elib.getDataFromExcel("org", 7, 6);
		String phoneNo 			= elib.getDataFromExcel("org", 7, 5);
		String contactLastName 	= elib.getDataFromExcel("contact", 7, 3) + jlib.getRandonNumber();
		String emailID  		= jlib.getEmailID();

		//Step 2 : navigate to Organization module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		//Step 3 : click on "Create Organization Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Icon button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();

		// Step 4 : Enter all the details and Create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization with Indutry and Phone no");
		OrganizationCreateNewPage cop = new OrganizationCreateNewPage(driver);
		cop.createOrgWithIndustryAndPhone(orgName, industry, type, descriptionInfo, phoneNo);

		// verify phoneNo info Excepted Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNo = oip.getPhoneMsg().getText();
		boolean status = actPhoneNo.equals(phoneNo);
		Assert.assertEquals(status, true);

		//Step 5 : navigate to contact module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Contact module");
		hp.getContactsLink().click();

		//Step 6 : click on "Create Contact Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact Icon button");
		ContactCreatePage ccp = new ContactCreatePage(driver);
		ccp.getCreateContactIcon().click();

		// Step 7 : Enter all the details and Create new Contact with Support Date
		UtilityClassObject.getTest().log(Status.INFO, "Create new Contact with Support Date");
		ContactInfoPage cip = new ContactInfoPage(driver);
		cip.createContactDetails(contactLastName, emailID);		
		cip.getAddOrgNameBtn().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Accounts&action");

		cip.getsearchTF().sendKeys(orgName);
		cip.getsearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "Contacts&action");
		
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		cip.getSupportStartDate().clear();
		cip.getSupportStartDate().sendKeys(startDate);
		cip.getSupportEndDate().clear();
		cip.getSupportEndDate().sendKeys(endDate);
		cip.fileUpload();
		cip.getContactSavebtn().click();
		
		// verifying Header Name & Last Name Excepted Result
		String actualHeader = cip.getVerifyHeaderMsg().getText();
		boolean status2 = actualHeader.contains(actualHeader);
		Assert.assertEquals(status2, true);
		
		String actLastName = cip.getVerifyLastNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, contactLastName);

		// verify Support Start & End Date Excepted Result
		String actStartDate = cip.getVerifySupportStartDate().getText();
		boolean ActualStartDate = actStartDate.contains(startDate);
		Assert.assertEquals(ActualStartDate, true);

		String actEndDate = cip.getVerifySupportEndDate().getText();
		boolean ActualEndDate = actEndDate.contains(endDate);
		Assert.assertEquals(ActualEndDate, true);
		soft.assertAll();
	}
	
	
	@Test(groups = {"smokeTest","regressionTest"})
	public void createContactWithOrgAndToDoListTest() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		
		String orgName 			= elib.getDataFromExcel("contact", 7, 2) + jlib.getRandonNumber();
		String industry 		= elib.getDataFromExcel("org", 7, 3);
		String type 			= elib.getDataFromExcel("org", 7, 4);
		String descriptionInfo 	= elib.getDataFromExcel("org", 7, 6);
		String phoneNo 			= elib.getDataFromExcel("org", 7, 5);
		String contactLastName 	= elib.getDataFromExcel("contact", 7, 3) + jlib.getRandonNumber();
		String emailID  		= jlib.getEmailID();
		String toDoSubjectTF	= elib.getDataFromExcel("calenderToDo", 1, 1);
		String descriptionTF	= elib.getDataFromExcel("calenderToDo", 1, 2);
		String taskStatusDD		= elib.getDataFromExcel("calenderToDo", 1, 3);
		String eventStartHrDD 	= elib.getDataFromExcel("calenderToDo", 1, 4);
		String eventStartMinDD 	= elib.getDataFromExcel("calenderToDo", 1, 5);
		String eventStartFmtDD 	= elib.getDataFromExcel("calenderToDo", 1, 6);

		//Step 2 : navigate to Organization module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		//Step 3 : click on "Create Organization Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Icon button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();

		// Step 4 : Enter all the details and Create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create new Organization with Indutry and Phone no");
		OrganizationCreateNewPage cop = new OrganizationCreateNewPage(driver);
		cop.createOrgWithIndustryAndPhone(orgName, industry, type, descriptionInfo, phoneNo);

		// verify phoneNo info Excepted Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNo = oip.getPhoneMsg().getText();
		boolean status = actPhoneNo.equals(phoneNo);
		Assert.assertEquals(status, true);

		//Step 5 : navigate to contact module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Contact module");
		hp.getContactsLink().click();

		//Step 6 : click on "Create Contact Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact Icon button");
		ContactCreatePage ccp = new ContactCreatePage(driver);
		ccp.getCreateContactIcon().click();

		// Step 7 : Enter all the details and Create new Contact with Support Date
		UtilityClassObject.getTest().log(Status.INFO, "Create new Contact with Support Date");
		ContactInfoPage cip = new ContactInfoPage(driver);
		cip.createContactDetails(contactLastName, emailID);		
		cip.getAddOrgNameBtn().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Accounts&action");

		cip.getsearchTF().sendKeys(orgName);
		cip.getsearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "Contacts&action");
		
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		cip.getSupportStartDate().clear();
		cip.getSupportStartDate().sendKeys(startDate);
		cip.getSupportEndDate().clear();
		cip.getSupportEndDate().sendKeys(endDate);
		cip.fileUpload();
		cip.getContactSavebtn().click();
		
		// verifying Header Name & Last Name Excepted Result
		String actualHeader = cip.getVerifyHeaderMsg().getText();
		boolean status2 = actualHeader.contains(actualHeader);
		Assert.assertEquals(status2, true);
		
		String actLastName = cip.getVerifyLastNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, contactLastName);

		// verify Support Start & End Date Excepted Result
		String actStartDate = cip.getVerifySupportStartDate().getText();
		boolean ActualStartDate = actStartDate.contains(startDate);
		Assert.assertEquals(ActualStartDate, true);

		String actEndDate = cip.getVerifySupportEndDate().getText();
		boolean ActualEndDate = actEndDate.contains(endDate);
		Assert.assertEquals(ActualEndDate, true);
		soft.assertAll();
		
		//Step 8 : click on To-Do link
		UtilityClassObject.getTest().log(Status.INFO, "Click ToDo Link");
		CalenderPage cap = new CalenderPage(driver);
		cap.getAddToDoLink().click();
		
		//Step 9 : Enter a To-Do details
		UtilityClassObject.getTest().log(Status.INFO, "Adding Contact to ToDoList");
		CalenderCreatePage cpp = new CalenderCreatePage(driver);
		cpp.creatingToDoInfo(toDoSubjectTF, descriptionTF, taskStatusDD);
		cpp.startEvent(eventStartHrDD, eventStartMinDD, eventStartFmtDD);
		cpp.getToDoStartDateTF().clear();
		String toDoStartDate = jlib.getSystemDateYYYYMMDD();
		cpp.getToDoStartDateTF().sendKeys(toDoStartDate);	
		Thread.sleep(5000);
		cpp.getToDoDueDateTF().clear();
		String toDoEndDate = jlib.getRequiredDateYYYYMMDD(7);
		cpp.getToDoDueDateTF().sendKeys(toDoEndDate);
		Thread.sleep(5000);
		cpp.getSendNotificationCB().click();
		cpp.getSaveBtn().click();
	}
}