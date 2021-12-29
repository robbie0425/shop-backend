package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.Product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO {
  public Product findOne(Long productId);

  public List<Product> findAll();

  public List<Product> findProduct(String searchInput);

  public int insert(Product product);

  public int update(Product product);

  public int delete(Long productId);
}