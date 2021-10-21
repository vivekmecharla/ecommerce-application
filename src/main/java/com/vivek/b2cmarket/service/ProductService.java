package com.vivek.b2cmarket.service;

import com.vivek.b2cmarket.exception.ProductNotFoundException;
import com.vivek.b2cmarket.model.Product;
import com.vivek.b2cmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductBySku(String sku) {
        List<Product> productList = productRepository.findAllBySku(sku);
        return productList;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findAllByBrand(brand);
    }

    @Override
    public List<Product> getProductsByColor(String color) {
        return productRepository.findAllByColor(color);
    }

    @Override
    public List<Product> getProductsBySize(String size) {
        return productRepository.findAllBySize(size);
    }

    @Override
    public List<Product> getProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (!productRepository.findById(product.getId()).isPresent()) {
            throw new ProductNotFoundException("Product doesn't exist");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
