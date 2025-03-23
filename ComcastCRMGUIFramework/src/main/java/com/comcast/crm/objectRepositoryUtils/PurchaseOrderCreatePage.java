package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class PurchaseOrderCreatePage {

	WebDriver driver;
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	public PurchaseOrderCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@name='subject']")
	private WebElement subjectTF;

	@FindBy(xpath="//input[@name='vendor_name']/following-sibling::img[@title='Select']")
	private WebElement selectVendorNameIcon;
	
	@FindBy(xpath="//input[@id='search_txt']")
	private WebElement popUpSearchTF;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement popUpSearchBtn;
	
	@FindBy(xpath="//input[@name='contact_id']/following-sibling::img[@title='Select']")
	private WebElement selectContactNameIcon;
	
//	@FindBy(xpath="//input[@id='search_txt']")
//	private WebElement contactNameSearchTF;
//	
//	@FindBy(xpath="//input[@name='search']")
//	private WebElement contactNameSearchBtn;
	
	@FindBy(xpath="//input[@id='jscal_field_duedate']")
	private WebElement dueDateTF;	
	
	@FindBy(xpath="//select[@name='postatus']")
	private WebElement poStatusDD;
	
	@FindBy(xpath="//textarea[@name='bill_street']")
	private WebElement billingAddressTF;
	
	@FindBy(xpath="//input[@id='bill_pobox']")
	private WebElement billingPoBoxTF;
	
	@FindBy(xpath="//input[@id='bill_city']")
	private WebElement billingCityTF;
	
	@FindBy(xpath="//input[@id='bill_state']")
	private WebElement billingStateTF;
	
	@FindBy(xpath="//input[@id='bill_country']")
	private WebElement billingCountryTF;
	
	@FindBy(xpath="//input[@id='bill_code']")
	private WebElement billingCodeTF;
	
	@FindBy(xpath="//textarea[@name='ship_street']")
	private WebElement shippingAddressTF;
	
	@FindBy(xpath="//input[contains(@onclick,'return copyAddressLeft(EditView)')]")
	private WebElement copyShippingAddressRB;
	
	@FindBy(xpath="//input[@onclick='return copyAddressRight(EditView)']")
	private WebElement copyBillingAddressRB;
	
	@FindBy(xpath="//img[@id='searchIcon1']")
	private WebElement selectProductIcon;
	
	@FindBy(xpath="//textarea[@id='comment1']")
	private WebElement productCommentTA;	
	
	@FindBy(xpath="//img[@id='searchIcon1']")	//img[@title='Products']
	private WebElement productPickList;	
	
	@FindBy(xpath="//input[@id='qty1']")
	private WebElement productQtyTF;
	
	@FindBy(xpath="//input[@id='listPrice1']")
	private WebElement productListPriceTF;
	

	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
	private WebElement saveBtn;
	
	

	public WebElement getSubjectTF() {
		return subjectTF;
	}

	public WebElement getSelectVendorNameIcon() {
		return selectVendorNameIcon;
	}
	
	public WebElement getPopUpSearchTF() {
		return popUpSearchTF;
	}
	
	public WebElement getPopUpSearchBtn() {
		return popUpSearchBtn;
	}

	public WebElement getSelectContactNameIcon() {
		return selectContactNameIcon;
	}

//	public WebElement getContactNameSearchTF() {
//		return contactNameSearchTF;
//	}
//	
//	public WebElement getContactNameSearchBtn() {
//		return contactNameSearchBtn;
//	}

	public WebElement getDueDateTF() {
		return dueDateTF;
	}
	
	public WebElement getPoStatusDD() {
		return poStatusDD;
	}

	public WebElement getBillingAddressTF() {
		return billingAddressTF;
	}

	public WebElement getBillingPoBoxTF() {
		return billingPoBoxTF;
	}
	
	public WebElement getBillingCityTF() {
		return billingCityTF;
	}
	
	public WebElement getBillingStateTF() {
		return billingStateTF;
	}
	
	public WebElement getBillingCodeTF() {
		return billingCodeTF;
	}
	
	public WebElement getBillingCountryTF() {
		return billingCountryTF;
	}
	
	public WebElement getSelectProductIcon() {
		return selectProductIcon;
	}
	
	public WebElement getProductCommentTA() {
		return productCommentTA;
	}
	
	public WebElement getShippingAddressTF() {
		return shippingAddressTF;
	}

	public WebElement getCopyShippingAddressRB() {
		return copyShippingAddressRB;
	}

	public WebElement getCopyBillingAddressRB() {
		return copyBillingAddressRB;
	}

	public WebElement getProductPickList() {
		return productPickList;
	}

	public WebElement getProductQtyTF() {
		return productQtyTF;
	}

	public WebElement getProductListPriceTF() {
		return productListPriceTF;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void billingAddressInfo(String poStatusDD,String billingAddressTF, String billingPoBoxTF, String billingCityTF, String billingStateTF, String billingCodeTF, String billingCountryTF) {
		wlib.select(getPoStatusDD(), poStatusDD);
		getBillingAddressTF().sendKeys(billingAddressTF);
		getBillingPoBoxTF().sendKeys(billingPoBoxTF);
		getBillingCityTF().sendKeys(billingCityTF);
		getBillingStateTF().sendKeys(billingStateTF);
		getBillingCodeTF().sendKeys(billingCodeTF);
		getBillingCountryTF().sendKeys(billingCountryTF);
	}
	
	
}
