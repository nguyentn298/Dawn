package com.dante.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dante.controller.validators.ProductValidator;
import com.dante.db.entity.Product;
import com.dante.db.repository.ProductRepository;
import com.dante.db.service.PagingService;
import com.dante.util.DateUtil;

@Controller
public class ValidationController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PagingService pagingService;
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}
	
//	@InitBinder
//	public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
//	    new PropertyEditorSupport() {
//	        public void setAsText(String value) {
//	            try {
//	                Date parsedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(value);
//	                setValue(new Timestamp(parsedDate.getTime()));
//	            } catch (ParseException e) {
//	                setValue(null);
//	            }
//	        }
//	    });
//	}
	
	@RequestMapping(value = "productSearch", method = RequestMethod.GET)
	public String searchProduct(Model model) {
		model.addAttribute("product", new Product()); 
		return "product";
	}
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute("product") Product product, BindingResult result) {
		ProductValidator productValidator = new ProductValidator();
		
		if (product.getProductDateUpdated() == null) {
			product.setProductDateUpdated(new Date());
		} else {
				Date myDate = DateUtil.convertDateToDate(product.getProductDateUpdated());
				product.setProductDateUpdated(myDate);
		}
		
		productValidator.validate(product, result);
		if(result.hasErrors()) {
			return "product";
		} else {
			System.out.println("Start adding");
			productRepository.save(product);
			System.out.println("Finish adding");
			model.addAttribute("productIdNew", product.getProductId());
			model.addAttribute("productNameNew", product.getProductName());
			model.addAttribute("productQuantityNew", product.getProductQuantity());
			
			return "product";
		}
	}
	
	// Table
	@RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
	public String getProductPage(@PathVariable Integer pageNumber, Model model) {
	    Page<Product> page = pagingService.getAllProduct(pageNumber);
	    int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    
	    model.addAttribute("productTables", page.getContent());
	    model.addAttribute("productPage", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    return "product-list";
	}
}
