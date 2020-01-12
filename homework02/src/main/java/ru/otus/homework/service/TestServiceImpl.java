package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.HomeworkException;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final InputOutputService inputOutputService;
    private final PersonService personService;
    private final QuestionService questionService;

    @Autowired
    public TestServiceImpl(InputOutputService inputOutputService, PersonService personService, QuestionService questionService) {
        this.inputOutputService = inputOutputService;
        this.personService = personService;
        this.questionService = questionService;
    }

    @Override
    public void run() throws HomeworkException {
        int correct = 0;
        Person person = this.personService.getPerson();
        List<Question> questions = this.questionService.getQuestions();
        for (Question question : questions) {
            if (questionService.askQuestion(question).equals(question.getAnswer())) {
                correct++;
            }
        }
        inputOutputService.printMessage("test.student", person.getLastName(), person.getFirstName());
        inputOutputService.printMessage("test.correct", String.valueOf(correct));
    }
}
