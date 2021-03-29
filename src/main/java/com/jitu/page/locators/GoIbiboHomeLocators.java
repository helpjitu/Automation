package com.jitu.page.locators;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoIbiboHomeLocators {
    @FindBy(xpath = "//input[@id='fromCity']")
    public WebElement sourcePlace;
    @FindBy(xpath = "//input[@id='toCity']")
    public WebElement destPlace;
    @FindBy(xpath = "gosuggest_inputDest")
    public WebElement searchButton;
    @FindBy(xpath = "gosuggest_inputDest")
    public WebElement departure;
}
