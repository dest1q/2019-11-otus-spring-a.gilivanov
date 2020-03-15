package ru.otus.homework09.dao;

import ru.otus.homework09.domain.Book;
import ru.otus.homework09.domain.Comment;

import java.util.List;

public interface CommentDao {
    Comment add(Book book, String content);
    List<Comment> getComments(Book book);
}
