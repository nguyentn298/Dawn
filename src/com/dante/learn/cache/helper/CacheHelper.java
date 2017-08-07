package com.dante.learn.cache.helper;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheHelper {
	
	final Logger log = LoggerFactory.getLogger(CacheHelper.class);
	
	public void clearAllCache() {

	}
	
	public Object getValueFromCache(String name, String value, CacheManager cacheManager) {
		Object result = null;
		
		Cache cache = cacheManager.getCache(name);
		if (cache.isKeyInCache("myTestKey")) {
			Element cacheElement = cache.get("myTestKey");
			if (cacheElement == null) {
				log.info("CacheName: " + name + "has cacheElement = null");
				return null;
			} else {
				
				result = cacheElement.getValue();
				log.info("CacheName: " + name + " has result [ " +  result + " ]");
			}
		} else {
			log.info("CacheName: " + name + " isKeyInCache = null");
		}
		return result;
	}
}
