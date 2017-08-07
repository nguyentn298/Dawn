package com.dante.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {
	protected final Log log = LogFactory.getLog(getClass());
	
	public void setLogPath(String path) {
		System.setProperty("file.name", path);
	}
}
