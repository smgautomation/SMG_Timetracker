package com.smg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.smg.utils.WebDriverNavigator;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverNavigator driverNavigator;
	
	public void setDriver(WebDriverNavigator driverNavigator) {
        this.driverNavigator = driverNavigator;
        this.driver = driverNavigator.getDriver();
        PageFactory.initElements(driver, this);
    }
}