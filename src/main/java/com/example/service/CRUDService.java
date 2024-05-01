package com.example.service;

import com.example.entity.BaseEntity;

import java.util.Collection;

public interface CRUDService <E extends BaseEntity> {

    E create(E entity);
    E update(E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
