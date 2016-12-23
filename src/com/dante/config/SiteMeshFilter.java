package com.dante.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// Get path from requestmapping of controller
		builder.addDecoratorPath("/*", "/WEB-INF/decorators/default.jsp").addExcludedPath("/pages/*").addExcludedPath("/resources/*");
	}

}
