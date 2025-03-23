package practice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Incognito1ParallelExecution {

	@Test
	public void incognitoMode() {

		ChromeOptions opt = new ChromeOptions();

		opt.addArguments("--Incognito");

		opt.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(opt);

		driver.get("http://localhost:8888");
	}
}
