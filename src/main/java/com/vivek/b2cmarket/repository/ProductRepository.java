package com.vivek.b2cmarket.repository;

import com.vivek.b2cmarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySku(String sku);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByColor(String color);

    List<Product> findAllBySize(String size);

    List<Product> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
