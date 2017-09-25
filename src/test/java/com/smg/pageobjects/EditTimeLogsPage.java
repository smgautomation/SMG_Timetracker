package com.smg.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.smg.utils.DateUtil;
import com.smg.utils.WebActionsUItil;

public class EditTimeLogsPage extends BasePage{
	private static final Logger log = LogManager.getLogger(EditTimeLogsPage.class);
	
	/* Page Elements */
	@FindBy(id = "ui-dialog-title-popupEditTimeLogs")
    private WebElement form_EditTimeLogs;
	
	@FindBys(value = @FindBy (css = "table#tblManualEditTimeLogs tr th"))
	private List<WebElement> list_EditTimeLogsColumn;
	
	/* Methods */
	
	/**
	 * Navigate to Edit Timelogs page of the current date
	 */
	public void navigateToCurrentEditTimeLogs() {
		log.entry();
		By dateLocator = By.xpath("//table[@id='TimelogsTable']//a[text()='" + DateUtil.getDateToday("M/d/yyyy") + "']");
		driverNavigator.clickButton(dateLocator);
		if (driverNavigator.isElementVisible(form_EditTimeLogs)) {
			log.info("Edit Timelogs modal is displayed.");
			driverNavigator.embedScreenshot(scenario);
		} else {
			log.error("Edit Timelogs modal is NOT displayed.");
			Assert.fail();
		}
		log.exit();
	}
	
	/**
	 * Validate the columns of the Edit Timelogs page
	 * @return
	 */
	public boolean validateTheEditTimeLogsColumns() {
		List<String> expectedColumns = new ArrayList<>();
		expectedColumns.add("Date");
		expectedColumns.add("Shift");
		expectedColumns.add("Time In Time(hh:mm AM/PM)");
		expectedColumns.add("Time Out Time(hh:mm AM/PM)");
		expectedColumns.add("Remarks");
		expectedColumns.add("Reason for TITO Override");
		if (WebActionsUItil.validateTheFields(expectedColumns, list_EditTimeLogsColumn)) {
			driverNavigator.embedScreenshot(scenario);
			return true;
		}
		return false;
	}
}
