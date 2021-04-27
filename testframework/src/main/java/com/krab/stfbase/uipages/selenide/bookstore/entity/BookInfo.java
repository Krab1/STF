package com.krab.stfbase.uipages.selenide.bookstore.entity;

import com.codeborne.selenide.SelenideElement;
import com.krab.stfbase.uipages.selenide.bookstore.EditBookPage;
import com.krab.stfbase.uipages.selenide.bookstore.MainPage;
import lombok.SneakyThrows;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

public class BookInfo {
    String baseXpath;
    long id;
    String name;
    double price;
    int age;
    String author;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAge() {
        return age;
    }

    public String getAuthor() {
        return author;
    }
    @SneakyThrows
    public static BookInfo Parse(SelenideElement row) {
       List<String> columns = row.$$x("td")
               .stream()
               .map(SelenideElement::getText)
               .collect(Collectors.toList());
        if(columns.size() > 4) {
            return new BookInfo(Long.parseLong(columns.get(0)), columns.get(1), NumberFormat.getInstance(Locale.getDefault()).parse(columns.get(2)).doubleValue(), Integer.parseInt(columns.get(3)), columns.get(4));
        }
        return null;
    }
    public BookInfo(long id, String name, double price, int age, String author){
        this.id = id;
        this.name = name;
        this.price = price;
        this.age = age;
        this.author = author;
        this.baseXpath = "//*[@id='tablelistbooks']//tr[td[1]/text()[contains(., "+ id + ")]]";
    }

    public String getBaseXpath() {
        return baseXpath;
    }
}
