package com.example.repository;

import com.example.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository<U extends Customer> extends BaseRepository<U> {

    @Modifying
    @Query("update Customer set isActive = false where id = :id")
    void delete(@Param("id") Long id);

    boolean existsByEmail(String email);
}
