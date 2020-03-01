package ru.otus.homework07.dao;

import ru.otus.homework07.domain.Book;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.util.List;

public interface BookDao {
    Book findById(int id) throws NoDataFoundException;
    Book findByName(String name) throws NoDataFoundException;
    List<Book> findAll();
    Book add(Book book);
    void update(Book book);
    void delete(int id);
}
