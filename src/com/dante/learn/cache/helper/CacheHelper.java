package com.dante.learn.cache.helper;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {
	
	final Logger log = LoggerFactory.getLogger(CacheHelper.class);
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public final static String FIELD_SEPARATOR = "@@";
	
	private static final String CACHE_REQUEST = "siteCache";
	private static final String GET_CACHE_INFO = "getInfo";
	private static final String CLEAR_CACHE = "clearCache";
	private static final String ACTION = "action";
	private static final String CACHE_NAME = "cacheName";
	public static final String ALL_CACHES = "*";
	
	@Autowired
	protected CacheManager cacheManager;
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * Cache [name, key, value]
	 */
	public Object getValueFromCache(String name, String key) {
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
	
	public void putToCache(String name, String key, Object value){
		Element newCacheElement = new Element(key, (Serializable) value);
		Cache cache = cacheManager.getCache(name);
		cache.put(newCacheElement);
	}
	
	public void clearAllCache(CacheManager cacheManager) {
		cacheManager.removeAllCaches();
	}
	
	public String clearCache(String cacheName) {
		StringBuffer buffer = new StringBuffer();
		if (ALL_CACHES.equals(cacheName)) {
			String[] cacheNames = cacheManager.getCacheNames();
			for (int i = 0; i < cacheNames.length; i++) {
				cacheName = cacheNames[i];
				Cache cache = cacheManager.getCache(cacheName);
				int noOfItems = cache.getSize();
				cache.removeAll();
				buffer.append("Dante test clearCache ==== " + LINE_SEPARATOR + "Cleared cache \"" + cacheName + "\" successfully [" + noOfItems + "].");
			}
		} else {
			Cache cache = cacheManager.getCache(cacheName);
			if (cache == null) {
				return "Cache \"" + cacheName + "\" not found.";
			}
			int noOfItems = cache.getSize();
			cache.removeAll();
			buffer.append("Dante test clearCache ==== " + LINE_SEPARATOR + "Cleared cache \"" + cacheName + "\" successfully [" + noOfItems + "].");
		}

		return buffer.toString();
	}
	
	public String getCacheInfo(String cacheName) {
		CacheManager cacheManager = getCacheManager();
		StringBuffer buffer = new StringBuffer();
		if (ALL_CACHES.equals(cacheName)) {
			String[] cacheNames = cacheManager.getCacheNames();
			for (int i = 0; i < cacheNames.length; i++) {
				cacheName = cacheNames[i];
				Cache cache = cacheManager.getCache(cacheName);
				int noOfItems = cache.getSize();
				buffer.append("Dante test getCacheInfo ==== " + LINE_SEPARATOR + "Cache \"" + cacheName + "\": [Total items = " + noOfItems + "].");
			}
		} else {
			Cache cache = cacheManager.getCache(cacheName);
			if (cache == null) {
				return "Cache \"" + cacheName + "\" not found.";
			}
			int noOfItems = cache.getSize();
			buffer.append("Dante test getCacheInfo ==== " + LINE_SEPARATOR + "Cache \"" + cacheName + "\": [Total items = " + noOfItems + "].");
		}

		return buffer.toString();
	}
	
	public static String getStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
	}
	
	public String performCacheRequest(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		try {
			String action = request.getParameter(ACTION);
			String cacheName = request.getParameter(CACHE_NAME);
			if (CLEAR_CACHE.equalsIgnoreCase(action)) {
				result = clearCache(cacheName);
			} else if (GET_CACHE_INFO.equalsIgnoreCase(action)) {
				result = getCacheInfo(cacheName);
			} else {
				result = "Dante test performCacheRequest ==== Missing or invalid action parameter: " + action;
			}
		} catch (Exception e) {
			result = "Dante test performCacheRequest ==== Error when performing " + CACHE_REQUEST + " request: " + getStackTrace(e);
		}

		return result;
	}
}
