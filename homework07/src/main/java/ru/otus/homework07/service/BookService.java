package ru.otus.homework07.service;

import ru.otus.homework07.domain.Book;
import ru.otus.homework07.exceptions.NoDataFoundException;

import java.util.List;

public interface BookService {
    Book findBook(String title) throws NoDataFoundException;
    List<Book> findAllBooks();
    Book addBook(String title, String authorName, String genreName);
    void updateBook(int id, String title, String authorName, String genreName);
    void deleteBook(int id);
}
