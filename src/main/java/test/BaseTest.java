package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.BaseHelper;

public class BaseTest extends BaseHelper {
	@BeforeSuite
	public void initializeExtentReport() {
		initializeReport();

	}
	
	@AfterSuite
	public void flushReport() {
		extent.flush();
	}
}
