package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions() throws QuestionDaoException;

    String askQuestion(Question q);
}
