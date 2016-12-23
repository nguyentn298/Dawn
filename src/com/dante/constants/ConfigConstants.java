package com.dante.constants;

public interface ConfigConstants {

	public static final String DAWN_DRIVER_CLASS_NAME = "dawn.db.driver.class.name";
	public static final String DAWN_DB_USER = "dawn.database.username";
	public static final String DAWN_DB_PASS = "dawn.database.password";
	public static final String DAWN_DB_URL = "dawn.database.url";
	
	interface Uri {
		final String CLASSPATH_CONFIG = "classpath:/com/dante/config/config.properties";

		final String COMMON_CONFIG_FILE = "dem_common_config_file_path";
		final String MACHINE_CONFIG_FILE = "dem_machine_config_file_path";
	}
}
