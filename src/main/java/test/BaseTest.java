package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import propertyfilereader.ConfigFileReader;
import utilities.BaseHelper;

@Listeners (listener.TestNGListener.class)
public class BaseTest extends BaseHelper {
	public static String url;
	ConfigFileReader configFileReader;
	private static String enviPath;
	private static int testCount = 0;
	private static int failedTestCount = 0;
	private static long startingTime = 0;
	
	
	@BeforeSuite
	public void initializeTestExecution() {
		startingTime = System.currentTimeMillis();
		initializeReport();
		enviPath = getEnviPath();
		configFileReader = new ConfigFileReader(enviPath);
		url = configFileReader.getApplicationUrl();
	}
	
	@BeforeClass
	public void setupTest(){
		testCount = testCount + 1;
		isFailed = false;
	}
	
	@AfterClass
	public void endTest() {
		closeBrowser();
		
		if (isFailed == true ) {
			failedTestCount = 1; 
		}
		
	}
	
	
	@AfterSuite
	public void endTestExecution() {
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startingTime;
        int hours = (int) ((runTime / (1000*60*60)) % 24);
        int minutes = (int) ((runTime / (1000 * 60)) % 60);
        int seconds = (int) (runTime / 1000) % 60;
		int pass = testCount - failedTestCount;

		logBuilder.info("*********************************************************");
		logBuilder.info("Passed TestCases : " + pass);
		logBuilder.info("Failed TestCases : " + failedTestCount);
		logBuilder.info("Total  TestCases : " + testCount);
		logBuilder.info("Total  Execution Time : " + hours + " hours : " + minutes + " minutes : " + seconds + " seconds");
		logBuilder.info("*********************************************************");
		logBuilder.info("Test Completed");
		logBuilder.endTestCase();
		extent.flush();
	}
}
