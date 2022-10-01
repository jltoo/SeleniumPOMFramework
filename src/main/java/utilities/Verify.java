package utilities;

import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import org.testng.asserts.SoftAssert;

public class Verify extends BaseHelper{

	public void verifyElementDisplayed(WebElement locator) {
		try {
			WebElement element = locator;
			element.isDisplayed();
			assertPass("PASS Verified - " +locator + " : Element is found successfully.");
		} catch (Exception e) {
			softAssertFail("FAIL Verified "+ locator + " : Element is not displayed.");
		}
	}

	public void verifyUrl(String val) {
		String URL = driver.getCurrentUrl();
		if (URL.equals(val)) {
			assertPass("PASS Verified URL Matched - Expected URL is "  + val + ": Actual Text is "+ URL);
		} else {
			softAssertFail( "FAIL Verified - URL not matched - Expected URL is " + val + "Actual Url is " + URL);
		}
	}

	public void verifyElementText(WebElement locator, String text)  {
		try {

			String actualText = locator.getText();

			if (actualText.equals(text)) {
				assertPass("PASS Verified Text matched - Expected Text is " + text + ": Actual Text is "+ actualText);

			} else {
				softAssertFail("FAIL Verified Expected Text is " + text + ": Actual Text is "+ actualText);
			}

		} catch(Exception e) {
			softAssertFail("FAIL Verified - Unable to Find Element - " + locator);
		}
	}

	public void verifyElementTextContains(WebElement locator, String text)  {
		try {

			String actualText = locator.getText().trim();

			if (actualText.contains(text)) {
				assertPass("PASS Verified Text matched - Expected Text is " + text + ": Actual Text is "+ actualText);

			} else {
				softAssertFail("FAIL Verified Expected Text is " + text + ": Actual Text is "+ actualText);
			}

		} catch(Exception e) {
			softAssertFail("FAIL Verified - Unable to Find Element - " + locator);
		}
	}

	public void verifyElementAttribute(WebElement locator, String attribute, String value) {
		try {
			String attribValue = locator.getAttribute(attribute);
			if (attribValue.equalsIgnoreCase(value)) {
				assertPass("PASS Verified Attribute Matched - Expected - " + value + " Actual -" + attribValue);
			} else {
				softAssertFail("FAIL Verified Attribute Not Matched - Expected - " + value + " Actual -" + attribValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			softAssertFail("FAIL Verified - Unable to Find Element - " + locator);
		}

	}

	public void verifyElementAttributeContains(WebElement locator, String attribute, String value) {
		try {
			String attribValue = locator.getAttribute(attribute).trim();
			if (attribValue.contains(value)) {
				assertPass("PASS Verified Attribute Matched - Expected - " + value + " Actual -" + attribValue);
			} else {
				softAssertFail("FAIL Verified Attribute Not Matched - Expected - " + value + " Actual -" + attribValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			softAssertFail("FAIL Verified - Unable to Find Element - " + locator);
		}
	}

	public void verifyElementNotDisplayed(WebElement locator) {
		try {
			WebElement element = locator;
			element.isDisplayed();
			softAssertFail("FAIL Verified "+ locator + " : Element is displayed.");
		} catch (Exception e) {
			assertPass("PASS Verified - " +locator + " : Element is not found");
		}
	}

	public void verifyElementAttributeNotContains(WebElement locator, String attribute, String value) {
		try {
			String attribValue = locator.getAttribute(attribute).trim();
			if (!attribValue.contains(value)) {
				assertPass("PASS Verified Attribute Not Matched - Expected - " + value + " Actual -" + attribValue);
			} else {
				softAssertFail("FAIL Verified Attribute Matched - Expected - " + value + " Actual -" + attribValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			softAssertFail("FAIL Verified - Unable to Find Element - " + locator);
		}
	}

}
