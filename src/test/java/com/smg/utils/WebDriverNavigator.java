package com.smg.utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.smg.constants.CommonConstants;

import cucumber.api.Scenario;

public class WebDriverNavigator {
	private static final Logger log = Logger.getLogger(WebDriverNavigator.class);
	private final WebDriver driver;

    public WebDriverNavigator(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void get(String url) {
    	try {
    		driver.get(url);
    	} catch (TimeoutException e) {
    		Assert.fail("Page Load Timeout occurred after " + 30 + " seconds waiting time: " + e);
    	} catch (UnreachableBrowserException e) {
    		Assert.fail("Unreachable Browser Exception occurred: " + e);
    	} catch (Exception e) {
    		Assert.fail("Exception: " + e);
    	}
    }
    
    /**
     * Description: Switch to a specified frame
     */
    public void switchToFrame(By elementBy) {
        log.info("Switch to default content.");
        driver.switchTo().defaultContent();
        log.info("Switch to frame: " + elementBy);
        driver.switchTo().frame(driver.findElement(elementBy));
        log.info("Successfully Switched to frame: " + elementBy);
    }
    
    /**
     * Check if element is present
     * @param locator
     * @return
     */
    public boolean isElementPresent(By locator) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	WebDriverWait wait = new WebDriverWait(driver, CommonConstants.DEFAULT_WEBDRIVER_TIMEOUT);
    	try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			log.error("Element is NOT present.",  e);
		} finally {
			driver.manage().timeouts().implicitlyWait(CommonConstants.DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		}
    	return false;
    }
    
    /**
     * Check if element is present
     * @param element
     * @return
     */
    public boolean isElementPresent(WebElement element) {
    	try {
    		element.isDisplayed();
    		return true;
		} catch (Exception e) {
			log.error("Element is NOT present.", e);
		}
    	return false;
    }
    
    /**
     * Check if element is visible in the page.
     * @param locator
     * @return
     */
    public boolean isElementVisible(By locator) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	WebDriverWait wait = new WebDriverWait(driver, CommonConstants.DEFAULT_WEBDRIVER_TIMEOUT);
    	try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			log.error("Element is NOT visible.",  e);
		} finally {
			driver.manage().timeouts().implicitlyWait(CommonConstants.DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		}
    	return false;
    }
    
    /**
     * Check if element is visible in the page.
     * @param element
     * @return
     */
    public boolean isElementVisible(WebElement element) {
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	WebDriverWait wait = new WebDriverWait(driver, CommonConstants.DEFAULT_WEBDRIVER_TIMEOUT);
    	try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			log.error("Element is NOT visible.",  e);
		} finally {
			driver.manage().timeouts().implicitlyWait(CommonConstants.DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		}
    	return false;
    }
    
    /**
     * Click a button
     * @param buttonElement
     */
    public void clickButton(WebElement buttonElement) {
    	if (isElementPresent(buttonElement)) {
    		buttonElement.click();
    		log.info("Button is clicked.");
    	} else {
    		log.error("Button is NOT present.");
    	}
    }
    
    /**
	* Embed a screenshot in test report
	*/
	public void embedScreenshot(Scenario scenario) {
		String outPath = "target/screenshots/" + scenario.getId() + scenario.getName() + "/" + scenario.getName().replaceAll("[; !@#$%^&()+=]", "_")+".png";
		try {
			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(outPath).getAbsoluteFile());
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
}
