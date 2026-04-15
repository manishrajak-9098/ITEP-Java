package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ProductDao;
import com.spring.entity.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public void saveProduct(Product product) {
		productDao.save(product);
		
	}


	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}
	
}

