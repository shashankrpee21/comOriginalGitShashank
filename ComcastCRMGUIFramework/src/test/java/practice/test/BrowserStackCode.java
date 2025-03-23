package practice.test;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BrowserStackCode {

    @Test
    public void vTiger() throws Throwable {
        String userName = "shashanks_SNtkUT";
        String password = "CdxVUcyJno5jdPzs7cpZ";

        // Directly copied from https://www.browserstack.com/docs/automate/capabilities
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

        // Remote WebDriver for BrowserStack
        RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + userName + ":" + password + "@hub-cloud.browserstack.com/wd/hub"), capabilities);

        String Username = "admin";
        String Password = "admin";

        Random random = new Random();
        int randomInt = random.nextInt(1000);

        // Load Excel data
        FileInputStream fis1 = new FileInputStream(getClass().getClassLoader().getResource("testData/testScriptData.xlsx").getFile());
        Workbook wb = WorkbookFactory.create(fis1);
        Sheet sh = wb.getSheet("contact");
        Row row = sh.getRow(1);
        String lastName = (row != null && row.getCell(2) != null) ? row.getCell(2).toString() + randomInt : "DefaultName" + randomInt;
        wb.close();

        // Use BrowserStack (not local drivers)
        driver.get("http://49.249.28.218:8888/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.name("user_name")).sendKeys(Username);
        driver.findElement(By.name("user_password")).sendKeys(Password);
        driver.findElement(By.id("submitButton")).click();
        driver.findElement(By.linkText("Contacts")).click();
        driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='dtlview_Last Name']")));

        String actLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
        if (actLastName.contains(lastName)) {
            System.out.println(lastName + " is created = Pass");
        } else {
            System.out.println(lastName + " is not created = Fail");
        }

        // Action and logout
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"))).perform();
        driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
        driver.quit();
    }
}
