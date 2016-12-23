package com.dante.db.repository;

import com.dante.db.entity.Product;

// extends DawnCustomRepository<Product, Integer> to get entityManagerFactory to perform a custom CRUD
// EntityManagerFactory consist of datasource, provider, scan package ...
public class ProductRepositoryImpl extends DawnCustomRepository<Product, Integer> implements ProductCustomRepository{

}
