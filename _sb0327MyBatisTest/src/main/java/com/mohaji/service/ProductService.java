package com.mohaji.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohaji.model.Product;
import com.mohaji.repository.ProductMapper;

import java.util.List;

@Service 
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getProductById(Long id) {

        return productMapper.selectProductById(id);
    }

    public List<Product> getAllProducts() {

        return productMapper.selectAllProducts();
    }

    @Transactional // 인서트 실패시 롤백
    public void addProduct(Product product) {

        productMapper.insertProduct(product);
    }
}
