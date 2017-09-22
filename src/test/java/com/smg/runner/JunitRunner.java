package com.smg.runner;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features", 
		glue = { "com.smg.stepdefs" }, 
		plugin = {
		"pretty", "json:target/output/cucumber.json",
		"com.cucumber.listener.ExtentCucumberFormatter:target/output/report.html"},
		tags = {"@loggingofhours"}, 
		monochrome = true)
public class JunitRunner {
	String reportName = "defaultReportName.html";
	
	@Before
	public void beforeClass() {
		
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows");
	}
}
