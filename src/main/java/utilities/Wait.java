package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class Wait extends BaseHelper {

	/**
	 * Method to static delay
	 * 
	 * @param seconds
	 */
	public void wait(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
			test.log(Status.INFO, locator + " : Element is still not visible.");

		}
	}


	public void waitUntilElementInvisible(WebElement locator, int timeout) {
		logBuilder.info("Waiting for Element to be Invisible.");
		try {
			By byLocator = toByVal(locator);
			WebDriverWait wait = new WebDriverWait(DriverInstance.getWebDriver(), timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};
			wait.until(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logBuilder.info(e.getMessage());
			test.log(Status.INFO, locator + " : Element is visible.");
			logBuilder.info(locator + " : Element is visible.");
		}
	}

	public void waitUntilElementClickable(WebElement locator, int timeout) {
		By byLocator = toByVal(locator);
		try {
			for (int x = 0; x < 5; x++) {
				WebDriverWait wait = new WebDriverWait(DriverInstance.getWebDriver(), timeout);
				wait.until(ExpectedConditions.elementToBeClickable(byLocator));
				ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
								.equals("complete");
					}
				};
				logBuilder.info("Wait until element is clickable");
				wait.until(condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logBuilder.info(e.getMessage());
			test.log(Status.INFO, locator + " : Element is still not visible.");
			logBuilder.info(locator + " : Element is still not visible.");
		}

	}

	
}
