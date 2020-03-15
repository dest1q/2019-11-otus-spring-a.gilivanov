package ru.otus.homework09.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework09.dao.AuthorDao;
import ru.otus.homework09.domain.Author;
import ru.otus.homework09.service.AuthorService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public Optional<Author> findByName(String name) {
        return authorDao.findByName(name);
    }

    @Override
    public Author add(String name) {
        return authorDao.save(new Author(name));
    }
}
