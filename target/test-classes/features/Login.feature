@web
Feature: Leave Application
	As an employee
	I want to file a leave
	
Scenario: Successful filing of General Leave
	Given I am logged in as an employee
	When I click the File A Leave icon
	
	Scenario: UnSuccessful filing of General Leave
	Given I am logged in as an employees
	When I click the File A Leave icon
	
	Scenario: UnSuccessful filing of General Leave
	Given I am logged in as an employees
	When I click the File A Leave icon
