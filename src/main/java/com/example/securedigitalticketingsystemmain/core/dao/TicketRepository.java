package com.example.securedigitalticketingsystemmain.core.dao;

import com.example.securedigitalticketingsystemmain.core.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<CustomerEntity, Integer> {
}
