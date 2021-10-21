package com.vivek.b2cmarket.controller;

import com.vivek.b2cmarket.exception.ProductNotFoundException;
import com.vivek.b2cmarket.model.Product;
import com.vivek.b2cmarket.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Cacheable("products")
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Cacheable(value = "products", key = "#sku")
    @GetMapping("/sku/{sku}")
    public List<Product> getProductsBySku(@PathVariable String sku) {
        return productService.getProductBySku(sku);
    }

    @Cacheable(value = "products", key = "#brand")
    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        return productService.getProductsByBrand(brand);
    }

    @Cacheable(value = "products", key = "#color")
    @GetMapping("/color/{color}")
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getProductsByColor(color);
    }

    @Cacheable(value = "products", key = "#size")
    @GetMapping("/size/{size}")
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getProductsBySize(size);
    }

    @CachePut("products")
    @PostMapping("/create")
    public Product create(@Validated @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @CachePut("products")
    @PutMapping("/update")
    public Product update(@Validated @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(product);
    }

    @CacheEvict("products")
    @DeleteMapping("/delete/{id}")
    public void delete(@RequestBody Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/price/")
    public List<Product> getProductsByPrice(@Param("min") BigDecimal minPrice, @Param("max") BigDecimal maxPrice) {
        return productService.getProductsByPrice(minPrice, maxPrice);
    }
}
