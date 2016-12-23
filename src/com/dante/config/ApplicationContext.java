package com.dante.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.dante.web.config.WebMvcConfig;


/**
 * @author Ken Duc
 */
@Configuration
@ComponentScan(
		basePackages = { "com.dante" },
		excludeFilters= {
				@ComponentScan.Filter(type=FilterType.REGEX, pattern={"com.dante.web.*"})
		})
@Import({ ResourcesConfig.class, WebMvcConfig.class })
@Profile({ ProfileType.WEB })
public class ApplicationContext {

//	@Import({ ResourcesConfig.class, CachingConfig.class, WebMvcConfig.class })
//	@Autowired
//	private IConfigurationManager configurationManager;
//
//	@PostConstruct
//	public void postConstruct() throws FileNotFoundException {
//		String demLogPath = configurationManager.getProperty(ConfigConstants.DEM_PATH_TO_LOG_FILE);
//		System.setProperty(ConfigConstants.DEM_PATH_TO_LOG_FILE, demLogPath);
//	}

}
