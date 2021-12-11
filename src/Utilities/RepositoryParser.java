package Utilities;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;


public class RepositoryParser {

	private String repositoryFileName = null;
	private Properties repositoryFile = new Properties();

	public RepositoryParser(String fileName) throws IOException
	{
		if (this.repositoryFileName == null) {
			this.repositoryFileName = fileName;
			repositoryFile.load(getClass().getResourceAsStream(repositoryFileName));
		}
		
	}

	public By getObjectLocator(String elementName)
	{
		String locatorProperty = repositoryFile.getProperty(elementName);
		System.out.println(locatorProperty.toString());
		String locatorType = locatorProperty.split(":")[0];
		String locatorValue = locatorProperty.split(":")[1];

		By locator = null;
		switch(locatorType)
		{
		case Constant.LOCATOR_ID:
			locator = By.id(locatorValue);
			break;
		case Constant.LOCATOR_NAME:
			locator = By.name(locatorValue);
			break;
		case Constant.LOCATOR_CSSSELECTOR:
			locator = By.cssSelector(locatorValue);
			break;
		case Constant.LOCATOR_LINKTEXT:
			locator = By.linkText(locatorValue);
			break;
		case Constant.LOCATOR_PARTIALLINKTEXT:
			locator = By.partialLinkText(locatorValue);
			break;
		case Constant.LOCATOR_TAGNAME:
			locator = By.tagName(locatorValue);
			break;
		case Constant.LOCATOR_XPATH:
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}
