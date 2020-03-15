package ru.otus.homework09.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework09.dao.BookDao;
import ru.otus.homework09.dao.CommentDao;
import ru.otus.homework09.domain.Book;
import ru.otus.homework09.domain.Comment;
import ru.otus.homework09.exceptions.NoDataFoundException;
import ru.otus.homework09.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    public List<Comment> show(long bookId) throws NoDataFoundException {
        Optional<Book> book = bookDao.findById(bookId);
        if (book.isEmpty()){
            throw new NoDataFoundException("not found");
        } else {
            return commentDao.getComments(book.get());
        }
    }

    @Override
    public Comment add(long bookId, String content) throws NoDataFoundException {
        Optional<Book> book = bookDao.findById(bookId);
        if (book.isEmpty()){
            throw new NoDataFoundException("not found");
        } else {
            return commentDao.add(book.get(), content);
        }
    }
}
