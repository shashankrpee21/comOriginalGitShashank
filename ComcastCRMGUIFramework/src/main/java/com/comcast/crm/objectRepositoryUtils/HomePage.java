package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class HomePage {
	
	WebDriver driver;
	WebDriverUtility wlib = new WebDriverUtility();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement OrganizationsLink;
	
	@FindBy(linkText="Contacts")
	private WebElement ContactsLink;
	
	
	@FindBy(linkText="Opportunities")
	private WebElement OpportunitiesLink;
	
	@FindBy(linkText="More")
	private WebElement MoreLink;
	
	@FindBy(linkText="Products")
	private WebElement ProductsLink;
		
	@FindBy(linkText="Campaigns")
	private WebElement CampaignsLink;
	
	@FindBy(linkText="Leads")
	private WebElement LeadsLink;
	
	@FindBy(linkText="Vendors")
	private WebElement VendorsLink;
	
	@FindBy(linkText="Price Books")
	private WebElement priceBooksLink;
	
	@FindBy(linkText="Purchase Order")
	private WebElement purchaseOrderLink;
	
	@FindBy(xpath="//td[@class='small']/img")
	private WebElement adminBtn;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement adminLogoutBtn;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}

	public WebElement getMoreLink() {
		return MoreLink;
	}
	
	public WebElement getProductsLink() {
		return ProductsLink;				
	}

	public WebElement getCampaignsLink() {
		return CampaignsLink;
	}
	
	public WebElement getLeadsLink() {
		return LeadsLink;
	}
		
	public WebElement getVendorsLink() {
		return VendorsLink;
	}
	
	public WebElement getPriceBooksLink() {
		return priceBooksLink;
	}
	
	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}
	
	public WebElement getAdminBtn() {
		return adminBtn;
	}	
	
	public WebElement getAdminLogoutBtn() {
		return adminLogoutBtn;
	}

	public void navigateToMore() {
		wlib.moveToElement(driver, MoreLink);
		CampaignsLink.click();
	}
	
	public void adminLogout() {
		adminBtn.click();
		adminLogoutBtn.click();
	}
	
}