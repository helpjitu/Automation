package com.jitu.listeners;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.jitu.base.Page;
import com.jitu.extents.ExtentManager;
import com.jitu.extents.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class ListenerTestNG extends Page implements ITestListener {
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        logInfo(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        logPass("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
    }

    public void onTestFailure(ITestResult result) {
        logFail("*** Test execution " + result.getMethod().getMethodName() + " failed...");


        ExtentTestManager.getTest().fail(
                MediaEntityBuilder.createScreenCaptureFromBase64String(takeSnapShot(),"Screenshot").build());

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        logSkip("*** Test " + result.getMethod().getMethodName() + " skipped...");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
        logInfo("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}
