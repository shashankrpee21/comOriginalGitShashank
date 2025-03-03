package com.comcast.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtility.WebDriverUtility;

public class LeadsCreatePage {

	WebDriver driver;
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	public LeadsCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//select[@name='salutationtype']")
	private WebElement leadFirstNameTitleDD;
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement leadFirstNameTF;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement leadLastNameTF;
		
	@FindBy(xpath="//input[@name='company']")
	private WebElement leadCompanyTF;
	
	@FindBy(xpath = "//select[@name='leadsource']")
	private WebElement leadSourceDD;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDD;
	
	@FindBy(id = "email")
	private WebElement emailTF;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement descriptionTA;
		
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getLeadFirstNameTitleDD() {
		return leadFirstNameTitleDD;
	}
	
	public WebElement getLeadFirstNameTF() {
		return leadFirstNameTF;
	}
	
	public WebElement getLeadLastNameTF() {
		return leadLastNameTF;
	}
	
	public WebElement getLeadCompanyTF() {
		return leadCompanyTF;
	}
	
	public WebElement getLeadSourceDD() {
		return leadSourceDD;
	}
	
	public WebElement getIndustryDD() {
		return industryDD;
	}	
	
	public WebElement getEmailTF() {
		return emailTF;
	}
	
	public WebElement getDescriptionTA() {
		return descriptionTA;
	}
	
	public WebElement getProductSaveBtn() {
		return saveBtn;
	}		
	
	public void createLead(String lastName, String company) {
		leadLastNameTF.sendKeys(lastName);
		leadCompanyTF.sendKeys(company);
		saveBtn.click();
	}
	
	public void createLeadWithDetails(String leadFirstNameTitleDD, String firstName, String lastName, String company, String leadSource, String industry, String emailId, String description) {
		wlib.select(getLeadFirstNameTitleDD(), leadFirstNameTitleDD);
		leadFirstNameTF.sendKeys(firstName);
		leadLastNameTF.sendKeys(lastName);
		leadCompanyTF.sendKeys(company);
		wlib.select(getLeadSourceDD(), leadSource);
		wlib.select(getIndustryDD(), industry);
		emailTF.sendKeys(emailId);
		descriptionTA.sendKeys(description);
		saveBtn.click();
	}
}
