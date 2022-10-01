package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {
	private static ExtentReports extent;
	private static ExtentTest childTest;

	public static ExtentReports getInstance(String fileName) {
		if (extent == null)
			createInstance(fileName);
		return extent;
	}
	
	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);

		spark.config().setDocumentTitle("TEST");
		spark.config().setTheme(Theme.DARK);

		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", System.getProperty("os.name"));

		return extent;
	}
	
	
}
