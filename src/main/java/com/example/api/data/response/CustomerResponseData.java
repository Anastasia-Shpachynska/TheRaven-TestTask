package com.example.api.data.response;

import com.example.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseData {
    private Long id;
    private String fullName;
    private String email;
    private String phone;

    public CustomerResponseData() {

    }

    public CustomerResponseData(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }
}
