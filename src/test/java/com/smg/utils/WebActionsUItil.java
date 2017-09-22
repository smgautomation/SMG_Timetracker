package com.smg.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class WebActionsUItil {
	private static final Logger log = LogManager.getLogger(WebActionsUItil.class);
	
	/**
	 * Validate the fields
	 * @param expectedList
	 * @param actualWebElementList
	 * @return
	 */
	public static boolean validateTheFields(List<String> expectedList, List<WebElement> actualWebElementList) {
		log.entry();
		for (int i = 0; i <= expectedList.size() - 1; i++) {
			String actual = actualWebElementList.get(i).getText().replaceAll("[\\t\\n\\r]+", " ");
			String expected = expectedList.get(i);
			if (actual.equalsIgnoreCase(expected)) {
				log.info("Actual {} is equal to the expected value {}.", 
						actual, 
						expected);
			} else {
				log.error("Actual {} is NOT equal to the expected value {}.", actual, expected);
				return false;
			}
		}
		log.exit();
		return true;
	}
	
	/**
	 * Get the text of the list Webelement
	 * @param webElementList
	 * @return
	 */
	public static  List<String> getTheTextOfTheListElement(List<WebElement> webElementList) {
		List<String> list = new ArrayList<>();
		for(WebElement element : webElementList) {
			log.info("Get the text of the element. {}", element.getText());
			list.add(element.getText());
		}
		return list;
	}

}
