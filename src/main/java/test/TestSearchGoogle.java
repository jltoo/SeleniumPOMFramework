package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import pages.HomePage;

public class TestSearchGoogle extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_001_Verify_User_Can_Search";
	
	
	@Test(priority= 1)
	public void openHomePage() {
		setUpTest(tcName, "This is a Sample Test");
		openUrl(url);
	}
	
	@Test(priority= 2)
	public void testGoogleSearch() {
		homePage = new HomePage(driver);
		homePage.searchFromGoogle("test");
		homePage.verifyText();
	}
	
	@AfterClass
	public void endTest() {
		closeBrowser();
		
	}
	
	
}