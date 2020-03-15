package ru.otus.homework09.dao;

import ru.otus.homework09.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(long id);
    Optional<Book> findByName(String name);
    List<Book> findAll();
    Book save(Book book);
    void delete(long id);
}
