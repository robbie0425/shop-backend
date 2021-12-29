package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.Customer;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO {
  public Customer findId(String username);

  public List<Customer> findAll();

  public Customer findUser(String username);

  public Customer findOne(String id);

  public int insert(Customer customer);

  public int update(Customer customer);

  public int delete(Long customerId);
}