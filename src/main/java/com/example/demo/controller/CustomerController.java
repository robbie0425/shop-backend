package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.Customer;

@RestController
@CrossOrigin(value = "http://localhost:3000", maxAge = 1800, allowedHeaders = "*")
public class CustomerController {
   @Autowired
   CustomerDAO dao;

   @GetMapping(value = { "/customer" })
   public List<Customer> retrieveCustomers() throws SQLException {
      return dao.findAll();
   }

   @PostMapping(value = { "/customer/account" })
   public Customer retrieveOneCustomer(@RequestBody String id) throws SQLException {
      return dao.findOne(id);
   }

   @PostMapping(value = "/customer/login")
   public Customer processFormLogin(@RequestBody String username) throws SQLException {
      return dao.findId(username);
   }

   @PostMapping(value = "/customer/register")
   public void processFormCreate(@RequestBody Customer customer) throws SQLException {
      dao.insert(customer);
   }

   @PutMapping(value = "/customer/update")
   public int processFormUpdate(@RequestBody Customer customer) throws SQLException {
      return dao.update(customer);
   }

   @DeleteMapping(value = "/customer/{customerId}")
   public int deleteCustomer(@PathVariable("customerId") Long customerId) throws SQLException {
      return dao.delete(customerId);
   }
}