package com.smg.utils;

import cucumber.api.Scenario;

public class ScenarioName {
	
	private Scenario scenario;
	
	public ScenarioName (Scenario scenario) {
		this.setScenario(scenario);
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}
