package com.comcast.crm.objectRepositoryUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtils.WebDriverUtility;

public class PriceBooksCreatePage {

	WebDriver driver;
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	public PriceBooksCreatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='bookname']")
	private WebElement createPriceBooksTF;
	
	@FindBy(xpath = "//select[@id='currency_id']")
	private WebElement currencyDD;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement descriptionTA;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]'][1]") //input[contains(@onclick,'this.form.action.value='Save'; return validateInventory('PriceBooks')')]
	private WebElement saveBtn;
		
	public WebElement getCreatePriceBooksTF() {
		return createPriceBooksTF;
	}
	
	public WebElement getcurrencyDD() {
		return currencyDD;
	}
	
	public WebElement getDescriptionTA() {
		return descriptionTA;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void PriceBooksCreateDetails(String createPriceBooksTF, String currencyDD, String descriptionTA) {
		getCreatePriceBooksTF().sendKeys(createPriceBooksTF);
		//wlib.select(getcurrencyDD(), currencyDD);
		getDescriptionTA().sendKeys(descriptionTA);
	}
}
