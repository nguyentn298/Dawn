package com.dante.db.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dante.db.entity.Product;

// It's main repository, use CrudRepository to perform a CrudRepository CRUD
// Extend ProductCustomRepository.java to get entityManagerFactory (because ProductCustomRepository extends IBaseRepository)
public interface ProductRepository extends ProductCustomRepository, CrudRepository<Product, Integer> {

	@Query("FROM Product product WHERE productId = ?")
	public Product findByProductId(int productId);
}
