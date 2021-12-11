package pageObjects.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BaseAppiumPageObjectClass {
	protected  static  AppiumDriver<MobileElement> driver;
	protected  static boolean result;
	private static final int TIMEOUT = 20;
	
	public BaseAppiumPageObjectClass(AppiumDriver<MobileElement> driver) {
		BaseAppiumPageObjectClass.driver = driver;
		BaseAppiumPageObjectClass.result = true;
//		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(TIMEOUT)), this);
	}
}
