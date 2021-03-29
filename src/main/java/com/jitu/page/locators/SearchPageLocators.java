package com.jitu.page.locators;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPageLocators {
    @FindBy(xpath = "//input[@class='_3704LK']")
    public WebElement searchField;
    @FindBy(xpath = "//button[@class='L0Z3Pu']")
    public WebElement searchProductButton;

}
