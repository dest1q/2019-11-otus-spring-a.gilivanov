package ru.otus.homework.dao;

import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws QuestionDaoException;
}
