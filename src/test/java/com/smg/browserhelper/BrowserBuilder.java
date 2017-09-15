package com.smg.browserhelper;

import org.openqa.selenium.WebDriver;

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
		switch (desiredBrowserName) {
//        case "internet explorer":
//        	driver = IEBrowser.buildIEBrowser();
//            break;
        case "Chrome":
        	driver = ChromeBrowserDriver.buildChromeDriver();
            break;
//        case "FireFox":
//        	driver = FirefoxBrowser.buildFirefoxBrowser();
//            break;
        default:
            // work out what to do when a browser isn't needed
            break;
		}
		return driver;
	}
	
}
