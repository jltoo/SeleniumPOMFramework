package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	public static By by;
	public boolean staleElement = true; 
	
	private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        DriverInstance.webDriver = webDriver;
    }

    public WebDriver getDriver() {
        return webDriver;
    }

	
	public WebDriver initializeDriver(String browser) {
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
    		options.addArguments("--incognito");
    		options.addArguments("--whitelisted-ips");
    		options.addArguments("--disable-popup-blocking");
    		
			driver = WebDriverManager.chromedriver().capabilities(options).create();
		}
		
		driver.manage().window().maximize();		
		
		return driver;
	}
	
	
	
	
	
	
	
}
