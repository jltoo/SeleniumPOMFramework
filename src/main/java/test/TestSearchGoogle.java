package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import utilities.DriverInstance;

public class TestSearchGoogle extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_001_Verify_User_Can_Search";

	@Test(priority= 1)
	public void openHomePage() {
		setUpTest(tcName, "This is a Sample Test");
		openUrl("https://www.google.com");	
	}
	
	@Test(priority= 2)
	public void testGoogleSearch() {
		homePage = new HomePage(driver);
		homePage.searchFromGoogle("test");
	}
	
	@Test(priority= 3)
	public void endTest() {
		closeBrowser();
		
	}
	
	
}
