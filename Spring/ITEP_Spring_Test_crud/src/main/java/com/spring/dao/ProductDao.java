package com.spring.dao;


import java.util.List;


import com.spring.entity.Product;


public interface ProductDao {
void save(Product product);
List<Product> getAll();
}