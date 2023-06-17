package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***** TEST STARTED  " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***** TEST SUCCESS  " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***** TEST FAILED  " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***** TEST SKIPPED  " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("***** TEST STARTED" + result);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
//		System.out.println("***** TEST STARTED" + context);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("***** TEST FINISH  " + context.getName());
	}

}
