package main_page;


import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;



import mail_extentReport.Mail_report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import utility.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import extentReport.ExtentReportFactory;
import browser.BrowserFactory;
import Page_Object_page.accountPage;
import Page_Object_page.contactPage;
import Page_Object_page.lead_page;
import Page_Object_page.login_page;
import common.Common;
import excelutility.ExcelUtility;


public class TestEngine {
 
WebDriver driver;
ExtentTest parentTest,childTest;
private static ExtentReports reporter = ExtentReportFactory.getReporter();

	
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
	        parentReport(browser, methodName);
			login(methodName,environment,browser,TC_Name);
			
			lead_page leadpage = PageFactory.initElements(driver,lead_page.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			childReport("LeadTab clicked");
			leadpage.lead_tab.click();
			childReport("New button clicked");
			Common.presenceOfElement(driver,leadpage.new_button);
			leadpage.new_tab.click();
			childReport("Salutation entered");
			leadpage.select_list(ExcelUtility.readExcel(methodName,TC_Name,"Salutation"));
			childReport("FirstName entered");
			leadpage.firstname.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"First Name"));
			childReport("LastName entered");
			leadpage.lastname.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Last Name"));
			childReport("Company name entered");
			leadpage.company.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Company"));
			childReport("Street name entered");
			leadpage.street.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Street"));
			childReport("city name entered");
			leadpage.city.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"City"));
			childReport("State name entered");
			leadpage.state.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"State"));
			childReport("zip code entered");
			leadpage.zip.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Zip"));
			childReport("country name entered");
			leadpage.country.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Country"));
			childReport("Lead status selected");
			leadpage.leadstatus_dropdown(ExcelUtility.readExcel(methodName,TC_Name,"Lead Status"));
			childReport("Save button clicked");
			leadpage.leadsave.click();
			childReport("lead name verified");
			Common.presenceOfElement(driver,leadpage.leadVerify);
			leadpage.lead_Verify(ExcelUtility.readExcel(methodName,TC_Name,"LeadName Verify"));
			childReport("delete button clicked");
			leadpage.deleteButton.click();
			childReport("Record deleted");
			leadpage.alertAccept(driver);
			
	 }
   
	@Test
    @Parameters({"browser","environment"})
	public void accountCreation(String browser,String environment,Method method) throws Exception{
		 String methodName = method.getName(), TC_Name = "TC002";
         parentReport(browser, methodName);
		 login(methodName,environment,browser,TC_Name);
		 
		 accountPage acc_Page=PageFactory.initElements(driver, accountPage.class);
		 childReport("Account tab clicked");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 acc_Page.accountTab.click();
		 childReport("New button clicked");
		 Common.presenceOfElement(driver, acc_Page.newButtonVerify);
		 acc_Page.newButton.click();
		 childReport("AccountName Entered");
		 acc_Page.accountName.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Account Name"));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 childReport("ParentAccount entered");
		 acc_Page.clickOnLookup(ExcelUtility.readExcel(methodName,TC_Name,"ParentAccount"));
		 childReport("Save button clicked");
		 acc_Page.save.click();
		 childReport("ParentAccount Verified");
		 Common.presenceOfElement(driver,acc_Page.parentAccountVerification);
		 acc_Page.parentAccountVerify(ExcelUtility.readExcel(methodName,TC_Name,"ParentAccount Verify"));
	 }
	
	@Test
	@Parameters({"browser","environment"})
	public void contactCreation(String browser,String environment, Method method)throws Exception{
		 String methodName = method.getName(),TC_Name = "TC003";
         parentReport(browser, methodName);
		 login(methodName,environment,browser,TC_Name);
		 
		 contactPage cont_Page=PageFactory.initElements(driver, contactPage.class);
		 childReport("ContactTab clicked");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 cont_Page.contactTab.click();
		 childReport("New button clicked");
		 Common.presenceOfElement(driver,cont_Page.newButtonVerify);
		 cont_Page.newButton.click();
		 childReport("LastName entered");
		 cont_Page.lastName.sendKeys(ExcelUtility.readExcel(methodName,TC_Name,"Last Name"));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 childReport("AccountName entered");
		 cont_Page.clickOnLookup(ExcelUtility.readExcel(methodName,TC_Name,"Account Name"));
		 childReport("Save button clicked");
		 cont_Page.save.click();
		 childReport("AccountNameVerified");
		 Common.presenceOfElement(driver, cont_Page.accountName_Verification);
		 cont_Page.accountNameVerify(ExcelUtility.readExcel(methodName,TC_Name,"AccountName Verify"));
		 childReport("account tab clicked");
		 cont_Page.accountTab.click();
		 childReport("account record selected");
		 cont_Page.account_Data_Deletion(ExcelUtility.readExcel(methodName,TC_Name,"Account Record"));
		 childReport("delete button clicked");
		 cont_Page.deleteButton.click();
		 childReport("Record deleted");
		 cont_Page.alertAccept(driver);
	 }
	@Test
	 @Parameters({"browser","environment"})
	public  void leadTabVerification(String browser,String environment, Method method)throws Exception{
		    String methodName = method.getName(), TC_Name = "TC004";
	        parentReport(browser, methodName);
			login(methodName,environment,browser,TC_Name);
			
			lead_page leadpageverify = PageFactory.initElements(driver,lead_page.class);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			leadpageverify.leadTabVerify();
			
	 }
	@AfterMethod
	@Parameters({"browser"})
	public void tearDown(ITestResult result,String browser) throws Exception{
		 String methodName=result.getMethod().getMethodName();
		 if(result.getStatus()==ITestResult.FAILURE){
			 String res=result.getThrowable().getMessage();
			 Utility.screenshot(driver, methodName+browser,childTest,res);
		 }
		 reporter.endTest(parentTest);
		 this.driver.close();
		 
	}
	
	@AfterSuite
	public void afterSuite(){
		reporter.flush();
		Mail_report.send_report();
	}
	
	
	private  void login(String videoName,String env,String browser,String tcName) throws Exception {
	    try{
	    	String methodName = "login";
	    	if(env.equals("production")){
	    		
	    	 childReport("URL entered");
	    	 driver.get(ExcelUtility.readExcel(methodName,tcName,"URL"));
			 login_page obj=  PageFactory.initElements(driver,login_page.class);
			 childReport("Username entered");
			 obj.txtbx_username.sendKeys(ExcelUtility.readExcel(methodName,tcName,"User name"));
			 childReport("Password entered");
			 obj.txtbx_password.sendKeys(ExcelUtility.readExcel(methodName,tcName,"Password"));
	    	
			 childReport("Login clicked");
			 obj.lnk_login.click();
			 childReport("Login verified");
			 Common.presenceOfElement(driver, obj.by_loginVerify);
	    	}
	    	
		 }
		 catch(Exception ex){
			 throw ex;
		}
	}
	
	private void parentReport(String browser, String methodName) {
		parentTest = reporter.startTest(methodName+browser);
		parentTest.assignCategory(browser);
	}
	private void childReport(String methodName) {
		childTest = reporter.startTest(methodName);
		childTest.log(LogStatus.PASS, methodName);
		parentTest.appendChild(childTest);
	}
}
