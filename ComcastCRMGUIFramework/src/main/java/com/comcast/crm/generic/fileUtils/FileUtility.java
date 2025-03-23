package com.comcast.crm.generic.fileUtils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws Throwable {

		FileInputStream fis = new FileInputStream(IPathConstants.filePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(key);
		return data;
	}

//	public void getExcelupload(WebDriver driver, WebElement ele) {
//		driver.get("URL_OF_THE_PAGE_WITH_UPLOAD");
//
//		WebElement uploadElement = driver.findElement(By.id("file-upload"));
//
//		String filePath = "C:\\path\\to\\your\\file.txt";
//
//		uploadElement.sendKeys(filePath);
//
//		WebElement submitButton = driver.findElement(By.id("submit-button"));
//		submitButton.click();
//	}

	public void fileUploadPopup(WebElement element, String file_path) {
		element.sendKeys(file_path);
	}

}
