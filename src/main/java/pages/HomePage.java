package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.DriverInstance;
import utilities.Enums;

public class HomePage extends BasePage{
	@FindBy(xpath="//div[@class='home-header__account']//a[contains(.,'Log in')]")
	public WebElement loginLinkNav;
	
	@FindBy(xpath="//button[contains(.,'Log in')]")
	public WebElement loginBtn;
	
	@FindBy(xpath="//a[@class='close-button']")
	public WebElement closePopupBtn;
	
	@FindBy(xpath="//input[@type='email']")
	public WebElement  loginEmailFld;
	
	@FindBy(xpath="//input[@type='password']")
	public WebElement  loginPswrdFld;
	
	@FindBy(xpath="//div[contains(@class,'c-modal__ciam__signin')]//button[text()=' Log in ']")
	public WebElement loginModalLoginBtn;
	
	@FindBy(xpath="//*[@name='q']")
	public WebElement googleSearchInptField;
	
	@FindBy(xpath="(//input[@name='btnK'])[1]")
	public WebElement googleSearchBtn;

	@FindBy(xpath="//*[text()='Filipino']")
	public WebElement filipinoLinkTxt;
	
	@FindBy(xpath="//h3[text()='Speedtest by Ookla - The Global Broadband Speed Test']")
	public WebElement ooklaLink;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickClosePopupBtn() {
		wait.waitUntilElementClickable(closePopupBtn, 60);
		click(closePopupBtn);
	}
	
	public void clickLoginBtn() {
		mouseHoverToElement(loginLinkNav, 5);
		click(loginBtn);
	}
	
	public void loginUser(String username, String password) {
		wait.waitUntilElementVisible(loginEmailFld, 10);
		sendText(loginEmailFld, username);
		sendText(loginPswrdFld, password);
		
	}
	
	public void clickLoginBtnInLoginModal() {
		click(loginModalLoginBtn);
	}
	
	public HomePage searchFromGoogle(String text) {
//		sendText(googleSearchInptField, text);
		sendTextThenPressEnter(googleSearchInptField,text,1);
		waitTime(2);
//		verify.verifyElementText(filipinoLinkTxt, Enums.HomePage.filipinoLinkTxt.label);
		click(googleSearchBtn);
		return this;
	}
	
	public HomePage verifyText() {
		verify.verifyElementDisplayed(ooklaLink);
		return this;
	}
	
	public HomePage clickOoklaLink() {
		scrollToElement(ooklaLink, 2);
		click(ooklaLink);
		return this;
	}
	
	public HomePage verifyOoklaUrl() {
		verify.verifyUrl("https://www.speedtest.net/");
		return this;
	}
}
