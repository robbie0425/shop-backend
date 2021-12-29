package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;

@RestController
@CrossOrigin(value = "http://localhost:3000", maxAge = 1800, allowedHeaders = "*")
public class ProductController {
   @Autowired
   ProductDAO dao;

   @GetMapping(value = { "/product" })
   public List<Product> retrieveProduct() throws SQLException {
      return dao.findAll();
   }

   @PostMapping(value = { "/product/search" })
   public List<Product> retrieveProducts(@RequestBody String searchInput) throws SQLException {
      return dao.findProduct(searchInput);
   }

   @GetMapping(value = { "/product/{productId}" })
   public Product retrieveOneProduct(@PathVariable("productId") Long productId) throws SQLException {
      return dao.findOne(productId);
   }

   @PostMapping(value = "/product")
   public void processFormCreate(@RequestBody Product product) throws SQLException {
      dao.insert(product);
   }

   @PutMapping(value = "/product")
   public void processFormUpdate(@RequestBody Product product) throws SQLException {
      dao.update(product);
   }

   @DeleteMapping(value = "/product/{productId}")
   public void deleteproduct(@PathVariable("productId") Long productId) {
      dao.delete(productId);
   }

}
