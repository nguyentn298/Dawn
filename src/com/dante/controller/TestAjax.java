package com.dante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "testAjax")
public class TestAjax {

	@RequestMapping(value = "testLoad", method = RequestMethod.GET)
	public String loadAjax() {
		return "test-jquery";
		
	}
	
	@RequestMapping(value = "/pages/testLoadPage", method = RequestMethod.GET)
	public String loadAjaxPage() {
		return "/pages/test-jquery-load";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/pages/getForm", method = RequestMethod.GET)
	public String getForm(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
		String result = "Get My name is: " + name + " and my age is: " + age;
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/pages/loadForm", method = RequestMethod.POST)
	public String loadForm(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
		String result = "Get My name is: " + name + " and my age is: " + age;
		return result;
		
	}
}
