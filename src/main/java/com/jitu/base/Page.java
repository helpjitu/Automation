package com.jitu.base;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.aventstack.extentreports.Status;
import com.jitu.extents.ExtentTestManager;
import com.jitu.utilities.ExcelReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Page {
	public static WebDriver driver;
	public static String browser;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static Properties prop;
	public static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean initConfiguration() {
		browser=loadProp().getProperty("browser");
		if(OS.contains("windows"))
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver.exe");
		}
		else
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver");
		}
		if (!isInternetConnected()) {
			logFail("Internet is not connected");
			Assert.fail("No Internet Connectivity");
			return false;
		} else {
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
				logInfo("Launching Chrome");
			}
			else if(browser.equalsIgnoreCase("html"))
			{
				driver=new HtmlUnitDriver();
				logInfo("Launching HTML Unit browser");
			}

			driver.get(loadProp().getProperty("testSiteUrl"));
			driver.manage().window().maximize();
			return true;
		}

	}


    public static void openUrl(String url)
	{
		if (driver!=null)
		{
			driver.get(url);
			driver.manage().window().maximize();
		}
	}

    public static void initBrowser()
	{
		if(OS.contains("windows"))
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver.exe");
		}
		else
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver");
		}
		if(isInternetConnected()) {
			driver = new ChromeDriver();
			logInfo("Launching Chrome");
		}
		else
		{
			logInfo("Internet not Connected");
		}
	}

	public static void clickElement(WebElement element) {
		element.click();
		logInfo("Clicking on an Element: " + element);
	}

	public static void type(WebElement element, String value) {
		element.sendKeys(value);
		logInfo("Entering the value as: " + value + " for the element: " + element);
	}

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static Properties loadProp() {
		File propertyFile = new File("src/test/resources/Config.properties");

		try {
			FileInputStream file = new FileInputStream(propertyFile);
			prop = new Properties();
			try {
				prop.load(file);
				return prop;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static boolean isInternetConnected()
	{
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Internet is connected");
			return true;
		} catch (IOException e) {
			System.out.println("Internet is not connected");
			return false;
		}
	}

	public String takeSnapShot(){
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

	}

	public String getTitle()
	{
		String pageTitle;
		pageTitle=driver.getTitle().trim();
		return pageTitle;
	}

	public static void logInfo(String logMessage)
	{
		ExtentTestManager.getTest().log(Status.INFO,logMessage);
	}
	public static void logPass(String logMessage)
	{
		ExtentTestManager.getTest().log(Status.PASS,logMessage);
	}
	public static void logFail(String logMessage)
	{
		ExtentTestManager.getTest().log(Status.FAIL,logMessage);
	}
	public static void logSkip(String logMessage)
	{
		ExtentTestManager.getTest().log(Status.SKIP,logMessage);
	}
	public static void logWarning(String logMessage)
	{
		ExtentTestManager.getTest().log(Status.WARNING,logMessage);
	}
}