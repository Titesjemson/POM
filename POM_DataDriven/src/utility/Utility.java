
/**
 * 
 */
package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import constant.Constant;


/**
 * This class contains the utility methods like screenshot.
 *
 */
public class Utility {

	public static void screenshot(WebDriver screenDriver, String testCaseName, ExtentTest logger,String res) throws IOException{
		try{
			File file  = ((TakesScreenshot)screenDriver).getScreenshotAs(OutputType.FILE);
			File dir = new File(Constant.screenshotPath+testCaseName);
			dir.mkdirs();
						
			String fileName= Constant.screenshotPath+testCaseName+"/"+testCaseName+".jpg";
			FileUtils.copyFile(file, new File(fileName));
			
			String img = logger.addScreenCapture(fileName);
		    logger.log(LogStatus.FAIL, "Image", res+img);
		}
		catch(Exception ex){
			System.out.println("Exception while taking screen  shot");
		}		
	}
	
	
}



