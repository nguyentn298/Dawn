package com.dante.test;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dante.config.DawnPersistenceConfig;
import com.dante.config.ResourcesConfig;
import com.dante.constants.ConfigConstants;
import com.dante.service.IConfigurationManager;

@Configuration
@ComponentScan(basePackages = { "com.dante" })
@Import({ DawnPersistenceConfig.class, ResourcesConfig.class })
public class TestApplicationContext {

	static final String TEST_LOG4J_FILE = "test-log4j.xml";

	@Autowired
	private IConfigurationManager configurationManager;

	@PostConstruct
	public void postConstruct() {
//		String demLogPath = configurationManager.getProperty(ConfigConstants.DEM_PATH_TO_LOG_FILE);
//		System.setProperty(ConfigConstants.DEM_PATH_TO_LOG_FILE, demLogPath);

//		URL logConfigURL = getClass().getResource(TEST_LOG4J_FILE);
//		DOMConfigurator.configure(logConfigURL);
	}

}
