package com.dante.config;

import java.beans.PropertyVetoException;
import java.util.Set;

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
import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.dante.db.repository",
		entityManagerFactoryRef = "ideaEntityManagerFactory",
		transactionManagerRef = "ideaTransactionManager")
public class DawnPersistenceConfig {

		/**
		  		entityManagerFactoryRef in @EnableJpaRepositories
				Explicitly wire the EntityManagerFactory to be used with the repositories being detected by the repositories element. 
				Usually used if multiple EntityManagerFactory beans are used within the application. 
				If not configured we will automatically lookup the EntityManagerFactory bean with the name entityManagerFactory in the ApplicationContext.
		 */
		private static final String HIBERNATE_MY_SQL5_DIALECT = "org.hibernate.dialect.MySQL5Dialect";

		private static final String DAWN_SCAN_PACKAGE = "com.dante.db";

		@Autowired
		private IConfigurationManager configurationManager;

		// Add lib: spring-data-commons-1.11.2.RELEASE
		@Bean
		PlatformTransactionManager ideaTransactionManager() {
			return new JpaTransactionManager(ideaEntityManagerFactory().getObject());
		}

		/**
		 * JPA is a specification for accessing, persisting and managing the data between Java objects and the relational database.
		 *  As the definition says its API, it is only the specification. There is no implementation for the API. 
		 *  JPA specifies the set of rules and guidelines for developing the interfaces that follows standard.
		 *  Straight to the point : JPA is just guidelines to implement the Object Relational Mapping (ORM) and there is no underlying code for the implementation. 
		 *  Where as, Hibernate is the actual implementation of JPA guidelines.
		 *  When hibernate implements the JPA specification, this will be certified by the JPA group upon following all the standards mentioned in the specification.
		 *  For example, JPA guidelines would provide information of mandatory and optional features to be implemented as part of the JPA implementation.
		 * @return
		 */
		@Bean
		LocalContainerEntityManagerFactoryBean ideaEntityManagerFactory() {
			
			// JpaVendorAdapter implementation for Hibernate EntityManager
			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			vendorAdapter.setGenerateDdl(false);
			vendorAdapter.setShowSql(false);
		
			/**
			 * Hibernate.dialect property tells Hibernate to generate the appropriate SQL statements for the chosen database.
			 * It you use SQl Server, you maybe change it to org.hibernate.dialect.SQLServerDialect
			 * one db go with one dialect ( 1 - 1)
			 */
			vendorAdapter.setDatabasePlatform(HIBERNATE_MY_SQL5_DIALECT);

			/**
			 * EntityManagerFactory implements interface EntityManager.
			 * Interface EntityManager: persist( insert ), merge ( update sql ), remove row OR Entity transaction, Query, 
			 * And add more additional feature
			 */
			LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

			// Datasource ( 1 datasource need 1 Entitymanager )
			factoryBean.setDataSource(ideaDataSource());
			
			// Provider ( Hibernate ): Using Hibernate to implement EntityManager of JPA
			factoryBean.setJpaVendorAdapter(vendorAdapter);
			
			// Declare entity for using JPA
			factoryBean.setPackagesToScan(DAWN_SCAN_PACKAGE);

			/**
			 * NOTE: JPA is just API to describe Query, storage, and manage data and database (ORM)
			 * Its interface
			 * JPA is the dance, hibernate is the dancer
			 */
			return factoryBean;
		}

		/**
		 * Some popular datasource:
		 * 1/ DriverManagerDataSource (org.springframework.jdbc.datasource.DriverManagerDataSource): 
				  This class is not an actual connection pool; it does not actually pool Connections. 
				  It just serves as simple replacement for a full-blown connection pool, implementing the same standard interface, but creating new Connections on every call. 
				  
				  Useful for test or standalone environments outside of a J2EE container, either as a DataSource bean in a corresponding ApplicationContext 
				  or in conjunction with a simple JNDI environment. 
				  Pool-assuming Connection.close() calls will simply close the Connection, so any DataSource-aware persistence code should work. 
		 * 2/ BasicDataSource (org.apache.commons.dbcp.BasicDataSource): has some issues about reconnection, DBCP consistently generated exceptions into our test application
		 * 3/ ComboPooledDataSource (com.mchange.v2.c3p0.ComboPooledDataSource): support hibernate

			  Commons DBCP's BasicDataSource and C3P0's ComboPooledDataSource are full connection pool beans, 
			  supporting the same basic properties as this class plus specific settings (such as minimal/maximal pool size etc). 
		 */
		@Bean
		DataSource ideaDataSource() {
			
			/**
			 * (connection pool (restrict from connection openning and closing, increase performance ))
			 * Connection Pooling addresses this problem by creating a pool of connections and storing them in an Object Pool. 
			 * Whenever the client requests for some data, an idle connection object is retrieved from the connection pool and the database is queried against this connection. 
			 * If a connection object is not available and the maximum pool limit is not reached a new connection object is returned. 
			 * If there are no idle connection objects available and the maximum open connection limit for the pool has been reached, the request is queued up. 
			 * Once any of the requests releases the connection the requests in the queue can use that object
			 */
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

			// This is connection pool (optional)
			ideaDataSource.setAcquireIncrement(2);
			ideaDataSource.setMinPoolSize(2);
			ideaDataSource.setMaxPoolSize(30);
			ideaDataSource.setMaxIdleTime(5000);
			ideaDataSource.setMaxStatements(100);
			ideaDataSource.setIdleConnectionTestPeriod(300);
			
			Set set = C3P0Registry.getPooledDataSources();
		    int sz = set.size();
		    if ( sz == 1 ) {
		    	System.out.println("My pool: " + set.iterator().next());
		    }

			return ideaDataSource;
		}

	}

