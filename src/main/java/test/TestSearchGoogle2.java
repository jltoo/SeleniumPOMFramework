package test;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;

public class TestSearchGoogle2 extends BaseTest {
	HomePage homePage;
	String tcName;

	@Test(priority= 1)
	@Parameters("b")
	public void TC_001_Verify_User_Can_Search_Text_To_Google(@Optional("test") String text) {
		tcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		setUpTest(tcName, "Test 2");
		openUrl(url);
		
		homePage = new HomePage(driver);
		homePage.searchFromGoogle(text)
		.verifyText()
		.clickOoklaLink()
		.verifyOoklaUrl();
	}

	@Test(priority= 2)
	@Parameters("a")
	public void TC_002_Verify_User_Can_Search_Text_To_Google(@Optional("Test") String text) {
		tcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		setUpTest(tcName, "Test 1");
		openUrl(url);

		homePage = new HomePage(driver);
		homePage.searchFromGoogle(text)
		.verifyText()
		.clickOoklaLink()
		.verifyOoklaUrl();
	}
	
	
}
