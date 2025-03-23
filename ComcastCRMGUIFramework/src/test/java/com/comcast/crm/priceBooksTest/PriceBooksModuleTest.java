package com.comcast.crm.priceBooksTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import com.comcast.objectRepositoryUtils.HomePage;
import com.comcast.objectRepositoryUtils.PriceBooksCreatePage;
import com.comcast.objectRepositoryUtils.PriceBooksInfoPage;
import com.comcast.objectRepositoryUtils.PriceBooksPage;
import com.comcast.objectRepositoryUtils.VendorCreatePage;
import com.comcast.objectRepositoryUtils.VendorInfoPage;
import com.comcast.objectRepositoryUtils.VendorPage;

/**
 * @author Shashank
 */

//@Listeners(com.comcast.crm.generic.listenerUtils.ListenerImplementationClass.class)
public class PriceBooksModuleTest extends BaseClass{

	@Test(groups = "smokeTest")
	public void createVendorTest() throws Throwable {

		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
		String priceBookName 	= elib.getDataFromExcel("priceBooks", 1, 1) + jlib.getRandonNumber();
		String currencyDD 		= elib.getDataFromExcel("priceBooks", 1, 2) + jlib.getRandonNumber();
		String description 		= elib.getDataFromExcel("priceBooks", 1, 3) + jlib.getRandonNumber();

		//Step 2 : navigate to Product module 
		UtilityClassObject.getTest().log(Status.INFO, "navigate to PriceBooks module");
		HomePage hp = new HomePage(driver);
		hp.getMoreLink().click();
		hp.getPriceBooksLink().click();
		
		//Step 3 : click on "Create Products Icon" button
		UtilityClassObject.getTest().log(Status.INFO, "Create PriceBooks Icon button");
		PriceBooksPage pbp = new PriceBooksPage(driver);
		pbp.getCreatePriceBooks().click();
		
		// Step 4 : Enter all the details and Create new Vendor
		UtilityClassObject.getTest().log(Status.INFO, "Create new PriceBooks");
		PriceBooksCreatePage pbc = new PriceBooksCreatePage(driver);
		pbc.PriceBooksCreateDetails(priceBookName, currencyDD, description);
		pbc.getSaveBtn().click();
		
		//Verifying Vendor Header Info and Vendor Name
		PriceBooksInfoPage pip = new PriceBooksInfoPage(driver);
		String PriceBooksHeaderInfo = pip.getPriceBooksHeaderMsg().getText();
		boolean VerifyPriceBooksHeaderInfo = PriceBooksHeaderInfo.contains(priceBookName);
		Assert.assertEquals(VerifyPriceBooksHeaderInfo, true);

		String actualPriceBooksName = pip.getVerifPriceBooksTF().getText();
		boolean verifyActualPriceBooksName = actualPriceBooksName.equals(priceBookName);
		Assert.assertEquals(verifyActualPriceBooksName, true);
	}
}
