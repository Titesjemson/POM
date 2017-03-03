package com.mst.selenium.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mst.selenium.common.Common;
import com.mst.selenium.excelutility.ExcelUtility;
import com.mst.selenium.extentreport.ReportGenerator;

public class Login_page {
	
	final WebDriver driver;
	
	 @FindBy(how = How.ID, using = "username")
	 public WebElement txtbx_username;

	 @FindBy(how = How.ID, using = "password")
	 public WebElement txtbx_password;

	 @FindBy(how = How.ID, using = "Login")
	 public WebElement lnk_login;
	 
	 @FindBy(how = How.ID, using = "Account_Tab")
	 public WebElement loginVerify;
	 
	 
	 public By by_loginVerify = By.id("Account_Tab"); 
	 public Login_page(WebDriver driver){ 
		 
		    this.driver = driver; 
		 
		    } 
	 	    
		public static void performLoginAction(WebDriver driver, String env,  String browser,String tcName, ReportGenerator generator) throws Exception {
		    try{
		    	String methodName = "login";
		    	if (env.equals("production")){
		    	 generator.childReport("URL entered");
		    	 driver.get(ExcelUtility.readExcel(methodName,tcName, "URL"));
				 Login_page obj=  PageFactory.initElements(driver,Login_page.class);
				 generator.childReport("Username entered");
				 obj.txtbx_username.sendKeys(ExcelUtility.readExcel(methodName,tcName,"User name"));
				 generator.childReport("Password entered");
				 obj.txtbx_password.sendKeys(ExcelUtility.readExcel(methodName,tcName,"Password"));
		    	
				 generator.childReport("Login clicked");
				 obj.lnk_login.click();
				 generator.childReport("Login verified");
				 Common.presenceOfElement(driver, obj.by_loginVerify);
		    	}
			 }
			 catch(Exception ex){
				 throw ex;
			}
		    	 
		}

	 }






