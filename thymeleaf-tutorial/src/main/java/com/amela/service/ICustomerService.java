package com.amela.service;

import com.amela.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    List<Customer> findByName(String name);

    void update(int id, Customer customer);

    void remove(int id);
}