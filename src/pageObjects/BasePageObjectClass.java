package pageObjects;

import org.openqa.selenium.WebDriver;
/*
 * This must be extended by all pageObject classes
 */
public class BasePageObjectClass {
	public  static WebDriver driver;
	protected  static boolean result;
	
	public BasePageObjectClass(WebDriver driver) {
		BasePageObjectClass.driver = driver;
		BasePageObjectClass.result = true;
	}

}
