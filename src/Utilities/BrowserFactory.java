package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	/*
	 * Factory method for getting browsers
	 */

	public static WebDriver getBrowser(String browserName){
		WebDriver driver = null;

		switch (browserName){
			case Constant.FIREFOX_BROWSER:
				driver = drivers.get(Constant.FIREFOX_BROWSER);
				if(driver== null){
					WebDriverManager.firefoxdriver().setup();
					driver= new FirefoxDriver();
					drivers.put(Constant.FIREFOX_BROWSER,driver);
					driver.manage().window().maximize();
				}else{
					driver.navigate().refresh();
				}
				break;

			case Constant.CHROME_BROWSER:
				driver = drivers.get(Constant.CHROME_BROWSER);
				if(driver== null){
					ChromeOptions options = new ChromeOptions();
					options.setAcceptInsecureCerts(true);
					WebDriverManager.chromedriver().setup();
					driver= new ChromeDriver(options);
					drivers.put(Constant.CHROME_BROWSER,driver);
					driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
				}else{
					driver.navigate().refresh();
				}
				break;

			case Constant.Edge_BROWSER:
				driver = drivers.get(Constant.Edge_BROWSER);
				if(driver== null){
					WebDriverManager.edgedriver().setup();
					driver= new EdgeDriver();
					drivers.put(Constant.Edge_BROWSER,driver);
					driver.manage().window().maximize();
				}else{
					driver.navigate().refresh();
				}
				break;

		}
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;

	}

}
