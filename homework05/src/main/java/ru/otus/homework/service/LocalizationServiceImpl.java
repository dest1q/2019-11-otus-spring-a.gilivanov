package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.LocalizationConfig;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private Locale locale;

    @Autowired
    public LocalizationServiceImpl(MessageSource messageSource, LocalizationConfig localizationConfig) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localizationConfig.getTag());
    }

    @Override
    public String getMessage(String message, String... params) {
        return this.messageSource.getMessage(message, params, locale);
    }

    @Override
    public void setLocale(String localeTag){
        this.locale = Locale.forLanguageTag(localeTag);
    }
}
