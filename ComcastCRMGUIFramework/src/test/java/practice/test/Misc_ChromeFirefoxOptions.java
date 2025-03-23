package practice.test;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Misc_ChromeFirefoxOptions {

	@Test
	public void browserVersion() {

		ChromeOptions opt = new ChromeOptions();

		opt.addArguments("--headless");

		WebDriver driver = new ChromeDriver(opt);

		ChromeDriver br = (ChromeDriver) driver;//down casting to the properties from WebDrver to since getCapabilities() are present in chromeOptions

		String browserVersion = br.getCapabilities().getBrowserName();

		String browserName = br.getCapabilities().getBrowserVersion();

		System.out.println(browserVersion + "\t" + browserName);
	}
	

	@Test
	public void incognitoMode() {

		ChromeOptions opt = new ChromeOptions();

		opt.addArguments("--Incognito");

		opt.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(opt);

		driver.get("http://localhost:8888");
	}

	// In firefox we have this inBuilt () getFullPageScreenshotAs - get full page length
	@Test
	public void fullPageScreenShot() throws Throwable {

		FirefoxOptions opt = new FirefoxOptions();

		opt.addArguments("--private");// --private-window

		WebDriver driver = new FirefoxDriver(opt);

		driver.get("https://www.amazon.in");

		FirefoxDriver ff = (FirefoxDriver) driver;

		// In firefox we have this inBuilt () getFullPageScreenshotAs - get full page length
		File src = ff.getFullPageScreenshotAs(OutputType.FILE);

		File dst = new File("./screenShot/firefox.png");

		FileUtils.copyFile(src, dst);
	}

	@Test
	public void fullPageScreenShot_FireFox() throws Throwable {

		FirefoxOptions opt = new FirefoxOptions();

		opt.addArguments("--private");// --private-window

		// Firefox does not have a --start-maximized argument like Chrome
		opt.addArguments("--width=1520", "--height=900");

		WebDriver driver = new FirefoxDriver(opt);

		driver.get("https://www.flipkart.com/");

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		FirefoxDriver ff = (FirefoxDriver) driver;

		File src = ff.getFullPageScreenshotAs(OutputType.FILE);

		File dst = new File("./screenShot/firefox.png");

		FileUtils.copyFile(src, dst);
	}
	
	
	@Test  //In Chrome don't have inBuild fullPage-SS so we are using the 3rd party dependency - AShot;
	public void fullPageScreenShot_Chrome() throws Throwable {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://nike.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
		Thread.sleep(1000);
		
		// Here unable to take screenshot in full size so we have used "JSE" and "Robot class" [3rd party tool doesn't support for some laptop];
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("document.body.style.zoom = '0.75'");
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_MINUS);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_MINUS);
		
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_MINUS);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_MINUS);
		
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_MINUS);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_MINUS);
		
		Thread.sleep(1000);


		//In Chrome don't have inBuild fullPage-SS so we are using the 3rd party dependency - AShot;
		AShot screen = new AShot();
		Screenshot screenshot = screen.shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		
		File dst = new File("./screenShot/chromeScreenShot1.png");
		
		ImageIO.write(screenshot.getImage(), "png", dst);
		
		System.out.println("---Screenshot Saved---");
		
		driver.quit();
		
	}
	
}
