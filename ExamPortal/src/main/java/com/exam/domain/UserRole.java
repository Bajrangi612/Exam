package com.exam.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

    @ManyToOne
    private Role role;
}
