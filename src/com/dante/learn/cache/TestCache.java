package com.dante.learn.cache;

import net.sf.ehcache.CacheManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dante.config.ProfileType;
import com.dante.learn.cache.helper.CacheHelper;
import com.dante.learn.cache.service.TestCacheService;
import com.dante.learn.cache.service.TestNoCacheService;
import com.dante.test.TestApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationContext.class })
@ActiveProfiles(ProfileType.TEST)
public class TestCache {
	final static Logger log = LoggerFactory.getLogger(TestCache.class);
	
	ApplicationContext applicationContext;
	
	@Autowired
	TestCacheService testCache;
	
	@Autowired
	TestNoCacheService testNoCache;
	
	@Autowired
	CacheManager cacheManager;
	
	@Test
	public void testNocache() {
		log.info("test --> " + testNoCache.testNoCache());
		log.info("test --> " + testNoCache.testNoCache());
		log.info("test --> " + testNoCache.testNoCache());
		
	}

	@Test
	public void testCache() {
		System.out.println("Begin cache");
		
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		
		log.info("test --> " + testCache.testCache2("myTestKey"));
		log.info("test --> " + testCache.testCache2("myTestKey"));
		log.info("test --> " + testCache.testCache2("myTestKey"));
		
		
		
//		System.out.println("Get cache: " + cacheManager.getCache("testCache").get(0)); 
//		System.out.println("My cache name: "+ cacheManager.getCacheNames());
//		cacheManager.getCache("").g
//		testCache.refreshAllProducts();
		
		Object result = "";
		String[] names = cacheManager.getCacheNames();
		CacheHelper helper = new CacheHelper();
		System.out.println("Begin getValueFromCache");
		for (String name : names) {
			result = helper.getValueFromCache(name, "myTestKey", cacheManager);
		}
		System.out.println("Finished getValueFromCache!!");
		
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		
		System.out.println("End cache");
	}

	@Test
	public void checkBean() {
		applicationContext = new AnnotationConfigApplicationContext(TestApplicationContext.class);
		String[] myBeans = applicationContext.getBeanDefinitionNames();
		for (String string : myBeans) {
			System.out.println("My bean: " + string);
		}
		
		((AbstractApplicationContext)applicationContext).close();
	}
}
