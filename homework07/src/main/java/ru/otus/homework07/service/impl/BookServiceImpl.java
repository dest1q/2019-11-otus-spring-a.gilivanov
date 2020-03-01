package ru.otus.homework07.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework07.dao.AuthorDao;
import ru.otus.homework07.dao.BookDao;
import ru.otus.homework07.dao.GenreDao;
import ru.otus.homework07.domain.Author;
import ru.otus.homework07.domain.Book;
import ru.otus.homework07.domain.Genre;
import ru.otus.homework07.exceptions.NoDataFoundException;
import ru.otus.homework07.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public Book findBook(String title) throws NoDataFoundException {
        return bookDao.findByName(title);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book addBook(String title, String authorName, String genreName) {
        return bookDao.add(new Book(title, getAuthorByName(authorName), getGenreByName(genreName)));
    }

    @Override
    public void updateBook(int id, String title, String authorName, String genreName){
        bookDao.update(new Book(id, title, getAuthorByName(authorName), getGenreByName(genreName)));
    }

    @Override
    public void deleteBook(int id) {
        bookDao.delete(id);
    }

    private Genre getGenreByName(String genreName){
        Genre genre;
        try {
            genre = genreDao.findByName(genreName);
        } catch (NoDataFoundException e) {
            genre = genreDao.add(new Genre(genreName));
        }
        return genre;
    }

    private Author getAuthorByName(String authorName){
        Author author;
        try {
            author = authorDao.findByName(authorName);
        } catch (NoDataFoundException e) {
            author = authorDao.add(new Author(authorName));
        }
        return author;
    }
}
