package com.smg.stepdefs;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.smg.pageobjects.EditTimeLogsPage;

import cucumber.api.java8.En;

public class loggingofhoursStepdefs implements En{
	
	public loggingofhoursStepdefs(ScenarioHooks hooks, EditTimeLogsPage editTimeLogsPage) {
		And("I am in the Edit Timelogs modal$", () -> {
			editTimeLogsPage.setDriver(hooks.getDriverNavigator(), hooks.getScenarioName());
			editTimeLogsPage.navigateToCurrentEditTimeLogs();
		});
		
		Then("I should view Edit Timelogs modal layout$", () -> {
			editTimeLogsPage.setDriver(hooks.getDriverNavigator(), hooks.getScenarioName());
			Assert.assertThat(editTimeLogsPage.validateTheEditTimeLogsColumns(), Matchers.equalTo(true));
		});
	}
}
