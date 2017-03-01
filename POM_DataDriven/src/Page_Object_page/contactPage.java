package Page_Object_page;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.Common;

import exception.CustomException;

public class contactPage {
	static WebDriver driver;
	
	@FindBy(how = How.ID, using = "Contact_Tab")
	public WebElement contactTab;
	
	@FindBy(how = How.CSS, using ="input[title='New']")
	public WebElement newButton;
	 
	@FindBy(how = How.ID, using = "name_lastcon2")
	public WebElement lastName;
	
	
	@FindBy(how = How.CSS, using = "input[title='Save']")
	public WebElement save;
	
	public By newButtonVerify = By.cssSelector("input[title='New']");
	public By accountName_Verification = By.id("con4_ileinner");
	

	 public contactPage(WebDriver driver){
		 this.driver=driver;
	 }
	
	 public void clickOnLookup(String data)throws Exception{
		   
		    driver.findElement(By.xpath("html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[2]/div[3]/table/tbody/tr[4]/td[2]/span/a/img")).click();
			String parentWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			   for(String windowHandle  : handles)
			       {
			       if(!windowHandle.equals(parentWindow))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame("resultsFrame");
		              
			          WebElement table = driver.findElement(By.xpath("html/body/form/div/div[2]/div/div[2]/table/tbody")); 

			          List<WebElement> rows = table.findElements(By.tagName("tr"));
			          int rowscount = rows.size();
			          
			          for (int i=0; i<rowscount; i++){
			          List<WebElement> columns = rows.get(i).findElements(By.tagName("th"));
			          int columnscount = columns.size();
			         
			          
			          for (int j=0; j<columnscount; j++){
			          String var1=columns.get(j).getText();
			        
			          if(data.equals(var1)){
			        	columns.get(j).findElement(By.tagName("a")).click();
	                      i= rowscount + 1;
	                      j=columnscount+1;
			          }
			         }
			       }
			     }
			   }
			   driver.switchTo().window(parentWindow);
		}
	
	 public void accountNameVerify(String expected) throws Exception{
		 try{
			 WebElement element = driver.findElement(By.id("con4_ileinner"));
		     String actual = element.getText();
		     if(actual.equals(expected)){
		    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		         Common.highLightElement(element,driver);
		     }
		     else{
		    	 Common.highLightFailedElement(element, driver);
		     	 throw new CustomException("The value "+actual+"is not matched with the given value "+expected);
		     }
		 }
		 catch(Exception E){
			 throw E;
		 }
	 }
}
		     
			 
	   
	  
     
         
  
    
     	 
     
     
     	
     