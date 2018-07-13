package com.dante.learn.core;

import org.junit.Test;

public class TestInformationCheck {

	@Test
	public void logMemoryStatistics() {
		Runtime runtime = Runtime.getRuntime();
		int mb = 1024 * 1024;
		long freeMemoryInMB = runtime.freeMemory() / mb;
		long totalMemoryInMB = runtime.totalMemory() / mb;
		long maxMemoryInMB = runtime.maxMemory() / mb;
		String memoryStatistics = String.format(
				"System memory statistics : Used Memory: %dMB, Free Memory: %dMB, Total Memory: %dMB, Max Memory: %dMB",
				totalMemoryInMB - freeMemoryInMB, 
				freeMemoryInMB,
				totalMemoryInMB, maxMemoryInMB);
		System.out.println(memoryStatistics);
	}
	
	
}
