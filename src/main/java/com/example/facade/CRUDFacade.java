package com.example.facade;

import java.util.Collection;

public interface CRUDFacade<REQUEST_CREATE, REQUEST_UPDATE, RESPONSE> {
    RESPONSE create(REQUEST_CREATE request);
    RESPONSE update(Long id, REQUEST_UPDATE request);
    RESPONSE findById(Long id);
    Collection<RESPONSE> findAll();
    void delete(Long id);
}
