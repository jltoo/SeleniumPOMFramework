package utilities;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import pages.BasePage;

import com.aventstack.extentreports.MediaEntityBuilder;
//import utilities.DriverInstance;

public class BaseHelper extends DriverInstance{
	protected static WebDriver driver;
	WebElement element;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String tcName;
	static DriverInstance obj;
	public static String propertyFilePath;
//	static Logger log = Logger.getLogger(BasePage.class);
	public LogBuilder logBuilder = new LogBuilder();
	
	
	
	public void setUpTest(String tcName, String description) {
//		initializeReport();
		this.tcName = tcName;
		test = extent.createTest(tcName,description);
	}

	public void openUrl(String url) {
		obj = new DriverInstance();
		driver = obj.initializeDriver("chrome");
		
		driver.get(url);
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public static void initializeReport(){
    	String path = System.getProperty("user.dir");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy-HH_mm_ss_SSS");
	    Date date = new Date();
	    String name = sdf.format(date);
	    String extentReport = path + "/TNG_Reports/" + "TestRunReport-" + name + ".html";
	    
        extent = Reports.createInstance(extentReport);
    }
	
	public static String getEnviPath() {
		String envi = System.getProperty("envi");
		if(envi==null) {
			envi = "test";
		} 
		System.out.println("Environment - "+envi);
		return propertyFilePath= "envi/"+ envi +".properties";
		
	}

	public void click(WebElement locator) {
		try {
			locator.click();
			test.log(Status.PASS, locator + " : Click is done successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL, locator + " : Click is not done.");
			// throw new AssertionError("Unable to click an element in a page", e);
		}
		addScreenshotToReport();
	}

	public void mouseHoverToElement(WebElement locator, int timeout) {
		try {
			WebElement hoverElement = locator;
			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			waitTime(timeout);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//test.log(Status.FAIL, loc + " : Element not found");
		}

	}
	
	public void sendText(WebElement locator, String data) {
		   try {
			   WebElement element = locator;
			   //element.click();
			   element.sendKeys(data);
			   test.log(Status.PASS, "Typed the value " + data);
		   } catch (Exception e) {
			   e.printStackTrace();
//			   test.log(Status.FAIL, loc + " : Unable to put text to field");
		   }
		   
		   addScreenshotToReport();
	   }

	public void waitTime(int seconds) {
		try {
            int ms = 1000 * seconds;
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public void waitUntilElementClickable(WebElement locator, Integer timeout) {
		try {
			System.out.println("WAIT UNTIL ELEMENT CLICKABLE");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

			wait.until(condition);
		} catch (Exception e) {
			e.printStackTrace();
			//    		test.log(Status.INFO, locator + " : Element is still not visible.");

		}
	}
	
	public void waitUntilElementVisible(WebElement locator, Integer timeout) {
		try {
			System.out.println("WAIT UNTIL ELEMENT VISIBLE");
			By byLocator = toByVal(locator);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
			ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

			wait.until(condition);
		} catch (Exception e) {
//			e.printStackTrace();
			//    		test.log(Status.INFO, locator + " : Element is still not visible.");

		}
	}
	
	public By toByVal(WebElement we) {
	    // By format = "[foundFrom] -> locator: term"
	    // see RemoteWebElement toString() implementation
	    String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
	    String locator = data[0];
	    String locValue = data[1];
	    
	    System.out.println("WebElement value - " + we);
	    By by=null;
	    
		if(locator.equalsIgnoreCase("id"))
		{
			by = By.id(locValue);
		}else if(locator.equalsIgnoreCase("css"))
		{
			by = By.cssSelector(locValue);
		}else if(locator.equalsIgnoreCase("class"))
		{
			by = By.className(locValue);
		}else if(locator.equalsIgnoreCase("linkText"))
		{
			by = By.linkText(locValue);
		}else if(locator.equalsIgnoreCase("name"))
		{
			by = By.name(locValue);
		}else if(locator.equalsIgnoreCase("tagName"))
		{
			by = By.tagName(locValue);
		}else if(locator.equalsIgnoreCase("xpath"))
		{
			by = By.xpath(locValue);
		}else if(locator.equalsIgnoreCase("parLinkText"))
		{
			by = By.partialLinkText(locValue);
		}
		return by;
	    
	}
	
	
	public static String getScreenshot(WebDriver driver) throws ParserConfigurationException, SAXException, IOException{

		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DOMAIN_SDF24h);
		Date date = new Date();
		String name = sdf.format(date);
		
		File destination = new File("TNG_Reports/"+tcName+"/"+name+".png");
		File ssdestination = new File (tcName+"/"+name+".png");
		String ssFilepath = ssdestination.getPath();
		try {
			FileUtils.copyFile(src, destination);	
		} catch(IOException e) {

			System.out.println("Capture Failed"+e.getMessage());
		}
		return ssFilepath;
	}
	
	public void addScreenshotToReport(){
		try {
			test.log(Status.INFO,"Screen Capture", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("CANNOT TAKE SCREENSHOT");
		}
		
		
	}
	
	
	 public void verifyIsElementDisplayed(WebElement locator) {
	        try {
	        	WebElement element = locator;
	            element.isDisplayed();
	            test.log(Status.PASS, "PASS Verified " +locator + " : Element is found successfully.");
	            logBuilder.info("PASS Verified " + locator + " : Element is found successfully.");
	        } catch (Exception e) {
//	            logBuilder.info(e.getMessage());
	            test.log(Status.FAIL, "FAIL Verified "+ locator + " : Element is not found.");
	            logBuilder.info("FAIL Verified " + locator + " : Element was not found.");
	            Assert.fail("TEST");
	            addScreenshotToReport();
	        }
	    }
	
}
