package com.smg.stepdefs;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.smg.pageobjects.TimeTrackerHomePage;

import cucumber.api.java8.En;

public class filealeaveStepdefs implements En{
	
	public filealeaveStepdefs(ScenarioHooks hooks, TimeTrackerHomePage timeTrackerHomePage) {
		When("I click the File a Leave icon$", () -> {
			timeTrackerHomePage.setDriver(hooks.getDriverNavigator(), hooks.getScenarioName());
			Assert.assertThat(timeTrackerHomePage.clickFileALeaveButton(), Matchers.equalTo(true));
		});
	}
}
