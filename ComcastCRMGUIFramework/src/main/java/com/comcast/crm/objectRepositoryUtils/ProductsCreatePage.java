package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileUtils.IPathConstants;

public class ProductsCreatePage {

	WebDriver driver;
	
	public ProductsCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='productname']")
	private WebElement ProductNameTF;	

	@FindBy(xpath="//input[@id='jscal_field_sales_start_date']")
	private WebElement salesStartDateTF;
	
	@FindBy(xpath="//input[@id='jscal_field_sales_end_date']")
	private WebElement salesEndDateTF;
	
	@FindBy(xpath="//input[@id='jscal_field_sales_start_date']")
	private WebElement supportStartDateTF;
	
	@FindBy(xpath="//input[@id='jscal_field_sales_end_date']")
	private WebElement supportExpiryDateTF;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement selectVendorNameIcon;
	
	@FindBy(xpath="//input[@id='search_txt']")
	private WebElement childWindowSearchTF;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement childWindowSearchBtn;
	
	@FindBy(xpath="//input[@id='vendor_part_no']")
	private WebElement vendorPartNumTF;
	
	@FindBy(xpath="//input[@id='my_file_element']")
	private WebElement productImageFileUploadBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")//input[contains(@onclick,"this.form.action.value='Save'; return validateInventory('Products')")]
	private WebElement saveBtn; // (//input[@title='Save [Alt+S]'][1]
	
	
	
	public WebElement getProductNameTF() {
		return ProductNameTF;
	}
	
	public WebElement getSalesStartDateTF() {
		return salesStartDateTF;
	}

	public WebElement getSalesEndDateTF() {
		return salesEndDateTF;
	}

	public WebElement getSupportStartDateTF() {
		return supportStartDateTF;
	}

	public WebElement getSupportExpiryDateTF() {
		return supportExpiryDateTF;
	}
	
	public WebElement getSelectVendorNameIcon() {
		return selectVendorNameIcon;
	}
	
	public WebElement getChildWindowSearchTF() {
		return childWindowSearchTF;
	}
	
	public WebElement getChildWindowSearchBtn() {
		return childWindowSearchBtn;
	}
	
	public WebElement getVendorPartNumTF() {
		return vendorPartNumTF;
	}
	
	public WebElement getProductImageBtn() {
		return productImageFileUploadBtn;
	}

	public WebElement getProductSaveBtn() {
		return saveBtn;
	}
	
	public void fileUploadProductImage() {			
		productImageFileUploadBtn.sendKeys(IPathConstants.uploadFile);
	}
	
}
