package com.mst.selenium.main.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.mst.selenium.exception.CustomException;
import com.mst.selenium.extentreport.ReportGenerator;
import com.mst.selenium.page.AccountPage;
import com.mst.selenium.page.ContactPage;
import com.mst.selenium.page.Lead_page;
import com.mst.selenium.page.Login_page;

public class TestEngine {

	WebDriver driver;

	ReportGenerator reporter;

	@BeforeSuite
	public void beforeSuite() throws IOException {
		ExcelUtility.openStream();
	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void getBrowser(String browser, Method method) throws Exception {
		driver = BrowserFactory.getDriver(browser);
		driver.manage().window().maximize();
	}

	@Test
	@Parameters({ "browser", "environment" })
	public void leadCreation(String browser, String environment, Method method) throws Exception {
		String methodName = method.getName(), TC_Name = "TC001";
		reporter = new ReportGenerator(browser, methodName);
		Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);

		Lead_page leadPage = PageFactory.initElements(driver, Lead_page.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		leadPage.createNewLeadAction(leadPage, reporter, methodName, TC_Name);

		reporter.childReport("lead name verified");
		Common.presenceOfElement(driver, leadPage.leadVerify);

		String expectedLeadName = ExcelUtility.readExcel(methodName, TC_Name, "LeadName Verify"); 
		WebElement actualLeadNameElement = leadPage.getLeadName();
		highlightElementBasedOnResult(actualLeadNameElement, expectedLeadName);

		leadPage.deleteLeadAction(leadPage, reporter);

	}

	@Test
	@Parameters({ "browser", "environment" })
	public void accountCreation(String browser, String environment, Method method) throws Exception {
		String methodName = method.getName(), TC_Name = "TC002";
		reporter = new ReportGenerator(browser, methodName);
		Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);

		AccountPage accPage = PageFactory.initElements(driver, AccountPage.class);
		accPage.createNewAccount(reporter, methodName, TC_Name, accPage);
		reporter.childReport("ParentAccount Verified");
		Common.presenceOfElement(driver, accPage.parentAccountVerification);

		String expectedParentAccount = ExcelUtility.readExcel(methodName, TC_Name, "ParentAccount Verify");
		WebElement element = accPage.getParentAccount(expectedParentAccount);
		highlightElementBasedOnResult(element, expectedParentAccount);

	}

	@Test
	@Parameters({ "browser", "environment" })
	public void contactCreation(String browser, String environment, Method method) throws Exception {
		String methodName = method.getName(), TC_Name = "TC003";
		reporter = new ReportGenerator(browser, methodName);
		Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);

		ContactPage contPage = PageFactory.initElements(driver, ContactPage.class);
		contPage.createContact(reporter, methodName, TC_Name, contPage);
		Common.presenceOfElement(driver, contPage.by_loginVerify);
	}

	@Test
	@Parameters({ "browser", "environment" })
	public void leadTabVerification(String browser, String environment, Method method) throws Exception {
		String methodName = method.getName(), TC_Name = "TC004";
		reporter = new ReportGenerator(browser, methodName);
		Login_page.performLoginAction(driver, environment, browser, TC_Name, reporter);

		Lead_page leadpageverify = PageFactory.initElements(driver, Lead_page.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (leadpageverify.isLeadTabDisplayed()) {
			throw new CustomException("Lead tab should not be displayed");
		}
	}

	@AfterMethod
	@Parameters({ "browser" })
	public void tearDown(ITestResult result, String browser) throws Exception {
		String methodName = result.getMethod().getMethodName();
		if (result.getStatus() == ITestResult.FAILURE) {
			String res = result.getThrowable().getMessage();
			reporter.logScreenshot(driver, methodName + browser, res);
		}
		reporter.endTest();
		this.driver.close();

	}

	@AfterSuite
	public void afterSuite() throws IOException {
		reporter.flush();
		ExcelUtility.closeStream();
		// Mail_report.send_report();
	}

	private void highlightElementBasedOnResult(WebElement element, String expectedValue)
			throws InterruptedException {
		String actualValue = element.getText();
		if (actualValue.equals(expectedValue)) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Common.highLightElement(element, driver);
		} else {
			Common.highLightFailedElement(element, driver);
			throw new CustomException(
					"The value " + actualValue + "is not matched with the given value " + expectedValue);
		}

	}
}
