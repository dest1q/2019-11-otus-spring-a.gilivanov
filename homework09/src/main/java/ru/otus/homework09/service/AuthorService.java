package ru.otus.homework09.service;

import ru.otus.homework09.domain.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findByName(String name);
    Author add(String name);
}
