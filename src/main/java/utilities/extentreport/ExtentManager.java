package utilities.extentreport;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    
    public synchronized static ExtentReports createExtentReports() {
    	String path = System.getProperty("user.dir");
    	SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy-HH_mm_ss_SSS");
	    Date date = new Date();
	    String name = sdf.format(date);
	    
        ExtentSparkReporter reporter = new ExtentSparkReporter(path + "/TNG_Reports/" + "TestRunReport-" + name + ".html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
//        extentReports.setSystemInfo("Blog Name", "SW Test Academy");
//        extentReports.setSystemInfo("Author", "Onur Baskirt");
        return extentReports;
    }
}