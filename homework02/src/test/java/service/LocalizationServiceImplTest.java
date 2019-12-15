package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис локализации")
public class LocalizationServiceImplTest {
    @DisplayName("Возвращается корректное сообшение")
    @Test
    void returningCorrectMessage(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        LocalizationService localizationService = new LocalizationServiceImpl(messageSource, "ru-RU");
        assertEquals("тест", localizationService.getMessage("тест"));
    }
}
