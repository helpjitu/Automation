package com.jitu.page.actions;

import com.jitu.base.Page;
import com.jitu.page.locators.GoIbiboHome;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class GoIbibo extends Page {
    public GoIbiboHome goIbiboHome;
    public GoIbibo()
    {
        this.goIbiboHome=new GoIbiboHome();
        AjaxElementLocatorFactory factory =new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this.goIbiboHome);
    }
    public void typeSource(String sourcePlace)
    {
        type(goIbiboHome.sourcePlace, sourcePlace);
    }
    public void typeDestination(String destPlace)
    {
        type(goIbiboHome.destPlace, destPlace);
    }
}
