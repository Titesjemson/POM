package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import constant.Constant;
import exception.CustomException;

public class BrowserFactory {

	 static WebDriver driver;
	
     static ChromeOptions options;
    
     public static WebDriver getDriver(String browser){
        if(browser.equals("chrome")){
               System.setProperty("webdriver.chrome.driver",Constant.chromeDriver);
               options = new ChromeOptions();
               options.addArguments("--disable-extensions");
               driver =new ChromeDriver(options);
        }
        else if(browser.equals("firefox")){
               driver = new FirefoxDriver();
              
        }
        else{
               throw new CustomException("Browser is not correct");
        }
        return driver;
     }
}
