package com.dante.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogController {
	
	/**
	 * Config at log4j.properties
	 * Config multi log at LogUtil
	 */
	private static final Logger logger = Logger.getLogger(LogController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String myString(Model model) {
		model.addAttribute("test123", "This is my test with model map 123");
		logger.info("Test log info");
		logger.warn("Test log warn");
		
		return "view";
	}

	@RequestMapping(value = "/myStringAgain", method = RequestMethod.GET)
	public String myStringAgain(Model model) {
		model.addAttribute("test123", "This is my test with model map 123, Again, gain, ain, in!!");
		logger.error("This is Error message", new Exception("Testing"));
		return "view";
	}
	
	@RequestMapping(value = "myString", method = RequestMethod.GET)
	public String myString2(Model model) {
		model.addAttribute("test123", "This is my test with model map 123 my String2");
		return "view";
	}
	
	@RequestMapping("/test1")
	public ModelAndView getForm1(Model model) {
		model.addAttribute("test123", "This is my test with model map 123 my String2");
		return new ModelAndView("view");
	}
	
	@RequestMapping(value="pages/testSitemesh", method = RequestMethod.GET)
	public String testSitemesh() {
		return "/pages/test-sitemesh";
		
	}

	public static void main(String[] args) {
		logger.info("========== Test 2 =============");
	}
}
