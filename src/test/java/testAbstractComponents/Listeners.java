package testAbstractComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExcelUtils;
import resources.ExtentReporterNG;

public class Listeners implements ITestListener {

	ExtentReports extent;
	ExtentTest test;

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentReporterNG.getReportObject();

		// ✅ Create Excel file once
		try {
			ExcelUtils.createExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().pass("Test Passed");

		// ✅ Excel update
		try {
			ExcelUtils.writeResult(result.getMethod().getMethodName(), "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());

		try {

			// ✅ Get driver safely
			BaseTest baseTest = (BaseTest) result.getInstance();
			WebDriver driver = baseTest.driver;

			// Screenshot
			String path = BaseTest.getScreenshot(driver, result.getMethod().getMethodName());

			extentTest.get().addScreenCaptureFromPath(path);

			// Excel update
			ExcelUtils.writeResult(result.getMethod().getMethodName(), "FAIL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().skip(result.getThrowable());

		try {
			ExcelUtils.writeResult(result.getMethod().getMethodName(), "SKIP");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}