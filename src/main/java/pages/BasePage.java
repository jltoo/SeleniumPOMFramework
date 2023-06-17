package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.DriverInstance;
import utilities.Verify;
import utilities.Wait;

public class BasePage extends utilities.BaseHelper{
	WebDriver driver;
	WebElement element;
	Verify verify = new Verify();
	Wait wait = new Wait();
}
