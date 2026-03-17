package com.actitime.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Screenshot extends BaseTest implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		Reporter.log("Testcase has failed: " + result.getName(), true);

		try {
			BaseTest.loadpropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		String methodName = result.getName();
		String timestamp = new Date().toString().replaceAll("[: ]", "_");

		File screenshotDir = new File(p.getProperty("screenshotPath"));
		if (!screenshotDir.exists()) {
			screenshotDir.mkdirs();
		}

		File destination = new File(p.getProperty("screenshotPath") + methodName + "_" + timestamp + ".png");

		try {
			FileHandler.copy(srcFile, destination);
			Reporter.log("Screenshot saved at: " + destination.getAbsolutePath(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}