package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import utilities.DriverInstance;

public class TestLogin extends utilities.BaseHelper {
	HomePage homePage;
	String tcName = "Reg_TC_002_Verify_User_Can_Login";

	@BeforeTest
	public void openHomePage() {
		setUpTest(tcName, "This is a Sample Test");
		openUrl("https://www.cebupacificair.com");	
	}
	
	@Test
	public void closePopup() {
		homePage = new HomePage(DriverInstance.getWebDriver());
		homePage.clickClosePopupBtn();
		homePage.loginUser("JohnLovie.Too@cebupacificair.com", "P@$$W0rd1_2019");
	}
	
	
	@AfterTest
	public void endTest() {
		closeBrowser();
		
	}
	
}
