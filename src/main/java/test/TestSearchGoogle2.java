package test;

import org.testng.annotations.Test;

import pages.HomePage;

public class TestSearchGoogle2 extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_002_Verify_User_Can_Search";

	@Test(priority= 1)
	public void openHomePage() {
		setUpTest(tcName, "This is a Sample Test");
		openUrl("https://www.google.com");	
	}
	
	@Test(priority= 2)
	public void testGoogleSearch() {
		homePage = new HomePage(driver);
		homePage.searchFromGoogle("sample");
	}
	
	@Test(priority= 3)
	public void endTest() {
		closeBrowser();
		
	}
	
	
}
