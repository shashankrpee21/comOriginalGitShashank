package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class LeadsInfoPage {

	WebDriver driver;
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	public LeadsInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement LeadsHeaderMsg;

	public WebElement getLeadsHeaderMsg() {
		return LeadsHeaderMsg;
	}
	
	@FindBy(id="dtlview_Last Name")
	private WebElement compareLastNameMsg;

	public WebElement getVerifyLastNameTF() {
		return compareLastNameMsg;
	}	
	
	@FindBy(id="dtlview_Company")
	private WebElement compareCompanyTF;

	public WebElement getVerifyCompanyTF() {
		return compareCompanyTF;
	}
	
	@FindBy(id="dtlview_Mobile")
	private WebElement compareMobileTF;

	public WebElement getVerifyMobileTF() {
		return compareMobileTF;
	}
	
	@FindBy(id="dtlview_Email")
	private WebElement compareEmailTF;
	
	public WebElement getVerifyEmailTF() {
		return compareEmailTF;
	}
	
	//----------------Covert to Lead------------------
	
	@FindBy(linkText="Convert Lead")
	private WebElement convertLeadLink;
	
	public WebElement getLeadToOpportunityLink() {
		return convertLeadLink;
	}
	
	
	@FindBy(xpath="//td[contains(text(),'Convert Lead :')]")
	private WebElement convertLeadHeaderText;
	
	public WebElement getConvertLeadHeaderText() {
		return convertLeadHeaderText;
	}
	
	@FindBy(id="select_potential")
	private WebElement opportunityCB;
	
	public WebElement getopportunityCB() {
		return opportunityCB;
	}
	
//	@FindBy(id="jscal_trigger_closedate")
//	private WebElement calenderIcon;
//	
//	public WebElement getCalenderIcon() {
//		return calenderIcon;
//	}
	
	@FindBy(name="closingdate")
	private WebElement calenderTF;
	
	public WebElement getExpectedCloseDateCalenderTF() {
		return calenderTF;
	}
	
	@FindBy(xpath="//select[@name='sales_stage']")
	private WebElement salesStageDD;
	
	public WebElement getSalesStageDD() {
		return salesStageDD;
	}
	
	@FindBy(name="Save")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	@FindBy(id="txtbox_ModCommentsDetailViewBlockCommentWidget")
	private WebElement addCommentTA;
	
	public WebElement getAddCommentTA() {
		return addCommentTA;
	}
	
	@FindBy(xpath="//div[@id='editarea_ModCommentsDetailViewBlockCommentWidget']/input")
	private WebElement addCommentSaveBtn;
	
	public WebElement getAddCommentSaveBtn() {
		return addCommentSaveBtn;
	}
	
	public void covertLeadToOpportunity(String expectCloseDate, String salesStageDD) {
		getLeadToOpportunityLink().click();
		getopportunityCB().click();
		getExpectedCloseDateCalenderTF().sendKeys(expectCloseDate);
		wlib.select(getSalesStageDD(), salesStageDD);
		getSaveBtn().click();
	}
	
	public void addComment(String addComment) {
		getAddCommentTA().sendKeys(addComment);
		getAddCommentSaveBtn().click();
		
	}
}