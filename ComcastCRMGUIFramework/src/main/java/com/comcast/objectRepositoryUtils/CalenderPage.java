package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalenderPage {

	WebDriver driver;
	
	public CalenderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Add Event")
	private WebElement addEventLink;

	@FindBy(linkText = "Add To Do")
	private WebElement addToDoLink;

	public WebElement getAddEventLink() {
		return addEventLink;
	}
	
	public WebElement getAddToDoLink() {
		return addToDoLink;
	}
	
	
}
