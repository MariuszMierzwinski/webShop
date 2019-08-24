package com.example.webshop.models;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Eager
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")

    private Address address;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "sure_name")
    private String sureName;
    private String login;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public User(String login) {
        this.login = login;
    }


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
