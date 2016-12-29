package com.dante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dante.controller.validators.ProductValidator;
import com.dante.db.entity.Product;
import com.dante.db.repository.ProductRepository;

@Controller
public class ValidationController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "productSearch", method = RequestMethod.GET)
	public String searchProduct(Model model) {
		model.addAttribute("product", new Product()); 
		return "product";
	}
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute("product") Product product, BindingResult result) {
		ProductValidator productValidator = new ProductValidator();
		productValidator.validate(product, result);
		if(result.hasErrors()) {
			return "product";
		} else {
			System.out.println("Start adding");
//			productRepository.save(product);
//			System.out.println("Finish adding");
			model.addAttribute("productIdNew", product.getProductId());
			model.addAttribute("productNameNew", product.getProductName());
			model.addAttribute("productQuantityNew", product.getProductQuantity());
			
			return "product";
		}
	}
}
