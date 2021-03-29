package com.jitu.page.locators;
/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPageLocators {
	
	@FindBy(xpath = "//input[@class='_2IX_2- VJZDxU']")
	public WebElement emailMobile;

	@FindBy(xpath = "//input[@class='_2IX_2- _3mctLh VJZDxU']")
	public WebElement password;

	@FindBy(xpath = "//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")
	public WebElement signInButton;

	@FindBy(xpath="//a[@class='_14Me7y']")
	public WebElement signUpButton;

	@FindBy(xpath = "//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")
	public WebElement continueButton;

}
