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

import com.example.demo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
  @Autowired
  private DataSource dataSource;

  // jdbc
  public Product findOne(Long productId) {
    Product product = new Product();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select productId, productCategory, productName, productPrice, productCount, productNew, productImg from product where productId = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, productId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        product = getProduct(rs);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return product;
  }

  public List<Product> findAll() {
    List<Product> products = new ArrayList<Product>();
    try {
      String sql = "select productId, productCategory, productName, productPrice, productCount, productNew, productImg from product";
      try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
          while (rs.next()) {
            products.add(getProduct(rs));
          }
          rs.close();
        }
        stmt.close();
        conn.close();
      }
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return products;
  }

  // Connection conn = dataSource.getConnection();
  // String sql = "select productId, productCategory, productName, productPrice,
  // productCount, productNew, productImg from product";
  // PreparedStatement stmt = conn.prepareStatement(sql);
  // ResultSet rs = stmt.executeQuery();
  // while (rs.next()) {
  // products.add(getProduct(rs));
  // }
  // conn.close();
  // } catch (Exception e) {
  // // something wrong
  // System.out.println(e);
  // }
  // return products;
  // }

  public List<Product> findProduct(String searchInput) {
    List<Product> products = new ArrayList<Product>();
    try {
      String sql = "select * from product where productName like '%" + searchInput + "%' or productPrice like '%"
          + searchInput + "%' or productCategory like '%" + searchInput + "%'";
      try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
          while (rs.next()) {
            products.add(getProduct(rs));
          }
          rs.close();
        }
        stmt.close();
        conn.close();
      }
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return products;
  }

  public Product getProduct(ResultSet rs) throws SQLException {
    return new Product(
        rs.getLong("productId"),
        rs.getString("productName"),
        rs.getLong("productPrice"),
        rs.getString("productCategory"),
        rs.getLong("productCount"),
        rs.getString("productNew"),
        rs.getString("productImg"));
  }

  public int insert(Product product) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "insert into product (productId, productCategory, productName, productPrice, productCount, productNew, productImg) values(?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, product.getProductId());
      stmt.setString(2, product.getProductCategory());
      stmt.setString(3, product.getProductName());
      stmt.setLong(4, product.getProductPrice());
      stmt.setLong(5, product.getProductCount());
      stmt.setString(6, product.getProductNew());
      stmt.setString(7, product.getProductImg());
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int update(Product product) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "update product set productId=?, productCategory=?, productName=?, productPrice=?, productCount=?, productNew=?, productImg=? where productId =?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(7, product.getProductId());
      stmt.setString(1, product.getProductCategory());
      stmt.setString(2, product.getProductName());
      stmt.setLong(3, product.getProductPrice());
      stmt.setLong(4, product.getProductCount());
      stmt.setString(5, product.getProductNew());
      stmt.setString(6, product.getProductImg());
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int delete(Long id) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "delete from product where productId =?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
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