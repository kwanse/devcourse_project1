package com.grepp.domain.admin;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;
}
