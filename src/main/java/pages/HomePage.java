package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.DriverInstance;

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
	
	@FindBy(xpath="//input[@name='q']")
	public WebElement googleSearchInptField;
	
	@FindBy(xpath="(//input[@name='btnK'])[1]")
	public WebElement googleSearchBtn;
	
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
	
	public void searchFromGoogle(String text) {
		sendText(googleSearchInptField, text);
		click(googleSearchBtn);
	}
	
	public void verifyText() {
		verify.verifyIsElementDisplayed(ooklaLink);
	}
	
	public void clickOoklaLink() {
		scrollToElement(ooklaLink, 2);
		click(ooklaLink);
	}
	
	public void verifyOoklaUrl() {
		verify.verifyUrl("https://www.speedtest.net/");
	}
}
