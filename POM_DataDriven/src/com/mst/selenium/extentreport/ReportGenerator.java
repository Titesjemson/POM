package com.mst.selenium.extentreport;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.mst.selenium.constant.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportGenerator {
	ExtentTest childTest, parentTest;
	private static ExtentReports reporter = ExtentReportFactory.getReporter();

	public ReportGenerator (String browser, String methodName){
		parentReport(browser, methodName);
	}
	
	public void parentReport(String browser, String methodName) {
		parentTest = reporter.startTest(methodName+browser);
		parentTest.assignCategory(browser);
	
	}

	public void childReport(String methodName) {
		childTest = reporter.startTest(methodName);
		childTest.log(LogStatus.PASS, methodName);
		parentTest.appendChild(childTest);
	}

	public void flush(){
		reporter.flush();
	}
	public void endTest(){
		reporter.endTest(parentTest);
	}
	public void logScreenshot(WebDriver screenDriver, String testCaseName, String res) throws IOException{
		try{
			File file  = ((TakesScreenshot)screenDriver).getScreenshotAs(OutputType.FILE);
			File dir = new File(Constant.screenshotPath+testCaseName);
			dir.mkdirs();
			String workspace = 	((new File(".").getAbsolutePath()).replace("\\", "/")).replace(".", "");
			String fileName= workspace+Constant.screenshotPath+testCaseName+"/"+testCaseName+".jpg";
			FileUtils.copyFile(file, new File(fileName));
			ExtentTest logger = this.childTest;
			String img = logger.addScreenCapture(fileName);
		    logger.log(LogStatus.FAIL, "Image", res+img);
		}
		catch(Exception ex){
			System.out.println("Exception while taking screen  shot");
		}		
	}
	
}
