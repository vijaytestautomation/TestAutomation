package test.java;

import Utilities.Utils;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Utilities.Utils.getConfigParser;
import static java.util.logging.Level.*;

public class BaseTestClassTest {
	public  WebDriver driver;
	protected String testCaseName;

	static long time = System.currentTimeMillis();
	static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter( System.getProperty("user.dir") + "\\src\\test\\Reports\\HtmlReports\\SBAReport-" + time + ".html");
	static ExtentReports testReport = new ExtentReports();
	static ExtentTest eTest;
	ATUTestRecorder atuTestRecorder;
	
	public BaseTestClassTest(WebDriver driver,String testcaseName) {
		this.driver = driver;
		this.testCaseName = testcaseName;
	}

	protected void AddTestToReport(String testName, String description) throws Exception {

			testReport.attachReporter(htmlReporter);
			eTest = testReport.createTest(testName, description);
			htmlReporter.config().setDocumentTitle("Automation Test Results");
			htmlReporter.config().setReportName("Test Environment");
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

	}

	public void AddDetailsToReport(int status, String description) throws Exception {

		switch (status){
			case 1: eTest.pass(description);
				break;
			case 2: eTest.fail(description + eTest.addScreenCaptureFromPath(captureScreen()));
				break;
			case 3: eTest.warning(description);
				break;
			case 4: eTest.error(description);
				break;
			case 9: eTest.log(Status.INFO, description);
				break;
			case 5: eTest.info(description);
				break;
		}
	}

	protected void FlushReport(){
		testReport.flush();
	}

	/* Code to handle Javascript errors on screen*/
	public void ExtractJSLogs() throws Exception {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		if (logEntries.getAll().isEmpty()){
			System.out.println("Console output from browser:" + logEntries );
			AddDetailsToReport(1, "No Javascript errors");
		}
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			if(entry.getLevel() == SEVERE) {
				AddDetailsToReport(4, "Javascript Information on WebPage are: " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}else if(entry.getLevel() == FINE){
				AddDetailsToReport(1, "FINE Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}else if(entry.getLevel() == WARNING){
				AddDetailsToReport(3, "WARNING Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + WARNING + " " + entry.getMessage());
			}else if(entry.getLevel() == INFO){
				AddDetailsToReport(9, "INFO Javascript Logs on WebPage are: " + new Date(entry.getTimestamp()) + " " + INFO + " " + entry.getMessage());
			}
		}
	}

	public String captureScreen() throws IOException, Exception {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") +getConfigParser().getPropertyValue("Savescreenshotpath") + getConfigParser().getPropertyValue("Savescreenshotfileextension");
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}


	public void VideoRecorderSetUp() throws ATUTestRecorderException {

		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		atuTestRecorder =new ATUTestRecorder( System.getProperty("user.dir") + "\\src\\test\\Reports\\Videos","TestVideo-"+dateFormat.format(date), false);
		atuTestRecorder.start();

	}

	public void VideoRecorderTeardown() throws ATUTestRecorderException {

		atuTestRecorder.stop();
	}

}
