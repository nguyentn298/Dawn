package com.dante.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="form")
public class FormController {
	
	@RequestMapping("detail")
	public String myForm(Model model) {
		return "form";
	}
	
	// for spring form, ex: <form:form>
	@ModelAttribute("twoMap")
	protected Map twoMap(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		referenceData.put("countryList", country);
		return referenceData;
	}
	
	@ModelAttribute("countryList")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		map.put("US", "United Stated");
		map.put("CHINA", "China");
		map.put("SG", "Singapore");
		map.put("MY", "Malaysia");
		return map;
	}
}
