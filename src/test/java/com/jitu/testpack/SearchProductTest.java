package com.jitu.testpack;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */

import com.jitu.base.Page;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.jitu.page.actions.SearchPage;
import com.jitu.page.actions.SignInPage;
import com.jitu.utilities.Utilities;

import java.util.Hashtable;

@Listeners(com.jitu.listeners.ListenerTestNG.class)
public class SearchProductTest extends Page {
    @Test(dataProviderClass = Utilities.class,dataProvider = "dp")
    public void searchProductTest(Hashtable<String, String> data) throws InterruptedException {
        if(data.get("runmode").equalsIgnoreCase("N"))
        {
            logSkip("Skipping the test as the Run mode is NO");
            throw new SkipException("Skipping the test as the Run mode is NO");
        }
        else {

            if (initConfiguration())
            {
                SignInPage signInPage=new SignInPage();
                signInPage.doLogin(data.get("username"),data.get("password"));
                SearchPage searchPage=new SearchPage();
                searchPage.searchProduct(data.get("productName"));
                Thread.sleep(3000);
                Assert.assertEquals("Hello",getTitle());
            }
        }
    }
    @AfterMethod
    public void end()
    {
        tearDown();
    }
}
