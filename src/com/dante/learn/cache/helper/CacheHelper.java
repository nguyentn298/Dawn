package com.dante.learn.cache.helper;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheHelper {
	
	final Logger log = LoggerFactory.getLogger(CacheHelper.class);
	
	public void clearAllCache() {

	}
	
	/**
	 * Cache [name, key, value]
	 */
	public Object getValueFromCache(String name, String key, CacheManager cacheManager) {
		Object value = null;
		
		Cache cache = cacheManager.getCache(name);
		if (cache == null) {
			throw new RuntimeException("Could not find any cache with name '" + name + "'.");
		};
		
		if (cache.isKeyInCache(key)) {
			Element cacheElement = cache.get("myTestKey");
			if (cacheElement == null) {
				log.info("CacheName: " + name + "has cacheElement = null");
				return null;
			} else {
				
				value = cacheElement.getValue();
				log.info("CacheName: " + name + " has result [ " +  value + " ]");
			}
		} else {
			log.info("CacheName: " + name + " isKeyInCache = null");
		}
		return value;
	}
	
	public void putToCache(String name, String key, Object value, CacheManager cacheManager){
		Element newCacheElement = new Element(key, (Serializable) value);
		Cache cache = cacheManager.getCache(name);
		cache.put(newCacheElement);
	}
	
	public void clearAllCache(CacheManager cacheManager) {
		cacheManager.removeAllCaches();
	}
	
}
