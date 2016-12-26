package com.dante.db.repository;

import com.dante.db.IBaseRepository;
import com.dante.db.entity.Product;

// extends IBaseRepository to use custom method on BaseCustomRepository (BaseCustomRepository implements IBaseRepository)
public interface ProductCustomRepository extends IBaseRepository<Product, Integer> {

}
