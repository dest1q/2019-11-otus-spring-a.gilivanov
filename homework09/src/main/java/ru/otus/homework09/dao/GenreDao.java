package ru.otus.homework09.dao;

import ru.otus.homework09.domain.Genre;

import java.util.Optional;


public interface GenreDao {
    Optional<Genre> findById(long id);
    Optional<Genre> findByName(String name);
    Genre save(Genre genre);
}
