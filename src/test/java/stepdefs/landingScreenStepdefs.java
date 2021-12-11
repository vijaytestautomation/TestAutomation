package test.java.stepdefs;

import Utilities.Log;
import Utilities.Utils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pageObjects.BasePageObjectClass;
import test.java.BaseTestClassTest;


import static java.lang.Thread.sleep;


public class landingScreenStepdefs extends BaseTestClassTest {
    private static Logger logger = Logger.getLogger(landingScreenStepdefs.class.getName());

    public landingScreenStepdefs() throws Exception {
        super(Utils.getBrowserDriver(Utils.getBrowserName()),"Open application in web browser Testcase");
    }

    @Given("^open web browser$")
    public void openWebBrowser() throws Exception {
        try {
            driver.get(Utils.getApplicationURL());
            logger.info("Successfully launched SBA Application " + Utils.getApplicationURL());
            AddDetailsToReport(1, "Successfully launched Service Booking Application in a device web browser");

        }catch(Exception e) {
            logger.error("Exception in OpenBrowser  " + e.getMessage());
            AddDetailsToReport(2, "Exception in OpenBrowser");
            e.printStackTrace();
            throw(e);
        }
    }

    @When("^If Start Now button are visible$")
    public void ifStartNowButtonAreVisible() throws Exception {
        try{
            System.out.println("Inside When definition");

        }catch(Exception e){
            logger.error("Exception in ifStartNowButtonAreVisible " + e.getMessage());
            AddDetailsToReport(2, "Exception in ifStartNowButtonAreVisible");
            e.printStackTrace();
            throw(e);
        }
    }

    @Then("^Click on Start Now Button$")
    public void clickButton() throws Exception {
        try{
            System.out.println("Inside Then definition");

        }catch(Exception e){
            logger.error("Exception in clickButton  " + e.getMessage());
            AddDetailsToReport(2, "Exception in clickButton");
            e.printStackTrace();
            throw(e);
        }
    }


    @Before
    public void beforeMethod() throws Exception {
        DOMConfigurator.configure("./src/main/resources/log4j.xml");
        Log.startTestCase(testCaseName);
        AddTestToReport("landingScreenStepdefs", "Open application in web browser Testcase");
        new BasePageObjectClass(driver);
    }

    @After
    public void afterMethod() {
        Log.endTestCase(testCaseName);
        FlushReport();
    }



}
