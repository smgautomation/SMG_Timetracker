package com.smg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.smg.utils.ScenarioName;
import com.smg.utils.WebDriverNavigator;

import cucumber.api.Scenario;

public abstract class BasePage {
	WebDriver driver;
	WebDriverNavigator driverNavigator;
	public Scenario scenario;
	
	public void setDriver(WebDriverNavigator driverNavigator, ScenarioName scenarioName) {
        this.driverNavigator = driverNavigator;
        this.driver = driverNavigator.getDriver();
        this.scenario = scenarioName.getScenario();
        PageFactory.initElements(driver, this);
    }

	public Scenario getScenario() {
		return scenario;
	}
}