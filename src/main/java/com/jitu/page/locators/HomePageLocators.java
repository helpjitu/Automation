package com.jitu.page.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePageLocators {
	
	
	  @FindBy(xpath="") public WebElement emailId;
	  
	  @FindAll({
	 
	  @FindBy(xpath="//"),
	  
	  @FindBy(css="#") }) public WebElement password;
	  
	  @FindBys({
	  
	  @FindBy(xpath=""),
	  
	  @FindBy(xpath="") }) public WebElement signInButton;
	 

}
