package com.dante.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dante.db.entity.Product;
import com.dante.db.repository.ProductRepository;

@Service
@Transactional
public class PagingServiceImpl implements PagingService {

	private static final int PAGE_SIZE = 15;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Page<Product> getAllProduct(Integer pageNumber) {
//		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime"); 
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "productId"); 
		return productRepository.findAll(pageRequest);
	}

}
