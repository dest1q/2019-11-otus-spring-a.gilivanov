package ru.otus.homework07.dao;

import ru.otus.homework07.domain.Author;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.util.List;

public interface AuthorDao {
    Author findById(int id) throws NoDataFoundException;
    Author findByName(String name) throws NoDataFoundException;
    List<Author> findAll();
    Author add(Author author);
    void update(Author author);
}
