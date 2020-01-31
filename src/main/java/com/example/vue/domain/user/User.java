package com.example.vue.domain.user;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "findByEmail", query = "select u from User u where u.email = :email")
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
