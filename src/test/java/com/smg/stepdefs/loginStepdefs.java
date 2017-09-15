package com.smg.stepdefs;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.smg.pageobjects.TimeTrackerHomePage;

import cucumber.api.java8.En;

public class loginStepdefs implements En{
	
	public loginStepdefs(ScenarioHooks hooks, TimeTrackerHomePage timeTrackerHomePage) {
		Given("I am logged in as an (.*)$", (String userRole) -> {
			timeTrackerHomePage.setDriver(hooks.getDriverNavigator());
			Assert.assertThat(timeTrackerHomePage.loginTimetracker(userRole), Matchers.equalTo(true));
		});
		
		When("I click the File A Leave icon$", () -> {
			Assert.assertThat(timeTrackerHomePage.clickFileALeaveButton(), Matchers.equalTo(true));
		});
	}
}
