package ru.otus.homework.service;

public interface InputOutputService {
    void printLine(String s);
    void printMessage(String message, String...params);

    String readLine();
}
