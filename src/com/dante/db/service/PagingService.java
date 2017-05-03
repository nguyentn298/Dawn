package com.dante.db.service;

import org.springframework.data.domain.Page;

import com.dante.db.entity.Product;

public interface PagingService {
	Page<Product> getAllProduct(Integer pageNumber);
	
	Page<Product> getAllProduct(Integer pageNumber, String orderColumn, String orderType);
}
