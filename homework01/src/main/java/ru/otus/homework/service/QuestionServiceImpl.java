package ru.otus.homework.service;

import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final InputOutputService inputOutputService;
    private final QuestionDao questionDao;

    public QuestionServiceImpl(InputOutputService inputOutputService, QuestionDao questionDao) {
        this.inputOutputService = inputOutputService;
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() throws QuestionDaoException {
        return this.questionDao.getQuestions();
    }

    @Override
    public String askQuestion(Question q) {
        String answer;
        inputOutputService.printLine(q.getQuestion());
        answer = inputOutputService.readLine();
        return answer;
    }
}
