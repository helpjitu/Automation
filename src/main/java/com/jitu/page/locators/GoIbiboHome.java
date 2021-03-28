package com.jitu.page.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoIbiboHome {
    @FindBy(xpath = "//input[@id='fromCity']")
    public WebElement sourcePlace;
    @FindBy(xpath = "//input[@id='toCity']")
    public WebElement destPlace;
    @FindBy(xpath = "gosuggest_inputDest")
    public WebElement searchButton;
    @FindBy(xpath = "gosuggest_inputDest")
    public WebElement departure;
}
