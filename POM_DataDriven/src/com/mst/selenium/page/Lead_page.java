package com.mst.selenium.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.mst.selenium.common.Common;
import com.mst.selenium.excelutility.ExcelUtility;
import com.mst.selenium.exception.CustomException;
import com.mst.selenium.extentreport.ReportGenerator;

public class Lead_page {

	final WebDriver driver;

	@FindBy(how = How.ID, using = "Lead_Tab")
	public WebElement lead_tab;

	public By new_button = By.cssSelector("input[title='New']");

	@FindBy(how = How.CSS, using = "input[title='New']")
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

	@FindBy(how = How.ID, using = "lea16city")
	public WebElement city;

	@FindBy(how = How.ID, using = "lea16state")
	public WebElement state;

	@FindBy(how = How.ID, using = "lea16zip")
	public WebElement zip;

	@FindBy(how = How.ID, using = "lea16country")
	public WebElement country;

	@FindBy(how = How.ID, using = "lea13")
	public WebElement leadstatus;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[1]/table/tbody/tr/td[2]/input[1]")
	public WebElement leadsave;

	@FindBy(how = How.CSS, using = ".btn[title='Delete']")
	public WebElement deleteButton;

	public By leadVerify = By.cssSelector("#lea2_ileinner");

	public Lead_page(WebDriver driver) {
		this.driver = driver;
	}

	public void select_list(String salutation) {
		Select element = new Select(driver.findElement(By.id("name_salutationlea2")));
		element.selectByVisibleText(salutation);
	}

	public void leadstatus_dropdown(String leadstatus) {
		Select element1 = new Select(driver.findElement(By.id("lea13")));
		element1.selectByVisibleText(leadstatus);
	}

	public WebElement getLeadName() throws Exception {
		WebElement element = null;
		try {

			element = driver.findElement(By.id("lea2_ileinner"));
		} catch (Exception E) {
		}
		return element;
	}

	public void alertAccept(WebDriver driver) throws Exception {

		Alert confirmationAlert = driver.switchTo().alert();
		String text = confirmationAlert.getText();
		System.out.println("Alert text is " + text);
		confirmationAlert.accept();
	}

	public boolean isLeadTabDisplayed() throws Exception {
		boolean isLeadTabDisplayed = false;
		try {
			WebElement element = driver.findElement(By.id("Lead_Tab"));
			isLeadTabDisplayed = element.isDisplayed();
		} catch (Exception ex) {

		}
		return isLeadTabDisplayed;
	}

	public void createNewLeadAction(Lead_page leadpage, ReportGenerator reporter, String methodName, String TC_Name)
			throws Exception {
		reporter.childReport("LeadTab clicked");
		leadpage.lead_tab.click();
		reporter.childReport("New button clicked");
		Common.presenceOfElement(driver, leadpage.new_button);
		leadpage.new_tab.click();
		reporter.childReport("Salutation entered");
		leadpage.select_list(ExcelUtility.readExcel(methodName, TC_Name, "Salutation"));
		reporter.childReport("FirstName entered");
		leadpage.firstname.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "First Name"));
		reporter.childReport("LastName entered");
		leadpage.lastname.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Last Name"));
		reporter.childReport("Company name entered");
		leadpage.company.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Company"));
		reporter.childReport("Street name entered");
		leadpage.street.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Street"));
		reporter.childReport("city name entered");
		leadpage.city.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "City"));
		reporter.childReport("State name entered");
		leadpage.state.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "State"));
		reporter.childReport("zip code entered");
		leadpage.zip.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Zip"));
		reporter.childReport("country name entered");
		leadpage.country.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Country"));
		reporter.childReport("Lead status selected");
		leadpage.leadstatus_dropdown(ExcelUtility.readExcel(methodName, TC_Name, "Lead Status"));
		reporter.childReport("Save button clicked");
		leadpage.leadsave.click();

	}

	public void deleteLeadAction(Lead_page leadpage, ReportGenerator reporter) throws Exception {
		reporter.childReport("delete button clicked");
		leadpage.deleteButton.click();
		reporter.childReport("Record deleted");
		leadpage.alertAccept(driver);
	}
}
