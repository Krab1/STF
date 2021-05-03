package com.krab.stfbase.api.pojos;

import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

