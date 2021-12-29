package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
  @Autowired
  private DataSource dataSource;

  // jdbc
  public Customer findId(String username) {
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select * from customer where customerEmail='" + username + "'";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return getCustomer(rs);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return null;
  }

  public List<Customer> findAll() {
    List<Customer> customers = new ArrayList<Customer>();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select customerId, customerName, customerEmail, customerPassword from customer";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        customers.add(getCustomer(rs));
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return customers;
  }

  public Customer getCustomer(ResultSet rs) throws SQLException {
    return new Customer(
        rs.getLong("customerId"),
        rs.getString("customerName"),
        rs.getString("customerEmail"),
        rs.getString("customerPassword"));
  }

  public Customer findUser(String username) {

    try {
      Connection conn = dataSource.getConnection();
      String sql = "select * from customer where customerEmail='" + username + "'";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return getCustomer(rs);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return null;
  }

  public Customer findOne(String id) {

    try {
      Connection conn = dataSource.getConnection();
      String sql = "select * from customer where customerId=" + Integer.parseInt(id);
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return getCustomer(rs);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return null;
  }

  public int insert(Customer customer) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "insert into customer (customerName, customerEmail, customerPassword) values(?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, customer.getCustomerName());
      stmt.setString(2, customer.getCustomerEmail());
      String encoded = new BCryptPasswordEncoder().encode(customer.getCustomerPassword());
      stmt.setString(3, encoded);
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int update(Customer customer) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "update customer set ";
      List<String> params = new ArrayList<String>();

      if (customer.getCustomerName() != "") {
        params.add("customerName= '" + customer.getCustomerName() + "'");

      }
      if (customer.getCustomerEmail() != "") {
        params.add("customerEmail= '" + customer.getCustomerEmail() + "'");
      }
      if (customer.getCustomerPassword() != "") {
        String encoded = new BCryptPasswordEncoder().encode(customer.getCustomerPassword());
        params.add("customerPassword= '" + encoded + "'");
      }

      sql += String.join(",", params);
      sql += " where customerId=" + customer.getCustomerId();
      PreparedStatement stmt = conn.prepareStatement(sql);
      result = stmt.executeUpdate();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // something wrong
      System.out.println(e);
    }
    return result;
  }

  public int delete(Long customerId) {
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "delete from customer where customerId =?";
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