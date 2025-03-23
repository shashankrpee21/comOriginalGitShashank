package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPage {

	WebDriver driver;
	
	public PurchaseOrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[normalize-space()='Create Purchase Order']")
	private WebElement createPurchaseOrderLinkFromProductPage;

	public WebElement getCreatePurchaseOrderLinkFromProductPage() {
		return createPurchaseOrderLinkFromProductPage;
	}
	
	@FindBy(xpath="//img[@title='Create Purchase Order...']")
	private WebElement createPurchaseOrderLink;
	
	public WebElement getCreatePurchaseOrderLink() {
		return createPurchaseOrderLink;
	}
}
