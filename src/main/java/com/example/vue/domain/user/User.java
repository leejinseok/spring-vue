package com.example.vue.domain.user;

import com.example.vue.domain.auth.RegisterRequestDto;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NamedQuery(name = "findByEmail", query = "select u from User u where u.email = :email")
@NamedQuery(name = "findAllUser", query = "select u from User u order by u.id asc")
@NoArgsConstructor
public class User  {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Transient
    private String role;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User(Claims claims) {
        this.id = Long.valueOf(claims.get("userId").toString());
        this.name = claims.get("name").toString();
        this.role = claims.get("role").toString();
    }

    public User(RegisterRequestDto registerRequestDto) {
        this.password = registerRequestDto.getPassword();
        this.email = registerRequestDto.getEmail();
        this.name = registerRequestDto.getName();
        this.createdAt = LocalDateTime.now();
    }

}
