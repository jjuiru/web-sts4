package com.mohaji;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohaji.model.Product;
import com.mohaji.repository.ProductMapper;
import com.mohaji.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Sb0327MyBatisTestApplicationTests {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
		System.out.println(productMapper.count());
		log.debug("products 레코드 수 :{}",productMapper.count());
		log.info("products 레코드 수 :{}",productMapper.count());
	}
	
	@Test
	public void getProductById() {
		Product product= productService.getProductById(1L); //long 타입
		log.debug("product: {}",product);
		log.info("product: {}",product);
	}
	
	@Test
	public void getAllProducts() {
		List<Product>products = productService.getAllProducts(); 
		log.debug("product: {}",products);
		log.info("product: {}",products);
	}
	
	@Test
	public void addProduct() {
		productService.addProduct(new Product("쿤달 샴푸", 7900)); 
		productService.addProduct(new Product("마스크팩", 1000)); 
		productService.addProduct(new Product("티셔트", 5900)); 
	}	
}
