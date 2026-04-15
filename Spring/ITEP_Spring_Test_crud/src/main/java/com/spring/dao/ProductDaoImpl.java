package com.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Product product) {
		 em.persist(product);
	}

	@Override
	public List<Product> getAll(){
		return em.createQuery("from Product",Product.class).getResultList();
	}
}