package pageObjects;


import CRUD.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginScreenPageObject extends BasePageObjectClass {

    public LoginScreenPageObject(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //WebElements
    @FindBy(xpath="//input[@name='email']")
    private WebElement Username;

    @FindBy(xpath="//input[@name='password']")
    private WebElement Password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement SignIn;


    public void EnterCredentials(Login login){
        Username.clear();
        Username.sendKeys(login.getUsername());
        System.out.println("entered username" + " " + login.getUsername());
        Password.clear();
        Password.sendKeys(login.getPassword());
        System.out.println("entered password" + " " + login.getPassword());
    }

    public void ClickSignInBtn(){
        //click SignIn button
        SignIn.click();
    }


}
