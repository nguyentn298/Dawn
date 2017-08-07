package com.dante.learn.cache.service;

public interface TestCacheService {
	public String testCache();
	
	public String testCache2(String myTestKey);
	
	public void refreshAllProducts();
}
