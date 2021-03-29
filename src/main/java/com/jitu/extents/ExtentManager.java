package com.jitu.extents;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report"+".html";
    private static String fileSeparator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeparator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeparator+ reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);

        ExtentSparkReporter spark = new ExtentSparkReporter(fileName).viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY,ViewName.EXCEPTION}).apply();

        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle(reportFileName);
        spark.config().setEncoding("utf-8");
        spark.config().setReportName(reportFileName);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        //Set environment details
        extent.setSystemInfo("Owner", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Test", "Automation");
        extent.setSystemInfo("Author", "Jitendra");
        extent.setSystemInfo("Project","Shopping");

        return extent;
    }

    //Create the report path
    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }
}
