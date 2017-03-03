package com.mst.selenium.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mst.selenium.constant.Constant;

import atu.testrecorder.ATUTestRecorder;

public class Common {
	static ATUTestRecorder recorder;
	public static WebElement presenceOfElement(WebDriver driver,By by){
        return new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(by));
 }

 
 public static void highLightElement(WebElement element,WebDriver driver) throws InterruptedException{
		scroll(element,driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.background='Green'", element);
		TimeUnit.MILLISECONDS.sleep(500);
		js.executeScript("arguments[0].style.background=''", element);
	}
 public static void highLightFailedElement(WebElement element,WebDriver driver) throws InterruptedException{
		scroll(element,driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.background='Red'", element);
		TimeUnit.MILLISECONDS.sleep(500);
		js.executeScript("arguments[0].style.background=''", element);
	}
	
public static void scroll(WebElement element,WebDriver driver){
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

}
