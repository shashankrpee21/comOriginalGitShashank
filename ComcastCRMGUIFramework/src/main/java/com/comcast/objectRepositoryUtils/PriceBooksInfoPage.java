package com.comcast.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceBooksInfoPage {

	WebDriver driver;
	
	public PriceBooksInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement priceBooksHeaderMsg;
	
	@FindBy(id="dtlview_Price Book Name")
	private WebElement verifPriceBooksTF;

	public WebElement getPriceBooksHeaderMsg() {
		return priceBooksHeaderMsg;
	}
	
	public WebElement getVerifPriceBooksTF() {
		return verifPriceBooksTF;
	}	
	

}
