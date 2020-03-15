package ru.otus.homework09.service;

import ru.otus.homework09.domain.Book;
import ru.otus.homework09.exceptions.NoDataFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findBook(String title);
    List<Book> findAllBooks();
    Book addBook(String title, String authorName, String genreName);
    void updateBook(long id, String title, String authorName, String genreName) throws NoDataFoundException;
    void deleteBook(long id);
}
