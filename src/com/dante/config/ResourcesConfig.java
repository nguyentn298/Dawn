package com.dante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.dante.constants.ConfigConstants;

@PropertySources({
	@PropertySource(name="classPathConfig", value=ConfigConstants.Uri.CLASSPATH_CONFIG)
})
public class ResourcesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		return configurer;
	}

}

