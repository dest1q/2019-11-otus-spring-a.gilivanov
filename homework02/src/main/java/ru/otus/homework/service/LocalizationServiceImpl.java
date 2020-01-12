package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final Locale locale;

    @Autowired
    public LocalizationServiceImpl(MessageSource messageSource, @Value("${locale.tag}") String localeTag) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public String getMessage(String message, String... params) {
        return this.messageSource.getMessage(message, params, locale);
    }
}
