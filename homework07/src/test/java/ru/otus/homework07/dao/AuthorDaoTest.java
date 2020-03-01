package ru.otus.homework07.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework07.dao.impl.AuthorDaoImpl;
import ru.otus.homework07.domain.Author;
import ru.otus.homework07.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@DisplayName("Tests for AuthorDao")
@Import(AuthorDaoImpl.class)
class AuthorDaoTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void shouldFindById() throws NoDataFoundException{
        assertEquals("Test Author", authorDao.findById(1).getFullName());
    }

    @Test
    void shouldAdd() {
        assertNotEquals(0, authorDao.add(new Author("New Author")).getId());
    }
}