package com.jitu.testpack;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.jitu.base.Page;
import com.jitu.page.actions.GoIbibo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoIbiboTest extends Page {
    @Test
    public void checkDynamicDropDown() {
        initBrowser();
        openUrl("https://www.makemytrip.com/");
        GoIbibo goIbibo=new GoIbibo();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

        goIbibo.typeSource("Del");
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

        List<WebElement> suggestiveDropDown=driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']//li"));
        System.out.println("Total values in the dropdown: "+suggestiveDropDown.size());
//        for(WebElement suggestive:suggestiveDropDown)
//        {
//            if(suggestive.getText().contains("Dehradun"))
//            {
//                for(int i=0;i<=2;i++)
//                {
//                    try
//                    {
//                     //   suggestive.click();
//                      //  break;
//                    }
//                    catch (Exception e)
//                    {
//                        System.out.println(e);
//                    }
//                }
//            }
//
//        }
//        Thread.sleep(10000);
        tearDown();
    }

}
