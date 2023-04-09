package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product entity);

    List<Product> findAll();

    Optional<Product> findById(Long aLong);

    void deleteById(Long aLong);

    List<Product> findProductByCategoryId(Long id);
}
