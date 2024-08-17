package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	static ExtentReports extentreport;

	public static ExtentReports GetReporter() {

		String ReportPath =System.getProperty("user.dir")+"\\Reports\\ExecutionReport.html";
		ExtentSparkReporter Reporter= new ExtentSparkReporter(ReportPath);
		Reporter.config().setReportName("Appium Automation");
		Reporter.config().setDocumentTitle("Test Results");
		extentreport = new ExtentReports();
		extentreport.attachReporter(Reporter);
		extentreport.setSystemInfo("Tester", "Ahmed Gamal");
		return extentreport;
	}

}
