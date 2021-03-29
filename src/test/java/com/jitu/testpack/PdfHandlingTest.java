package com.jitu.testpack;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.jitu.base.Page;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Listeners(com.jitu.listeners.ListenerTestNG.class)
public class PdfHandlingTest extends Page {


	@Test
	public void verifyContentInPDf(){
		logInfo("Browser initiated");
		String url="http://www.pdf995.com/samples/pdf.pdf";
		initBrowser();
		openUrl(url);
		logInfo("URL opened");
		try
		{
			String pdfContent = readPdfContent(url);
			Assert.assertTrue(pdfContent.contains("The Pdf995 Suite offers the following features"));
		}
		catch (IOException e)
		{
			logWarning("Exception thrown: "+e);
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver!=null)
		{
			driver.quit();
		}
	}


	public static  String readPdfContent(String url) throws IOException {

		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		int numberOfPages = getPageCount(doc);
		System.out.println("The total number of pages "+numberOfPages);
		String content = new PDFTextStripper().getText(doc);
		doc.close();

	return content;
}

	public static int getPageCount(PDDocument doc) {
		//get the total number of pages in the pdf document
		return doc.getNumberOfPages();
	}

}