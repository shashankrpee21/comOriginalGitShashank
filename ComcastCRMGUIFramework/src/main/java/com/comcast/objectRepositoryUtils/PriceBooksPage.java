package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceBooksPage {

	WebDriver driver;
	
	public PriceBooksPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Price Book...']")
	private WebElement createPriceBooks;

	public WebElement getCreatePriceBooks() {
		return createPriceBooks;
	}
}
