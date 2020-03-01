package ru.otus.homework.service;

public interface LocalizationService {
    String getMessage(String message, String...params);
    void setLocale(String localeTag);
}
