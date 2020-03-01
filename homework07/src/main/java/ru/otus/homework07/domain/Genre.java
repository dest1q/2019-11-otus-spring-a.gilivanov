package ru.otus.homework07.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Genre {
    private int id;
    private String name;

    public Genre(String name){
        this.name = name;
    }
}
