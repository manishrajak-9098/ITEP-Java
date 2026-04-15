package com.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="productname")
private String productName;

@Column(name="category")
private String category;

@Column(name="description")
private String description;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



}