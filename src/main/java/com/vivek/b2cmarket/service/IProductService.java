package com.vivek.b2cmarket.service;

import com.vivek.b2cmarket.exception.ProductNotFoundException;
import com.vivek.b2cmarket.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);

    List<Product> getProductBySku(String sku);

    List<Product> getAllProducts();

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByColor(String color);

    List<Product> getProductsBySize(String size);

    List<Product> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    Product updateProduct(Product product) throws ProductNotFoundException;

    void deleteProduct(Long id);
}
