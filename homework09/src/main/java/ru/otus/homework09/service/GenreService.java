package ru.otus.homework09.service;

import ru.otus.homework09.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findByName(String name);
    Genre add(String name);
}
