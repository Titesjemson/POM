package Page_Object_page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class login_page {
	
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
	 public login_page(WebDriver driver){ 
		 
		    this.driver = driver; 
		 
		    } 
	 }






