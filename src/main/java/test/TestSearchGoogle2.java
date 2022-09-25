package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;

public class TestSearchGoogle2 extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_002_Verify_User_Can_Search";
	

	@Test(priority= 1)
	@Parameters("b")
	public void testGoogleSearch(String text) {
		setUpTest(tcName, "Test 2");
		openUrl(url);
		if (text==null) {
			text = "test";
		}
		
		homePage = new HomePage(driver);
		homePage.searchFromGoogle(text);
		homePage.verifyText();
//		homePage.clickOoklaLink();
//		homePage.verifyOoklaUrl();
	}
	
	
}
