package com.smg.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.smg.utils.DateUtil;

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
		if (validateTheFields(expectedColumns, list_EditTimeLogsColumn)) {
			driverNavigator.embedScreenshot(scenario);
			return true;
		}
		return false;
	}
	
	public boolean validateTheFields(List<String> expectedList, List<WebElement> actualWebElementList) {
		log.entry();
		for (int i = 0; i <= expectedList.size(); i++) {
			if (actualWebElementList.get(i).getText().equalsIgnoreCase(expectedList.get(i))) {
				log.info("Actual {} is equal to the expected value {}.", 
						actualWebElementList.get(i).getText().replace("\n", "").replace("\r", ""), 
						expectedList.get(i));
			} else {
				log.error("Actual {} is NOT equal to the expected value {}.", actualWebElementList.get(i).getText(), expectedList.get(i));
				return false;
			}
		}
		log.exit();
		return true;
	}
	
	public List<String> getTheTextOfTheListElement(List<WebElement> webElementList) {
		List<String> list = new ArrayList<>();
		for(WebElement element : webElementList) {
			log.info("Get the text of the element. {}", element.getText());
			list.add(element.getText());
		}
		return list;
	}
}
