package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import propertyfilereader.ConfigFileReader;
import utilities.BaseHelper;

public class BaseTest extends BaseHelper {
	public static String url;
	ConfigFileReader configFileReader;
	private static String enviPath;
	
	
	@BeforeSuite
	public void initializeExtentReport() {
		initializeReport();
		enviPath = getEnviPath();
		configFileReader = new ConfigFileReader(enviPath);
		url = configFileReader.getApplicationUrl(); 
	}
	
	@AfterSuite
	public void flushReport() {
		extent.flush();
	}
}
