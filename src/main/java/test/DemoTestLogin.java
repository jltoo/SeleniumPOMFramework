package test;

import org.testng.annotations.*;

import pages.DemoInventoryPage;
import pages.DemoLoginPage;
import pages.HomePage;


public class DemoTestLogin extends BaseTest {
    DemoLoginPage demoLoginPage;
    DemoInventoryPage demoInventoryPage;

    @Test(priority= 1)
    @Parameters("a")
    public void TEST_USER_LOGIN(@Optional("Test") String username) {
        tcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        setUpTest(tcName, "Test 1");
        openUrl("https://www.saucedemo.com/?ref=hackernoon.com");
        demoLoginPage = new DemoLoginPage(driver);
        demoInventoryPage = new DemoInventoryPage(driver);

        demoLoginPage.loginUser(username, "secret_sauce");
//        demoLoginPage.loginUser("standard_user", "secret_sauce");
        demoInventoryPage.verifyInventoryPage()
                .clickProductItem();
    }

}