package com.smg.utils;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyUtil {
	public static PropertiesConfiguration prop = null;
	
	public static String getProp(String propertyPath, String propertyName) {
		String propertyValue = null;
		try {
            File file = new File(propertyPath);
            String propsFileName = file.getAbsolutePath();
            prop = new PropertiesConfiguration(propsFileName);
            propertyValue = prop.getProperty(propertyName).toString();
        } catch (Exception e) {
        	System.out.println("Error getting the property file.");
        }
		return propertyValue;
	}
	
	public static String getTestDataProp(String propertyName) {
		String propertyValue = null;
		try {
            File file = new File("src/test/resources/properties/test-data.properties");
            String propsFileName = file.getAbsolutePath();
            prop = new PropertiesConfiguration(propsFileName);
            propertyValue = prop.getProperty(propertyName).toString();
        } catch (Exception e) {
        	System.out.println("Error getting the property file.");
        }
		return propertyValue;
	}
	
	public static String getConfig(String propertyName) {
		String propertyValue = null;
		try {
            File file = new File("src/test/resources/properties/config.properties");
            String propsFileName = file.getAbsolutePath();
            prop = new PropertiesConfiguration(propsFileName);
            propertyValue = prop.getProperty(propertyName).toString();
        } catch (Exception e) {
        	System.out.println("Error getting the property file.");
        }
		return propertyValue;
	}
}
