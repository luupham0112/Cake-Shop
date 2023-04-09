package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Product;
import com.phanmem.cakeshop.repository.ProductRepository;
import com.phanmem.cakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public List<Product> findProductByCategoryId(Long id) {
        return productRepository.findProductsByCategoryId(id);
    }
}
