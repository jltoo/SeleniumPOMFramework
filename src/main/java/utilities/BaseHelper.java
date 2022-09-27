package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
//import utilities.DriverInstance;
import com.aventstack.extentreports.Status;


public class BaseHelper extends DriverInstance implements IHookable{
	protected static WebDriver driver;
	WebElement element;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String tcName;
	static DriverInstance obj;
	public static String propertyFilePath;;
	public LogBuilder logBuilder = new LogBuilder();
//	SoftAssert softAssert = new SoftAssert();
	public static boolean isFailed = false;


	private static SoftAssert softAssert = null;
	
	public void run(IHookCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub
		setSoftAssert();
		
		callBack.runTestMethod(testResult);
		
		setAssertAll();
	}
	
	public static void setSoftAssert(){
		SoftAssert softAssert = new SoftAssert();
		
	}
	
	public static void setAssertAll(){
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();

	}

	public void setUpTest(String tcName, String description) {
		BaseHelper.tcName = tcName;
		test = extent.createTest(tcName,description);
	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword openUrl
	 * @usage for initializing webdriver and redirecting to url
	 * @param url
	 * @return NA
	 * @example openUrl("<url>")
	 */


	public void openUrl(String url) {
		obj = new DriverInstance();

		driver = obj.initializeDriver("chrome");

		redirectToUrl(url);
	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword redirectToUrl
	 * @usage redirecting to url
	 * @param url
	 * @return NA
	 * @example redirectToUrl("<url>")
	 */

	public void redirectToUrl(String url) {
		driver.get(url);
		test.log(Status.INFO, "Redirecting to url - " + url);
		logBuilder.info("Redirecting to url - " + url);
	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword closeBrowser
	 * @usage For Closing browser instance
	 * @param NA
	 * @return NA
	 * @example closeBrowser()
	 */

	public void closeBrowser() {
		driver.quit();
		test.log(Status.INFO, "Test Case Execution Completed - Closing Browser ");
		logBuilder.info("Test Case Execution Completed - Closing Browser");
	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword initializeReport
	 * @usage For initializing Extent Report- Only called once in BaseTest
	 * @param NA
	 * @return NA
	 * @example initializeReport()
	 */

	public static void initializeReport(){
		String path = System.getProperty("user.dir");
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy-HH_mm_ss_SSS");
		Date date = new Date();
		String name = sdf.format(date);
		String extentReport = path + "/TNG_Reports/" + "TestRunReport-" + name + ".html";

		extent = Reports.createInstance(extentReport);
	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword getEnviPath
	 * @usage For getting envi path used for envi configs (Envi params can be passed by executing -Denvi in maven command
	 * @param NA
	 * @return NA
	 * @example getEnviPath()
	 */

	public static String getEnviPath() {
		String envi = System.getProperty("envi");
		if(envi==null) {
			envi = "test";
		} 
		System.out.println("Environment - "+envi);
		return propertyFilePath= "envi/"+ envi +".properties";

	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword click
	 * @usage click element
	 * @param NA
	 * @return NA
	 * @example click(<LOCATOR>)
	 */

	public void click(WebElement locator) {
		try {
			locator.click();
			assertPass("PASS Verified - Successfully Clicked Element - " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			hardAssertFail("FAIL Verified -  Unable to Click Element - " + locator);
		}

	}

	/**
	 * @author Lovie Too
	 * @createdDate NA
	 * @modifiedBy NA
	 * @modifiedDate NA
	 * @keyword click
	 * @usage scroll to element to view
	 * @param NA
	 * @return NA
	 * @example scrollToElement(<LOCATOR>, waitTime)
	 */

	public void scrollToElement(WebElement locator,int time) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);",locator);
			js.executeScript("window.scrollBy(0,-100)", "");
			waitTime(time);
			assertPass("PASS Verified - Successfully Scrolled to Element - " + locator);
		} catch (Exception e) {
			waitTime(time);
			hardAssertFail("FAIL Verified - Unable to Scroll to Element - " + locator);
		}
	}

	public void mouseHoverToElement(WebElement locator, int timeout) {
		try {
			WebElement hoverElement = locator;
			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElement).perform();
			waitTime(timeout);
			assertPass("PASS Verified - Successfully Hovered To Element - " + locator);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			hardAssertFail("FAIL Verified - Unable to Mouse Hover to Element - " + locator);
		}

	}

	public void sendText(WebElement locator, String data) {
		try {
			WebElement element = locator;
			element.sendKeys(data);
			assertPass("PASS Verified - Successfully inputted " + data + " To Element -" + locator);
		} catch (Exception e) {
			e.printStackTrace();
			softAssertFail("Unable to type to Element - " + locator);
		}
	}

	public void waitTime(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DOMAIN_SDF);

		Date date = new Date();
		String name = sdf.format(date);
		String folderName = sdf1.format(date);

		File destination = new File("TNG_Reports/"+folderName+"/"+tcName+"/"+name+".png");
		File ssdestination = new File (folderName+"/"+tcName+"/"+name+".png");
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

	public void hardAssertFail(String message) {
		isFailed = true;
		test.log(Status.FAIL, message);
		logBuilder.warn(message);
		Assert.fail(message);
		addScreenshotToReport();
	}

	public void softAssertFail(String message) {
		isFailed = true;
		test.log(Status.FAIL, message);
		logBuilder.warn(message);
		softAssert.fail(message);
//		sa.assertAll();

		addScreenshotToReport();
	}

	public void assertPass(String message) {
		test.log(Status.PASS, message);
		logBuilder.info(message);
		addScreenshotToReport();
	}




}
