package pageObjects.Mobile;

import Utilities.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AppiumSmarterWaterPageObjects extends BaseAppiumPageObjectClass {
	private static MobileElement element = null;
	private static Logger logger = Logger.getLogger(AppiumSmarterWaterPageObjects.class.getName());

	public AppiumSmarterWaterPageObjects(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public static MobileElement getMobileElement(String elementName) throws Exception{
		try {
			Utils.waitForVisibilityOfTheElement(driver, AppiumSmarterWaterPageObjects.getMobileObjectLocator(elementName));
			element = driver.findElement(Utils.getRepositoryParser().getObjectLocator(elementName));
			return element;
		} catch(Exception e) {
			logger.error("Failed to locate element " + elementName + e.getMessage());
			e.printStackTrace();
			throw(e);
		}
	}
	
	public static MobileElement getMobileElementStraight(String elementName) throws Exception{
		try {
			element = driver.findElement(Utils.getRepositoryParser().getObjectLocator(elementName));
			return element;
		} catch(Exception e) {
			logger.error("Failed to locate element " + elementName + e.getMessage());
			e.printStackTrace();
			throw(e);
		}
	}
	
	public static By getMobileObjectLocator(String elementName) throws Exception{
		try {
			return Utils.getRepositoryParser().getObjectLocator(elementName);
		} catch(Exception e) {
			logger.error("Failed to find the object locator for  element " + elementName + e.getMessage());
			e.printStackTrace();
			throw(e);
		}
	}

}
