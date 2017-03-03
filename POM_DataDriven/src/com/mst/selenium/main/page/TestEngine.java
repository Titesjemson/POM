package com.mst.selenium.main.page;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mst.selenium.browser.BrowserFactory;
import com.mst.selenium.common.Common;
import com.mst.selenium.excelutility.ExcelUtility;
import com.mst.selenium.extentreport.ReportGenerator;
import com.mst.selenium.mail.Mail_report;
import com.mst.selenium.page.AccountPage;
import com.mst.selenium.page.ContactPage;
import com.mst.selenium.page.Lead_page;
import com.mst.selenium.page.Login_page;



public class TestEngine {
 
WebDriver driver;

ReportGenerator reporter;
	@BeforeSuite
	public void beforeSuite() throws IOException{
		ExcelUtility.openStream();
	}
	@BeforeMethod
	@Parameters({"browser"})
	public void getBrowser(String browser,Method method) throws Exception{
	   	driver = BrowserFactory.getDriver(browser);
		driver.manage().window().maximize();
	}
	
	 @Test
	 @Parameters({"browser","environment"})
	public  void leadCreation(String browser,String environment, Method method)throws Exception{
		    String methodName = method.getName(), TC_Name = "TC001";
			reporter = new ReportGenerator(browser, methodName);
		    Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);
			
			Lead_page leadPage = PageFactory.initElements(driver,Lead_page.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			leadPage.createNewLeadAction(leadPage, reporter, methodName, TC_Name);
			
			reporter.childReport("lead name verified");
			Common.presenceOfElement(driver,leadPage.leadVerify);
			
			leadPage.lead_Verify(ExcelUtility.readExcel(methodName,TC_Name,"LeadName Verify"));
			
			leadPage.deleteLeadAction(leadPage, reporter);
			
	 }
   
	@Test
    @Parameters({"browser","environment"})
	public void accountCreation(String browser,String environment,Method method) throws Exception{
		 String methodName = method.getName(), TC_Name = "TC002";
		reporter = new ReportGenerator(browser, methodName);
	    Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);
		 
		 AccountPage accPage=PageFactory.initElements(driver, AccountPage.class);
		 reporter.childReport("Account tab clicked");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 accPage.accountTab.click();
		 reporter.childReport("New button clicked");
		 Common.presenceOfElement(driver, accPage.newButtonVerify);
		 accPage.newButton.click();
		 reporter.childReport("AccountName Entered");
		 accPage.accountName.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Account Name"));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 reporter.childReport("ParentAccount entered");
		 accPage.clickOnLookup(ExcelUtility.readExcel(methodName,TC_Name,"ParentAccount"));
		 reporter.childReport("Save button clicked");
		 accPage.save.click();
		 reporter.childReport("ParentAccount Verified");
		 Common.presenceOfElement(driver,accPage.parentAccountVerification);
		 accPage.parentAccountVerify(ExcelUtility.readExcel(methodName,TC_Name,"ParentAccount Verify"));
	 }
	
	@Test
	@Parameters({"browser","environment"})
	public void contactCreation(String browser,String environment, Method method)throws Exception{
		 String methodName = method.getName(),TC_Name = "TC003";
		reporter = new ReportGenerator(browser, methodName);
	    Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);

		 
		 ContactPage contPage=PageFactory.initElements(driver, ContactPage.class);
		 reporter.childReport("ContactTab clicked");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 contPage.contactTab.click();
		 reporter.childReport("New button clicked");
		 Common.presenceOfElement(driver,contPage.newButtonVerify);
		 contPage.newButton.click();
		 reporter.childReport("LastName entered");
		 contPage.lastName.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Last Name"));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 reporter.childReport("AccountName entered");
		 contPage.clickOnLookup(ExcelUtility.readExcel(methodName,TC_Name,"Account Name"));
		 reporter.childReport("Save button clicked");
		 contPage.save.click();
		 reporter.childReport("AccountNameVerified");
		 Common.presenceOfElement(driver, contPage.accountName_Verification);
		 contPage.accountNameVerify(ExcelUtility.readExcel(methodName,TC_Name,"AccountName Verify"));
		 reporter.childReport("account clicked");
		 contPage.accountName.click();
		 reporter.childReport("delete button clicked");
		 contPage.deleteButton.click();
		 reporter.childReport("Record deleted");
		 contPage.alertAccept(driver);
		 Common.presenceOfElement(driver, contPage.by_loginVerify); 
	 }
	@Test
	 @Parameters({"browser","environment"})
	public  void leadTabVerification(String browser,String environment, Method method)throws Exception{
		    String methodName = method.getName(), TC_Name = "TC004";
			reporter = new ReportGenerator(browser, methodName);
		    Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);
			
			Lead_page leadpageverify = PageFactory.initElements(driver,Lead_page.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			leadpageverify.leadTabVerify();
			
	 }
	@AfterMethod
	@Parameters({"browser"})
	public void tearDown(ITestResult result,String browser) throws Exception{
		 String methodName=result.getMethod().getMethodName();
		 if(result.getStatus()==ITestResult.FAILURE){
			 String res=result.getThrowable().getMessage();
			 reporter.logScreenshot(driver, methodName+browser,res);
		 }
		 reporter.endTest();
		 this.driver.close();
		 
	}
	
	@AfterSuite
	public void afterSuite() throws IOException{
		reporter.flush();
		ExcelUtility.closeStream();
		//Mail_report.send_report();
	}
	
	
	
}
