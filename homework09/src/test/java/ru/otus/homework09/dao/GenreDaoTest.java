package ru.otus.homework09.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework09.dao.impl.GenreDaoJpa;
import ru.otus.homework09.domain.Genre;
import ru.otus.homework09.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AuthorDao")
@Import(GenreDaoJpa.class)
class GenreDaoTest {

    @Autowired
    GenreDao genreDao;

    @Test
    void findById() throws NoDataFoundException {
        assertEquals("Test Genre", genreDao.findById(1).get().getName());
    }

    @Test
    void add() {
        assertNotEquals(0, genreDao.save(new Genre("New Genre")).getId());
    }
}