package ru.otus.homework07.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Author {
    private int id;
    private String fullName;

    public Author(String fullName){
        this.fullName = fullName;
    }
}
