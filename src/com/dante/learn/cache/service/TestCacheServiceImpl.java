package com.dante.learn.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("TestCacheService")
public class TestCacheServiceImpl implements TestCacheService {
	
	@Override
	@Cacheable(value = "testCache")
	public String testCache() {
		slowLookupOperation();
		System.out.println("Caching!!!!");
		return "This operator of testCache successful!!!";

	}

	@Override
	@Cacheable(value = "testCache2", key = "#myTestKey")
	public String testCache2(String myTestKey) {
		slowLookupOperation();
		System.out.println("Caching222!!!!");
		return "This operator of testCache222 successful!!!";
	}
	
	@Override
	@CacheEvict(value = "testCache", allEntries = true)
	public void refreshAllProducts() {
		// This method will remove all 'products' from cache, say as a result of
		// flush API call.
		System.out.println("Refreshing!!!!");
	} 
	
	public void slowLookupOperation() {
		long time = 2000L;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end- start);
	}
}
