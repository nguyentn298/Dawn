package com.dante.db.repository;

import com.dante.db.entity.Product;

//	Extends DawnCustomRepository<Product, Integer> to get entityManagerFactory to perform a custom CRUD (if not extends, we must be implements all methods of IBaseRepository)
//	because ProductCustomRepository extends IBaseRepository, and don't get entity ManagerFactory
//	It's got entity ManagerFactory from BaseCustomRepository implements IBaseRepository above
// =======================================
//	EntityManagerFactory consist of datasource, provider (hibernate), scan package ...
public class ProductRepositoryImpl extends DawnCustomRepository<Product, Integer> implements ProductCustomRepository{

}
