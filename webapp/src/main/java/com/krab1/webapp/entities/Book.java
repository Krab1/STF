package com.krab1.webapp.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "BOOKS")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "BOOKNAME")
    private String name;
    @Column(name = "PRICE_VALUE")
    private Double price;
    @Basic
    @Column(name = "DATE_OF_ISSUE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateofissue;
    @Transient
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "id")
    private Author author;

    public Book(){}

    public Book(String name, Double price, LocalDate dateofissue, Author author) {
        this.name = name;
        this.price = price;
        this.dateofissue = dateofissue;
        this.author = author;
    }
    public Integer getAge() {
        return Period.between(dateofissue, LocalDate.now()).getYears();
    }
}
