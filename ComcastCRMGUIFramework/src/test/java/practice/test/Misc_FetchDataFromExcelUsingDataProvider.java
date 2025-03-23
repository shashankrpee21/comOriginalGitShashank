package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtils.ExcelUtility;

public class Misc_FetchDataFromExcelUsingDataProvider {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	    driver = new FirefoxDriver();
	    driver.get("https://amazon.in");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//driver.manage().window().maximize();
	}
	
	@Test(dataProvider = "genericDataProvider")
	public void getProductInfoTest(String brandName, String productName) {

		// Search Product
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search Amazon.in']")));
		searchBox.sendKeys(brandName, Keys.ENTER);
		
		// Capture Product
		String x = "//span[text()='" + productName + "']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
	}
	
	@AfterMethod
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}
	
	@DataProvider
	public Object[][] genericDataProvider() throws Throwable{
		ExcelUtility elib = new ExcelUtility();
		Object[][] value = elib.dataProvider("amazon");
		return value;
	}
}
