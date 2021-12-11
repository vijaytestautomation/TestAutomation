package pageObjects.Mobile;


import CRUD.Mobile.LoginMobile;
import Utilities.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class LoginScreenMobilePageObject extends BaseAppiumPageObjectClass {


    public LoginScreenMobilePageObject(AppiumDriver<MobileElement> driver) throws Exception {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    //WebElements
    @AndroidFindBy(xpath="//input[@id='Input_UsernameVal']")
    private AndroidElement Username;

    @AndroidFindBy(xpath="//button[@type='submit']")
    private AndroidElement NextButton;

	@AndroidFindBy(xpath="//input[@id='Input_PasswordVal']")
	private AndroidElement Password;

    @AndroidFindBy(xpath="//input[@id='Checkbox1']")
    private AndroidElement RememberMeCheckbox;

    @AndroidFindBy(xpath="//button[@type='submit']")
    private AndroidElement LoginButton;


    public void EnterCredentials(LoginMobile login) throws InterruptedException {
//        Utils.waitForVisibilityOfMobileElement(driver, By.xpath("//input[@id='Input_UsernameVal']"));
        Utils.findMobileElementSleep(driver,2000);
        Username.clear();
        Username.sendKeys(login.getUsername());
        System.out.println("entered username" + " " + login.getUsername());
        Utils.findMobileElementSleep(driver,2000);
        driver.hideKeyboard();
        Utils.findMobileElementSleep(driver,2000);
        //click next button
        NextButton.click();
        Password.clear();
        Password.sendKeys(login.getPassword());
        System.out.println("entered password" + " " + login.getPassword());
        //uncheck remember me
//        Utils.waitForVisibilityOfMobileElement(driver, By.xpath("//input[@id='Checkbox1']"));
        Utils.findMobileElementSleep(driver,2000);
        RememberMeCheckbox.click();

    }

    public void ClickLoginBtn() throws InterruptedException {
        //click Login button
//        Utils.waitForVisibilityOfMobileElement(driver,By.xpath("//button[@type='submit']"));
        Utils.findMobileElementSleep(driver,2000);
        LoginButton.click();
    }


}
