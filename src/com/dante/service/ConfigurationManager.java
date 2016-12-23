package com.dante.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager implements IConfigurationManager {
//	static final Log log = LogFactory.getLog(ConfigurationManager.class);
	
	static final int DEFAULT_INT_VALUE = 0;

	@Autowired
	Environment environment;

	@Override
	public String getProperty(String propertyName) {
		return this.environment.getProperty(propertyName);
	}

	@Override
	public int getIntProperty(String propertyName) {
		String value = getProperty(propertyName);
		if (value == null) {
			return DEFAULT_INT_VALUE;
		}
		return NumberUtils.toInt(value, DEFAULT_INT_VALUE);
	}
	
	public static void main(String[] args) {
		String test = "123";
		int i = NumberUtils.toInt(test, DEFAULT_INT_VALUE);
		System.out.println("this i is: " + i);
	}
}
