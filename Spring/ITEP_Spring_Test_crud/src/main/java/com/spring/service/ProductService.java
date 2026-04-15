package com.spring.service;


import java.util.List;
import com.spring.entity.Product;


public interface ProductService {
void saveProduct(Product product);
List<Product> getAllProducts();
}