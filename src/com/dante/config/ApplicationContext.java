package com.dante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.dante.web.config.WebMvcConfig;

@Configuration
@ComponentScan(
		basePackages = { "com.dante" },
		excludeFilters= {
				@ComponentScan.Filter(type=FilterType.REGEX, pattern={"com.dante.web.*"})
		})
//@Import({ ResourcesConfig.class, WebMvcConfig.class })
@Import({ ResourcesConfig.class, WebMvcConfig.class, CachingConfig.class})
//@Profile({ ProfileType.WEB })
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

	/**
		If the number of threads is less than the corePoolSize, create a new Thread to run a new task.
		If the number of threads is equal (or greater than) the corePoolSize, put the task into the queue.
		If the queue is full, and the number of threads is less than the maxPoolSize, create a new thread to run tasks in.
		If the queue is full, and the number of threads is greater than or equal to maxPoolSize, reject the task.
	*/
	
	// ThreadPoolTaskExecutor manage number of active threads.
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(2);
		pool.setMaxPoolSize(4);
		pool.setQueueCapacity(6);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
}
