package com.smg.browserhelper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.smg.utils.PropertyUtil;

public class FirefoxBrowserDriver extends FirefoxDriver{
	
	public static WebDriver buildFirefoxDriver() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\" + PropertyUtil.getConfig("local.firefox.driver.location"));
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability("acceptSslCerts" , true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        WebDriver browser = new FirefoxDriver(capabilities);
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        browser.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        return browser;
    }

}
