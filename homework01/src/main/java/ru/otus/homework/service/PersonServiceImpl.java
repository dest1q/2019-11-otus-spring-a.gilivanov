package ru.otus.homework.service;

import ru.otus.homework.domain.Person;

public class PersonServiceImpl implements PersonService {
    private final InputOutputService inputOutputService;

    public PersonServiceImpl(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    @Override
    public Person getPerson() {
        String lastName;
        String firstName;
        inputOutputService.printLine("What's your last name?");
        lastName = inputOutputService.readLine();
        inputOutputService.printLine("What's your first name?");
        firstName = inputOutputService.readLine();
        return new Person(lastName, firstName);
    }
}
