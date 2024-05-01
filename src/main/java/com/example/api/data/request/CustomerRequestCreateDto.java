package com.example.api.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestCreateDto {
    private String fullName;
    private String email;
    private String phone;
}
