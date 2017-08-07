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

	/**
	 * Cache [name, key, value]
	 * 1 name has a pair of key and value
	 * Example: 
	 * 		name: testCache2
	 * 		key: myTestKey
	 * 		value: This operator of testCache222 successful!!!
	 */
	@Test
	public void testCache() {
		System.out.println("Begin cache");
		
		/**
		 * Test without key
		 */
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		log.info("test --> " + testCache.testCache());
		
		/**
		 * Test with key
		 */
		log.info("test --> " + testCache.testCache2("myTestKey"));
		log.info("test --> " + testCache.testCache2("myTestKey"));
		log.info("test --> " + testCache.testCache2("myTestKey"));
		
//		testCache.refreshAllProducts();
		Object result = null;
		
		CacheHelper helper = new CacheHelper();
		
//		helper.clearAllCache(cacheManager);
		
		System.out.println("Begin getValueFromCache");
		
		String key = "myTestKey";
		String[] names = cacheManager.getCacheNames();
		for (String name : names) {
			result = helper.getValueFromCache(name, key, cacheManager);
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
