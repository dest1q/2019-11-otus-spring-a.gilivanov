package ru.otus.homework09.service;

import ru.otus.homework09.domain.Comment;
import ru.otus.homework09.exceptions.NoDataFoundException;

import java.util.List;

public interface CommentService {
    List<Comment> show(long bookId) throws NoDataFoundException;
    Comment add(long bookId, String content) throws NoDataFoundException;
}
