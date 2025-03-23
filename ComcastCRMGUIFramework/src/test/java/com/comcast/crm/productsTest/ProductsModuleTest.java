package com.comcast.crm.productsTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.crm.objectRepositoryUtils.ContactCreatePage;
import com.comcast.crm.objectRepositoryUtils.ContactInfoPage;
import com.comcast.crm.objectRepositoryUtils.HomePage;
import com.comcast.crm.objectRepositoryUtils.ProductsCreatePage;
import com.comcast.crm.objectRepositoryUtils.ProductsInfoPage;
import com.comcast.crm.objectRepositoryUtils.ProductsPage;
import com.comcast.crm.objectRepositoryUtils.PurchaseOrderCreatePage;
import com.comcast.crm.objectRepositoryUtils.PurchaseOrderInfoPage;
import com.comcast.crm.objectRepositoryUtils.PurchaseOrderPage;
import com.comcast.crm.objectRepositoryUtils.VendorCreatePage;
import com.comcast.crm.objectRepositoryUtils.VendorInfoPage;
import com.comcast.crm.objectRepositoryUtils.VendorPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class ProductsModuleTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createProductsTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String ProductsName = elib.getDataFromExcel("products", 1, 1) + jlib.getRandonNumber();

		// Step 2 : navigate to Product module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Product module");
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		// Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Products Icon button");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsIcon().click();

		// Step 4 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		ProductsCreatePage pcp = new ProductsCreatePage(driver);
		pcp.getProductNameTF().sendKeys(ProductsName);
		pcp.getProductSaveBtn().click();

		// Verifying Product Header Info and Product Name
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String ProductHeaderInfo = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyProductHeaderInfo = ProductHeaderInfo.contains(ProductsName);
		Assert.assertEquals(VerifyProductHeaderInfo, true);

		String actualProductsName = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyactualProductsName = actualProductsName.equals(ProductsName);
		Assert.assertEquals(VerifyactualProductsName, true);
	}

	@Test(groups = "regressionTest")
	public void createProductsWithVendorTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String vendorName = elib.getDataFromExcel("vendor", 1, 1) + jlib.getRandonNumber();
		String emailId = jlib.getEmailID();
		String ProductsName = elib.getDataFromExcel("products", 4, 1) + jlib.getRandonNumber();
		String vendorPartNum = elib.getDataFromExcel("products", 4, 2) + jlib.getRandonNumber();

		// Step 2 : navigate to Product module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Vendor module");
		HomePage hp = new HomePage(driver);
		hp.getMoreLink().click();
		hp.getVendorsLink().click();

		// Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Vendor Icon button");
		VendorPage vp = new VendorPage(driver);
		vp.getCreateVendorIcon().click();

		// Step 4 : Enter all the details and Create new Vendor
		UtilityClassObject.getTest().log(Status.INFO, "Create new Vendor");
		VendorCreatePage vcp = new VendorCreatePage(driver);
		vcp.getVendorNameTF().sendKeys(vendorName);
		vcp.getEmailTF().sendKeys(emailId);
		vcp.getSaveBtn().click();

		// Verifying Vendor Header Info and Vendor Name
		VendorInfoPage vip = new VendorInfoPage(driver);
		String VendorHeaderInfo = vip.getVendorHeaderMsg().getText();
		boolean VerifyVendorHeaderInfo = VendorHeaderInfo.contains(vendorName);
		Assert.assertEquals(VerifyVendorHeaderInfo, true);

		String actualVendorName = vip.getVerifyVendorName().getText();
		boolean VerifyactualVendorName = actualVendorName.equals(vendorName);
		Assert.assertEquals(VerifyactualVendorName, true);

		// Step 5 : navigate to Product module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Product module");
		hp.getProductsLink().click();

		// Step 6 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Products Icon button");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsIcon().click();

		// Step 7 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		ProductsCreatePage pcp = new ProductsCreatePage(driver);
		pcp.getProductNameTF().sendKeys(ProductsName);

		String salesStartDate = jlib.getSystemDateYYYYMMDD();
		String salesEndDate = jlib.getRequiredDateYYYYMMDD(7);
		pcp.getSalesEndDateTF().sendKeys(salesStartDate);
		pcp.getSalesEndDateTF().sendKeys(salesEndDate);

		String supportStartDate = jlib.getSystemDateYYYYMMDD();
		String supportExpiryDate = jlib.getRequiredDateYYYYMMDD(15);
		pcp.getSupportStartDateTF().sendKeys(supportStartDate);
		pcp.getSupportExpiryDateTF().sendKeys(supportExpiryDate);
		pcp.getSelectVendorNameIcon().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Vendors&action");

		pcp.getChildWindowSearchTF().sendKeys(vendorName);
		pcp.getChildWindowSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + vendorName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=Products&action");

		pcp.getVendorPartNumTF().sendKeys(vendorPartNum);
		pcp.fileUploadProductImage();
		pcp.getProductSaveBtn().click();

		// Verifying Product Header Info and Product Name
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String ProductHeaderInfo = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyProductHeaderInfo = ProductHeaderInfo.contains(ProductsName);
		Assert.assertEquals(VerifyProductHeaderInfo, true);

		String actualProductsName = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyactualProductsName = actualProductsName.equals(ProductsName);
		Assert.assertEquals(VerifyactualProductsName, true);
	}

	@Test(groups = { "smokeTest", "regressionTest" })
	public void createProductsWithVendorAndContactAndPurchaseOrderTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String contactLastName 	= elib.getDataFromExcel("contact", 1, 1) + jlib.getRandonNumber();
		String vendorName 		= elib.getDataFromExcel("vendor", 1, 1) + jlib.getRandonNumber();
		String emailId 			= jlib.getEmailID();
		String productsName 	= elib.getDataFromExcel("products", 4, 1) + jlib.getRandonNumber();
		String vendorPartNum 	= elib.getDataFromExcel("products", 4, 2) + jlib.getRandonNumber();		
		String subject 			= elib.getDataFromExcel("purchaseOrder", 1, 1) + jlib.getRandonNumber();
		String poStatusDD		= elib.getDataFromExcel("purchaseOrder", 1, 2);
		String billAddress		= elib.getDataFromExcel("purchaseOrder", 1, 3);
		String billPoBox		= elib.getDataFromExcel("purchaseOrder", 1, 4);
		String billCity 		= elib.getDataFromExcel("purchaseOrder", 1, 5);
		String billState 		= elib.getDataFromExcel("purchaseOrder", 1, 6);
		String billPostCode 	= elib.getDataFromExcel("purchaseOrder", 1, 7);
		String billCountry 		= elib.getDataFromExcel("purchaseOrder", 1, 8);
		String qty 				= elib.getDataFromExcel("purchaseOrder", 1, 16);
		String listPrice 		= elib.getDataFromExcel("purchaseOrder", 1, 17);
		
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
		boolean VerifyactualLastName = actualHeader.contains(contactLastName);
		Assert.assertEquals(VerifyactualLastName, true);
		
		String actLastName = cip.getVerifyLastNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, contactLastName);
		soft.assertAll();

		// Step 5 : navigate to Product module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Vendor module");
		hp.getMoreLink().click();
		hp.getVendorsLink().click();

		// Step 6 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Vendor Icon button");
		VendorPage vp = new VendorPage(driver);
		vp.getCreateVendorIcon().click();

		// Step 7 : Enter all the details and Create new Vendor
		UtilityClassObject.getTest().log(Status.INFO, "Create new Vendor");
		VendorCreatePage vcp = new VendorCreatePage(driver);
		vcp.getVendorNameTF().sendKeys(vendorName);
		vcp.getEmailTF().sendKeys(emailId);
		vcp.getSaveBtn().click();

		// Verifying Vendor Header Info and Vendor Name
		VendorInfoPage vip = new VendorInfoPage(driver);
		String VendorHeaderInfo = vip.getVendorHeaderMsg().getText();
		boolean VerifyVendorHeaderInfo = VendorHeaderInfo.contains(vendorName);
		Assert.assertEquals(VerifyVendorHeaderInfo, true);

		String actualVendorName = vip.getVerifyVendorName().getText();
		boolean VerifyactualVendorName = actualVendorName.equals(vendorName);
		Assert.assertEquals(VerifyactualVendorName, true);

		// Step 8 : navigate to Product module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Product module");
		hp.getProductsLink().click();

		// Step 9 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create Products Icon button");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsIcon().click();

		// Step 10 : Enter all the details and Create new Products
		UtilityClassObject.getTest().log(Status.INFO, "Create new Products");
		ProductsCreatePage pcp = new ProductsCreatePage(driver);
		pcp.getProductNameTF().sendKeys(productsName);

		String salesStartDate = jlib.getSystemDateYYYYMMDD();
		String salesEndDate = jlib.getRequiredDateYYYYMMDD(7);
		pcp.getSalesEndDateTF().sendKeys(salesStartDate);
		pcp.getSalesEndDateTF().sendKeys(salesEndDate);

		String supportStartDate = jlib.getSystemDateYYYYMMDD();
		String supportExpiryDate = jlib.getRequiredDateYYYYMMDD(15);
		pcp.getSupportStartDateTF().sendKeys(supportStartDate);
		pcp.getSupportExpiryDateTF().sendKeys(supportExpiryDate);
		pcp.getSelectVendorNameIcon().click();

		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Vendors&action");

		pcp.getChildWindowSearchTF().sendKeys(vendorName);
		pcp.getChildWindowSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + vendorName + "']")).click();

		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=Products&action");

		pcp.getVendorPartNumTF().sendKeys(vendorPartNum);
		pcp.fileUploadProductImage();
		pcp.getProductSaveBtn().click();

		// Verifying Product Header Info and Product Name
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String ProductHeaderInfo = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyProductHeaderInfo = ProductHeaderInfo.contains(productsName);
		Assert.assertEquals(VerifyProductHeaderInfo, true);

		String actualProductsName = pip.getVerifyProductsHeaderMsg().getText();
		boolean VerifyactualProductsName = actualProductsName.equals(productsName);
		Assert.assertEquals(VerifyactualProductsName, true);
		
		// Step 11 : Click on Purchase Order Link
		UtilityClassObject.getTest().log(Status.INFO, "Click on Purchase Order Link");
		hp.getMoreLink().click();
		hp.getPurchaseOrderLink().click();
		
		PurchaseOrderPage pop = new PurchaseOrderPage(driver);
		pop.getCreatePurchaseOrderLink().click();
		
		//Step 9 : Enter a To-Do details and Create P.O
		UtilityClassObject.getTest().log(Status.INFO, "Purchase Order creation");
		PurchaseOrderCreatePage poc = new PurchaseOrderCreatePage(driver);
		poc.getSubjectTF().sendKeys(subject);
		
		poc.getSelectVendorNameIcon().click();
		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Vendors&action");
		poc.getPopUpSearchTF().sendKeys(vendorName);
		poc.getPopUpSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + vendorName + "']")).click();
		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=PurchaseOrder&action");
		
		
		poc.getSelectContactNameIcon().click();		
		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Contacts&action");		
		poc.getPopUpSearchTF().sendKeys(contactLastName);
		poc.getPopUpSearchBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'"+ contactLastName +"')]")).click();
		wlib.switchToAlertAndAccept(driver);
		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=PurchaseOrder&action");
		
		String dueDate = jlib.getRequiredDateYYYYMMDD(25);
		poc.getDueDateTF().clear();
		poc.getDueDateTF().sendKeys(dueDate);
		poc.billingAddressInfo(poStatusDD, billAddress, billPoBox, billCity, billState, billPostCode, billCountry);
		poc.getCopyBillingAddressRB().click();
		
		poc.getSelectProductIcon().click();	
		// switch to child window
		wlib.swtichToTabOnUrl(driver, "module=Products&action");
		poc.getPopUpSearchTF().sendKeys(productsName);
		poc.getPopUpSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + productsName + "']")).click();
		// switch to parent window
		wlib.swtichToTabOnUrl(driver, "module=PurchaseOrder&action");

		poc.getProductQtyTF().sendKeys(qty);
		poc.getProductListPriceTF().sendKeys(listPrice);
		poc.getProductListPriceTF().clear();
		poc.getProductListPriceTF().sendKeys(listPrice);
		poc.getSaveBtn().click();
		
		// Verifying PurchaseOrder Header Info and Subject Text field
		PurchaseOrderInfoPage pup = new PurchaseOrderInfoPage(driver);
		String PurchaseOrderHeaderInfo = pup.getVerifyPurchaseOrderHeaderMsg().getText();
		boolean VerifyPurchaseOrderHeaderInfo = PurchaseOrderHeaderInfo.contains(subject);
		Assert.assertEquals(VerifyPurchaseOrderHeaderInfo, true);

		String actualSubjectTF = pup.getVerifySubjectTF().getText();
		boolean VerifyActualSubjectTF = actualSubjectTF.equals(subject);
		Assert.assertEquals(VerifyActualSubjectTF, true);
		
		String actualStatus = pup.getVerifyStatusTF().getText();
		boolean VerifyActualStatusInfo = actualStatus.contains(poStatusDD);
		Assert.assertEquals(VerifyActualStatusInfo, true);
		
		String actualVendorNameTF = pup.getVerifyVendorNameTF().getText();
		String actualContactNameTF  = pup.getVerifyContactNameTF().getText().trim();
		soft.assertEquals(actualVendorNameTF, vendorName);
		soft.assertEquals(actualContactNameTF, contactLastName);		
		soft.assertAll();

	}
}
