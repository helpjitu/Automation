package com.jitu.page.actions;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.jitu.base.Page;
import com.jitu.page.locators.GoIbiboHomeLocators;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class GoIbibo extends Page {
    public GoIbiboHomeLocators goIbiboHome;
    public GoIbibo()
    {
        this.goIbiboHome=new GoIbiboHomeLocators();
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
