package com.jitu.base;

import com.aventstack.extentreports.Status;
import com.jitu.extents.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import com.jitu.utilities.ExcelReader;
import com.jitu.utilities.Logs;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Properties;

public class Page extends Logs {
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
			LOGGER.error("Internet is not connected");
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

			driver.get(Constants.testSiteUrl);
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

    public static WebDriver initBrowser()
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
		return driver;
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

	public static void writeToFile(String dataToWrite)
	{
		try{
			FileWriter fstream = new FileWriter("test-output/Logs/Application.log",true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(dataToWrite+"\n");
			out.close();
		}catch (Exception e){
			System.err.println("Error while writing to file: " +
					e.getMessage());
		}
	}

	public static void takeSnapShot(String fileWithPath,String screenshotName){

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		String fileSeparate=fileSeparator();

		//Move image file to new destination

		File DestFile=new File(fileWithPath);
		if (!DestFile.exists()){
			DestFile.mkdirs();
		}
		//Copy file at destination

		try {

			FileHandler.copy(SrcFile, new File(DestFile+fileSeparate+screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getTitle()
	{
		String pageTitle;
		pageTitle=driver.getTitle().trim();
		return pageTitle;
	}

	public static String fileSeparator()
	{
		String fileSeparator=System.getProperty("file.separator");
		return fileSeparator;
	}

	public void navigateTo(String url)
	{
		driver.navigate().to(url);
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