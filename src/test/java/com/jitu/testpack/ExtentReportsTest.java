package com.jitu.testpack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Test;

public class ExtentReportsTest {
    ExtentReports extent;
    @Test
    public void extentTest()
    {
        // directory where output is to be printed
        ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
        extent = new ExtentReports();
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Extent Reports By Jitendra");
        extent.attachReporter(spark);

        ExtentTest test=extent.createTest("Login Test");
        test.pass("Passed");
        extent.flush();


    }
}
