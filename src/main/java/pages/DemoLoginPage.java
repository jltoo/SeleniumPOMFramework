package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.DriverInstance;
import utilities.Enums;

public class DemoLoginPage extends BasePage{

    @FindBy(xpath="//input[@id='login-button']")
    public WebElement loginBtn;


    @FindBy(xpath="//input[@id='user-name']")
    public WebElement  loginUserNameFld;

    @FindBy(xpath="//input[@id='password']")
    public WebElement  loginPswrdFld;


    public DemoLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public DemoLoginPage loginUser(String username, String password) {
        wait.waitUntilElementVisible(loginUserNameFld, 10);
        sendText(loginUserNameFld, username);
        sendText(loginPswrdFld, password);
        click(loginBtn);

        return this;
    }




}
