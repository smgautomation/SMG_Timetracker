package com.smg.browserhelper;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.smg.utils.PropertyUtil;

public class BrowserBuilder {
	private static final Logger log = LogManager.getLogger(BrowserBuilder.class);
	
	public static WebDriver getDriverBrowser(){
    	WebDriver driver = getWebDriver();
    	if (driver != null) {
    		driver.manage().deleteAllCookies();
    		driver.manage().window().maximize();
    	}
        return driver;
    }
	
	private static WebDriver getWebDriver() {
		
		String desiredBrowserName = PropertyUtil.getProp("src/test/resources/properties/config.properties", "capabilities.browserName");
		WebDriver driver = null;
		log.info("\nSelected browser: {} \nSelected Execution Mode: {}", PropertyUtil.getConfig("capabilities.browserName"), PropertyUtil.getConfig("execution.mode"));
		if (PropertyUtil.getConfig("execution.mode").equalsIgnoreCase("Local")) {
			switch (desiredBrowserName) {
	        case "Chrome":
	        	driver = ChromeBrowserDriver.buildChromeDriver();
	            break;
	        case "FireFox":
	        	driver = FirefoxBrowserDriver.buildFirefoxDriver();
	            break;
	        default:
	            break;
			}
			
		} else if (PropertyUtil.getConfig("execution.mode").equalsIgnoreCase("Grid")) {
			
			DesiredCapabilities capabilities = null;
	        if (PropertyUtil.getConfig("capabilities.browserName").equalsIgnoreCase("chrome")) {
	        	capabilities = DesiredCapabilities.chrome();
	        } else if (PropertyUtil.getConfig("capabilities.browserName").equalsIgnoreCase("firefox")) {
	        	capabilities = DesiredCapabilities.firefox();
	        } else {
	        	
	        }

        	try {
				driver = new RemoteWebDriver(new URL("http://172.16.220.108:3023/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		driver.manage().window().maximize();
		return driver;
	}
}
