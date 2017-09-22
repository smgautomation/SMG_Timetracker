package com.smg.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.listener.Reporter;
import com.smg.utils.PropertyUtil;

public class TimeTrackerHomePage extends BasePage {
	private static final Logger log = LogManager.getLogger(TimeTrackerHomePage.class);
	
	@FindBy(id = "Username")
    private WebElement fld_Username;
	
	@FindBy(id = "Password")
    private WebElement fld_Password;
	
	@FindBy(id = "LoginSubmit")
    private WebElement btn_LoginSubmit;
	
	@FindBy(id = "profilelinks")
    private WebElement link_Profile;
	
	@FindBy(css = "table#TimelogsTable tr:nth-child(7) a.fileLeaveLink")
    private WebElement btn_fileLeave;
	
	@FindBy(id = "dialog-modal-leave")
    private WebElement modal_fileALeave;

	/**
	 * Login to timetracker
	 */
	public boolean loginTimetracker(String userRole) {
		driverNavigator.get(PropertyUtil.getTestDataProp("timetracker.url"));
		
		if (userRole.equalsIgnoreCase("Employee")) {
			fld_Username.sendKeys(PropertyUtil.getTestDataProp("employee.username"));
			fld_Password.sendKeys(PropertyUtil.getTestDataProp("employee.password"));
		}
		driverNavigator.clickButton(btn_LoginSubmit);
		return isUserSuccessfullyLoggedIn();
	}
	
	/**
	 * Click the File a leave button
	 */
	public boolean clickFileALeaveButton() {
		driverNavigator.clickButton(btn_fileLeave);
		if (driverNavigator.isElementPresent(modal_fileALeave)) {
			driverNavigator.embedScreenshot(scenario);
			log.info("File a Leave modal is displayed.");
			return true;
		}
		log.error("File a leave modal is NOT displayed.");
		return false;
	}
	
	/**
	 * Verify if user is successfully logged in
	 * @return
	 */
	private boolean isUserSuccessfullyLoggedIn() {
		if (driverNavigator.isElementVisible(link_Profile)) {
			Reporter.addScenarioLog("User is successfully logged in.");
			return true;
		}
		Reporter.addScenarioLog("User is NOT logged in.");
		return false;
	}
}
