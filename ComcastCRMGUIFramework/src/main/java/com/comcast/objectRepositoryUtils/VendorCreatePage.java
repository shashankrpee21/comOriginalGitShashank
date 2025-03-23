package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorCreatePage {

	WebDriver driver;
	
	public VendorCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='vendorname']")
	private WebElement vendorNameTF;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement emailTF;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
	//input[contains(@onclick,'displaydeleted();return validateInventory('Vendors')')]
	private WebElement saveBtn;

	
	public WebElement getVendorNameTF() {
		return vendorNameTF;
	}

	public WebElement getEmailTF() {
		return emailTF;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
}
