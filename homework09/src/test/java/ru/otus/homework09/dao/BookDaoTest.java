package ru.otus.homework09.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework09.dao.impl.BookDaoJpa;
import ru.otus.homework09.domain.Author;
import ru.otus.homework09.domain.Book;
import ru.otus.homework09.domain.Genre;
import ru.otus.homework09.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AuthorDao")
@Import(BookDaoJpa.class)
class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    void findById() throws NoDataFoundException {
        assertEquals("Test Book", bookDao.findById(1).get().getTitle());
    }

    @Test
    void add() {
        Genre genre = new Genre(1, "Test Genre");
        Author author = new Author(1, "Test Author");
        assertNotEquals(0, bookDao.save(new Book("New Book", author, genre)).getId());
    }
}