package com.dante.learn.cache.service;

import org.springframework.stereotype.Service;

@Service("TestNoCacheService")
public class TestNoCacheServiceImpl implements TestNoCacheService {

	@Override
	public String testNoCache() {
		slowLookupOperation();
		return "This operator has no cache!!!";

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

}
