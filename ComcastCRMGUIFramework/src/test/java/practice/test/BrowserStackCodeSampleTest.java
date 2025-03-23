package practice.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class BrowserStackCodeSampleTest {
	
	@Test
	public void sampleTest() throws MalformedURLException{
		
		String userName = "shashanks_SNtkUT";
		String password = "CdxVUcyJno5jdPzs7cpZ";
		
		
		//Directly copied from https://www.browserstack.com/docs/automate/capabilities
		MutableCapabilities capabilities = new MutableCapabilities();
		HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
		capabilities.setCapability("browserName", "Chrome");
		bstackOptions.put("os", "Windows");
		bstackOptions.put("osVersion", "10");
		bstackOptions.put("browserVersion", "120.0");
//		bstackOptions.put("userName", "shashanks_SNtkUT");
//		bstackOptions.put("accessKey", "CdxVUcyJno5jdPzs7cpZ");
		bstackOptions.put("consoleLogs", "info");
		capabilities.setCapability("bstack:options", bstackOptions);

		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://"+userName+":"+password+"@hub-cloud.browserstack.com/wd/hub"), capabilities);
	
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
