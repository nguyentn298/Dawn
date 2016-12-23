package com.dante.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dante.constants.ConfigConstants;
import com.dante.service.IConfigurationManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.dante.db.repository",
		entityManagerFactoryRef = "ideaEntityManagerFactory",
		transactionManagerRef = "ideaTransactionManager")
public class DawnPersistenceConfig {

		private static final String HIBERNATE_MY_SQL5_DIALECT = "org.hibernate.dialect.MySQL5Dialect";

		private static final String DAWN_SCAN_PACKAGE = "com.dante.db";

		@Autowired
		private IConfigurationManager configurationManager;

		// Add lib: spring-data-commons-1.11.2.RELEASE
		@Bean
		PlatformTransactionManager ideaTransactionManager() {
			return new JpaTransactionManager(ideaEntityManagerFactory().getObject());
		}

		@Bean
		LocalContainerEntityManagerFactoryBean ideaEntityManagerFactory() {
			
			// JpaVendorAdapter implementation for Hibernate EntityManager
			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			vendorAdapter.setGenerateDdl(false);
			vendorAdapter.setShowSql(false);
			vendorAdapter.setDatabasePlatform(HIBERNATE_MY_SQL5_DIALECT);

			LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

			// Datasource
			factoryBean.setDataSource(ideaDataSource());
			
			// Provider
			factoryBean.setJpaVendorAdapter(vendorAdapter);
			
			// Declare entity for using JPA
			factoryBean.setPackagesToScan(DAWN_SCAN_PACKAGE);

			return factoryBean;
		}

		@Bean
		DataSource ideaDataSource() {
			ComboPooledDataSource ideaDataSource = new ComboPooledDataSource();
			String driverClass = configurationManager.getProperty(ConfigConstants.DAWN_DRIVER_CLASS_NAME);
			try {
				ideaDataSource.setDriverClass(driverClass);
			} catch (PropertyVetoException e) {
				throw new BeanCreationException(String.format("Failed to set Drive Class of DataSource to '%s'", driverClass), e);
			}
			ideaDataSource.setJdbcUrl(configurationManager.getProperty(ConfigConstants.DAWN_DB_URL));
			ideaDataSource.setUser(configurationManager.getProperty(ConfigConstants.DAWN_DB_USER));
			ideaDataSource.setPassword(configurationManager.getProperty(ConfigConstants.DAWN_DB_PASS));

			ideaDataSource.setAcquireIncrement(2);
			ideaDataSource.setMinPoolSize(2);
			ideaDataSource.setMaxPoolSize(30);
			ideaDataSource.setMaxIdleTime(5000);
			ideaDataSource.setMaxStatements(100);
			ideaDataSource.setIdleConnectionTestPeriod(300);

			return ideaDataSource;
		}

	}

