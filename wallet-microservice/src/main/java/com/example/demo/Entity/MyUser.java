package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class MyUser {
    @Id
    @Column(name="id",length = 25)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",length = 255, nullable = false, unique = true)
    private String username;

    @Column(name="password",length = 255, nullable = false)
    private String password;

    @Column(name="email",length = 255, nullable = false, unique = true)
    private String email;

    @Column(name="role",length = 255, nullable = false)
    private String role;

    public MyUser() {
    }

    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
