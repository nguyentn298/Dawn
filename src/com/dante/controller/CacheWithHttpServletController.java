package com.dante.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dante.learn.cache.helper.CacheHelper;
import com.dante.learn.cache.service.TestCacheService;

@Controller
@RequestMapping("/cached")
public class CacheWithHttpServletController {
	
	@Autowired
	CacheHelper cacheHelper;
	
	@Autowired
	TestCacheService testCache;
	/**
	 * Use HashMap to test (jsp: ${myDanteModels.message} )
	 */
	
	@RequestMapping("/testClearCached")
	public ModelAndView testClearCache(HttpServletRequest request, HttpServletResponse response) {
		String result = cacheHelper.performCacheRequest(request, response);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", result);
		return new ModelAndView("testHttpServlet", "myDanteModels", model);
	}
	
	@RequestMapping("/testCache")
	public void testCache() {
		testCache.testCache();
		testCache.testCache2("myTestKey");
	}
	
}
