package practice.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class RemoteWebDriverSampleTest {

	RemoteWebDriver driver;
	
	@Parameters({"browserName","platform"})
	@Test
	public void sampleTest(String bN, String ptf) throws MalformedURLException{
		
		
		URL ipAdd = new URL("https://192.168.137.195:4444");		
		
		if(bN.equals("edge") && ptf.equals("windows")) {
			EdgeOptions opt = new EdgeOptions();
			opt.setPlatformName("windows");
			driver = new RemoteWebDriver(ipAdd, opt);
		}
		
		else if(bN.equals("chrome") && ptf.equals("windows")){
			ChromeOptions opt = new ChromeOptions();
			opt.setPlatformName("windows");
			driver = new RemoteWebDriver(ipAdd, opt);
		}
		
		else if(bN.equals("chrome") && ptf.equals("Linux")) {
			ChromeOptions opt = new ChromeOptions();
			opt.setPlatformName("linux");
			driver = new RemoteWebDriver(ipAdd, opt);
		}
		
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		driver.quit();
			
	}
}
