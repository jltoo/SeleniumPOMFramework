package test;

import org.testng.annotations.*;

import pages.HomePage;


public class TestSearchGoogle extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_001_Verify_User_Can_Search";

	@Test(priority= 1)
	@Parameters("a")
	public void testGoogleSearch(@Optional("Test") String text) {
		setUpTest(tcName, "Test 1");
		openUrl(url);

		homePage = new HomePage(driver);
		homePage.searchFromGoogle(text);
		homePage.verifyText();
		homePage.clickOoklaLink();
		homePage.verifyOoklaUrl();
	}
	
	
	
}