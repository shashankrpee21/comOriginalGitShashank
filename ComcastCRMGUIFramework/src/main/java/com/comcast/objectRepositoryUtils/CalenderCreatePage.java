package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class CalenderCreatePage {

	WebDriver driver;

	WebDriverUtility wlib = new WebDriverUtility();

	public CalenderCreatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "activitytype")
	private WebElement eventTypeDD;

	@FindBy(name = "subject")
	private WebElement eventNameTF;

	@FindBy(name = "description")
	private WebElement descriptionTA;

	@FindBy(name = "location")
	private WebElement locationTF;

	@FindBy(name = "sendnotification")
	private WebElement sendNotificationCB;

	@FindBy(id = "starthr")
	private WebElement eventStartHrDD;

	@FindBy(id = "startmin")
	private WebElement eventStartMinDD;

	@FindBy(id = "startfmt")
	private WebElement eventStartFmtDD;

	@FindBy(xpath = "//input[@id='jscal_field_date_start']")
	private WebElement eventStartDateTF;

	@FindBy(id = "endhr")
	private WebElement eventEndHrDD;

	@FindBy(id = "endmin")
	private WebElement eventEndMinDD;

	@FindBy(id = "endfmt")
	private WebElement eventEndFmtDD;

	@FindBy(xpath = "//input[@id='jscal_field_due_date']")
	private WebElement eventEndDateTF;

	@FindBy(id = "availableusers")
	private WebElement selectAvailableUsersDD;

	@FindBy(xpath = "//input[@value='Add >>']")
	private WebElement addBtn;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="subject")
	private WebElement toDoSubjectTF;
	
	@FindBy(name="description")
	private WebElement descriptionTF;
	
	@FindBy(id="taskstatus")
	private WebElement taskStatusDD;
	
	@FindBy(xpath = "//input[@id='date_start']")
	private WebElement toDoStartDateTF;
	
	@FindBy(xpath = "//input[@id='due_date']")
	private WebElement toDoDueDateTF;
	
	

	public WebElement getEventTypeDD() {
		return eventTypeDD;
	}

	public WebElement getEventNameTF() {
		return eventNameTF;
	}

	public WebElement getDescriptionTA() {
		return descriptionTA;
	}

	public WebElement getLocationTF() {
		return locationTF;
	}

	public WebElement getSendNotificationCB() {
		return sendNotificationCB;
	}

	public WebElement getEventStartHrDD() {
		return eventStartHrDD;
	}

	public WebElement getEventStartMinDD() {
		return eventStartMinDD;
	}

	public WebElement getEventStartFmtDD() {
		return eventStartFmtDD;
	}

	public WebElement getEventStartDateTF() {
		return eventStartDateTF;
	}

	public WebElement getEventEndHrDD() {
		return eventEndHrDD;
	}

	public WebElement getEventEndMinDD() {
		return eventEndMinDD;
	}

	public WebElement getEventEndFmtDD() {
		return eventEndFmtDD;
	}

	public WebElement getEventEndDateTF() {
		return eventEndDateTF;
	}

	public WebElement getSelectAvailableUsersDD() {
		return selectAvailableUsersDD;
	}

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getToDoSubjectTF() {
		return toDoSubjectTF;
	}

	public WebElement getDescriptionTF() {
		return descriptionTF;
	}

	public WebElement getTaskStatusDD() {
		return taskStatusDD;
	}

	public WebElement getToDoStartDateTF() {
		return toDoStartDateTF;
	}

	public WebElement getToDoDueDateTF() {
		return toDoDueDateTF;
	}
	
	
	public void getSelectMultipleOptions() {

		Select select = (Select) getSelectAvailableUsersDD();

		if (select.isMultiple()) {
			select.selectByValue("5");
			select.selectByValue("6");
			select.selectByValue("7");
		}
	}

	public void creatingEventInfo(String eventTypeDD, String eventName, String descriptionTA, String locationTF) {
		wlib.select(getEventTypeDD(), eventTypeDD);
		getEventNameTF().sendKeys(eventName);
		getDescriptionTA().sendKeys(descriptionTA);
		getLocationTF().sendKeys(locationTF);
		getSendNotificationCB().click();
	}
	
	public void startEvent(String eventStartHrDD, String eventStartMinDD, String eventStartFmtDD) {
		wlib.select(getEventStartHrDD(), eventStartHrDD);
		wlib.select(getEventStartMinDD(), eventStartMinDD);
		wlib.select(getEventStartFmtDD(), eventStartFmtDD);
	}

	public void endEvent(String eventEndHrDD, String eventEndMinDD, String eventEndFmtDD) {
		wlib.select(getEventEndHrDD(), eventEndHrDD);
		wlib.select(getEventEndMinDD(), eventEndMinDD);
		wlib.select(getEventEndFmtDD(), eventEndFmtDD);
	}
	
	public void availableUsers(String selectAvailableUsersDD1, String selectAvailableUsersDD2, String selectAvailableUsersDD3) {
		wlib.select(getSelectAvailableUsersDD(), selectAvailableUsersDD1);
		wlib.select(getSelectAvailableUsersDD(), selectAvailableUsersDD2);
		wlib.select(getSelectAvailableUsersDD(), selectAvailableUsersDD3);
	}
	
	public void creatingToDoInfo(String toDoSubjectTF, String descriptionTF, String taskStatusDD) {
		getToDoSubjectTF().sendKeys(toDoSubjectTF);
		getDescriptionTF().sendKeys(descriptionTF);
		wlib.select(getTaskStatusDD(), taskStatusDD);
	}
}