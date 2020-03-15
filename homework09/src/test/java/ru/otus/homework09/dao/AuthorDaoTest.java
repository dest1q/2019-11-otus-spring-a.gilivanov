package ru.otus.homework09.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework09.dao.impl.AuthorDaoJpa;
import ru.otus.homework09.domain.Author;
import ru.otus.homework09.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AuthorDao")
@Import(AuthorDaoJpa.class)
class AuthorDaoTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void shouldFindById() throws NoDataFoundException{
        assertEquals("Test Author", authorDao.findById(1).get().getFullName());
    }

    @Test
    void shouldAdd() {
        assertNotEquals(0, authorDao.save(new Author("New Author")).getId());
    }
}