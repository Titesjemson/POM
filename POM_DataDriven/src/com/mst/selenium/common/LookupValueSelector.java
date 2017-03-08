package com.mst.selenium.common;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LookupValueSelector {
	public static void clickOnLookup(WebDriver driver, String data, String lookupPath, String valueToSelectPath) throws Exception {

		driver.findElement(By
				.xpath(lookupPath))
				.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				driver.switchTo().frame("resultsFrame");

				WebElement table = driver.findElement(By.xpath(valueToSelectPath));

				List<WebElement> rows = table.findElements(By.tagName("tr"));
				int rowscount = rows.size();

				for (int i = 0; i < rowscount; i++) {
					List<WebElement> columns = rows.get(i).findElements(By.tagName("th"));
					int columnscount = columns.size();

					for (int j = 0; j < columnscount; j++) {
						String var1 = columns.get(j).getText();

						if (data.equals(var1)) {
							columns.get(j).findElement(By.tagName("a")).click();
							i = rowscount + 1;
							j = columnscount + 1;
						}
					}
				}
			}
		}
		driver.switchTo().window(parentWindow);
	}

}
