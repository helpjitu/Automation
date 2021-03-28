package com.jitu.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.jitu.base.Page;
import com.jitu.extents.ExtentManager;
import com.jitu.extents.ExtentTestManager;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

public class ListenerTestNG extends Page implements ITestListener {
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

        logInfo("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        logInfo((result.getMethod().getMethodName() + " failed!"));

        ITestContext context = result.getTestContext();
        String testClassName = getClassName(context).trim();
     //   String testClassName=getClassName();
        String testMethodName = result.getName().toString().trim();
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        String timeStamp = timestamp1.toString().replace(" ","").replace(".","").replace(":","").replace("-",""); // get timestamp


        String screenShotName = testMethodName + timeStamp + ".png";
        String targetLocation=System.getProperty("user.dir")+fileSeparator()+"TestReport"+fileSeparator()+"screenshots"+fileSeparator()+testClassName+fileSeparator();

        takeSnapShot(targetLocation, screenShotName);

        ExtentTestManager.getTest().fail("Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath(targetLocation+screenShotName).build());
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
    public String getClassName(ITestContext iTestContext) {

    //    String case1= iTestContext.getClass().getName().trim();//.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();

        String className=iTestContext.getName();
        return className;
    }
}
