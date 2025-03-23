package practice.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class DockerSampleTest {

	@Test
	public void sampleTest() throws MalformedURLException, InterruptedException {
		
		URL ipAdd = new URL("http://localhost:4444");
		ChromeOptions opt = new ChromeOptions();
		RemoteWebDriver driver = new RemoteWebDriver(ipAdd, opt);
		
		System.out.println("==On Live==");
		Thread.sleep(5000);
		
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		driver.close();
	}
}
