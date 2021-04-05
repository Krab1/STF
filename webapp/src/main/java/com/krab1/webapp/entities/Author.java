package com.krab1.webapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "NAME")
    String name;
    @Column(name = "LASTNAME")
    String surname;
    public Author() { }
    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
