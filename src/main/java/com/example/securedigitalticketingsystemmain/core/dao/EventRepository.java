package com.example.securedigitalticketingsystemmain.core.dao;

import com.example.securedigitalticketingsystemmain.core.entity.CustomerEntity;
import com.example.securedigitalticketingsystemmain.core.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<CustomerEntity, Integer> {
}