package com.example.securedigitalticketingsystemmain.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer", schema = "public", catalog = "postgres")
public class CustomerEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic
    @Column(name = "email", nullable = true, length = 20)
    private String email;

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    private String password;
}
