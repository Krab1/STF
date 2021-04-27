package com.krab.stfbase.api.pojos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonSerialize
public class BookImpl {
    Integer id;
    String name;
    Double price;
    LocalDate dateofissue;
    Integer age;
    AuthorImpl author;
}

