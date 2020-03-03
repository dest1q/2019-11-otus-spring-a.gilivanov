package ru.otus.homework07.dao;

import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.util.List;

public interface GenreDao {
    Genre findById(int id) throws NoDataFoundException;
    Genre findByName(String name) throws NoDataFoundException;
    List<Genre> findAll();
    Genre add(Genre genre);
    void update(Genre genre);
}
