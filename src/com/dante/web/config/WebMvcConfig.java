package com.dante.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.dante.config.ProfileType;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dante")
@Profile({ ProfileType.WEB })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// use for Jboss
	// extends WebMvcConfigurerAdapter
	// @Override
	// public void configureDefaultServletHandling(
	// DefaultServletHandlerConfigurer configurer) {
	// configurer.enable();
	// }

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//		registry.addResourceHandler("/decorators/**").addResourceLocations("/decorators/");
	}
}
