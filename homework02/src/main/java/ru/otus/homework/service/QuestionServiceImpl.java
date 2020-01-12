package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final InputOutputService inputOutputService;
    private final QuestionDao questionDao;

    @Autowired
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
