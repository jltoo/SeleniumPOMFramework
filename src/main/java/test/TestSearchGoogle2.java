package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import pages.HomePage;

public class TestSearchGoogle2 extends BaseTest {
	HomePage homePage;
	String tcName = "Reg_TC_002_Verify_User_Can_Search";
	
//	@BeforeClass
//	public void initiateTest() {
//		enviPath = getEnviPath();
//		configFileReader = new ConfigFileReader(enviPath);
//		String url = configFileReader.getApplicationUrl(); 
//	}
	
	
	@Test(priority= 1)
	public void openHomePage() {
	
		setUpTest(tcName, "This is a Sample Test");
		openUrl(url);
	}
	@Test(priority= 2)
	public void testGoogleSearch() {
		homePage = new HomePage(driver);
		homePage.searchFromGoogle("sample");
	}
	
	@AfterClass
	public void endTest() {
		closeBrowser();
		
	}
	
	
}
