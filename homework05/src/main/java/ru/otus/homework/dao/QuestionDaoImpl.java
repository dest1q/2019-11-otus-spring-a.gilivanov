package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;
import ru.otus.homework.service.LocalizationService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private final LocalizationService localizationService;

    @Autowired
    public QuestionDaoImpl(LocalizationService localizationService) throws QuestionDaoException {
        this.localizationService = localizationService;
    }

    @Override
    public List<Question> getQuestions() throws QuestionDaoException {
        CSVReader reader;
        Resource resource = new ClassPathResource(localizationService.getMessage("questions.filename"));
        try {
            reader = new CSVReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            throw new QuestionDaoException("Error opening CSV");
        }
        List<Question> questions = new ArrayList<>();
        List<String[]> records;
        try {
            records = reader.readAll();
        } catch (CsvException e) {
            throw new QuestionDaoException("Error reading CSV");
        } catch (IOException e) {
            throw new QuestionDaoException("Error reading CSV (IO Error)");
        }
        for (String[] record : records) {
            questions.add(new Question(record[0], record[1]));
        }
        return questions;
    }
}
