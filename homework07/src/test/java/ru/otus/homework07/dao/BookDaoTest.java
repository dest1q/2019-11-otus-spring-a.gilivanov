package ru.otus.homework07.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework07.dao.impl.AuthorDaoImpl;
import ru.otus.homework07.dao.impl.BookDaoImpl;
import ru.otus.homework07.domain.Author;
import ru.otus.homework07.domain.Book;
import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@DisplayName("Tests for AuthorDao")
@Import(BookDaoImpl.class)
class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    void findById() throws NoDataFoundException {
        assertEquals("Test Book", bookDao.findById(1).getTitle());
    }

    @Test
    void add() {
        Genre genre = new Genre(1, "Test Genre");
        Author author = new Author(1, "Test Author");
        assertNotEquals(0, bookDao.add(new Book("New Book", author, genre)).getId());
    }
}