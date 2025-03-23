package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage {

	WebDriver driver;
	
	public VendorPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@title='Create Vendor...']")
	private WebElement createVendorIcon;

	public WebElement getCreateVendorIcon() {
		return createVendorIcon;
	}
}
