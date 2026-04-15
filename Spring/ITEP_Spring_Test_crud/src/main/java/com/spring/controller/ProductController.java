package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.Product;
import com.spring.service.ProductService;

@Controller
public class ProductController{
	
	private ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
	model.addAttribute("product", new Product());
	return "add-product";
	}


	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
	productService.saveProduct(product);
	return "redirect:/viewAll";
	}


	@GetMapping("/viewAll")
	public String viewAll(Model model) {
	model.addAttribute("products", productService.getAllProducts());
	return "view-product";
	}
}