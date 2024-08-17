package Utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class Listeners extends TestBase implements ITestListener {

	ExtentTest test;
	ExtentReports extentreport = ExtentReporter.GetReporter();
	AndroidDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		test = extentreport.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable()); //1st thing we expect is to mark the test case as failed, this step will do that

		// 2nd thing  is to take a screenshot for the failure
		
		/* the below driver step will go to the testNG file and search for the running current test case 
		and get it's driver to be used here to take a ss and attach it in the report with the same tc */

//		try {
//			driver = (AndroidDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//			 } 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		driver = TestBase.driver;
		String TestCaseName = result.getMethod().getMethodName();
		try {
			test.addScreenCaptureFromPath(GetScreenshotPath(TestCaseName, driver), TestCaseName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
