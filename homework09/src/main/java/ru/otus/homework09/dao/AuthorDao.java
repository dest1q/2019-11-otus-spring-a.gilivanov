package ru.otus.homework09.dao;

import ru.otus.homework09.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);
    Optional<Author> findByName(String name);
    Author save(Author author);
}
