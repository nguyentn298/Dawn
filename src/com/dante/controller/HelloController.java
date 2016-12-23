package com.dante.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dante.db.entity.Product;
import com.dante.db.repository.ProductRepository;

@Controller
public class HelloController {
	
	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger logger = Logger.getLogger(HelloController.class);

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
	
	@RequestMapping(value = "productSearch", method = RequestMethod.GET)
	public String searchProduct2() {
		
		return "product";
	}
	
	@RequestMapping(value = "productDetail", method = RequestMethod.POST)
	public String searchProduct3(Model model, @RequestParam("id") int id) {
		
//		int productId = (int) id;
		
//		Product product = productRepository.findByProductId(id);
		
		// test BaseCustomRepository
		Product product = productRepository.findFirstRow();
		
		model.addAttribute("productId", product.getProductId());
		model.addAttribute("productName", product.getProductName());
		model.addAttribute("productQuantity", product.getProductQuantity());
		
		return "product";
	}
	
	@RequestMapping(value="pages/testSitemesh", method = RequestMethod.GET)
	public String testSitemesh() {
		return "/pages/test-sitemesh";
		
	}

}
