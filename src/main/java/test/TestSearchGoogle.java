package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;


public class TestSearchGoogle extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_001_Verify_User_Can_Search";
	
	
	@Test(priority= 1)
	public void openHomePage() {
		setUpTest(tcName, "Test 1");
		openUrl(url);
	}
	
	@Test(priority= 2)
	@Parameters("a")
	public void testGoogleSearch(String text) {	
		if (text==null) {
			text = "test";
		}
		
		homePage = new HomePage(driver);
		homePage.searchFromGoogle(text);
		homePage.verifyText();
		homePage.clickOoklaLink();
		homePage.verifyOoklaUrl();
	}
	
	
	
}