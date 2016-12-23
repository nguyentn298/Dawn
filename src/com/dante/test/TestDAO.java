package com.dante.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dante.config.ProfileType;
import com.dante.db.entity.Product;
import com.dante.db.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestApplicationContext.class })
@Transactional
@Rollback
@Profile(ProfileType.TEST)
public class TestDAO {
	
	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		TestDAO test = new TestDAO();
		test.findByProductId(1);
	}
	
	@Test
	public void findByProductId(int id) {
		Product product = productRepository.findByProductId(1);
		System.out.println("id: " + product.getProductId());
		System.out.println("name: " + product.getProductName());
		System.out.println("quantity: " + product.getProductQuantity());
	}

}
