package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class LoginPage extends WebDriverUtility{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement username;

	@FindBy(name = "user_password")
	private WebElement password;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLogin() {
		return loginBtn;
	}

	public void loginToApp(String Username, String Password) {
		username.sendKeys(Username);
		password.sendKeys(Password);
	}
}
