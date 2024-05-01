package com.example.facade;

import com.example.api.data.request.CustomerRequestCreateDto;
import com.example.api.data.request.CustomerRequestUpdateDto;
import com.example.api.data.response.CustomerResponseData;

public interface CustomerFacade extends CRUDFacade<CustomerRequestCreateDto, CustomerRequestUpdateDto, CustomerResponseData> {
    boolean isValidFullName(String fullName);
    boolean isValidEmail(String email);
    boolean isValidPhone(String phone);
}
