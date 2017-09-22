package com.smg.stepdefs;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.smg.browserhelper.BrowserBuilder;
import com.smg.utils.ScenarioName;
import com.smg.utils.WebDriverNavigator;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class ScenarioHooks implements En{
	private static final Logger log = LogManager.getLogger(ScenarioHooks.class);
	public WebDriverNavigator driverNavigator;
	public static ScenarioName scenarioName;
	
	public WebDriverNavigator getDriverNavigator() {
		return driverNavigator;
	}
	
	public static ScenarioName getScenarioName() {
		return scenarioName;
	}
	
	public String takeScreenShot(Scenario scenario) {
		String outPath = "target/screenshots/" + scenario.getId() + scenario.getName() + "/" + scenario.getName().replaceAll("[; !@#$%^&()+=]", "_")+".png";
		try {
			File scrFile = ((TakesScreenshot) driverNavigator.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(outPath).getAbsoluteFile());
        } catch (Throwable e) {
            e.printStackTrace();
        }
		return outPath;
	}
	
	public ScenarioHooks() {
		Before(new String[] { "@scenarios" }, (Scenario scenario) -> {
			if (driverNavigator == null) {
				log.info("Scenario: {}", scenario.getName());
				try {
					WebDriver driver = BrowserBuilder.getDriverBrowser();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					driverNavigator = new WebDriverNavigator(driver);
					scenarioName = new ScenarioName(scenario);
				} catch (Exception e) {
					log.error("WebDriver initialization failed : {}", e);
				}
			}
		});
		
		After(new String[] { "@scenarios" }, (Scenario scenario) -> {
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