package com.example.securedigitalticketingsystemmain.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "event", schema = "public", catalog = "postgres")
public class EventEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic
    @Column(name = "startdatetime", nullable = true)
    private Timestamp startdatetime;
    @Basic
    @Column(name = "enddatetime", nullable = true)
    private Timestamp enddatetime;
    }