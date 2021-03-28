package com.jitu.page.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.jitu.base.Page;
import com.jitu.page.locators.HomePageLocators;

public class HomePage extends Page{
	public HomePageLocators homePageLocators;
	public HomePage()
	{
		this.homePageLocators=new HomePageLocators();
		AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.homePageLocators);
	}
	
	public HomePage clickOnSignIn()
	{
		logInfo("Clicking on SignInButton");
		clickElement(homePageLocators.signInButton);
		return this;
	} 
}
