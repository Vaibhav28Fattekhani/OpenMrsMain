package Listener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.QA.Base.TestBase;
import com.QA.Utility.ExtentManager;
import com.QA.Utility.Util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import lombok.experimental.UtilityClass;

public class ListnerManager implements ITestListener{
	private static ExtentReports extent= ExtentManager.SetupExtentReport();
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result) {
		String testcasename =result.getMethod().getMethodName();
		ExtentTest extenttest=extent.createTest(testcasename);
		test.set(extenttest);
		test.get().log(Status.INFO, testcasename + " is started at : " +CaptureCurrentTime());
		
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		String testcasename =result.getMethod().getMethodName();
		test.get().log(Status.INFO, testcasename + " is Skipped");
	}

	

	@Override
	public void onTestSuccess(ITestResult result) {
		String testcasename =result.getMethod().getMethodName();
		test.get().log(Status.INFO, testcasename + " is passed at :"+CaptureCurrentTime());
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testcasename =result.getMethod().getMethodName();
		test.get().log(Status.INFO, testcasename + " is Failed at :" + CaptureCurrentTime());
		test.get().log(Status.FAIL, testcasename + " is Failed beacsuse :" + result.getThrowable());
		//String path="C://Users//Vaibhav//eclipse-workspace//Hybrid_OpenMrs//Screenshots//Allscreenshot";
		//String screenshot=Util.TakeScreenShot(path+testcasename+CaptureCurrentTime()+".png");
		//test.get().addScreenCaptureFromPath(screenshot);
		
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	
	private static String CaptureCurrentTime()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currenttime=LocalDateTime.now();
		String formatedtime=currenttime.format(formatter);
		return formatedtime;
	}
	
	
	
	
}
