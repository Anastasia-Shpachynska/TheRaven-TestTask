package com.example.service.impl;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.example.util.UniversalException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer entity) {
        entity.setCreated(System.currentTimeMillis());
        entity.setUpdated(System.currentTimeMillis());
        entity.setActive(true);
        Customer savedCustomer = (Customer) customerRepository.save(entity);
        return savedCustomer;
    }

    @Transactional
    @Override
    public Customer update(Customer entity) {
        entity.setUpdated(System.currentTimeMillis());
        return (Customer) customerRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer findById(Long id) {
        try {
            return (Customer) customerRepository.findById(id)
                    .orElseThrow(() -> new UniversalException("Customer with id " + id + " not found."));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
