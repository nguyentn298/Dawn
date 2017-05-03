package com.dante.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	
	@Override
	public Page<Product> getAllProduct(Integer pageNumber, String orderType, String orderColumn) {
//		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime"); 
		
//		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "productId");
		PageRequest pageRequest = null;
		if(orderColumn == null || orderColumn.isEmpty()) {
			pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Sort.Direction.ASC, "productId"));
		} else {
			if(orderType.equalsIgnoreCase("ASC")) {
				pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Sort.Direction.DESC, orderColumn));
			} else {
				pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Sort.Direction.ASC, orderColumn));
			}
		}
		
		return productRepository.findAll(pageRequest);
	}

}
