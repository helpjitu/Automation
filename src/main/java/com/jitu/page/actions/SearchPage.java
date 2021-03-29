package com.jitu.page.actions;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import com.jitu.base.Page;
import com.jitu.page.locators.SearchPageLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage extends Page {

    public SearchPageLocators searchPage;
    public SearchPage()
    {
        this.searchPage=new SearchPageLocators();
        AjaxElementLocatorFactory factory =new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this.searchPage);
    }

    public  void searchProduct(String productName)
    {
        logInfo("Typing product name");
        type(searchPage.searchField,productName);
        Actions actions=new Actions(driver);
        actions.click().sendKeys(Keys.ENTER).perform();

      //  clickElement(searchPage.searchProductButton);
    }
}
