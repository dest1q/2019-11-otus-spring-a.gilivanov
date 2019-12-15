package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {
    private final InputOutputService inputOutputService;

    @Autowired
    public PersonServiceImpl(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    @Override
    public Person getPerson() {
        String lastName;
        String firstName;
        inputOutputService.printMessage("person.last_name_request");
        lastName = inputOutputService.readLine();
        inputOutputService.printMessage("person.first_name_request");
        firstName = inputOutputService.readLine();
        return new Person(lastName, firstName);
    }
}
