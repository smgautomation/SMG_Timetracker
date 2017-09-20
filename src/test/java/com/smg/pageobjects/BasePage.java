package com.smg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.smg.utils.ScenarioName;
import com.smg.utils.WebDriverNavigator;

import cucumber.api.Scenario;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverNavigator driverNavigator;
	protected Scenario scenario;
	
	public void setDriver(WebDriverNavigator driverNavigator, ScenarioName scenarioName) {
        this.driverNavigator = driverNavigator;
        this.driver = driverNavigator.getDriver();
        this.scenario = scenarioName.getScenario();
        PageFactory.initElements(driver, this);
    }
}