package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderInfoPage {
	
	WebDriver driver;
	
	public PurchaseOrderInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement VerifyHeaderMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Subject']")
	private WebElement VerifySubjectTF;
	
	@FindBy(xpath="//span[@id='dtlview_Status']")
	private WebElement VerifyStatusTF;
	
	@FindBy(xpath="//td[@id='mouseArea_Vendor Name']/a")
	private WebElement VerifyVendorNameTF;

	@FindBy(xpath="//td[@id='mouseArea_Contact Name']")
	private WebElement VerifyContactNameTF;


	public WebElement getVerifyPurchaseOrderHeaderMsg() {
		return VerifyHeaderMsg;
	}
	
	public WebElement getVerifySubjectTF() {
		return VerifySubjectTF;
	}
	
	public WebElement getVerifyStatusTF() {
		return VerifyStatusTF;
	}
	
	public WebElement getVerifyVendorNameTF() {
		return VerifyVendorNameTF;
	}
	
	public WebElement getVerifyContactNameTF() {
		return VerifyContactNameTF;
	}
	
}
