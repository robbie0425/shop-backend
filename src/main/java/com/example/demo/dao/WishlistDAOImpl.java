package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Wishlist;

@Repository
public class WishlistDAOImpl implements WishlistDAO {
  @Autowired
  private DataSource dataSource;

  // jdbc
  public List<Wishlist> findOne(Long customerId) {
    List<Wishlist> wishlists = new ArrayList<Wishlist>();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select wishlistId, customerId, productId from wishlist where customerId = " + customerId + "";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        wishlists.add(getWishlist(rs));
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return wishlists;
  }

  public int checkOne(Wishlist wishlist) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select wishlistId, customerId, productId from wishlist where customerId = ? and productId=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, wishlist.getCustomerId());
      stmt.setInt(2, wishlist.getProductId());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = 1;
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public List<Wishlist> findAll() {
    List<Wishlist> wishlists = new ArrayList<Wishlist>();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select wishlistId, customerId, productId, from wishlist";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        wishlists.add(getWishlist(rs));
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return wishlists;
  }

  public Wishlist getWishlist(ResultSet rs) throws SQLException {
    return new Wishlist(
        rs.getLong("wishlistId"),
        rs.getInt("customerId"),
        rs.getInt("productId"));
  }

  public int insert(Wishlist wishlist) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "insert into wishlist (customerId, productId) values(?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, wishlist.getCustomerId());
      stmt.setInt(2, wishlist.getProductId());
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int update(Wishlist wishlist) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "update wishlist set customerId=? , productId=?  where wishlistId=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(3, wishlist.getWishlistId());
      stmt.setInt(1, wishlist.getCustomerId());
      stmt.setInt(2, wishlist.getProductId());
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int delete(Long wishlistId) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "delete from wishlist where wishlistId =?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, wishlistId);
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int delete2(Long customerId) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "delete from wishlist where customerId =?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, customerId);
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }
}