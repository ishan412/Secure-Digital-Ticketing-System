package com.example.securedigitalticketingsystemmain.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ticket", schema = "public", catalog = "postgres")
public class TicketEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "event_id", nullable = true)
    private Long eventId;
    @Basic
    @Column(name = "customer_id", nullable = true)
    private Long customerId;
}
