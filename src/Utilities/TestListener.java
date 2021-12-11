package Utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.java.BaseTestClassTest;


public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
      return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Text attachments for Allure
    @Attachment(value = "screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }


    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + "passed");

        //Get driver from BaseTestClass and assign to local webdriver variable
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTestClassTest) testClass).driver;

        //Allure ScreenShotRobot and SaveTestLog
        if(driver instanceof WebDriver){
            System.out.println("screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure
        saveTextLog(getTestMethodName(iTestResult) + "passed and screenshot taken!");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + "failed");

        //Get driver from BaseTestClass and assign to local webdriver variable
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTestClassTest) testClass).driver;

        //Allure ScreenShotRobot and SaveTestLog
        if(driver instanceof WebDriver){
            System.out.println("screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure
        saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
    }


    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
