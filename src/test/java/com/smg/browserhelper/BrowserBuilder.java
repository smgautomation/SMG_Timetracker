package com.smg.browserhelper;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.smg.utils.PropertyUtil;

public class BrowserBuilder {
	
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
		
		if (PropertyUtil.getConfig("execution.mode").equalsIgnoreCase("Local")) {
			switch (desiredBrowserName) {
//	        case "internet explorer":
//	        	driver = IEBrowser.buildIEBrowser();
//	            break;
	        case "Chrome":
	        	driver = ChromeBrowserDriver.buildChromeDriver();
	            break;
	        case "FireFox":
//	        	driver = FirefoxBrowser.buildFirefoxBrowser();
	            break;
	        default:
	            break;
			}
		} else if (PropertyUtil.getConfig("execution.mode").equalsIgnoreCase("Grid")) {
			
			DesiredCapabilities capabilities = null;
	        if (PropertyUtil.getConfig("capabilities.browserName").equalsIgnoreCase("chrome")) {
	        	capabilities = DesiredCapabilities.chrome();
	        } else if (PropertyUtil.getConfig("capabilities.browserName").equalsIgnoreCase("forefox")) {
	        	capabilities = DesiredCapabilities.firefox();
	        }	

        	try {
				driver = new RemoteWebDriver(new URL("http://172.15.76.71:5555/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		return driver;
	}
	
}
