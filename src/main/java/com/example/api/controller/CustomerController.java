package com.example.api.controller;

import com.example.api.data.request.CustomerRequestCreateDto;
import com.example.api.data.request.CustomerRequestUpdateDto;
import com.example.api.data.response.CustomerResponseData;
import com.example.api.data.response.ResponseData;
import com.example.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<ResponseData<CustomerResponseData>> create(@RequestBody CustomerRequestCreateDto customerRequestCreateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData<>(customerFacade.create(customerRequestCreateDto)));
    }

    @GetMapping
    public ResponseEntity<ResponseData<Collection<CustomerResponseData>>> findAll() {
        return ResponseEntity.ok(new ResponseData<>(customerFacade.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<CustomerResponseData>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseData<>(customerFacade.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<CustomerResponseData>> update(@PathVariable Long id, @RequestBody CustomerRequestUpdateDto customerRequestUpdateDto) {
        return ResponseEntity.ok(new ResponseData<>(customerFacade.update(id, customerRequestUpdateDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerFacade.delete(id);
        return ResponseEntity.noContent().build();
    }
}
