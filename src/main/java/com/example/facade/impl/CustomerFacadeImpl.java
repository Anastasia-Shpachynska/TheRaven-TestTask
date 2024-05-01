package com.example.facade.impl;

import com.example.api.data.request.CustomerRequestCreateDto;
import com.example.api.data.request.CustomerRequestUpdateDto;
import com.example.api.data.response.CustomerResponseData;
import com.example.entity.Customer;
import com.example.facade.CustomerFacade;
import com.example.service.CustomerService;
import com.example.util.UniversalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;

    @Override
    public CustomerResponseData create(CustomerRequestCreateDto customerRequestCreateDto) {
        if(customerService.existsByEmail(customerRequestCreateDto.getEmail())) {
            throw new UniversalException("This email is already registered.");
        }
        if(!isValidFullName(customerRequestCreateDto.getFullName()) || !isValidEmail(customerRequestCreateDto.getEmail()) || !isValidPhone(customerRequestCreateDto.getPhone())) {
            throw new UniversalException("Check the correctness of the entered data.");
        }
        Customer customer = new Customer();
        customer.setFullName(customerRequestCreateDto.getFullName());
        customer.setEmail(customerRequestCreateDto.getEmail());
        customer.setPhone(customerRequestCreateDto.getPhone());
        return new CustomerResponseData(customerService.create(customer));
    }

    @Override
    public CustomerResponseData update(Long id, CustomerRequestUpdateDto customerRequestUpdateDto) {
        Customer customer = customerService.findById(id);
        if(!customer.isActive()) {
            throw new UniversalException("It is not possible to edit data for a deleted account.");
        }
        if(!isValidFullName(customerRequestUpdateDto.getFullName()) || !isValidPhone(customerRequestUpdateDto.getPhone())) {
            throw new UniversalException("Check the correctness of the entered data.");
        }
        customer.setFullName(customerRequestUpdateDto.getFullName());
        customer.setPhone(customerRequestUpdateDto.getPhone());
        return new CustomerResponseData(customerService.update(customer));
    }

    @Override
    public CustomerResponseData findById(Long id) {
      return new CustomerResponseData(customerService.findById(id));
    }

    @Override
    public Collection<CustomerResponseData> findAll() {
        return customerService.findAll().stream().map(CustomerResponseData::new).toList();
    }

    @Override
    public void delete(Long id) {
        if(!customerService.findById(id).isActive()) {
            throw new UniversalException("The account has already been deleted.");
        }
        customerService.delete(id);
    }

    @Override
    public boolean isValidFullName(String fullName) {
        return fullName.matches("^(?=.{2,50}$)[a-zA-Z\\s]+$");
    }

    @Override
    public boolean isValidEmail(String email) {
        return email.matches("^(?=.{2,100}$)[^\\s@]+@[^\\s@]+.[^\\s@]{2,}$");
    }

    @Override
    public boolean isValidPhone(String phone) {
        return phone.matches("^(?:\\+[0-9]{5,13})?$");
    }
}
