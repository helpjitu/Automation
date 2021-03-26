package com.jitu.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.jitu.utilities.ExcelReader;
import com.jitu.utilities.Logs;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;


public class Page extends Logs {
	public static WebDriver driver;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static Properties prop;
	public static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean initConfiguration() {
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
		if(isInternetConnected() == true)
		{
			driver = new ChromeDriver();
			LOGGER.debug("Launching Chrome");

			driver.get(Constants.testSiteUrl);
			driver.manage().window().maximize();
			return true;
		}
		else {
			LOGGER.error("Internet is not connected");
			Assert.fail();
			return false;
		}


    }

    public static void openUrl(String URL)
	{
		if (driver!=null)
		{
			driver.get(URL);
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
		if(isInternetConnected() == true) {
			driver = new ChromeDriver();
			LOGGER.debug("Launching Chrome");
		}
		else
		{
			LOGGER.debug("Internet not Connected");
		}
		return driver;
	}

	public static void clickElement(WebElement element) {
		element.click();
		LOGGER.info("Clicking on an Element: " + element);
	}

	public static void type(WebElement element, String value) {
		element.sendKeys(value);
		LOGGER.info("Entering the value as: " + value + " for the element: " + element);
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
}
