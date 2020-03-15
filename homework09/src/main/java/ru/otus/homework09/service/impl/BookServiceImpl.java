package ru.otus.homework09.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework09.dao.BookDao;
import ru.otus.homework09.domain.Author;
import ru.otus.homework09.domain.Book;
import ru.otus.homework09.domain.Genre;
import ru.otus.homework09.exceptions.NoDataFoundException;
import ru.otus.homework09.service.AuthorService;
import ru.otus.homework09.service.BookService;
import ru.otus.homework09.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Optional<Book> findBook(String title){
        return bookDao.findByName(title);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book addBook(String title, String authorName, String genreName) {
        return bookDao.save(new Book(title, findOrCreateAuthor(authorName), findOrCreateGenre(genreName)));
    }

    @Override
    public void updateBook(long id, String title, String authorName, String genreName) throws NoDataFoundException{
        Optional<Book> book = bookDao.findById(id);
        if (book.isEmpty()){
            throw new NoDataFoundException("not found");
        }else{
            Book updatingBook = book.get();
            updatingBook.setAuthor(findOrCreateAuthor(authorName));
            updatingBook.setGenre(findOrCreateGenre(genreName));
            bookDao.save(updatingBook);
        }
    }

    @Override
    public void deleteBook(long id) {
        bookDao.delete(id);
    }

    private Author findOrCreateAuthor(String authorName){
        Optional<Author> author = authorService.findByName(authorName);
        Author bookAuthor;
        if (author.isEmpty()){
            bookAuthor = authorService.add(authorName);
        }else {
            bookAuthor = author.get();
        }
        return bookAuthor;
    }

    private Genre findOrCreateGenre(String genreName){
        Optional<Genre> genre = genreService.findByName(genreName);
        Genre bookGenre;
        if (genre.isEmpty()){
            bookGenre = genreService.add(genreName);
        }else{
            bookGenre = genre.get();
        }
        return bookGenre;
    }
}
