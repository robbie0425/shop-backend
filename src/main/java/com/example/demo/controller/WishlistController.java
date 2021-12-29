package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.WishlistDAO;
import com.example.demo.entity.Wishlist;

@RestController
@CrossOrigin(value = "http://localhost:3000", maxAge = 1800, allowedHeaders = "*")
public class WishlistController {
   @Autowired
   WishlistDAO dao;

   @GetMapping(value = { "/wishlist" })
   public List<Wishlist> retrieveWishlists() throws SQLException {
      return dao.findAll();
   }

   @GetMapping(value = { "/wishlist/{customerId}" })
   public List<Wishlist> retrieveOneWishlist(@PathVariable("customerId") Long customerId) throws SQLException {
      return dao.findOne(customerId);
   }

   @PostMapping(value = "/wishlist/check")
   public int processFormCheck(@RequestBody Wishlist wishlist) throws SQLException {
      return dao.checkOne(wishlist);
   }

   @PostMapping(value = "/wishlist")
   public int processFormCreate(@RequestBody Wishlist wishlist) throws SQLException {
      return dao.insert(wishlist);
   }

   @PutMapping(value = "/wishlist")
   public void processFormUpdate(@RequestBody Wishlist wishlist) throws SQLException {
      dao.update(wishlist);
   }

   @DeleteMapping(value = "/wishlist/{wishlistId}")
   public int deleteWishlist(@PathVariable("wishlistId") Long wishlistId) throws SQLException {
      return dao.delete(wishlistId);
   }

   @DeleteMapping(value = "/wishlist/customer/{customerId}")
   public int deleteWishlist2(@PathVariable("customerId") Long customerId) throws SQLException {
      return dao.delete2(customerId);
   }
}