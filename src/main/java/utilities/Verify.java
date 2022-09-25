package utilities;

import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;

public class Verify extends BaseHelper{

	

	public void verifyIsElementDisplayed(WebElement locator) {
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
			assertPass("Pass Verified URL Matched - Expected URL is "  + val + ": Actual Text is "+ URL);
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
}
