package com.mst.selenium.page;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.mst.selenium.common.Common;
import com.mst.selenium.common.LookupValueSelector;
import com.mst.selenium.excelutility.ExcelUtility;
import com.mst.selenium.exception.CustomException;
import com.mst.selenium.extentreport.ReportGenerator;

public class ContactPage {
	static WebDriver driver;

	@FindBy(how = How.ID, using = "Contact_Tab")
	public WebElement contactTab;

	@FindBy(how = How.CSS, using = "input[title='New']")
	public WebElement newButton;

	@FindBy(how = How.ID, using = "name_lastcon2")
	public WebElement lastName;

	@FindBy(how = How.CSS, using = "input[title='Save']")
	public WebElement save;

	@FindBy(how = How.CSS, using = "a[title^=Accounts]")
	public WebElement accountTab;

	@FindBy(how = How.CSS, using = ".btn[title='Delete']")
	public WebElement deleteButton;

	public By by_loginVerify = By.id("Account_Tab");

	public By newButtonVerify = By.cssSelector("input[title='New']");
	public By accountName_Verification = By.id("con4_ileinner");

	@FindBy(how = How.ID, using = "con4_ileinner")
	public WebElement accountName;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnLookup(String data) throws Exception {
		LookupValueSelector.clickOnLookup(driver, data,
				"html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[2]/div[3]/table/tbody/tr[4]/td[2]/span/a/img",
				"html/body/form/div/div[2]/div/div[2]/table/tbody");
	}

	public void accountNameVerify(String expected) throws Exception {
		try {
			WebElement element = driver.findElement(By.id("con4_ileinner"));
			String actual = element.getText();
			if (actual.equals(expected)) {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Common.highLightElement(element, driver);
			} else {
				Common.highLightFailedElement(element, driver);
				throw new CustomException("The value " + actual + "is not matched with the given value " + expected);
			}
		} catch (Exception E) {
			throw E;
		}
	}

	public void alertAccept(WebDriver driver) throws Exception {

		Alert confirmationAlert = driver.switchTo().alert();
		String text = confirmationAlert.getText();
		System.out.println("Alert text is " + text);
		confirmationAlert.accept();
	}

	public void createContact(ReportGenerator reporter, String methodName, String TC_Name, ContactPage contPage)
			throws Exception {
		reporter.childReport("ContactTab clicked");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		contPage.contactTab.click();
		reporter.childReport("New button clicked");
		Common.presenceOfElement(driver, contPage.newButtonVerify);
		contPage.newButton.click();
		reporter.childReport("LastName entered");
		contPage.lastName.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Last Name"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reporter.childReport("AccountName entered");
		contPage.clickOnLookup(ExcelUtility.readExcel(methodName, TC_Name, "Account Name"));
		reporter.childReport("Save button clicked");
		contPage.save.click();
		reporter.childReport("AccountNameVerified");
		Common.presenceOfElement(driver, contPage.accountName_Verification);
		contPage.accountNameVerify(ExcelUtility.readExcel(methodName, TC_Name, "AccountName Verify"));
		reporter.childReport("account clicked");
		contPage.accountName.click();
		deleteContact(reporter, contPage);
	}

	private void deleteContact(ReportGenerator reporter, ContactPage contPage) throws Exception {
		reporter.childReport("delete button clicked");
		contPage.deleteButton.click();
		reporter.childReport("Record deleted");
		contPage.alertAccept(driver);
	}

}