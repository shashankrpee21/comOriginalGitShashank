package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInfoPage {

	WebDriver driver;
	
	public VendorInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "lvtHeaderText")//xpath = "//span[@class='lvtHeaderText']"
	private WebElement vendorHeaderMsg;
	
	@FindBy(id = "dtlview_Vendor Name")
	private WebElement compareVendorNameMsg;

	@FindBy(xpath = "//span[@id='dtlview_Vendor Name']")
	private WebElement verifyVendorName;
	
	
	public WebElement getVendorHeaderMsg() {
		return vendorHeaderMsg;
	}
	
	public WebElement getCompareVendorNameMsg() {
		return compareVendorNameMsg;
	}
	
	public WebElement getVerifyVendorName() {
		return verifyVendorName;
	}
}
