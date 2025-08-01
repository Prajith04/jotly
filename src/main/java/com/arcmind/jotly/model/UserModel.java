package com.arcmind.jotly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class UserModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Setter
    private String username;
    @Setter
    @Column(nullable = false)
    private String password;
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

