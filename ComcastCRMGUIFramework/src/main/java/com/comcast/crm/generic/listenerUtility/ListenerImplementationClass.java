package com.comcast.crm.generic.listenerUtility;

//import java.io.File;
import java.util.Date;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.basetest.BaseClass;
//import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

	//ListenerImplementationClass used to analysis the RunTime-Event and to perform appropriate action
	
	
	//public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Configaturation");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		// Spark Report Config  
		ExtentSparkReporter spark =  new ExtentSparkReporter("./AdvanceReport/Report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		
		/* add Environment & Create Test */
		report = new ExtentReports();
		report.attachReporter(spark);		
		report.setSystemInfo("Browser", "Chrome-132");
		report.setSystemInfo("Operating System", System.getProperty("os.name"));//report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Java Version", System.getProperty("java.version"));
		report.setSystemInfo("User Name", System.getProperty("user.name"));
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report BackUp");		
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=========" + result.getMethod().getMethodName() + "======START====");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		//test.log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED <===");
		UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED <===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=========" + result.getMethod().getMethodName() + "======END====");
//		test.log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED <===");
//		test.pass(MarkupHelper.createLabel("Extent Report - PASS", ExtentColor.GREEN));
		UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED <===");
		UtilityClassObject.getTest().pass(MarkupHelper.createLabel("Extent Report - PASS", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		TakesScreenshot edriver = (TakesScreenshot) BaseClass.staticDriver;
		String srcfilePath = edriver.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
//		test.addScreenCaptureFromBase64String(srcfilePath, testName+"_"+time);
//		test.log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED <===");
//		test.fail(MarkupHelper.createLabel("Extent Report - FAIL", ExtentColor.RED));
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(srcfilePath, testName+"_"+time);
		UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED <===");
		UtilityClassObject.getTest().fail(MarkupHelper.createLabel("Extent Report - FAIL", ExtentColor.RED));
		UtilityClassObject.getTest().log(Status.FAIL, result.getThrowable());//to get the exception details in the report
/*		
		String testName = result.getMethod().getMethodName();

		TakesScreenshot edriver = (TakesScreenshot) BaseClass.staticDriver;
		File src = edriver.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		try {
			FileUtils.copyFile(src, new File("./screenShot/" + testName + "+" + time + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
/*		
		String pics = "./screenShot/" + testName + "_";
		Date d = new Date();
		String d1 = d.toString();
		String d2 = d1.replaceAll(":", "_");
		TakesScreenshot edriver = (TakesScreenshot) BaseClass.staticDriver;
		File src2 = edriver.getScreenshotAs(OutputType.FILE);
		File dst = new File(pics + d2 + ".jpeg");
		try {
			FileHandler.copy(src2, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
*/		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
}
