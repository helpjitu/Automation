package com.jitu.testpack;


import com.jitu.base.Page;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PdfHandlingTest extends Page {
		

	@Test
	public void verifyContentInPDf() {
		//specify the url of the pdf file
		if (initConfiguration())
		{
			String url="http://www.pdf995.com/samples/pdf.pdf";
			openUrl(url);
			try {
				String pdfContent = readPdfContent(url);
				Assert.assertTrue(pdfContent.contains("The Pdf995 Suite offers the following features"));
				//		AssertJUnit.assertTrue(pdfContent.contains("The Pdf995 Suite offers the following features"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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