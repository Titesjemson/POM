package Page_Object_page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import common.Common;

import exception.CustomException;

public class lead_page {
	
	final WebDriver driver;
	
	 @FindBy(how = How.ID, using = "Lead_Tab")
	 public WebElement lead_tab;
	 
	 public By new_button=By.cssSelector("input[title='New']");
	 
	 @FindBy(how=How.CSS, using ="input[title='New']")
	 public WebElement new_tab;
	 
	 @FindBy(how = How.ID, using = "name_salutationlea2")
	 public WebElement salutation;
	 
	 @FindBy(how = How.ID, using = "name_firstlea2")
	 public WebElement firstname;
	 
	 @FindBy(how = How.ID, using = "name_lastlea2")
	 public WebElement lastname;
	 
	 @FindBy(how = How.ID, using = "lea3")
	 public WebElement company;
	 
	 @FindBy(how = How.ID, using = "lea16street")
	 public WebElement street;
	 
	 @FindBy(how = How.ID, using ="lea16city")
	 public WebElement city;
	 
	 @FindBy(how = How.ID, using = "lea16state")
	 public WebElement state;
	 
	 @FindBy(how = How.ID, using = "lea16zip")
	 public WebElement zip;
	 
	 @FindBy(how = How.ID, using = "lea16country")
	 public WebElement country;
	 
	 @FindBy(how = How.ID, using = "lea13")
	 public WebElement leadstatus;
	 
	 @FindBy(how = How.XPATH, using ="html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[1]/table/tbody/tr/td[2]/input[1]")
	 public WebElement leadsave;
	 
	 @FindBy(how = How.CSS, using = ".btn[title='Delete']")
	 public  WebElement deleteButton;
	 
	 public By leadVerify = By.cssSelector("#lea2_ileinner");
	
	
	 
	 public lead_page(WebDriver driver){
		 this.driver=driver;
	 }
	 
	 public void select_list(String salutation){
	      Select element=new Select(driver.findElement(By.id("name_salutationlea2")));
	      element.selectByVisibleText(salutation);
	 }
	 public void leadstatus_dropdown(String leadstatus){
		 Select element1 = new Select(driver.findElement(By.id("lea13")));
		 element1.selectByVisibleText(leadstatus);
	 }
	 public void lead_Verify(String expected) throws Exception{
		 try{
	   
		 WebElement element = driver.findElement(By.id("lea2_ileinner"));
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
	 public void alertAccept(WebDriver driver) throws Exception{

			Alert confirmationAlert = driver.switchTo().alert();
			String text = confirmationAlert.getText();
			System.out.println("Alert text is " + text);
			confirmationAlert.accept();
		}
	 
	 public boolean leadTabVerify() throws Exception{
	 		boolean ele=true;
	 		try{
	 		WebElement element =driver.findElement(By.id("Lead_Tab"));
	 		
	 		if(element.isDisplayed()){
	 			throw new CustomException("Lead tab should not be displayed");
	 			}
	 		else{
	 			System.out.println("Leadtab not present");
	 			ele=true;
	 		}
	 	 }
	 	 catch(Exception ex){
	 			throw ex;
	 		}
	 		return ele;
	 	 } 


}
