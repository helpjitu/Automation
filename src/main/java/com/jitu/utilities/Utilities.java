package com.jitu.utilities;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.jitu.base.Page;
import org.testng.annotations.DataProvider;

public class Utilities extends Page {

	public static String screenshotPath;
	public static String screenshotName;
	public static String destFile=System.getProperty("user.dir")+"/target/surefire-reports/extentReports";

	public static void captureScreenshot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		try {
			FileUtils.copyFile(scrFile, new File(destFile));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@DataProvider(name="dp")
	public static Object[][] getData(Method m) {

		String sheetName = m.getName();
		System.out.println(sheetName+" Sheet name");
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	
public static boolean isTestRunnable(String testName, ExcelReader excel){
		
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);

				return runmode.equalsIgnoreCase("Y");
			}
			
			
		}
		return false;
	}
}
