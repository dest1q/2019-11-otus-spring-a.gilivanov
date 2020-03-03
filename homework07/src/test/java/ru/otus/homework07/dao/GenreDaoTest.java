package ru.otus.homework07.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework07.dao.impl.GenreDaoImpl;
import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@DisplayName("Tests for AuthorDao")
@Import(GenreDaoImpl.class)
class GenreDaoTest {

    @Autowired
    GenreDao genreDao;

    @Test
    void findById() throws NoDataFoundException {
        assertEquals("Test Genre", genreDao.findById(1).getName());
    }

    @Test
    void add() {
        assertNotEquals(0, genreDao.add(new Genre("New Genre")).getId());
    }
}