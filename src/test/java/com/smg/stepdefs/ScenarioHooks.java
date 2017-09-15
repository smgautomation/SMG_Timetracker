package com.smg.stepdefs;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.smg.browserhelper.BrowserBuilder;
import com.smg.utils.WebDriverNavigator;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class ScenarioHooks implements En{
	private static final Logger log = Logger.getLogger(ScenarioHooks.class);
	public WebDriverNavigator driverNavigator;
	
	public WebDriverNavigator getDriverNavigator() {
		return driverNavigator;
	}
	
	public ScenarioHooks() {
		Before(new String[] { "@web" }, (Scenario scenario) -> {
			if (driverNavigator == null) {
				log.info("Scenario: " + scenario.getName());
				try {
					WebDriver driver = BrowserBuilder.getDriverBrowser();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					driverNavigator = new WebDriverNavigator(driver);
				} catch (Exception e) {
					log.info("WebDriver initialization failed : {}", e);
				}
			}
		});
		
		After(new String[] { "@web" }, (Scenario scenario) -> {
			if (scenario.isFailed()) {
				driverNavigator.embedScreenshot(scenario);
			}
			log.info("Closing the driver instance.");
			driverNavigator.getDriver().quit();
			driverNavigator = null;
			log.info("End of Scenario: " + scenario.getName());
		});
	}
}