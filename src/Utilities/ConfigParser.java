package Utilities;


import java.io.IOException;
import java.util.Properties;


public class ConfigParser {
	private String configFileName = null;
	private Properties configFile = new Properties();

	public ConfigParser(String fileName) throws IOException
	{
		if (this.configFileName == null) {
			this.configFileName = fileName;
			configFile.load(getClass().getResourceAsStream(configFileName));
			
		}
		
	}
	
	public String getPropertyValue(String key) {
		String propValue = configFile.getProperty(key);
		return propValue;
	}

}
