package com.pyga_crm.genericLib;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

	@Override
	public void onStart(ITestContext context) {
		
		//Random random=new Random();
		//int randomNum = random.nextInt(2000);
		
		LocalDateTime localDT = LocalDateTime.now();
		String dateTime = localDT.toString().replace(" ","_").replace(":","_");

		
		ExtentSparkReporter spark= new ExtentSparkReporter("ExtentReport/Reports.html"+dateTime);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("ExtentReport");
		spark.config().setReportName("Report");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Platform", "windows 11");
		report.setSystemInfo("Executed By", "Sushma");
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getThrowable());

	     String screenShot = WebActionsLib.takeScreenshot(result.getMethod().getMethodName());
	     test.addScreenCaptureFromPath(screenShot);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName());
		test.log(Status.SKIP, result.getThrowable());

	}

	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName());
		test.log(Status.PASS, result.getThrowable());
	}
	

}
