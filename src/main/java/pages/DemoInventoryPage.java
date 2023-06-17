package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utilities.DriverInstance;
import utilities.Enums;

public class DemoInventoryPage extends BasePage{

    @FindBy(xpath="//div[text()='Sauce Labs Backpack']")
    public WebElement sauceLabBackPackTxt;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    public WebElement sauceLabProductPackTxtProductPage;


    public DemoInventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public DemoInventoryPage verifyInventoryPage() {
        verify.verifyElementDisplayed(sauceLabBackPackTxt);

        return this;
    }

    public DemoInventoryPage clickProductItem() {
        click(sauceLabBackPackTxt);
        verify.verifyElementDisplayed(sauceLabProductPackTxtProductPage);
        return this;
    }

}
