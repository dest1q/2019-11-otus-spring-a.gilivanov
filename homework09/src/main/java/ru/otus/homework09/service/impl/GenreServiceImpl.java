package ru.otus.homework09.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework09.dao.GenreDao;
import ru.otus.homework09.domain.Genre;
import ru.otus.homework09.service.GenreService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public Optional<Genre> findByName(String name) {
        return genreDao.findByName(name);
    }

    @Override
    public Genre add(String name) {
        return genreDao.save(new Genre(name));
    }
}
