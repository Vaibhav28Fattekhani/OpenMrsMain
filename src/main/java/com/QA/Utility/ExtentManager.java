package com.QA.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentSparkReporter reporter;
	private static ExtentTest test;

	
	public static ExtentReports SetupExtentReport()
	{
		String reportpath= System.getProperty("user.dir")+"//Reports//TestExecutionReport.html";
		reporter= new ExtentSparkReporter(reportpath);
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Automation Test Execution Report");
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name","Local Host");
		extent.setSystemInfo("Envirnment", "UAT");
		extent.setSystemInfo("Vaibhav Fattekhani", "Automation Tester");
		return extent;
		
		
	}
	

}
