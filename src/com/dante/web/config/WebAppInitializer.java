package com.dante.web.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.dante.config.ApplicationContext;
import com.dante.config.CommonConstants;
import com.dante.config.ProfileType;
import com.dante.config.SiteMeshFilter;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final String RESTEASY_SERVLET_NAME = "Resteasy";
	private static final String RESTEASY_SERVLET_MAPPING = "/webservices/*";
	private static final String RESTEASY_SERVLET_MAPPING_PREFIX_ATTR_NAME = "resteasy.servlet.mapping.prefix";
	private static final String RESTEASY_SERVLET_MAPPING_PREFIX_VALUE = "/webservices";
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		servletContext.setInitParameter(CommonConstants.SPRING_PROFILES_ACTIVE, ProfileType.WEB);
		
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationContext.class);

		rootContext.setServletContext(servletContext);
		
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);

		configureSiteMeshFilter(servletContext, dispatcherTypes);
		
		// Manage the life-cycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		Dynamic servlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(rootContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);

	}

	private void configureSiteMeshFilter(ServletContext servletContext, EnumSet<DispatcherType> dispatcherTypes) {
		SiteMeshFilter siteMeshFilter = new SiteMeshFilter();
		FilterRegistration.Dynamic sitemesh = servletContext.addFilter("sitemesh", siteMeshFilter);
		sitemesh.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
	}
	
	// Test config WS
//	private void configureResteasyServlet(ServletContext servletContext, WebApplicationContext rootContext) {
//		RestEasyWSServlet resteasyServlet = new RestEasyWSServlet(rootContext);
//		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(RESTEASY_SERVLET_NAME, resteasyServlet);
//		dispatcher.setInitParameter(THROW_EXCEPTION_IF_NO_HANDLER_FOUND, "true");
//		dispatcher.setLoadOnStartup(2);
//		dispatcher.addMapping(RESTEASY_SERVLET_MAPPING);
//	}
	
	// Use for Jboss
	// @Override
	// public void onStartup(ServletContext servletContext) throws
	// ServletException {
	//
	// AnnotationConfigWebApplicationContext rootContext = new
	// AnnotationConfigWebApplicationContext();
	// rootContext.register(WebMvcConfig.class);
	//
	// rootContext.setServletContext(servletContext);
	//
	// Dynamic servlet = servletContext.addServlet("dispatcher",
	// new DispatcherServlet(rootContext));
	// servlet.addMapping("*.do");
	// servlet.setLoadOnStartup(1);
	//
	// }

}
