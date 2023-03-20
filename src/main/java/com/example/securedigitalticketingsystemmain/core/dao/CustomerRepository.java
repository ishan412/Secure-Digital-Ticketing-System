package com.example.securedigitalticketingsystemmain.core.dao;

import com.example.securedigitalticketingsystemmain.core.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query(value = "SELECT * FROM customer WHERE name=?1 AND password=?2", nativeQuery = true)
    CustomerEntity one(String name, String pwd);

    @Query(value = "SELECT * FROM customer WHERE name=?1", nativeQuery = true)
    CustomerEntity one(String name);

}