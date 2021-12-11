package Utilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;


public class Utils {
	private static RepositoryParser repParser = null;
	private static ConfigParser configParser = null;
	private static Logger logger = Logger.getLogger(Utils.class.getName());

	public static RepositoryParser getRepositoryParser() throws Exception {
		if (repParser == null) {
			repParser = new RepositoryParser("ObjectRepo.properties");
			logger.info("Successfully initialised Repository parser");
		} 
		return repParser;
	}
	
	public static WebDriver getBrowserDriver(String browserName) {
		return BrowserFactory.getBrowser(browserName);
	}


	public static void waitForVisibilityOfTheElement(WebDriver driver,By element) {
		WebDriverWait waitButton = new WebDriverWait(driver, 30);
		waitButton.until(ExpectedConditions.visibilityOfElementLocated(element));
		
	}
	
	public static void waitForPresenceOfTheElement(WebDriver driver,By element) {
		WebDriverWait waitButton = new WebDriverWait(driver, 30);
		waitButton.until(ExpectedConditions.presenceOfElementLocated(element));
		
	}
	
	
	public static ConfigParser getConfigParser() throws Exception {
		if (configParser == null) {
			configParser = new ConfigParser("/Utilities/config.properties");
			logger.info("Successfully initialised Config parser");
		} 
		return configParser;
	}
	
	public static String getBrowserName() throws Exception {
		return getConfigParser().getPropertyValue("BrowserName");
	}
	
	public static String getApplicationURL() throws Exception {
		return getConfigParser().getPropertyValue("ApplicationBaseURL");
	}


	public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{
		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + getConfigParser().getPropertyValue("Savescreenshotpath") + sTestCaseName + getConfigParser().getPropertyValue("Savescreenshotfileextension")));
		} catch (Exception e){
			logger.error("Class Utils | Method takeScreenshot | Exception occurred while capturing ScreenShot for testcase :"
					+ sTestCaseName + " Error:  " +e.getMessage());
			throw new Exception();
		}

	}
}
