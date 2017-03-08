package com.mst.selenium.page;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

public class AccountPage {
	static WebDriver driver;

	@FindBy(how = How.ID, using = "Account_Tab")
	public WebElement accountTab;

	@FindBy(how = How.CSS, using = "input[title='New']")
	public WebElement newButton;

	@FindBy(how = How.ID, using = "acc2")
	public WebElement accountName;

	@FindBy(how = How.CSS, using = "input[title='Save']")
	public WebElement save;

	public By newButtonVerify = By.cssSelector("input[title='New']");
	public By parentAccountVerification = By.id("acc3_ileinner");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnLookup(String data) throws Exception {
		LookupValueSelector.clickOnLookup(driver, data,
				"html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[2]/div[3]/table/tbody/tr[3]/td[2]/span/a/img",
				"html/body/form/div/div[2]/div/div[2]/table/tbody");
	}

	public WebElement getParentAccount(String expected) throws Exception {
		WebElement element = null;
		try {

			element = driver.findElement(By.id("acc3_ileinner"));

		} catch (Exception E) {
		}
		return element;
	}

	public void createNewAccount(ReportGenerator reporter, String methodName, String TC_Name, AccountPage accPage)
			throws Exception {
		reporter.childReport("Account tab clicked");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accPage.accountTab.click();

		reporter.childReport("New button clicked");
		Common.presenceOfElement(driver, accPage.newButtonVerify);
		accPage.newButton.click();

		reporter.childReport("AccountName Entered");
		accPage.accountName.sendKeys(ExcelUtility.readExcel(methodName, TC_Name, "Account Name"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		reporter.childReport("ParentAccount entered");
		accPage.clickOnLookup(ExcelUtility.readExcel(methodName, TC_Name, "ParentAccount"));

		reporter.childReport("Save button clicked");
		accPage.save.click();
	}

}
