package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionDaoException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private final CSVReader reader;

    public QuestionDaoImpl(Resource resource) throws QuestionDaoException {
        try {
            this.reader = new CSVReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            throw new QuestionDaoException("Error opening CSV");
        }
    }

    @Override
    public List<Question> getQuestions() throws QuestionDaoException {
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
