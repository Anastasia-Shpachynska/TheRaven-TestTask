package com.example.service;

import com.example.entity.Customer;

public interface CustomerService extends CRUDService<Customer> {

    boolean existsByEmail(String email);
}


