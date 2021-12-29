package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.Wishlist;

import org.springframework.stereotype.Repository;

@Repository
public interface WishlistDAO {
  public List<Wishlist> findOne(Long customerId);

  public List<Wishlist> findAll();

  public int checkOne(Wishlist wishlist);

  public int insert(Wishlist wishlist);

  public int update(Wishlist wishlist);

  public int delete(Long wishlistId);

  public int delete2(Long customerId);
}